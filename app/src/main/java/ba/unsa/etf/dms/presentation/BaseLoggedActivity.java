package ba.unsa.etf.dms.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hugsby on 12/25/17.
 */

public class BaseLoggedActivity extends AppCompatActivity {

    public final String USERNAME_CONST = "USERNAME"+this.getPackageName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isUserLogged()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    boolean isUserLogged(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return !sharedPref.getString(USERNAME_CONST, "").isEmpty();
    }
}
