package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.CryptoUtils;

/**
 * Created by Andrey on 02.12.2016.
 */

public class UserStorageInit {

    private List<UserEntity> mEntityList = new ArrayList<>();

    public List<UserEntity> getData(List<TypeUserEntity> entities) {

        for(TypeUserEntity entity: entities){

            switch (entity.getAccessLevel()){

                case 1:{
                    mEntityList.add(new UserEntity(1, "admin", CryptoUtils.getHasSHA("admin".getBytes()), entity.getIdType()));
                }
                break;
                case 2:{
                    mEntityList.add(new UserEntity(1, "user", CryptoUtils.getHasSHA("user".getBytes()), entity.getIdType()));
                }
                break;
            }
        }

        return mEntityList;
    }

}
