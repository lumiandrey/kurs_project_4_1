package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.LogTimeListActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.LogTimeSingleActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.enumiration.Operation;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.HasTaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PersonDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeTaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.HasTaskPersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeTaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.TypeUserName;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;

/**
 * Created by Андрей on 09.11.2016.
 */

public class TaskFragment extends Fragment {

    private final static String TAG = TaskFragment.class.getName();

    private static final String ARG_TASK_ID = "task_id";
    private static final String ARG_TASK_OPERATION = "task_operation_fragment";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE_BEGIN = 111_111_111;
    private static final int REQUEST_DATE_END = 123_123_123;
    private static final int REQUEST_TIME = 10;

    private TaskEntity mTaskEntity;
    private Operation mOperation;
    private List<TypeTaskEntity> mTypeTaskEntities = null;

    private Button mDateBeginBtn;
    private Button mDateEndBtn;
    private Button mAddLogTimeBtn;
    private Button mShowLogTimeBtn;
    private Button mAddTaskBtn;
    private Button mAddPersonToTaskBtn;
    private EditText mDescriptionEdTx;
    private EditText mNameTaskEdTx;
    private EditText mProgressBarEdTx;
    private TextView mNameAddPersonTaskTxV;
    private TextView mIdTaskTxV;
    private CheckBox mDoneTask;
    private Spinner mTypeTaskSpinner;
    private ProgressBar mTaskProgressBar;
    private LinearLayout mLayoutLogTask;
    private LinearLayout mAddingLayout;

    @SuppressLint("ValidFragment")
    private TaskFragment() {
    }

    public static TaskFragment newInstance(int idTask, Operation operationTask) {
        Bundle args = new Bundle();

        args.putInt(ARG_TASK_ID, idTask);
        args.putSerializable(ARG_TASK_OPERATION, operationTask);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /**
 * Мы не заполняем представление фрагмента.
 * @param savedInstanceState - использует объект Bundle для сохранения и загрузки состояния.
 */
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);

            int crimeId;
            Bundle args = getArguments();

            if(args != null) {
                crimeId = args.getInt(ARG_TASK_ID);
                mOperation = (Operation) args.getSerializable(ARG_TASK_OPERATION);

                if(Operation.CREATE.equals(mOperation)) {

                    mTaskEntity = new TaskEntity();
                    mTaskEntity.setIdPersonAdd(ApplicationSettings.getIdPersonSystem(getContext()));
                } else {

                    mTaskEntity = new TaskDaoLite(getContext()).read(crimeId);
                    if(mTaskEntity == null)
                        this.finish();
                }
            }

            mTypeTaskEntities = new TypeTaskDaoLite(getContext()).reads();
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
        mAddLogTimeBtn = (Button) view.findViewById(R.id.add_log_time_task_fragment_btn);
        mShowLogTimeBtn = (Button) view.findViewById(R.id.show_logs_task_fragment_btn);
        mAddTaskBtn = (Button) view.findViewById(R.id.add_task_fragment_btn);
        mAddPersonToTaskBtn = (Button) view.findViewById(R.id.add_poerson_to_task_fragment_btn);
        mDescriptionEdTx = (EditText) view.findViewById(R.id.description_task_fragment_task_edit_text);
        mNameTaskEdTx = (EditText) view.findViewById(R.id.name_task_fragment_edit_text);
        mProgressBarEdTx = (EditText) view.findViewById(R.id.edit_progress_bar);
        mNameAddPersonTaskTxV = (TextView) view.findViewById(R.id.add_person_name_task_fragment_text_view);
        mIdTaskTxV = (TextView) view.findViewById(R.id.id_task_fragment_task_text_view);
        mDoneTask = (CheckBox) view.findViewById(R.id.done_task_fragment_check_box);
        mTaskProgressBar = (ProgressBar) view.findViewById(R.id.task_fragment_progress_bar);
        mLayoutLogTask = (LinearLayout) view.findViewById(R.id.layout_log_task);
        mAddingLayout = (LinearLayout) view.findViewById(R.id.layout_add_task);
        mTypeTaskSpinner = (Spinner) view.findViewById(R.id.type_task_spinner);

        mAddPersonToTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if(ApplicationSettings.isWorking(getContext())) {

            mAddPersonToTaskBtn.setVisibility(View.VISIBLE);
        } else {

            mAddPersonToTaskBtn.setVisibility(View.GONE);
        }

        mDoneTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTaskEntity.setProgress(100);
                    mTaskEntity.setDone(1);

                } else {
                    mTaskEntity.setProgress(1);
                    mTaskEntity.setDone(0);
                }
                mTaskProgressBar.setProgress(mTaskEntity.getProgress());
                mProgressBarEdTx.setText(String.valueOf(mTaskEntity.getProgress()));
            }
        });

        mProgressBarEdTx.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // Здесь намеренно оставлено пустое место
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {

                mProgressBarEdTx.setError(null);

                if(c.toString().length() > 0) {
                    int valueProgressBar = Integer.valueOf(mProgressBarEdTx.getText().toString());
                    if (valueProgressBar < 0 || valueProgressBar > 101) {
                        mProgressBarEdTx.setError(getString(R.string.error_range_value_progress_bar));
                        mProgressBarEdTx.requestFocus();
                    } else {

                        if(valueProgressBar == 100) {
                            mDoneTask.setChecked(true);
                        }
                        mTaskEntity.setProgress(valueProgressBar);
                    }
                } else {
                    mProgressBarEdTx.setError(getString(R.string.error_field_required));
                    mProgressBarEdTx.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable c) {
                mTaskProgressBar.setProgress(mTaskEntity.getProgress());
            }

        });

        mDateBeginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mTaskEntity.getDateBegin());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_DATE_BEGIN);

                dialog.show(manager, DIALOG_DATE);
            }
        });

        mDateEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mTaskEntity.getDateEnd());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_DATE_END);

                dialog.show(manager, DIALOG_DATE);
            }
        });

        mAddLogTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LogTimeSingleActivity.newIntent(getContext(), mTaskEntity.getName(), Long.valueOf(mTaskEntity.getIdTask())));
            }
        });

        mShowLogTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(
                        LogTimeListActivity.newIntent(
                        getContext(),
                        Long.valueOf(mTaskEntity.getIdTask()),
                        mTaskEntity.getName())
                );

            }
        });

        final List<String> listTypeActivityString = new ArrayList<>();
        for(TypeTaskEntity o: mTypeTaskEntities)
            listTypeActivityString.add(o.getNameType());

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item,
                        listTypeActivityString.toArray(new String[listTypeActivityString.size()]));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeTaskSpinner.setAdapter(arrayAdapter);
        mTypeTaskSpinner.setEnabled(false);
        mTypeTaskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTaskEntity.setIdTypeTask(mTypeTaskEntities.get(position).getIdTypeTask());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        int i = 0;
        boolean isSelected = false;
        for(; i < mTypeTaskEntities.size(); i++){
            if(mTypeTaskEntities.get(i).getIdTypeTask() == mTaskEntity.getIdTypeTask()) {
                isSelected = true;
                break;
            }
        }

        if(isSelected) {
            mTypeTaskSpinner.setSelection(i);
        } else {
            mTypeTaskSpinner.setSelection(0);
        }

        updateDate();

        changeOperation();

        return view;
    }

    /**
     * заполняет меню, определенное в файле fragment_task.xml
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(ApplicationSettings.getAccessLevelName(getContext()).equals(TypeUserName.Admin) ||
                mTaskEntity.getIdPersonAdd() == ApplicationSettings.getIdPersonSystem(getContext()))
            inflater.inflate(R.menu.fragment_task, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;
        switch (item.getItemId()) {
            case R.id.menu_item_delete:

                new TaskDaoLite(getContext()).delete(mTaskEntity);

                finish();

                result = true;
                break;

            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    private boolean isCorrectTask(){

        boolean result = true;

        mDateEndBtn.setError(null);
        mProgressBarEdTx.setError(null);
        mNameTaskEdTx.setError(null);

        if(mTaskEntity.getDateBegin().getTime() > mTaskEntity.getDateEnd().getTime()){
            mDateEndBtn.setError(getString(R.string.error_date_end));
            mDateEndBtn.requestFocus();
            result = false;
        }
        if(mNameTaskEdTx.getText().toString().length() < 1){
            mNameTaskEdTx.setError(getString(R.string.error_field_required));
            mNameTaskEdTx.requestFocus();
            result = false;
        }

        if(mDescriptionEdTx.getText().toString().length() < 1){
            mDescriptionEdTx.setError(getString(R.string.error_field_required));
            mDescriptionEdTx.requestFocus();
            result = false;
        }

        if(mProgressBarEdTx.getText().toString().length() > 0) {

            int valueProgressBar = Integer.valueOf(mProgressBarEdTx.getText().toString());

            if (valueProgressBar < 0 && valueProgressBar > 101) {
                mProgressBarEdTx.setError(getString(R.string.error_range_value_progress_bar));
                mProgressBarEdTx.requestFocus();
                result = false;
            }
        } else {
            mProgressBarEdTx.setError(getString(R.string.error_field_required));
            mProgressBarEdTx.requestFocus();
            result = false;
        }

        return result;
    }

    private void initEntity(){

        mTaskEntity.setName(mNameTaskEdTx.getText().toString());
        mTaskEntity.setDescription(mDescriptionEdTx.getText().toString());


    }

    private void changeOperation(){

        switch (mOperation){

            case CREATE:{

                mAddingLayout.setVisibility(View.VISIBLE);
                mLayoutLogTask.setVisibility(View.GONE);
                mDateBeginBtn.setEnabled(true);
                mDateEndBtn.setEnabled(true);
                mTypeTaskSpinner.setEnabled(true);
                mDescriptionEdTx.setEnabled(true);
                mNameTaskEdTx.setEnabled(true);
            } break;
            case SHOW_OR_UPDATE:
            case SHOW:{

                mAddingLayout.setVisibility(View.GONE);

                mLayoutLogTask.setVisibility(View.VISIBLE);

                mAddingLayout.setVisibility(View.VISIBLE);

                if(ApplicationSettings.getAccessLevelName(getContext()).equals(TypeUserName.Admin) ||
                        mTaskEntity.getIdPersonAdd() == ApplicationSettings.getIdPersonSystem(getContext())){

                    mDateBeginBtn.setEnabled(true);
                    mDateEndBtn.setEnabled(true);
                    mDescriptionEdTx.setEnabled(true);
                    mNameTaskEdTx.setEnabled(true);
                    mTypeTaskSpinner.setEnabled(true);
                } else {

                    mAddingLayout.setVisibility(View.GONE);
                }
            } break;
        }

        if(mOperation.equals(Operation.CREATE)) {
            mAddTaskBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String message;

                    if (isCorrectTask()) {

                        initEntity();

                        long taskID = new TaskDaoLite(getContext()).create(mTaskEntity);

                        if (taskID > 0) {
                            mTaskEntity.setIdTask((int) taskID);

                            new HasTaskDaoLite(getContext())
                                    .create(new HasTaskPersonEntity(
                                            0,
                                            (int) taskID,
                                            mTaskEntity.getIdPersonAdd()
                                    ));

                            mOperation = Operation.SHOW_OR_UPDATE;
                            changeOperation();

                            message = getString(R.string.add_log_time_message_successfully);
                        } else {
                            message = getString(R.string.add_log_time_message_failed);
                        }
                    } else {
                        message = getString(R.string.error_correct);
                    }

                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            mAddTaskBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String message;

                    if (isCorrectTask()) {

                        initEntity();

                        new TaskDaoLite(getContext()).update(mTaskEntity);

                        mOperation = Operation.SHOW_OR_UPDATE;
                        changeOperation();

                        message = getString(R.string.update_log_time_message_successfully);
                    } else {

                        message = getString(R.string.error_correct);
                    }

                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }
            });
            mAddTaskBtn.setText(R.string.update_button);
        }

        showData();
    }

    private void showData(){

        if(mTaskEntity != null) {

            mDateBeginBtn.setText(mTaskEntity.getDateBeginString());
            mDateEndBtn.setText(mTaskEntity.getDateEndString());
            mDescriptionEdTx.setText(mTaskEntity.getDescription());
            mNameTaskEdTx.setText(mTaskEntity.getName());

            mNameAddPersonTaskTxV.setText(getIO(new PersonDaoLite(getContext()).read(mTaskEntity.getIdPersonAdd())));
            mIdTaskTxV.setText(String.valueOf(mTaskEntity.getIdTask()));
            mDoneTask.setChecked(mTaskEntity.isDone());
            mTaskProgressBar.setProgress(mTaskEntity.getProgress());
            mProgressBarEdTx.setText(String.valueOf(mTaskEntity.getProgress()));
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
            case REQUEST_DATE_BEGIN:{
                Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

                mTaskEntity.setDateBegin(date);
                updateDate();
            } break;
            case REQUEST_DATE_END:{
                Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

                mTaskEntity.setDateEnd(date);
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
        mDateBeginBtn.setText(mTaskEntity.getDateBeginString());
        mDateEndBtn.setText(mTaskEntity.getDateEndString());
    }

    private void updateTime() {
        //mTimeButton.setText(new SimpleDateFormat("HH mm").format( mTaskEntity.getDateAndTime()));
    }

    public void finish(){
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
        //getFragmentManager().popBackStackImmediate();
    }

    private String getIO(PersonEntity personEntity){
        return personEntity.getName() + ' ' + personEntity.getPatronymic();
    }


}
