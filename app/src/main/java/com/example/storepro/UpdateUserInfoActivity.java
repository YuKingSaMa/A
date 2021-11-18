package com.example.storepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storepro.sqllite.sqlliteDB;

import java.util.ArrayList;

public class UpdateUserInfoActivity extends AppCompatActivity {

    private EditText edit_name;
    private EditText edit_sex;
    private EditText edit_birthday;
    private String user_email;
    private Button btn_update_userinfo;

    sqlliteDB sqllite_DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        init();
    }
    private void init(){
        edit_name = findViewById(R.id.edit_update_name);
        edit_sex = findViewById(R.id.edit_update_sex);
        edit_birthday = findViewById(R.id.edit_update_birthday);
    }

    public void update(View view){
        String username = edit_name.getText().toString().trim();
        String sex = edit_sex.getText().toString().trim();
        String birthday = edit_birthday.getText().toString().trim();
        Intent intent = getIntent();
        user_email = intent.getStringExtra("user_email");
        ArrayList<User> data = sqllite_DB.getAllData();
        for (int i = 0; i < data.size(); i++) {
            User user = data.get(i);
            if (user_email.equals(user.getEmail())) {
                sqllite_DB.update3(username);
                sqllite_DB.update4(sex);
                sqllite_DB.update5(birthday);
                Toast.makeText(this, "修改成功", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "修改失败", Toast.LENGTH_LONG).show();
            }
        }
    }

}