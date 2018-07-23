package de.testapp.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import de.testapp.App;

@Singleton
@Component(modules = {AppModule.class, ActivityBuilder.class})
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {}
}