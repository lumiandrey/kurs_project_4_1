package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;
import android.database.CursorWrapper;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.PersonEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class PersonCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public PersonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public PersonEntity getPerson(){

        PersonEntity entity = new PersonEntity();

        entity.setIdPerson(getInt(getColumnIndex(PersonTable.Colums.ID_PERSON)));
        entity.setSurname(getString(getColumnIndex(PersonTable.Colums.SURNAME)));
        entity.setName(getString(getColumnIndex(PersonTable.Colums.SURNAME)));
        entity.setPatronymic(getString(getColumnIndex(PersonTable.Colums.SURNAME)));
        entity.setDateOfBirth(getString(getColumnIndex(PersonTable.Colums.SURNAME)));
        entity.setSex(getString(getColumnIndex(PersonTable.Colums.SURNAME)));
        entity.seteMail(getString(getColumnIndex(PersonTable.Colums.SURNAME)));
        entity.setIdUser(getInt(getColumnIndex(PersonTable.Colums.ID_USER)));
        entity.setIdPost(getInt(getColumnIndex(PersonTable.Colums.ID_POST)));
        entity.setIdDepartment(getInt(getColumnIndex(PersonTable.Colums.ID_DEPARTMENT)));
        entity.setIdCity(getInt(getColumnIndex(PersonTable.Colums.ID_CITY)));
        entity.setMobilePhone(getString(getColumnIndex(PersonTable.Colums.MOBILE_PHONE)));
        entity.setHomePhone(getString(getColumnIndex(PersonTable.Colums.HOME_PHONE)));

        return entity;
    }
}
