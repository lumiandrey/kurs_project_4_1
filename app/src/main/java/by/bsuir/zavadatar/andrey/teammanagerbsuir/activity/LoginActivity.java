package by.bsuir.zavadatar.andrey.teammanagerbsuir.activity;

import android.support.v4.app.Fragment;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.LoginFragment;

public class LoginActivity extends SingleFragmentActivity  {

    private static final String TAG = LoginActivity.class.getName();

    @Override
    protected Fragment createFragment() {

        Log.d(TAG, "create Login Activity");

        return new LoginFragment();
    }
}

