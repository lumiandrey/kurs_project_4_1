package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.CountryCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.CountryDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CountryEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.CountryTable;

/**
 * Created by Andrey on 01.12.2016.
 */

public class CountryDaoLite extends AbstractDaoBase<CountryEntity> implements CountryDao {

    public static final String TAG = CountryDaoLite.class.getName();
    public static final String NAME_TABLE = CountryTable.NAME;
    public static final String ID_WHERE = CountryTable.Colums.ID_COUNTRY;

    public CountryDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public CountryDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    public CountryDaoLite(Context context){
        super(ApplicationHelper.getInstance(context));
    }

    @Override
    public long create(CountryEntity userEntity) {

        return super.create(userEntity, NAME_TABLE);
    }

    @Override
    public CountryEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public CountryEntity update(CountryEntity userEntity) {

        return super.update(userEntity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(userEntity.getIdCountry())});
    }

    @Override
    public int delete(CountryEntity userEntity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(userEntity.getIdCountry())});
    }

    @Override
    protected ContentValues getContentValuesNotId(CountryEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(CountryTable.Colums.NAME_COUNTRY, userEntity.getNameCountry());
        values.put(CountryTable.Colums.KEY_CONTRY, userEntity.getKeyContry());
        values.put(CountryTable.Colums.KEY_PHONE, userEntity.getKeyPhone());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(CountryEntity userEntity) {

        ContentValues values = getContentValuesNotId(userEntity);

        values.put(CountryTable.Colums.ID_COUNTRY, userEntity.getIdCountry());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<CountryEntity> getCursorWrapper(Cursor cursor) {
        return new CountryCursorWrapper(cursor);
    }

    @Override
    public List<CountryEntity> create(List<CountryEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<CountryEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
