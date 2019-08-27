package com.sourav.developer.moviedb.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class VideoResponse implements Parcelable {

    private Long id;
    private List<Video> results;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeList(this.results);
    }

    protected VideoResponse(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        results = in.createTypedArrayList(Video.CREATOR);
    }

    public static final Creator<VideoResponse> CREATOR = new Creator<VideoResponse>() {
        @Override
        public VideoResponse createFromParcel(Parcel in) {
            return new VideoResponse(in);
        }

        @Override
        public VideoResponse[] newArray(int size) {
            return new VideoResponse[size];
        }
    };
}
