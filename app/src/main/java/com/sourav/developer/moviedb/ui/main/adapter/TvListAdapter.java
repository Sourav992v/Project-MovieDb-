package com.sourav.developer.moviedb.ui.main.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sourav.developer.moviedb.R;
import com.sourav.developer.moviedb.data.local.data.entity.TvEntity;
import com.sourav.developer.moviedb.databinding.MoviesListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TvListAdapter extends RecyclerView.Adapter<TvListAdapter.CustomViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Activity activity;
    private List<TvEntity> tvEntities;

    public TvListAdapter(Activity activity) {
        this.activity = activity;
        this.tvEntities = new ArrayList<>();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        MoviesListItemBinding itemBinding = MoviesListItemBinding.inflate(inflater,viewGroup,false);

        CustomViewHolder customViewHolder = new CustomViewHolder(itemBinding);

        return customViewHolder;
    }

    public void setItems(List<TvEntity> tvEntities){
        int startPosition = this.tvEntities.size();
        this.tvEntities.addAll(tvEntities);
        notifyItemRangeChanged(startPosition,tvEntities.size());
    }

    public TvEntity getItem(int possition){
        return tvEntities.get(possition);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int position) {
        customViewHolder.bindTo(getItem(position));

    }

    @Override
    public int getItemCount() {
        return tvEntities.size();
    }

    protected class CustomViewHolder extends RecyclerView.ViewHolder {

        MoviesListItemBinding binding;
        public CustomViewHolder(@NonNull MoviesListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

            itemView.setLayoutParams(new RecyclerView.LayoutParams(new Float(width * 0.85F).intValue(),
                    RecyclerView.LayoutParams.WRAP_CONTENT));
        }

        public void bindTo(TvEntity tvEntity){
            Picasso.get().load(tvEntity.getPosterPath())
                    .placeholder(R.drawable.ic_tv)
                    .into(binding.image);
        }
    }
}
