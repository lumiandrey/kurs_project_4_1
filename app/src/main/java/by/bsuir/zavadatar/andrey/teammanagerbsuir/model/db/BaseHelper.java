package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.CountryDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.UserDao;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.CityDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.CountryDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.DepartmentDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.PostDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeActivityDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeTaskDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.TypeUserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.dao.sqllite.UserDaoLite;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata.CityStorageInit;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata.CountryStorageInit;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata.DepartmentStorageInit;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata.PostStorageInit;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata.TypeActivityStorageInit;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata.TypeTaskSorageInit;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata.TypeUserStorageInit;
import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.startdata.UserStorageInit;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

abstract public class BaseHelper extends SQLiteOpenHelper {

    private static final String TAG = BaseHelper.class.getName();

    protected static Context context;

    protected BaseHelper(Context context,
                         final String DATABASE_NAME,
                         final SQLiteDatabase.CursorFactory cursorFactory,
                         final int VERSION) {

        super(context, DATABASE_NAME, cursorFactory, VERSION);
    }

    public void close(){
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + CountryTable.NAME + " (" +
                CountryTable.Colums.ID_COUNTRY +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                CountryTable.Colums.NAME_COUNTRY + " TEXT ," +
                CountryTable.Colums.KEY_CONTRY + " TEXT ," +
                CountryTable.Colums.KEY_PHONE +" TEXT " +
                ")");

        Log.d(TAG, "create table (country successfully)");

        db.execSQL("CREATE TABLE " + CityTable.NAME + " (" +
                CityTable.Colums.ID_CITY +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                CityTable.Colums.NAME + " TEXT," +
                CityTable.Colums.CODE_PHONE + " TEXT," +
                CityTable.Colums.ID_COUNTRY + " INTEGER NOT NULL," +
                CityTable.REF_COUNTRY +
                ")");

        Log.d(TAG, "create table (city successfully)");

        db.execSQL("CREATE TABLE " + DepartmentTable.NAME + " (" +
                DepartmentTable.Colums.ID_DEPARTMENT +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                DepartmentTable.Colums.NAME_DEPARTMENT + " TEXT"+
                ")");

        Log.d(TAG, "create table (department successfully)");

        db.execSQL("CREATE TABLE " + PostTable.NAME + " (" +
                PostTable.Colums.ID_POST +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                PostTable.Colums.NAME_POST + " TEXT NOT NULL," +
                PostTable.Colums.RATE + " FLOAT"+
                ")");

        Log.d(TAG, "create table (post successfully)");

        db.execSQL("CREATE TABLE " + TypeUserTable.NAME + " (" +
                TypeUserTable.Colums.ID_TYPE +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                TypeUserTable.Colums.NAME + " TEXT NOT NULL," +
                TypeUserTable.Colums.ACCESS_LEVEL + " INTEGER NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_user successfully)");

        db.execSQL("CREATE TABLE " + UserTable.NAME + " (" +
                UserTable.Colums.ID_USER +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                UserTable.Colums.LOGIN + " TEXT NOT NULL," +
                UserTable.Colums.PASSWORD + " TEXT NOT NULL," +
                UserTable.Colums.ID_TYPE_USER + " INTEGER NOT NULL," +
                UserTable.REF_TYPE_USER +
                ")");

        Log.d(TAG, "create table (user successfully)");

        db.execSQL("CREATE TABLE " + PersonTable.NAME + " (" +
                PersonTable.Colums.ID_PERSON +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                PersonTable.Colums.SURNAME + " TEXT ," +
                PersonTable.NAME + " TEXT ," +
                PersonTable.Colums.PATRONYMIC + " TEXT," +
                PersonTable.Colums.DATE_OF_BIRTH + " TEXT," +
                PersonTable.Colums.SEX + " TEXT," +
                PersonTable.Colums.E_MAIL + " TEXT," +
                PersonTable.Colums.ID_USER + " INTEGER NOT NULL," +
                PersonTable.Colums.ID_POST + " INTEGER NOT NULL," +
                PersonTable.Colums.ID_DEPARTMENT + " INTEGER NOT NULL," +
                PersonTable.Colums.ID_CITY + " INTEGER NOT NULL," +
                PersonTable.Colums.MOBILE_PHONE + " TEXT," +
                PersonTable.Colums.HOME_PHONE + " TEXT," +
                PersonTable.REF_USER + ',' +
                PersonTable.REF_POST + ',' +
                PersonTable.REF_DEPARTMENT + ',' +
                PersonTable.REF_CITY +
                ")");

        Log.d(TAG, "create table (person successfully)");

        db.execSQL("CREATE TABLE " + TypeTaskTable.NAME + " (" +
                TypeTaskTable.Colums.ID_TYPE_TASK +
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                TypeTaskTable.Colums.NAME_TYPE + " TEXT NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_task successfully)");

        db.execSQL("CREATE TABLE " + TaskTable.NAME + " (" +
                TaskTable.Colums.ID_TASK + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                TaskTable.NAME + " string ," +
                TaskTable.Colums.DESCRIPTION + " TEXT," +
                TaskTable.Colums.DATE_BEGIN + " TEXT ," +
                TaskTable.Colums.DATE_END + " TEXT," +
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
                " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                HasTaskPersonTable.Colums.ID_TASK + " INTEGER NOT NULL," +
                HasTaskPersonTable.Colums.ID_PERSON + " INTEGER NOT NULL," +
                HasTaskPersonTable.REF_PERSON + ',' +
                HasTaskPersonTable.REF_TASK +
                ")");

        Log.d(TAG, "create table (has_task_person successfully)");

        db.execSQL("CREATE TABLE " + TypeActivityTable.NAME + " (" +
                TypeActivityTable.Colums.ID_TYPE_ACTIVITY + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                TypeActivityTable.Colums.NAME_ACTIVITY + " TEXT NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_activity successfully)");

        db.execSQL("CREATE TABLE " + LogTimeTaskTable.NAME + " (" +
                LogTimeTaskTable.Colums.ID_LOG + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                LogTimeTaskTable.Colums.DATE_LOG + " TEXT NOT NULL," +
                LogTimeTaskTable.Colums.DESCRIPTION + " TEXT ," +
                LogTimeTaskTable.Colums.LINKS_EXT_STOR + " TEXT," +
                LogTimeTaskTable.Colums.ID_TYPE_ACTIVITY + " INTEGER NOT NULL," +
                LogTimeTaskTable.Colums.ID_HAS_TASK_PERSON + " INTEGER NOT NULL," +
                LogTimeTaskTable.REF_TYPE_ACTIVITY + ',' +
                LogTimeTaskTable.REF_HAS_TASK_PERSON +
                ")");

        Log.d(TAG, "create table (log_time_task successfully)");

        Log.d(TAG, "create Base (successfully");

        putDataBase(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void putDataBase(SQLiteDatabase db){
        CountryDaoLite countryDao = new CountryDaoLite(db);
        countryDao.create(new CountryStorageInit().getData());

        CityDaoLite cityDaoLite = new CityDaoLite(db);
        cityDaoLite.create(new CityStorageInit().getData(countryDao.reads()));

        DepartmentDaoLite departmentDaoLite = new DepartmentDaoLite(db);
        departmentDaoLite.create(new DepartmentStorageInit().getData());

        PostDaoLite postDaoLite = new PostDaoLite(db);
        postDaoLite.create(new PostStorageInit().getData());

        TypeActivityDaoLite typeActivityDaoLite = new TypeActivityDaoLite(db);
        typeActivityDaoLite.create(new TypeActivityStorageInit().getData());

        TypeTaskDaoLite typeTaskDaoLite = new TypeTaskDaoLite(db);
        typeTaskDaoLite.create(new TypeTaskSorageInit().getData());

        TypeUserDaoLite typeUserDaoLite = new TypeUserDaoLite(db);
        typeUserDaoLite.create(new TypeUserStorageInit().getData());

        UserDao userDao = new UserDaoLite(db);
        userDao.create(new UserStorageInit().getData(typeUserDaoLite.reads()));
    }
}
