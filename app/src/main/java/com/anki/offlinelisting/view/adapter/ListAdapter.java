package com.anki.offlinelisting.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anki.offlinelisting.databinding.ItemCardViewBinding;
import com.anki.offlinelisting.local.entity.Member;
import com.anki.offlinelisting.viewmodel.MemberListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MemberViewHolder> {
    MemberListViewModel memberListViewModel;
    private ArrayList<Member> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @Inject
    public ListAdapter(OnItemClickListener onItemClickListener , MemberListViewModel memberListViewModel ){
        this.onItemClickListener = onItemClickListener;
        this.memberListViewModel = memberListViewModel;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MemberViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder viewHolder, int position) {
        viewHolder.onBind(list.get(position) , memberListViewModel);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(ArrayList<Member> entities) {
        this.list = entities;
        notifyDataSetChanged();
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {

        private static MemberViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemCardViewBinding itemMovieListBinding = ItemCardViewBinding.inflate(inflater, parent, false);
            return new MemberViewHolder(itemMovieListBinding);
        }

        final ItemCardViewBinding binding;

        private MemberViewHolder(ItemCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(Member member ,  MemberListViewModel memberListViewModel) {
            binding.setItem(member);
            binding.setViewModel(memberListViewModel);
            binding.executePendingBindings();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int status , Member member);
    }

}
