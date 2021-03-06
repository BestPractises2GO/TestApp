package de.testapp.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.testapp.R;
import de.testapp.view.adapter.FragmentTabAdapter;
import de.testapp.view.base.BaseActivity;
import de.testapp.view.maps.MapsFragment;
import de.testapp.view.parta.Parta;
import de.testapp.view.partb.Partb;
import de.testapp.view.userprofil.UserProfileFragment;

public class HomeActivity extends BaseActivity {
    private static String USER_LOGIN = "JakeWharton";
    @BindView(R.id.viewpager_home)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;


    private FragmentTabAdapter adapterTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //Tablayout + ViewPager
        viewPager.setOffscreenPageLimit(4);
        adapterTab = new FragmentTabAdapter(getSupportFragmentManager());
        addFragmentsToViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    //Tabs: Fragments werden durch Slide oder Click aufgerufen
    private void addFragmentsToViewPager(ViewPager viewPager) {
        adapterTab.addFragment(new Parta(), "Part1");
        adapterTab.addFragment(new Partb(), "Part2");
        Fragment f3 = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(UserProfileFragment.UID_KEY, USER_LOGIN);
        f3.setArguments(bundle);

        adapterTab.addFragment(f3, "Profil");
        adapterTab.addFragment(new MapsFragment(), "Map");
        viewPager.setAdapter(adapterTab);
    }

/* Methode von Interface von Fragment PartB und Daten von PartB zum Fragment MapsFragment zu senden
    @Override
    public void sendLatLng(Float v1, Float v2) {
        String tag = "android:switcher:" + R.id.viewpager_home + ":" + 3;
        MapsFragment mapsFragment = (MapsFragment) getSupportFragmentManager().findFragmentByTag(tag);
        mapsFragment.displayReceivedData(v1,v2);
    } */
}
