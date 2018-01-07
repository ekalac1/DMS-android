package ba.unsa.etf.dms.data.content;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.nio.charset.Charset;
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
 * Created by Hugsby on 1/7/18.
 */

public class UserWorkspaceExecutor {

    public UserWorkspaceExecutor(ContentListViewModel contentListViewModel, final Context context, final DocumentCallback callback) {

        RetrofitBuilder.getInstance().getUserWorkspace(getLoggedUser(context)).enqueue(new Callback<List<ContentResponse>>() {
            @Override
            public void onResponse(Call<List<ContentResponse>> call, Response<List<ContentResponse>> response) {
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
