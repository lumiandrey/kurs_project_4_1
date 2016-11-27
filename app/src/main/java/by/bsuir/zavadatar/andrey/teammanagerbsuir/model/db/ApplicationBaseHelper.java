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

    private static ApplicationBaseHelper sApplicationBaseHelper = null;

    public ApplicationBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE country (" +
                CountryTable.ID_COUNTRY +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                CountryTable.NAME_COUNTRY + " string ," +
                CountryTable.KEY_CONTRY + " string ," +
                CountryTable.KEY_PHONE +" string " +
                ")");

        Log.d(TAG, "create table (country successfully)");

        db.execSQL("CREATE TABLE city (" +
                CityTable.ID_CITY +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                CityTable.NAME + " string," +
                CityTable.CODE_PHONE + " string," +
                CityTable.ID_COUNTRY + " integer NOT NULL," +
                "FOREIGN KEY (id_country) REFERENCES country(id_country)" +
                ")");

        Log.d(TAG, "create table (city successfully)");

        db.execSQL("CREATE TABLE department (" +
                DepartmentTable.ID_DEPARTMENT +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                DepartmentTable.NAME_DEPARTMENT + " string"+
                ")");

        Log.d(TAG, "create table (department successfully)");

        db.execSQL("CREATE TABLE post (" +
                PostTable.ID_POST +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                PostTable.NAME_POST + " string NOT NULL," +
                PostTable.RATE + " FLOAT"+
                ")");

        Log.d(TAG, "create table (post successfully)");

        db.execSQL("CREATE TABLE type_user (" +
                TypeUserTable.ID_TYPE +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeUserTable.NAME + " string NOT NULL," +
                TypeUserTable.ACCESS_LEVEL + " integer NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_user successfully)");

        db.execSQL("CREATE TABLE user (" +
                UserTable.ID_USER +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                UserTable.LOGIN + " string NOT NULL," +
                UserTable.PASSWORD + " string NOT NULL," +
                UserTable.ID_TYPE_USER + " integer NOT NULL," +
                "FOREIGN KEY (id_type_user) REFERENCES type_user(id_type)"+
                ")");

        Log.d(TAG, "create table (user successfully)");

        db.execSQL("CREATE TABLE person (" +
                PersonTable.ID_PERSON +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                PersonTable.SURNAME + " string ," +
                PersonTable.NAME + " string ," +
                PersonTable.PATRONYMIC + " string," +
                PersonTable.DATE_OF_BIRTH + " DATE," +
                PersonTable.SEX + " string," +
                PersonTable.E_MAIL + " string," +
                PersonTable.ID_USER + " integer NOT NULL," +
                PersonTable.ID_POST + " integer NOT NULL," +
                PersonTable.ID_DEPARTMENT + " integer NOT NULL," +
                PersonTable.ID_CITY + " integer NOT NULL," +
                PersonTable.MOBILE_PHONE + " string," +
                PersonTable.HOME_PHONE + " string," +
                "FOREIGN KEY (id_user) REFERENCES user(id_user)," +
                "FOREIGN KEY (id_post) REFERENCES post(id_post)," +
                "FOREIGN KEY (id_department) REFERENCES department(id_department)," +
                "FOREIGN KEY (id_city) REFERENCES city(id_city)"+
                ")");

        Log.d(TAG, "create table (person successfully)");

        db.execSQL("CREATE TABLE type_task (" +
                TypeTaskTable.ID_TYPE_TASK +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeTaskTable.NAME_TYPE + " string NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_task successfully)");

        db.execSQL("CREATE TABLE task (" +
                TaskTable.ID_TASK + " integer PRIMARY KEY AUTOINCREMENT," +
                TaskTable.NAME + " string ," +
                TaskTable.DESCRIPTION + " string," +
                TaskTable.DATE_BEGIN + " DATE ," +
                TaskTable.DATE_END + " DATE," +
                TaskTable.DONE + " integer," +
                TaskTable.PROGRESS + " integer," +
                TaskTable.ID_TYPE_TASK + " integer NOT NULL," +
                TaskTable.ID_PERSON_ADD + " integer NOT NULL," +
                "FOREIGN KEY (id_type_task) REFERENCES type_task(id_type_task)," +
                "FOREIGN KEY (id_person_add) REFERENCES person(id_person)"+
                ")");

        Log.d(TAG, "create table (task successfully)");

        db.execSQL("CREATE TABLE has_task_person (" +
                HasTaskPersonTable.ID_HAS_TASK_PERSON +
                " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                HasTaskPersonTable.ID_TASK + " integer NOT NULL," +
                HasTaskPersonTable.ID_PERSON + " integer NOT NULL," +
                "FOREIGN KEY (id_person) REFERENCES person(id_person)," +
                "FOREIGN KEY (id_task) REFERENCES task(id_task)"+
                ")");

        Log.d(TAG, "create table (has_task_person successfully)");

        db.execSQL("CREATE TABLE type_activity (" +
                TypeActivityTable.ID_TYPE_ACTIVITY + " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                TypeActivityTable.NAME_ACTIVITY + " string NOT NULL"+
                ")");

        Log.d(TAG, "create table (type_activity successfully)");

        db.execSQL("CREATE TABLE log_time_task (" +
                LogTimeTaskTable.ID_LOG + " integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
                LogTimeTaskTable.DATE_LOG + " DATE NOT NULL," +
                LogTimeTaskTable.DESCRIPTION + " string ," +
                LogTimeTaskTable.LINKS_EXT_STOR + " string," +
                LogTimeTaskTable.ID_TYPE_ACTIVITY + " integer NOT NULL," +
                LogTimeTaskTable.ID_HAS_TASK_PERSON + " integer NOT NULL," +
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
