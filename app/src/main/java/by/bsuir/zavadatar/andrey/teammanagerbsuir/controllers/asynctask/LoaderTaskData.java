package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.TypeShowTaskList;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.utilsview.IShowProgress;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;


public class LoaderTaskData extends AsyncTask<Void, Void, List<TaskEntity>> {

    private final TypeShowTaskList mTypeShowTaskList;
    private final UpdateData<TaskEntity> mUpdateData;
    private final IShowProgress mIShowProgress;
    private final Context mContext;

    public LoaderTaskData(
            @NonNull TypeShowTaskList typeShowTaskList,
            @NonNull UpdateData<TaskEntity> updateData,
            @NonNull IShowProgress showProgress,
            @NonNull Context context) {
        mTypeShowTaskList = typeShowTaskList;
        mUpdateData = updateData;
        mIShowProgress = showProgress;
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mIShowProgress.showProgress(true);
    }

    @Override
    protected List<TaskEntity> doInBackground(Void... params) {


        List<TaskEntity> entities = null;

        int personID = ApplicationSettings.getIdPersonSystem(mContext);
        switch (mTypeShowTaskList){
            case SHOW_All_PERSON_TASK:{
                entities = new TaskDaoLite(mContext).readsTasksByPerson(personID);
            } break;
            case SHOW_DONE_PERSON_TASK:{
                entities = new TaskDaoLite(mContext).readsTasksByPersonDone(personID);
            } break;
            case SHOW_CURRENT_PERSON_TASK:{
                entities = new TaskDaoLite(mContext).readsCurrentTasksByPerson(personID);
            } break;
            case SHOW_ALL_TASK:{
                entities = new TaskDaoLite(mContext).reads();
            } break;
            default:
                entities = new ArrayList<>();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return entities;
    }

    @Override
    protected void onPostExecute(List<TaskEntity> resultData) {
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
