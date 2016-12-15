package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.PersonListFragment;

public class PersonListActivity extends PersonUseApplicationActivity {

    @Override
    protected Fragment createFragment() {
        return PersonListFragment.newInstance();
    }

    public static Intent newInstance(@NonNull Context context){
        Intent intent = new Intent(context, PersonListActivity.class);

        return intent;
    }

}
