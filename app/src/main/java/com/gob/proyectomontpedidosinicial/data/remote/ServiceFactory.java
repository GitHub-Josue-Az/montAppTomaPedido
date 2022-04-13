package com.gob.proyectomontpedidosinicial.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Service Factory for Retrofit
 */
public class ServiceFactory {

    /*public static final String API_BASE_URL = BuildConfig.BASE;*/

    /* API  */
    public static final String NUEVO_URL = "https://api.montgroup.com.pe/";


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

   /* private static OkHttpClient.Builder okHttpClientTimeout =
            new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS);*/


    private static Retrofit.Builder nuevoBuilder =
            new Retrofit.Builder()
                    .baseUrl(NUEVO_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createServiceNuevo(Class<S> serviceClass) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging)
                .addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        //   Retrofit retrofit = trasuBuilder.client(httpClient.build()).build();
        Retrofit retrofit = nuevoBuilder.client(httpClient.build()).client(client).build();
        return retrofit.create(serviceClass);
    }

    public static Retrofit nuevoRetrofit() {
        Retrofit retrofit = nuevoBuilder.client(
                                httpClient.build()
                        ).build();
        return retrofit;
    }


}
