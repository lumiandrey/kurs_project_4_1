package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.UserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class UserCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public UserEntity getUser(){

        UserEntity userEntity = new UserEntity();

        userEntity.setIdUser(getInt(getColumnIndex(UserTable.Colums.ID_USER)));
        userEntity.setLogin(getString(getColumnIndex(UserTable.Colums.LOGIN)));
        userEntity.setPassword(getString(getColumnIndex(UserTable.Colums.PASSWORD)));
        userEntity.setIdTypeUser(getInt(getColumnIndex(UserTable.Colums.ID_TYPE_USER)));

        return userEntity;
    }


}
