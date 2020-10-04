package com.anki.offlinelisting.di.module;

import com.anki.offlinelisting.view.activity.MainActivity;
import com.anki.offlinelisting.di.annotation.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class , FragmentsBuilder.class})
    MainActivity getMainActivity();
}
