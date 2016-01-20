package com.esseckers.dragndropapp.operation;

import android.support.annotation.NonNull;

import com.esseckers.dragndropapp.database.DataBaseGateway;
import com.esseckers.dragndropapp.model.ContactModel;
import com.redmadrobot.chronos.ChronosOperationResult;

import java.util.List;

/**
 * Created by Fake on 20.01.2016.
 */
public class SortContactsOperation extends AbstractOperation<Boolean> {

    private List<ContactModel> contactModels;

    public SortContactsOperation(List<ContactModel> contactModels) {
        this.contactModels = contactModels;
    }

    @Override
    protected Boolean executeRoutine() {
        return DataBaseGateway.getInstance().sortContacts(contactModels);
    }

    @NonNull
    @Override
    public Class<? extends ChronosOperationResult<Boolean>> getResultClass() {
        return Result.class;
    }

    public static class Result extends ChronosOperationResult<Boolean> {
    }
}
