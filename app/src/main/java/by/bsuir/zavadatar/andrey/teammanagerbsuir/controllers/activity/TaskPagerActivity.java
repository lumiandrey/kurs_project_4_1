package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.TypeShowTaskList;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.LoaderTaskData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.UpdateData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.Operation;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.TaskFragment;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.ShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;

/**
 * Created by Andrey on 12.11.2016.
 */

public class TaskPagerActivity extends AppCompatActivity implements UpdateData<TaskEntity> {

    private static final String ARG_KEY_TASK_ID = "TaskEntity.taskId";
    private static final String ARG_KEY_TYPE_SHOW_TASK_LIST = "_type_show_task_list";
    private static final String TAG = TaskPagerActivity.class.getName();

    private ViewPager mViewPager;
    private List<TaskEntity> mTaskEntities;
    private TypeShowTaskList mTypeShowTaskList;
    private ShowProgress mShowProgress;
    private LoaderTaskData mLoaderTaskData = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!ApplicationSettings.isAuthorisation(this)){
            gotoLoginPage();
        } else {

            setContentView(R.layout.activity_pager);

            mTypeShowTaskList = (TypeShowTaskList) getIntent().getSerializableExtra(ARG_KEY_TYPE_SHOW_TASK_LIST);

            mViewPager = (ViewPager) findViewById(R.id.activity_task_pager_view_pager);

            mShowProgress = new ShowProgress(findViewById(R.id.progress_view), findViewById(R.id.data_view), this);

            loadData();

            Log.d(TAG, "called TaskPagerActivity");
        }
    }

    @Override
    public void endLoader(@NonNull List<TaskEntity> data) {
        mTaskEntities = data;

        FragmentManager fragmentManager = getSupportFragmentManager();//получаем экземпляр FragmentManager для активности.

        //адаптером назначается безымянный экземпляр FragmentStatePagerAdapter
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            /**
             * получает экземпляр Task для заданной позиции в наборе данных,
             * после чего использует его идентификатор для создания и возвращения
             * правильно настроенного экземпляра Fragment .
             *
             * @param position - позиция в списке
             * @return - настроенный фрагмент
             */
            @Override
            public Fragment getItem(int position) {
                TaskEntity task = mTaskEntities.get(position);

                Operation operation;

                switch (ApplicationSettings.getAccessLevelName(getApplicationContext())) {
                    case Admin: {
                        operation = Operation.SHOW_OR_UPDATE;
                    }
                    break;
                    case Worker:
                    case Guest: {
                        if (task.getIdPersonAdd() == ApplicationSettings.getIdPersonSystem(getApplicationContext()))
                            operation = Operation.SHOW_OR_UPDATE;
                        else
                            operation = Operation.SHOW;
                    }
                    break;
                    default:
                        operation = Operation.SHOW;
                }

                return TaskFragment.newInstance(task.getIdTask(), operation);
            }

            /**
             * возвращает текущее количество элементов в списке.
             *
             * @return - текущее количество элементов в списке.
             */
            @Override
            public int getCount() {
                return mTaskEntities.size();
            }
        });

        //ищет с определенным индексом объект и запускает именно его
        for (int i = 0; i < mTaskEntities.size(); i++) {
            if (mTaskEntities.get(i).getIdTask().equals(getIntent().getIntExtra(ARG_KEY_TASK_ID, 0))) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    private void loadData(){

        if ((mLoaderTaskData != null) && mLoaderTaskData.getStatus() != AsyncTask.Status.RUNNING) {
            if (mLoaderTaskData.isCancelled()) {
                mLoaderTaskData = new LoaderTaskData(
                        mTypeShowTaskList,
                        this,
                        mShowProgress,
                        this);

                mLoaderTaskData.execute((Void[]) null);
            } else {
                mLoaderTaskData.cancel(true);
                mLoaderTaskData = new LoaderTaskData(
                        mTypeShowTaskList,
                        this,
                        mShowProgress,
                        this);

                mLoaderTaskData.execute((Void[]) null);
            }

        } else if(mLoaderTaskData != null && mLoaderTaskData.getStatus() == AsyncTask.Status.PENDING) {

            mLoaderTaskData = new LoaderTaskData(
                    mTypeShowTaskList,
                    this,
                    mShowProgress,
                    this);

            mLoaderTaskData.execute((Void[]) null);
        } else if ((mLoaderTaskData != null) && mLoaderTaskData.getStatus() == AsyncTask.Status.FINISHED) {

            mLoaderTaskData = new LoaderTaskData(
                    mTypeShowTaskList,
                    this,
                    mShowProgress,
                    this);

            mLoaderTaskData.execute((Void[]) null);
        } else if (mLoaderTaskData == null) {

            mLoaderTaskData = new LoaderTaskData(
                    mTypeShowTaskList,
                    this,
                    mShowProgress,
                    this);

            mLoaderTaskData.execute((Void[]) null);
        }
    }

    public static Intent newIntent(Context packageContext, int taskId, TypeShowTaskList typeShowTaskList) {
        Intent intent = new Intent(packageContext, TaskPagerActivity.class);

        intent.putExtra(ARG_KEY_TASK_ID, taskId);
        intent.putExtra(ARG_KEY_TYPE_SHOW_TASK_LIST, typeShowTaskList);

        return intent;
    }

    private void gotoLoginPage() {
        finish();
        startActivity(LoginActivity.newIntent(this));
    }

}
