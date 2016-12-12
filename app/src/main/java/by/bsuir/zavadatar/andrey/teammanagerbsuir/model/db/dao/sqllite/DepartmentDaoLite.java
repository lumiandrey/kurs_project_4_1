package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.DepartmentCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.DepartmentDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.DepartmentEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.CountryTable;
import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.DepartmentTable;

/**
 * Created by Andrey on 02.12.2016.
 */

public class DepartmentDaoLite extends AbstractDaoBase<DepartmentEntity> implements DepartmentDao {

    public static final String TAG = DepartmentDaoLite.class.getName();
    public static final String NAME_TABLE = DepartmentTable.NAME;
    public static final String ID_WHERE = DepartmentTable.Colums.ID_DEPARTMENT;

    public DepartmentDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public DepartmentDaoLite(Context context){
        super(ApplicationHelper.getInstance(context));
    }

    @Override
    public long create(DepartmentEntity userEntity) {

        return super.create(userEntity, NAME_TABLE);
    }

    public DepartmentDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    @Override
    public DepartmentEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public DepartmentEntity update(DepartmentEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdDepartment())});
    }

    @Override
    public int delete(DepartmentEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdDepartment())});
    }

    @Override
    protected ContentValues getContentValuesNotId(DepartmentEntity entity) {

        ContentValues values = new ContentValues();

        values.put(DepartmentTable.Colums.NAME_DEPARTMENT, entity.getNameDepartment());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(DepartmentEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(CountryTable.Colums.ID_COUNTRY, entity.getIdDepartment());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<DepartmentEntity> getCursorWrapper(Cursor cursor) {
        return new DepartmentCursorWrapper(cursor);
    }

    @Override
    public List<DepartmentEntity> create(List<DepartmentEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<DepartmentEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
