package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.Lookup;

/**
 * Created by Andrey on 29.11.2016.
 */

abstract class AbstractDaoBase<T>{

    public static final String TAG = AbstractDaoBase.class.getName();
    public static final String MSG_ERROR_READ = "Error read to data base UserDao";

    protected abstract ContentValues getContentValuesNotId(T value);
    protected abstract ContentValues getContentValuesWithId(T value);

    private SQLiteDatabase mDatabase;

    protected AbstractDaoBase() {

        mDatabase = Lookup.getInstance().lookup(SQLiteDatabase.class);

    }

    protected T create(T entity, final String NAME_TABLE) {

        ContentValues values = getContentValuesNotId(entity);

        mDatabase.insert(NAME_TABLE, null, values);

        Log.d(TAG, "add entity = " + entity.toString());

        return entity;
    }


    protected T read(int id) {
        return null;
    }


    protected T update(T entity,
                       final String NAME_TABLE,
                       final String WHERE_CLAUSE,
                       final String[] WHERE_ARG) {

        ContentValues values = getContentValuesNotId(entity);

        mDatabase.update(NAME_TABLE, values,
                WHERE_CLAUSE,
                WHERE_ARG);

        Log.d(TAG, "update entity = " + entity.toString());

        return entity;

    }


    protected int delete(final String NAME_TABLE, final String WHERE_CLAUSE, final String[] WHERE_ARG) {

        return mDatabase.delete(NAME_TABLE, WHERE_CLAUSE, WHERE_ARG);
    }

    protected CursorWrapper queryCrimesWhere(final String NAME_TABLE,
                                             final String[] COLUMNS,
                                           final String WHERE_CLAUSE,
                                           final String[] WHERE_ARG,
                                             final String ORDER) {

        Cursor cursor = mDatabase.query(
                NAME_TABLE, //name table
                COLUMNS, // Columns - null выбирает все столбцы
                WHERE_CLAUSE, //where if
                WHERE_ARG,//if args
                null, // groupBy
                null, // having
                ORDER // orderBy
        );

        return new CursorWrapper(cursor);
    }

}
