package com.anki.offlinelisting.viewmodel;


import android.app.Application;
import android.util.Log;
import android.view.View;

import com.anki.offlinelisting.di.annotation.ApplicationScope;
import com.anki.offlinelisting.local.entity.Member;
import com.anki.offlinelisting.remote.Resource;
import com.anki.offlinelisting.remote.repository.MemberRepository;



import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import dagger.Module;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * List view model
 */
public class MemberListViewModel extends ViewModel {
    private LiveData<Resource<List<Member>>> listData;

    @Inject
    MemberRepository repository;

    @Inject
    public MemberListViewModel(){
       super();
    }

    public void getData(String value , boolean refresh){
        listData = repository.loadData(value , refresh);
    }
    public LiveData<Resource<List<Member>>> getMemberLiveData() {
        return listData;
    }


    public void onClickContinue(Member member , int status){
        repository.updateData(member , status);
    }
}
