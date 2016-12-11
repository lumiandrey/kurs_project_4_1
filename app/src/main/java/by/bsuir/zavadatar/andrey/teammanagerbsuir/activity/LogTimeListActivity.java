package by.bsuir.zavadatar.andrey.teammanagerbsuir.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.LogTimeListFragment;

public class LogTimeListActivity extends PersonUseApplicationActivity {

    private static final String TAG = LogTimePagerActivity.class.getName();

    private static final String EXTRA_TASK_ID = "TaskEntity.taskId";
    private static final String EXTRA_TASK_NAME = "TaskEntity.name";

    @Override
    protected Fragment createFragment() {
        return null;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if(getIntent().getExtras() != null)
            super.replaceFrame(
                    LogTimeListFragment.newInstance(
                            getIntent().getExtras().getString(EXTRA_TASK_NAME),
                            getIntent().getExtras().getLong(EXTRA_TASK_ID)
                    )
            );

        Log.d(TAG, "create LogTimeSingleActivity");
    }


    public static Intent newIntent(Context context, long taskID, @NonNull String nameTask){

        Intent intent = new Intent(context, LogTimeListActivity.class);

        intent.putExtra(EXTRA_TASK_ID, taskID);
        intent.putExtra(EXTRA_TASK_NAME, nameTask);

        return intent;
    }
}
