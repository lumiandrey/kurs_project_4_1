package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.CityCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.CityDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.CityTable;

/**
 * Created by Andrey on 01.12.2016.
 */

public class CityDaoLite extends AbstractDaoBase<CityEntity> implements CityDao {

    public static final String TAG = CityDaoLite.class.getName();
    public static final String NAME_TABLE = CityTable.NAME;
    public static final String ID_WHERE = CityTable.Colums.ID_CITY;

    public CityDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public CityDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    @Override
    public long create(CityEntity userEntity) {

        return super.create(userEntity, NAME_TABLE);
    }

    @Override
    public CityEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public CityEntity update(CityEntity userEntity) {

        return super.update(userEntity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(userEntity.getIdCity())});
    }

    @Override
    public int delete(CityEntity userEntity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(userEntity.getIdCity())});
    }

    @Override
    protected ContentValues getContentValuesNotId(CityEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(CityTable.Colums.CODE_PHONE, userEntity.getCodePhone());
        values.put(CityTable.Colums.NAME, userEntity.getName());
        values.put(CityTable.Colums.ID_COUNTRY, userEntity.getIdCountry());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(CityEntity userEntity) {

        ContentValues values = getContentValuesNotId(userEntity);

        values.put(CityTable.Colums.ID_CITY, userEntity.getIdCity());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<CityEntity> getCursorWrapper(Cursor cursor) {
        return new CityCursorWrapper(cursor);
    }

    @Override
    public List<CityEntity> create(List<CityEntity> entities) {


        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<CityEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public List<CityEntity> getCityByCountry(int idCountry) {
        return super.reads(NAME_TABLE, CityTable.Colums.ID_COUNTRY + " = ?", new String[]{String.valueOf(idCountry)});
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }
}
