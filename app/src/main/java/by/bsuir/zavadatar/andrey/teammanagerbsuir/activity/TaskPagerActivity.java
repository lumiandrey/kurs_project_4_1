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
import java.util.UUID;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.TaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment.TaskFragment;

/**
 * Created by Andrey on 12.11.2016.
 */

public class TaskPagerActivity extends AppCompatActivity {

    private static final String EXTRA_TASK_ID = "TaskEntity.taskId";
    private static final String TAG = TaskPagerActivity.class.getName();

    private ViewPager mViewPager;
    private List<TaskEntity> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_pager);

        int taskId = getIntent().getIntExtra(EXTRA_TASK_ID, 0);

        mViewPager = (ViewPager) findViewById(R.id.activity_task_pager_view_pager);
        //получаем набор данных — контейнер List объектов .

        FragmentManager fragmentManager = getSupportFragmentManager();//получаем экземпляр FragmentManager для активности.

        //адаптером назначается безымянный экземпляр FragmentStatePagerAdapter
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            /**
             * получает экземпляр Crime для заданной позиции в наборе данных,
             * после чего использует его идентификатор для создания и возвращения
             * правильно настроенного экземпляра CrimeFragment .
             * @param position - позиция в списке
             * @return - настроенный фрагмент
             */
            @Override
            public Fragment getItem(int position) {
                TaskEntity crime = mCrimes.get(position);
                return TaskFragment.newInstance(crime.getIdTask());
            }

            /**
             * возвращает текущее количество элементов в списке.
             * @return - текущее количество элементов в списке.
             */
            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        //ищет с определенным индексом объект и запускает именно его
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getIdTask().equals(taskId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        Log.d(TAG, "called CrimePagerActivity");
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, TaskPagerActivity.class);

        intent.putExtra(EXTRA_TASK_ID, crimeId);

        return intent;
    }

}
