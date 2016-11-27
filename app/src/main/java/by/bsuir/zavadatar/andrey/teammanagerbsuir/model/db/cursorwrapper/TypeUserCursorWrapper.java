package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.TypeUserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class TypeUserCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TypeUserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TypeUserEntity getTypeUser(){

        TypeUserEntity userEntity = new TypeUserEntity();

        userEntity.setIdType(getInt(getColumnIndex(TypeUserTable.Colums.ID_TYPE)));
        userEntity.setName(getString(getColumnIndex(TypeUserTable.Colums.NAME)));
        userEntity.setAccessLevel(getInt(getColumnIndex(TypeUserTable.Colums.ACCESS_LEVEL)));

        return userEntity;
    }
}
