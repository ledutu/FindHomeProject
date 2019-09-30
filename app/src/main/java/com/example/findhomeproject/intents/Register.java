package com.example.findhomeproject.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findhomeproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText txtEmailRegister, txtPassWordRegister, txtPassWordComfirmRegister;
    Button btnRegister;
    ProgressBar progressBarRegister;
    TextView btnBackInLogin;

    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        btnBackInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void register() {
        progressBarRegister.setVisibility(View.VISIBLE);

        String email = txtEmailRegister.getText().toString();
        String password = txtPassWordRegister.getText().toString();
        String passwordComfirm = txtPassWordComfirmRegister.getText().toString();

        if (email.isEmpty() || password.isEmpty() || passwordComfirm.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Mật khẩu và xác nhận mật khẩu không trùng nhau!", Toast.LENGTH_LONG).show();
            txtPassWordRegister.setText("");
            txtPassWordComfirmRegister.setText("");
            progressBarRegister.setVisibility(View.GONE);
        }

        else {
            if(password.equals(passwordComfirm))
            {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Đăng kí thành công!", Toast.LENGTH_LONG).show();
                            user = mAuth.getCurrentUser();
                            progressBarRegister.setVisibility(View.GONE);
                            Intent intent = new Intent();
                            intent.putExtra("EMAIL_REGISTER", user.getEmail());
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_LONG).show();
                            txtPassWordRegister.setText("");
                            txtPassWordComfirmRegister.setText("");
                            txtEmailRegister.setText("");
                            txtEmailRegister.requestFocus();
                            progressBarRegister.setVisibility(View.GONE);
                        }
                    }
                });
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin!", Toast.LENGTH_LONG).show();
                progressBarRegister.setVisibility(View.GONE);
            }
        }


    }

    private void addControls() {
        txtEmailRegister = findViewById(R.id.txtEmailRegister);
        txtPassWordRegister = findViewById(R.id.txtPassWordRegister);
        txtPassWordComfirmRegister = findViewById(R.id.txtPassWordComfirmRegister);
        btnRegister = findViewById(R.id.btnRegister);
        progressBarRegister = findViewById(R.id.progressBarRegister);
        mAuth = FirebaseAuth.getInstance();
        btnBackInLogin = findViewById(R.id.btnBackInLogin);
    }
}
