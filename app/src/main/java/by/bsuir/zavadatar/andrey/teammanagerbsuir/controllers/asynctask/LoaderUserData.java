package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.IShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.UserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;


public class LoaderUserData extends AsyncTask<Void, Void, List<UserEntity>> {

    private final UpdateData<UserEntity> mUpdateData;
    private final IShowProgress mIShowProgress;
    private final Context mContext;

    public LoaderUserData(UpdateData<UserEntity> updateData, IShowProgress iShowProgress, Context context) {
        mUpdateData = updateData;
        mIShowProgress = iShowProgress;
        mContext = context;
    }

    @Override
    protected List<UserEntity> doInBackground(Void... params) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return new UserDaoLite(mContext).reads();
    }

    @Override
    protected void onPostExecute(List<UserEntity> resultData) {
        mIShowProgress.showProgress(false);

        if(resultData == null)
            resultData = new ArrayList<>();

        mUpdateData.endLoader(resultData);
    }

    @Override
    protected void onCancelled() {

        mIShowProgress.showProgress(false);
    }

}
