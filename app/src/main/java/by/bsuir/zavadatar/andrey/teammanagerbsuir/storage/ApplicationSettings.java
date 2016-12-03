package by.bsuir.zavadatar.andrey.teammanagerbsuir.storage;

import android.content.Context;
import android.content.SharedPreferences;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PersonDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;

/**
 * Created by Andrey on 03.12.2016.
 */

public class ApplicationSettings {

    private static final String NAME = "_timeManagerApplicationSetting";

    public static SharedPreferences getStorageSettings(Context context){

        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static void saveUser(Context context, UserEntity userEntity){

        SharedPreferences sharedPreferences = getStorageSettings(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        PersonDaoLite daoLite = new PersonDaoLite(ApplicationHelper.getInstance(context));

        int idPerson = daoLite.getPersonIdByUser(userEntity.getIdUser());
        if(idPerson >= 0) {
            editor.putInt("_idUser", userEntity.getIdUser());
            editor.putInt("_idPerson", idPerson);
            editor.putString("_loginUser", userEntity.getLogin());
            editor.putString("_password", userEntity.getPassword());
        }

        editor.apply();

    }
}
