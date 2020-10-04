package com.anki.offlinelisting.databinding;


import com.anki.offlinelisting.local.entity.Member;
import com.anki.offlinelisting.remote.Resource;
import com.anki.offlinelisting.view.adapter.ListAdapter;


import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Binding adapters
 */
final class ListBindingAdapter {

    private ListBindingAdapter(){
        // Private Constructor to hide the implicit one
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof ListAdapter){
            ((ListAdapter)adapter).setData((ArrayList<Member>) resource.data);
        }
    }

}
