package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.CountryEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class CountryCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CountryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public CountryEntity getCountry(){

        CountryEntity countryEntity = new CountryEntity();

        countryEntity.setIdCountry(getInt(getColumnIndex(CountryTable.Colums.ID_COUNTRY)));
        countryEntity.setNameCountry(getString(getColumnIndex(CountryTable.Colums.NAME_COUNTRY)));
        countryEntity.setKeyContry(getString(getColumnIndex(CountryTable.Colums.KEY_CONTRY)));
        countryEntity.setKeyPhone(getString(getColumnIndex(CountryTable.Colums.KEY_PHONE)));

        return countryEntity;
    }
}
