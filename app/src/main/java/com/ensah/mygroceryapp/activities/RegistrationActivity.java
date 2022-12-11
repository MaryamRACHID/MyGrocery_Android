package com.ensah.mygroceryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.User;
import com.ensah.mygroceryapp.ui.categories.CategoriesFragment;

public class RegistrationActivity extends AppCompatActivity {
      EditText name ,email,password;
      Button signUp ;
      Button signIn;
      Button registration;
    private DatabaseHelper Helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registration=(Button)findViewById(R.id.idBtnRegister);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=findViewById(R.id.ideditName);
                email=findViewById(R.id.idEdtUserName);
                password=findViewById(R.id.idEdtPassword);
                String nameuser= String.valueOf(name.getText());
                String emailuser= String.valueOf(email.getText());
                String passworduser= String.valueOf(password.getText());
                if(!TextUtils.isEmpty(nameuser) && !TextUtils.isEmpty(emailuser) && !TextUtils.isEmpty(passworduser)){
                    Helper=new DatabaseHelper(RegistrationActivity.this);
                    User u=new User(emailuser,nameuser,passworduser);
                    if(Helper.createUSER(u)){
                        Toast.makeText(RegistrationActivity.this, "succefuly registered!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        signIn=(Button) findViewById(R.id.signin);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}