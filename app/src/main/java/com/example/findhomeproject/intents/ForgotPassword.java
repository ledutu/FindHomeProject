package com.example.findhomeproject.intents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findhomeproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    
    EditText txtEmailForgotPassword;
    Button btnResetPassword;
    TextView btnBackInLogin;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        btnBackInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void resetPassword() {
        String email = txtEmailForgotPassword.getText().toString();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ForgotPassword.this, "Reset thành công, hãy kiểm tra email của bạn", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ForgotPassword.this, "Email không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addControls() {
        txtEmailForgotPassword = findViewById(R.id.txtEmailForgotPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        btnBackInLogin = findViewById(R.id.btnBackInLogin);
        auth = FirebaseAuth.getInstance();

    }
}
