package de.testapp.view.partb;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.testapp.R;
import de.testapp.view.adapter.PartBAdapter;
import de.testapp.view.base.BaseFragment;
import de.testapp.view.maps.MapsFragment;
import de.testapp.view.parta.Parta;
import de.testapp.view.userprofil.UserProfileFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Partb extends BaseFragment implements PartBAdapter.BAdapterListener{

    @BindView(R.id.lat)
    EditText latValue;
    @BindView(R.id.lng)
    EditText lngValue;
    @BindView(R.id.recycler_partb)
    RecyclerView recyclerView;
    //SendMessage SM;

    PartBAdapter mAdapter = null;
    private PublishSubject<Float> mPublishSubject;

    public Partb() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_partb, container, false);
        ButterKnife.bind(this,v);

        //Recycler Init
        mAdapter = new PartBAdapter();
        mAdapter.setListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        //Create Observable for Input
        createObservable();

        //Recycler mit Daten füllen
        List<String> l1 = new ArrayList<>();
        l1.add("test");
        l1.add("test2");
        l1.add("test3");
        l1.add("test4");
        mAdapter.addItems(l1);
        mAdapter.notifyDataSetChanged();

        //Map als Child hinzufügen
        addChildFragment(R.id.frame2, new MapsFragment());
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapsTextEingeben();
    }

    public void mapsTextEingeben(){
        latValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0){
                    float number = 0;
                    try{
                        number = Float.valueOf(latValue.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    mPublishSubject.onNext(number);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void clickListener(int pos) {
        Log.i("adapter","click on Position "+pos);
    }

    public void createObservable(){
        mPublishSubject = PublishSubject.create();
        mPublishSubject
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(aFloat -> !aFloat.isNaN())
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Float>() {
                    @Override
                    public void onNext(Float aFloat) {
                        Log.i("maps","onNext: "+aFloat);
                      //  SM.sendLatLng(aFloat,5f);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /*
    public interface SendMessage{
        void sendLatLng(Float v1, Float v2);
    }*/


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }*/
    }

}
