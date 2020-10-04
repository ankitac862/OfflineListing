package com.anki.offlinelisting.local;

import android.content.Context;

import com.anki.offlinelisting.local.dao.MemberDao;
import com.anki.offlinelisting.local.entity.Member;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
/**
 * Room Db create
 */
@Database(entities = {Member.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
    private static final String DB_NAME = "OF_DB";
    private static AppDb appDb;

    public static AppDb getAppDb(Context context) {
        if (appDb == null) {
            synchronized (AppDb.class) {
                if (appDb == null) {
                    appDb = Room.databaseBuilder(context, AppDb.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return appDb;
    }

    public abstract MemberDao memberDao();
}
