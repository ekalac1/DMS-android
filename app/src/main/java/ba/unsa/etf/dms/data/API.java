package ba.unsa.etf.dms.data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Hugsby on 12/25/17.
 */

public interface API {

    @POST("login")
    Call<ResponseBody> login(@Body AuthData data);
}
