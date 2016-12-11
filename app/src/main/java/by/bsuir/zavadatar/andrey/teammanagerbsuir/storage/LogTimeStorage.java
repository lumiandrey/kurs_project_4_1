package by.bsuir.zavadatar.andrey.teammanagerbsuir.storage;

import android.content.Context;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.LogTimeDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;

public class LogTimeStorage {

    private static List<LogTimeTaskEntity> sLogTimeTaskEntities = null;

    public static List<LogTimeTaskEntity> getData(Context context, long taskID){
        sLogTimeTaskEntities = new LogTimeDaoLite(context).readsByTask(taskID);

        return sLogTimeTaskEntities;
    }
}
