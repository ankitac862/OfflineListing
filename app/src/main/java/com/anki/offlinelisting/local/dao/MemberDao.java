package com.anki.offlinelisting.local.dao;

import com.anki.offlinelisting.local.entity.Member;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Dao Class
 */

@Dao
public interface MemberDao {

    @Insert()
    void insertData(Member member);

    @Query("SELECT * FROM member ORDER BY date")
    LiveData<List<Member>> loadData();

    @Query("SELECT * FROM member ORDER BY date DESC")
    LiveData<List<Member>> loadDataDesc();

    @Query("UPDATE member SET status=:status WHERE memberId = :id")
    void update(long id , int status);

}
