package com.sourav.developer.moviedb.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.sourav.developer.moviedb.data.local.data.converter.CastListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.CreditResponseTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.CrewListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.GenreListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.MovieListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.StringListConverter;
import com.sourav.developer.moviedb.data.local.data.converter.TvListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.VideoListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.dao.MovieDao;
import com.sourav.developer.moviedb.data.local.data.dao.TvDao;
import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;
import com.sourav.developer.moviedb.data.local.data.entity.TvEntity;

@Database(entities = {MovieEntity.class, TvEntity.class}, version = 1, exportSchema = false)
@TypeConverters({GenreListTypeConverter.class, VideoListTypeConverter.class,
        CreditResponseTypeConverter.class, MovieListTypeConverter.class,
        CastListTypeConverter.class, CrewListTypeConverter.class,
        StringListConverter.class, TvListTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "entertainment.db";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context,
                        AppDatabase.class,DATABASE_NAME)
                        .allowMainThreadQueries().build();
            }
        }

        return sInstance;
    }

    public abstract MovieDao movieDao();
    public abstract TvDao tvDao();
}
