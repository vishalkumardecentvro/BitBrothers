package com.myapp.bitbrothers.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.myapp.bitbrothers.HomeActivity;
import com.myapp.bitbrothers.R;
import com.myapp.bitbrothers.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {
  private FragmentLoginBinding binding;
  private FirebaseAuth firebaseAuth;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentLoginBinding.inflate(inflater, container, false);
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
    if (firebaseAuth.getCurrentUser() != null) {
      startActivity(new Intent(getContext(), HomeActivity.class));
    }

  }

  private void initialise() {

  }

  private void listen() {
    binding.loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        processLogIn();
      }
    });

    binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new SignUpFragment());
        fragmentTransaction.commit();
      }
    });

  }

  private void load() {

  }

  private void processLogIn() {
    if (binding.tilEmail.getEditText().getText().toString().isEmpty() || binding.tilPassword.getEditText().getText().toString().isEmpty()) {
      Toast.makeText(getContext(), "Please enter valid credentials", Toast.LENGTH_SHORT).show();
      return;
    }

    String email = binding.tilEmail.getEditText().getText().toString();
    String password = binding.tilPassword.getEditText().getText().toString();

    firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
      @Override
      public void onSuccess(AuthResult authResult) {
        startActivity(new Intent(getContext(), HomeActivity.class));
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "Given email or password is incorrect!", Toast.LENGTH_SHORT).show();
      }
    });
  }
}