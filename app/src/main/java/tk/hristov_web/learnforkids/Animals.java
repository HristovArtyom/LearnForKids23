package tk.hristov_web.learnforkids;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Animals extends ActionBarActivity implements View.OnClickListener {
    public ImageView  imageView12, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11;
    private Intent intent;
    private SoundPool mSoundPool;
    public int catSound, chickenSound, cowSound, dogSound, duckSound, elephantSound, hippopotamusSound, lionSound, pigSound, rhinocerosSound, sheepSound, tigerSound;
    private AssetManager mAssetManager;
    private int mStreamID;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
        imageView12 = (ImageView) findViewById(R.id.imageView12);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);
        imageView10 = (ImageView) findViewById(R.id.imageView10);
        imageView11 = (ImageView) findViewById(R.id.imageView11);
        imageView12.setImageResource(R.drawable.cat);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("Cat");
        imageView6.setOnClickListener(this);
        imageView7.setOnClickListener(this);
        imageView8.setOnClickListener(this);
        imageView9.setOnClickListener(this);
        imageView10.setOnClickListener(this);
        imageView11.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView11:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView6:
                intent = new Intent(this, Menu.class);
                startActivity(intent);
                break;
            case R.id.imageView7:
                playSound(catSound);
                break;
            case R.id.imageView8:
                playSound(catSound);
                break;
            case R.id.imageView9:
                textView.setVisibility(View.VISIBLE);
                break;
            case R.id.imageView10:
                intent = new Intent(this, activity_animals2.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }


    public int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }
    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Can't load " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool();
        } else {
            // Для новых устройств
            createNewSoundPool();
        }

        mAssetManager = getAssets();

        // получим идентификаторы
        catSound = loadSound("cat.mp3");
        chickenSound = loadSound("chicken.mp3");
        cowSound = loadSound("cow.mp3");
        dogSound = loadSound("dog.mp3");
        duckSound = loadSound("duck.mp3");
        elephantSound = loadSound("elephant.mp3");
        hippopotamusSound = loadSound("hippopotamus.mp3");
        lionSound = loadSound("lion.mp3");
        pigSound = loadSound("pig.mp3");
        rhinocerosSound = loadSound("rhinoceros.mp3");
        sheepSound = loadSound("sheep.mp3");
        tigerSound = loadSound("tiger.mp3");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
        mSoundPool = null;
    }
}
