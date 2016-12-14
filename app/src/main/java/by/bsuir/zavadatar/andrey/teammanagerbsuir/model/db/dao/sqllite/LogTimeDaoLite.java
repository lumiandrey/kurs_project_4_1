package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.LogTimeTaskCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.LogTimeTaskDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.HasTaskPersonTable;
import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.LogTimeTaskTable;

/**
 * Created by Andrey on 02.12.2016.
 */

public class LogTimeDaoLite extends AbstractDaoBase<LogTimeTaskEntity> implements LogTimeTaskDao {

    public static final String TAG = LogTimeDaoLite.class.getName();
    public static final String NAME_TABLE = LogTimeTaskTable.NAME;
    public static final String ID_WHERE = LogTimeTaskTable.Colums.ID_LOG;

    public LogTimeDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public LogTimeDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    public LogTimeDaoLite(Context context){
        super(ApplicationHelper.getInstance(context));
    }

    @Override
    public long create(LogTimeTaskEntity entity) {

        return super.create(entity, NAME_TABLE);
    }

    @Override
    public LogTimeTaskEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public LogTimeTaskEntity update(LogTimeTaskEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdHasTaskPerson())});
    }

    @Override
    public int delete(LogTimeTaskEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdHasTaskPerson())});
    }

    @Override
    protected ContentValues getContentValuesNotId(LogTimeTaskEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(LogTimeTaskTable.Colums.ID_HAS_TASK_PERSON, userEntity.getIdHasTaskPerson());
        values.put(LogTimeTaskTable.Colums.DATE_LOG, userEntity.getDateLogString());
        values.put(LogTimeTaskTable.Colums.DESCRIPTION, userEntity.getDescription());
        values.put(LogTimeTaskTable.Colums.HOURS, userEntity.getHours());
        values.put(LogTimeTaskTable.Colums.ID_TYPE_ACTIVITY, userEntity.getIdTypeActivity());
        values.put(LogTimeTaskTable.Colums.LINKS_EXT_STOR, userEntity.getLinksExtStor());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(LogTimeTaskEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(ID_WHERE, entity.getIdLog());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<LogTimeTaskEntity> getCursorWrapper(Cursor cursor) {
        return new LogTimeTaskCursorWrapper(cursor);
    }

    @Override
    public List<LogTimeTaskEntity> create(List<LogTimeTaskEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<LogTimeTaskEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public List<LogTimeTaskEntity> readsByTask(long idTask) {
        return super.reads(
                NAME_TABLE + " INNER JOIN " + HasTaskPersonTable.NAME +
                        " ON " + LogTimeTaskTable.NAME + '.' + LogTimeTaskTable.Colums.ID_HAS_TASK_PERSON +
                        " = " + HasTaskPersonTable.NAME + '.' + HasTaskPersonTable.Colums.ID_HAS_TASK_PERSON,
                HasTaskPersonTable.NAME + '.' + HasTaskPersonTable.Colums.ID_TASK + " = ?",
                new String[]{String.valueOf(idTask)}
        );
    }

    @Override
    public boolean isCorrectDelete(long personID, LogTimeTaskEntity logTimeTaskEntity) {


        return false;
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
