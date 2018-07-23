package de.testapp.model;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.testapp.App;
import de.testapp.model.local.UserDao;
import de.testapp.model.remote.UserWebservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository {
    private static int FRESH_TIMEOUT_IN_MINUTES = 3;


    private final UserWebservice webservice;
    private final UserDao userDao;
    private final Executor executor;


    @Inject
    public UserRepository(UserWebservice webservice, UserDao userDao, Executor executor) {
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }

    //Lade Daten von REST API wenn es die Regel zulässt, und dann speicher Daten in offline Datenbank
    //Zum Schluß --> lade immer von offline Datenbank
    public LiveData<User> getUser(String userLogin) {
        refreshUser(userLogin); // try to refresh data if possible from Github Api
        return userDao.load(userLogin); // return a LiveData directly from the database.
    }


    private void refreshUser(final String userLogin) {
        executor.execute(() -> {
            // Check if user was fetched recently
            boolean userExists = (userDao.hasUser(userLogin, getMaxRefreshTime(new Date())) != null);
            // If user have to be updated
            if (!userExists) {
                webservice.getUser(userLogin).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            User user = response.body();
                            user.setLastRefresh(new Date());
                            userDao.save(user);
                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) { }
                });
            }else{
              Log.i("database","offline Data loaded, no refresh!");
            }
        });
    }

    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }

}
