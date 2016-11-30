package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.UserEntity;

/**
 * Created by Andrey on 27.11.2016.
 */

public interface UserDao extends BaseDao<UserEntity> {

    UserEntity read(String login);

}
