package ba.unsa.etf.dms.data.content;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.List;

import ba.unsa.etf.dms.data.RetrofitBuilder;
import ba.unsa.etf.dms.data.Utils;
import ba.unsa.etf.dms.presentation.DocumentCallback;
import ba.unsa.etf.dms.presentation.content.list.ContentListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static ba.unsa.etf.dms.presentation.BaseLoggedActivity.USERNAME_CONST;

/**
 * Created by Hugsby on 1/2/18.
 */

public class UserProfileExecutor {


    public UserProfileExecutor(ContentListViewModel contentListViewModel, final Context context, final DocumentCallback callback) {

        RetrofitBuilder.getInstance().getUserDocuments(getLoggedUser(context)).enqueue(new Callback<List<ContentResponse>>() {
            @Override
            public void onResponse(Call<List<ContentResponse>> call, @NonNull Response<List<ContentResponse>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(Utils.parseError(response));
                }
            }

            @Override
            public void onFailure(Call<List<ContentResponse>> call, Throwable t) {
                callback.onNetworkError(t.getLocalizedMessage());
            }
        });
    }

    private String getLoggedUser(Context context) {
        SharedPreferences settings = context.getSharedPreferences(USERNAME_CONST, MODE_PRIVATE);
        return settings.getString(USERNAME_CONST, "");
    }
}
