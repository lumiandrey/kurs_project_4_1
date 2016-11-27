package by.bsuir.zavadatar.andrey.teammanagerbsuir.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andrey on 27.11.2016.
 */

public class TimeApplicationBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = TimeApplicationBaseHelper.class.getName();
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "timebase.db";

    public TimeApplicationBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
