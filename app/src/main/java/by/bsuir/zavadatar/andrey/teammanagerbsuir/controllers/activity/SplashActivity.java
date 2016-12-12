package by.bsuir.zavadatar.andrey.teammanagerbsuir.controllers.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import by.bsuir.zavadatar.andrey.teammanagerbsuir.storage.ApplicationSettings;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(!ApplicationSettings.isAuthorisation(this)) {
            startActivity(LoginActivity.newIntent(this));
        } else {
            startActivity(UserRoomActivity.newIntent(this));
        }
        finish();
    }
}
