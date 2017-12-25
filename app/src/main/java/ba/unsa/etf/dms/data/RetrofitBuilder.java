package ba.unsa.etf.dms.data;

import java.util.logging.Level;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hugsby on 12/25/17.
 */

public class RetrofitBuilder {

    private static API DmsAPI;

    private static API serviceBuilder() {
        

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://salty-taiga-13205.herokuapp.com/")
                .client(client)
                .build();

        return retrofit.create(API.class);
    }

    public static API getInstance() {
        if (DmsAPI == null) {
            DmsAPI = serviceBuilder();
        }
        return DmsAPI;
    }


}
