package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.LogTimeTaskTable;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.DateConvert;

import static android.content.ContentValues.TAG;

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
        try {
            entity.setDateLog(DateConvert.getDate(getString(getColumnIndex(LogTimeTaskTable.Colums.DATE_LOG))));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "error parsing date", e);
            entity.setDateLog(null);
        }
        entity.setIdHasTaskPerson(getInt(getColumnIndex(LogTimeTaskTable.Colums.ID_HAS_TASK_PERSON)));
        entity.setDescription(getString(getColumnIndex(LogTimeTaskTable.Colums.DESCRIPTION)));
        entity.setHours(getFloat(getColumnIndex(LogTimeTaskTable.Colums.HOURS)));
        entity.setIdTypeActivity(getInt(getColumnIndex(LogTimeTaskTable.Colums.ID_TYPE_ACTIVITY)));
        entity.setLinksExtStor(getString(getColumnIndex(LogTimeTaskTable.Colums.LINKS_EXT_STOR)));

        return entity;
    }
}
