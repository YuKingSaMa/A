package com.example.storepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.storepro.sqllite.sqlliteDB;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    sqlliteDB sqllite_DB;

    private TextInputEditText uEmail;
    private TextInputEditText uPassword;
    private TextInputEditText uNewPassword;
    private TextInputEditText uReNewPassword;
    private AppCompatButton btn_update;
    private AppCompatTextView tv_update_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        sqllite_DB = new sqlliteDB(this);
        initView();

    }

    private boolean checkForm() {
        String email = uEmail.getText().toString().trim();
        String password = uPassword.getText().toString().trim();
        String newpassword = uNewPassword.getText().toString().trim();
        String renewpassword = uReNewPassword.getText().toString().trim();

        boolean isPass = true;


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            uEmail.setError("邮箱格式错误");
        } else {
            uEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            uPassword.setError("旧密码位数至少为6");
            isPass = false;
        } else {
            uPassword.setError(null);
        }

        if (newpassword.isEmpty() || newpassword.length() < 6) {
            uNewPassword.setError("新密码位数至少为6");
            isPass = false;
        } else {
            uNewPassword.setError(null);
        }

        if (renewpassword.isEmpty() || renewpassword.length() < 6 || !(renewpassword.equals(newpassword))) {
            uReNewPassword.setError("两次新密码不一致");
        } else {
            uReNewPassword.setError(null);
        }

        return isPass;
    }

    private void initView() {
        uEmail=findViewById(R.id.edit_update_email);
        uPassword=findViewById(R.id.edit_update_password);
        uNewPassword=findViewById(R.id.edit_update_new_password);
        uReNewPassword=findViewById(R.id.edit_update_re_new_password);
        btn_update=findViewById(R.id.btn_update);
        tv_update_login=findViewById(R.id.tv_update_login);

        btn_update.setOnClickListener(this);
        tv_update_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();
                String newpassword = uNewPassword.getText().toString().trim();
                if (checkForm()){
                    ArrayList<User> data = sqllite_DB.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                            sqllite_DB.update1(newpassword);
                            Toast.makeText(this,"密码修改成功",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(this,"密码修改失败",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            case R.id.tv_update_login:
                Intent intent=new Intent(UpdateActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
