package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;

/**
 * Created by Andrey on 01.12.2016.
 */

public interface TaskDao extends BaseDao<TaskEntity> {

    List<TaskEntity> readsTasksByPerson(long personID);
    List<TaskEntity> readsTasksByPersonDone(long personID);

}
