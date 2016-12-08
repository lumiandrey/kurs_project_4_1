package by.bsuir.zavadatar.andrey.teammanagerbsuir.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.UserRoomFragment;

public class UserRoomActivity extends PersonUseApplicationActivity { //

    private static final String TAG = UserRoomActivity.class.getName();

    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "create Room Activity");

        return new UserRoomFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, UserRoomActivity.class);

        return intent;
    }
}
