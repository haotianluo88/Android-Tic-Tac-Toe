package com.example.mad_tictactoe.menu_interfaces;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.mad_tictactoe.MainActivityData;
import com.example.mad_tictactoe.R;
import com.example.mad_tictactoe.game.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        // Create view model
        MainActivityData mainViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        // Initialise buttons and spinners
        ImageView homeButton = rootView.findViewById(R.id.settingsHomeButton);
        Spinner boardSpinner = rootView.findViewById(R.id.spinnerBoardSize);
        Spinner winSpinner = rootView.findViewById(R.id.spinnerWinCond);
        Spinner markerSpinner1 = rootView.findViewById(R.id.spinnerMarker1);
        Spinner markerSpinner2 = rootView.findViewById(R.id.spinnerMarker2);

        // Creating an ArrayAdapter for each spinner
        ArrayAdapter<CharSequence> boardAdapter = ArrayAdapter.createFromResource(getContext(), R.array.board_sizes, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> winAdapter = ArrayAdapter.createFromResource(getContext(), R.array.win_conditions, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> markerAdapter1 = ArrayAdapter.createFromResource(getContext(), R.array.marker_choices, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> markerAdapter2 = ArrayAdapter.createFromResource(getContext(), R.array.marker_choices, android.R.layout.simple_spinner_item);

        // setting up each spinner
        boardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSpinner.setAdapter(boardAdapter);
        winAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        winSpinner.setAdapter(winAdapter);
        markerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        markerSpinner1.setAdapter(markerAdapter1);
        markerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        markerSpinner2.setAdapter(markerAdapter2);

        // set up initial positions of each spinner
        boardSpinner.setSelection(mainViewModel.getBoardSize() - 2, false); // Offset by a certain amount to get correct pos
        winSpinner.setSelection(mainViewModel.getBoardSize() - 2, false); // the boolean prevents onItemSelectedListener from triggering

        int markerID1 = mainViewModel.getMarkerP1(); // find out what P1 has selected
        if(markerID1 == R.drawable.cross_marker_red) // set to position accordingly
        {
            markerSpinner1.setSelection(0, false);
        }
        else if(markerID1 == R.drawable.circle_marker_red)
        {
            markerSpinner1.setSelection(1, false);
        }
        else if(markerID1 == R.drawable.square_marker_red)
        {
            markerSpinner1.setSelection(2, false);
        }
        else if(markerID1 == R.drawable.triangle_marker_red)
        {
            markerSpinner1.setSelection(3, false);
        }

        int markerID2 = mainViewModel.getMarkerP2(); // find out what P2 has selected
        if(markerID2 == R.drawable.cross_marker_blue) // set to position accordingly
        {
            markerSpinner2.setSelection(0, false);
        }
        else if(markerID2 == R.drawable.circle_marker_blue)
        {
            markerSpinner2.setSelection(1, false);
        }
        else if(markerID2 == R.drawable.square_marker_blue)
        {
            markerSpinner2.setSelection(2, false);
        }
        else if(markerID2 == R.drawable.triangle_marker_blue)
        {
            markerSpinner2.setSelection(3, false);
        }

        // Set up the home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(mainViewModel.getGameInProgress())
                {
                    mainViewModel.setStartGameCoordinate(3); // Set coordinate to go back to home screen
                    mainViewModel.setMenuCoordinate(3);
                }
                else
                {
                    mainViewModel.resetPlayers(); //sorry I need this here to make sure its reset everytime we go to menu
                    mainViewModel.setMenuCoordinate(0); // Set coordinate to go back to home screen
                }
            }
        });

        // Set up Board Size Spinner
        boardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean dialogShown = false; // Initialise false value; we haven't shown a warning dialogue yet
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) // When the item changes
            {
                if(mainViewModel.getGameInProgress()) // If there is a game in progress, we need to warn the user that it'll be reset
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // Building dialog box
                    builder.setCancelable(false); // Disable ability to exit the box without clicking Y/N
                    builder.setMessage("Warning: Your game will be reset. Are you sure?"); // Explanation
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) // If they select Y
                        {
                            mainViewModel.setGameInProgress(false); // Set this to false, which SHOULD CLEAR ALL THINGS RELATED TO THE GAME IN MainActivity
                            mainViewModel.setBoardSize(pos + 2); // Set new board size
                            mainViewModel.getGame().setGridArray(new int[(pos + 2) * (pos + 2)]);
                            mainViewModel.getGame().setMovesCount(0);
                            mainViewModel.getGame().setMovesLeft((pos + 2) * (pos + 2));
                            mainViewModel.getGame().setList(new LinkedList());
                            mainViewModel.getGame().setWhosTurn(1);
                            mainViewModel.getGame().setMaxMoveCount(0);
                            mainViewModel.getGame().setWinner(0);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) // If they select N
                        {
                            boardSpinner.setSelection(mainViewModel.getBoardSize() - 2); // Set it back to the original value
                            dialogShown = true; // Note that we don't have to show the dialog again for this change in value
                        }
                    });

                    // The code below prevents the popup from appearing twice in a row when selecting No
                    if(!dialogShown) { // If the dialog hasn't been shown yet
                        AlertDialog resetAlert = builder.create(); // Show dialog!
                        resetAlert.show();
                    }
                    else // If the dialog has been shown
                    {
                        dialogShown = false; // Don't show the dialog, but note that it should be shown next time
                    }
                }
                else // If no game is ongoing
                {
                    mainViewModel.setBoardSize(pos + 2); // New board size with no resistance
                    mainViewModel.getGame().setGridArray(new int[(pos + 2) * (pos + 2)]);
                    mainViewModel.getGame().setMovesCount(0);
                    mainViewModel.getGame().setMovesLeft((pos + 2) * (pos + 2));
                    mainViewModel.getGame().setList(new LinkedList());
                    mainViewModel.getGame().setWhosTurn(1);
                    mainViewModel.getGame().setMaxMoveCount(0);
                    mainViewModel.getGame().setWinner(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                // Placeholder, THIS SHOULD IDEALLY NEVER RUN
            }
        });

        // Set up Win Condition Spinner
        winSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean dialogShown = false; // Initialise false value; we haven't shown a warning dialogue yet
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) // When the item changes
            {
                if (mainViewModel.getGameInProgress()) // If there is a game in progress, we need to warn the user that it'll be reset
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // Building dialog box
                    builder.setCancelable(false); // Disable ability to exit the box without clicking Y/N
                    builder.setMessage("Warning: Your game will be reset. Are you sure?"); // Explanation
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) // If they select Y
                        {
                            mainViewModel.setGameInProgress(false); // Set this to false, which SHOULD CLEAR ALL THINGS RELATED TO THE GAME IN MainActivity
                            mainViewModel.setWinCond(pos + 2); // Set new win condition
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) // If they select N
                        {
                            winSpinner.setSelection(mainViewModel.getWinCond() - 2); // Set it back to the original value
                            dialogShown = true; // Note that we don't have to show the dialog again for this change in value
                        }
                    });

                    // The code below prevents the popup from appearing twice in a row when selecting No
                    if (!dialogShown) { // If the dialog hasn't been shown yet
                        AlertDialog resetAlert = builder.create(); // Show dialog!
                        resetAlert.show();
                    } else // If the dialog has been shown
                    {
                        dialogShown = false; // Don't show the dialog, but note that it should be shown next time
                    }
                }
                else // If no game is ongoing
                {
                    mainViewModel.setWinCond(pos + 2); // New win condition with no resistance
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                // Placeholder, THIS SHOULD IDEALLY NEVER RUN
            }
        });

        // Set up Marker Spinner 1
        markerSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) // When an item is selected
            {
                if(pos == 0)
                {
                    mainViewModel.setMarkerP1(R.drawable.cross_marker_red);
                }
                else if(pos == 1)
                {
                    mainViewModel.setMarkerP1(R.drawable.circle_marker_red);
                }
                else if(pos == 2)
                {
                    mainViewModel.setMarkerP1(R.drawable.square_marker_red);
                }
                else if(pos == 3)
                {
                    mainViewModel.setMarkerP1(R.drawable.triangle_marker_red);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Placeholder
            }
        });

        // Set up Marker Spinner 2
        markerSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l)
            {
                if(pos == 0)
                {
                    mainViewModel.setMarkerP2(R.drawable.cross_marker_blue);
                }
                else if(pos == 1)
                {
                    mainViewModel.setMarkerP2(R.drawable.circle_marker_blue);
                }
                else if(pos == 2)
                {
                    mainViewModel.setMarkerP2(R.drawable.square_marker_blue);
                }
                else if(pos == 3)
                {
                    mainViewModel.setMarkerP2(R.drawable.triangle_marker_blue);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Placeholder
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        ImageView homeButton = view.findViewById(R.id.settingsHomeButton);
        MainActivityData mainViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        if (mainViewModel.getGameInProgress())
        {
            homeButton.setImageResource(R.drawable.back_arrow);
        }

    }
}