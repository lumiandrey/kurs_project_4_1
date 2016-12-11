package by.bsuir.zavadatar.andrey.teammanagerbsuir.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.LogTimeFragment;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.Operation;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.LogTimeStorage;

public class LogTimePagerActivity extends AppCompatActivity {

    private static final String TAG = LogTimePagerActivity.class.getName();
    private static final String EXTRA_TASK_ID = "TaskEntity.taskId";
    private static final String EXTRA_TASK_NAME = "TaskEntity.name";
    private static final String EXTRA_LOG_TIME_ID = "LogTimeEntity.ID";

    private ViewPager mViewPager;
    private List<LogTimeTaskEntity> mTaskEntities;
    private long taskID;
    private long logID;
    private String nameTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!ApplicationSettings.isAuthorisation(this)){
            gotoLoginPage();
        } else {

            setContentView(R.layout.activity_pager);

            taskID = getIntent().getIntExtra(EXTRA_TASK_ID, 0);
            nameTask = getIntent().getStringExtra(EXTRA_TASK_NAME);
            logID = getIntent().getLongExtra(EXTRA_LOG_TIME_ID, 0L);

            mViewPager = (ViewPager) findViewById(R.id.activity_task_pager_view_pager);

            mTaskEntities = LogTimeStorage.getData(getApplicationContext(), taskID);

            mTaskEntities = (mTaskEntities != null ? mTaskEntities : new ArrayList<LogTimeTaskEntity>());
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

                    Operation operation;

                    switch (ApplicationSettings.getAccessLevelName(getApplicationContext())) {
                        case Admin: {
                            operation = Operation.SHOW_OR_UPDATE;
                        }
                        break;
                        case Worker:
                        case Guest: {
                                operation = Operation.SHOW;
                        } break;
                        default:
                            operation = Operation.SHOW;
                    }

                    return LogTimeFragment.newInstance(nameTask, taskID, operation);
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
                if (mTaskEntities.get(i).getIdLog() == logID) {
                    mViewPager.setCurrentItem(i);
                    break;
                }
            }
        }
    }

    private void gotoLoginPage() {
        finish();
        startActivity(LoginActivity.newIntent(this));
    }

    public static Intent newIntent(Context context, long taskID, @NonNull String nameTask, int logTimeID){

        Intent intent = new Intent(context, LogTimePagerActivity.class);

        intent.putExtra(EXTRA_TASK_ID, taskID);
        intent.putExtra(EXTRA_TASK_NAME, nameTask);
        intent.putExtra(EXTRA_LOG_TIME_ID, logTimeID);

        return intent;
    }
}
