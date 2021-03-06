package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


/**
 * Created by Andrey on 10.11.2016.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    private final static String TAG = SingleFragmentActivity.class.getName();

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            if(fragment != null)
                fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        Log.d(TAG, "called abstract SingleFragmentActivity");

    }

}
