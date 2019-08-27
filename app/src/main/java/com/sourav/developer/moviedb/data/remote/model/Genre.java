package com.sourav.developer.moviedb.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Genre implements Parcelable {


    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre withId(Long id){
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre withName(String name){
        this.name = name;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.name);
    }

    public Genre() {
    }

    protected Genre(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = (String) in.readValue(String.class.getClassLoader());
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };
}
