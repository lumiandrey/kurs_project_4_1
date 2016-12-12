package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import java.util.Locale;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.activity.R;


public class SettingApplicationActivity extends PreferenceActivity {

    PendingIntent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_application);

        ListPreference listPreference = (ListPreference) findPreference("lang");
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                Locale locale = new Locale((String) o);

                Configuration configuration = new Configuration();
                configuration.setLocale(locale);
                getBaseContext().getResources().updateConfiguration(configuration, null);

                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                return true;
            }
        });
        intent = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getIntent()), 0);

    }
}
