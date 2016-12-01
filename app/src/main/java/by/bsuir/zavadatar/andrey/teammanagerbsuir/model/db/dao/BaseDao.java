package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

import java.util.List;

/**
 * Created by Andrey on 28.11.2016.
 */

public interface BaseDao <T>{

    long create(T userEntity);
    List<T> create(List<T> entities);
    List<T> reads();
    T read(int id);
    T update(T userEntity);
    int delete(T userEntity);
    void deleteAll();
}
