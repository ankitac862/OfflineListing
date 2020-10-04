package com.anki.offlinelisting.di.module;

import android.content.Context;

import com.anki.offlinelisting.view.activity.MainActivity;
import com.anki.offlinelisting.di.annotation.contextquilifiers.ActivityContext;


import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainActivityModule {

    @Binds
    @ActivityContext
    public abstract Context provideActivityContext(AppCompatActivity appCompatActivity);


    @Binds
    public abstract AppCompatActivity bindMainActivity(MainActivity appCompatActivity);



}
