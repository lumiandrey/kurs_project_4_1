package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.LogTimeFragment;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.Operation;

public class LogTimeSingleActivity extends PersonUseApplicationActivity {

    private static final String TAG = LogTimeSingleActivity.class.getName();

    private static final String ARG_NAME = "Name task";
    private static final String ARG_ID_TASK = "Task id";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if(getIntent().getExtras() != null)
            super.replaceFrame(LogTimeFragment.newInstance(
                    getIntent().getExtras().getString(ARG_NAME),
                    getIntent().getExtras().getLong(ARG_ID_TASK),
                Operation.CREATE) );

        Log.d(TAG, "create LogTimeSingleActivity");
    }

    @Override
    protected Fragment createFragment() {
        return null;
    }

    public static Intent newIntent(Context packageContext, String nameTask, Long idTask) {
        Intent intent = new Intent(packageContext, LogTimeSingleActivity.class);

        intent.putExtra(ARG_NAME, nameTask);
        intent.putExtra(ARG_ID_TASK, idTask);

        return intent;
    }
}
