package com.sourav.developer.moviedb.data.local.data.dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sourav.developer.moviedb.data.local.data.entity.TvEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface TvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTvList(List<TvEntity> tvEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTv(TvEntity tvEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateTv(TvEntity tvEntity);

    @Query("SELECT * FROM `TvEntity` WHERE id = :id")
    TvEntity getTvEntityById(Long id);

    @Query("SELECT * FROM `TvEntity` WHERE id = :id")
    Flowable<TvEntity> getTvDetailById(Long id);

    @Query("SELECT * FROM `TvEntity` WHERE page = :page")
    List<TvEntity> getTvListByPage(Long page);
}
