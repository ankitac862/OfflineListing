package com.anki.offlinelisting.di.module;


import android.app.Application;

import com.anki.offlinelisting.di.annotation.ApplicationScope;
import com.anki.offlinelisting.viewmodel.ViewModelFactory;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);

    @Provides
    public static LinearLayoutManager provideLinearLayoutManager(Application context){
        return new LinearLayoutManager(context);
    }

    @Provides
    public static CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

}
