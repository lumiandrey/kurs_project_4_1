package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.KorpPortalDBSchema.*;

/**
 * Created by Andrey on 27.11.2016.
 */

public class ApplicationBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = ApplicationBaseHelper.class.getName();
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "timebase.db";

    public ApplicationBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + CountryTable.NAME + " (" +
                CountryTable.Colums.ID_COUNTRY +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
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
                CityTable.Colums.ID_COUNTRY + " integer NOT NULL," +
                "FOREIGN KEY (id_country) REFERENCES country(id_country)" +
                ")");

        Log.d(TAG, "create table (city successfully)");

        db.execSQL("CREATE TABLE " + DepartmentTable.NAME + " (" +
                DepartmentTable.Colums.ID_DEPARTMENT +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                DepartmentTable.Colums.NAME_DEPARTMENT + " string"+
                ")");

        Log.d(TAG, "create table (department successfully)");

        db.execSQL("CREATE TABLE " + PostTable.NAME + " (" +
                PostTable.Colums.ID_POST +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                PostTable.Colums.NAME_POST + " string NOT NULL," +
                PostTable.Colums.RATE + " FLOAT"+
                ")");

        Log.d(TAG, "create table (post successfully)");

        db.execSQL("CREATE TABLE " + TypeUserTable.NAME + " (" +
                TypeUserTable.Colums.ID_TYPE +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeUserTable.Colums.NAME + " string NOT NULL," +
                TypeUserTable.Colums.ACCESS_LEVEL + " integer NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_user successfully)");

        db.execSQL("CREATE TABLE " + UserTable.NAME + " (" +
                UserTable.Colums.ID_USER +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                UserTable.Colums.LOGIN + " string NOT NULL," +
                UserTable.Colums.PASSWORD + " string NOT NULL," +
                UserTable.Colums.ID_TYPE_USER + " integer NOT NULL," +
                "FOREIGN KEY (id_type_user) REFERENCES type_user(id_type)"+
                ")");

        Log.d(TAG, "create table (user successfully)");

        db.execSQL("CREATE TABLE " + PersonTable.NAME + " (" +
                PersonTable.Colums.ID_PERSON +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                PersonTable.Colums.SURNAME + " string ," +
                PersonTable.NAME + " string ," +
                PersonTable.Colums.PATRONYMIC + " string," +
                PersonTable.Colums.DATE_OF_BIRTH + " DATE," +
                PersonTable.Colums.SEX + " string," +
                PersonTable.Colums.E_MAIL + " string," +
                PersonTable.Colums.ID_USER + " integer NOT NULL," +
                PersonTable.Colums.ID_POST + " integer NOT NULL," +
                PersonTable.Colums.ID_DEPARTMENT + " integer NOT NULL," +
                PersonTable.Colums.ID_CITY + " integer NOT NULL," +
                PersonTable.Colums.MOBILE_PHONE + " string," +
                PersonTable.Colums.HOME_PHONE + " string," +
                "FOREIGN KEY (id_user) REFERENCES user(id_user)," +
                "FOREIGN KEY (id_post) REFERENCES post(id_post)," +
                "FOREIGN KEY (id_department) REFERENCES department(id_department)," +
                "FOREIGN KEY (id_city) REFERENCES city(id_city)"+
                ")");

        Log.d(TAG, "create table (person successfully)");

        db.execSQL("CREATE TABLE " + TypeTaskTable.NAME + " (" +
                TypeTaskTable.Colums.ID_TYPE_TASK +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeTaskTable.Colums.NAME_TYPE + " string NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_task successfully)");

        db.execSQL("CREATE TABLE " + TaskTable.NAME + " (" +
                TaskTable.Colums.ID_TASK + " integer PRIMARY KEY AUTOINCREMENT," +
                TaskTable.NAME + " string ," +
                TaskTable.Colums.DESCRIPTION + " string," +
                TaskTable.Colums.DATE_BEGIN + " DATE ," +
                TaskTable.Colums.DATE_END + " DATE," +
                TaskTable.Colums.DONE + " integer," +
                TaskTable.Colums.PROGRESS + " integer," +
                TaskTable.Colums.ID_TYPE_TASK + " integer NOT NULL," +
                TaskTable.Colums.ID_PERSON_ADD + " integer NOT NULL," +
                "FOREIGN KEY (id_type_task) REFERENCES type_task(id_type_task)," +
                "FOREIGN KEY (id_person_add) REFERENCES person(id_person)"+
                ")");

        Log.d(TAG, "create table (task successfully)");

        db.execSQL("CREATE TABLE " + HasTaskPersonTable.NAME + " (" +
                HasTaskPersonTable.Colums.ID_HAS_TASK_PERSON +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                HasTaskPersonTable.Colums.ID_TASK + " integer NOT NULL," +
                HasTaskPersonTable.Colums.ID_PERSON + " integer NOT NULL," +
                "FOREIGN KEY (id_person) REFERENCES person(id_person)," +
                "FOREIGN KEY (id_task) REFERENCES task(id_task)"+
                ")");

        Log.d(TAG, "create table (has_task_person successfully)");

        db.execSQL("CREATE TABLE " + TypeActivityTable.NAME + " (" +
                TypeActivityTable.Colums.ID_TYPE_ACTIVITY + " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeActivityTable.Colums.NAME_ACTIVITY + " string NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_activity successfully)");

        db.execSQL("CREATE TABLE " + LogTimeTaskTable.NAME + " (" +
                LogTimeTaskTable.Colums.ID_LOG + " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                LogTimeTaskTable.Colums.DATE_LOG + " DATE NOT NULL," +
                LogTimeTaskTable.Colums.DESCRIPTION + " string ," +
                LogTimeTaskTable.Colums.LINKS_EXT_STOR + " string," +
                LogTimeTaskTable.Colums.ID_TYPE_ACTIVITY + " integer NOT NULL," +
                LogTimeTaskTable.Colums.ID_HAS_TASK_PERSON + " integer NOT NULL," +
                "FOREIGN KEY (id_type_activity) REFERENCES type_activity(id_type_activity)," +
                "FOREIGN KEY (id_has_task_person) REFERENCES has_task_person(id_has_task_person)"+
                ")");

        Log.d(TAG, "create table (log_time_task successfully)");

        Log.d(TAG, "create Base (successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
