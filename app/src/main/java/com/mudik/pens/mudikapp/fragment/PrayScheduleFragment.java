package com.mudik.pens.mudikapp.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mudik.pens.mudikapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rimawanti Fauzyah on 7/5/2016.
 */

public class PrayScheduleFragment extends android.support.v4.app.Fragment {
    String [] tempatArray = {"Surabaya","Semarang","Jakarta","Banten","Medan","Samarinda","Palembang","Bali","Balikpapan"};
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vResult    = inflater.inflate(R.layout.prayschedule_fragment, container, false);

        return  vResult;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView fajarTxt = (TextView) view.findViewById(R.id.fajar_txt);
        final TextView dhuhurTxt = (TextView) view.findViewById(R.id.duhur_txt);
        final TextView asharTxt = (TextView) view.findViewById(R.id.ashar_txt);
        final TextView maghribTxt = (TextView) view.findViewById(R.id.maghrib_txt);
        final TextView isyaTxt = (TextView) view.findViewById(R.id.isha_txt);

        final String[] urlplace = {""};
        final Spinner spinnerTempat = (Spinner) view.findViewById(R.id.place_spinner);

        //spinner pilih tempat
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item,tempatArray);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
                spinnerTempat.setAdapter(adapter);

        //looping
       /* for (int position = 0; position < adapter.getCount(); position++) {
            //if(adapter.getItem(position).equals(urlplace)) {
            urlplace[0] = String.valueOf(adapter.getItem(position));
            // }
        }*/


        spinnerTempat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = spinnerTempat.getSelectedItemPosition();
                urlplace[0] = tempatArray[index];
                //Toast.makeText(getActivity(), "urlplace: "+ urlplace[0], Toast.LENGTH_LONG).show();
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                // String url ="http://api.aladhan.com/timingsByCity?city=Surabaya&country=ID&method=1";
                String url ="http://api.aladhan.com/timingsByCity?city="+urlplace[0]+"&country=ID&method=1";


                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @TargetApi(Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onResponse(String response) {

                                JSONObject jResponse = null;
                                try {
                                    jResponse = new JSONObject(response);
                                    JSONObject jData = jResponse.getJSONObject("data");
                                    JSONObject jTiming = jData.getJSONObject("timings");
                                    String fajar = jTiming.getString("Fajr");
                                    String dhuhur = jTiming.getString("Dhuhr");
                                    String ashar = jTiming.getString("Asr");
                                    String maghrib = jTiming.getString("Maghrib");
                                    String isya = jTiming.getString("Isha");

                                    fajarTxt.setText(fajar);
                                    dhuhurTxt.setText(dhuhur);
                                    asharTxt.setText(ashar);
                                    maghribTxt.setText(maghrib);
                                    isyaTxt.setText(isya);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "It did not works!! ", Toast.LENGTH_SHORT).show();
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
