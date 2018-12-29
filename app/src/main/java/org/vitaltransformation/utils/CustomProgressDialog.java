package org.vitaltransformation.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.vitaltransformation.R;

public class CustomProgressDialog extends ProgressDialog {

    private CustomProgressDialog(Context context) {
        super(context);
    }

    public static ProgressDialog create(Context context) {
        CustomProgressDialog dialog = new CustomProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);

        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(0xFFce6213, android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void show() {
        super.show();
        TextView txtMessage = findViewById(R.id.txtProgressDialog_msg);
        txtMessage.setText("");
        txtMessage.setVisibility(View.GONE);
    }

    public void setMessage(String message) {
        TextView txtMessage = findViewById(R.id.txtProgressDialog_msg);
        txtMessage.setVisibility(View.VISIBLE);
        txtMessage.setText(message);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
