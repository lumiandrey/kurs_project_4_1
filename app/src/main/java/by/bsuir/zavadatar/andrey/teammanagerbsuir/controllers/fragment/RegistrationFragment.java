package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.UserRoomActivity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.ShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.CityDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.CountryDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.DepartmentDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PersonDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PostDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeUserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.UserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CountryEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.DepartmentEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PostEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.DepartEnum;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.PostEnum;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.Sex;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.TypeUserName;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.CryptoUtils;

import static android.Manifest.permission.READ_CONTACTS;


public class RegistrationFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = RegistrationFragment.class.getName();
    private static final int REQUEST_DATE = 123_321_123;
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String DIALOG_DATE = "DialogDate";

    private Context mContext;
    private RegistrationFragment.RegistrationTask mAuthTask = null;

    //Fields model
    private PersonEntity mPersonEntity;
    private UserEntity mUserEntity;
    private List<CountryEntity> mCountryList = new ArrayList<>();
    private List<CityEntity> mCityList = new ArrayList<>();

    //Fields view
    private View mProgressView;
    private View mRegistrationFormView;
    private ShowProgress mShowProgress;

    private AutoCompleteTextView mEmailOrLoginView;
    private EditText mPasswordView;
    private EditText mRepeatPasswordView;
    private AutoCompleteTextView mNameTextView;
    private AutoCompleteTextView mSurnameTextView;
    private AutoCompleteTextView mPatronymicTextView;
    private AutoCompleteTextView mEmailTextView;
    private AutoCompleteTextView mMobilePhoneTextView;
    private AutoCompleteTextView mHomePhoneTextView;
    private TextView mPostTextView;
    private TextView mDepartmentTextView;
    private Button mDateOfBirthBtn;
    private Button mRegisterBtn;
    private Spinner mCountrySpinner;
    private Spinner mCitySpinner;
    private Spinner mSexSpinner;

    @SuppressLint("ValidFragment")
    private RegistrationFragment() {
    }

    public static RegistrationFragment newInstance(){
        return new RegistrationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_registration, container,
                false);

        mContext = inflater.getContext();

        mPersonEntity = new PersonEntity();
        mUserEntity = new UserEntity();

        initView(view);

        mShowProgress = new ShowProgress(mProgressView, mRegistrationFormView, getContext());

        mShowProgress.showProgress(true);

        final List<TypeUserEntity> typeUserEntities = new TypeUserDaoLite(getContext()).reads();
        for(TypeUserEntity o: typeUserEntities){
            if(o.getNameToString().equals(TypeUserName.Guest.name())) {
                mUserEntity.setIdTypeUser(o.getIdType());
            }
        }

        final List<PostEntity> typePostEntities = new PostDaoLite(getContext()).reads();
        for(PostEntity o: typePostEntities){
            if(o.getNamePost().equals(PostEnum.NONE.name())) {
                mPersonEntity.setIdPost(o.getIdPost());
            }
        }

        final List<DepartmentEntity> departmentEntities = new DepartmentDaoLite(getContext()).reads();
        for(DepartmentEntity o: departmentEntities){
            if(o.getNameDepartment().equals(DepartEnum.NONE.name())) {
                mPersonEntity.setIdDepartment(o.getIdDepartment());
            }
        }

        mShowProgress.showProgress(false);

        dataBinding();

        return view;
    }

    private void initView(View root) {

        mProgressView = root.findViewById(R.id.registration_progress);
        mRegistrationFormView = root.findViewById(R.id.activity_registration);

        mEmailOrLoginView = (AutoCompleteTextView) root.findViewById(R.id.email_or_login);
        mPasswordView = (EditText) root.findViewById(R.id.password);
        mRepeatPasswordView = (EditText) root.findViewById(R.id.repeat_password);
        mNameTextView = (AutoCompleteTextView) root.findViewById(R.id.name_aut_comp_tv);
        mSurnameTextView = (AutoCompleteTextView) root.findViewById(R.id.surname_aut_comp_tv);
        mPatronymicTextView = (AutoCompleteTextView) root.findViewById(R.id.patronymic_aut_comp_tv);
        mDateOfBirthBtn = (Button) root.findViewById(R.id.date_of_birth_btn);
        mSexSpinner = (Spinner) root.findViewById(R.id.room_content_sex_spin);
        mCountrySpinner = (Spinner) root.findViewById(R.id.room_content_country_spin);
        mCitySpinner = (Spinner) root.findViewById(R.id.room_content_city_spin);
        mEmailTextView = (AutoCompleteTextView) root.findViewById(R.id.email_aut_comp_tv);
        mMobilePhoneTextView = (AutoCompleteTextView) root.findViewById(R.id.mobile_phone_aut_comp_tv);
        mHomePhoneTextView = (AutoCompleteTextView) root.findViewById(R.id.home_phone_aut_comp_tv);
        mPostTextView = (TextView) root.findViewById(R.id.post_text_view);
        mDepartmentTextView = (TextView) root.findViewById(R.id.department_text_view);
        mRegisterBtn = (Button) root.findViewById(R.id.registration_button);
    }

    private void dataBinding(){

        populateAutoComplete();

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });

        mRepeatPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });

        mNameTextView.setText(mPersonEntity.getName());

        mSurnameTextView.setText(mPersonEntity.getSurname());

        mPatronymicTextView.setText(mPersonEntity.getPatronymic());

        updateDate();
        mDateOfBirthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mPersonEntity.getDateOfBirth());
                dialog.setTargetFragment(RegistrationFragment.this, REQUEST_DATE);

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
                    mCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            mPersonEntity.setIdCity(mCityList.get(position).getIdCity());

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    int i = 0;
                    boolean isSelected = false;
                    for( ; i < mCityList.size(); i++){
                        if(mPersonEntity.getIdCity() == mCityList.get(i).getIdCity()) {
                            isSelected = true;
                            break;
                        }
                    }

                    int visible;
                    if(isSelected) {
                        mCitySpinner.setSelection(i);
                        visible = View.VISIBLE;
                    } else {
                        if(mCityList.size() > 0) {
                            mCitySpinner.setSelection(0);
                            visible = View.VISIBLE;
                        } else
                            visible = View.GONE;
                    }

                    mCitySpinner.setVisibility(visible);

                } catch (Throwable e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error spinner", e);
                    Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mCountrySpinner.setSelection(0);

        mEmailTextView.setText(mPersonEntity.geteMail());

        mMobilePhoneTextView.setText(mPersonEntity.getMobilePhone());

        mHomePhoneTextView.setText(mPersonEntity.getHomePhone());

        mPostTextView.setText(PostEnum.NONE.name());

        mDepartmentTextView.setText(DepartEnum.NONE.name());

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               registration();

            }
        });
    }

    private void initEntity() {

        mUserEntity.setLogin(mEmailOrLoginView.getText().toString());
        mUserEntity.setPassword(CryptoUtils.getHasSHA(mPasswordView.getText().toString().getBytes()));

        mPersonEntity.setName(mNameTextView.getText().toString());
        mPersonEntity.setPatronymic(mPatronymicTextView.getText().toString());
        mPersonEntity.setSurname(mSurnameTextView.getText().toString());
        mPersonEntity.seteMail(mEmailTextView.getText().toString());
        mPersonEntity.setHomePhone(mHomePhoneTextView.getText().toString());
        mPersonEntity.setMobilePhone(mMobilePhoneTextView.getText().toString());
    }

    private void registration() {

        mEmailOrLoginView.setError(null);
        mPasswordView.setError(null);
        mRepeatPasswordView.setError(null);
        mSurnameTextView.setError(null);
        mNameTextView.setError(null);
        mPatronymicTextView.setError(null);
        mEmailTextView.setError(null);

        String loginOfMail = mEmailOrLoginView.getText().toString();
        String password = mPasswordView.getText().toString();
        String repeatPassword = mRepeatPasswordView.getText().toString();
        String surname = mSurnameTextView.getText().toString();
        String name = mNameTextView.getText().toString();
        String patronymic = mPatronymicTextView.getText().toString();
        String email = mEmailTextView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(loginOfMail.length() < 3){
            mEmailOrLoginView.setError(getString(R.string.error_field_required));
            focusView = mEmailOrLoginView;
            cancel = true;
        }

        boolean validPassword = !TextUtils.isEmpty(password) && isPasswordValid(password);

        if(!validPassword ){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        boolean validRepeatPassword = !TextUtils.isEmpty(repeatPassword) && isPasswordValid(repeatPassword);

        if(!validRepeatPassword){
            mRepeatPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mRepeatPasswordView;
            cancel = true;
        }

        if(validPassword && validRepeatPassword && !password.equals(repeatPassword)){
            mRepeatPasswordView.setError(getString(R.string.error_math_password));
            mPasswordView.setError(getString(R.string.error_math_password));
            focusView = mRepeatPasswordView;
            cancel = true;
        }

        if(surname.length() < 3  && !isNamesValid(surname)){
            mSurnameTextView.setError(getString(R.string.error_field_required));
            focusView = mSurnameTextView;
            cancel = true;
        }

        if(name.length() < 3  && !isNamesValid(name)){
            mNameTextView.setError(getString(R.string.error_field_required));
            focusView = mNameTextView;
            cancel = true;
        }

        if(patronymic.length() < 3 && !isNamesValid(patronymic)){
            mPatronymicTextView.setError(getString(R.string.error_field_required));
            focusView = mPatronymicTextView;
            cancel = true;
        }

        if(email.length() < 3 && !isEmailValid(email)){
            mEmailTextView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailTextView;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
        } else {

            initEntity();

            mShowProgress.showProgress(true);
            mAuthTask = new RegistrationTask(mUserEntity, mPersonEntity);
            mAuthTask.execute((Void) null);
        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 3;
    }

    private boolean isNamesValid(String name) {

        return name.length() > 1;
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (mContext.checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailOrLoginView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    /**
     * Show email account device user.
     *
     * Retrieve data rows for the device user's 'profile' contact.
     * Select only email addresses.
     * Show primary email addresses first. Note that there won't be
     * a primary email address if the user hasn't specified one.
     * @param i
     * @param bundle
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(mContext,

                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), RegistrationFragment.ProfileQuery.PROJECTION,

                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(RegistrationFragment.ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailOrLoginView.setAdapter(adapter);
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

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
    }

    private void updateDate() {
        mDateOfBirthBtn.setText(mPersonEntity.getDateOfBirthToString());
    }

    private class RegistrationTask extends AsyncTask<Void, Void, Integer> {

        private final int CODE_ERROR_ADD_USER = 111_222_333;
        private final int CODE_ERROR_ADD_PERSON = 111_222_444;
        private final int CODE_ERROR_NOT_UNIQUE_LOGIN = 111_333_000;
        private final int CODE_SUCCESSFULLY = 111_111_111;
        private final int CODE_EXCEPTION = 999_999_999;

        private final UserEntity mUserEntity;
        private final PersonEntity mPersonEntity;

        private RegistrationTask(UserEntity userEntity, PersonEntity personEntity) {
            mUserEntity = userEntity;
            mPersonEntity = personEntity;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            Integer resultCode;

            try {
                Thread.sleep(2000);

                UserDaoLite userDaoLite = new UserDaoLite(getContext());

                if(userDaoLite.read(mUserEntity.getLogin()) == null ) {

                    long idUser = userDaoLite.create(mUserEntity);
                    if (idUser > 0) {

                        mUserEntity.setIdUser((int) idUser);
                        mPersonEntity.setIdUser((int) idUser);

                        long idPerson = new PersonDaoLite(getContext()).create(mPersonEntity);

                        if(idPerson > 0) {
                            mPersonEntity.setIdPerson((int) idPerson);
                            resultCode = CODE_SUCCESSFULLY;

                        } else
                            resultCode = CODE_ERROR_ADD_PERSON;

                    } else {

                        resultCode = CODE_ERROR_ADD_USER;
                    }

                } else {
                    resultCode = CODE_ERROR_NOT_UNIQUE_LOGIN;
                }

                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "error task! " + e.getMessage(), e);
                resultCode = CODE_EXCEPTION;
            }

            return resultCode;
        }

        @Override
        protected void onPostExecute(Integer resultCode) {
            mAuthTask = null;
            mShowProgress.showProgress(false);

            switch (resultCode){
                case CODE_SUCCESSFULLY:{

                    ApplicationSettings.saveUser(getActivity().getApplicationContext(), mUserEntity);
                    gotoRoomPerson();

                } break;
                case CODE_ERROR_ADD_USER:{

                    Toast.makeText(getContext(), getString(R.string.error_add_user), Toast.LENGTH_LONG).show();
                } break;
                case CODE_ERROR_ADD_PERSON:{

                    Toast.makeText(getContext(), getString(R.string.error_add_person), Toast.LENGTH_LONG).show();
                } break;
                case CODE_ERROR_NOT_UNIQUE_LOGIN:{
                    mEmailOrLoginView.setError(getString(R.string.error_not_unique_login));
                    mEmailOrLoginView.requestFocus();
                } break;
                case CODE_EXCEPTION:{

                    Toast.makeText(getContext(), getString(R.string.error_exception_registration), Toast.LENGTH_LONG).show();
                } break;
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            mShowProgress.showProgress(false);
        }
    }

    private void gotoRoomPerson() {
        getActivity().finish();
        startActivity(UserRoomActivity.newIntent(getActivity()));
    }
}
