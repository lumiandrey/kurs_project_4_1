package by.bsuir.zavadatar.andrey.teammanagerbsuir.storage;


import android.content.Context;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;

public class TaskStorage {

    private static List<TaskEntity> sTaskEntities = null;

    public static List<TaskEntity> getData(Context context, long taskID){
        sTaskEntities = new TaskDaoLite(context).readsTasksByPerson(taskID);

        return sTaskEntities;
    }
}
