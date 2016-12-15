package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.PersonPagerActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.LoaderPersonData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.UpdateData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.ShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.DepartmentDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PostDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.DepartmentEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PostEntity;


public class PersonListFragment extends Fragment implements UpdateData<PersonEntity> {

    private final static String KEY_ARG_HANDLE = "message_users";

    private RecyclerView mTaskRecyclerView;
    private PersonAdapter mAdapter;
    private List<DepartmentEntity> mDepartmentEntities;
    private List<PostEntity> mPostEntities;
    private ShowProgress mShowProgress;
    private LoaderPersonData mLoaderUserData = null;

    @SuppressLint("ValidFragment")
    private PersonListFragment() {
    }

    public static PersonListFragment newInstance(){

        PersonListFragment fragment = new PersonListFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mDepartmentEntities = new DepartmentDaoLite(getContext()).reads();
        mPostEntities = new PostDaoLite(getContext()).reads();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container,
                false);

        mShowProgress = new ShowProgress(view.findViewById(R.id.progress_view), view.findViewById(R.id.data_view), getContext());

        mTaskRecyclerView = (RecyclerView) view
                .findViewById(R.id.list_recycler_view);

        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));

        mShowProgress = new ShowProgress(view.findViewById(R.id.progress_view), view.findViewById(R.id.data_view), getContext());

        updateUI();

        return view;
    }

    @Override
    public void endLoader(@NonNull List<PersonEntity> data) {

        if (mAdapter == null) {

            mAdapter = new PersonAdapter(data);
            mTaskRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

        } else {
            mAdapter.setUserEntities(data);
            mAdapter.notifyDataSetChanged();

        }
    }

    private void updateUI() {

        if ((mLoaderUserData != null) && mLoaderUserData.getStatus() != AsyncTask.Status.RUNNING) {
            if (mLoaderUserData.isCancelled()) {
                mLoaderUserData = new LoaderPersonData(
                        this,
                        mShowProgress,
                        getContext());

                mLoaderUserData.execute((Void[]) null);
            } else {
                mLoaderUserData.cancel(true);
                mLoaderUserData = new LoaderPersonData(
                        this,
                        mShowProgress,
                        getContext());

                mLoaderUserData.execute((Void[]) null);
            }

        } else if(mLoaderUserData != null && mLoaderUserData.getStatus() == AsyncTask.Status.PENDING) {

            mLoaderUserData = new LoaderPersonData(
                    this,
                    mShowProgress,
                    getContext());

            mLoaderUserData.execute((Void[]) null);
        } else if ((mLoaderUserData != null) && mLoaderUserData.getStatus() == AsyncTask.Status.FINISHED) {

            mLoaderUserData = new LoaderPersonData(
                    this,
                    mShowProgress,
                    getContext());

            mLoaderUserData.execute((Void[]) null);
        } else if (mLoaderUserData == null) {

            mLoaderUserData = new LoaderPersonData(
                    this,
                    mShowProgress,
                    getContext());

            mLoaderUserData.execute((Void[]) null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        updateUI();
    }

    private class PersonHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        private TextView mFIOTextView;
        private TextView mDepartmentTextView;
        private TextView mPostTextView;
        private Snackbar mSnackbar;

        private PersonEntity mPersonEntity;

        public PersonHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(this);

            mFIOTextView = (TextView) itemView.findViewById(R.id.fio_item);
            mDepartmentTextView = (TextView) itemView.findViewById(R.id.department_item);
            mPostTextView = (TextView) itemView.findViewById(R.id.post_item);
        }

        public void bindCrime(PersonEntity userEntity) {
            mPersonEntity = userEntity;

            mFIOTextView.setText(mPersonEntity.getName() + ' ' + mPersonEntity.getPatronymic() + ' ' + mPersonEntity.getSurname());
            for(DepartmentEntity o: mDepartmentEntities){
                if(o.getIdDepartment() == mPersonEntity.getIdDepartment()) {
                    mDepartmentTextView.setText(o.getNameDepartment());
                    break;
                }
            }
            for(PostEntity o: mPostEntities){
                if(o.getIdPost() == mPersonEntity.getIdPost())
                    mPostTextView.setText(o.getNamePost());
            }

        }

        @Override
        public boolean onLongClick(View v) {
            mSnackbar = Snackbar.make(v, "Перейти к редактированию?", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Да", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(PersonPagerActivity.newIntent(getContext(), mPersonEntity.getIdPerson()));
                        }
                    });
            mSnackbar.show();
            return false;
        }
    }

    private class PersonAdapter extends RecyclerView.Adapter<PersonHolder> {

        private List<PersonEntity> mUserEntities;

        public PersonAdapter(List<PersonEntity> userEntities) {
            mUserEntities = userEntities;
        }

        /**
         * вызывается виджетом RecyclerView , когда ему потребуется новое представление
         * для отображения элемента. В этом методе мы создаем объект View и упаковываем
         * его в ViewHolder . RecyclerView пока не ожидает, что представление будет связано
         * с какими-либо данными. Для получения представления мы заполняем макет из
         * стандартной библиотеки Android с именем simple_list_item_1.
         * Этот макет содержит один виджет TextView, оформленный так, чтобы он хорошо
         * смотрелся в списке.
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.person_item_list, parent, false);

            return new PersonHolder(view);
        }

        /**
         * связывает представление View объекта ViewHolder с объектом модели.
         * @param holder - ViewHolder
         * @param position - позиция в наборе даныных. Позиция используется для нахождения
         *                 правильных данных модели, после чего View обновляется в соответствии с этими данными.
         */
        @Override
        public void onBindViewHolder(PersonHolder holder, int position) {

            PersonEntity personEntity = mUserEntities.get(position);

            holder.bindCrime(personEntity);
        }

        @Override
        public int getItemCount() {

            return mUserEntities.size();
        }

        public void setUserEntities(List<PersonEntity> userEntities) {
            mUserEntities = userEntities;
        }
    }
}
