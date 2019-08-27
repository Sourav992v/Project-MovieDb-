package com.sourav.developer.moviedb.ui.main.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sourav.developer.moviedb.data.Resource;
import com.sourav.developer.moviedb.data.local.data.dao.MovieDao;
import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;
import com.sourav.developer.moviedb.data.remote.api.MovieApiService;
import com.sourav.developer.moviedb.data.repository.MovieRepository;
import com.sourav.developer.moviedb.ui.base.BaseViewModel;

import java.util.List;

public class MovieListViewModel extends BaseViewModel {

    private String type;
    private MovieRepository movieRepository;
    private MutableLiveData<Resource<List<MovieEntity>>> moviesLiveData = new MutableLiveData<>();

    public MovieListViewModel(MovieDao movieDao, MovieApiService movieApiService){
        movieRepository = new MovieRepository(movieDao, movieApiService);
    }

    public void setType(String type){
        this.type = type;
    }

    public void loadMoreMovies(Long currentPage){
        movieRepository.loadMoviesByType(currentPage,type)
                .doOnSubscribe(disposable -> addToDisposable(disposable))
                .subscribe(resource -> getMoviesLiveData().postValue(resource));
    }

    public boolean isLastPage(){
        return moviesLiveData.getValue() != null &&
                !moviesLiveData.getValue().data.isEmpty() ?
                moviesLiveData.getValue().data.get(0).isLastPage():
                false;
    }

    public MutableLiveData<Resource<List<MovieEntity>>> getMoviesLiveData(){
        return moviesLiveData;
    }
}
