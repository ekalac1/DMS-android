package ba.unsa.etf.dms.presentation;

import android.os.Bundle;

import ba.unsa.etf.dms.R;

public class MainActivity extends BaseLoggedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  if (!isUserLogged()) {
            startActivity(new Intent(this, LoginActivity.class));
        }*/
    }

    /*boolean isUserLogged(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return !sharedPref.getString("username", "").isEmpty();
    }*/
}
