package com.bignerdranch.android.beatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by t7500 on 3/30/2017.
 */

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain the fragment to preserve sound playing
        // FragmentManager destroys a frag's views first,
        // frag manager then checks a frag's retainInstance to decide
        // if it needs to destroy and re-create it
        // frag's are only retiained if setRetainInstance(true)
        // and host activity is being destroyed for a config change
        setRetainInstance(true);

        mBeatBox = new BeatBox(getActivity());
    }

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

        // Wire up the adapter
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }

    private class SoundHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Button mButton;
        private Sound mSound;

        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound, container, false));

            mButton = (Button)itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(this);
        }

        public void bindSound(Sound sound) {
            mSound = sound;
            mButton.setText(mSound.getName());
        }

        @Override
        public void onClick(View v) {
            mBeatBox.play(mSound);
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
