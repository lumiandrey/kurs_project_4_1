package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PostEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.PostEnum;

/**
 * Created by Andrey on 02.12.2016.
 */

public class PostStorageInit {

    private List<PostEntity> mEntityList = new ArrayList<>();

    public List<PostEntity> getData() {

        mEntityList.add(new PostEntity(1, "Developer ", (double) 1));
        mEntityList.add(new PostEntity(2, "Developer ", 0.5));
        mEntityList.add(new PostEntity(3, "Developer ", 0.25));
        mEntityList.add(new PostEntity(4, "Software engineer", (double) 1));
        mEntityList.add(new PostEntity(5, "Software engineer", 0.5));
        mEntityList.add(new PostEntity(6, "Software engineer", 0.25));
        mEntityList.add(new PostEntity(7, "Test software developer", (double) 1));
        mEntityList.add(new PostEntity(8, "Test software developer", 0.5));
        mEntityList.add(new PostEntity(9, "Test software developer", 0.25));
        mEntityList.add(new PostEntity(10, "Designer", (double) 1));
        mEntityList.add(new PostEntity(11, "Designer", 0.5));
        mEntityList.add(new PostEntity(12, "Designer", 0.25));
        mEntityList.add(new PostEntity(13, "CEO", (double) 1));
        mEntityList.add(new PostEntity(14, "HR", (double) 1));
        mEntityList.add(new PostEntity(15, PostEnum.NONE.name(), (double) 0));

        return mEntityList;
    }

}
