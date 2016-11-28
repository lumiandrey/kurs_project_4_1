package by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.TaskEntity;

/**
 * Created by Андрей on 09.11.2016.
 */

public class TaskFragment extends Fragment {

    private final static String TAG = TaskFragment.class.getName();

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 10;

    private TaskEntity mCrime;


    /**
     * Мы не заполняем представление фрагмента.
     * @param savedInstanceState - использует объект Bundle для сохранения и загрузки состояния.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        int crimeId = getArguments().getInt(ARG_CRIME_ID);

        //mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

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

        View v = inflater.inflate(R.layout.fragment_task, container, false);

        /*mDateButton = (Button)v.findViewById(R.id.task_date);//TODO-Andrey add layout data task
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDateBegin());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_DATE);

                dialog.show(manager, DIALOG_DATE);
            }
        });

        mTimeButton = (Button) v.findViewById(R.id.task_time);//TODO-Andrey add layout time task
        updateTime();
        mTimeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getDateBegin());
                dialog.setTargetFragment(TaskFragment.this, REQUEST_TIME);

                dialog.show(manager, DIALOG_DATE);
            }

        });*/

        return v;
    }


    @Override
    public void onPause() {
        super.onPause();

        //CrimeLab.get(getActivity()).updateCrime(mCrime);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode){
            case REQUEST_DATE:{
                Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

                mCrime.setDateBegin(date);
                updateDate();
            } break;
            case REQUEST_TIME: {
                Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_DATE);

                mCrime.getDateBegin().setHours(date.getHours());
                mCrime.getDateBegin().setMinutes(date.getMinutes());
                mCrime.getDateBegin().setSeconds(date.getSeconds());
                updateTime();
            } break;

        }
    }

    private void updateDate() {
        //mDateButton.setText(mCrime);
    }

    private void updateTime() {
        //mTimeButton.setText(new SimpleDateFormat("HH mm").format( mCrime.getDate()));
    }

    public void finish(){
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
        //getFragmentManager().popBackStackImmediate();
    }

    public static TaskFragment newInstance(int idTask) {
        Bundle args = new Bundle();

        args.putInt(ARG_CRIME_ID, idTask);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
