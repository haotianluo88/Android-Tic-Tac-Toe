package com.example.mad_tictactoe.menu_interfaces;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_tictactoe.MainActivityData;
import com.example.mad_tictactoe.R;
import com.example.mad_tictactoe.User;
import java.util.ArrayList;

/*  Code by Zu Xiang Yek (20905457)
    Description:    The fragment loaded in order to display the in-game leaderboard.
                    Uses a RecyclerView for the display. Look at onCreateView() for more info.      */

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaderboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaderboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeaderboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaderboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaderboardFragment newInstance(String param1, String param2) {
        LeaderboardFragment fragment = new LeaderboardFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        // Create view model
        MainActivityData mainViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        // Initialise the layout items that the user can interact with
        RecyclerView theLeaderboard = rootView.findViewById(R.id.leaderboardRecycler);
        ImageView homeButton = rootView.findViewById(R.id.leaderboardHomeButton);
        ImageView theGhost = rootView.findViewById(R.id.leaderboardGhost);

        // Hiding ghost, default
        theGhost.setVisibility(View.INVISIBLE);

        // Insert player data for the leaderboard
        ArrayList<User> userData = mainViewModel.getCurrUserList();

        // Check if player data is empty
        if(userData.isEmpty())
        {
            theGhost.setVisibility(View.VISIBLE); // Show ghost if empty
        }
        else
        {
            // If not empty, then set layout manager for RecyclerView
            theLeaderboard.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            // Create adapter
            LeaderboardAdapter theAdapter = new LeaderboardAdapter(userData);
            // Set adapter
            theLeaderboard.setAdapter(theAdapter);

            // Set up observer to update adapter whenever data set changes
            mainViewModel.currUsers.observe(getActivity(), new Observer<ArrayList<User>>()
            {
               @Override
               public void onChanged(ArrayList<User> theList)
               {
                   theAdapter.notifyDataSetChanged(); // Notify of changes
               }
            });
        }

        // Set up the home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mainViewModel.setMenuCoordinate(0); // Change menu coordinate to go back to Home
            }
        });

        return rootView;
    }
}