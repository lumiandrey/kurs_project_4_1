package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;

/**
 * Created by Andrey on 29.11.2016.
 */

public interface PersonDao extends BaseDao<PersonEntity> {

    int getPersonIdByUser(int idUser);

}
