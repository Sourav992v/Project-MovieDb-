package com.sourav.developer.moviedb.ui.detail;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sourav.developer.moviedb.data.local.data.dao.MovieDao;
import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;
import com.sourav.developer.moviedb.data.remote.api.MovieApiService;
import com.sourav.developer.moviedb.data.repository.MovieRepository;


import javax.inject.Inject;

public class MovieDetailViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<MovieEntity> movieDetailsLiveData = new MutableLiveData<>();

    @Inject
    public MovieDetailViewModel(MovieDao movieDao, MovieApiService movieApiService){
        movieRepository = new MovieRepository(movieDao, movieApiService);
    }

    public void fetchMovieDetail(MovieEntity movieEntity){
        movieRepository.fetchMovieDetails(movieEntity.getId())
                .subscribe(resource -> {
                    if(resource.isLoaded()) {
                        getMovieDetailsLiveData().postValue(resource.data);
                    }
                });
    }

    public MutableLiveData<MovieEntity> getMovieDetailsLiveData(){
        return movieDetailsLiveData;
    }
}
