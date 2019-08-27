package com.sourav.developer.moviedb.data.local.data.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;

import java.lang.reflect.Type;
import java.util.List;

public class MovieListTypeConverter {

    @TypeConverter
    public List<MovieEntity> fromString(String value) {

        Type listType = new TypeToken<List<MovieEntity>>(){}.getType();
        return new Gson().fromJson(value,listType);
    }

    @TypeConverter
    public String fromList(List<MovieEntity> movies){
        return new Gson().toJson(movies);
    }
}
