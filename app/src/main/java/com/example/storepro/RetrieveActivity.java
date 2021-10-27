package com.example.storepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.storepro.Email.RandomNumber;
import com.example.storepro.Email.SendEmail;
import com.example.storepro.sqllite.sqlliteDB;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class RetrieveActivity extends AppCompatActivity implements View.OnClickListener {
    sqlliteDB sqllite_DB;
    private TextInputEditText rEmail;
    private TextInputEditText rVerificationCode;
    private TextInputEditText rPassword;
    private TextInputEditText rRePassword;
    private AppCompatButton btn_retrieve;
    private AppCompatButton btn_send;
    private AppCompatTextView tv_retrieve_login;

    private long verificationCode = 0;            //生成的验证码
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        sqllite_DB = new sqlliteDB(this);
        initView();
    }

    private boolean checkForm() {
        //String email = rEmail.getText().toString().trim();
        //String phone = rPhone.getText().toString().trim();
        String newpassword = rPassword.getText().toString().trim();
        String renewpassword = rRePassword.getText().toString().trim();

        boolean isPass = true;

//        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            rEmail.setError("邮箱格式错误");
//        } else {
//            rEmail.setError(null);
//        }

//        if (phone.isEmpty() || phone.length() != 11) {
//            rPhone.setError("手机号码格式错误");
//        } else {
//            rPhone.setError(null);
//        }

        if (newpassword.isEmpty() || newpassword.length() < 6) {
            rPassword.setError("新密码位数至少为6");
            isPass = false;
        } else {
            rPassword.setError(null);
        }

        if (renewpassword.isEmpty() || renewpassword.length() < 6 || !(renewpassword.equals(newpassword))) {
            rRePassword.setError("两次新密码不一致");
        } else {
            rRePassword.setError(null);
        }

        return isPass;
    }

    private void initView() {
        rEmail = findViewById(R.id.edit_retrieve_email);
        rVerificationCode = findViewById(R.id.edit_verification_code);
        rPassword = findViewById(R.id.edit_retrieve_password);
        rRePassword = findViewById(R.id.edit_retrieve_re_password);
        btn_retrieve = findViewById(R.id.btn_retrieve);
        btn_send = findViewById(R.id.btn_send);
        tv_retrieve_login = findViewById(R.id.tv_retrieve_login);

        btn_retrieve.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        tv_retrieve_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                email = rEmail.getText().toString().trim();//邮箱
                sendVerificationCode(email); //发送验证码
                break;
            case R.id.btn_retrieve:
                //String email = rEmail.getText().toString().trim();
                String newpassword = rPassword.getText().toString().trim();
                if (checkForm()) {
                    ArrayList<User> data = sqllite_DB.getAllData();
                    for (int i = 0; i < data.size(); i++) {
                        //User user = data.get(i);
//                        if (!email.equals(user.getEmail())) {
//                            Toast.makeText(this, "输入已注册的邮箱", Toast.LENGTH_LONG).show();
//                        }
                        if (judgeVerificationCode()) {
                            sqllite_DB.update1(newpassword);
                            Toast.makeText(this, "密码修改成功", Toast.LENGTH_LONG).show();
                        }
//                        else {
//                            Toast.makeText(this, "密码修改失败", Toast.LENGTH_LONG).show();
//                        }
                    }
                }
                break;
            case R.id.tv_retrieve_login:
                Intent intent = new Intent(RetrieveActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    //发送验证码
    private void sendVerificationCode(final String email) {
        try {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        RandomNumber rn = new RandomNumber();
                        verificationCode = rn.getRandomNumber(6);
                        SendEmail se = new SendEmail(email);
                        se.sendHtmlEmail(verificationCode);//发送html邮件
                        Toast.makeText(RetrieveActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //判断输入的验证码是否正确
    private boolean judgeVerificationCode() {
        boolean is_verificationcode_pass = false;
        if (Integer.parseInt(rVerificationCode.getText().toString()) == verificationCode) {    //验证码和输入一致
            is_verificationcode_pass = true;
            Toast.makeText(RetrieveActivity.this, "验证成功", Toast.LENGTH_LONG).show();
        } else {
            is_verificationcode_pass = false;
            Toast.makeText(RetrieveActivity.this, "验证失败", Toast.LENGTH_LONG).show();
        }
        return is_verificationcode_pass;
    }
}
