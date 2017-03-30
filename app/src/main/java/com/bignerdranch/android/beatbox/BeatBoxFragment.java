package com.bignerdranch.android.beatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by t7500 on 3/30/2017.
 */

public class BeatBoxFragment extends Fragment {

    /**
     * Returns a new instance of a BeatBoxFragment
     * @return a new BeatBoxFragment
     */
    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);

        RecyclerView recyclerView = (RecyclerView)view
                .findViewById(R.id.fragment_beat_box_recycler_view);

        // RecyclerView has a grid layout w/ 3 columns
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        return view;
    }
}
