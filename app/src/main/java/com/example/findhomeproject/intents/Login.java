package com.example.findhomeproject.intents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findhomeproject.MainActivity;
import com.example.findhomeproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText txtEmail, txtPassWord;
    TextView btnFotgotPassword, btnGoToRegister;
    Button btnLogin;

    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    public final int REGISTER_REQUEST = 123;
    private FirebaseUser user;
    SharedPreferences sp;

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        btnFotgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword();
            }
        });
    }

    private void forgotPassword() {
        Intent intent = new Intent(Login.this, ForgotPassword.class);
        String email = txtEmail.getText().toString();
        intent.putExtra("EMAIL", email);
        startActivity(intent);
    }

    private void register() {
        Intent intent = new Intent(Login.this, Register.class);

        startActivityForResult(intent, REGISTER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REGISTER_REQUEST)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                String email = data.getStringExtra("EMAIL_REGISTER");
                txtEmail.setText(email);
            }
        }
    }

    private void login() {
        progressBar.setVisibility(View.VISIBLE);
        String email = txtEmail.getText().toString();
        String password = txtPassWord.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập email!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập password!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        user = mAuth.getCurrentUser();
                        sp.edit().putBoolean("logged", true).apply();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });
        }




    }

    private void addControls() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPassWord = findViewById(R.id.txtPassWord);
        btnFotgotPassword = findViewById(R.id.btnFotgotPassword);
        btnGoToRegister = findViewById(R.id.btnGoToRegister);
        btnLogin = findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if(mAuth.getCurrentUser() != null)
        {
            handler=new Handler();
            progressBar.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            },1000);
        }
    }
}
