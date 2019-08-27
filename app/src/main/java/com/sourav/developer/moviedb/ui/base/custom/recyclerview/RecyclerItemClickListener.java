package com.sourav.developer.moviedb.ui.base.custom.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private OnRecyclerViewItemClickListener itemClickListener;

    public RecyclerItemClickListener(Context context, OnRecyclerViewItemClickListener itemClickListener) {

        this.itemClickListener = itemClickListener;

        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

        if (childView != null && itemClickListener != null && gestureDetector.onTouchEvent(motionEvent)){
            itemClickListener.onItemClick(recyclerView, childView, recyclerView.getChildLayoutPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View parent,View child, int position);

    }
}
