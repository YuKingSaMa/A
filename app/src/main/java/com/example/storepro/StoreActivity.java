package com.example.storepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.storepro.Adapter.SectionsPagerAdaper;
import com.example.storepro.Fragments.Fragment_Home;
import com.example.storepro.Fragments.Fragment_Find;
import com.example.storepro.Fragments.Fragment_Cart;
import com.example.storepro.Fragments.Fragment_Me;
import com.example.storepro.Fragments.Fragment_Sort;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class StoreActivity extends AppCompatActivity{

    private TabLayout myTab;
    private ViewPager2 myPager2;
    List<String> titles=new ArrayList<>();
    List<Integer> icons=new ArrayList<>();
    List<Fragment> fragments=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        myTab = findViewById(R.id.tab);
        myPager2 = findViewById(R.id.viewpager2);

        //添加标题
        titles.add("主页");
        titles.add("图片");
        titles.add("发现");
        titles.add("流行");
        titles.add("我的");

        icons.add(R.drawable.home);
        icons.add(R.drawable.picture);
        icons.add(R.drawable.search);
        icons.add(R.drawable.internet);
        icons.add(R.drawable.userinfo);

        //添加Fragment进去
        fragments.add(new Fragment_Home());
        fragments.add(new Fragment_Sort());
        fragments.add(new Fragment_Find());
        fragments.add(new Fragment_Cart());
        fragments.add(new Fragment_Me());


        myTab.setTabMode(TabLayout.MODE_FIXED);

        //实例化适配器
        SectionsPagerAdaper sectionsPagerAdaper =new SectionsPagerAdaper(getSupportFragmentManager(),getLifecycle(),fragments);

        //设置适配器
        myPager2.setAdapter(sectionsPagerAdaper);

        //TabLayout和Viewpager2进行关联
        new TabLayoutMediator(myTab, myPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
                tab.setIcon(icons.get(position));
            }
        }).attach();

    }

}
