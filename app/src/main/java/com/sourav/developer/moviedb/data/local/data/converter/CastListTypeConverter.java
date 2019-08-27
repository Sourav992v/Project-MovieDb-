package com.sourav.developer.moviedb.data.local.data.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourav.developer.moviedb.data.remote.model.Cast;

import java.lang.reflect.Type;
import java.util.List;

public class CastListTypeConverter {

    @TypeConverter
    public List<Cast> fromString(String value){
        Type listType = new TypeToken<List<Cast>>(){}.getType();
        return new Gson().fromJson(value,listType);
    }

    public String fromList(List<Cast> casts){
        return new Gson().toJson(casts);
    }
}
