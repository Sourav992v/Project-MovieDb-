package com.sourav.developer.moviedb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.sourav.developer.moviedb.R;
import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;
import com.sourav.developer.moviedb.data.local.data.entity.TvEntity;
import com.sourav.developer.moviedb.data.remote.model.Genre;
import com.sourav.developer.moviedb.ui.base.custom.menu.SlideMenuItem;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.sourav.developer.moviedb.AppConstants.MOVIE_STATUS_RELEASED;

public class AppUtils {

    private static Date getDate(String aDate){
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date stringDate = simpleDateFormat.parse(aDate,pos);
        return stringDate;
    }

    public static String getFormattedDate(String dateString){
        Date date = getDate(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day = cal.get(Calendar.DATE);
        switch (day % 10){
            case 1:
                return new SimpleDateFormat("MMMM d'st', YYYY" ).format(date);
            case 2:
                return new SimpleDateFormat("MMMM d'nd', yyyy").format(date);
            case 3:
                return new SimpleDateFormat("MMMM d'rd', yyyy").format(date);
            default:
                return new SimpleDateFormat("MMMM d'th', yyyy").format(date);
        }
    }

    public static List<String> getGenres(List<Genre> genres){
        List<String> genresNames = new ArrayList<>(genres.size());
        for(Object obj : genres){
            if (obj instanceof String){
                genresNames.add(Objects.toString(obj,null));

            }else genresNames.add(String.valueOf(((Genre)obj).getName()));
        }
        return genresNames;
    }

    public static int getScreenWidth(Context mContext){
        boolean width = false;
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int width1 = size.x;

        return width1;
    }

    public static int getScreenHeight(Context mContext) {
        boolean height = false;
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int height1 = size.y;

        return height1;
    }

    public static List<SlideMenuItem> getMenuList(Context context){
        List<SlideMenuItem> slideMenuItems = new ArrayList<>();
        List<String> menuTitles = Arrays.asList(context.getResources().getStringArray(R.array.menu_names));
        TypedArray menuIcons = context.getResources().obtainTypedArray(R.array.menu_icons);

        for(int i = 0; i< menuTitles.size(); i++) {
            SlideMenuItem slideMenuItem = new SlideMenuItem(menuTitles.get(i), menuIcons.getResourceId(i, -1));
            slideMenuItems.add(slideMenuItem);
        }

        menuIcons.recycle();
        return slideMenuItems;
    }

    public static List<MovieEntity> getMoviesByType(String type,
                                                    List<MovieEntity> movieEntities){
        List<MovieEntity> finalList = new ArrayList<>();
        for(MovieEntity movieEntity: movieEntities) {
            boolean add = false;
            for(String categoryType : movieEntity.getCategoryTypes()){
                if (type.equalsIgnoreCase(categoryType)){
                    add = true;
                }
            }

            if (add) finalList.add(movieEntity);
        }
        return finalList;
    }

    public static List<TvEntity> getTvListByType(String type,
                                                 List<TvEntity> tvEntities) {
        List<TvEntity> finalList = new ArrayList<>();
        for(TvEntity tvEntity: tvEntities) {
            boolean add = false;
            for(String categoryType : tvEntity.getCategoryTypes()) {
                if(type.equalsIgnoreCase(categoryType)) {
                    add = true;
                }
            }
            if(add) finalList.add(tvEntity);
        }
        return finalList;
    }

    public static String getRunTimeInMins(String status,
                                          Long runtime,
                                          String releaseDate){
        return (MOVIE_STATUS_RELEASED.equalsIgnoreCase(status) && runtime != null)
                ? String.format("%s mins", String.valueOf(runtime))
                :getFormattedDate(releaseDate);
    }

    public static String getSeasonNumber(Long seasonNumber){
        return String.format("Season %s",seasonNumber);
    }

    public static void closeKeyboard(Activity activity){
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()){
            if (activity.getCurrentFocus() != null){
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),0);
            }
        }
    }
}
