package com.example.storepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.example.storepro.sqllite.sqlliteDB;

public class SignUPActivity extends AppCompatActivity implements View.OnClickListener {
    sqlliteDB sqllite_DB;
    SQLiteDatabase db;
    ContentValues values;

    private TextInputEditText mName;
    private TextInputEditText mEmail;
    private TextInputEditText mPhone;
    private TextInputEditText mPassword;
    private TextInputEditText mRePassword;
    private TextInputEditText mSex;
    private TextInputEditText mBirthday;
    private AppCompatButton singup;
    private AppCompatTextView linklogin;

//    @BindView(R.id.edit_sign_up_name)
//    TextInputEditText mName = null;
//    @BindView(R.id.edit_sign_up_email)
//    TextInputEditText mEmail = null;
//    @BindView(R.id.edit_sign_up_phone)
//    TextInputEditText mPhone = null;
//    @BindView(R.id.edit_sign_up_password)
//    TextInputEditText mPassword = null;
//    @BindView(R.id.edit_sign_up_re_password)
//    TextInputEditText mRePassword = null;




    private boolean checkForm() {

        String username = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String repassword = mRePassword.getText().toString().trim();
        //String sex = mSex.getText().toString().trim();
        //String birthday = mBirthday.getText().toString().trim();

        boolean isPass = true;

        if (username.isEmpty()) {
            mName.setError("请输入用户名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("邮箱格式错误");
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码位数至少为6");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (repassword.isEmpty() || repassword.length() < 6 || !(repassword.equals(password))) {
            mRePassword.setError("两次密码不一致");
        } else {
            mRePassword.setError(null);
        }



        return isPass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sqllite_DB = new sqlliteDB(this);
        initView();

    }

    private void initView() {
        //初始化控件
        mName = findViewById(R.id.edit_sign_up_name);
        mEmail = findViewById(R.id.edit_sign_up_email);
        mPhone = findViewById(R.id.edit_sign_up_phone);
        mPassword = findViewById(R.id.edit_sign_up_password);
        mRePassword = findViewById(R.id.edit_sign_up_re_password);
        mSex=findViewById(R.id.edit_sign_up_sex);
        mBirthday=findViewById(R.id.edit_sign_up_birthday);
        singup = findViewById(R.id.btn_sign_up);
        linklogin = findViewById(R.id.tv_link_login);
        //设置点击事件监听器
        singup.setOnClickListener(this);
        linklogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_up:
                String username = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String repassword = mRePassword.getText().toString().trim();
                String sex = mSex.getText().toString().trim();
                String birthday = mBirthday.getText().toString().trim();
                if (checkForm()){
                    sqllite_DB.add(username,email,phone,password,sex,birthday);
                    Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_link_login:
                Intent intent=new Intent(SignUPActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

//    @OnClick(R.id.btn_sign_up) void singup(){
//
//        if (checkForm()){
//            String username = mName.getText().toString().trim();
//            String email = mEmail.getText().toString().trim();
//            String phone = mPhone.getText().toString().trim();
//            String password = mPassword.getText().toString().trim();
//            sqllite_DB.add(username,email,phone,password);
//            Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
//        }
//    }
//
//    @OnClick(R.id.tv_link_login) void linklogin(){
//        Intent intent=new Intent(SignUPActivity.this,LoginActivity.class);
//        startActivity(intent);
//    }




}
