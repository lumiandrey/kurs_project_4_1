package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.TypeActivityEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class TypeActivityCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TypeActivityCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TypeActivityEntity getTypeActivity(){

        TypeActivityEntity entity = new TypeActivityEntity();

        entity.setIdTypeActivity(getInt(getColumnIndex(TypeActivityTable.Colums.ID_TYPE_ACTIVITY)));
        entity.setNameActivity(getString(getColumnIndex(TypeActivityTable.Colums.NAME_ACTIVITY)));

        return entity;
    }
}
