package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.UserEntity;

/**
 * Created by Andrey on 27.11.2016.
 */

public interface UserDao {

    UserEntity create(UserEntity userEntity);
    UserEntity read(int id);
    UserEntity read(String login);
    UserEntity update(UserEntity userEntity);
    UserEntity delete(UserEntity userEntity);

}
