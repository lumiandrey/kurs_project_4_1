package by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeActivityEntity;


public class LogTimeFragment extends Fragment {

    private final static String TAG = LogTimeFragment.class.getName();

    private static final int REQUEST_DATE = 0;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_NAME = "Name task";
    private static final String ARG_ID_TASK = "Task id";

    private TextView mIdTaskTxV;
    private TextView mNameTaskTxV;
    private Button mDateBtn;
    private EditText mHours;
    private EditText mDescriptionEdTx;
    private Spinner mTypeActivitySpinner;

    private LogTimeTaskEntity mLogTimeTaskEntity;
    private String mNameTask;
    private Integer idTask;
    private List<TypeActivityEntity> mActivityEntityList = null;

    public static LogTimeFragment newInstance(String nameTask, Integer idTask){
        Bundle args = new Bundle();
        args.putString(ARG_NAME, nameTask);
        args.putInt(ARG_ID_TASK, idTask);

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
            mNameTask = args.getString(ARG_NAME);
            idTask = args.getInt(ARG_ID_TASK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task, container, false);

        mDateBtn = (Button) view.findViewById(R.id.date_log_task_fragment_btn);
        mDescriptionEdTx = (EditText) view.findViewById(R.id.description_log_task_fragment_edit_text);
        mHours = (EditText) view.findViewById(R.id.hours_log_task_fragment_edit_text);
        mNameTaskTxV = (TextView) view.findViewById(R.id.name_task_log_task_fragment_label);
        mIdTaskTxV = (TextView) view.findViewById(R.id.id_task_log_task_fragment_label);
        mTypeActivitySpinner = (Spinner) view.findViewById(R.id.type_activity_log_task_fragment_spinner);

        showData();
        updateDate();

        mDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mLogTimeTaskEntity.getDateLog());
                dialog.setTargetFragment(LogTimeFragment.this, REQUEST_DATE);

                dialog.show(manager, DIALOG_DATE);
            }
        });



        return view;
    }

    private void showData(){

        if(mLogTimeTaskEntity != null) {

            mDateBtn.setText(mLogTimeTaskEntity.getDateLogString());
            mDescriptionEdTx.setText(mLogTimeTaskEntity.getDescription());
            mHours.setText(String.valueOf(mLogTimeTaskEntity.getHours()));
            mNameTaskTxV.setText(mNameTask);
            mIdTaskTxV.setText(String.valueOf(idTask));

        }

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
        }

    }

    private void updateDate() {
        mDateBtn.setText(mLogTimeTaskEntity.getDateLogString());
    }

}
