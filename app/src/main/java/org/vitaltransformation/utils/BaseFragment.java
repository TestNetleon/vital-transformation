package org.vitaltransformation.utils;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class BaseFragment extends Fragment {

    private ProgressDialog progressDialog;
    private SharedPreferences preferences;
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    protected ArrayList<Location> bankList;

    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.create(getActivity());
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void showProgress(String message) {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.create(getActivity());
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
        Utils.closeKeyBoard(getActivity());
    }

    public void showToaster(String message) {
        if (!TextUtils.isEmpty(message)) Utils.showToaster(getActivity(), message);
    }

    public void showMessage(String message) {
        if (!TextUtils.isEmpty(message))
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showToaster(@StringRes int messageResource) {
        Utils.showToaster(getActivity(), getString(messageResource));
    }

    public void showToaster(Throwable t) {
        if (t != null && t.getMessage() != null) showToaster(t.getMessage());
    }

    public SharedPreferences getPreferences() {
        if (preferences == null)
            preferences = Utils.getPreferences(getContext());
        return preferences;
    }

    /*protected User getUser() {
        User user = null;
        if (getPreferences().contains( Constants.Pref.USER)) {
            user = new Gson().fromJson(getPreferences().getString( Constants.Pref.USER, null),
                    User.class);
        }
        return user;
    }

    protected void saveAddShgDataToPreference(AddSHG addSHG) {
        if (addSHG != null) {
            getPreferences().edit().putString( Constants.Pref.SHG, (new Gson()).toJson(addSHG))
                    .apply();
        }
    }

    protected AddSHG getSavedSHGFromPreferences() {
        AddSHG addSHG = null;
        if (getPreferences().contains( Constants.Pref.SHG)) {
            addSHG = new Gson().fromJson(getPreferences().getString( Constants.Pref.SHG, null),
                    AddSHG.class);
        }
        return addSHG;
    }

    public void setSelectedItemPositionSpinners(Spinner spinner, String id, String name,
                                                ArrayList<Location> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            Location location = arrayList.get(i);
            if (!TextUtils.isEmpty(id)) {
                if (!TextUtils.isEmpty(location.getId()) && id.equals(location.getId())) {
                    spinner.setSelection(i);
                    break;
                }
            } else if (!TextUtils.isEmpty(name)) {
                if (!TextUtils.isEmpty(location.getName()) && name.equalsIgnoreCase(location.getName())) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    protected void setupTextMandatory(TextView textView, String string) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(string.concat("<font color=\"#ff0000\">*</font>"),
                    Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(string.concat("<font color=\"#ff0000\">*</font>")));
        }
    }

    public AddMonthlyReportActivity getActivityMpr() {
        return (AddMonthlyReportActivity) getActivity();
    }

    public NavigationDrawerActivity getMainActivity() {
        return (NavigationDrawerActivity) getActivity();
    }
*/
    protected void logoutUser() {
        getPreferences().edit().clear().apply();
    }

    protected int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* Convert dp to pixel */
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

   /* @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof NavigationDrawerActivity)
            setupToolbar();
    }

    private void setupToolbar() {
        getMainActivity().getToolbar().findViewById(R.id.imageBack).setVisibility(View.GONE);
        getMainActivity().getToolbar().findViewById(R.id.imageAppLogo).setVisibility(View.GONE);
        getMainActivity().getToolbar().findViewById(R.id.toolTitle).setVisibility(View.VISIBLE);
        getMainActivity().getToolbar().findViewById(R.id.imgSearch).setVisibility(View.GONE);
        getMainActivity().getToolbar().findViewById(R.id.imgMenu).setVisibility(View.VISIBLE);
        getMainActivity().getToolbar().findViewById(R.id.constraintDistrict).setVisibility(View.GONE);
        getMainActivity().getToolbar().findViewById(R.id.imageAppLogoRight).setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActivity() instanceof NavigationDrawerActivity)
            resetToolbar();
    }

    private void resetToolbar() {
        getMainActivity().getToolbar().findViewById(R.id.imageBack).setVisibility(View.GONE);
        getMainActivity().getToolbar().findViewById(R.id.imageAppLogo).setVisibility(View.VISIBLE);
        getMainActivity().getToolbar().findViewById(R.id.toolTitle).setVisibility(View.GONE);
        getMainActivity().getToolbar().findViewById(R.id.imgSearch).setVisibility(View.GONE);
        getMainActivity().getToolbar().findViewById(R.id.imgMenu).setVisibility(View.VISIBLE);
        getMainActivity().getToolbar().findViewById(R.id.constraintDistrict).setVisibility(View.VISIBLE);
        getMainActivity().getToolbar().findViewById(R.id.imageAppLogoRight).setVisibility(View.GONE);
    }*/

}
