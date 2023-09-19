package com.example.mad_tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Page After AI OR Play selection Page
 * Responsible for selecting players for the game
 * Or One player for the AI game
 *
 * By Janet Joy (20342054)
 */
public class PlayerSelectionFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public PlayerSelectionFragment() {
        // Required empty public constructor
    }

    public static PlayerSelectionFragment newInstance(String param1, String param2) {
        PlayerSelectionFragment fragment = new PlayerSelectionFragment();
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
        View pView = inflater.inflate(R.layout.fragment_player_selection, container, false);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        ArrayList<User> list = mainActivityDataViewModel.getCurrUserList(); //returning no reference
        RecyclerView rv = pView.findViewById(R.id.recyclerView);
        ImageView ghost = pView.findViewById(R.id.ghost);
        ImageView arrow = pView.findViewById(R.id.imageView);

        ghost.setVisibility(View.INVISIBLE);
        if (list.isEmpty())
        {
            ghost.setVisibility(View.VISIBLE);
        }
        else
        {
            rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
            PlayerSelectionAdapter adapter = new PlayerSelectionAdapter(mainActivityDataViewModel);
            rv.setAdapter(adapter);

            mainActivityDataViewModel.currUsers.observe(getActivity(), new Observer<ArrayList<User>>() {
                @Override
                public void onChanged(ArrayList<User> userList) {
                    adapter.notifyDataSetChanged();
                }
            });
        }

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityDataViewModel.resetPlayers();
                mainActivityDataViewModel.setStartGameCoordinate(1);
            }
        });

        return pView;
    }
}