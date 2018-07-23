package de.testapp.view.partb;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.testapp.R;
import de.testapp.view.adapter.PartBAdapter;
import de.testapp.view.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Partb extends BaseFragment implements PartBAdapter.BAdapterListener{


    PartBAdapter mAdapter = null;
    RecyclerView recyclerView = null;
    public Partb() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_partb, container, false);

        recyclerView = v.findViewById(R.id.recycler_partb);
        mAdapter = new PartBAdapter();
        mAdapter.setListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        List<String> l1 = new ArrayList<>();
        l1.add("test");
        l1.add("test2");
        l1.add("test3");
        l1.add("test4");
        mAdapter.addItems(l1);
        mAdapter.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void clickListener(int pos) {
        Log.i("adapter","click on Position "+pos);
    }
}
