package com.sourav.developer.moviedb.data.local.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sourav.developer.moviedb.AppConstants;
import com.sourav.developer.moviedb.data.local.data.converter.CastListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.CrewListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.ReviewListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.StringListConverter;
import com.sourav.developer.moviedb.data.local.data.converter.TvListTypeConverter;
import com.sourav.developer.moviedb.data.local.data.converter.VideoListTypeConverter;
import com.sourav.developer.moviedb.data.remote.model.Cast;
import com.sourav.developer.moviedb.data.remote.model.Crew;
import com.sourav.developer.moviedb.data.remote.model.Genre;
import com.sourav.developer.moviedb.data.remote.model.Review;
import com.sourav.developer.moviedb.data.remote.model.Video;

import java.util.ArrayList;
import java.util.List;

@Entity(primaryKeys = ("id"))
public class TvEntity implements Parcelable {
    @SerializedName("id")
    @Expose
    private Long id;

    @Expose
    private Long page;

    @Expose
    private Long totalPages;

    @SerializedName(value="header", alternate={"title", "name"})
    @Expose
    private String header;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName(value="description", alternate={"overview", "synopsis"})
    private String description;


    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;


    @SerializedName("videos")
    @Expose
    @TypeConverters(VideoListTypeConverter.class)
    private List<Video> videos;

    @Expose
    @TypeConverters(CrewListTypeConverter.class)
    private List<Crew> crews;


    @Expose
    @TypeConverters(CastListTypeConverter.class)
    private List<Cast> casts;

    @Expose
    @TypeConverters(ReviewListTypeConverter.class)
    private List<Review> reviews;

    @Expose
    @TypeConverters(StringListConverter.class)
    private List<String> categoryTypes;

    @Expose
    @TypeConverters(TvListTypeConverter.class)
    private List<TvEntity> similarTvEntities;


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("number_of_seasons")
    @Expose
    private Long numberOfSeasons;



    public Long getId() {
        return id;
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
        if(posterPath != null && !posterPath.startsWith("http")) {
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

    public List<Crew> getCrews() {
        return crews;
    }

    public void setCrews(List<Crew> crews) {
        this.crews = crews;
    }

    public List<Cast> getCasts() {
        return casts;
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

    public List<TvEntity> getSimilarTvEntities() {
        return similarTvEntities;
    }

    public void setSimilarTvEntities(List<TvEntity> similarTvEntities) {
        this.similarTvEntities = similarTvEntities;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Long numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public List<String> getCategoryTypes() {
        return categoryTypes;
    }

    public void setCategoryTypes(List<String> categoryTypes) {
        this.categoryTypes = categoryTypes;
    }

    public boolean isLastPage() {
        return getPage() >= getTotalPages();
    }

    public TvEntity() {
        this.casts = new ArrayList<>();
        this.crews = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.videos = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.categoryTypes = new ArrayList<>();
        this.similarTvEntities = new ArrayList<>();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.page);
        dest.writeValue(this.totalPages);
        dest.writeString(header);
        dest.writeString(posterPath);
        dest.writeString(description);
        dest.writeTypedList(genres);
        dest.writeTypedList(videos);
        dest.writeTypedList(crews);
        dest.writeTypedList(casts);
        dest.writeTypedList(reviews);
        dest.writeStringList(categoryTypes);
        dest.writeTypedList(similarTvEntities);
        dest.writeString(status);
        dest.writeLong(numberOfSeasons);

    }

    protected TvEntity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.page = (Long) in.readValue(Long.class.getClassLoader());
        this.totalPages = (Long) in.readValue(Long.class.getClassLoader());
        this.header = in.readString();
        this.posterPath = in.readString();
        this.description = in.readString();
        this.genres = in.createTypedArrayList(Genre.CREATOR);
        this.videos = in.createTypedArrayList(Video.CREATOR);
        this.crews = in.createTypedArrayList(Crew.CREATOR);
        this.casts = in.createTypedArrayList(Cast.CREATOR);
        this.reviews = in.createTypedArrayList(Review.CREATOR);
        this.categoryTypes = in.createStringArrayList();
        this.similarTvEntities = in.createTypedArrayList(TvEntity.CREATOR);
        this.status = in.readString();
        this.numberOfSeasons = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<TvEntity> CREATOR = new Creator<TvEntity>() {
        @Override
        public TvEntity createFromParcel(Parcel in) {
            return new TvEntity(in);
        }

        @Override
        public TvEntity[] newArray(int size) {
            return new TvEntity[size];
        }
    };
}
