package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.cursorwrapper.BaseCustomCursorWrapper;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.entity.Entity;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.utils.Lookup;

/**
 * Created by Andrey on 29.11.2016.
 */

abstract class AbstractDaoBase<T extends Entity>{

    private static final String TAG = AbstractDaoBase.class.getName();
    private static final String MSG_ERROR_READ = "Error read to database ";
    private static final String SQL_SELECT_ALL_QUERY = "SELECT * FROM ";

    protected abstract ContentValues getContentValuesNotId(T value);
    protected abstract ContentValues getContentValuesWithId(T value);
    protected abstract BaseCustomCursorWrapper<T> getCursorWrapper(Cursor cursor);

    private SQLiteDatabase mDatabase;

    protected AbstractDaoBase() {

        mDatabase = Lookup.getInstance().lookup(SQLiteDatabase.class);

    }

    final protected T create(T entity, final String NAME_TABLE) {

        ContentValues values = getContentValuesNotId(entity);

        mDatabase.insert(NAME_TABLE, null, values);

        Log.d(TAG, "add entity = " + entity.toString());

        return entity;
    }

    final protected List<T> create(List<T> entities, final String NAME_TABLE){

        for(T entity: entities){
            create(entity, NAME_TABLE);
        }

        return entities;
    }


    final protected Entity read(final String NAME_TABLE,
                           final String WHERE_CLAUSE,
                           final String[] WHERE_ARG) {

        Entity result = null;

        try (BaseCustomCursorWrapper cursorWrapper =
                     getCursorWrapper(queryCrimesWhere(NAME_TABLE, null, WHERE_CLAUSE, WHERE_ARG, null))) {

            if (cursorWrapper.getCount() != 0) {

                cursorWrapper.moveToFirst();
                result = cursorWrapper.getData();
            }
            } catch (Exception e){
            Log.e(TAG, MSG_ERROR_READ, e);
        }

        return result;
    }

    public List<T> reads(final String NAME_TABLE) {

        List<Entity> tList = new ArrayList<>();

        try(BaseCustomCursorWrapper cursorWrapper =
        getCursorWrapper(mDatabase.rawQuery(SQL_SELECT_ALL_QUERY+ NAME_TABLE, null))){

            if(cursorWrapper.moveToFirst()){
                do {
                    // Adding to list
                    tList.add(cursorWrapper.getData());
                } while (cursorWrapper.moveToNext());
            }

        }


        return null;
    }


    final protected T update(T entity,
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


    final protected int delete(final String NAME_TABLE, final String WHERE_CLAUSE, final String[] WHERE_ARG) {

        return mDatabase.delete(NAME_TABLE, WHERE_CLAUSE, WHERE_ARG);
    }

    private CursorWrapper queryCrimesWhere(final String NAME_TABLE,
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
