package org.vitaltransformation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.vitaltransformation.R;
import org.vitaltransformation.http.ApiRequester;
import org.vitaltransformation.model.User;
import org.vitaltransformation.utils.BaseActivity;
import org.vitaltransformation.utils.Toaster;
import org.vitaltransformation.utils.Utils;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,
        ApiRequester.RegisterRequestListener {

    private TextView newUser;
    private EditText yourName, email, mobile, password;
    private ApiRequester apiRequester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView imageView = findViewById(R.id.imgLogo);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = (int) ((double) getScreenWidth() / 2);
        params.width = (int) ((double) getScreenWidth() / 2);
        imageView.setLayoutParams(params);


        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        newUser = findViewById(R.id.newUser);

        yourName = findViewById(R.id.yourName);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);


    }


    private void checkValidation() {

        // Store values at the time of the login attempt.
        String name = yourName.getText().toString();
        String mail = email.getText().toString();
        String phone = mobile.getText().toString();
        String pass = password.getText().toString();

        if (!TextUtils.isEmpty(name)) {
            Toaster.showToast(this, "Enter name");
            return;
        } else if (!TextUtils.isEmpty(mail)) {
            Toaster.showToast(this, "Enter email");
            return;
        } else if (!Utils.isValidEmail(mail)) {
            Toaster.showToast(this, "Enter valid email");
            return;
        } else if (!TextUtils.isEmpty(phone)) {
            Toaster.showToast(this, "Enter phone");
            return;
        } else if (phone.length() != 10 || (!phone.startsWith("9")
                || !phone.startsWith("8") || !phone.startsWith("7")
                || !phone.startsWith("6"))) {
            Toaster.showToast(this, "Enter valid phone");
            return;
        } else if (!TextUtils.isEmpty(pass)) {
            Toaster.showToast(this, "Enter password");
            return;
        }

        showProgress();
        apiRequester.requestRegister(name, mail, phone, pass);
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

    @Override
    public void onClick(View view) {
        checkValidation();

    }

    private void navigateToHome() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }
    @Override
    public void onRegisterSuccess(User user) {
        if (apiRequester != null) {
            hideProgress();
            saveUser(user);
            navigateToHome();
        }
    }

    @Override
    public void onRequestFailed(String message) {
        if (apiRequester != null) {
            hideProgress();
            Toaster.showToast(this, message);
        }

    }

    @Override
    public void onNetworkConnectionError() {
        if (apiRequester != null) {
            hideProgress();
            Toaster.showToast(this, getString(R.string.network_error));
        }
    }
}
