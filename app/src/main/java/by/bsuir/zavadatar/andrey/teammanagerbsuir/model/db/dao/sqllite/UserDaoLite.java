package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.UserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.UserCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.UserDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.Lookup;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class UserDaoLite extends AbstractDaoBase<UserEntity> implements UserDao{

    public static final String TAG = UserDaoLite.class.getName();
    public static final String MSG_ERROR_READ = "Error read to data base UserDao";


    public UserDaoLite(){
        super();
    }

    @Override
    public UserEntity create(UserEntity userEntity) {

        super.create(userEntity, UserTable.NAME);

        return userEntity;
    }

    @Override
    public UserEntity read(int id) {

        return readUser(UserTable.Colums.ID_USER + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public UserEntity read(String login) {

        return readUser(UserTable.Colums.LOGIN + " = ?", new String[]{login});
    }

    private UserEntity readUser(final String WHERE_CLAUSE, final String[] WHERE_ARG){

        UserEntity result = null;

        try (UserCursorWrapper cursorWrapper =
                     new UserCursorWrapper(

                             queryCrimesWhere(
                                     UserTable.NAME, WHERE_CLAUSE, WHERE_ARG))) {

            if (cursorWrapper.getCount() == 0) {

                result = null;
            } else {

                cursorWrapper.moveToFirst();
                result = cursorWrapper.getUser();
            }
        } catch (Exception e){
            Log.e(TAG, MSG_ERROR_READ, e);
        }

        return result;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {

        return super.update(userEntity, UserTable.NAME,
                UserTable.Colums.ID_USER + " = ?",
                new String[] {String.valueOf(userEntity.getIdUser())});
    }

    @Override
    public int delete(UserEntity userEntity) {

        return super.delete(UserTable.NAME, UserTable.Colums.ID_USER + " = ?",
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


}
