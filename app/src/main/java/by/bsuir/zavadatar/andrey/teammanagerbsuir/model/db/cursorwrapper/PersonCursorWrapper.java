package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper;

import android.database.Cursor;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.PersonTable;

/**
 * Created by Andrey on 27.11.2016.
 */

public class PersonCursorWrapper extends BaseCustomCursorWrapper<PersonEntity> {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public PersonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public PersonEntity getData(){

        PersonEntity entity = new PersonEntity();

        entity.setIdPerson(getInt(getColumnIndex(PersonTable.Colums.ID_PERSON)));
        entity.setSurname(getString(getColumnIndex(PersonTable.Colums.SURNAME)));
        entity.setName(getString(getColumnIndex(PersonTable.Colums.NAME)));
        entity.setPatronymic(getString(getColumnIndex(PersonTable.Colums.PATRONYMIC)));
        entity.setDateOfBirth(getString(getColumnIndex(PersonTable.Colums.DATE_OF_BIRTH)));
        entity.setSex(getString(getColumnIndex(PersonTable.Colums.SEX)));
        entity.seteMail(getString(getColumnIndex(PersonTable.Colums.E_MAIL)));
        entity.setIdUser(getInt(getColumnIndex(PersonTable.Colums.ID_USER)));
        entity.setIdPost(getInt(getColumnIndex(PersonTable.Colums.ID_POST)));
        entity.setIdDepartment(getInt(getColumnIndex(PersonTable.Colums.ID_DEPARTMENT)));
        entity.setIdCity(getInt(getColumnIndex(PersonTable.Colums.ID_CITY)));
        entity.setMobilePhone(getString(getColumnIndex(PersonTable.Colums.MOBILE_PHONE)));
        entity.setHomePhone(getString(getColumnIndex(PersonTable.Colums.HOME_PHONE)));

        return entity;
    }
}
