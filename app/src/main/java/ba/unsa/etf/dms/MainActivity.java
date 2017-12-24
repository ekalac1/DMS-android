package ba.unsa.etf.dms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isUserLogged()) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    boolean isUserLogged(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return !sharedPref.getString("username", "").isEmpty();
    }
}
