package com.myapp.bitbrothers.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.myapp.bitbrothers.R;
import com.myapp.bitbrothers.databinding.FragmentSignUpBinding;


public class SignUpFragment extends Fragment {
  private FragmentSignUpBinding binding;
  private FirebaseAuth firebaseAuth;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = FragmentSignUpBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    instantiate();
    initialise();
    listen();
    load();
  }

  private void instantiate() {
    firebaseAuth = FirebaseAuth.getInstance();
  }

  private void initialise() {

  }

  private void listen() {
    binding.signButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        processSignIn();
      }
    });

  }

  private void load() {

  }

  private void processSignIn() {
    if (binding.tilEmail.getEditText().getText().toString().isEmpty()
            || binding.tilPassword.getEditText().getText().toString().isEmpty()
            || binding.tilName.getEditText().getText().toString().isEmpty()) {
      Toast.makeText(getContext(), "Please enter valid credentials", Toast.LENGTH_SHORT).show();
      return;
    }

    String email = binding.tilEmail.getEditText().getText().toString();
    String password = binding.tilPassword.getEditText().getText().toString();
    if(password.length()<6)
      Toast.makeText(getContext(), "Password length should be greater than 6!", Toast.LENGTH_SHORT).show();

    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
      @Override
      public void onSuccess(AuthResult authResult) {
        Toast.makeText(getContext(), "Successfully registered! Please login!", Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new LoginFragment());
        fragmentTransaction.commit();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Log.e("--error--",e.toString());
        Toast.makeText(getContext(), "Given email is already registered!", Toast.LENGTH_SHORT).show();
      }
    });
  }
}