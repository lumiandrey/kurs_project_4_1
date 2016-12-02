package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;

/**
 * Created by Andrey on 02.12.2016.
 */

public class TypeUserStorageInit {

    private List<TypeUserEntity> mEntityList = new ArrayList<>();

    public List<TypeUserEntity> getData() {

        mEntityList.add(new TypeUserEntity(1, "Admin", 1));
        mEntityList.add(new TypeUserEntity(2, "Worker", 2));

        return mEntityList;
    }
}
