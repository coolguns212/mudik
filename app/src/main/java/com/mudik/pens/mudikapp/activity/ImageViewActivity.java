package com.mudik.pens.mudikapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.mudik.pens.mudikapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Silver on 7/15/2016.
 */
public class ImageViewActivity extends Activity {
    ImageView imageview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.imageviewlayout);
        Bundle extre = getIntent().getExtras();

        // Insert your Video URL
        String url = extre.getString("ids");
        imageview = (ImageView) this.findViewById(R.id.gambar);
        Picasso.with(getBaseContext()).load(url).into(imageview);

    }

}
