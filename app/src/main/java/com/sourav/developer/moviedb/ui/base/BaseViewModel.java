package com.sourav.developer.moviedb.ui.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addToDisposable(Disposable disposable){
        compositeDisposable.remove(disposable);
        compositeDisposable.add(disposable);

    }
    public void stop(){
        compositeDisposable.clear();
    }
}
