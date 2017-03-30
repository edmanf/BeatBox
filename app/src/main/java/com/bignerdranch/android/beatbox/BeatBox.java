package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by t7500 on 3/30/2017.
 */

public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds;
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        // Usually don't need to worry about what context is passed in
        // they usually point to same set of assets
        mAssets = context.getAssets();

        // Deprecated method used for compatibility (our min is 16, new is L)
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            // list()) lists filenames contained in folderpath
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        // add every sound found to our list
        for (String filename : soundNames) {
            try {

                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }

    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    /**
     * Load a sound into the SoundPool
     * @param sound the sound you want to load
     * @throws IOException
     */
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);
    }

}
