package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.HasTaskPersonEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class HasTaskPersonCursorWrapper extends BaseCustomCursorWrapper<HasTaskPersonEntity> {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public HasTaskPersonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public HasTaskPersonEntity getData(){

        HasTaskPersonEntity entity = new HasTaskPersonEntity();

        entity.setIdHasTaskPerson(getInt(getColumnIndex(HasTaskPersonTable.Colums.ID_HAS_TASK_PERSON)));
        entity.setIdPerson(getInt(getColumnIndex(HasTaskPersonTable.Colums.ID_PERSON)));
        entity.setIdTask(getInt(getColumnIndex(HasTaskPersonTable.Colums.ID_TASK)));

        return entity;
    }

}
