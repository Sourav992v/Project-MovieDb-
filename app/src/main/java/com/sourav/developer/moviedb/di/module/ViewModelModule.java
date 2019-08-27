package com.sourav.developer.moviedb.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.sourav.developer.moviedb.factory.ViewModelFactory;
import com.sourav.developer.moviedb.ui.detail.MovieDetailViewModel;
import com.sourav.developer.moviedb.ui.main.viewmodel.MovieListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    protected abstract ViewModel movieListViewModel(MovieListViewModel movieListViewModel);

    protected abstract ViewModel movieDeatilViewModel(MovieDetailViewModel)




}
