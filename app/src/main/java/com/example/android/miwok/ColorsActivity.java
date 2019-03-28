package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);


        // Create a list of words
        final ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("red", "weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        words.add(new Words("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new Words("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Words("green", "chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Words("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Words("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Words("black", "kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Words("white", "kelelli",R.drawable.color_white,R.raw.color_white));


        WordsAdaptor adapter = new WordsAdaptor(this, words,R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words w=words.get(position);
                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, w.getmAudioresId());

                // Start the audio file
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaplayer();
                    }
                });
            }
        });

    }
    public void releaseMediaplayer(){ if (mMediaPlayer != null){
        mMediaPlayer.release();
        mMediaPlayer=null;
    }}


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaplayer();
    }
}
