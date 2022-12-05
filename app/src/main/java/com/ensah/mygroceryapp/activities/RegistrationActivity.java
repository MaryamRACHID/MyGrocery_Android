package com.ensah.mygroceryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ensah.mygroceryapp.R;

public class RegistrationActivity extends AppCompatActivity {
      EditText name ,email,password;
      Button signUp ;
      TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}