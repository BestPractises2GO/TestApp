package de.testapp.view;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.testapp.di.scope.PerActivity;
import de.testapp.di.scope.PerFragment;
import de.testapp.view.base.BaseActivityModule;
import de.testapp.view.parta.Parta;
import de.testapp.view.parta.PartaModule;
import de.testapp.view.partb.Partb;
import de.testapp.view.partb.PartbModule;
import de.testapp.view.userprofil.UserProfileFragment;
import de.testapp.view.userprofil.UserProfileFragmentModule;

@Module(includes = BaseActivityModule.class)
public abstract class HomeActivityModule {

    /**
     * Provides the injector for the {@link Parta}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = PartaModule.class)
    abstract Parta bindParta();

    @PerFragment
    @ContributesAndroidInjector(modules = PartbModule.class)
    abstract Partb bindPartb();

    @PerFragment
    @ContributesAndroidInjector(modules = UserProfileFragmentModule.class)
    abstract UserProfileFragment bindUserProfilFragment();

    @Binds
    @PerActivity
    abstract AppCompatActivity activity(HomeActivity activity);
}
