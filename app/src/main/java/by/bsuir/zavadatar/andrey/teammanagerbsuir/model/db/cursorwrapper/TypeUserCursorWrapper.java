package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.TypeUserTable;

/**
 * Created by Andrey on 27.11.2016.
 */

public class TypeUserCursorWrapper extends BaseCustomCursorWrapper<TypeUserEntity> {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TypeUserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public TypeUserEntity getData(){

        TypeUserEntity userEntity = new TypeUserEntity();

        userEntity.setIdType(getInt(getColumnIndex(TypeUserTable.Colums.ID_TYPE)));
        userEntity.setNameToString(getString(getColumnIndex(TypeUserTable.Colums.NAME)));
        userEntity.setAccessLevel(getInt(getColumnIndex(TypeUserTable.Colums.ACCESS_LEVEL)));

        return userEntity;
    }
}
