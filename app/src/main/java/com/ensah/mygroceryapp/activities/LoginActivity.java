package com.ensah.mygroceryapp.activities;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.User;
import com.ensah.mygroceryapp.sidebarMenu;

public class LoginActivity extends AppCompatActivity {
   private DatabaseHelper Helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login=(Button)findViewById(R.id.idBtnRegister);
        Button register=(Button) findViewById(R.id.signup);
        EditText usermail=(EditText)findViewById(R.id.idEdtUserName);
        EditText password=(EditText)findViewById(R.id.idEdtPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail= String.valueOf(usermail.getText());
                String paswrd= String.valueOf(password.getText());
                Helper = new DatabaseHelper(LoginActivity.this);
                if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(paswrd)){
                    if(Helper.CheckUser(mail,paswrd)){
                    Intent i =new Intent(getApplicationContext(), sidebarMenu.class);
                    startActivity(i);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "User not found please register if you don't have an account!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                }
                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i =new Intent(getApplicationContext(), RegistrationActivity.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}