package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

/**
 * Created by Andrey on 28.11.2016.
 */

public interface BaseDao <T>{

    T create(T userEntity);
    T read(int id);
    T update(T userEntity);
    int delete(T userEntity);
}
