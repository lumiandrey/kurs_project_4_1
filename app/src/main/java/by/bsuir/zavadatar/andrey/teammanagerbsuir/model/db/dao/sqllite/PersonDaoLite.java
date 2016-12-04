package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.PersonCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.PersonDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.PersonEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.PersonTable;

/**
 * Created by Andrey on 03.12.2016.
 */

public class PersonDaoLite extends AbstractDaoBase<PersonEntity> implements PersonDao {

    public static final String TAG = PersonDaoLite.class.getName();
    public static final String NAME_TABLE = PersonTable.NAME;
    public static final String ID_WHERE = PersonTable.Colums.ID_PERSON;

    public PersonDaoLite(BaseHelper mDatabase) {
        super(mDatabase);
    }

    public PersonDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    @Override
    public long create(PersonEntity entity) {

        return super.create(entity, NAME_TABLE);
    }

    @Override
    public PersonEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public PersonEntity update(PersonEntity entity) {

        return super.update(entity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdPerson())});
    }

    @Override
    public int delete(PersonEntity entity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(entity.getIdPerson())});
    }

    @Override
    protected ContentValues getContentValuesNotId(PersonEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(PersonTable.Colums.SURNAME, userEntity.getSurname());
        values.put(PersonTable.Colums.NAME, userEntity.getName());
        values.put(PersonTable.Colums.PATRONYMIC, userEntity.getPatronymic());
        values.put(PersonTable.Colums.DATE_OF_BIRTH, userEntity.getDateOfBirthToString());
        values.put(PersonTable.Colums.SEX, userEntity.getSex());
        values.put(PersonTable.Colums.E_MAIL, userEntity.geteMail());
        values.put(PersonTable.Colums.ID_USER, userEntity.getIdUser());
        values.put(PersonTable.Colums.ID_POST, userEntity.getIdPost());
        values.put(PersonTable.Colums.ID_DEPARTMENT, userEntity.getIdDepartment());
        values.put(PersonTable.Colums.ID_CITY, userEntity.getIdCity());
        values.put(PersonTable.Colums.MOBILE_PHONE, userEntity.getMobilePhone());
        values.put(PersonTable.Colums.HOME_PHONE, userEntity.getHomePhone());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(PersonEntity entity) {

        ContentValues values = getContentValuesNotId(entity);

        values.put(ID_WHERE, entity.getIdPerson());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<PersonEntity> getCursorWrapper(Cursor cursor) {
        return new PersonCursorWrapper(cursor);
    }

    @Override
    public List<PersonEntity> create(List<PersonEntity> entities) {

        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<PersonEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }

    @Override
    public int getPersonIdByUser(int idUser) {
        PersonEntity entity = super.read(NAME_TABLE, PersonTable.Colums.ID_USER + " = ?", new String[]{String.valueOf(idUser)});
        return (entity != null ? entity.getIdPerson(): -1);
    }
}
