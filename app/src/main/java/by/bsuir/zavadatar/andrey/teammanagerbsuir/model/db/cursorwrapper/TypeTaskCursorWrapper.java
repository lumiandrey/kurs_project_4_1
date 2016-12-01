package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeTaskEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class TypeTaskCursorWrapper extends BaseCustomCursorWrapper<TypeTaskEntity> {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TypeTaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public TypeTaskEntity getData(){

        TypeTaskEntity entity = new TypeTaskEntity();

        entity.setIdTypeTask(getInt(getColumnIndex(TypeTaskTable.Colums.ID_TYPE_TASK)));
        entity.setNameType(getString(getColumnIndex(TypeTaskTable.Colums.NAME_TYPE)));

        return entity;
    }
}
