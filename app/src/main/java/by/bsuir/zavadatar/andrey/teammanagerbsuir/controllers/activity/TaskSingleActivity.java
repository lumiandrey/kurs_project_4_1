package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.Operation;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.TaskFragment;

public class TaskSingleActivity extends PersonUseApplicationActivity {

    private static final String TAG = LogTimeSingleActivity.class.getName();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        super.replaceFrame(TaskFragment.newInstance(
                0,
                Operation.CREATE)
        );

        Log.d(TAG, "create LogTimeSingleActivity");
    }

    @Override
    protected Fragment createFragment() {
        return null;
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, TaskSingleActivity.class);

        return intent;
    }
}
