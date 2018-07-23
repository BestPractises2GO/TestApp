package de.testapp.model.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import de.testapp.model.DataConverter;
import de.testapp.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class UserDatabase extends RoomDatabase{

    // --- SINGLETON ---
    private static volatile UserDatabase INSTANCE;

    // --- DAO ---
    public abstract UserDao userDao();
}
