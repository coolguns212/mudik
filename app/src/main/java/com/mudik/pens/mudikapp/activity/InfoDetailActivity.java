package com.mudik.pens.mudikapp.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mudik.pens.mudikapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Silver on 7/14/2016.
 */
public class InfoDetailActivity extends Activity {
    String idd = "";
    TextView namaTxt,kotaTxt,alamatTxt,teleponTxt,websiteTxt,emailTxt,fbTxt,latTxt,longTxt;
    ImageView foto;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info_fragment);
        foto = (ImageView) this.findViewById(R.id.img_detailtempat);
        namaTxt = (TextView) this.findViewById(R.id.nama_detailtempat);
        kotaTxt = (TextView) this.findViewById(R.id.kota_detailtempat);
        alamatTxt = (TextView) this.findViewById(R.id.alamat_detailtempat);
        teleponTxt = (TextView) this.findViewById(R.id.telepon_detailtempat);
        websiteTxt = (TextView) this.findViewById(R.id.website_detailtempat);
        emailTxt = (TextView) this.findViewById(R.id.email_detailtempat);
        fbTxt = (TextView) this.findViewById(R.id.fb_detailtempat);
        latTxt = (TextView) this.findViewById(R.id.lat);
        longTxt = (TextView) this.findViewById(R.id.log);

        Bundle extre = getIntent().getExtras();
        idd = extre.getString("ids");
        new RetrieveFeedTask().execute();



    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
//            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
//            String email = emailText.getText().toString();
            // Do some validation here

            try {
                URL url = new URL("https://www.carlyshare.com/mudik/index.php?r=api/tempat/view&id="+idd);
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
//            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            //responseView.setText(response);
            String test = "";
            String name = null,ids,lat = null,log = null,kota = null,alamat=null,telf = null,webs = null,fb = null,im = null;
            String fofo=null;
            // TODO: check this.exception
            // TODO: do something with the feed

            try {

                JSONObject object2 = (JSONObject) new JSONTokener(response).nextValue();
                JSONArray jsonMainArr = object2.getJSONArray("data");
                for (int i = 0; i < jsonMainArr.length(); i++) {
                    JSONObject jsonobject = jsonMainArr.getJSONObject(i);
                    name = jsonobject.getString("nama");
                    ids = jsonobject.getString("id");
                    lat = jsonobject.getJSONObject("places").getString("lat");
                    log = jsonobject.getJSONObject("places").getString("log");
                    kota = jsonobject.getJSONObject("alamat").getString("kota");
                    alamat = jsonobject.getJSONObject("alamat").getString("alamat_lengkap");
                    telf = jsonobject.getString("telepon");
                    webs = jsonobject.getString("website");
                    im = jsonobject.getString("email");
                    fb = jsonobject.getJSONObject("social_media").getString("fb");
                    fofo = jsonobject.getString("foto");
                    fofo = fofo.replace("\\","");
                    webs = webs.replace("\\","");
                    //double lat = jsonobject.getDouble("lat");
                    //double log = jsonobject.getDouble("log");
                    //test += " " + name+" "+ lat +" "+log+"\n";
                    test += " " + name;
                }
//                responseView.setText(test);
                namaTxt.setText(test);
                longTxt.setText(log);
                latTxt.setText(lat);
                kotaTxt.setText(kota);
                alamatTxt.setText(alamat);
                teleponTxt.setText(telf);
                websiteTxt.setText(webs);
                emailTxt.setText(im);
                fbTxt.setText(fb);

                if(fofo!=null && !fofo.equalsIgnoreCase("")) {


                    Picasso.with(getBaseContext()).load(fofo).into(foto);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //responseView.setText(test);
        }
    }
}
