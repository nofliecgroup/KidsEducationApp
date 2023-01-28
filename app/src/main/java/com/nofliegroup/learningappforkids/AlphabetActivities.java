package com.nofliegroup.learningappforkids;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlphabetActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

       String name = getIntent().getStringExtra("name").toLowerCase();
       Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        ImageView img = findViewById(R.id.imageView2);
        int id = getResources().getIdentifier(name, "drawable", getPackageName());
        img.setImageResource(id);

        int mid = getResources().getIdentifier(name, "raw", getPackageName());
        MediaPlayer mp = MediaPlayer.create(this, mid);
        mp.start();


    }
}