package by.bsuir.zavadatar.andrey.teammanagerbsuir;

import android.content.Context;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.model.db.BaseHelper;

/**
 * Created by Andrey on 01.12.2016.
 */

public class ApplicationBaseHelperTest extends BaseHelper {

    private static final String TAG = ApplicationBaseHelperTest.class.getName();

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "timebaseTest.db";

    private static ApplicationBaseHelperTest mInstance = null;

    private ApplicationBaseHelperTest(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static ApplicationBaseHelperTest getInstance(Context context){
        if(mInstance == null){
            mInstance = new ApplicationBaseHelperTest(context);
        }
        return mInstance;
    }
}
