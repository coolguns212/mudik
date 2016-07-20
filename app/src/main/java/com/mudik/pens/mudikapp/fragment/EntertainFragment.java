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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mudik.pens.mudikapp.R;
import com.mudik.pens.mudikapp.activity.ImageViewActivity;
import com.mudik.pens.mudikapp.activity.InfoDetailActivity;
import com.mudik.pens.mudikapp.activity.VideoViewActivity;
import com.mudik.pens.mudikapp.adapter.MediaAdapter;
import com.mudik.pens.mudikapp.adapter.PlaceAdapter;
import com.mudik.pens.mudikapp.model.Media;
import com.mudik.pens.mudikapp.model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rimawanti Fauzyah on 7/5/2016.
 */
public class EntertainFragment extends android.support.v4.app.Fragment {
    private List<Media> listMedia = new ArrayList<>();;
    private MediaAdapter listAdaptorMedia= null;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vResult    = inflater.inflate(R.layout.entertain_fragment, container, false);
       /* TextView text = new TextView(container.getContext());
        text.setText("Fragment content");
        text.setGravity(Gravity.CENTER);*/

        return  vResult;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //list place CONTOH
        //listMedia = new ArrayList<>();
//        listMedia.add(new Media("Video 1","https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4"));
//        listMedia.add(new Media("Video 2","https://www.youtube.com/watch?v=upueueKL1Z0"));
//        listMedia.add(new Media("Video 3","https://www.youtube.com/watch?v=LYpmybdzBmk"));
        //listMedia.add(new Media("Video 3","https://www.youtube.com/watch?v=LYpmybdzBmk",67));
        new RetrieveFeedTask().execute();
        ListView m = (ListView) view.findViewById(R.id.medialistView);
        Button b = (Button) view.findViewById(R.id.buttonrep);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });

        listAdaptorMedia = new MediaAdapter(getActivity(),
                R.layout.media_list_view, listMedia);
        m.setAdapter(listAdaptorMedia);
        m.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView)view.findViewById(R.id.media_id)).getText().toString();
                String kat = ((TextView)view.findViewById(R.id.medialist_label_nama)).getText().toString();

                //Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
                if(kat.equalsIgnoreCase("Video")){
                    Intent i = new Intent(getActivity(),VideoViewActivity.class);
                    i.putExtra("ids",item);
                    getActivity().startActivity(i);
                }else if(kat.equalsIgnoreCase("Gambar")){
                    Intent i = new Intent(getActivity(),ImageViewActivity.class);
                    i.putExtra("ids",item);
                    getActivity().startActivity(i);
                } else {
                    Toast.makeText(getContext(), "Tidak Didukung", Toast.LENGTH_LONG).show();
                }


                //Intent i = new Intent(getActivity(),InfoDetailActivity.class);
                //i.putExtra("ids",item);
                //getActivity().startActivity(i);

            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
                URL url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/hiburan/index");
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
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            //responseView.setText(response);
            String test = "kosong";
            // TODO: check this.exception
            // TODO: do something with the feed

            try {

                JSONObject object2 = (JSONObject) new JSONTokener(response).nextValue();
                JSONArray jsonMainArr = object2.getJSONArray("data");
                for (int i = 0; i < jsonMainArr.length(); i++) {
                    JSONObject jsonobject = jsonMainArr.getJSONObject(i);
                    String name = jsonobject.getString("judul");
                    String cat = jsonobject.getString("kategori");
                    String id = jsonobject.getString("konten");
                    id=id.replace("\\","");
                    //int idd = Integer.parseInt(id);
                    //double lat = jsonobject.getDouble("lat");
                    //double log = jsonobject.getDouble("log");
                    //test += " " + name+" "+ lat +" "+log+"\n";
                    //test += " " + name+" "+haa;
                    listMedia.add(new Media(cat,name,id));
                    //listMedia.add(new Media("lol","sad",2));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //responseView.setText(test);
        }
    }


}
