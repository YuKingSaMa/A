package com.example.storepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.storepro.Fragments.Fragment_Me;
import com.example.storepro.R;
import com.example.storepro.sqllite.sqlliteDB;

import java.util.ArrayList;

public class PersonalSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton btn_exit;
    private AppCompatButton btn_update;
    private ImageView image_back;
    private EditText edit_name;
    private EditText edit_sex;
    private EditText edit_birthday;
    sqlliteDB sqllite_DB;
    private String user_email;
    private String user_name;
    private String user_sex;
    private String user_birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);

        sqllite_DB = new sqlliteDB(this);
        initView();

        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        user_sex = intent.getStringExtra("user_sex");
        user_birthday = intent.getStringExtra("user_birthday");
        //user_email = intent.getStringExtra("user_email");
        edit_name.setText(user_name);
        edit_sex.setText(user_sex);
        edit_birthday.setText(user_birthday);
    }

    private void initView() {
        btn_exit = findViewById(R.id.btn_exit);
        btn_update = findViewById(R.id.btn_update);
        image_back = findViewById(R.id.img_back);
        edit_name = findViewById(R.id.edit_name);
        edit_sex = findViewById(R.id.edit_sex);
        edit_birthday = findViewById(R.id.edit_birthday);

        image_back.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit:
                Intent intent1 = new Intent(PersonalSettingActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_update:
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
                        Toast.makeText(this, "更新成功", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "更新失败", Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
    }
}
