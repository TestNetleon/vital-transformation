package org.vitaltransformation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.vitaltransformation.R;
import org.vitaltransformation.utils.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.imgLogo);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = (int) ((double) getScreenWidth() / 2);
        params.width = (int) ((double) getScreenWidth() / 2);
        imageView.setLayoutParams(params);

        findViewById(R.id.getStarted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLogin();
//                navigateToMainActivity();
            }
        });

        if (getUser()!=null){
            navigateToMainActivity();
        }

    }

    private void navigateToLogin() {
        startActivity(new Intent(SplashActivity.this,
                LoginActivity.class));
        finish();
    }

   private void navigateToMainActivity() {
        startActivity(new Intent(SplashActivity.this,
                MainActivity.class));
        finish();
    }

    boolean isBackPressed = false;

    @Override
    public void onBackPressed() {

        if (isBackPressed) {
            super.onBackPressed();
        }

        isBackPressed = true;
        showMessage(getString(R.string.press_back_to_exit));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        }, 1500);

    }
}
