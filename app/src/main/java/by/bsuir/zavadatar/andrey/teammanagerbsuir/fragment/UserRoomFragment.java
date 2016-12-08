package by.bsuir.zavadatar.andrey.teammanagerbsuir.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.CityDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.CountryDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.DepartmentDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PostDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CountryEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.Sex;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;

/**
 * Created by Andrey on 26.11.2016.
 */

public class UserRoomFragment extends Fragment {

    private static final String TAG = UserRoomFragment.class.getName();
    private static final int REQUEST_DATE = 1;
    private static final String DIALOG_DATE = "DialogDate";

    private Context mContext;
    private PersonEntity mPersonEntity;
    private List<CountryEntity> mCountryList = new ArrayList<>();
    private List<CityEntity> mCityList = new ArrayList<>();

    //Fields view
    private AutoCompleteTextView mNameTextView;
    private AutoCompleteTextView mSurnameTextView;
    private AutoCompleteTextView mPatronymicTextView;
    private AutoCompleteTextView mEmailTextView;
    private AutoCompleteTextView mMobilePhoneTextView;
    private AutoCompleteTextView mHomePhoneTextView;
    private TextView mPostTextView;
    private TextView mDepartmentTextView;
    private Button mDateOfBirthBtn;
    private Spinner mCountrySpinner;
    private Spinner mCitySpinner;
    private Spinner mSexSpinner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "create User Room Fragment");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_user_room, container,
                false);

        mContext = inflater.getContext();

        mPersonEntity = ApplicationSettings.sPersonEntity(getContext());

        initView(view);

        return view;
    }

    private void initView(View root){

        mNameTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_name_aut_comp_tv);
        mNameTextView.setText(mPersonEntity.getName());

        mSurnameTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_surname_aut_comp_tv);
        mSurnameTextView.setText(mPersonEntity.getSurname());

        mPatronymicTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_patronymic_aut_comp_tv);
        mPatronymicTextView.setText(mPersonEntity.getPatronymic());

        mEmailTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_email_aut_comp_tv);
        mEmailTextView.setText(mPersonEntity.geteMail());

        mEmailTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // Здесь намеренно оставлено пустое место
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {

                //TODO-Andrey изменение текста

            }

            @Override
            public void afterTextChanged(Editable c) {
                // И здесь тоже
            }

        });

        mMobilePhoneTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_mobile_phone_aut_comp_tv);
        mMobilePhoneTextView.setText(mPersonEntity.getMobilePhone());

        mMobilePhoneTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // Здесь намеренно оставлено пустое место
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {

                //TODO-Andrey изменение текста

            }

            @Override
            public void afterTextChanged(Editable c) {
                // И здесь тоже
            }

        });

        mHomePhoneTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_home_phone_aut_comp_tv);
        mHomePhoneTextView.setText(mPersonEntity.getHomePhone());

        mHomePhoneTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                // Здесь намеренно оставлено пустое место
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {

                //TODO-Andrey изменение текста

            }

            @Override
            public void afterTextChanged(Editable c) {
                // И здесь тоже
            }

        });

        mPostTextView = (TextView) root.findViewById(R.id.room_content_post_text_view);
        mPostTextView.setText((new PostDaoLite(
                        ApplicationHelper.getInstance(getContext()))
                        .read(mPersonEntity.getIdPost()).getNamePost()));

        mDepartmentTextView = (TextView) root.findViewById(R.id.room_content_department_text_view);
        mDepartmentTextView.setText((new DepartmentDaoLite(
                ApplicationHelper.getInstance(getContext()))
                .read(mPersonEntity.getIdPost()).getNameDepartment()));

        mDateOfBirthBtn = (Button) root.findViewById(R.id.room_content_date_of_birth_btn);
        updateDate();
        mDateOfBirthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mPersonEntity.getDateOfBirth());
                dialog.setTargetFragment(UserRoomFragment.this, REQUEST_DATE);

                dialog.show(manager, DIALOG_DATE);
            }
        });

        mCountrySpinner = (Spinner) root.findViewById(R.id.room_content_country_spin);
        mCitySpinner = (Spinner) root.findViewById(R.id.room_content_city_spin);

        mCountryList = new CountryDaoLite(ApplicationHelper.getInstance(getContext())).reads();
        CityEntity cityEntity = new CityDaoLite(ApplicationHelper.getInstance(getContext())).read(mPersonEntity.getIdCity());
        final List<String> listCountry = new ArrayList<>();
        for(CountryEntity o: mCountryList)
            listCountry.add(o.getNameCountry());
        ArrayAdapter<String> adapterCountry =
                new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item,
                        listCountry.toArray(new String[listCountry.size()]));
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountrySpinner.setAdapter(adapterCountry);
        mCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                try {
                    ArrayAdapter<String> adapterCity;
                    List<String> listCity = new ArrayList<>();

                    mCityList = new CityDaoLite(ApplicationHelper.getInstance(getContext()))
                            .getCityByCountry(mCountryList.get(position).getIdCountry());
                    for (CityEntity o : mCityList)
                        listCity.add(o.getName());
                    adapterCity = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listCity);

                    adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    mCitySpinner.setAdapter(adapterCity);

                    for(int i = 0 ; i < mCityList.size(); i++){
                        if(mPersonEntity.getIdCity() == mCityList.get(i).getIdCity()) {
                            mCitySpinner.setSelection(i);
                            break;
                        }
                    }

                    mCitySpinner.setVisibility(View.VISIBLE);

                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        for(int i = 0; i < mCountryList.size(); i++){
            if(cityEntity.getIdCountry() == mCountryList.get(i).getIdCountry()){
                mCountrySpinner.setSelection(i);
                break;
            }
        }

        mSexSpinner = (Spinner) root.findViewById(R.id.room_content_sex_spin);
        ArrayAdapter<Sex> sexArrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, Sex.values());
        mSexSpinner.setAdapter(sexArrayAdapter);
        sexArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSexSpinner.setSelection(0);

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.user_room, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode){
            case REQUEST_DATE:{
                Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

                mPersonEntity.setDateOfBirth(date);
                updateDate();
            } break;


        }
    }

    private void finish(){
        getActivity().finish();
    }

    private void updateDate() {
        mDateOfBirthBtn.setText(mPersonEntity.getDateOfBirthToString());
    }
}
