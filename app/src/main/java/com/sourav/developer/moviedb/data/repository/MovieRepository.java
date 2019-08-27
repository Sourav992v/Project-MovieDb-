package com.sourav.developer.moviedb.data.repository;

import android.support.annotation.NonNull;

import com.sourav.developer.moviedb.data.NetworkBoundResource;
import com.sourav.developer.moviedb.data.Resource;
import com.sourav.developer.moviedb.data.local.data.dao.MovieDao;
import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;
import com.sourav.developer.moviedb.data.remote.api.MovieApiService;
import com.sourav.developer.moviedb.data.remote.model.MovieApiResponse;
import com.sourav.developer.moviedb.utils.AppUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Singleton
public class MovieRepository {

    private MovieDao movieDao;
    private MovieApiService movieApiService;

    public MovieRepository(MovieDao movieDao,
                           MovieApiService movieApiService ){
        this.movieDao = movieDao;
        this.movieApiService = movieApiService;
    }

    public Observable<Resource<List<MovieEntity>>> loadMoviesByType(Long page, String type){
        return new NetworkBoundResource<List<MovieEntity>, MovieApiResponse>() {

            @Override
            protected void saveCallResult(@NonNull MovieApiResponse item) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for(MovieEntity movieEntity : item.getResults()){
                    MovieEntity storedMovieEntity = movieDao.getMovieById(movieEntity.getId());
                    if(storedMovieEntity == null) movieEntity.setCategoryTypes(Arrays.asList(type));
                    else{
                        List<String> categories = storedMovieEntity.getCategoryTypes();
                        categories.add(type);
                        movieEntity.setCategoryTypes(categories);
                    }

                    movieEntity.setPage(item.getPage());
                    movieEntity.setTotalPages(item.getTotalPages());
                    movieEntities.add(movieEntity);
                }
                movieDao.insertMovies(movieEntities);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<MovieEntity>> loadFromDb() {
                List<MovieEntity> movieEntities = movieDao.getMoviesByPage(page);
                if (movieEntities == null || movieEntities.isEmpty()){
                    return Flowable.empty();
                }
                return Flowable.just(AppUtils.getMoviesByType(type,movieEntities));
            }

            @NonNull
            @Override
            protected Observable<Resource<MovieApiResponse>> createCall() {
                return movieApiService.fetchMoviesByType(type,page)
                        .flatMap(movieApiResponse -> Observable.just(movieApiResponse == null
                        ? Resource.error("", new MovieApiResponse())
                        : Resource.success(movieApiResponse)));
            }
        }.getAsObservable();
    }

    public Observable<Resource<List<MovieEntity>>> fetchMovieDetails(Long movieId){
        return new NetworkBoundResource<MovieEntity, MovieEntity>(){

            @Override
            protected void saveCallResult(@NonNull MovieEntity item) {
                MovieEntity movieEntity = movieDao.getMovieById(item.getId());
                if (movieEntity == null){
                    movieDao.insertMovie(movieEntity);
                }
                else movieDao.updateMovie(item);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<MovieEntity> loadFromDb() {

                MovieEntity movieEntity = movieDao.getMovieById(movieId);
                if (movieEntity == null){
                    return Flowable.empty();
                }
                return Flowable.just(movieEntity);
            }

            @NonNull
            @Override
            protected Observable<Resource<MovieEntity>> createCall() {

                String id = String.valueOf(movieId);

                return Observable.combineLatest(movieApiService.fetchMovieDetail(id),
                        movieApiService.fetchMovieVideo(id),
                        movieApiService.fetchCastDetail(id),
                        movieApiService.fetchSimilarMovie(id,1),
                        movieApiService.fetchMovieReview(id),
                        ((movieEntity, videoResponse, creditResponse, movieApiResponse, reviewApiResponse) -> {
                            if (videoResponse != null){
                                movieEntity.setVideos(videoResponse.getResults());
                            }
                            if (creditResponse != null){
                                movieEntity.setCrews(creditResponse.getCrew());
                                movieEntity.setCasts(creditResponse.getCast());
                            }

                            if(movieApiResponse != null) {
                                movieEntity.setSimilarMovies(movieApiResponse.getResults());
                            }

                            if(reviewApiResponse != null) {
                                movieEntity.setReviews(reviewApiResponse.getResults());
                            }

                            return Resource.success(movieEntity);
                        }));
            }
        }.getAsObservable();
    }

    public Observable<Resource<List<MovieEntity>>> searchMovies(Long page,
                                                             String query){
        return new NetworkBoundResource<List<MovieEntity>, MovieApiResponse>() {
            @Override
            protected void saveCallResult(@NonNull MovieApiResponse item) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for(MovieEntity movieEntity : item.getResults()){

                    MovieEntity storedMovieEntity = movieDao.getMovieById(movieEntity.getId());
                    if (storedMovieEntity == null) movieEntity.setCategoryTypes(Arrays.asList(query));
                    else{
                        List<String> categories = storedMovieEntity.getCategoryTypes();
                        categories.add(query);
                        movieEntity.setCategoryTypes(categories);
                    }

                    movieEntity.setPage(item.getPage());
                    movieEntity.setTotalPages(item.getTotalPages());
                    movieEntities.add(movieEntity);

                }
                movieDao.insertMovies(movieEntities);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<MovieEntity>> loadFromDb() {

                List<MovieEntity> movieEntities = movieDao.getMoviesByPage(page);
                if (movieEntities == null || movieEntities.isEmpty()){
                    return Flowable.empty();
                }
                return Flowable.just(AppUtils.getMoviesByType(query,movieEntities));
            }

            @NonNull
            @Override
            protected Observable<Resource<MovieApiResponse>> createCall() {
                return movieApiService.searchMoviesByQuery(query,1)
                        .flatMap(movieApiResponse -> Observable.just(movieApiResponse == null
                                                        ? Resource.error("", new MovieApiResponse())
                                                        : Resource.success(movieApiResponse)));
            }
        }.getAsObservable();
    }
}
