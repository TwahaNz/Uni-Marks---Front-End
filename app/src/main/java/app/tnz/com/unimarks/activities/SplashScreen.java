package app.tnz.com.unimarks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import app.tnz.com.unimarks.R;

/**
 * Created by Admin on 2016/08/31.
 */
public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        progressBar = new ProgressBar(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    startActivity(new Intent(SplashScreen.this, StudentLogInScreen.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
