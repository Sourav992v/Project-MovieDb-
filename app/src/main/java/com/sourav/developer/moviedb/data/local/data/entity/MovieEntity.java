package com.sourav.developer.moviedb.data.local.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sourav.developer.moviedb.AppConstants;
import com.sourav.developer.moviedb.data.local.data.converter.CastListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.CrewListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.GenreListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.MovieListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.ReviewListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.StringListConverter;
import com.sourav.developer.moviedb.data.local.data.converter.VideoListTypeConverter;
import com.sourav.developer.moviedb.data.remote.model.Cast;
import com.sourav.developer.moviedb.data.remote.model.Crew;
import com.sourav.developer.moviedb.data.remote.model.Genre;
import com.sourav.developer.moviedb.data.remote.model.Review;
import com.sourav.developer.moviedb.data.remote.model.Video;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "movie")
public class MovieEntity implements Parcelable {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Long id;

    @Expose
    private Long page;

    @Expose
    private Long totalPage;

    @SerializedName(value = "header", alternate = {"title", "name"})
    private String header;

    @SerializedName("posterPath")
    private String posterPath;

    @SerializedName(value = "description", alternate = {"overview", "synopsis"})
    private String description;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("genres")
    @Expose
    @TypeConverters(GenreListTypeConverter.class)
    private List<Genre> genres;

    @SerializedName("videos")
    @Expose
    @TypeConverters(VideoListTypeConverter.class)
    private List<Video> videos;

    @Expose
    @TypeConverters(CastListTypeConverter.class)
    private List<Cast> casts;

    @Expose
    @TypeConverters(CrewListTypeConverter.class)
    private List<Crew> crews;

    @Expose
    @TypeConverters(ReviewListTypeConverter.class)
    private List<Review> reviews;

    @Expose
    @TypeConverters(StringListConverter.class)
    private List<String> categoryTypes;

    @Expose
    @TypeConverters(MovieListTypeConverter.class)
    private List<MovieEntity> similarMovies;

    @SerializedName("runtime")
    @Expose
    private Long runtime;

    @SerializedName("status")
    @Expose
    private String status;

    public Long getId() {
        return id;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPages(Long totalPage) {
        this.totalPage = totalPage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPosterPath() {

        if(posterPath != null && !posterPath.startsWith("http")){
            posterPath = String.format(AppConstants.IMAGE_URL, posterPath);
        }
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public void setCrews(List<Crew> crews) {
        this.crews = crews;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<String> getCategoryTypes() {
        return categoryTypes;
    }

    public void setCategoryTypes(List<String> categoryTypes) {
        this.categoryTypes = categoryTypes;
    }

    public List<MovieEntity> getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(List<MovieEntity> similarMovies) {
        this.similarMovies = similarMovies;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isLastPage(){
        return getPage() >= getTotalPage();
    }


    public MovieEntity() {

        this.casts = new ArrayList<>();
        this.crews = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.videos = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.categoryTypes = new ArrayList<>();
        this.similarMovies = new ArrayList<>();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeValue(this.id);
        dest.writeValue(this.page);
        dest.writeValue(this.totalPage);
        dest.writeString(this.header);
        dest.writeTypedList(this.genres);
        dest.writeTypedList(this.videos);
        dest.writeTypedList(this.crews);
        dest.writeTypedList(this.casts);
        dest.writeTypedList(this.reviews);
        dest.writeStringList(this.categoryTypes);
        dest.writeTypedList(this.similarMovies);
        dest.writeString(this.posterPath);
        dest.writeString(this.description);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.runtime);
        dest.writeString(this.status);
    }


    protected MovieEntity(Parcel in) {

        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.page = (Long) in.readValue(Long.class.getClassLoader());
        this.totalPage = (Long) in.readValue(Long.class.getClassLoader());
        this.header = in.readString();
        this.posterPath = in.readString();
        this.description = in.readString();
        this.releaseDate = in.readString();
        this.genres = in.createTypedArrayList(Genre.CREATOR);
        this.runtime = (Long) in.readValue(Long.class.getClassLoader());
        this.status = in.readString();

    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel in) {
            return new MovieEntity(in);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };


}
