package com.example.storepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.storepro.sqllite.sqlliteDB;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    sqlliteDB sqllite_DB;

    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private AppCompatButton login;
    private AppCompatButton signup;
    private AppCompatCheckBox remember_pwd;
    private SharedPreferences.Editor editor;
    private String useremail;
    private String username;
    private String usersex;
    private String userbirthday;
    private AppCompatTextView retrievet_pwd;

    //   @BindView(R.id.edit_login_email)
    //    TextInputEditText mEmail = null;
//    @BindView(R.id.edit_login_password)
//    TextInputEditText mPassword = null;


    private boolean checkForm() {
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();

        boolean isPass = true;


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱格式错误");
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码位数至少为6");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sqllite_DB = new sqlliteDB(this);
        initView();

        SharedPreferences sp= getSharedPreferences("user_mes",MODE_PRIVATE);
        editor = sp.edit();
        if (sp.getBoolean("flag",false)){
            String email_remember = sp.getString("mEmail","");
            String pwd_remember = sp.getString("mPassword","");
            mEmail.setText(email_remember);
            mPassword.setText(pwd_remember);
            remember_pwd.setChecked(true);
        }


    }

    private void initView() {
        //初始化控件
        mEmail = findViewById(R.id.edit_login_email);
        mPassword = findViewById(R.id.edit_login_password);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_sign_up);
        remember_pwd= findViewById(R.id.remember_pwd);
        retrievet_pwd=findViewById(R.id.retrievet_pwd);
        //设置点击事件监听器
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        retrievet_pwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (checkForm()) {
                    ArrayList<User> data = sqllite_DB.getAllData();
                    boolean match = false;
                    boolean match2 = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                            useremail = user.getEmail();
                            username=user.getUsername();
                            usersex=user.getSex();
                            userbirthday=user.getBirthday();
                            match = true;
                            if (remember_pwd.isChecked()){
                                editor.putBoolean("flag",true);
                                editor.putString("mEmail",user.getEmail());
                                editor.putString("mPassword",user.getPassword());
                                editor.apply();
                                match2=true;
                            }else {
                                editor.putString("mEmail",user.getEmail());
                                editor.putString("mPassword","");
                                editor.clear();
                                editor.apply();
                                match2=false;
                            }
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        if (match2){
                            remember_pwd.setChecked(true);
                        }
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

                        Thread thread = new Thread(){
                            @Override
                            public void run() {
                                try {
                                    sleep(2000);
                                    String user_email=useremail;
                                    String user_name=username;
                                    String user_sex=usersex;
                                    String user_birthday=userbirthday;
                                    Intent intent =new Intent(LoginActivity.this, StoreActivity.class);
                                    intent.putExtra("user_email",user_email);
                                    intent.putExtra("user_name",user_name);
                                    intent.putExtra("user_sex",user_sex);
                                    intent.putExtra("user_birthday",user_birthday);
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        };
                        thread.start();

                    } else {
                        Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_sign_up:
                Intent intent = new Intent(LoginActivity.this, SignUPActivity.class);
                startActivity(intent);
                break;
            case R.id.retrievet_pwd:
                Intent forgetpwd = new Intent(LoginActivity.this, RetrieveActivity.class);
                startActivity(forgetpwd);
                break;
        }
    }



//    @OnClick(R.id.btn_login)
//    void login() {
//        String email = mEmail.getText().toString().trim();
//        String password = mPassword.getText().toString().trim();
//        if (checkForm()) {
//            ArrayList<User> data = sqllite_DB.getAllData();
//            boolean match = false;
//            for (int i = 0; i < data.size(); i++) {
//                User user = data.get(i);
//                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
//                    match = true;
//                    break;
//                } else {
//                    match = false;
//                }
//            }
//            if (match) {
//                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, StoreActivity.class);
//                startActivity(intent);
//                finish();
//            } else {
//                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }
//
//    @OnClick(R.id.tv_sign_up)
//    void signup() {
//        Intent intent = new Intent(LoginActivity.this, SignUPActivity.class);
//        startActivity(intent);
//    }
}
