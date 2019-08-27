package com.sourav.developer.moviedb.ui.main.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.sourav.developer.moviedb.data.Resource;
import com.sourav.developer.moviedb.data.local.data.dao.TvDao;
import com.sourav.developer.moviedb.data.local.data.entity.TvEntity;
import com.sourav.developer.moviedb.data.remote.api.TvApiService;
import com.sourav.developer.moviedb.data.repository.TvRepository;
import com.sourav.developer.moviedb.ui.base.BaseViewModel;

import java.util.List;
import java.util.ListIterator;

public class TvViewModel extends BaseViewModel {

    private String type;
    private TvRepository tvRepository;
    private MutableLiveData<Resource<List<TvEntity>>> tvLiveData = new MutableLiveData<>();

    public TvViewModel(TvDao tvDao, TvApiService tvApiService){
        tvRepository = new TvRepository(tvDao,tvApiService);
    }
    public void setType(String type){
        this.type = type;
    }

    public void loadMoreTvs(Long currentPage){
        tvRepository.loadTvsByType(currentPage, type)
                .doOnSubscribe(disposable -> addToDisposable(disposable))
                .subscribe(resource -> getTvLiveData().postValue(resource));
    }

    public boolean isLastPage(){
        return tvLiveData.getValue() != null &&
                !tvLiveData.getValue().data.isEmpty()
                ? tvLiveData.getValue().data.get(0).isLastPage()
                : false;
    }

    public MutableLiveData<Resource<List<TvEntity>>> getTvLiveData(){
        return tvLiveData;
    }
}
