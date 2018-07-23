package de.testapp.view.parta;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.testapp.R;
import de.testapp.view.MapsActivity;
import de.testapp.view.base.BaseFragment;
import de.testapp.view.partb.Partb;


/**
 * A simple {@link Fragment} subclass.
 */
public class Parta extends BaseFragment {

    @BindView(R.id.button_go_map)
    Button button;
    public Parta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_parta, container, false);
        ButterKnife.bind(this,view);
        addChildFragment(R.id.frame1, new Partb());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(view1 -> {
         Intent intent = new Intent(getActivity(), MapsActivity.class);
         startActivity(intent);
        });
    }
}
