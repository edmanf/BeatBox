package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;

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
        mAssets = context.getAssets();
    }
}
