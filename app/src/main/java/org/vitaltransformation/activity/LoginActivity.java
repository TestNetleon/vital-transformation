package org.vitaltransformation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.vitaltransformation.R;
import org.vitaltransformation.http.ApiRequester;
import org.vitaltransformation.model.User;
import org.vitaltransformation.utils.BaseActivity;
import org.vitaltransformation.utils.Toaster;
import org.vitaltransformation.utils.Utils;

import static org.vitaltransformation.http.ApiRequester.LoginRequestListener;

public class LoginActivity extends BaseActivity implements LoginRequestListener {

    private EditText mEmailView;
    private EditText mPasswordView;
    private ApiRequester apiRequester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        findViewById(R.id.constraintNewUser).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRegister();
            }
        });

        ImageView imageView = findViewById(R.id.imgLogo);
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = (int) ((double) getScreenWidth() / 2);
        params.width = (int) ((double) getScreenWidth() / 2);
        imageView.setLayoutParams(params);

        apiRequester = new ApiRequester(this, this);
    }

    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
        } else if (!Utils.isValidEmail(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
        }
        showProgress();
        apiRequester.requestLogin(email, password);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
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

    @Override
    public void onLoginSuccess(User user) {
        if (apiRequester != null) {
            hideProgress();
            saveUser(user);
            navigateToHome();
        }
    }

    private void navigateToRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }

    private void navigateToHome() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}

