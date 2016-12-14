package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.CityDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.CountryDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.DepartmentDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PersonDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PostDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CountryEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.DepartmentEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PostEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.Sex;


public class EditPersonFragment extends Fragment {

    private static final String TAG = EditPersonFragment.class.getName();
    private static final int REQUEST_DATE = 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_PERSON = "PERSON";

    private PersonEntity mPersonEntity;
    private List<CountryEntity> mCountryList = new ArrayList<>();
    private List<CityEntity> mCityList = new ArrayList<>();
    private List<PostEntity> mPostEntities = new ArrayList<>();
    private List<DepartmentEntity> mDepartmentEntities = new ArrayList<>();

    //Fields view
    private AutoCompleteTextView mNameTextView;
    private AutoCompleteTextView mSurnameTextView;
    private AutoCompleteTextView mPatronymicTextView;
    private AutoCompleteTextView mEmailTextView;
    private AutoCompleteTextView mMobilePhoneTextView;
    private AutoCompleteTextView mHomePhoneTextView;
    private Spinner mPostSpinner;
    private Spinner mDepartmentSpinner;
    private Button mDateOfBirthBtn;
    private Button mUpdateBtn;
    private Spinner mCountrySpinner;
    private Spinner mCitySpinner;
    private Spinner mSexSpinner;
    private Snackbar mSnackbar;

    private EditPersonFragment(){}

    public static EditPersonFragment newInstance(@NonNull PersonEntity personEntity){

        Bundle args = new Bundle();

        args.putSerializable(ARG_PERSON, personEntity);

        EditPersonFragment fragment = new EditPersonFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null){

            mPersonEntity = (PersonEntity) args.getSerializable(ARG_PERSON);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_person_access_admin, container,
                false);

        initView(view);

        dataBinding();

        return view;
    }

    private void initView(View root){

        mNameTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_name_aut_comp_tv);
        mSurnameTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_surname_aut_comp_tv);
        mPatronymicTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_patronymic_aut_comp_tv);
        mEmailTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_email_aut_comp_tv);
        mUpdateBtn = (Button) root.findViewById(R.id.edit_button);
        mMobilePhoneTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_mobile_phone_aut_comp_tv);
        mHomePhoneTextView = (AutoCompleteTextView) root.findViewById(R.id.room_content_home_phone_aut_comp_tv);
        mPostSpinner = (Spinner) root.findViewById(R.id.room_content_post_text_view);
        mDepartmentSpinner = (Spinner) root.findViewById(R.id.room_content_department_text_view);
        mDateOfBirthBtn = (Button) root.findViewById(R.id.room_content_date_of_birth_btn);
        mCountrySpinner = (Spinner) root.findViewById(R.id.room_content_country_spin);
        mCitySpinner = (Spinner) root.findViewById(R.id.room_content_city_spin);
        mSexSpinner = (Spinner) root.findViewById(R.id.room_content_sex_spin);

    }

    private void dataBinding() {
        mNameTextView.setText(mPersonEntity.getName());

        mSurnameTextView.setText(mPersonEntity.getSurname());

        mPatronymicTextView.setText(mPersonEntity.getPatronymic());

        updateDate();
        mDateOfBirthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mPersonEntity.getDateOfBirth());
                dialog.setTargetFragment(EditPersonFragment.this, REQUEST_DATE);

                dialog.show(manager, DIALOG_DATE);
            }
        });

        ArrayAdapter<Sex> sexArrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, Sex.values());
        mSexSpinner.setAdapter(sexArrayAdapter);
        sexArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPersonEntity.setSex(Sex.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        int i = 0;
        boolean isSelected = false;
        for (; i < Sex.values().length; i++){
            if(mPersonEntity.getSex().equals(Sex.values()[i].name())){
                isSelected = true;
                break;
            }
        }
        int visible;
        if(isSelected) {
            mSexSpinner.setSelection(i);
            visible = View.VISIBLE;
        } else {
            if(mCityList.size() > 0) {
                mSexSpinner.setSelection(0);
                visible = View.VISIBLE;
            } else
                visible = View.GONE;
        }
        mSexSpinner.setVisibility(visible);

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
                    mCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            mPersonEntity.setIdCity(mCityList.get(position).getIdCity());

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    mCitySpinner.setAdapter(adapterCity);

                    int i = 0;
                    boolean isSelected = false;
                    for (; i < mCityList.size(); i++) {
                        if (mPersonEntity.getIdCity() == mCityList.get(i).getIdCity()) {
                            isSelected = true;
                            break;
                        }
                    }

                    int visible;
                    if (isSelected) {
                        mCitySpinner.setSelection(i);
                        visible = View.VISIBLE;
                    } else {
                        if (mCityList.size() > 0) {
                            mCitySpinner.setSelection(0);
                            visible = View.VISIBLE;
                        } else
                            visible = View.GONE;
                    }

                    mCitySpinner.setVisibility(visible);


                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        i = 0;
        isSelected = false;
        for( ; i < mCountryList.size(); i++){
            if(cityEntity.getIdCountry() == mCountryList.get(i).getIdCountry()) {
                isSelected = true;
                break;
            }
        }

        if(isSelected) {
            mCountrySpinner.setSelection(i);
            visible = View.VISIBLE;
        } else {
            if(mCountryList.size() > 0) {
                mCountrySpinner.setSelection(0);
                visible = View.VISIBLE;
            } else
                visible = View.GONE;
        }

        mCountrySpinner.setVisibility(visible);

        mEmailTextView.setText(mPersonEntity.geteMail());

        mMobilePhoneTextView.setText(mPersonEntity.getMobilePhone());

        mHomePhoneTextView.setText(mPersonEntity.getHomePhone());

        mPostEntities = new PostDaoLite(getContext()).reads();

        final List<String> listPost = new ArrayList<>();

        for(PostEntity o: mPostEntities)
            listPost.add(o.getNamePost() + '(' + o.getRate() + ')' );
        ArrayAdapter<String> adapterPost =
                new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item,
                        listPost.toArray(new String[listPost.size()]));
        adapterPost.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPostSpinner.setAdapter(adapterPost);
        mPostSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                mPersonEntity.setIdPost(mPostEntities.get(position).getIdPost());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        i = 0;
        isSelected = false;
        for( ; i < mPostEntities.size(); i++){
            if(mPersonEntity.getIdPost() == mPostEntities.get(i).getIdPost()) {
                isSelected = true;
                break;
            }
        }

        if(isSelected) {
            mPostSpinner.setSelection(i);
            visible = View.VISIBLE;
        } else {
            if(mPostEntities.size() > 0) {
                mPostSpinner.setSelection(0);
                visible = View.VISIBLE;
            } else
                visible = View.GONE;
        }

        mPostSpinner.setVisibility(visible);

        mDepartmentEntities = new DepartmentDaoLite(getContext()).reads();

        final List<String> listDepartment = new ArrayList<>();

        for(DepartmentEntity o: mDepartmentEntities)
            listDepartment.add(o.getNameDepartment());
        ArrayAdapter<String> adapterDepartment =
                new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item,
                        listDepartment.toArray(new String[listDepartment.size()]));
        adapterDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDepartmentSpinner.setAdapter(adapterDepartment);
        mDepartmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                mPersonEntity.setIdDepartment(mDepartmentEntities.get(position).getIdDepartment());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        i = 0;
        isSelected = false;
        for( ; i < mDepartmentEntities.size(); i++){
            if(mPersonEntity.getIdDepartment() == mDepartmentEntities.get(i).getIdDepartment()) {
                isSelected = true;
                break;
            }
        }

        if(isSelected) {
            mDepartmentSpinner.setSelection(i);
            visible = View.VISIBLE;
        } else {
            if(mDepartmentEntities.size() > 0) {
                mDepartmentSpinner.setSelection(0);
                visible = View.VISIBLE;
            } else
                visible = View.GONE;
        }

        mDepartmentSpinner.setVisibility(visible);

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSnackbar = Snackbar.make(v, "Подтвердите редактирование", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Редактировать", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isCorrectData();
                            }
                        });
                mSnackbar.show();

            }
        });
    }

    private void initEntity() {
        mPersonEntity.setName(mNameTextView.getText().toString());
        mPersonEntity.setPatronymic(mPatronymicTextView.getText().toString());
        mPersonEntity.setSurname(mSurnameTextView.getText().toString());
        mPersonEntity.seteMail(mEmailTextView.getText().toString());
        mPersonEntity.setHomePhone(mHomePhoneTextView.getText().toString());
        mPersonEntity.setMobilePhone(mMobilePhoneTextView.getText().toString());
    }

    private void isCorrectData() {

        String surname = mSurnameTextView.getText().toString();
        String name = mNameTextView.getText().toString();
        String patronymic = mPatronymicTextView.getText().toString();
        String email = mEmailTextView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(!TextUtils.isEmpty(surname) && !isNamesValid(surname)){
            mSurnameTextView.setError(getString(R.string.error_field_required));
            focusView = mSurnameTextView;
            cancel = true;
        }

        if(!TextUtils.isEmpty(name) && !isNamesValid(name)){
            mNameTextView.setError(getString(R.string.error_field_required));
            focusView = mNameTextView;
            cancel = true;
        }

        if(!TextUtils.isEmpty(patronymic) && !isNamesValid(patronymic)){
            mPatronymicTextView.setError(getString(R.string.error_field_required));
            focusView = mPatronymicTextView;
            cancel = true;
        }

        if(!TextUtils.isEmpty(email) && !isEmailValid(email)){
            mEmailTextView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailTextView;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
        } else {

            initEntity();

            new PersonDaoLite(getContext()).update(mPersonEntity);

            Toast.makeText(getContext(), R.string.update_log_time_message_successfully, Toast.LENGTH_LONG).show();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isNamesValid(String name) {

        return name.length() > 1;
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

    private void updateDate() {
        mDateOfBirthBtn.setText(mPersonEntity.getDateOfBirthToString());
    }
}
