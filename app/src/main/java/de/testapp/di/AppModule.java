package de.testapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import de.testapp.di.scope.PerActivity;
import de.testapp.model.UserDao;
import de.testapp.model.UserDatabase;
import de.testapp.model.UserRepository;
import de.testapp.model.UserWebservice;
import de.testapp.view.App;
import de.testapp.view.HomeActivity;
import de.testapp.view.HomeActivityModule;
import de.testapp.view.base.BaseActivityModule;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = {ViewModelModule.class, AndroidSupportInjectionModule.class})
    public class AppModule{

    // --- DATABASE INJECTION ---

    @Provides
    @Singleton
    UserDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                UserDatabase.class, "UserDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(UserDatabase database) { return database.userDao(); }



    // --- REPOSITORY INJECTION ---

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }


/*  Doppelt gemoppelt ;) <--  return new UserRepository(w,u,e) greift auf Konstruktor(web, user, executor) zu
    @Provides
    @Singleton
    UserRepository provideUserRepository(UserWebservice webservice, UserDao userDao, Executor executor) {
        return new UserRepository(webservice, userDao, executor);
    }
*/

    // --- NETWORK INJECTION ---

    private static String BASE_URL = "https://api.github.com/";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    UserWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(UserWebservice.class);
    }


    @Provides
    Application provideApplication(App application){
        return application;
    };

    }

