package ba.unsa.etf.dms.data.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import ba.unsa.etf.dms.data.RetrofitBuilder;
import ba.unsa.etf.dms.data.Utils;
import ba.unsa.etf.dms.presentation.BaseLoggedActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Hugsby on 12/25/17.
 */

public class LoginExecutor {

    public interface LoginInterface {
       void onLoginSuccess();
       void onLoginError(String errorMessage);
    }

    private LoginInterface loginInterface;

    public LoginExecutor(final String username, String password, final Context context) {
        loginInterface = (LoginInterface) context;

        AuthData authData = new AuthData(username, password);

        RetrofitBuilder.getInstance().login(authData).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loginInterface.onLoginSuccess();
                    saveUserToSharedPreferences(context, username);

                } else {
                    loginInterface.onLoginError(Utils.parseError(response));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                loginInterface.onLoginError("Network not avaliable.");
            }
        });
    }

    private void saveUserToSharedPreferences(Context context, String username) {
        SharedPreferences settings = context.getSharedPreferences(BaseLoggedActivity.USERNAME_CONST, MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString(BaseLoggedActivity.USERNAME_CONST, username);
        editor.apply();
    }

}
