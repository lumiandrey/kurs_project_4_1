package by.bsuir.zavadatar.andrey.teammanagerbsuir.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.Log;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.config.ACRAConfiguration;
import org.acra.config.ConfigurationBuilder;

import java.util.Locale;

/**
 * Created by Andrey on 26.11.2016.
 */

public class Runner extends Application {

    private final static String TAG = Runner.class.getName();
    public static final String MAIL_TO = "lumiandreylive@gmail.com";

    private SharedPreferences preferences;
    private Locale locale;
    private String lang;

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        lang = preferences.getString("lang", "default");
        if (lang.equals("default")) {lang=getResources().getConfiguration().locale.getCountry();}
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.d(TAG, "Application Terminate");
    }


}
