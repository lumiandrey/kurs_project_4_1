package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeTaskEntity;

/**
 * Created by Andrey on 02.12.2016.
 */

public class TypeTaskSorageInit {

    private List<TypeTaskEntity> mEntityList = new ArrayList<>();

    public List<TypeTaskEntity> getData() {

        mEntityList.add(new TypeTaskEntity(1, "Building"));
        mEntityList.add(new TypeTaskEntity(2, "Testing"));
        mEntityList.add(new TypeTaskEntity(3, "Education"));
        mEntityList.add(new TypeTaskEntity(4, "Review"));
        mEntityList.add(new TypeTaskEntity(5, "Build architecture"));

        return mEntityList;
    }
}
