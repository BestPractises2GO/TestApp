package de.testapp.view.maps;

import android.support.v4.app.Fragment;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import de.testapp.di.scope.PerFragment;
import de.testapp.view.base.BaseFragmentModule;
import de.testapp.view.parta.Parta;

@Module(includes = BaseFragmentModule.class)
public abstract class MapsFragmentModule {

    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract Fragment fragment(MapsFragment mapsFragment);
}
