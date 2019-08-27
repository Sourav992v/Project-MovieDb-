package com.sourav.developer.moviedb.data.local.data.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourav.developer.moviedb.data.remote.model.Review;

import java.lang.reflect.Type;
import java.util.List;

public class ReviewListTypeConverter {

    @TypeConverter
    public List<Review> fromString(String value){
        Type listType = new TypeToken<List<Review>>(){}.getType();
        return new Gson().fromJson(value,listType);
    }

    @TypeConverter
    public String fromList(List<Review> reviews){
        return new Gson().toJson(reviews);
    }
}
