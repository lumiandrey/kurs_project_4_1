package by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.HasTaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.LogTimeDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeActivityDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeActivityEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;


public class LogTimeFragment extends Fragment {

    private final static String TAG = LogTimeFragment.class.getName();

    private static final int REQUEST_DATE = 1_000_111_999;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_NAME = "Name task";
    private static final String ARG_ID_TASK = "Task id";
    private static final String ARG_OPERATION = "_logOperation";
    private static final String ARG_ID_LOG = "_LogTaskID";

    private TextView mIdTaskTxV;
    private TextView mNameTaskTxV;
    private Button mDateBtn;
    private Button mAddBtn;
    private EditText mHours;
    private EditText mDescriptionEdTx;
    private Spinner mTypeActivitySpinner;

    private LogTimeTaskEntity mLogTimeTaskEntity;

    private String mNameTask;
    private Long idTask;
    private Integer idPerson;
    private Operation mOperation;
    private List<TypeActivityEntity> mActivityEntityList = null;
    private boolean isCorrect =  false;

    @SuppressLint("ValidFragment")
    private LogTimeFragment() {
    }

    public static LogTimeFragment newInstance(String nameTask, Long idTask, Operation operation){
        Bundle args = new Bundle();

        args.putString(ARG_NAME, nameTask);
        args.putLong(ARG_ID_TASK, idTask);
        args.putSerializable(ARG_OPERATION, operation);

        LogTimeFragment fragment = new LogTimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static LogTimeFragment newInstance(Long idLog, Operation operation){
        Bundle args = new Bundle();

        args.putLong(ARG_ID_LOG, idLog);
        args.putSerializable(ARG_OPERATION, operation);

        LogTimeFragment fragment = new LogTimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle args = getArguments();

        if(args != null) {

            idPerson = ApplicationSettings.getIdPersonSystem(getContext());
            mNameTask = args.getString(ARG_NAME);
            idTask = args.getLong(ARG_ID_TASK);

            mOperation = (Operation) args.getSerializable(ARG_OPERATION);

            assert mOperation != null;
            switch (mOperation){
                case CREATE:{
                    mLogTimeTaskEntity = new LogTimeTaskEntity();
                    isCorrect = true;
                } break;
                case SHOW_OR_UPDATE: {
                    mLogTimeTaskEntity = new LogTimeDaoLite(getContext()).read((int) args.getLong(ARG_ID_LOG));
                    isCorrect = true;
                } break;
                case SHOW:{
                    //TODO-Andrey блокирование всех элементов ввода, только просмотр
                    mLogTimeTaskEntity = new LogTimeDaoLite(getContext()).read((int) args.getLong(ARG_ID_LOG));
                    isCorrect = false;
                } break;

            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log_time, container, false);

        mDateBtn = (Button) view.findViewById(R.id.date_log_task_fragment_btn);
        mAddBtn = (Button) view.findViewById(R.id.add_log_task_fragment_btn);
        mDescriptionEdTx = (EditText) view.findViewById(R.id.description_log_task_fragment_edit_text);
        mHours = (EditText) view.findViewById(R.id.hours_log_task_fragment_edit_text);
        mNameTaskTxV = (TextView) view.findViewById(R.id.name_task_log_task_fragment_label);
        mIdTaskTxV = (TextView) view.findViewById(R.id.id_task_log_task_fragment_label);
        mTypeActivitySpinner = (Spinner) view.findViewById(R.id.type_activity_log_task_fragment_spinner);

        showData();
        updateDate();

        if(mOperation.equals(Operation.CREATE)) {
            mAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message;

                    if(isCorrectData()) {

                        int idHas = new HasTaskDaoLite(getContext()).getIDHasByIDPersonIDTask(idPerson, idTask.intValue());

                        if (idHas > 0) {
                            initEntity();
                            mLogTimeTaskEntity.setIdHasTaskPerson(idHas);
                            mLogTimeTaskEntity.setIdLog((int) new LogTimeDaoLite(getContext()).create(mLogTimeTaskEntity));
                            message = getString(R.string.add_log_time_message_successfully);
                        } else {
                            message = getString(R.string.add_log_time_message_failed);
                        }
                    } else {
                        message = getString(R.string.error_correct);
                    }
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            });
            mAddBtn.setText(R.string.add_log_time_task);
        } else {
            mAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message;

                    if(isCorrectData()) {

                        initEntity();

                        new LogTimeDaoLite(getContext()).update(mLogTimeTaskEntity);

                        message = getString(R.string.update_log_time_message_successfully);

                    } else {
                        message = getString(R.string.error_correct);
                    }
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            });
            mAddBtn.setText(R.string.update_log_time_task);
        }

        mDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mLogTimeTaskEntity.getDateLog());
                dialog.setTargetFragment(LogTimeFragment.this, REQUEST_DATE);

                dialog.show(manager, DIALOG_DATE);
            }
        });

        if(!isCorrect){
            mDateBtn.setClickable(false);
            mAddBtn.setVisibility(View.GONE);
            mDescriptionEdTx.setFocusable(false);
            mHours.setFocusable(false);
            mTypeActivitySpinner.setClickable(false);
        }

        return view;
    }

    private void initEntity() {
        mLogTimeTaskEntity.setDescription(mDescriptionEdTx.getText().toString());
        mLogTimeTaskEntity.setHours(Float.parseFloat(mHours.getText().toString()));
    }

    private boolean isCorrectData(){
        boolean result = false;

        if(mDescriptionEdTx.getText().toString().length() > 1 &&
                Float.parseFloat(mHours.getText().toString()) > 0)
            result = true;

        return result;
    }

    private void showData(){

        if(mLogTimeTaskEntity != null) {

            mDateBtn.setText(mLogTimeTaskEntity.getDateLogString());
            mDescriptionEdTx.setText(mLogTimeTaskEntity.getDescription());
            mHours.setText(String.valueOf(mLogTimeTaskEntity.getHours()));
            mNameTaskTxV.setText(mNameTask);
            mIdTaskTxV.setText(String.valueOf(idTask));



        }

        mActivityEntityList = new TypeActivityDaoLite(getContext()).reads();

        if(mActivityEntityList != null){

            final List<String> listTypeActivityString = new ArrayList<>();
            for(TypeActivityEntity o: mActivityEntityList)
                listTypeActivityString.add(o.getNameActivity());

            ArrayAdapter<String> adapterActivity =
                    new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_spinner_item,
                            listTypeActivityString.toArray(new String[listTypeActivityString.size()]));
            adapterActivity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mTypeActivitySpinner.setAdapter(adapterActivity);
            mTypeActivitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {

                    mLogTimeTaskEntity.setIdTypeActivity(mActivityEntityList.get(position).getIdTypeActivity());

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            for(int i = 0; i < mActivityEntityList.size(); i++){
                if(mActivityEntityList.get(i).getIdTypeActivity() == (mLogTimeTaskEntity.getIdTypeActivity())) {
                    mTypeActivitySpinner.setSelection(i);
                        break;
                    } else {
                        mTypeActivitySpinner.setSelection(0);
                }
            }

        }

    }

    private void updateDate() {
        mDateBtn.setText(mLogTimeTaskEntity.getDateLogString());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode){
            case REQUEST_DATE:{
                Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

                mLogTimeTaskEntity.setDateLog(date);
                updateDate();
            } break;
        }
    }
}
