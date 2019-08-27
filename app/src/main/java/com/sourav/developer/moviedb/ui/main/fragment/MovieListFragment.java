package com.sourav.developer.moviedb.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sourav.developer.moviedb.databinding.MoviesListFragmentBinding;
import com.sourav.developer.moviedb.factory.ViewModelFactory;
import com.sourav.developer.moviedb.ui.base.BaseFragment;
import com.sourav.developer.moviedb.ui.base.custom.recyclerview.RecyclerItemClickListener;
import com.sourav.developer.moviedb.ui.main.adapter.MoviesListAdapter;
import com.sourav.developer.moviedb.ui.main.viewmodel.MovieListViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MovieListFragment extends BaseFragment implements RecyclerItemClickListener.OnRecyclerViewItemClickListener {

    @Inject
    ViewModelFactory viewModelFactory;


    MovieListViewModel movieListViewModel;

    private MoviesListFragmentBinding binding;
    private MoviesListAdapter moviesListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);

    }

    @Override
    public void onItemClick(View parent, View child, int position) {

    }
}
