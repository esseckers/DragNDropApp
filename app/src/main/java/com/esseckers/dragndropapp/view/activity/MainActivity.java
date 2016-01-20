package com.esseckers.dragndropapp.view.activity;

import android.support.v7.widget.RecyclerView;

import com.esseckers.dragndropapp.R;
import com.esseckers.dragndropapp.operation.GetContactsOperation;

import butterknife.Bind;

public class MainActivity extends AbstractActivity {

    @Bind(R.id.rv_contacts)
    RecyclerView rvContacts;

    @Override
    protected void initView() {
        super.initView();
    }

    public void onOperationFinished(final GetContactsOperation.Result result) {
        blockerDialog.dismiss();
        if (result.isSuccessful()) {

        }
    }
}
