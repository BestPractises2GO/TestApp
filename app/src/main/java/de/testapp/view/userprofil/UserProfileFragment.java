package de.testapp.view.userprofil;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.testapp.R;
import de.testapp.model.User;
import de.testapp.view.UserProfileViewModel;
import de.testapp.view.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends BaseFragment {
    // FOR DATA
    public static final String UID_KEY = "uid";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UserProfileViewModel viewModel;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    // FOR DESIGN
    @BindView(R.id.fragment_user_profile_image)
    ImageView imageView;
    @BindView(R.id.fragment_user_profile_username)
    TextView username;
    @BindView(R.id.fragment_user_profile_company)
    TextView company;
    @BindView(R.id.fragment_user_profile_website)
    TextView website;
    @BindView(R.id.swipe_container_user_profile)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    String userLogin;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null) {
             userLogin = getArguments().getString(UID_KEY);
        }else{
             userLogin = "null";
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserProfileViewModel.class);
        viewModel.init(userLogin);
        viewModel.getUser().observe(this, user -> updateUI(user));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.init(userLogin);
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable User user){
        if (user != null){
            String sponge_gif = "https://thumbs.gfycat.com/ReasonableHauntingCormorant-max-1mb.gif";
            Glide.with(this).load(sponge_gif).apply(RequestOptions.circleCropTransform()).into(imageView);
            this.username.setText(user.getName());
            this.company.setText(user.getCompany());
            this.website.setText(user.getBlog());
        }
    }
}
