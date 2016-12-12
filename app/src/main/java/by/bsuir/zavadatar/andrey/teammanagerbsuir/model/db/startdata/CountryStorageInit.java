package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CountryEntity;

/**
 * Created by Andrey on 02.12.2016.
 */

public class CountryStorageInit {

    private List<CountryEntity> mEntityList = new ArrayList<>();

    public List<CountryEntity> getData(){

        mEntityList.add(new CountryEntity(1, "Беларусь", "BY", "375"));
        mEntityList.add(new CountryEntity(1, "Россия", "RU", "9"));
        mEntityList.add(new CountryEntity(1, "Украина", "UA", "376"));

        return mEntityList;
    }
}
