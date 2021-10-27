package com.example.storepro.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.storepro.R;

//分类
public class Fragment_Sort extends Fragment {

    private View sortView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sortView = inflater.inflate(R.layout.module_fragment_sort, container, false);
        return sortView;
    }
}