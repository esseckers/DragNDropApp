package com.esseckers.dragndropapp.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.esseckers.dragndropapp.R;
import com.esseckers.dragndropapp.dialog.BlockedDialog;

import butterknife.ButterKnife;

/**
 * Created by Fake on 20.01.2016.
 */
public class AbstractActivity extends Activity {

    protected BlockedDialog blockerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        blockerDialog = new BlockedDialog(this);
        initView();
    }

    protected void initView() {

    }
}
