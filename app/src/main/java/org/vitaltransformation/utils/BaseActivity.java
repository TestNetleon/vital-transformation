package org.vitaltransformation.utils;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.google.gson.Gson;
import org.vitaltransformation.model.User;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private SharedPreferences preferences;

    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.create(this);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void showProgress(String message) {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.create(this);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void hideProgress() {
        if (progressDialog == null) return;
        progressDialog.dismiss();
        progressDialog = null;
    }

    public void closeKeyboard() {
        Utils.closeKeyBoard(this);
    }

    public void showToaster(String message) {
        if (!TextUtils.isEmpty(message)) Utils.showToaster(this, message);
    }

    public void showMessage(String message) {
        if (!TextUtils.isEmpty(message))
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToaster(@StringRes int messageResource) {
        Utils.showToaster(this, getString(messageResource));
    }

    public void showToaster(Throwable t) {
        if (t != null && t.getMessage() != null) showToaster(t.getMessage());
    }

    public void printLog(String s) {
        System.out.println(s);
    }

    public SharedPreferences getPreferences() {
        if (preferences == null)
            preferences = Utils.getPreferences(this);
        return preferences;
    }

    protected User getUser() {
        User user = null;
        if (getPreferences().contains( Constants.Pref.USER)) {
            user = new Gson().fromJson(getPreferences().getString( Constants.Pref.USER, null),
                    User.class);
        }
        return user;
    }

    protected void saveUser(User user) {
        if (user != null) {
            getPreferences().edit().putString(Constants.Pref.USER, (new Gson()).toJson(user)).apply();
        }
    }

    protected void logoutUser() {
        getPreferences().edit().clear().apply();
    }

    protected int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /*protected void showSnackBar() {
        Snackbar.make(getCurrentFocus(), "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }*/
}