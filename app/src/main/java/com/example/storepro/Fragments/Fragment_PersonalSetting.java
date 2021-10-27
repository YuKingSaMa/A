package com.example.storepro.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.storepro.LoginActivity;
import com.example.storepro.R;

public class Fragment_PersonalSetting extends Fragment implements View.OnClickListener {

    private View pView;

    private AppCompatButton btn_exit;
    private AppCompatButton btn_update;
    private ImageView image_back;
    private EditText edit_name;
    private EditText edit_sex;
    private EditText edit_birthday;
    Intent intent = new Intent();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pView = inflater.inflate(R.layout.module_fragment_personal_setting,container,false);
        btn_exit = pView.findViewById(R.id.btn_exit);
        btn_update = pView.findViewById(R.id.btn_update);
        image_back = pView.findViewById(R.id.img_back);
        edit_name=pView.findViewById(R.id.edit_name);
        edit_sex=pView.findViewById(R.id.edit_sex);
        edit_birthday=pView.findViewById(R.id.edit_birthday);

        image_back.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_update.setOnClickListener(this);

        return pView;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.img_back:
                Intent intent1 = new Intent(Fragment_PersonalSetting.this.getActivity(), Fragment_Me.class);
                startActivity(intent1);
                break;
            case R.id.btn_exit:
                Intent intent2 = new Intent(Fragment_PersonalSetting.this.getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_update:
                break;
        }
    }
}
