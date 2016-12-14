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

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.LoaderPersonData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.UpdateData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment.EditPersonFragment;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.ShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;

public class PersonPagerActivity extends PersonUseApplicationActivity implements UpdateData<PersonEntity> {

    private static final String EXTRA_PERSON_ID = "PERSON_ENTITY.PERSON_ID";
    private List<PersonEntity> mPersonEntities;
    private ViewPager mViewPager;
    private ShowProgress mShowProgress;
    private LoaderPersonData mLoaderUserData = null;
    private int personID;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_pager);

        personID = getIntent().getIntExtra(EXTRA_PERSON_ID, 0);

        mViewPager = (ViewPager) findViewById(R.id.activity_task_pager_view_pager);

        mShowProgress = new ShowProgress(findViewById(R.id.progress_view), findViewById(R.id.data_view), this);

        loadData();
    }

    public static Intent newIntent(Context context, int personID){

        Intent intent = new Intent(context, PersonPagerActivity.class);

        intent.putExtra(EXTRA_PERSON_ID, personID);

        return intent;
    }

    private void loadData(){

        if ((mLoaderUserData != null) && mLoaderUserData.getStatus() != AsyncTask.Status.RUNNING) {
            if (mLoaderUserData.isCancelled()) {
                mLoaderUserData = new LoaderPersonData(
                        this,
                        mShowProgress,
                        this);

                mLoaderUserData.execute((Void[]) null);
            } else {
                mLoaderUserData.cancel(true);
                mLoaderUserData = new LoaderPersonData(

                        this,
                        mShowProgress,
                        this);

                mLoaderUserData.execute((Void[]) null);
            }

        } else if(mLoaderUserData != null && mLoaderUserData.getStatus() == AsyncTask.Status.PENDING) {

            mLoaderUserData = new LoaderPersonData(
                    this,
                    mShowProgress,
                    this);

            mLoaderUserData.execute((Void[]) null);
        } else if ((mLoaderUserData != null) && mLoaderUserData.getStatus() == AsyncTask.Status.FINISHED) {

            mLoaderUserData = new LoaderPersonData(
                    this,
                    mShowProgress,
                    this);

            mLoaderUserData.execute((Void[]) null);
        } else if (mLoaderUserData == null) {

            mLoaderUserData = new LoaderPersonData(
                    this,
                    mShowProgress,
                    this);

            mLoaderUserData.execute((Void[]) null);
        }
    }

    @Override
    protected Fragment createFragment() {
        return null;
    }

    @Override
    public void endLoader(@NonNull List<PersonEntity> data) {
        mPersonEntities = data;

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
                PersonEntity personEntity = mPersonEntities.get(position);


                return EditPersonFragment.newInstance(personEntity);
            }

            /**
             * возвращает текущее количество элементов в списке.
             *
             * @return - текущее количество элементов в списке.
             */
            @Override
            public int getCount() {
                return mPersonEntities.size();
            }
        });

        //ищет с определенным индексом объект и запускает именно его
        for (int i = 0; i < mPersonEntities.size(); i++) {
            if (mPersonEntities.get(i).getIdPerson() == personID ) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
