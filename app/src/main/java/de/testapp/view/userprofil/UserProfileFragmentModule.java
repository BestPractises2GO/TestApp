package de.testapp.view.userprofil;

import android.support.v4.app.Fragment;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import de.testapp.di.scope.PerFragment;
import de.testapp.view.base.BaseFragmentModule;

@Module(includes = BaseFragmentModule.class)
public abstract class UserProfileFragmentModule {

    @PerFragment
    @Named(BaseFragmentModule.FRAGMENT)
    @Binds
    abstract Fragment fragment(UserProfileFragment userProfileFragment);
}
