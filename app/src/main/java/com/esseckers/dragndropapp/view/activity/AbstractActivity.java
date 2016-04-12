package com.esseckers.dragndropapp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.esseckers.dragndropapp.R;

import butterknife.ButterKnife;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class AbstractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
    }
}
