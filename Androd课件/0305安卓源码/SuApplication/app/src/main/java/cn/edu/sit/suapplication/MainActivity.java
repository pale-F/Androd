package cn.edu.sit.suapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private MediaPlayer mp;
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onCreate", "onCreate: "+"info");
        try {
            InputStream is = getAssets().open("qwer.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = is.read(buffer)) !=-1){
                baos.write(buffer,0,len);
            }
            Log.i("TAG", "onCreate: "+baos.toString());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream isd =  getAssets().open("zhao.jpg");
            iv = findViewById(R.id.iv);
            Bitmap bm = BitmapFactory.decodeStream(isd);
            iv.setImageBitmap(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            AssetFileDescriptor afd= getAssets().openFd("dasong.mp3");
            mp = new MediaPlayer();
            mp.reset();
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        wv = findViewById(R.id.wv);
        wv.loadUrl("file:///android_asset/web/index.html");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}