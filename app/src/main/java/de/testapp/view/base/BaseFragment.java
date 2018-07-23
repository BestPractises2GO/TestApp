package de.testapp.view.base;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DaggerDialogFragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;
import dagger.android.support.HasSupportFragmentInjector;

public class BaseFragment extends DaggerFragment{
    @Inject
    protected Context activityContext;

    // Note that this should not be used within a child fragment.
    @Inject
    @Named(BaseFragmentModule.CHILD_FRAGMENT_MANAGER)
    protected FragmentManager childFragmentManager;

    /*
    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;
    */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        // Perform injection here for versions before M as onAttach(*Context*) did not yet exist
        // This fixes DaggerFragment issue: https://github.com/google/dagger/issues/777
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(*Activity*)
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(context);
    }

    /*
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }*/

    protected final void addChildFragment(@IdRes int containerViewId, Fragment fragment){
        childFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }
}
