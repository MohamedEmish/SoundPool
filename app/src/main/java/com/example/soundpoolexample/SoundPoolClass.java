package com.example.soundpoolexample;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundPoolClass extends AppCompatActivity {

    SoundPool soundPool;
    int sound1,sound2,sound3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);

        Button mediaGo = findViewById(R.id.media_player_go);
        mediaGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SoundPoolClass.this,MediaPlayerOne.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();

        }else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }

        sound1 = soundPool.load(this,R.raw.audio1,1);
        sound2 = soundPool.load(this,R.raw.audio2,1);
        sound3 = soundPool.load(this,R.raw.audio3,1);
    }

    public void playSound(View v){
        switch (v.getId()){
            case R.id.audio_1_button:
                soundPool.play(sound1,1,1,0,0,1);
                break;
            case R.id.audio_2_button:
                soundPool.play(sound2,1,1,0,0,1);
                break;
            case R.id.audio_3_button:
                soundPool.play(sound3,1,1,0,0,1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
