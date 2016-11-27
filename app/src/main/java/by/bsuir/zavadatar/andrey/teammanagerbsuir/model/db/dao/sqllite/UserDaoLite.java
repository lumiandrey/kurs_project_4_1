package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.entity.UserEntity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.UserCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.UserDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.Lookup;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class UserDaoLite implements UserDao{

    public static final String TAG = UserDaoLite.class.getName();
    SQLiteDatabase mDatabase;

    public UserDaoLite(){

        mDatabase = Lookup.getInstance().lookup(SQLiteDatabase.class);
    }

    @Override
    public UserEntity create(UserEntity userEntity) {

        ContentValues values = getContentValuesNotId(userEntity);

        mDatabase.insert(UserTable.NAME, null, values);

        Log.d(TAG, "add crime" + userEntity.toString());

        return userEntity;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public UserEntity read(int id) {

        UserEntity result = null;

        try (UserCursorWrapper cursorWrapper = queryCrimesWhere(UserTable.Colums.ID_USER + " = ?", new String[]{String.valueOf(id)})) {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            result = cursorWrapper.getUser();
        }

        return result;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public UserEntity read(String login) {
        UserEntity result = null;

        try (UserCursorWrapper cursorWrapper = queryCrimesWhere(UserTable.Colums.LOGIN + " = ?", new String[]{login})) {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            result = cursorWrapper.getUser();
        }

        return result;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {

        ContentValues values = getContentValuesNotId(userEntity);

        mDatabase.update(UserTable.NAME, values,
                UserTable.Colums.ID_USER + " = ?",
                new String[] {String.valueOf(userEntity.getIdUser())});

        Log.d(TAG, "update crime" + userEntity.toString());

        return userEntity;
    }

    @Override
    public UserEntity delete(UserEntity userEntity) {
        return null;
    }

    private ContentValues getContentValuesNotId(UserEntity userEntity) {

        ContentValues values = new ContentValues();

        values.put(UserTable.Colums.LOGIN, userEntity.getLogin());
        values.put(UserTable.Colums.PASSWORD, userEntity.getPassword());
        values.put(UserTable.Colums.ID_TYPE_USER, userEntity.getIdTypeUser());

        return values;
    }

    private ContentValues getContentValuesWithId(UserEntity userEntity) {

        ContentValues values = getContentValuesNotId(userEntity);

        values.put(UserTable.Colums.ID_USER, userEntity.getIdUser());

        return values;
    }

    private UserCursorWrapper queryCrimesWhere(String whereClause, String[] whereArgs) {

        Cursor cursor = mDatabase.query(
                UserTable.NAME, //name table
                null, // Columns - null выбирает все столбцы
                whereClause, //where if
                whereArgs,//if args
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new UserCursorWrapper(cursor);
    }

}
