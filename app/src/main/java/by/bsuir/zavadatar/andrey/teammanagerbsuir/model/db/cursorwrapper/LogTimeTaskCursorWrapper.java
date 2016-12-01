package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.LogTimeTaskTable;

/**
 * Created by Andrey on 27.11.2016.
 */

public class LogTimeTaskCursorWrapper extends BaseCustomCursorWrapper<LogTimeTaskEntity> {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public LogTimeTaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public LogTimeTaskEntity getData(){

        LogTimeTaskEntity entity = new LogTimeTaskEntity();

        entity.setIdLog(getInt(getColumnIndex(LogTimeTaskTable.Colums.ID_LOG)));
        entity.setDateLog(getString(getColumnIndex(LogTimeTaskTable.Colums.DATE_LOG)));
        entity.setIdHasTaskPerson(getInt(getColumnIndex(LogTimeTaskTable.Colums.ID_HAS_TASK_PERSON)));
        entity.setDiscription(getString(getColumnIndex(LogTimeTaskTable.Colums.DESCRIPTION)));
        entity.setIdTypeActivity(getInt(getColumnIndex(LogTimeTaskTable.Colums.ID_TYPE_ACTIVITY)));
        entity.setLinksExtStor(getString(getColumnIndex(LogTimeTaskTable.Colums.LINKS_EXT_STOR)));

        return entity;
    }
}
