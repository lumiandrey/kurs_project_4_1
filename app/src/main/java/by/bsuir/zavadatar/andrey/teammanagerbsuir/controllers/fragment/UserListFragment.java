package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity.R;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.LoaderUserData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask.UpdateData;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.ShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeUserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.UserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;


public class UserListFragment extends Fragment implements UpdateData<UserEntity> {

    private final static String KEY_ARG_HANDLE = "message_users";

    private List<TypeUserEntity> mTypeUserEntityList;
    private List<String> typeUserListString;
    private RecyclerView mTaskRecyclerView;
    private UserAdapter mAdapter;
    Handler handler;

    private ShowProgress mShowProgress;
    private LoaderUserData mLoaderUserData = null;

    @SuppressLint("ValidFragment")
    private UserListFragment() {
    }

    public static UserListFragment newInstance(){

        UserListFragment fragment = new UserListFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mTypeUserEntityList = new TypeUserDaoLite(getContext()).reads();
        typeUserListString = new ArrayList<>();
        for(TypeUserEntity o: mTypeUserEntityList)
            typeUserListString.add(o.getNameToString());

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String message = bundle.getString(KEY_ARG_HANDLE);
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_list, container,
                false);

        mShowProgress = new ShowProgress(view.findViewById(R.id.progress_view), view.findViewById(R.id.data_view), getContext());

        mTaskRecyclerView = (RecyclerView) view
                .findViewById(R.id.list_recycler_view);

        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));

        mShowProgress = new ShowProgress(view.findViewById(R.id.progress_view), view.findViewById(R.id.data_view), getContext());

        updateUI();

        return view;
    }

    @Override
    public void endLoader(@NonNull List<UserEntity> data) {

        if (mAdapter == null) {

            mAdapter = new UserAdapter(data);
            mTaskRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

        } else {
            mAdapter.setUserEntities(data);
            mAdapter.notifyDataSetChanged();

        }
    }

    private void updateUI() {

        if ((mLoaderUserData != null) && mLoaderUserData.getStatus() != AsyncTask.Status.RUNNING) {
            if (mLoaderUserData.isCancelled()) {
                mLoaderUserData = new LoaderUserData(
                        this,
                        mShowProgress,
                        getContext());

                mLoaderUserData.execute((Void[]) null);
            } else {
                mLoaderUserData.cancel(true);
                mLoaderUserData = new LoaderUserData(
                        this,
                        mShowProgress,
                        getContext());

                mLoaderUserData.execute((Void[]) null);
            }

        } else if(mLoaderUserData != null && mLoaderUserData.getStatus() == AsyncTask.Status.PENDING) {

            mLoaderUserData = new LoaderUserData(
                    this,
                    mShowProgress,
                    getContext());

            mLoaderUserData.execute((Void[]) null);
        } else if ((mLoaderUserData != null) && mLoaderUserData.getStatus() == AsyncTask.Status.FINISHED) {

            mLoaderUserData = new LoaderUserData(
                    this,
                    mShowProgress,
                    getContext());

            mLoaderUserData.execute((Void[]) null);
        } else if (mLoaderUserData == null) {

            mLoaderUserData = new LoaderUserData(
                    this,
                    mShowProgress,
                    getContext());

            mLoaderUserData.execute((Void[]) null);
        }
    }

    private class UserHolder extends RecyclerView.ViewHolder{

        private TextView mLoginTextView;
        private Spinner mTypeUserSpinner;

        private UserEntity mUserEntity;

        public UserHolder(View itemView) {
            super(itemView);

            mLoginTextView = (TextView)
                    itemView.findViewById(R.id.login_item);

            mTypeUserSpinner = (Spinner)
                    itemView.findViewById(R.id.type_user_spinner);

            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_spinner_item,
                            typeUserListString.toArray(new String[typeUserListString.size()]));
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mTypeUserSpinner.setAdapter(arrayAdapter);
            mTypeUserSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mUserEntity.setIdTypeUser(mTypeUserEntityList.get(position).getIdType());

                   new Runnable(){
                       @Override
                       public void run() {

                           Message msg = handler.obtainMessage();
                           Bundle bundle = new Bundle();
                           String message;

                           try {
                               new UserDaoLite(UserListFragment.this.getContext()).update(mUserEntity);
                               message = getString(R.string.update_log_time_message_successfully);
                           } catch (Exception e) {
                               message = getString(R.string.error_correct);
                           }
                           bundle.putString(KEY_ARG_HANDLE, message);
                           msg.setData(bundle);
                           handler.sendMessage(msg);
                       }
                   }.run();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

        public void bindCrime(UserEntity userEntity) {
            mUserEntity = userEntity;

            mLoginTextView.setText(mUserEntity.getLogin());

            int i = 0;
            boolean isSelected = false;
            for(; i < mTypeUserEntityList.size(); i++){
                if(mTypeUserEntityList.get(i).getIdType() == mUserEntity.getIdTypeUser()) {
                    isSelected = true;
                    break;
                }
            }

            if(isSelected) {
                mTypeUserSpinner.setSelection(i);
            } else {
                mTypeUserSpinner.setSelection(0);
            }

            if(ApplicationSettings.sPersonEntity(getContext()).getIdUser() == mUserEntity.getIdUser())
                mTypeUserSpinner.setEnabled(false);
        }

    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {

        private List<UserEntity> mUserEntities;

        public UserAdapter(List<UserEntity> userEntities) {
            mUserEntities = userEntities;
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
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.user_item_list, parent, false);

            return new UserHolder(view);
        }

        /**
         * связывает представление View объекта ViewHolder с объектом модели.
         * @param holder - ViewHolder
         * @param position - позиция в наборе даныных. Позиция используется для нахождения
         *                 правильных данных модели, после чего View обновляется в соответствии с этими данными.
         */
        @Override
        public void onBindViewHolder(UserHolder holder, int position) {

            UserEntity userEntity = mUserEntities.get(position);

            holder.bindCrime(userEntity);
        }

        @Override
        public int getItemCount() {

            return mUserEntities.size();
        }

        public void setUserEntities(List<UserEntity> userEntities) {
            mUserEntities = userEntities;
        }
    }
}
