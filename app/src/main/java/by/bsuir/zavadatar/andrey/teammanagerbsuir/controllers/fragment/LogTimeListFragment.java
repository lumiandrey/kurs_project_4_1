package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.LogTimePagerActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.HasTaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.LogTimeDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.LogTimeStorage;


public class LogTimeListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private static final String ARG_TASK_ID = "task_id";
    private static final String ARG_TASK_NAME = "task_name";

    private Context mContext;
    private RecyclerView mTaskRecyclerView;
    private LogTimeAdapter mAdapter;
    private boolean mSubtitleVisible;//для хранения признака видимости подзаголовка.
    private TextView mMessageEmpty;

    private static Long taskID;
    private static String nameTask;


    @SuppressLint("ValidFragment")
    private LogTimeListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mContext = getContext();

        Bundle args = getArguments();
        if(args != null){

            nameTask = args.getString(ARG_TASK_NAME);
            taskID = args.getLong(ARG_TASK_ID);
        } else {

            nameTask = "Empty name";
            taskID = 0L;
        }
    }

    private void addTask() {

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


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    private void updateUI() {

        List<LogTimeTaskEntity> entities = LogTimeStorage.getData(mContext, taskID);;

        if(entities.size() < 1){
            mMessageEmpty.setVisibility(View.VISIBLE);
        } else {
            mMessageEmpty.setVisibility(View.GONE);
        }

        if (mAdapter == null) {

            mAdapter = new LogTimeAdapter(entities);
            mTaskRecyclerView.setAdapter(mAdapter);

        } else {
            mAdapter.setTaskEntities(entities);
            mAdapter.notifyDataSetChanged();

        }

    }

    /**
     * хранит ссылку на представление объекта.
     */
    private class LogTimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public static final int LENGTH_DESCRIPTION = 20;
        private TextView mLogTimeID;
        private TextView mDescription;
        private TextView mDate;
        private TextView mHours;
        private Snackbar mSnackbar;

        private LogTimeTaskEntity mTaskEntity;

        public LogTimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            mLogTimeID = (TextView) itemView.findViewById(R.id.id_log_task);
            mDescription = (TextView) itemView.findViewById(R.id.log_time_description);
            mDate = (TextView) itemView.findViewById(R.id.log_time_date);
            mHours = (TextView) itemView.findViewById(R.id.log_time_hours);
        }

        @SuppressLint("SetTextI18n")
        public void bindCrime(LogTimeTaskEntity crime) {
            mTaskEntity = crime;

            mLogTimeID.setText(String.valueOf(mTaskEntity.getIdLog()));
            String description = mTaskEntity.getDescription().length() > LENGTH_DESCRIPTION ?
                    mTaskEntity.getDescription().substring(0, LENGTH_DESCRIPTION) + ' ' + getString(R.string.more) :
                    mTaskEntity.getDescription();
            mDescription.setText(description);
            mDate.setText(mTaskEntity.getDateLogString());
            mHours.setText(String.valueOf(mTaskEntity.getHours()));

        }

        @Override
        public void onClick(View v) {

            Intent intent = LogTimePagerActivity.newIntent(
                    getActivity(),
                    taskID,
                    nameTask,
                    mTaskEntity.getIdLog()
            );

            startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            mSnackbar = Snackbar.make(v, R.string.confirm_deletion, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Удалить", snackbarOnClickListener);
            mSnackbar.show();
            return true;
        }

        View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int idPerson = ApplicationSettings.getIdPersonSystem(getContext());

                int idHas = new HasTaskDaoLite(getContext()).getIDHasByIDPersonIDTask(idPerson, taskID.intValue());
                String message;

                if (idHas > 0) {
                    if(new LogTimeDaoLite(getContext()).delete(mTaskEntity) > 0) {
                        updateUI();
                        message = getString(R.string.delete_successfully);
                    } else {
                        message = getString(R.string.delete_fail);
                    }
                } else {
                    message = getString(R.string.error_access_message);
                }

                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        };
    }

    private class LogTimeAdapter extends RecyclerView.Adapter<LogTimeHolder> {

        private List<LogTimeTaskEntity> mTaskEntities;

        public LogTimeAdapter(List<LogTimeTaskEntity> crimes) {
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
        public LogTimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.log_task_item, parent, false);

            return new LogTimeHolder(view);
        }

        /**
         * связывает представление View объекта ViewHolder с объектом модели.
         * @param holder - ViewHolder
         * @param position - позиция в наборе даныных. Позиция используется для нахождения
         *                 правильных данных модели, после чего View обновляется в соответствии с этими данными.
         */
        @Override
        public void onBindViewHolder(LogTimeHolder holder, int position) {

            LogTimeTaskEntity crime = mTaskEntities.get(position);

            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {

            return mTaskEntities.size();
        }

        public void setTaskEntities(List<LogTimeTaskEntity> taskEntities) {
            mTaskEntities = taskEntities;
        }
    }

    public static LogTimeListFragment newInstance(String nameTask, Long idTask){
        Bundle args = new Bundle();

        args.putString(ARG_TASK_NAME, nameTask);
        args.putLong(ARG_TASK_ID, idTask);

        LogTimeListFragment fragment = new LogTimeListFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
