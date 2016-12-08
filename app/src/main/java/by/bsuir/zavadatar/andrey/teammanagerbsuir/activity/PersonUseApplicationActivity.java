package by.bsuir.zavadatar.andrey.teammanagerbsuir.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;

public abstract class PersonUseApplicationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = PersonUseApplicationActivity.class.getName();
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_room_application);

        if(!ApplicationSettings.isAuthorisation(this)){
            gotoLoginPage();
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_person_room_application);

        navigationView = (NavigationView) findViewById(R.id.nav_view_person_room_application);
        navigationView.setNavigationItemSelectedListener(this);

        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.name_person_nav_panel)).setText(ApplicationSettings.getFIO(this));

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frame_person_room_application);

        if (fragment == null) {
            fragment = createFragment();
            if(fragment != null)
                fm.beginTransaction()
                        .replace(R.id.frame_person_room_application, fragment)
                        .commit();
        }

        Log.d(TAG, "create PersonUseApplicationActivity");
    }

    private void gotoLoginPage() {
        finish();
        startActivity(LoginActivity.newIntent(this));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.go_to_home_person:{
                finish();
                startActivity(UserRoomActivity.newIntent(getApplicationContext()));
            } break;
            case R.id.nav_add_task_this_person:{

            } break;
            case R.id.nav_all_tasks_this_person:{
                startActivity(new Intent(getApplicationContext(), TaskListFragmentActivity.class));
            } break;
            case R.id.setting_application_person:{
                startActivity(new Intent(getApplicationContext(), SettingApplicationActivity.class));
            } break;
            case R.id.lot_out_person:{
                ApplicationSettings.logOut(getApplicationContext());
                finish();
                startActivity(new Intent(LoginActivity.newIntent(getApplicationContext())));

            } break;
            default:
                Log.d(TAG, "Default Action (no action)");
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
