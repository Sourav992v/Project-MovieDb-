package com.sourav.developer.moviedb.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sourav.developer.moviedb.data.remote.api.MovieApiService;
import com.sourav.developer.moviedb.data.remote.api.TvApiService;
import com.sourav.developer.moviedb.data.remote.interceptor.NetworkInterceptor;
import com.sourav.developer.moviedb.data.remote.interceptor.RequestIntercepter;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(MOVIE_ARRAY_LIST_CLASS_TYPE, new MoviesJsonDeserializer());
        return gsonBuilder.create();

    }

    @Provides
    @Singleton
    Cache provideCache(Application application){
        long cacheSize = 10 * 1024 * 1024;
        File httpCacheDirectory = new File(application.getCacheDir(), "http-cache");

        return new Cache(httpCacheDirectory, cacheSize);
    }

    @Provides
    @Singleton
    NetworkInterceptor provideNetworkInterceptor(Application application){
        return new NetworkInterceptor(application.getApplicationContext());
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, NetworkInterceptor interceptor){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache);
        httpClient.addInterceptor(interceptor);
        httpClient.addInterceptor(logging);
        httpClient.addNetworkInterceptor(new RequestIntercepter());
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);

        return httpClient.build();
    }

    @Provides
    @Singleton
    MovieApiService provideMovieApiService(Retrofit retrofit){
        return retrofit.create(MovieApiService.class);
    }

    @Provides
    @Singleton
    TvApiService provideTvApiservice(Retrofit retrofit){
        return retrofit.create(TvApiService.class);
    }
}
