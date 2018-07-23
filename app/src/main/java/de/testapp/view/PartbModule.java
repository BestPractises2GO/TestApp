package de.testapp.view;

import android.support.v4.app.Fragment;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import de.testapp.di.scope.PerFragment;
import de.testapp.view.base.BaseFragmentModule;

@Module(includes = BaseFragmentModule.class)
public abstract class PartbModule {

    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract Fragment fragment(Partb partb);
}
