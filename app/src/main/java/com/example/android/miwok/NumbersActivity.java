package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class NumbersActivity extends AppCompatActivity {
   MediaPlayer mMediaPlayer;
   private AudioManager mAudioManager;
   AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener= new AudioManager.OnAudioFocusChangeListener() {
      //  @Override
       public void onAudioFocusChange(int focusChange) {
           if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
               //pause playback
               mMediaPlayer.pause();
               mMediaPlayer.seekTo(0);
           }else if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
               mMediaPlayer.pause();
               mMediaPlayer.seekTo(0);
           }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
               //resume playback
               mMediaPlayer.start();
           }else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                 releaseMediaplayer();
           }

           }
       };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        //Array list
       final ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Words("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Words("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Words("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Words("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Words("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Words("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Words("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Words("nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Words("ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));


        WordsAdaptor itemsAdapter = new WordsAdaptor(this,words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words w=words.get(position);
                releaseMediaplayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                   // mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, w.getmAudioresId());

                    // Start the audio file
                    mMediaPlayer.start();
                }
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
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }}


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaplayer();
    }
}
