package com.gtoz.uxsocialmedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity {
    private EditText edtTxtUsername, edtTxtPassword;
    private ImageButton imgBtnFacebook, imgBtnTwitter;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Components
        edtTxtUsername = (EditText) findViewById(R.id.edtTxtUsername);
        edtTxtPassword = (EditText) findViewById(R.id.edtTxtPassword);
        imgBtnFacebook = (ImageButton) findViewById(R.id.imgBtnFacebook);
        imgBtnTwitter = (ImageButton) findViewById(R.id.imgBtnTwitter);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        // Button click listeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get username and password from text fields
                String username = edtTxtUsername.getText().toString();
                String password = edtTxtPassword.getText().toString();

                // Load main activity
                Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginIntent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Load main activity
                Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginIntent);
            }
        });

        imgBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Load main activity
                Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginIntent);
            }
        });

        imgBtnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Load main activity
                Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
