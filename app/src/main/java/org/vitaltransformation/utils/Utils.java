package org.vitaltransformation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetwork = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void closeKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void closeKeyBoard(Context context) {
        if (context instanceof Activity) {
            View view = ((Activity) context).getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static int convertDpToPx(Activity activity, int dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) (dp * (metrics.density));
    }

    public static int convertDpToPx(Activity activity, float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) (dp * (metrics.density));
    }

    public static void showToaster(Context context, String message) {
        if (message != null) Toaster.showToast(context, message);
    }

    public static void showToaster(Context context, @StringRes int messageResource) {
        showToaster(context, context.getString(messageResource));
    }

    public static void showToaster(Context context, Throwable t) {
        if (t != null && t.getMessage() != null)
            showToaster(context, t.getMessage());
    }

    public static String changeDateFormat(String currentFormat, String requiredFormat, String dateString) {
        String result = "";
        if (TextUtils.isEmpty(dateString)) {
            return result;
        }
        SimpleDateFormat formatterOld = new SimpleDateFormat(currentFormat, Locale.getDefault());
        SimpleDateFormat formatterNew = new SimpleDateFormat(requiredFormat, Locale.getDefault());
        Date date = null;
        try {
            date = formatterOld.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            result = formatterNew.format(date);
        }
        return result;
    }


    public static boolean checkStringObject(String str, boolean showLog) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return true;
    }

    public static boolean checkStringObject(String str) {
        return checkStringObject(str, false);
    }

    public static SimpleDateFormat getSimpleDateFormat(@NonNull String pattern, @NonNull Locale locale) {
        if (checkStringObject(pattern) && locale != null) {
            return new SimpleDateFormat(pattern, locale);
        }
        return null;
    }

    public static SimpleDateFormat getSimpleDateFormat(@NonNull String pattern) {
        if (checkStringObject(pattern)) {
            return getSimpleDateFormat(pattern, Locale.ENGLISH);
        }
        throw new IllegalArgumentException("pattern is null in getSimpleDateFormat method");
    }

    public static boolean isNumberValid(String number) {
        return number != null && number.length() == 10 && number.matches("^[6-9][0-9]{9}$");
    }

    public static boolean isAccountNumberValid(String number) {
        return number.matches("^[0-9]{9,18}$");
    }

    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(Constants.Pref.NAME, Context.MODE_PRIVATE);
    }

    public static InputFilter nameFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!(Character.isLetter(source.charAt(i))
                        || Character.toString(source.charAt(i)).equals(".")
                        || Character.toString(source.charAt(i)).equals(" "))) {
                    return "";
                }
            }
            return null;
        }
    };

    public static InputFilter addressFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                if (!(Character.isLetter(source.charAt(i))
                        || Character.isDigit(source.charAt(i))
                        || Character.toString(source.charAt(i)).equals(".")
                        || Character.toString(source.charAt(i)).equals("/")
                        || Character.toString(source.charAt(i)).equals("-")
                        || Character.toString(source.charAt(i)).equals(",")
                        || Character.toString(source.charAt(i)).equals(" "))) {
                    return "";
                }
            }
            return null;
        }
    };

}
