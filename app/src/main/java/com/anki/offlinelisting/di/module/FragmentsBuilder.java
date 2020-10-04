package com.anki.offlinelisting.di.module;

import com.anki.offlinelisting.di.annotation.ActivityScope;
import com.anki.offlinelisting.di.annotation.FragmentScope;
import com.anki.offlinelisting.di.annotation.contextquilifiers.ActivityContext;
import com.anki.offlinelisting.view.fragment.MemberFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentsBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = MemberFragmentModule.class)
    MemberFragment getMemberFragment();
}
