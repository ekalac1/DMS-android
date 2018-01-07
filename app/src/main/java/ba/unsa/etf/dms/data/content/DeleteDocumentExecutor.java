package ba.unsa.etf.dms.data.content;

import android.support.annotation.NonNull;

import ba.unsa.etf.dms.data.RetrofitBuilder;
import ba.unsa.etf.dms.presentation.content.list.ContentListViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hugsby on 1/7/18.
 */

public class DeleteDocumentExecutor {

    public interface DocumentDeleteExecutorInterface {
        void onDeleteSuccess();
        void onError(String errorMessage);
    }

    private DocumentDeleteExecutorInterface documentExecutorInterface;

    public DeleteDocumentExecutor(ContentListViewModel context, String user, int documentId) {
        documentExecutorInterface  = context;

        RetrofitBuilder.getInstance().deleteDocument(user, documentId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    documentExecutorInterface.onDeleteSuccess();
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
