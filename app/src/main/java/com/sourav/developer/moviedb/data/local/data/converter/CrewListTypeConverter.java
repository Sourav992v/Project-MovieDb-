package com.sourav.developer.moviedb.data.local.data.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourav.developer.moviedb.data.remote.model.Crew;

import java.lang.reflect.Type;
import java.util.List;

public class CrewListTypeConverter {

    @TypeConverter
    public List<Crew> fromString(String value){
        Type listType = new TypeToken<List<Crew>>(){}.getType();
        return new Gson().fromJson(value,listType);
    }

    @TypeConverter
    public String FromList(List<Crew> casts){
        return new Gson().toJson(casts);
    }
}
