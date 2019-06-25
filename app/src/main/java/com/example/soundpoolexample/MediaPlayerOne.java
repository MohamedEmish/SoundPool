package com.example.soundpoolexample;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MediaPlayerOne extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button videoPlayerGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_one);

        videoPlayerGo =findViewById(R.id.video_player_go);
        videoPlayerGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaPlayerOne.this, VideoPlayerOne.class);
                startActivity(intent);
            }
        });
    }

    public void playMedia(View v) {

        switch (v.getId()) {
            case R.id.play:
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.audio1);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if (mediaPlayer!=null){
                                mediaPlayer.release();
                                mediaPlayer=null;
                            }
                        }
                    });
                }
                mediaPlayer.start();
                break;
            case R.id.pause:
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mediaPlayer!=null){
                    mediaPlayer.release();
                    mediaPlayer=null;
                }
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
