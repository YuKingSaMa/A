package com.example.storepro.News;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storepro.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class NewsActivity1 extends AppCompatActivity {

    private TextView tv_title;
    private SmartImageView img;
    private TextView tv_content;
    private List<NewsContent> newsContentList;
    private NewsContent newsContent;
    private TextView tv_date;
    private TextView tv_writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news1);

        initview();
        fillData();

    }

    public void initview(){
        tv_title = (TextView) findViewById(R.id.tv_title);
        img = (SmartImageView) findViewById(R.id.iv_img);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_writer = (TextView) findViewById(R.id.tv_writer);
    }

    private void fillData() {

        //实例化AsyncHttpClient对象
        AsyncHttpClient client = new AsyncHttpClient();
        //获取string.xlm里的url，以get方式请求访问
        client.get(getString(R.string.server_url1), new AsyncHttpResponseHandler() {
            //成功时执行
            @Override
            public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {

                try {
                    //获取json文件
                    String json = new String(bytes, "utf-8");
                    newsContentList = JsonParse.getNewscontent(json);
                    //如果解析后的文件为空，则解析失败，并且不会执行之后的代码
                    if (newsContentList == null) {
                        Toast.makeText(NewsActivity1.this, "解析失败", Toast.LENGTH_SHORT).show();
                    } else {
                        newsContent = newsContentList.get(0);
                        img.setImageUrl(newsContent.getImg());
                        tv_title.setText(newsContent.getTitle());
                        tv_content.setText(newsContent.getContent());
                        tv_date.setText(newsContent.getDate());
                        tv_writer.setText(newsContent.getWriter());
                    }
                } catch (Exception e) {
                    Toast.makeText(NewsActivity1.this, "解析失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
            }

            //失败时执行
            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });


    }

    public void back(View view) {
        finish();
    }
}
