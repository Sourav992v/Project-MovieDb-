package com.sourav.developer.moviedb.ui.detail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sourav.developer.moviedb.data.local.data.dao.TvDao;
import com.sourav.developer.moviedb.data.local.data.entity.TvEntity;
import com.sourav.developer.moviedb.data.remote.api.TvApiService;
import com.sourav.developer.moviedb.data.repository.TvRepository;

public class TvDetailViewModel extends ViewModel {

    private TvRepository tvRepository;
    private MutableLiveData<TvEntity> tvDetailsLiveData = new MutableLiveData<>();

    public TvDetailViewModel(TvDao tvDao, TvApiService tvApiService){

        tvRepository = new TvRepository(tvDao, tvApiService);

    }

    public void fetchTvDetails(TvEntity tvEntity){
        tvRepository.fetchTvDetails(tvEntity.getId())
                .subscribe(resource -> {
                    if (resource.isLoaded())
                        getDetailsMutableLiveData().postValue(resource.data);
                });

    }

    public MutableLiveData<TvEntity> getDetailsMutableLiveData(){
        return tvDetailsLiveData;
    }
}
