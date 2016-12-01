package by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.TaskService;

/**
 * Created by Андрей on 09.11.2016.
 */

public class TaskFragment extends Fragment {

    private final static String TAG = TaskFragment.class.getName();

    private static final String ARG_TASK_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 10;

    private TaskEntity mTaskEntity;

    private Button mDateBeginBtn;
    private Button mDateEndBtn;
    private EditText mDescriptionEdTx;
    private EditText mNameTaskEdTx;
    private TextView mNameAddPersonTaskTxV;
    private TextView mIdTaskTxV;
    private CheckBox mDoneTask;
    private ProgressBar mTaskProgressBar;

    /**
     * Мы не заполняем представление фрагмента.
     * @param savedInstanceState - использует объект Bundle для сохранения и загрузки состояния.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        int crimeId = getArguments().getInt(ARG_TASK_ID);

        mTaskEntity = TaskService.fintTaskBiId(crimeId);

        //returnResult();
    }

    /**
     * в этом методе заполняется макет представления фрагмента, а заполненный объект View возвращается активности-хосту.
     *
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task, container, false);

        mDateBeginBtn = (Button) view.findViewById(R.id.date_begin_task_fragment_btn);
        mDateEndBtn = (Button) view.findViewById(R.id.date_end_task_fragment_btn);
        mDescriptionEdTx = (EditText) view.findViewById(R.id.description_task_fragment_task_edit_text);
        mNameTaskEdTx = (EditText) view.findViewById(R.id.name_task_fragment_edit_text);
        mNameAddPersonTaskTxV = (TextView) view.findViewById(R.id.add_person_name_task_fragment_text_view);
        mIdTaskTxV = (TextView) view.findViewById(R.id.id_task_fragment_task_text_view);
        mDoneTask = (CheckBox) view.findViewById(R.id.done_task_fragment_check_box);
        mTaskProgressBar = (ProgressBar) view.findViewById(R.id.task_fragment_progress_bar);

        showData();

        updateDate();
        mDateBeginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mTaskEntity.getDateBegin());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_DATE);

                dialog.show(manager, DIALOG_DATE);
            }
        });

        return view;
    }

    private void showData(){

        if(mTaskEntity != null) {

            mDateBeginBtn.setText(mTaskEntity.getDateBeginString());
            mDateEndBtn.setText(mTaskEntity.getDateEndString());
            mDescriptionEdTx.setText(mTaskEntity.getDescription());
            mNameTaskEdTx.setText(mTaskEntity.getName());
            mNameAddPersonTaskTxV.setText(String.valueOf(mTaskEntity.getIdPersonAdd()));
            mIdTaskTxV.setText(String.valueOf(mTaskEntity.getIdTask()));
            mDoneTask.setChecked(mTaskEntity.isDone());
            mTaskProgressBar.setProgress(mTaskEntity.getProgress());
        }

    }


    @Override
    public void onPause() {
        super.onPause();

        //CrimeLab.get(getActivity()).updateCrime(mTaskEntity);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode){
            case REQUEST_DATE:{
                Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

                mTaskEntity.setDateBegin(date);
                updateDate();
            } break;
            case REQUEST_TIME: {
                Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_DATE);

                mTaskEntity.getDateBegin().setHours(date.getHours());
                mTaskEntity.getDateBegin().setMinutes(date.getMinutes());
                mTaskEntity.getDateBegin().setSeconds(date.getSeconds());
                updateTime();
            } break;

        }
    }

    private void updateDate() {
        //mDateBeginBtn.setText();
    }

    private void updateTime() {
        //mTimeButton.setText(new SimpleDateFormat("HH mm").format( mTaskEntity.getDate()));
    }

    public void finish(){
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
        //getFragmentManager().popBackStackImmediate();
    }

    public static TaskFragment newInstance(int idTask) {
        Bundle args = new Bundle();

        args.putInt(ARG_TASK_ID, idTask);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
