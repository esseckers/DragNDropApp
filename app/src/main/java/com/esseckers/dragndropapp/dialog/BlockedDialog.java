package com.esseckers.dragndropapp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

import com.esseckers.dragndropapp.R;

import butterknife.ButterKnife;

public class BlockedDialog extends Dialog {
    private static BlockedDialog prevDialog;

    public BlockedDialog(Activity context) {
        super(context);
    }

    @Override
    public void show() {
        if (prevDialog != null && prevDialog.isShowing()) {
            prevDialog.dismiss();
        }
        super.show();
        prevDialog = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(getLayoutInflater().inflate(R.layout.dialog_progress, null));
        ButterKnife.bind(this);
    }
}
