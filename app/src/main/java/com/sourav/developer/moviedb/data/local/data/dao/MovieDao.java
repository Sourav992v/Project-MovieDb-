package com.sourav.developer.moviedb.data.local.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sourav.developer.moviedb.data.local.data.entity.MovieEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<MovieEntity> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMovie(MovieEntity movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateMovie(MovieEntity movie);

    @Query("SELECT * FROM `MovieEntity` WHERE id = :id")
    MovieEntity getMovieById(Long id);

    @Query("SELECT * FROM `MovieEntity` WHERE id = :id")
    Flowable<MovieEntity> getMovieDetailsById(Long id);

    @Query("SELECT * FROM `MovieEntity` WHERE page = :page")
    List<MovieEntity> getMoviesByPage(Long page);
}
