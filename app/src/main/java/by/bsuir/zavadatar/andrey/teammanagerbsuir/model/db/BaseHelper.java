package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

abstract public class BaseHelper extends SQLiteOpenHelper {

    private static final String TAG = BaseHelper.class.getName();

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " TEXT";

    protected BaseHelper(Context context,
                         final String DATABASE_NAME,
                         final SQLiteDatabase.CursorFactory cursorFactory,
                         final int VERSION) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    public void close(){
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + CountryTable.NAME + " (" +
                CountryTable.Colums.ID_COUNTRY +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                CountryTable.Colums.NAME_COUNTRY + " string ," +
                CountryTable.Colums.KEY_CONTRY + " string ," +
                CountryTable.Colums.KEY_PHONE +" string " +
                ")");

        Log.d(TAG, "create table (country successfully)");

        db.execSQL("CREATE TABLE " + CityTable.NAME + " (" +
                CityTable.Colums.ID_CITY +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                CityTable.Colums.NAME + " string," +
                CityTable.Colums.CODE_PHONE + " string," +
                CityTable.Colums.ID_COUNTRY + " INTEGER NOT NULL," +
                CityTable.REF_COUNTRY +
                ")");

        Log.d(TAG, "create table (city successfully)");

        db.execSQL("CREATE TABLE " + DepartmentTable.NAME + " (" +
                DepartmentTable.Colums.ID_DEPARTMENT +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                DepartmentTable.Colums.NAME_DEPARTMENT + " string"+
                ")");

        Log.d(TAG, "create table (department successfully)");

        db.execSQL("CREATE TABLE " + PostTable.NAME + " (" +
                PostTable.Colums.ID_POST +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                PostTable.Colums.NAME_POST + " string NOT NULL," +
                PostTable.Colums.RATE + " FLOAT"+
                ")");

        Log.d(TAG, "create table (post successfully)");

        db.execSQL("CREATE TABLE " + TypeUserTable.NAME + " (" +
                TypeUserTable.Colums.ID_TYPE +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeUserTable.Colums.NAME + " string NOT NULL," +
                TypeUserTable.Colums.ACCESS_LEVEL + " INTEGER NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_user successfully)");

        db.execSQL("CREATE TABLE " + UserTable.NAME + " (" +
                UserTable.Colums.ID_USER +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                UserTable.Colums.LOGIN + " string NOT NULL," +
                UserTable.Colums.PASSWORD + " string NOT NULL," +
                UserTable.Colums.ID_TYPE_USER + " INTEGER NOT NULL," +
                UserTable.REF_TYPE_USER +
                ")");

        Log.d(TAG, "create table (user successfully)");

        db.execSQL("CREATE TABLE " + PersonTable.NAME + " (" +
                PersonTable.Colums.ID_PERSON +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                PersonTable.Colums.SURNAME + " string ," +
                PersonTable.NAME + " string ," +
                PersonTable.Colums.PATRONYMIC + " string," +
                PersonTable.Colums.DATE_OF_BIRTH + " DATE," +
                PersonTable.Colums.SEX + " string," +
                PersonTable.Colums.E_MAIL + " string," +
                PersonTable.Colums.ID_USER + " INTEGER NOT NULL," +
                PersonTable.Colums.ID_POST + " INTEGER NOT NULL," +
                PersonTable.Colums.ID_DEPARTMENT + " INTEGER NOT NULL," +
                PersonTable.Colums.ID_CITY + " INTEGER NOT NULL," +
                PersonTable.Colums.MOBILE_PHONE + " string," +
                PersonTable.Colums.HOME_PHONE + " string," +
                PersonTable.REF_USER + ',' +
                PersonTable.REF_POST + ',' +
                PersonTable.REF_DEPARTMENT + ',' +
                PersonTable.REF_CITY +
                ")");

        Log.d(TAG, "create table (person successfully)");

        db.execSQL("CREATE TABLE " + TypeTaskTable.NAME + " (" +
                TypeTaskTable.Colums.ID_TYPE_TASK +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeTaskTable.Colums.NAME_TYPE + " string NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_task successfully)");

        db.execSQL("CREATE TABLE " + TaskTable.NAME + " (" +
                TaskTable.Colums.ID_TASK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TaskTable.NAME + " string ," +
                TaskTable.Colums.DESCRIPTION + " string," +
                TaskTable.Colums.DATE_BEGIN + " DATE ," +
                TaskTable.Colums.DATE_END + " DATE," +
                TaskTable.Colums.DONE + " INTEGER," +
                TaskTable.Colums.PROGRESS + " INTEGER," +
                TaskTable.Colums.ID_TYPE_TASK + " INTEGER NOT NULL," +
                TaskTable.Colums.ID_PERSON_ADD + " INTEGER NOT NULL," +
                TaskTable.REF_TYPE_TASK + ',' +
                TaskTable.REF_PERSON_ADD +
                ")");

        Log.d(TAG, "create table (task successfully)");

        db.execSQL("CREATE TABLE " + HasTaskPersonTable.NAME + " (" +
                HasTaskPersonTable.Colums.ID_HAS_TASK_PERSON +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                HasTaskPersonTable.Colums.ID_TASK + " INTEGER NOT NULL," +
                HasTaskPersonTable.Colums.ID_PERSON + " INTEGER NOT NULL," +
                HasTaskPersonTable.REF_PERSON + ',' +
                HasTaskPersonTable.REF_TASK +
                ")");

        Log.d(TAG, "create table (has_task_person successfully)");

        db.execSQL("CREATE TABLE " + TypeActivityTable.NAME + " (" +
                TypeActivityTable.Colums.ID_TYPE_ACTIVITY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeActivityTable.Colums.NAME_ACTIVITY + " string NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_activity successfully)");

        db.execSQL("CREATE TABLE " + LogTimeTaskTable.NAME + " (" +
                LogTimeTaskTable.Colums.ID_LOG + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                LogTimeTaskTable.Colums.DATE_LOG + " DATE NOT NULL," +
                LogTimeTaskTable.Colums.DESCRIPTION + " string ," +
                LogTimeTaskTable.Colums.LINKS_EXT_STOR + " string," +
                LogTimeTaskTable.Colums.ID_TYPE_ACTIVITY + " INTEGER NOT NULL," +
                LogTimeTaskTable.Colums.ID_HAS_TASK_PERSON + " INTEGER NOT NULL," +
                LogTimeTaskTable.REF_TYPE_ACTIVITY + ',' +
                LogTimeTaskTable.REF_HAS_TASK_PERSON +
                ")");

        Log.d(TAG, "create table (log_time_task successfully)");

        Log.d(TAG, "create Base (successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
