package de.testapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class UserDatabase extends RoomDatabase{

    // --- SINGLETON ---
    private static volatile UserDatabase INSTANCE;

    // --- DAO ---
    public abstract UserDao userDao();
}
