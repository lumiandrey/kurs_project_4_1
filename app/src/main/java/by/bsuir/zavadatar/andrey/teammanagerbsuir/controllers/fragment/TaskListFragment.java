package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.TypeShowTaskList;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.TaskPagerActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.TaskSingleActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.LoaderTaskData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.UpdateData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.ShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.TaskService;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;


/**
 * Created by Andrey on 10.11.2016.
 */

public class TaskListFragment extends Fragment implements UpdateData<TaskEntity> {

    private static final String TAG = TaskListFragment.class.getName();
    private static final int REQUEST_CRIME = 1;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private static final String ARG_KEY_TYPE_SHOW_TASK_LIST = "_type_show_task_list";
    public static final String CREATE_TASK_INTENT = "create task intent";

    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;
    private TypeShowTaskList mTypeShowTaskList;
    private boolean mSubtitleVisible;//?????? ???????????????? ???????????????? ?????????????????? ????????????????????????.
    private ShowProgress mShowProgress;
    private LoaderTaskData mLoaderTaskData = null;
    private TextView mMessageEmpty;

    @SuppressLint("ValidFragment")
    private TaskListFragment() {
    }

    public static TaskListFragment newInstance(TypeShowTaskList typeShowTaskList){

        Bundle args = new Bundle();

        args.putSerializable(ARG_KEY_TYPE_SHOW_TASK_LIST, typeShowTaskList);

        TaskListFragment fragment = new TaskListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle args = getArguments();
        if(args != null) {

            mTypeShowTaskList = (TypeShowTaskList) args.getSerializable(ARG_KEY_TYPE_SHOW_TASK_LIST);
        } else {
            mTypeShowTaskList = TypeShowTaskList.SHOW_All_PERSON_TASK;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list, container,
                false);

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        mMessageEmpty = (TextView) view.findViewById(R.id.message_list_empty_layout);
        mMessageEmpty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(TaskSingleActivity.newIntent(getContext()));
            }

        });

        mTaskRecyclerView = (RecyclerView) view
                .findViewById(R.id.list_recycler_view);

        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));

        mShowProgress = new ShowProgress(view.findViewById(R.id.progress_view), view.findViewById(R.id.data_view), getContext());

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CRIME && Activity.RESULT_OK == resultCode) {

        }

        Log.d(TAG, "resultCode " + resultCode);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void endLoader(@NonNull List<TaskEntity> data) {

        if(data.size() < 1){
            mMessageEmpty.setVisibility(View.VISIBLE);
        } else {
            mMessageEmpty.setVisibility(View.GONE);
        }

        if (mAdapter == null) {

            mAdapter = new TaskAdapter(data);
            mTaskRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

        } else {
            mAdapter.setTaskEntities(data);
            mAdapter.notifyDataSetChanged();

        }
    }

    private void updateUI() {

        if ((mLoaderTaskData != null) && mLoaderTaskData.getStatus() != AsyncTask.Status.RUNNING) {
            if (mLoaderTaskData.isCancelled()) {
                mLoaderTaskData = new LoaderTaskData(
                        mTypeShowTaskList,
                        this,
                        mShowProgress,
                        getContext());

                mLoaderTaskData.execute((Void[]) null);
            } else {
                mLoaderTaskData.cancel(true);
                mLoaderTaskData = new LoaderTaskData(
                        mTypeShowTaskList,
                        this,
                        mShowProgress,
                        getContext());

                mLoaderTaskData.execute((Void[]) null);
            }

        } else if(mLoaderTaskData != null && mLoaderTaskData.getStatus() == AsyncTask.Status.PENDING) {

            mLoaderTaskData = new LoaderTaskData(
                    mTypeShowTaskList,
                    this,
                    mShowProgress,
                    getContext());

            mLoaderTaskData.execute((Void[]) null);
        } else if ((mLoaderTaskData != null) && mLoaderTaskData.getStatus() == AsyncTask.Status.FINISHED) {

            mLoaderTaskData = new LoaderTaskData(
                    mTypeShowTaskList,
                    this,
                    mShowProgress,
                    getContext());

            mLoaderTaskData.execute((Void[]) null);
        } else if (mLoaderTaskData == null) {

            mLoaderTaskData = new LoaderTaskData(
                    mTypeShowTaskList,
                    this,
                    mShowProgress,
                    getContext());

            mLoaderTaskData.execute((Void[]) null);
        }

    }

    private void updateSubtitle() {

        int crimeCount = TaskService.mTaskEntities.size();
        String subtitle = getResources().getQuantityString(R.plurals.subtitle_plural, crimeCount, crimeCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    /**
     * ???????????? ???????????? ???? ?????????????????????????? ??????????????.
     */
    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private ProgressBar mProgressBar;

        private TaskEntity mTaskEntity;

        public TaskHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.name_task_item_list);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.date_end_task_item_list);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.done_task_item_list);

            mProgressBar =
                    (ProgressBar) itemView.findViewById(R.id.progress_bar_item_task_list);
        }

        public void bindCrime(TaskEntity crime) {
            mTaskEntity = crime;

            mTitleTextView.setText(mTaskEntity.getName());
            mDateTextView.setText(new SimpleDateFormat("dd MM yyyy").format( mTaskEntity.getDateEnd()));
            mSolvedCheckBox.setChecked(mTaskEntity.isDone());
            mProgressBar.setProgress(mTaskEntity.getProgress());
        }

        @Override
        public void onClick(View v) {

            Intent intent = TaskPagerActivity.newIntent(getActivity(),
                    mTaskEntity.getIdTask(), mTypeShowTaskList);

            startActivityForResult(intent, REQUEST_CRIME);

        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<TaskEntity> mTaskEntities;

        public TaskAdapter(List<TaskEntity> crimes) {
            mTaskEntities = crimes;
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
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.task_item_list, parent, false);

            return new TaskHolder(view);
        }

        /**
         * ?????????????????? ?????????????????????????? View ?????????????? ViewHolder ?? ???????????????? ????????????.
         * @param holder - ViewHolder
         * @param position - ?????????????? ?? ???????????? ??????????????. ?????????????? ???????????????????????? ?????? ????????????????????
         *                 ???????????????????? ???????????? ????????????, ?????????? ???????? View ?????????????????????? ?? ???????????????????????? ?? ?????????? ??????????????.
         */
        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {

            TaskEntity crime = mTaskEntities.get(position);

            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {

            return mTaskEntities.size();
        }

        public void setTaskEntities(List<TaskEntity> taskEntities) {
            mTaskEntities = taskEntities;
        }
    }
}
