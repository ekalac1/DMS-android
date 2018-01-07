package ba.unsa.etf.dms.presentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ba.unsa.etf.dms.presentation.auth.LoginActivity;

/**
 * Created by Hugsby on 12/25/17.
 */

public class BaseLoggedActivity extends AppCompatActivity {

    public static final String USERNAME_CONST = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isUserLogged()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private boolean isUserLogged(){
        SharedPreferences settings = getSharedPreferences(USERNAME_CONST, MODE_PRIVATE);
        return !settings.getString(USERNAME_CONST, "").isEmpty();
    }
}
