package com.ensah.mygroceryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.sidebarMenu;

public class LoginActivity extends AppCompatActivity {
   private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DatabaseHelper(LoginActivity.this);
        Button login=(Button)findViewById(R.id.idBtnRegister);
        EditText usermail=(EditText)findViewById(R.id.idEdtUserName);
        EditText password=(EditText)findViewById(R.id.idEdtPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail= String.valueOf(usermail.getText());
                String paswrd= String.valueOf(password.getText());
                Intent i =new Intent(getApplicationContext(), sidebarMenu.class);
                startActivity(i);
            }
        });
    }
}