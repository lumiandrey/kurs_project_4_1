package by.bsuir.zavadatar.andrey.teammanagerbsuir.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.config.ACRAConfiguration;
import org.acra.config.ConfigurationBuilder;

/**
 * Created by Andrey on 26.11.2016.
 */

public class Runner extends Application {

    private final static String TAG = Runner.class.getName();
    public static final String MAIL_TO = "lumiandreylive@gmail.com";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Create Application");
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        final Integer LOGCAT_LINES = 300;

        // Create an ConfigurationBuilder. It is prepopulated with values specified via annotation.
        // Set any additional value of the builder and then use it to construct an ACRAConfiguration.
        final ACRAConfiguration config = new ConfigurationBuilder(this)
                .setMailTo(MAIL_TO)
                .setCustomReportContent(
                        new ReportField[]{
                                ReportField.APP_VERSION_CODE,
                                ReportField.APP_VERSION_NAME,
                                ReportField.ANDROID_VERSION,
                                ReportField.PHONE_MODEL,
                                ReportField.CUSTOM_DATA,
                                ReportField.STACK_TRACE,
                                ReportField.LOGCAT
                        })
                .setLogcatArguments(new String[]{"-t", Integer.toString(LOGCAT_LINES), "-v", "time"})
                .build();

        // Initialise ACRA
        ACRA.init(this, config);

    }


    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.d(TAG, "Application Terminate");
    }


}
