package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.HasTaskPersonEntity;

/**
 * Created by Andrey on 01.12.2016.
 */

public interface HasTaskPersonDao extends BaseDao<HasTaskPersonEntity> {

    int getIDHasByIDPersonIDTask(int personID, int taskID);

}
