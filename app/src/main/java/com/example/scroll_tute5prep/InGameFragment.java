package com.example.scroll_tute5prep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Temporary to see if 2 Players get passed accurately to this fragment
 *
 * HAOTIAN!! This is your fragment replace how you like
 * to go back to home setStartGameCoordinate(0); (in MainActivityData)
 * please call resetPlayers() (in MainActivityData ) after game is over;
 */
public class InGameFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public InGameFragment() {
    }

    public static InGameFragment newInstance(String param1, String param2) {
        InGameFragment fragment = new InGameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in_game, container, false);



        //This code gets two ImageView from the layout
        //Creates access to MainActivity View Model
        //Gets the two main players!
        //the main players are USER objects!

        ImageView p1 = view.findViewById(R.id.p1);
        ImageView p2 = view.findViewById(R.id.p2);
        ImageView home = view.findViewById(R.id.home);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        //sets ImageView to Player's stored image
        p1.setImageResource(mainActivityDataViewModel.getPlayerOne().getResourceId());
        if(!mainActivityDataViewModel.getVsAI()) {
            p2.setImageResource(mainActivityDataViewModel.getPlayerTwo().getResourceId());
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mainActivityDataViewModel.resetPlayers();
                mainActivityDataViewModel.setProfileCoordinate(0);
            }
        });


        return view;
    }
}