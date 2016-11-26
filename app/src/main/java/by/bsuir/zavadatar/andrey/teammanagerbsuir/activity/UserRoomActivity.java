package by.bsuir.zavadatar.andrey.teammanagerbsuir.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.UserRoomFragment;

public class UserRoomActivity extends SingleFragmentActivity { //implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = UserRoomActivity.class.getName();

    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "create Room Activity");

        return new UserRoomFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, UserRoomActivity.class);

        //intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }
}
