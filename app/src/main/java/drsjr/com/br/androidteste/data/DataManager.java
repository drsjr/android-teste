package drsjr.com.br.androidteste.data;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by junior on 5/7/18.
 */

public abstract class DataManager<S> {

    public static final String API_BASE_URL = "http://192.168.1.30:8080/api/";

    public DataManager(){
    }

    private static OkHttpClient httpClient
            = new OkHttpClient.Builder()
            .authenticator(Authenticator.NONE)
            .build();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

}
