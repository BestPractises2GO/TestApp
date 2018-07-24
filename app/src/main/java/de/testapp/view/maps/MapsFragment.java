package de.testapp.view.maps;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.testapp.R;
import de.testapp.view.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends BaseFragment implements OnMapReadyCallback {
    @BindView(R.id.edit_lat)
    EditText lat;
    @BindView(R.id.edit_lng)
    EditText lng;
    GoogleMap myMap;
    MapView mMapView;
    private View rootView;

    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            this.rootView = inflater.inflate(R.layout.fragment_maps, container, false);
            ButterKnife.bind(this, rootView);
            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        } catch (InflateException e) {
            Log.e("maps", "Inflate exception");
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextHandler();
    }

    public void editTextHandler(){
        lat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0){
                    float number = 0;
                    try{
                        number = Float.valueOf(lat.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    if(!lng.getText().toString().isEmpty() && !Float.valueOf(lng.getText().toString()).isNaN()) {
                        setMarker(number, Float.valueOf(lng.getText().toString()));
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        lng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0){
                    float number = 0;
                    try{
                        number = Float.valueOf(lng.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    if(!lat.getText().toString().isEmpty() && !Float.valueOf(lat.getText().toString()).isNaN()) {
                        setMarker(Float.valueOf(lat.getText().toString()), number);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setMarker(Float lat, Float lng){
        if(myMap != null) {
            if(!lat.isNaN() && !lng.isNaN()) {
                LatLng sydney = new LatLng(lat, lng);
                myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Bochum"));
                myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
        }
    }


    public void displayReceivedData(Float v1, Float v2)
    {
        Log.i("maps",v1.toString());

        if(myMap != null) {
            LatLng sydney = new LatLng(51.4818445, 7.216);
            myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Bochum"));
            myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

        /*
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
*/
    }
}
