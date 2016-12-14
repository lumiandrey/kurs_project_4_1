package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;

/**
 * Created by Andrey on 01.12.2016.
 */

public interface LogTimeTaskDao extends BaseDao<LogTimeTaskEntity> {

    List<LogTimeTaskEntity> readsByTask(long idTask);
    boolean isCorrectDelete(long personID, LogTimeTaskEntity logTimeTaskEntity);

}
