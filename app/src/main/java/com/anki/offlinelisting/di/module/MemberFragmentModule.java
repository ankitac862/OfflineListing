package com.anki.offlinelisting.di.module;

import android.content.Context;

import com.anki.offlinelisting.di.annotation.ApplicationScope;
import com.anki.offlinelisting.di.annotation.ViewModelKey;
import com.anki.offlinelisting.view.adapter.ListAdapter;
import com.anki.offlinelisting.view.fragment.MemberFragment;
import com.anki.offlinelisting.viewmodel.MemberListViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;


@Module
public abstract class MemberFragmentModule {

    @Provides
    public static Context bindFragmentContext(MemberFragment memberFragment){
        return memberFragment.getContext();
    }

    @Binds
    @IntoMap
    @ViewModelKey(MemberListViewModel.class)
    public abstract ViewModel bindViewModel(MemberListViewModel memberListViewModel);


    @Binds
    public abstract ListAdapter.OnItemClickListener bindOnItemClickListiner(MemberFragment homeFragment);


}
