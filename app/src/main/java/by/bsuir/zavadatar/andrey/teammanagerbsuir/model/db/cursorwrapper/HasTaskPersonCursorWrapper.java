package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.HasTaskPersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class HasTaskPersonCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public HasTaskPersonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public HasTaskPersonEntity getHasTaskPerson(){

        HasTaskPersonEntity entity = new HasTaskPersonEntity();

        entity.setIdHasTaskPerson(getInt(getColumnIndex(HasTaskPersonTable.Colums.ID_HAS_TASK_PERSON)));
        entity.setIdPerson(getInt(getColumnIndex(HasTaskPersonTable.Colums.ID_PERSON)));
        entity.setIdTask(getInt(getColumnIndex(HasTaskPersonTable.Colums.ID_TASK)));

        return entity;
    }

}
