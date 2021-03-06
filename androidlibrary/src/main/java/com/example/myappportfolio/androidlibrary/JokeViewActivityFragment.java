package com.example.myappportfolio.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class JokeViewActivityFragment extends Fragment {

    public JokeViewActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.joke_view_fragment_main, container, false);

        Intent i = getActivity().getIntent();
        //for joke text
        if (i != null && i.hasExtra(JokeViewActivity.INTENT_EXTRA_PARAM_FOR_JOKE)) {
            TextView jokeTextView = (TextView) rootView.findViewById(R.id.jokeText);
            jokeTextView.setText(i.getStringExtra(JokeViewActivity.INTENT_EXTRA_PARAM_FOR_JOKE));
        }

        return rootView;
    }
}
