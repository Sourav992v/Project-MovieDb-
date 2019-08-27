package com.sourav.developer.moviedb.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CreditResponse implements Parcelable {

    private List<Cast> cast;
    private List<Crew> crew;

    public CreditResponse(List<Cast> cast, List<Crew> crew) {
        this.cast = cast;
        this.crew = crew;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(cast);
        dest.writeTypedList(crew);
    }

    protected CreditResponse(Parcel in) {
        cast = in.createTypedArrayList(Cast.CREATOR);
        crew = in.createTypedArrayList(Crew.CREATOR);
    }

    public static final Creator<CreditResponse> CREATOR = new Creator<CreditResponse>() {
        @Override
        public CreditResponse createFromParcel(Parcel in) {
            return new CreditResponse(in);
        }

        @Override
        public CreditResponse[] newArray(int size) {
            return new CreditResponse[size];
        }
    };
}
