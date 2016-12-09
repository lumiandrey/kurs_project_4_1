package by.bsuir.zavadatar.andrey.teammanagerbsuir.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.Operation;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.TaskFragment;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.TaskService;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;

/**
 * Created by Andrey on 12.11.2016.
 */

public class TaskPagerActivity extends AppCompatActivity {

    private static final String EXTRA_TASK_ID = "TaskEntity.taskId";
    private static final String TAG = TaskPagerActivity.class.getName();

    private ViewPager mViewPager;
    private List<TaskEntity> mTaskEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!ApplicationSettings.isAuthorisation(this)){
            gotoLoginPage();
        }

        setContentView(R.layout.activity_tasks_pager);

        int taskId = getIntent().getIntExtra(EXTRA_TASK_ID, 0);

        mViewPager = (ViewPager) findViewById(R.id.activity_task_pager_view_pager);
        mTaskEntities = TaskService.mTaskEntities;

        FragmentManager fragmentManager = getSupportFragmentManager();//получаем экземпляр FragmentManager для активности.

        //адаптером назначается безымянный экземпляр FragmentStatePagerAdapter
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            /**
             * получает экземпляр Task для заданной позиции в наборе данных,
             * после чего использует его идентификатор для создания и возвращения
             * правильно настроенного экземпляра Fragment .
             * @param position - позиция в списке
             * @return - настроенный фрагмент
             */
            @Override
            public Fragment getItem(int position) {
                TaskEntity task = mTaskEntities.get(position);

                Operation operation = Operation.SHOW;

                switch (ApplicationSettings.getAccessLevelName(getApplicationContext())){
                    case Admin:{
                        operation = Operation.SHOW_OR_UPDATE;
                    } break;
                    case Worker:
                    case Guest:{
                        if(task.getIdPersonAdd() == ApplicationSettings.getIdPersonSystem(getApplicationContext()))
                            operation = Operation.SHOW_OR_UPDATE;
                        else
                            operation = Operation.SHOW;
                    } break;
                    default:
                        operation = Operation.SHOW;
                }

                return TaskFragment.newInstance(task.getIdTask(), operation);
            }

            /**
             * возвращает текущее количество элементов в списке.
             * @return - текущее количество элементов в списке.
             */
            @Override
            public int getCount() {
                return mTaskEntities.size();
            }
        });

        //ищет с определенным индексом объект и запускает именно его
        for (int i = 0; i < mTaskEntities.size(); i++) {
            if (mTaskEntities.get(i).getIdTask().equals(taskId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        Log.d(TAG, "called TaskPagerActivity");
    }

    public static Intent newIntent(Context packageContext, int taskId) {
        Intent intent = new Intent(packageContext, TaskPagerActivity.class);

        intent.putExtra(EXTRA_TASK_ID, taskId);

        return intent;
    }

    private void gotoLoginPage() {
        finish();
        startActivity(LoginActivity.newIntent(this));
    }

}
