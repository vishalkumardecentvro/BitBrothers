package com.myapp.bitbrothers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
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

  }

  private void load() {

  }
}