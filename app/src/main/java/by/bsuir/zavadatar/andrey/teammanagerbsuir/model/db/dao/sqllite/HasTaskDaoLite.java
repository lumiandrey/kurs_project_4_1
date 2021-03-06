package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.HasTaskPersonCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.HasTaskPersonDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.HasTaskPersonEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.HasTaskPersonTable.Colums;
import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.HasTaskPersonTable.NAME;

/**
 * Created by Andrey on 02.12.2016.
 */

public class HasTaskDaoLite extends AbstractDaoBase<HasTaskPersonEntity> implements HasTaskPersonDao {

    public static final String TAG = HasTaskDaoLite.class.getName();
    public static final String NAME_TABLE = NAME;
    public static final String ID_WHERE = Colums.ID_HAS_TASK_PERSON;

    public HasTaskDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public HasTaskDaoLite(Context context){
        super(ApplicationHelper.getInstance(context));
    }

    @Override
    public long create(HasTaskPersonEntity entity) {

        return super.create(entity, NAME_TABLE);
    }

    public HasTaskDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    @Override
    public HasTaskPersonEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public HasTaskPersonEntity update(HasTaskPersonEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdHasTaskPerson())});
    }

    @Override
    public int delete(HasTaskPersonEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdHasTaskPerson())});
    }

    @Override
    protected ContentValues getContentValuesNotId(HasTaskPersonEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(Colums.ID_PERSON, userEntity.getIdPerson());
        values.put(Colums.ID_TASK, userEntity.getIdTask());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(HasTaskPersonEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(Colums.ID_HAS_TASK_PERSON, entity.getIdHasTaskPerson());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<HasTaskPersonEntity> getCursorWrapper(Cursor cursor) {
        return new HasTaskPersonCursorWrapper(cursor);
    }

    @Override
    public List<HasTaskPersonEntity> create(List<HasTaskPersonEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<HasTaskPersonEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }

    @Override
    public int getIDHasByIDPersonIDTask(int personID, int taskID) {
        HasTaskPersonEntity hasTaskPersonEntity = super.read(NAME_TABLE,
                Colums.ID_PERSON + " =? AND " + Colums.ID_TASK + " =?",
                new String[]{String.valueOf(personID), String.valueOf(taskID)});
        int result = -1;
        if(hasTaskPersonEntity != null)
            result = hasTaskPersonEntity.getIdHasTaskPerson();
        return result;
    }
}
