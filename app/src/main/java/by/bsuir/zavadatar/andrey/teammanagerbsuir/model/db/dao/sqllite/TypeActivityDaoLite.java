package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.TypeActivityCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.TypeActivityDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.TypeActivityEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 02.12.2016.
 */

public class TypeActivityDaoLite extends AbstractDaoBase<TypeActivityEntity> implements TypeActivityDao {

    public static final String TAG = TypeActivityDaoLite.class.getName();
    public static final String NAME_TABLE = TypeActivityTable.NAME;
    public static final String ID_WHERE = TypeActivityTable.Colums.ID_TYPE_ACTIVITY;

    public TypeActivityDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public TypeActivityDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    @Override
    public long create(TypeActivityEntity entity) {

        return super.create(entity, NAME_TABLE);
    }

    @Override
    public TypeActivityEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public TypeActivityEntity update(TypeActivityEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdTypeActivity())});
    }

    @Override
    public int delete(TypeActivityEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdTypeActivity())});
    }

    @Override
    protected ContentValues getContentValuesNotId(TypeActivityEntity entity) {

        ContentValues values = new ContentValues();

        values.put(TypeActivityTable.Colums.NAME_ACTIVITY, entity.getNameActivity());


        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(TypeActivityEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(ID_WHERE, entity.getIdTypeActivity());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<TypeActivityEntity> getCursorWrapper(Cursor cursor) {

        return new TypeActivityCursorWrapper(cursor);
    }

    @Override
    public List<TypeActivityEntity> create(List<TypeActivityEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<TypeActivityEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
