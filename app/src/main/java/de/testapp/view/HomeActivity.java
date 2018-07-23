package de.testapp.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.testapp.R;
import de.testapp.view.base.BaseActivity;

public class HomeActivity extends BaseActivity {
    private static String USER_LOGIN = "JakeWharton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Fragment f1 = new Parta();
        Fragment f2 = new Partb();
        Fragment f3 = new UserProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(UserProfileFragment.UID_KEY, USER_LOGIN);
        f3.setArguments(bundle);


        if(savedInstanceState == null){
            addFragment(R.id.frame_parta,f1);
            addFragment(R.id.frame_partb,f2);
            addFragment(R.id.frame_user,f3);
        }

    }



}
