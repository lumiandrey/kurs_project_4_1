package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeActivityEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class TypeActivityCursorWrapper extends BaseCustomCursorWrapper<TypeActivityEntity> {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TypeActivityCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public TypeActivityEntity getData(){

        TypeActivityEntity entity = new TypeActivityEntity();

        entity.setIdTypeActivity(getInt(getColumnIndex(TypeActivityTable.Colums.ID_TYPE_ACTIVITY)));
        entity.setNameActivity(getString(getColumnIndex(TypeActivityTable.Colums.NAME_ACTIVITY)));

        return entity;
    }
}
