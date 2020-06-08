package com.example.appnghenhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnghenhac.Adapter.ViewPagerPlaylistnhac;
import com.example.appnghenhac.Fragment.DiaNhacFragment;
import com.example.appnghenhac.Fragment.PlayDanhSachCacBaiHatFragment;
import com.example.appnghenhac.Model.BaiHat;
import com.example.appnghenhac.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.SimpleTimeZone;

public class PlayMusicActivity extends AppCompatActivity {

    BaiHat baiHat;


    Toolbar toolbar;
    TextView textView_TimeSong,textView_Totaltimesong;
    SeekBar seekBar;
    ImageButton imageButton_Play,imageButton_Repeat,imageButton_Next,imageButton_Pre,imageButton_Random;
    ViewPager viewPager_Playnhac;

    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    DiaNhacFragment fragment_dianhac;
    PlayDanhSachCacBaiHatFragment fragment_playdanhsachcacbaihat;
    MediaPlayer mediaPlayer;

    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        init();
        getDataIntent();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1)!= null){
                    if (mangbaihat.size()>0){
                        fragment_dianhac.Playnhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);

                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imageButton_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageButton_Play.setImageResource(R.drawable.iconplay);

                    if (fragment_dianhac.objectAnimator!=null){
                        fragment_dianhac.objectAnimator.pause();
                    }
                }else {
                    mediaPlayer.start();
                    imageButton_Play.setImageResource(R.drawable.iconpause);
                    if (fragment_dianhac.objectAnimator!=null){
                        fragment_dianhac.objectAnimator.resume();
                    }
                }


            }
        });
        imageButton_Repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false){
                    if (checkrandom == true){
                        checkrandom = false;
                        imageButton_Repeat.setImageResource(R.drawable.iconsyned);
                        imageButton_Random.setImageResource(R.drawable.iconsuffle);
                    }
                    imageButton_Repeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;

                }else {
                    imageButton_Repeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageButton_Random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false){
                    if (repeat == true){
                        repeat = false;
                        imageButton_Repeat.setImageResource(R.drawable.iconshuffled);
                        imageButton_Repeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imageButton_Random.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;

                }else {
                    imageButton_Random.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageButton_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size()>0){
                    if (mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer= null;

                    }
                    if (position< (mangbaihat.size())){
                        imageButton_Play.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true){
                            if (position ==0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position =  index -1;

                            }
                            position  = index;
                        }
                        if (position> (mangbaihat.size()-1 )){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imageButton_Pre.setClickable(false);
                imageButton_Next.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButton_Pre.setClickable(true);
                        imageButton_Next.setClickable(true);
                    }
                },5000);
            }
        });
        imageButton_Pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size()>0){
                    if (mediaPlayer.isPlaying() || mediaPlayer !=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer= null;

                    }
                    if (position< (mangbaihat.size())){
                        imageButton_Play.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position<0){
                            position = mangbaihat.size() -1;
                        }
                        if (repeat == true){

                            position += 1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position =  index -1;

                            }
                            position  = index;
                        }

                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imageButton_Pre.setClickable(false);
                imageButton_Next.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButton_Pre.setClickable(true);
                        imageButton_Next.setClickable(true);
                    }
                },5000);

            }
        });
    }


    private void init() {
        toolbar = findViewById(R.id.toolbarplaynhac);
        textView_TimeSong = findViewById(R.id.textviewtimesong);
        textView_Totaltimesong = findViewById(R.id.textviewtitletimesong);
        seekBar = findViewById(R.id.seebarsong);
        imageButton_Play = findViewById(R.id.imagebuttonplay);
        imageButton_Next = findViewById(R.id.imagebuttonnext);
        imageButton_Pre = findViewById(R.id.imagebuttonpre);
        imageButton_Random = findViewById(R.id.imagebuttonsuffe);
        imageButton_Repeat = findViewById(R.id.imagebuttonpepeat);
        viewPager_Playnhac = findViewById(R.id.viewPager_playnhac);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });

        fragment_dianhac = new DiaNhacFragment();
        fragment_playdanhsachcacbaihat = new PlayDanhSachCacBaiHatFragment();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_playdanhsachcacbaihat);
        adapternhac.AddFragment(fragment_dianhac);

        viewPager_Playnhac.setAdapter(adapternhac);
        fragment_dianhac  = (DiaNhacFragment) adapternhac.getItem(1);
        if (mangbaihat.size()>0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imageButton_Play.setImageResource(R.drawable.iconpause);
        }

    }

    private void getDataIntent() {

        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null){
            if (intent.hasExtra("cakhuc")){
                baiHat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> baiHatArrayListmangbaihat = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baiHatArrayListmangbaihat;
            }
        }

    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                     mediaPlayer.stop();
                     mediaPlayer.reset();
                }
            });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }


    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textView_Totaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void  UpdateTime(){
        final Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("mm:ss");
                    textView_TimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next  = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next==true){
                    if (position< (mangbaihat.size())){
                        imageButton_Play.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true){
                            if (position ==0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position =  index -1;

                            }
                            position  = index;
                        }
                        if (position> (mangbaihat.size()-1 )){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenbaihat());
                    }

                imageButton_Pre.setClickable(false);
                imageButton_Next.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButton_Pre.setClickable(true);
                        imageButton_Next.setClickable(true);
                    }
                },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },10000);
    }
}
