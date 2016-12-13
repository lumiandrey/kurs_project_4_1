package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.TypeShowTaskList;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.TypeUserName;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.Converter;

public abstract class PersonUseApplicationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = PersonUseApplicationActivity.class.getName();
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    private FragmentManager fm;

    private TextView mTvNamePerson;
    private ImageView mIvPhotoPerson;

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

        int menuResID;
        if(ApplicationSettings.getAccessLevelName(getApplicationContext()).equals(TypeUserName.Admin))
            menuResID = R.menu.admin_room_drawer;
        else
            menuResID = R.menu.user_room_drawer;
        navigationView.inflateMenu(menuResID);

        mTvNamePerson = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name_person_nav_panel);
        mTvNamePerson.setText(ApplicationSettings.getFIO(this));

        mIvPhotoPerson = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.image_person);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outHeight = (int) Converter.convertDpToPixel(40, getApplicationContext());
        options.outWidth = (int) Converter.convertDpToPixel(40, getApplicationContext());
        mIvPhotoPerson.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.user, options));

        fm = getSupportFragmentManager();
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

    final protected void replaceFrame(@NonNull Fragment frame){
        fm.beginTransaction()
                .replace(R.id.frame_person_room_application, frame)
                .commit();
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
            //User  menu item
            case R.id.go_to_home_person:{
                finish();
                startActivity(UserRoomActivity.newIntent(getApplicationContext()));
            } break;
            case R.id.nav_add_task_this_person:{
                startActivity(TaskSingleActivity.newIntent(getApplicationContext()));
            } break;
            case R.id.nav_all_tasks_this_person:{

                startActivity(TaskListFragmentActivity.newInstance(getApplicationContext(), TypeShowTaskList.SHOW_All_PERSON_TASK));
            } break;
            case R.id.nav_done_tasks_this_person:{

                startActivity(TaskListFragmentActivity.newInstance(getApplicationContext(), TypeShowTaskList.SHOW_DONE_PERSON_TASK));
            } break;
            case R.id.nav_current_tasks_this_person:{

                startActivity(TaskListFragmentActivity.newInstance(getApplicationContext(), TypeShowTaskList.SHOW_CURRENT_PERSON_TASK));
            } break;
            case R.id.setting_application_person:{
                finish();
                startActivity(new Intent(getApplicationContext(), SettingApplicationActivity.class));
            } break;
            case R.id.lot_out_person:{
                ApplicationSettings.logOut(getApplicationContext());
                finish();
                startActivity(new Intent(LoginActivity.newIntent(getApplicationContext())));
            } break;
            //Admin menu item
            case R.id.nav_show_all_tasks_person:{
                startActivity(TaskListFragmentActivity.newInstance(getApplicationContext(), TypeShowTaskList.SHOW_ALL_TASK));
            } break;
            case R.id.show_person:{
                startActivity(PersonListActivity.newInstance(this));
            } break;
            case R.id.show_users:{
                startActivity(UserListFragmentActivity.newInstance(this));
            } break;

            default:
                Log.d(TAG, "Default Action (no action)");
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
