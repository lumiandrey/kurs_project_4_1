package by.bsuir.zavadatar.andrey.teammanagerbsuir.storage;


import android.content.Context;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeUserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;

public class StorageTypeUser {

    public static List<TypeUserEntity> getTypeUser(Context context){
        return new TypeUserDaoLite(ApplicationHelper.getInstance(context)).reads();
    }
}
