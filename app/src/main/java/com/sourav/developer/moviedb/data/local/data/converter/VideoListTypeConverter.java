package com.sourav.developer.moviedb.data.local.data.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourav.developer.moviedb.data.remote.model.Video;

import java.lang.reflect.Type;
import java.util.List;

public class VideoListTypeConverter {
    @TypeConverter
    public List<Video> fromString(String value) {
        Type listType = new TypeToken<List<Video>>() {}.getType();
        List<Video> videos = new Gson().fromJson(value, listType);
        return videos;
    }

    @TypeConverter
    public String fromList(List<Video> videos) {
        return new Gson().toJson(videos);
    }
}
