package com.anki.offlinelisting.remote.repository;


import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.anki.offlinelisting.di.annotation.ActivityScope;
import com.anki.offlinelisting.local.dao.MemberDao;
import com.anki.offlinelisting.local.entity.Member;
import com.anki.offlinelisting.remote.APIService;
import com.anki.offlinelisting.remote.NetworkBoundResource;
import com.anki.offlinelisting.remote.Resource;
import com.anki.offlinelisting.remote.pojo.ListingModule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.Call;

/**
 * The Member repository which has access to local and remote data fetching services
 */
@ActivityScope
public class MemberRepository {

    private final MemberDao memberDao;
    private final APIService apiService;

    @Inject
    MemberRepository(MemberDao dao, APIService service) {
        this.memberDao = dao;
        this.apiService = service;
    }

    /**
     * This method fetches the member listing from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     *
     * @param howfarback index indicating how far back
     * @return List of members
     */
    public LiveData<Resource<List<Member>>> loadData(String howfarback, boolean toRefresh) {
        return new NetworkBoundResource<List<Member>, ListingModule>(toRefresh) {
            @Override
            protected void saveCallResult(ListingModule item) {
                if (null != item) {
                    for (Member items : item.getResults()) {
                        Date date = new Date(System.currentTimeMillis());
                        items.setDate(date);
                        memberDao.insertData(items);
                    }
                }
            }

            @NonNull
            @Override
            protected LiveData<List<Member>> loadFromDb(boolean toRefresh) {
                if (toRefresh)
                    return memberDao.loadDataDesc();
                else
                    return memberDao.loadData();
            }

            @NonNull
            @Override
            protected Call<ListingModule> createCall() {
                return apiService.getListData(howfarback);
            }

        }.getAsLiveData();
    }

    public void updateData(Member data, int val) {
        new AsyncTask<Void, Void, Void>() {
            int status;
            Member member;

            @Override
            protected Void doInBackground(Void... params) {
                memberDao.update(member.getMemberId(), status);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                status = val;
                member = data;
            }


            @Override
            protected void onPostExecute(Void needUpdate) {

            }
        }.execute();


    }


}
