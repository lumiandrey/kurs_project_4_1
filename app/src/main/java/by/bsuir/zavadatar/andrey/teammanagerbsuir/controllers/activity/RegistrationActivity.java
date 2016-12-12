package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.RegistrationFragment;

public class RegistrationActivity extends SingleFragmentActivity {
    
    @Override
    protected Fragment createFragment() {
        return RegistrationFragment.newInstance();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, RegistrationActivity.class);

        return intent;
    }

}
