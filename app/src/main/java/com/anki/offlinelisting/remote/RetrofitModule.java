package com.anki.offlinelisting.remote;

import android.app.Application;
import android.content.Context;


import com.anki.offlinelisting.local.AppDb;
import com.anki.offlinelisting.local.dao.MemberDao;
import com.anki.offlinelisting.utils.AppConstant;
import com.anki.offlinelisting.di.annotation.ApplicationScope;
import com.anki.offlinelisting.di.annotation.contextquilifiers.ApplicationContext;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module()
public class RetrofitModule {

    //50 MB Cache Size
    private final static long CACHE_SIZE = 50 * 1024 * 1024;

    @ApplicationScope
    @Provides
    public static HttpLoggingInterceptor provideHTTPLoggingInceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @ApplicationScope
    @Provides
    public static File provideFile(@ApplicationContext Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
        return httpCacheDirectory;
    }

    @ApplicationScope
    @Provides
    public static Cache provideCache(File httpCacheDirectory) {
        Cache cache = new Cache(httpCacheDirectory, CACHE_SIZE);
        return cache;
    }


    @ApplicationScope
    @Provides
    public static OkHttpClient provideOkHttpClient(@ApplicationContext final Context context,
                                                   Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        return chain.proceed(chain.request());

                    }
                })
                .build();
        return okHttpClient;
    }

    @ApplicationScope
    @Provides
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }


    @ApplicationScope
    @Provides
    public static APIService provideAPIInterface(Retrofit retrofit) {
        APIService apiInterface = retrofit.create(APIService.class);
        return apiInterface;
    }

    @ApplicationScope
    @Provides
    AppDb providerAppDb(Application application){
        return  Room.databaseBuilder(application, AppDb.class, "OF_DB.db").build();
    }
    @ApplicationScope
    @Provides
    MemberDao provideDao(AppDb db) {
        return db.memberDao();
    }

    @ApplicationScope
    @Provides
    public Executor getExecutor(){
        return  Executors.newFixedThreadPool(2);
    }

}