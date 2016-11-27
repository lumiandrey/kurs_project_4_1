package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.TypeTaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class TypeTaskCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TypeTaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TypeTaskEntity getTypeTask(){

        TypeTaskEntity entity = new TypeTaskEntity();

        entity.setIdTypeTask(getInt(getColumnIndex(TypeTaskTable.Colums.ID_TYPE_TASK)));
        entity.setNameType(getString(getColumnIndex(TypeTaskTable.Colums.NAME_TYPE)));

        return entity;
    }
}
