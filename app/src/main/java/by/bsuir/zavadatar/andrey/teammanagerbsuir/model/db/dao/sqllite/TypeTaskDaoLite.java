package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.TypeTaskCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.TypeTaskDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeTaskEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 02.12.2016.
 */

public class TypeTaskDaoLite extends AbstractDaoBase<TypeTaskEntity> implements TypeTaskDao {

    public static final String TAG = TypeTaskDaoLite.class.getName();
    public static final String NAME_TABLE = TypeTaskTable.NAME;
    public static final String ID_WHERE = TypeTaskTable.Colums.ID_TYPE_TASK;

    public TypeTaskDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public TypeTaskDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    @Override
    public long create(TypeTaskEntity entity) {

        return super.create(entity, NAME_TABLE);
    }

    @Override
    public TypeTaskEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public TypeTaskEntity update(TypeTaskEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdTypeTask())});
    }

    @Override
    public int delete(TypeTaskEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdTypeTask())});
    }

    @Override
    protected ContentValues getContentValuesNotId(TypeTaskEntity entity) {

        ContentValues values = new ContentValues();

        values.put(TypeTaskTable.Colums.NAME_TYPE, entity.getNameType());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(TypeTaskEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(ID_WHERE, entity.getIdTypeTask());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<TypeTaskEntity> getCursorWrapper(Cursor cursor) {

        return new TypeTaskCursorWrapper(cursor);
    }

    @Override
    public List<TypeTaskEntity> create(List<TypeTaskEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<TypeTaskEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
