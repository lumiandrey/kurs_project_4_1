package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.enumiration.PersonShowFilter;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.PersonListFragment;

public class PersonListActivity extends PersonUseApplicationActivity {

    private final static String KEY_ARG_PERSON_SHOW_FILTER= "message_users";
    private final static String KEY_ARG_TASK = "TASK_ID";

    @Override
    protected Fragment createFragment() {

        PersonListFragment resultFragment;

        PersonShowFilter personShowFilter = (PersonShowFilter) getIntent().getSerializableExtra(KEY_ARG_PERSON_SHOW_FILTER);

        if(personShowFilter.equals(PersonShowFilter.ADD_PERSON_TO_TASK)){
            int taskID = getIntent().getIntExtra(KEY_ARG_TASK, -1);
            if(taskID > 0){
                resultFragment = PersonListFragment.newInstanceAddToTask(taskID);
            } else {
                resultFragment = null;
            }
        } else {
            resultFragment = PersonListFragment.newInstance(personShowFilter);
        }
        return resultFragment;
    }

    public static Intent newInstance(@NonNull Context context, @NonNull PersonShowFilter personShowFilter){
        Intent intent = new Intent(context, PersonListActivity.class);

        intent.putExtra(KEY_ARG_PERSON_SHOW_FILTER, personShowFilter);

        return intent;
    }

    public static Intent newInstanceAddToTask(@NonNull Context context, int taskID){
        Intent intent = new Intent(context, PersonListActivity.class);

        intent.putExtra(KEY_ARG_TASK, taskID);
        intent.putExtra(KEY_ARG_PERSON_SHOW_FILTER, PersonShowFilter.ADD_PERSON_TO_TASK);

        return intent;
    }

}
