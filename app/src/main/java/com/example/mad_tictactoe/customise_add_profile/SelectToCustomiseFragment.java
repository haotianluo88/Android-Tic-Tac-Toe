package com.example.mad_tictactoe.customise_add_profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mad_tictactoe.MainActivityData;
import com.example.mad_tictactoe.R;
import com.example.mad_tictactoe.User;

import java.util.ArrayList;

/**
 * Initial Page: where users select a user they want to modify
 * or press the add button at the bottom to create a new user
 * Both paths lead to ProfileSelectionFragment
 * By Janet Joy (20342054)
 */
public class SelectToCustomiseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private ArrayList<User> list;
    public SelectToCustomiseFragment() {
        // Required empty public constructor
    }

    public static SelectToCustomiseFragment newInstance() {
        SelectToCustomiseFragment fragment = new SelectToCustomiseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View pView = inflater.inflate(R.layout.fragment_select_to_customise, container, false);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        list = mainActivityDataViewModel.getCurrUserList(); //returning no reference
        RecyclerView rv = pView.findViewById(R.id.recyclerView);
        ImageView ghost = pView.findViewById(R.id.ghost);
        ImageView home = pView.findViewById(R.id.home);
        Button button =  pView.findViewById(R.id.button);

        ghost.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setProfileCoordinate(2);
            }
        });

        if (list.isEmpty())
        {
            ghost.setVisibility(View.VISIBLE);
        }
        else
        {
            rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
            SelectToCustomiseAdapter adapter = new SelectToCustomiseAdapter(mainActivityDataViewModel);
            rv.setAdapter(adapter);

            mainActivityDataViewModel.currUsers.observe(getActivity(), new Observer<ArrayList<User>>() {
                @Override
                public void onChanged(ArrayList<User> userList) {
                    adapter.notifyDataSetChanged();
                }
            });
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityDataViewModel.setProfileCoordinate(0);
            }
        });

       return pView;
    }
}