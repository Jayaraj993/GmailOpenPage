package com.emdsys.android.gmailopenpage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by EMD029 on 8/14/2015.
 */
public class collapsing_image extends AppCompatActivity {
    ImageView collapse_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing_layout);
        collapse_image= (ImageView) findViewById(R.id.collapsing_image);
        Intent i=getIntent();
        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("collapse_image");
        collapse_image.setImageBitmap(bmp);
    }
}
