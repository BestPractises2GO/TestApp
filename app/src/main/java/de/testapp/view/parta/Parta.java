package de.testapp.view.parta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.testapp.R;
import de.testapp.view.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Parta extends BaseFragment {


    public Parta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parta, container, false);
    }

}
