package de.testapp.view.base;

import android.app.Activity;

import android.app.Application;
import android.content.Context;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.testapp.di.scope.PerActivity;
import de.testapp.view.HomeActivity;

@Module
public abstract class BaseActivityModule {

    static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";


    @Binds
    @PerActivity
    abstract Context activityContext(AppCompatActivity activity);


    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @PerActivity
    static FragmentManager activityFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
