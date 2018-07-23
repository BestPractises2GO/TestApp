package de.testapp.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;


import dagger.android.AndroidInjector;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;
import de.testapp.di.DaggerAppComponent;


public class App extends DaggerApplication{
    /*
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    */
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().create(this).inject(this);
        context = getApplicationContext();
    }

    /*
    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }*/

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
