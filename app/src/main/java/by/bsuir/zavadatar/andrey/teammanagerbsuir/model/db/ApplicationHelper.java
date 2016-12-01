package by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db;

import android.content.Context;

/**
 * Created by Andrey on 01.12.2016.
 */

public class ApplicationHelper extends BaseHelper {

    private static final String TAG = ApplicationHelper.class.getName();

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "timebase.db";

    private static ApplicationHelper mInstance = null;

    private ApplicationHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static ApplicationHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new ApplicationHelper(context);
        }
        return mInstance;
    }
}
