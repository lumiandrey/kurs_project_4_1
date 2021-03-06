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
import android.widget.Toast;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.PersonPagerActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.LoaderPersonData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.UpdateData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.enumiration.PersonShowFilter;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.ShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.HasTaskPersonDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.DepartmentDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.HasTaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PostDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.DepartmentEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.HasTaskPersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PostEntity;


public class PersonListFragment extends Fragment implements UpdateData<PersonEntity> {

    private final static String KEY_ARG_PERSON_SHOW_FILTER= "message_users";
    private final static String KEY_ARG_TASK = "TASK_ID";

    private RecyclerView mTaskRecyclerView;
    private PersonAdapter mAdapter;
    private List<DepartmentEntity> mDepartmentEntities;
    private List<PostEntity> mPostEntities;
    private ShowProgress mShowProgress;
    private LoaderPersonData mLoaderUserData = null;
    private PersonShowFilter personShowFilter;
    private int taskID;

    @SuppressLint("ValidFragment")
    private PersonListFragment() {
    }

    public static PersonListFragment newInstance(@NonNull PersonShowFilter personShowFilter){

        Bundle args = new Bundle();

        args.putSerializable(KEY_ARG_PERSON_SHOW_FILTER, personShowFilter);

        if(personShowFilter.equals(PersonShowFilter.ADD_PERSON_TO_TASK))
            throw new RuntimeException("Error! no ID TASK!!!");
        PersonListFragment fragment = new PersonListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static PersonListFragment newInstanceAddToTask(int taskID){

        Bundle args = new Bundle();

        args.putInt(KEY_ARG_TASK, taskID);
        args.putSerializable(KEY_ARG_PERSON_SHOW_FILTER, PersonShowFilter.ADD_PERSON_TO_TASK);

        PersonListFragment fragment = new PersonListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mDepartmentEntities = new DepartmentDaoLite(getContext()).reads();
        mPostEntities = new PostDaoLite(getContext()).reads();

        personShowFilter = (PersonShowFilter) getArguments().getSerializable(KEY_ARG_PERSON_SHOW_FILTER);

        if (personShowFilter != null && personShowFilter.equals(PersonShowFilter.ADD_PERSON_TO_TASK))
            taskID = getArguments().getInt(KEY_ARG_TASK);

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

            View.OnClickListener clickListener = null;
            String message = null;
            String titleAction = null;

            if (personShowFilter != null && personShowFilter.equals(PersonShowFilter.ADD_PERSON_TO_TASK)){
                clickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String messageToast = getString(R.string.error_add_to_base);
                        HasTaskPersonDao hasTaskPersonDao = new HasTaskDaoLite(getContext());
                        int hasTaskPersonID = hasTaskPersonDao
                                .getIDHasByIDPersonIDTask(mPersonEntity.getIdPerson(), taskID);
                        if( hasTaskPersonID < 0){
                            long id = hasTaskPersonDao.create(new HasTaskPersonEntity(0, taskID, mPersonEntity.getIdPerson()));

                            if(id > 0)
                                messageToast = getString(R.string.add_log_time_message_successfully);
                            else
                                messageToast = getString(R.string.add_log_time_message_failed);
                        } else {
                            messageToast = "???????????????????????? ?????? ???????????????? ?? ??????????????!";
                        }
                        Toast.makeText(getContext(), messageToast, Toast.LENGTH_LONG).show();
                    }
                };
                message = "???????????????? ?? ?????????????? ?????????????";
                titleAction = "????";
            } else {
                clickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(PersonPagerActivity.newIntent(getContext(), mPersonEntity.getIdPerson()));
                    }
                };
                message = "?????????????? ?? ?????????????????????????????";
                titleAction = "????";
            }
            mSnackbar = Snackbar.make(v, message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(titleAction, clickListener);
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
         * ???????????????????? ???????????????? RecyclerView , ?????????? ?????? ?????????????????????? ?????????? ??????????????????????????
         * ?????? ?????????????????????? ????????????????. ?? ???????? ???????????? ???? ?????????????? ???????????? View ?? ??????????????????????
         * ?????? ?? ViewHolder . RecyclerView ???????? ???? ??????????????, ?????? ?????????????????????????? ?????????? ??????????????
         * ?? ????????????-???????? ??????????????. ?????? ?????????????????? ?????????????????????????? ???? ?????????????????? ?????????? ????
         * ?????????????????????? ???????????????????? Android ?? ???????????? simple_list_item_1.
         * ???????? ?????????? ???????????????? ???????? ???????????? TextView, ?????????????????????? ??????, ?????????? ???? ????????????
         * ?????????????????? ?? ????????????.
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
         * ?????????????????? ?????????????????????????? View ?????????????? ViewHolder ?? ???????????????? ????????????.
         * @param holder - ViewHolder
         * @param position - ?????????????? ?? ???????????? ??????????????. ?????????????? ???????????????????????? ?????? ????????????????????
         *                 ???????????????????? ???????????? ????????????, ?????????? ???????? View ?????????????????????? ?? ???????????????????????? ?? ?????????? ??????????????.
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
