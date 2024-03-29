package com.sourav.developer.moviedb.ui.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.sourav.developer.moviedb.AppConstants;

public class BaseFragment extends Fragment implements AppConstants {

    protected Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
