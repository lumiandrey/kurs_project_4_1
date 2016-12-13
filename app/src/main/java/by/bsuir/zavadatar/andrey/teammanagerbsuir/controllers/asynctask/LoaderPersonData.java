package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.IShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PersonDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;


public class LoaderPersonData extends AsyncTask<Void, Void, List<PersonEntity>> {

    private final UpdateData<PersonEntity> mUpdateData;
    private final IShowProgress mIShowProgress;
    private final Context mContext;

    public LoaderPersonData(UpdateData<PersonEntity> updateData, IShowProgress iShowProgress, Context context) {
        mUpdateData = updateData;
        mIShowProgress = iShowProgress;
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mIShowProgress.showProgress(true);
    }

    @Override
    protected List<PersonEntity> doInBackground(Void... params) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return new PersonDaoLite(mContext).reads();
    }

    @Override
    protected void onPostExecute(List<PersonEntity> resultData) {
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
