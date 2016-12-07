package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeActivityEntity;

/**
 * Created by Andrey on 02.12.2016.
 */

public class TypeActivityStorageInit {

    private List<TypeActivityEntity> mEntityList = new ArrayList<>();

    public List<TypeActivityEntity> getData() {

        mEntityList.add(new TypeActivityEntity(1, "Developer"));
        mEntityList.add(new TypeActivityEntity(2, "Education"));
        mEntityList.add(new TypeActivityEntity(3, "Testing"));
        mEntityList.add(new TypeActivityEntity(4, "Review"));
        mEntityList.add(new TypeActivityEntity(5, "Management"));
        mEntityList.add(new TypeActivityEntity(6, "Self"));

        return mEntityList;
    }
}
