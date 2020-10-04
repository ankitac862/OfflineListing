package com.anki.offlinelisting.viewmodel;


import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * ViewModel factory class which keeps all the viewmodel instances
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
        private final  Map<Class<? extends ViewModel>, Provider<ViewModel>> CREATORS ;
        @Inject
        public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
            CREATORS = creators;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            Provider<? extends ViewModel> creator = CREATORS.get(modelClass);
            if(creator == null){
                for(Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry: CREATORS.entrySet()){
                    if(modelClass.isAssignableFrom(entry.getKey())){
                        creator = entry.getValue();
                        break;
                    }
                }
            }
            if(creator==null){
                throw new IllegalStateException("Unknow Model Class : "+modelClass);
            }
            try{
                return (T)creator.get();
            }catch (Exception e){
              System.out.println("------Expection----->" +e);
                throw new RuntimeException();
            }

        }

}