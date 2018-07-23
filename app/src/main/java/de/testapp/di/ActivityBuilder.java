package de.testapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.testapp.di.scope.PerActivity;
import de.testapp.view.HomeActivity;
import de.testapp.view.HomeActivityModule;

@Module
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity bindHomeActivity();
}
