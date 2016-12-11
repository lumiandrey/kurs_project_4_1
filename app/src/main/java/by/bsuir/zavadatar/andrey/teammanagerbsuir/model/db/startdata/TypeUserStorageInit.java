package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.TypeUserName;

/**
 * Created by Andrey on 02.12.2016.
 */

public class TypeUserStorageInit {

    private List<TypeUserEntity> mEntityList = new ArrayList<>();

    public List<TypeUserEntity> getData() {

        mEntityList.add(new TypeUserEntity(1, TypeUserName.Admin, 1));
        mEntityList.add(new TypeUserEntity(2, TypeUserName.Worker, 2));
        mEntityList.add(new TypeUserEntity(3, TypeUserName.Guest, 100_00));

        return mEntityList;
    }
}
