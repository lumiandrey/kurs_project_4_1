package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.TypeShowTaskList;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.TaskListFragment;

/**
 * Created by Andrey on 28.11.2016.
 */

public class TaskListFragmentActivity extends PersonUseApplicationActivity {

    private static final String ARG_KEY_TYPE_SHOW_TASK_LIST = "_type_show_task_list";

    @Override
    protected Fragment createFragment() {
        return TaskListFragment.newInstance(
                (TypeShowTaskList) getIntent().getSerializableExtra(ARG_KEY_TYPE_SHOW_TASK_LIST)
        );
    }

    public static Intent newInstance(@NonNull Context context, @NonNull TypeShowTaskList typeShowTaskList){
        Intent intent = new Intent(context, TaskListFragmentActivity.class);

        intent.putExtra(ARG_KEY_TYPE_SHOW_TASK_LIST, typeShowTaskList);

        return intent;
    }
}
