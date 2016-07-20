package com.mudik.pens.mudikapp.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.maps.android.MarkerManager;
import com.google.maps.android.PolyUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;
import com.mudik.pens.mudikapp.R;
import com.mudik.pens.mudikapp.activity.InfoDetailActivity;
import com.mudik.pens.mudikapp.activity.TabActivity;
import com.mudik.pens.mudikapp.adapter.CategoryAdapter;
import com.mudik.pens.mudikapp.adapter.PlaceAdapter;
import com.mudik.pens.mudikapp.model.GPSTracker;
import com.mudik.pens.mudikapp.model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rimawanti Fauzyah on 5/26/2016.
 */
public class InfoFragment extends android.support.v4.app.Fragment {
    private List<Place> listPlace = new ArrayList<>();;
    private PlaceAdapter listAdaptorPlace= null;
    ListView l;
    URL url = null;


    // stores the image database icons
    private static Integer[] catIcon = { R.drawable.kosong,R.drawable.ic_masjid,
            R.drawable.ic_pom, R.drawable.ic_home, R.drawable.ic_cek,
            R.drawable.ic_home };

    // stores the image database names
    private String[] catName = { "--- Pilih Kategori ---","Terminal Bus", "Restoran","Masjid", "Kantor Polisi", "Stasiun Kereta","Bandara","SPBU","Bank","Rumah Sakit"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vResult    = inflater.inflate(R.layout.info_fragment, container, false);

        return  vResult;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //list place CONTOH

//        listPlace.add(new Place("ATM Mandiri","1.084 km dari tempat anda",R.drawable.ic_home));
//        listPlace.add(new Place("ATM BNI SYARIAH","1.085 km dari tempat anda",R.drawable.ic_home));
//        listPlace.add(new Place("ATM Bank Danamon","1.084 km dari tempat anda",R.drawable.ic_home));


        l = (ListView) view.findViewById(R.id.placelistView);
        listAdaptorPlace = new PlaceAdapter(getActivity(),
                R.layout.place_list_view, listPlace);

        //kategori
        Spinner spinner = (Spinner) view.findViewById(R.id.cat);

        CategoryAdapter adapter = new CategoryAdapter(getActivity(), R.layout.category_list_view, catName, catIcon);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), selectedItem, Toast.LENGTH_LONG).show();
                listPlace.clear();
                listAdaptorPlace.clear();
                if(selectedItem.equals("Terminal Bus"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=1");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else
                if(selectedItem.equals("Restoran"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=2");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else
                if(selectedItem.equals("Masjid"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=3");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else
                if(selectedItem.equals("Kantor Polisi"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=4");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else
                if(selectedItem.equals("Stasiun Kereta"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=5");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else
                if(selectedItem.equals("Bandara"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=6");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else
                if(selectedItem.equals("SPBU"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=7");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else
                if(selectedItem.equals("Bank"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=8");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else
                if(selectedItem.equals("Rumah Sakit"))
                {
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/viewByKat&id=10");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/index");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                new RetrieveFeedTask().execute();
                l.setAdapter(listAdaptorPlace);
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView)view.findViewById(R.id.list_id)).getText().toString();

                //Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(),InfoDetailActivity.class);
                i.putExtra("ids",item);
                getActivity().startActivity(i);

            }
        });
    }

    public class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            // progressBar.setVisibility(View.VISIBLE);
            //responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            //String email = emailText.getText().toString();
            // Do some validation here

            try {
                //url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/index");

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            //progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            //responseView.setText(response);
            String test="kosong";
            // TODO: check this.exception
            // TODO: do something with the feed

            try {

                JSONObject object2 = (JSONObject) new JSONTokener(response).nextValue();
                JSONArray jsonMainArr = object2.getJSONArray("data");
                GPSTracker tete = new GPSTracker(getContext());
                for (int i=0;i<jsonMainArr.length();i++) {
                    JSONObject jsonobject = jsonMainArr.getJSONObject(i);
                    String name = jsonobject.getString("nama");
                    String ids = jsonobject.getString("id");
                    String lat = jsonobject.getJSONObject("places").getString("lat");
                    String log = jsonobject.getJSONObject("places").getString("log");
                    double aaa = Double.parseDouble(lat);
                    double bb = Double.parseDouble(log);
                    int idd = Integer.parseInt(ids);
                    //new LatLng();
                    tete.getMyLocation();
                    final double a = tete.getLatitude();
                    final double b = tete.getLongitude();
                    double aa=SphericalUtil.computeDistanceBetween(new LatLng(a,b),new LatLng(aaa,bb));
                    listPlace.add(new Place(name,aa,R.drawable.ic_home,idd));
                }
                //responseView.setText(test);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //responseView.setText(test);
        }
    }



}