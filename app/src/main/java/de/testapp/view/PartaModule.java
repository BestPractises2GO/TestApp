package de.testapp.view;

import android.support.v4.app.Fragment;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.testapp.di.scope.PerFragment;
import de.testapp.view.base.BaseFragmentModule;

@Module(includes = BaseFragmentModule.class)
public abstract class PartaModule {

    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract Fragment fragment(Parta parta);

}
