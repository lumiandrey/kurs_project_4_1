package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.LogTimeTaskCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.LogTimeTaskDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.LogTimeTaskEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 02.12.2016.
 */

public class LogTimeDaoLite extends AbstractDaoBase<LogTimeTaskEntity> implements LogTimeTaskDao {

    public static final String TAG = LogTimeDaoLite.class.getName();
    public static final String NAME_TABLE = LogTimeTaskTable.NAME;
    public static final String ID_WHERE = LogTimeTaskTable.Colums.ID_HAS_TASK_PERSON;

    public LogTimeDaoLite() {
        super();
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
        values.put(LogTimeTaskTable.Colums.DATE_LOG, userEntity.getDateLog());
        values.put(LogTimeTaskTable.Colums.DESCRIPTION, userEntity.getDiscription());
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
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
