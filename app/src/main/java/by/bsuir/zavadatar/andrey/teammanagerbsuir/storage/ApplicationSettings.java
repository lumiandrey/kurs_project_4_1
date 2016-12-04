package by.bsuir.zavadatar.andrey.teammanagerbsuir.storage;

import android.content.Context;
import android.content.SharedPreferences;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PersonDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;

/**
 * Created by Andrey on 03.12.2016.
 */

public class ApplicationSettings {

    private static final String NAME = "_timeManagerApplicationSetting";
    private static final String ID_USER = "_idUser";
    private static final String ID_PERSON = "_idPerson";
    private static final String LOGIN_USER = "_loginUser";
    private static final String PASSWORD = "_password";

    public static SharedPreferences getStorageSettings(Context context){

        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static void saveUser(Context context, UserEntity userEntity){

        SharedPreferences sharedPreferences = getStorageSettings(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        PersonDaoLite daoLite = new PersonDaoLite(ApplicationHelper.getInstance(context));

        int idPerson = daoLite.getPersonIdByUser(userEntity.getIdUser());
        if(idPerson > 0) {
            editor.putInt(ID_USER, userEntity.getIdUser());
            editor.putInt(ID_PERSON, idPerson);
            editor.putString(LOGIN_USER, userEntity.getLogin());
            editor.putString(PASSWORD, userEntity.getPassword());
        }

        editor.apply();
    }

    public static PersonEntity sPersonEntity(Context context){

        PersonEntity personEntity = null;

        SharedPreferences sharedPreferences = getStorageSettings(context);
        PersonDaoLite daoLite = new PersonDaoLite(ApplicationHelper.getInstance(context));

        int idPerson = sharedPreferences.getInt(ID_PERSON, -1);
        if(idPerson > 0)
            personEntity = daoLite.read(idPerson);

        return personEntity;
    }

    public static boolean isAuthorisation(Context context){

        SharedPreferences sharedPreferences = getStorageSettings(context);

        return sharedPreferences.getString(LOGIN_USER, null) != null &&
                sharedPreferences.getString(PASSWORD, null) != null &&
                sharedPreferences.getInt(ID_PERSON, -1) != -1 &&
                sharedPreferences.getInt(ID_USER, -1) != -1;
    }
}
