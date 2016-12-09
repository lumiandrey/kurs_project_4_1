package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.ApplicationHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.UserCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.UserDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.UserEntity;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.UserTable;

/**
 * Created by Andrey on 27.11.2016.
 */

public class UserDaoLite extends AbstractDaoBase<UserEntity> implements UserDao{

    public static final String TAG = UserDaoLite.class.getName();
    public static final String NAME_TABLE = UserTable.NAME;
    public static final String ID_WHERE = UserTable.Colums.ID_USER;

    public UserDaoLite(BaseHelper mDatabase){
        super(mDatabase);
    }

    public UserDaoLite(Context context){
        super(ApplicationHelper.getInstance(context));
    }

    @Override
    public long create(UserEntity userEntity) {

        return super.create(userEntity, NAME_TABLE);
    }

    public UserDaoLite(SQLiteDatabase mDatabase) {
        super(mDatabase);
    }

    @Override
    public UserEntity read(int id) {

        return super.read(NAME_TABLE, ID_WHERE + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public UserEntity read(String login) {

        return super.read(NAME_TABLE, UserTable.Colums.LOGIN + " = ?", new String[]{login});
    }

    @Override
    public UserEntity update(UserEntity userEntity) {

        return super.update(userEntity, NAME_TABLE,
                ID_WHERE + " = ?",
                new String[] {String.valueOf(userEntity.getIdUser())});
    }

    @Override
    public int delete(UserEntity userEntity) {

        return super.delete(NAME_TABLE, ID_WHERE + " = ?",
                new String[] {String.valueOf(userEntity.getIdUser())});
    }

    @Override
    protected ContentValues getContentValuesNotId(UserEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(UserTable.Colums.LOGIN, userEntity.getLogin());
        values.put(UserTable.Colums.PASSWORD, userEntity.getPassword());
        values.put(UserTable.Colums.ID_TYPE_USER, userEntity.getIdTypeUser());

        return values;
    }

    @Override
    protected ContentValues getContentValuesWithId(UserEntity userEntity) {

        ContentValues values = getContentValuesNotId(userEntity);

        values.put(UserTable.Colums.ID_USER, userEntity.getIdUser());

        return values;
    }

    @Override
    protected BaseCustomCursorWrapper<UserEntity> getCursorWrapper(Cursor cursor) {
        return new UserCursorWrapper(cursor);
    }

    @Override
    public List<UserEntity> create(List<UserEntity> entities) {

        return super.create(entities, NAME_TABLE);
    }

    @Override
    public List<UserEntity> reads() {

        return super.reads(NAME_TABLE);
    }

    @Override
    public void deleteAll(){
        super.deleteAll(NAME_TABLE);
    }

}
