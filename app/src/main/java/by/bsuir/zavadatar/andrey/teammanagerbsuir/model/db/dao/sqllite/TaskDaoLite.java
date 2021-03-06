package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.TaskCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.TaskDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TaskEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.HasTaskPersonTable;
import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.TaskTable;

/**
 * Created by Andrey on 02.12.2016.
 */

public class TaskDaoLite extends AbstractDaoBase<TaskEntity> implements TaskDao {

    public static final String TAG = TaskDaoLite.class.getName();
    public static final String NAME_TABLE = TaskTable.NAME;
    public static final String ID_WHERE = TaskTable.Colums.ID_TASK;

    public TaskDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public TaskDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    public TaskDaoLite(Context context){
        super(ApplicationHelper.getInstance(context));
    }

    @Override
    public long create(TaskEntity entity) {

        return super.create(entity, NAME_TABLE);
    }

    @Override
    public TaskEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public TaskEntity update(TaskEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdTask())});
    }

    @Override
    public int delete(TaskEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdTask())});
    }

    @Override
    protected ContentValues getContentValuesNotId(TaskEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(TaskTable.Colums.DATE_BEGIN, userEntity.getDateBeginString());
        values.put(TaskTable.Colums.DATE_END, userEntity.getDateEndString());
        values.put(TaskTable.Colums.DESCRIPTION, userEntity.getDescription());
        values.put(TaskTable.Colums.DONE, userEntity.isDone());
        values.put(TaskTable.Colums.ID_PERSON_ADD, userEntity.getIdPersonAdd());
        values.put(TaskTable.Colums.ID_TYPE_TASK, userEntity.getIdTypeTask());
        values.put(TaskTable.Colums.NAME, userEntity.getName());
        values.put(TaskTable.Colums.PROGRESS, userEntity.getProgress());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(TaskEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(ID_WHERE, entity.getIdTask());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<TaskEntity> getCursorWrapper(Cursor cursor) {

        return new TaskCursorWrapper(cursor);
    }

    @Override
    public List<TaskEntity> create(List<TaskEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<TaskEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public List<TaskEntity> readsTasksByPerson(long personID) {
        return super.reads(
                NAME_TABLE + " INNER JOIN " + HasTaskPersonTable.NAME +
                        " ON " + TaskTable.NAME + '.' + TaskTable.Colums.ID_TASK +
                        " = " + HasTaskPersonTable.NAME + '.' + HasTaskPersonTable.Colums.ID_TASK,
                HasTaskPersonTable.NAME + '.' + HasTaskPersonTable.Colums.ID_PERSON + " = ? ",
                new String[]{String.valueOf(personID)}
        );
    }

    @Override
    public List<TaskEntity> readsTasksByPersonDone(long personID) {
        return super.reads(
                NAME_TABLE + " INNER JOIN " + HasTaskPersonTable.NAME +
                        " ON " + TaskTable.NAME + '.' + TaskTable.Colums.ID_TASK +
                        " = " + HasTaskPersonTable.NAME + '.' + HasTaskPersonTable.Colums.ID_TASK,
                HasTaskPersonTable.NAME + '.' + HasTaskPersonTable.Colums.ID_PERSON + " = ? AND " + TaskTable.Colums.DONE + " <> 0",
                new String[]{String.valueOf(personID)}
        );
    }

    @Override
    public List<TaskEntity> readsCurrentTasksByPerson(long personID) {
        return super.reads(
                NAME_TABLE + " INNER JOIN " + HasTaskPersonTable.NAME +
                        " ON " + TaskTable.NAME + '.' + TaskTable.Colums.ID_TASK +
                        " = " + HasTaskPersonTable.NAME + '.' + HasTaskPersonTable.Colums.ID_TASK,
                HasTaskPersonTable.NAME + '.' + HasTaskPersonTable.Colums.ID_PERSON + " = ? AND " + TaskTable.Colums.DONE + " = 0",
                new String[]{String.valueOf(personID)}
        );
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
