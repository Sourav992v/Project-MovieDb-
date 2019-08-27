package com.sourav.developer.moviedb.data.local.data.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourav.developer.moviedb.data.remote.model.CreditResponse;

import java.lang.reflect.Type;
import java.util.List;

public class CreditResponseTypeConverter {

    @TypeConverter
    public CreditResponse fromString(String value){
        Type listType = new TypeToken<List<CreditResponse>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromList(List<CreditResponse> creditResponses){
        return new Gson().toJson(creditResponses);
    }
}
