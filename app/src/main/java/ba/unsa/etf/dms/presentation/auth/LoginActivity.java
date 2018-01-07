package ba.unsa.etf.dms.presentation.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import ba.unsa.etf.dms.R;
import ba.unsa.etf.dms.data.auth.LoginExecutor;
import ba.unsa.etf.dms.presentation.content.ListType;
import ba.unsa.etf.dms.presentation.content.list.host.WorkspaceActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Hugsby on 12/24/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginExecutor.LoginInterface {

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
                password.requestFocus();
            } else {
                Toast.makeText(getApplicationContext(), "Username must contain letters and/or numbers. ", Toast.LENGTH_LONG).show();
            }
        } else {
            if (isStringInserted(password.getText().toString())) {
                new LoginExecutor(username.getText().toString(), password.getText().toString(), this);
                closeKeyboard();
            }
        }
    }

    private boolean isStringInserted(String string) {
        return !string.isEmpty();
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(this, WorkspaceActivity.class));
        this.finish();
    }

    @Override
    public void onLoginError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
