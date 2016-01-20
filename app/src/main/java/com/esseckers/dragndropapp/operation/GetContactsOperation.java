package com.esseckers.dragndropapp.operation;

import android.support.annotation.NonNull;

import com.esseckers.dragndropapp.database.DataBaseGateway;
import com.esseckers.dragndropapp.model.ContactModel;
import com.redmadrobot.chronos.ChronosOperationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fake on 20.01.2016.
 */
public class GetContactsOperation extends AbstractOperation<List<ContactModel>> {

    @Override
    protected List<ContactModel> executeRoutine() {
        List<ContactModel> models = DataBaseGateway.getInstance().getContacts();
        if (models.isEmpty()) {
            DataBaseGateway.getInstance().sortContacts(createContacts());
        }
        return DataBaseGateway.getInstance().getContacts();
    }

    @NonNull
    @Override
    public Class<? extends ChronosOperationResult<List<ContactModel>>> getResultClass() {
        return Result.class;
    }

    public static class Result extends ChronosOperationResult<List<ContactModel>> {
    }

    private List<ContactModel> createContacts() {
        List<ContactModel> models = new ArrayList<ContactModel>();
        models.add(new ContactModel("1", "Ben", "http://tiaurus.info/wp-content/uploads/2011/12/Bezdomnyie-litsa-mira-0010.jpg"));
        models.add(new ContactModel("2", "Tom", "http://www.3dnews.ru/assets/external/illustrations/2008/04/11/79354.jpg"));
        models.add(new ContactModel("3", "Jef", "http://tiaurus.info/wp-content/uploads/2011/12/Bezdomnyie-litsa-mira-0010.jpg"));
        models.add(new ContactModel("4", "Raj", "http://www.3dnews.ru/assets/external/illustrations/2008/04/11/79354.jpg"));
        models.add(new ContactModel("5", "Kolya", "http://demiart.ru/forum/uploads/post-45623-1196274032.jpg"));
        models.add(new ContactModel("6", "Vitya", "http://tiaurus.info/wp-content/uploads/2011/12/Bezdomnyie-litsa-mira-0010.jpg"));
        models.add(new ContactModel("7", "Rob", "http://demiart.ru/forum/uploads/post-45623-1196274032.jpg"));
        models.add(new ContactModel("8", "Till", "http://tiaurus.info/wp-content/uploads/2011/12/Bezdomnyie-litsa-mira-0010.jpg"));
        models.add(new ContactModel("9", "Shon", "http://demiart.ru/forum/uploads/post-45623-1196274032.jpg"));
        return models;
    }
}
