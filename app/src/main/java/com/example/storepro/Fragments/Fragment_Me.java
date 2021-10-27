package com.example.storepro.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.storepro.PersonalSettingActivity;
import com.example.storepro.R;
import com.example.storepro.StoreActivity;


public class Fragment_Me extends Fragment implements View.OnClickListener {
    private View meView;
    private TextView tv_user_email;
    private TextView tv_setting;
    private String user_email;
    private String user_name;
    private String user_sex;
    private String user_birthday;
    //创建视图
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        meView = inflater.inflate(R.layout.module_fragment_me, container, false);
        Intent intent = getActivity().getIntent();
        user_email = intent.getStringExtra("user_email");
        user_name = intent.getStringExtra("user_name");
        user_sex = intent.getStringExtra("user_sex");
        user_birthday = intent.getStringExtra("user_birthday");
        tv_user_email = meView.findViewById(R.id.tv_user_avatr_email);
        tv_user_email.setText(user_email);
        tv_setting = meView.findViewById(R.id.tv_personal_setting);
        tv_setting.setOnClickListener(this);
        //initView();
        return meView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_personal_setting:
                Intent intent = new Intent(getActivity(), PersonalSettingActivity.class);
                intent.putExtra("user_email",user_email);
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_sex",user_sex);
                intent.putExtra("user_birthday",user_birthday);
                startActivity(intent);
                break;
        }
    }

//    private void initView() {
//        tv_setting=meView.findViewById(R.id.tv_personal_setting);
//        //tv_setting.setOnClickListener(this);
//        tv_setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Fragment_Me.this.getActivity(), Fragment_PersonalSetting.class);
//                startActivity(intent);
//            }
//        });
//    }

//    @Override
//    public void onClick(View v) {
//
//    }


//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//


}
