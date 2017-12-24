package ba.unsa.etf.dms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hugsby on 12/24/17.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        password.setVisibility(View.GONE);
    }

    @OnClick(R.id.log_in)
    void onLogInClick() {
        if (password.getVisibility() == View.GONE) {
            if (isStringInserted(username.getText().toString())) {
                password.setVisibility(View.VISIBLE);
            }
        }
    }

    boolean isStringInserted(String string) {
        return !string.isEmpty();
    }

}
