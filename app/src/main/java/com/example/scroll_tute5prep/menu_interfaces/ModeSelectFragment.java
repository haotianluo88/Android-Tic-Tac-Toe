package com.example.scroll_tute5prep.menu_interfaces;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scroll_tute5prep.MainActivityData;
import com.example.scroll_tute5prep.R;

/*  Code by Zu Xiang Yek (20905457)
    Description: The fragment loaded to display the single/multiplayer selection screen.   */

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModeSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModeSelectFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ModeSelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModeSelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModeSelectFragment newInstance(String param1, String param2) {
        ModeSelectFragment fragment = new ModeSelectFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_mode_select, container, false);
        // Create view model
        MainActivityData mainViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        // Initialise buttons for entering game, and home button
        Button singleButton = rootView.findViewById(R.id.selectSingleButton);
        Button multiButton = rootView.findViewById(R.id.selectMultiButton);
        ImageView homeButton = rootView.findViewById(R.id.selectHomeButton);

        // Set up the home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mainViewModel.setMenuCoordinate(0); // Set coordinate to go back to home
            }
        });

        // Set up other two buttons for entering game
        singleButton.setOnClickListener(new View.OnClickListener() {
           @Override public void onClick(View view)
           {
               mainViewModel.setVsAI(true); // Notify the game that the player is going Singleplayer
               mainViewModel.setStartGameCoordinate(2); // Enter player selection screen
           }
        });

        multiButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                mainViewModel.setVsAI(false); // Notify the game that the player is going Multiplayer
                mainViewModel.setStartGameCoordinate(2); // Enter player selection screen
            }
        });

        return rootView;
    }
}