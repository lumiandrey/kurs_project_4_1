package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class TaskCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TaskEntity getTask(){

        TaskEntity entity = new TaskEntity();

        entity.setIdTask(getInt(getColumnIndex(TaskTable.Colums.ID_TASK)));
        entity.setName(getString(getColumnIndex(TaskTable.Colums.NAME)));
        entity.setDescription(getString(getColumnIndex(TaskTable.Colums.DESCRIPTION)));
        entity.setDateBegin(getString(getColumnIndex(TaskTable.Colums.DATE_BEGIN)));
        entity.setDateEnd(getString(getColumnIndex(TaskTable.Colums.DATE_END)));
        entity.setDone(getInt(getColumnIndex(TaskTable.Colums.DONE)));
        entity.setIdTypeTask(getInt(getColumnIndex(TaskTable.Colums.ID_TYPE_TASK)));
        entity.setIdPersonAdd(getInt(getColumnIndex(TaskTable.Colums.ID_PERSON_ADD)));

        return entity;
    }
}
