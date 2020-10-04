package com.anki.offlinelisting.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anki.offlinelisting.R;
import com.anki.offlinelisting.databinding.FragmentListBinding;
import com.anki.offlinelisting.local.entity.Member;
import com.anki.offlinelisting.remote.Resource;
import com.anki.offlinelisting.view.adapter.ListAdapter;
import com.anki.offlinelisting.viewmodel.MemberListViewModel;
import com.anki.offlinelisting.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

public class MemberFragment extends DaggerFragment implements ListAdapter.OnItemClickListener{
    @Inject
    ViewModelFactory viewModelFactory;

    MemberListViewModel viewModel;

    LinearLayoutManager linearLayoutManager;

    @Inject
    Provider<LinearLayoutManager> linearLayoutManagerProvider;

    @Inject
    ListAdapter adapter;

    FragmentListBinding binding;

    public static MemberFragment newInstance() {
        Bundle args = new Bundle();
        MemberFragment fragment = new MemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MemberListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linearLayoutManager = linearLayoutManagerProvider.get();
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        binding.swipeContainer.setOnRefreshListener(() -> {
            binding.swipeContainer.setRefreshing(true);
            getData(true);
        });
        getData(false);
    }
    Observer<Resource<List<Member>>> observer = listResource -> {
        binding.swipeContainer.setRefreshing(false);
        Log.d("TAG" , "onChange");
        if (binding.itemProgressBar.getVisibility() == View.VISIBLE){
            binding.itemProgressBar.setVisibility(View.GONE);
        }
        if (listResource.getMessage() == null){
            adapter.setData((ArrayList<Member>) listResource.data);
            // If the cached data is already showing then no need to show the error
            if (null != binding.recyclerView.getAdapter() && binding.recyclerView.getAdapter().getItemCount() > 0) {
                binding.errorText.setVisibility(View.GONE);
            }
        }else{
            Toast.makeText(getActivity() ,listResource.getMessage() , Toast.LENGTH_SHORT).show();
        }
    };
    private void getData(boolean isRefresh){
        viewModel.getData("10" , isRefresh);
        viewModel.getMemberLiveData().observe(this, observer);

    }
    @Override
    public void onItemClick(int status, Member member) {

    }

}
