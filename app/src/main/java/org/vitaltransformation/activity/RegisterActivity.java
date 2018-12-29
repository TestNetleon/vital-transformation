package org.vitaltransformation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.vitaltransformation.R;
import org.vitaltransformation.utils.BaseActivity;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView imageView = findViewById(R.id.imgLogo);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = (int) ((double) getScreenWidth() / 2);
        params.width = (int) ((double) getScreenWidth() / 2);
        imageView.setLayoutParams(params);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateToLogin();
    }

    private void navigateToLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}
