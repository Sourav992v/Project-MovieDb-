package com.sourav.developer.moviedb.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.sourav.developer.moviedb.data.local.AppDatabase;
import com.sourav.developer.moviedb.data.local.data.dao.MovieDao;
import com.sourav.developer.moviedb.data.local.data.dao.TvDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application){
        return Room.databaseBuilder(application,
                AppDatabase.class, "entertainment.db")
                .allowMainThreadQueries().build();

    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(@NonNull AppDatabase appDatabase){
        return appDatabase.movieDao();
    }

    @Provides
    @Singleton
    TvDao provideTvDao(@NonNull AppDatabase appDatabase){
        return appDatabase.tvDao();
    }

}
