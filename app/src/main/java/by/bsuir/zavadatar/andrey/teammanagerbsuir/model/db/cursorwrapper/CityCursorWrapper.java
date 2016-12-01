package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.CityEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class CityCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CityCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public CityEntity getCity(){

        CityEntity cityEntity = new CityEntity();

        cityEntity.setIdCity(getInt(getColumnIndex(CityTable.Colums.ID_CITY)));
        cityEntity.setName(getString(getColumnIndex(CityTable.Colums.NAME)));
        cityEntity.setCodePhone(getString(getColumnIndex(CityTable.Colums.CODE_PHONE)));
        cityEntity.setIdCountry(getInt(getColumnIndex(CityTable.Colums.ID_CITY)));

        return cityEntity;
    }
}
