package by.bsuir.zavadatar.andrey.teammanagerbsuir.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PersonDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserName;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;


/**
 * Created by Andrey on 03.12.2016.
 */

public class ApplicationSettings {

    private static final String NAME = "_timeManagerApplicationSetting";
    private static final String ID_USER = "_idUser";
    private static final String ID_PERSON = "_idPerson";
    private static final String LOGIN_USER = "_loginUser";
    private static final String ACCESS_LEVEL = "_access_level";
    private static final String PASSWORD = "_password";
    public static final int DEF_ID_USER = -1;
    public static final int DEF_ID_PERSON = -1;

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
            List<TypeUserEntity> typeUserEntityList = StorageTypeUser.getTypeUser(context);
            for(TypeUserEntity o: typeUserEntityList)
                if (o.getIdType() == userEntity.getIdTypeUser())
                    editor.putString(ACCESS_LEVEL, o.getNameToString());
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
                sharedPreferences.getInt(ID_PERSON, DEF_ID_PERSON) != DEF_ID_PERSON &&
                sharedPreferences.getInt(ID_USER, DEF_ID_USER) != DEF_ID_USER;
    }

    public static void logOut(Context context){

        SharedPreferences sharedPreferences = getStorageSettings(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ID_USER, DEF_ID_USER);
        editor.putInt(ID_PERSON, DEF_ID_PERSON);
        editor.putString(LOGIN_USER, null);
        editor.putString(PASSWORD, null);
        editor.putString(ACCESS_LEVEL, null);

        editor.apply();

    }

    public static TypeUserName getAccessLevelName(Context context){
        SharedPreferences sharedPreferences = getStorageSettings(context);

        return TypeUserName.valueOf(sharedPreferences.getString(ACCESS_LEVEL, TypeUserName.Guest.name()));
    }

    public static String getFIO(Context context){
        PersonEntity personEntity = sPersonEntity(context);

        return personEntity.getName() + ' ' + personEntity.getPatronymic();
    }

    public static int getIdPersonSystem(Context context){

        return getStorageSettings(context).getInt(ID_PERSON, DEF_ID_PERSON);
    }
}
