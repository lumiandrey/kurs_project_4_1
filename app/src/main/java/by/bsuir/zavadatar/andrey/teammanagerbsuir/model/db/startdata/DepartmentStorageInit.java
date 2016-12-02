package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.DepartmentEntity;

/**
 * Created by Andrey on 02.12.2016.
 */

public class DepartmentStorageInit {

    private List<DepartmentEntity> mEntityList = new ArrayList<>();

    public List<DepartmentEntity> getData() {

        mEntityList.add(new DepartmentEntity(1, "Отдел разработки"));
        mEntityList.add(new DepartmentEntity(2, "Отдел тестирования"));
        mEntityList.add(new DepartmentEntity(3, "Отдел дизайна"));
        mEntityList.add(new DepartmentEntity(4, "Отдел проектирования"));
        mEntityList.add(new DepartmentEntity(5, "Отдел менеджмента и набора персонала"));

        return mEntityList;
    }
}
