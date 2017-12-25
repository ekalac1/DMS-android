package ba.unsa.etf.dms.data;

import android.content.Context;
import android.support.annotation.NonNull;

import ba.unsa.etf.dms.data.AuthData;
import ba.unsa.etf.dms.data.RetrofitBuilder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hugsby on 12/25/17.
 */

public class LoginExecutor {

    public interface LoginInterface {
       void onLoginSuccess();
       void onLoginError(String errorMessage);
    }

    private LoginInterface loginInterface;

    public LoginExecutor(String username, String password, Context context) {
        loginInterface = (LoginInterface) context;

        AuthData authData = new AuthData(username, password);

        RetrofitBuilder.getInstance().login(authData).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loginInterface.onLoginSuccess();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

            }
        });
    }

}
