package by.bsuir.zavadatar.andrey.teammanagerbsuir.storage;


import android.content.Context;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeUserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;

public class StorageTypeUser {

    private static List<TypeUserEntity> mTypeUserEntityList = null;

    public static List<TypeUserEntity> getTypeUser(Context context){
        if(mTypeUserEntityList == null) {
            mTypeUserEntityList = new TypeUserDaoLite(ApplicationHelper.getInstance(context)).reads();
        }
        return mTypeUserEntityList;
    }
}
