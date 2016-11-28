package by.bsuir.zavadatar.andrey.teammanagerbsuir.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.TaskEntity;

/**
 * Created by Andrey on 28.11.2016.
 */

public class TaskService {

    public static List<TaskEntity> mTaskEntities = new ArrayList<TaskEntity>(){{
        Date date = new Date();

        add(new TaskEntity(1, "create application", "create application for Android control time",
                date, new Date(date.getTime()*60*60*60*60), 0, 20, 1, 1));

        add(new TaskEntity(2, "create curs project", "create application for Android control time in curs project",
                date, new Date(date.getTime()*60*60*60*30), 0, 20, 1, 1));
    }};

    public static TaskEntity fintTaskBiId(int id){

        TaskEntity entity = null;

        for (TaskEntity o: mTaskEntities){

            if(o.getIdTask().equals(id)) {

                entity = o;

                break;
            }

        }

        return entity;
    }

}
