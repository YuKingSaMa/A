package com.example.storepro.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.storepro.GlideImageLoader;
import com.example.storepro.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends Fragment {
    private View homeView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.module_fragment_home, container, false);
        List images = new ArrayList();
        images.add("https://image14.m1905.cn/uploadfile/2018/0907/thumb_1_1380_460_20180907013518839623.jpg");
        images.add("https://image14.m1905.cn/uploadfile/2018/0906/thumb_1_1380_460_20180906040153529630.jpg");
        images.add("https://image13.m1905.cn/uploadfile/2018/0907/thumb_1_1380_460_20180907114844929630.jpg");

        Banner banner = homeView.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        return homeView;
    }
}