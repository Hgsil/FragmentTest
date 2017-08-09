package com.hgsil.android.fragementtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hgsil.android.fragementtest.Utils.HttpUtils.AsynNetUtils;
import com.hgsil.android.fragementtest.Utils.JsonUtils.JsonUtil;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class WebActivity extends AppCompatActivity {
    SurfaceView mSurfaceView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Button bu = (Button) findViewById(R.id.certen);
        mSurfaceView = (SurfaceView)findViewById(R.id.sur);

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
    private Bitmap downLoadBitmap(String url) {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                //图片压缩
                    BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;//宽高压缩为原来的1/2
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream(), null, options);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }
}
