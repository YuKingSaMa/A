package com.example.storepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class UpdateUserImgActivity extends AppCompatActivity {

    private ImageView tx;
    private byte[] bitmapToBytes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_img);
        initView();
    }

    private void initView() {

        tx = (ImageView) findViewById(R.id.user_img);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });
    }

    //点击完成按钮执行的方法
    public void complete(View view) {
        Intent intent = new Intent();
        intent.putExtra("image", bitmapToBytes);
        setResult(1, intent);
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColum = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColum, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColum[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            tx.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            Bitmap image = ((BitmapDrawable) tx.getDrawable()).getBitmap();
            bitmapToBytes = Bitmap2Bytes(image);
        }
    }

    private byte[] Bitmap2Bytes(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();

    }
}