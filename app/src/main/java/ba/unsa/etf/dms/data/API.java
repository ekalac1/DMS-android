package ba.unsa.etf.dms.data;

import java.util.List;

import ba.unsa.etf.dms.data.auth.AuthData;
import ba.unsa.etf.dms.data.content.ContentDTO;
import ba.unsa.etf.dms.data.content.ContentResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Hugsby on 12/25/17.
 */

public interface API {

    @POST("login")
    Call<ResponseBody> login(@Body AuthData data);

    @GET("content")
    Call<List<ContentResponse>> getUserDocuments(@Query("user") String user);

    @GET("content/workspace")
    Call<List<ContentResponse>> getUserWorkspace(@Query("user") String user);

    @POST("content/save")
    Call<ResponseBody> saveDocument(@Body ContentDTO ontentResponse);
}
