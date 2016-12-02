package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CountryEntity;

/**
 * Created by Andrey on 02.12.2016.
 */

public class CityStorageInit {

    private List<CityEntity> mEntityList = new ArrayList<>();

    public List<CityEntity> getData(List<CountryEntity> countryEntities){

        for(CountryEntity countryEntity: countryEntities){

            switch (countryEntity.getKeyContry()){

                case "BY":{
                    mEntityList.add(new CityEntity(1, "Минск", "17", countryEntity.getIdCountry()));
                    mEntityList.add(new CityEntity(1, "Брест", "20", countryEntity.getIdCountry()));
                    mEntityList.add(new CityEntity(1, "Гродно", "14", countryEntity.getIdCountry()));
                }
                break;
                case "RU":{
                    mEntityList.add(new CityEntity(1, "Москва", "15", countryEntity.getIdCountry()));
                    mEntityList.add(new CityEntity(1, "Санкт-Питербург", "34", countryEntity.getIdCountry()));
                    mEntityList.add(new CityEntity(1, "Норильск", "52", countryEntity.getIdCountry()));
                }
                break;
            }
        }

        return mEntityList;
    }
}
