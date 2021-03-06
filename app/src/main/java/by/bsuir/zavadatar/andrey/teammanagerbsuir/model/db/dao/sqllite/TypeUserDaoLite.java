package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.TypeUserCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.TypeUserDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeUserEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.TypeUserTable;

/**
 * Created by Andrey on 02.12.2016.
 */

public class TypeUserDaoLite extends AbstractDaoBase<TypeUserEntity> implements TypeUserDao {

    public static final String TAG = TypeUserDaoLite.class.getName();
    public static final String NAME_TABLE = TypeUserTable.NAME;
    public static final String ID_WHERE = TypeUserTable.Colums.ID_TYPE;

    public TypeUserDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public TypeUserDaoLite(Context context){
        super(ApplicationHelper.getInstance(context));
    }

    @Override
    public long create(TypeUserEntity entity) {

        return super.create(entity, NAME_TABLE);
    }

    public TypeUserDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    @Override
    public TypeUserEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public TypeUserEntity update(TypeUserEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdType())});
    }

    @Override
    public int delete(TypeUserEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdType())});
    }

    @Override
    protected ContentValues getContentValuesNotId(TypeUserEntity entity) {

        ContentValues values = new ContentValues();

        values.put(TypeUserTable.Colums.NAME, entity.getNameToString());
        values.put(TypeUserTable.Colums.ACCESS_LEVEL, entity.getAccessLevel());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(TypeUserEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(ID_WHERE, entity.getIdType());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<TypeUserEntity> getCursorWrapper(Cursor cursor) {

        return new TypeUserCursorWrapper(cursor);
    }

    @Override
    public List<TypeUserEntity> create(List<TypeUserEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<TypeUserEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
