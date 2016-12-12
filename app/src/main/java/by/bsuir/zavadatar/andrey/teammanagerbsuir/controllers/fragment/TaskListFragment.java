package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.TaskPagerActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.TaskSingleActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.TaskService;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.TaskStorage;


/**
 * Created by Andrey on 10.11.2016.
 */

public class TaskListFragment extends Fragment {

    private static final String TAG = TaskListFragment.class.getName();
    private static final int REQUEST_CRIME = 1;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    public static final String CREATE_TASK_INTENT = "create task intent";

    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;
    private boolean mSubtitleVisible;//для хранения признака видимости подзаголовка.
    private TextView mMessageEmpty;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void addTask() {
        TaskEntity entity = new TaskEntity();

        //Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());

        //startActivity(intent);
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

    private void updateUI() {

        List<TaskEntity> entities =
                TaskStorage.getData(
                        getContext(),
                        ApplicationSettings.getIdPersonSystem(getContext())
                );

        if(entities.size() < 1){
            mMessageEmpty.setVisibility(View.VISIBLE);
        } else {
            mMessageEmpty.setVisibility(View.GONE);
        }

        if (mAdapter == null) {

            mAdapter = new TaskAdapter(entities);
            mTaskRecyclerView.setAdapter(mAdapter);

        } else {
            mAdapter.setTaskEntities(entities);
            mAdapter.notifyDataSetChanged();

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
     * хранит ссылку на представление объекта.
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
                    mTaskEntity.getIdTask());

            startActivityForResult(intent, REQUEST_CRIME);

        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<TaskEntity> mTaskEntities;

        public TaskAdapter(List<TaskEntity> crimes) {
            mTaskEntities = crimes;
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
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.task_item_list, parent, false);

            return new TaskHolder(view);
        }

        /**
         * связывает представление View объекта ViewHolder с объектом модели.
         * @param holder - ViewHolder
         * @param position - позиция в наборе даныных. Позиция используется для нахождения
         *                 правильных данных модели, после чего View обновляется в соответствии с этими данными.
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
