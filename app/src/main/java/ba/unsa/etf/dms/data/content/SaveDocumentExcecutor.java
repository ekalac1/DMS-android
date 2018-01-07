package ba.unsa.etf.dms.data.content;

import android.support.annotation.NonNull;

import ba.unsa.etf.dms.data.RetrofitBuilder;

import ba.unsa.etf.dms.presentation.content.list.ContentListViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hugsby on 1/2/18.
 */

public class SaveDocumentExcecutor {

    public interface DocumentExecutorInterface {
        void onSaveSuccess();
        void onError(String errorMessage);
    }

    private DocumentExecutorInterface documentExecutorInterface;

    public SaveDocumentExcecutor(ContentListViewModel context, ContentDTO document) {
        documentExecutorInterface  = context;

        RetrofitBuilder.getInstance().saveDocument(document).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    documentExecutorInterface.onSaveSuccess();
                } else {
                    documentExecutorInterface.onError(response.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                documentExecutorInterface.onError(t.getMessage());
            }
        });
    }
}
