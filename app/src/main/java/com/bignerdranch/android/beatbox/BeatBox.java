package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by t7500 on 3/30/2017.
 */

public class BeatBox {
    /** Constant for logging */
    private static final String TAG = "BeatBox";

    /** Folder that sounds are saved in */
    private static final String SOUNDS_FOLDER = "sample_sounds";

    /** The asset manager used by this BeatBox to access assets */
    private AssetManager mAssets;

    public BeatBox(Context context) {
        // Usually don't need to worry about what context is passed in
        // they usually point to same set of assets
        mAssets = context.getAssets();
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }
    }

}
