package com.sourav.developer.moviedb.data.remote.api;

import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;
import com.sourav.developer.moviedb.data.remote.model.CreditResponse;
import com.sourav.developer.moviedb.data.remote.model.MovieApiResponse;
import com.sourav.developer.moviedb.data.remote.model.ReviewApiResponse;
import com.sourav.developer.moviedb.data.remote.model.VideoResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/{type}?language=en-US&region=US")
    Observable<MovieApiResponse> fetchMoviesByType(@Path("type") String type,
                                                   @Query("page") Long page);

    @GET("/3/movie/{movieId}")
    Observable<MovieEntity> fetchMovieDetail(@Path("movieId") String movieId);

    @GET("/3/movie/{movieId}/videos")
    Observable<VideoResponse> fetchMovieVideo(@Path("MovieId") String movieId);

    @GET("/3/movie/{movieId}/credits")
    Observable<CreditResponse> fetchCastDetail(@Path("movieId") String movieId);

    @GET("/3/movie/{movieId}/similar")
    Observable<MovieApiResponse> fetchSimilarMovie(@Path("movieId") String movieId,
                                                   @Query("page") long page);

    @GET("/3/movie/{movieId}/reviews")
    Observable<ReviewApiResponse> fetchMovieReview(@Path("movieId") String movieId);

    @GET("/3/serach/movie")
    Observable<MovieApiResponse> searchMoviesByQuery(@Query("query") String Query,
                                                    @Query("page") long page);
}
