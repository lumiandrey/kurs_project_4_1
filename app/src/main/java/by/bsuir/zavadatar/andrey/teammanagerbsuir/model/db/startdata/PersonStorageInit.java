package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.DepartmentEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PostEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.enumiration.Sex;

/**
 * Created by Andrey on 03.12.2016.
 */

public class PersonStorageInit {

    private List<PersonEntity> mEntityList = new ArrayList<>();

    public List<PersonEntity> getData(List<UserEntity> userEntities,
                                      List<PostEntity> postEntities,
                                      List<DepartmentEntity> departmentEntities,
                                      List<CityEntity> cityEntities) {

        for(UserEntity entity: userEntities){

            switch (entity.getLogin()){

                case "admin":{
                    try {
                        mEntityList.add(new PersonEntity(
                                1,
                                "Мартыненко",
                                "Андрей",
                                "Юрьевич",
                                "20.09.1995",
                                Sex.Мужской,
                                "lumiandreylive@gmail.com",
                                entity.getIdUser(),
                                postEntities.get(0).getIdPost(),
                                departmentEntities.get(0).getIdDepartment(),
                                cityEntities.get(0).getIdCity(),
                                "29 373 66 60",
                                "50 31 560"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case "user":{
                    try {
                        mEntityList.add(new PersonEntity(
                                2,
                                "Белевич",
                                "Андриан",
                                "Сергеевич",
                                "08.09.1996",
                                Sex.Мужской,
                                "code@gmail.com",
                                entity.getIdUser(),
                                postEntities.get(1).getIdPost(),
                                departmentEntities.get(1).getIdDepartment(),
                                cityEntities.get(1).getIdCity(),
                                "29 115 02 22",
                                "50 31 253"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }

        return mEntityList;
    }
}
