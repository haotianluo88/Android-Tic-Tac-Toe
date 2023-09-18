package com.example.scroll_tute5prep.menu_interfaces;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.scroll_tute5prep.MainActivityData;
import com.example.scroll_tute5prep.R;

/*  Code by Zu Xiang Yek (20905457)
    Description: The fragment loaded as the game's main menu.   */

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        // Create view model
        MainActivityData mainViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        // Initialise the layout items (menu navigation buttons)
        Button startButton = rootView.findViewById(R.id.menuStartButton);
        Button settingsButton = rootView.findViewById(R.id.menuSettingsButton);
        Button leaderboardButton = rootView.findViewById(R.id.menuLeaderboardButton);
        Button profileButton = rootView.findViewById(R.id.menuProfileButton);

        // Set up each button in order; this one enters the mode selection screen
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mainViewModel.setStartGameCoordinate(1); // Set coordinate to enter the mode selection screen
            }
        });

        // Button for entering Settings menu
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mainViewModel.setMenuCoordinate(1); // Set coordinate to enter the settings screen
            }
        });

        // Button for entering Leaderboards screen
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mainViewModel.setMenuCoordinate(2); // Set coordinate to enter the leaderboard screen
            }
        });

        // Button for entering profile edit screen
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mainViewModel.setProfileCoordinate(1); // Set coordinate to enter profile selection
            }
        });

        return rootView;
    }
}