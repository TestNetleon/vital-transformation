package org.vitaltransformation.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import org.vitaltransformation.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Toaster {

    static final int INDEFINITE = -2;
    static final int SHORT = -1;
    static final int MEDIUM = 1;
    static final int LONG = 2;
    public static Toaster instance;
    private static AlertDialog.Builder mBuilder;
    private static AlertDialog mDialog;
    private static View mDialogView;

    public static Toaster getInstance() {
        if (instance == null) instance = new Toaster();
        return instance;
    }

    public static void showToast(Context context, String message) {
        Toaster.getInstance().create(context).setMessage(message).setCancelable(true)
                .setNeutralButton("OKAY", new ToasterInterface.OnClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog) {
                        dialog.dismiss();
                    }
                }).show(true);
    }

    public static void showToast(Context context, String title, String message) {
        Toaster.getInstance().create(context).setTitle(title).setMessage(message)
                .setCancelable(true)
                .setNeutralButton("OKAY", new ToasterInterface.OnClickListener() {
                    @Override
                    public void onClick(AlertDialog dialog) {
                        dialog.dismiss();
                    }
                }).show(true);
    }

    public Toaster create(Context context) {
        new Toaster();
        mBuilder = new AlertDialog.Builder(context, R.style.MyCustomTheme);
        mDialogView = getLayoutInflater(context).inflate(R.layout.dialog_toaster, null);
        mBuilder.setView(mDialogView);
        return instance;
    }

    public Toaster setTitle(String title) {
        TextView mTextTitle = mDialogView.findViewById(R.id.textToaster_title);
        mTextTitle.setVisibility(View.VISIBLE);
        View divView = mDialogView.findViewById(R.id.vu4);
        divView.setVisibility(View.VISIBLE);
        mTextTitle.setText(title);
        return instance;
    }

    public Toaster setCancelable(boolean b) {
        mBuilder.setCancelable(b);
        return instance;
    }

    public Toaster setMessage(String message) {
        TextView mTextMessage = mDialogView.findViewById(R.id.textToaster_message);
        mTextMessage.setText(message);
        mTextMessage.setMovementMethod(new ScrollingMovementMethod());
        return instance;
    }

    public Toaster setPositiveButton(CharSequence buttonText, final ToasterInterface.OnClickListener onClickListener) {
        TextView mButtonPos = mDialogView.findViewById(R.id.textToaster_positive);
        mButtonPos.setVisibility(View.VISIBLE);
        mButtonPos.setText(buttonText);
        mButtonPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null && mDialog != null) {
                    onClickListener.onClick(mDialog);
                }
            }
        });
        return instance;
    }

    public Toaster setNegativeButton(CharSequence buttonText, final ToasterInterface.OnClickListener onClickListener) {
        TextView mButtonNeg = mDialogView.findViewById(R.id.textToaster_negative);
        mButtonNeg.setVisibility(View.VISIBLE);
        mButtonNeg.setText(buttonText);
        mButtonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null && mDialog != null) {
                    onClickListener.onClick(mDialog);
                }
            }
        });
        return instance;
    }

    public Toaster setNeutralButton(CharSequence buttonText, final ToasterInterface.OnClickListener onClickListener) {
        TextView mButtonNeutral = mDialogView.findViewById(R.id.textToaster_neutral);
        mButtonNeutral.setVisibility(View.VISIBLE);
        mButtonNeutral.setText(buttonText);
        mButtonNeutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null && mDialog != null) {
                    onClickListener.onClick(mDialog);
                }
            }
        });
        return instance;
    }

    public void show(boolean cancelOnTouchOutside) {
        mDialog = mBuilder.create();
        mDialog.setCanceledOnTouchOutside(cancelOnTouchOutside);
//        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x50000000));
        mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mDialog.getWindow().setDimAmount(0.5f);
        mDialog.show();
    }

    private LayoutInflater getLayoutInflater(Context context) {
        return ((Activity) context).getLayoutInflater();
    }

    @IntDef({INDEFINITE, SHORT, MEDIUM, LONG})
    @Retention(RetentionPolicy.SOURCE)
    @interface Duration {
    }

    public static class ToasterInterface {
        public interface OnClickListener {
            void onClick(AlertDialog dialog);
        }
    }
}