package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;

/**
 * Created by Andrey on 01.12.2016.
 */

public interface CityDao extends BaseDao<CityEntity> {

    List<CityEntity> getCityByCountry(int idCountry);

}
