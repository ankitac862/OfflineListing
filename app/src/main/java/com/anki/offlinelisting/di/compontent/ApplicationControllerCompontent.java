package com.anki.offlinelisting.di.compontent;

import android.app.Application;


import com.anki.offlinelisting.AppController;
import com.anki.offlinelisting.di.annotation.ApplicationScope;
import com.anki.offlinelisting.di.module.ActivityBuilder;
import com.anki.offlinelisting.di.module.ApplicationContextModule;
import com.anki.offlinelisting.di.module.ViewModelModule;
import com.anki.offlinelisting.remote.RetrofitModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@ApplicationScope
@Component(modules = {AndroidInjectionModule.class, ActivityBuilder.class, ApplicationContextModule.class, RetrofitModule.class, ViewModelModule.class})
public interface ApplicationControllerCompontent extends AndroidInjector<AppController> {

    @Component.Factory
    interface Factory{
        ApplicationControllerCompontent create(@BindsInstance Application application);
    }



}
