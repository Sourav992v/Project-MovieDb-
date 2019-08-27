package com.sourav.developer.moviedb.ui.main.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sourav.developer.moviedb.R;
import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;
import com.sourav.developer.moviedb.databinding.MoviesListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.CustomViewHolder>{
    private Activity activity;
    private List<MovieEntity> movies;

    public MoviesListAdapter(Activity activity) {
        this.activity = activity;
        this.movies = new ArrayList<>();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        MoviesListItemBinding itemBinding = MoviesListItemBinding.inflate(inflater,viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(itemBinding);
        return viewHolder;
    }

    public void setItems(List<MovieEntity> movies){
        int startPosition = this.movies.size();
        this.movies.addAll(movies);
        notifyItemRangeChanged(startPosition,movies.size());

    }

    public MovieEntity getItem(int position){
        return movies.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int position) {
        customViewHolder.bindTo(getItem(position));

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    protected class CustomViewHolder extends RecyclerView.ViewHolder {
        private MoviesListItemBinding binding;
        public CustomViewHolder(@NonNull MoviesListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

            itemView.setLayoutParams(new RecyclerView.LayoutParams(new Float(width * 0.85f).intValue(),
                    RecyclerView.LayoutParams.WRAP_CONTENT));
        }

        public void bindTo(MovieEntity movie){
            Picasso.get().load(movie.getPosterPath())
                    .placeholder(R.drawable.ic_movie)
                    .into(binding.image);
        }
    }
}
