package by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.activity.TaskListFragmentActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;

/**
 * Created by Andrey on 26.11.2016.
 */

public class UserRoomFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = UserRoomFragment.class.getName();
    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private PersonEntity mPersonEntity;

    private TextView test_user_room;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "create User Room Fragment");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_room, container,
                false);

        mContext = inflater.getContext();

        mPersonEntity = ApplicationSettings.sPersonEntity(getContext());

        test_user_room = (TextView) view.findViewById(R.id.test_user_room);
        test_user_room.setText(mPersonEntity.toString());

        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.user_room, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_all_tasks_this_person:{
                startActivity(new Intent(mContext, TaskListFragmentActivity.class));
            } break;
            default:
                Log.d(TAG, "Default Action (no action)");
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
