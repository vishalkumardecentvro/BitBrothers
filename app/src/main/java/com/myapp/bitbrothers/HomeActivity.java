package com.myapp.bitbrothers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
  private FirebaseAuth firebaseAuth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    instantiate();
    initialize();
    listen();
    load();
  }

  private void instantiate() {
    getSupportActionBar().setTitle("Home page");
    firebaseAuth = FirebaseAuth.getInstance();

  }

  private void initialize() {

  }

  private void listen() {

  }

  private void load() {

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.home_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.log_out:
        logOut();
    }
    return super.onOptionsItemSelected(item);
  }

  private void logOut() {
    firebaseAuth.signOut();
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }
}

