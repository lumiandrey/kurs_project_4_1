package by.bsuir.zavadatar.andrey.teammanagerbsuir.activity;

import android.support.v4.app.Fragment;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.TaskListFragment;

/**
 * Created by Andrey on 28.11.2016.
 */

public class TaskListFragmentActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }
}