package com.example.storepro.News;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storepro.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class NewsMainActivity extends AppCompatActivity {
    private LinearLayout loading;
    private ListView lvNews;
    private List<NewsInfo> newsInfos;
    private TextView tv_title;
    private TextView tv_description;
    private TextView tv_type;
    private NewsInfo newsInfo;
    private SmartImageView siv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        initView();
        fillData();
    }

    //初始化控件
    private void initView() {
        loading = (LinearLayout) findViewById(R.id.loading);
        lvNews = (ListView) findViewById(R.id.lv_news);
    }



    //使用AsyncHttpClient访问网络，获取网络的数据，包括图片和文字
    private void fillData() {

        //实例化AsyncHttpClient对象
        AsyncHttpClient client = new AsyncHttpClient();
        //获取string.xlm里的url，以get方式请求访问
        client.get(getString(R.string.server_url), new AsyncHttpResponseHandler() {
            //成功时执行
            @Override
            public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {

                try {
                    //获取json文件
                    String json = new String(bytes, "utf-8");
                    //调用getNewsInfo()解析json文件
                    newsInfos = JsonParse.getNewsInfo(json);
                    //如果解析后的文件为空，则解析失败，并且不会执行之后的代码
                    if (newsInfos == null) {
                        Toast.makeText(NewsMainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    } else {
                        //将界面设置为不可见
                        loading.setVisibility(View.INVISIBLE);
                        //设置适配器
                        lvNews.setAdapter(new NewsAdapter());
                        //为新闻添加点击事件
                        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:
                                        Intent intent = new Intent(NewsMainActivity.this,NewsActivity1.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent(NewsMainActivity.this,NewsActivity2.class);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent(NewsMainActivity.this,NewsActivity3.class);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent(NewsMainActivity.this,NewsActivity4.class);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent(NewsMainActivity.this,NewsActivity5.class);
                                        startActivity(intent4);
                                        break;
                                    case 5:
                                        Intent intent5 = new Intent(NewsMainActivity.this,NewsActivity6.class);
                                        startActivity(intent5);
                                        break;
                                    case 6:
                                        Intent intent6 = new Intent(NewsMainActivity.this, NewsActivity7.class);
                                        startActivity(intent6);
                                        break;
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //失败时执行
            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {

            }



        });
    }

    //适配器
    private class NewsAdapter extends BaseAdapter {
        //获取新闻的数目
        @Override
        public int getCount() {
            return newsInfos.size();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(NewsMainActivity.this, R.layout.news_item, null);
            siv = (SmartImageView) view.findViewById(R.id.siv_icon);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_description = (TextView) view.findViewById(R.id.tv_description);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            newsInfo = newsInfos.get(position);
            //设置每一条新闻显示的图片，如果没有获取到图片，就使用默认的图片
            siv.setImageUrl(newsInfo.getIcon(), R.drawable.ic_launcher, R.drawable.ic_launcher);
            tv_title.setText(newsInfo.getTitle());
            tv_description.setText(newsInfo.getContent());
            //设置新闻的种类，不同的种类有不同的表示方法
            int type = newsInfo.getType();
            switch (type) {
                case 1:
                    tv_type.setText("评论:" + newsInfo.getComment());
                    break;
                case 2:
                    tv_type.setTextColor(Color.RED);
                    tv_type.setText("专题");
                    break;
                case 3:
                    tv_type.setTextColor(Color.BLUE);
                    tv_type.setText("LIVE");
                    break;
            }
            return view;
        }
        //新闻对象
        @Override
        public Object getItem(int position) {
            return null;
        }
        //新闻id
        @Override
        public long getItemId(int position) {
            return position;
        }
    }
}
