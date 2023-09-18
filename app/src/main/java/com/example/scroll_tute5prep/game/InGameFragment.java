package com.example.scroll_tute5prep.game;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static android.view.ViewGroup.LayoutParams.*;

import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scroll_tute5prep.MainActivityData;
import com.example.scroll_tute5prep.R;

import java.util.Arrays;
import java.util.Random;


public class InGameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment game.
     */
    // TODO: Rename and change types and number of parameters
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

//////////////////////////// GET THESE VARIABLES FROM OTHER FRAGMENTS ETC /////////////////////////////////////////////////
    int gridSize = 3;
    int winCond = 3;
    boolean playAI = true;

    boolean gameInProgress;
    String playerOneProfileStr = "square_marker_red";
    String playerTwoProfileStr = "circle_marker_blue";
    String playerOneNameStr = "player One";
    String playerTwoNameStr = "player Two";
    String playerOneMarkerStr = "square_marker_red";
    String playerTwoMarkerStr = "circle_marker_blue";


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    int[] grid1DArray = new int[gridSize * gridSize];
    int[][] grid2DArray = new int[gridSize][gridSize];
    int movesLeft = gridSize * gridSize;
    int whosTurn = 1;
    int movesCount = 0;
    int maxMoveCount = 0;
    int winner = 0;
    Chronometer timer;
    LinkedList gridLinkedList = new LinkedList();
    String gridLinkedListString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        TextView playerOneName = rootView.findViewById(R.id.playerOneName);
        TextView playerTwoName = rootView.findViewById(R.id.playerTwoName);
        ImageView playerOneProfile = rootView.findViewById(R.id.playerOneProfile);
        ImageView playerTwoProfile = rootView.findViewById(R.id.playerTwoProfile);
        LinearLayout playerOneLayout = rootView.findViewById(R.id.playerOneLayout);
        LinearLayout playerTwoLayout = rootView.findViewById(R.id.playerTwoLayout);
        timer = rootView.findViewById(R.id.timer);
        TextView movesCountText = rootView.findViewById(R.id.movesCount);
        TextView movesLeftText = rootView.findViewById(R.id.movesLeft);
        LinearLayout boardLayout = rootView.findViewById(R.id.board);
        Button undoButton = rootView.findViewById(R.id.undoButton);
        Button redoButton = rootView.findViewById(R.id.redoButton);
        Button settingsButton = rootView.findViewById(R.id.settingsButton);

        // Creating our MainActivityData in order to fetch information to and from other parts of the app
        MainActivityData mainViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

//      Start a timer
        timer.start();
        gameInProgress = true;
//      Creates the first entry in the linked list which is an empty array
        gridLinkedListString = convertArrayToString(grid1DArray);
        gridLinkedList.insertNode(0, 0, gridLinkedListString);

//      Set player one icon and name
        playerOneName.setText(playerOneNameStr);
        playerOneProfile.setImageResource(getResources().getIdentifier(playerOneProfileStr, "drawable", getActivity().getPackageName()));

//      Set player two icon and name depending on if it's AI or not
        if (playAI) {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            playerTwoName.setText("AI");
            playerTwoProfile.setImageResource(getResources().getIdentifier("cross", "drawable", getActivity().getPackageName()));
            /////////////////////////// custom picture for ai?
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        } else {
            playerTwoName.setText(playerTwoNameStr);
            playerTwoProfile.setImageResource(getResources().getIdentifier(playerTwoProfileStr, "drawable", getActivity().getPackageName()));
        }

//      Create all the grids depending on the grid size
        for (int i = 0; i < gridSize; i++) {
//          Creates a linear layout
            LinearLayout boardRowLayout = new LinearLayout(getActivity());
//          Depending on the orientation of the device, properties of the linear layout is changed
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                boardRowLayout.setOrientation(LinearLayout.HORIZONTAL);
                boardRowLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            } else {
                boardRowLayout.setOrientation(LinearLayout.VERTICAL);
                boardRowLayout.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
            }
            boardRowLayout.setWeightSum(gridSize);
//          Adds the linear layout to the board layout
            boardLayout.addView(boardRowLayout);
            for (int j = 0; j < gridSize; j++) {
//              Creates an image view with propeties depending on the orientation of the device
                ImageView imageView = new ImageView(getActivity());
                imageView.setId(i * gridSize + j + 1);
                imageView.setImageResource(R.drawable.transparent);
                imageView.setAdjustViewBounds(true);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f));
                } else {
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, 0, 1f));
                }
                imageView.setBackground(getActivity().getDrawable(R.drawable.grids));

//              Creates an index
                final int index = (i * gridSize) + j;

//              When any of the grid images gets clicked
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                      If the square is currently not occupied and game is ongoing
                        if (grid1DArray[index] == 0 && winner == 0) {
//                          AI mode
                            if (playAI) {
//                              Initialise random number generator
                                Random rn = new Random();
                                int randomNumber;
//                              Updates board data with player one
                                grid1DArray[index] = 1;
                                grid2DArray = convert1DArrayTo2DArray(grid1DArray, gridSize);
//                              Increment moves count by one and reduce moves left by one
                                movesCount++;
                                movesLeft--;
//                              Converts the array into a string to store as history
                                gridLinkedListString = convertArrayToString(grid1DArray);
                                gridLinkedList.insertNode(movesCount - 1, movesCount, gridLinkedListString);
//                              Checks whether the game is over or not
                                winner = checkWin(grid2DArray, gridSize, winCond);
//                              Generate a random number of a square that is not occupied
                                do {
                                    randomNumber = rn.nextInt((gridSize * gridSize - 1) + 1);

                                } while (grid1DArray[randomNumber] != 0 && movesLeft > 0);
//                              If the game is not over
                                if (winner == 0) {
//                                  Updates board data with player two
                                    grid1DArray[randomNumber] = 2;
                                    grid2DArray = convert1DArrayTo2DArray(grid1DArray, gridSize);
//                                  Increment moves count by one and reduce moves left by one
                                    movesCount++;
                                    movesLeft--;
//                                  Converts the array into a string to store as history
                                    gridLinkedListString = convertArrayToString(grid1DArray);
                                    gridLinkedList.insertNode(movesCount - 1, movesCount, gridLinkedListString);
//                                  Checks whether the game is over or not
                                    winner = checkWin(grid2DArray, gridSize, winCond);
                                }
//                              Updates the board's ImageViews
                                drawGrid(rootView, grid2DArray, gridSize, playerOneMarkerStr, playerTwoMarkerStr);
//                          Two player mode
                            } else {
//                              Increment moves count by one and reduce moves left by one
                                movesCount++;
                                movesLeft--;
//                              Player one's turn
                                if (whosTurn == 1) {
//                                  Updates the arrays
                                    grid1DArray[index] = 1;
                                    grid2DArray = convert1DArrayTo2DArray(grid1DArray, gridSize);
//                                  Converts the array into a string to store as history
                                    gridLinkedListString = convertArrayToString(grid1DArray);
                                    gridLinkedList.insertNode(movesCount - 1, movesCount, gridLinkedListString);
//                                  Changes the turn to player twos
                                    playerOneLayout.setBackground(getActivity().getDrawable(R.drawable.purple_button_white_border));
                                    playerTwoLayout.setBackground(getActivity().getDrawable(R.drawable.purple_button_red_border));
                                    whosTurn++;
//                              Player two's turn
                                } else if (whosTurn == 2) {
//                                  Updates the arrays
                                    grid1DArray[index] = 2;
                                    grid2DArray = convert1DArrayTo2DArray(grid1DArray, gridSize);
//                                  Converts the array into a string to store as history
                                    gridLinkedListString = convertArrayToString(grid1DArray);
                                    gridLinkedList.insertNode(movesCount - 1, movesCount, gridLinkedListString);
//                                  Changes the turn to player ones
                                    playerTwoLayout.setBackground(getActivity().getDrawable(R.drawable.purple_button_white_border));
                                    playerOneLayout.setBackground(getActivity().getDrawable(R.drawable.purple_button_red_border));
                                    whosTurn--;
                                }
//                              Updates the board's ImageViews
                                drawGrid(rootView, grid2DArray, gridSize, playerOneMarkerStr, playerTwoMarkerStr);
//                              Checks whether the game is over or not
                                winner = checkWin(grid2DArray, gridSize, winCond);
                            }
                            maxMoveCount = movesCount;
//                          Updates moves count and moves left TextViews;
                            movesLeftText.setText("Moves Left: " + String.valueOf(movesLeft));
                            movesCountText.setText("Moves Count: " + String.valueOf(movesCount));
//                          Displays game over pop up
                            displayGameOverMessage(winner, timer);
                        }
                    }
                });
//              Adds an image view to the linear layout
                boardRowLayout.addView(imageView);
            }
        }

        grid2DArray = convert1DArrayTo2DArray(grid1DArray, gridSize);

//      When the undo button is clicked
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              If there are moves to undo
                if (movesCount > 0) {
//                  If playing against AI, get the node string two moves back else, get the node string one move back
                    if (playAI) {
                        gridLinkedListString = (String) gridLinkedList.getObject(gridLinkedList.peekNode((movesCount - 2)));
                    } else {
                        gridLinkedListString = (String) gridLinkedList.getObject(gridLinkedList.peekNode((movesCount - 1)));
                    }
//                  Converts string to array
                    grid1DArray = convertStringToIntArray(gridLinkedListString);
                    grid2DArray = convert1DArrayTo2DArray(grid1DArray, gridSize);
//                  Updates board
                    drawGrid(rootView, grid2DArray, gridSize, playerOneMarkerStr, playerTwoMarkerStr);
//                  Updates moves count and moves left depending on if playing AI or not
                    movesCount--;
                    movesLeft++;
                    if (playAI) {
                        movesCount--;
                        movesLeft++;
                    }
//                  Changes the turn to the opposition. This only applies in two player mode
                    if (whosTurn == 1) {
                        whosTurn++;
                    } else {
                        whosTurn--;
                    }
//                  Updates moves text
                    movesLeftText.setText(String.valueOf("Moves Left: " + movesLeft));
                    movesCountText.setText(String.valueOf("Moves Count: " + movesCount));
                }
            }
        });

//      When the redo button is clicked
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              If there is a an node in the linked list to redo
                if (movesCount < maxMoveCount) {
//                  If playing against AI, get the node string two moves ahead else, get the node string one move ahead
                    if (playAI) {
                        gridLinkedListString = (String) gridLinkedList.getObject(gridLinkedList.peekNode((movesCount + 2)));
                    } else {
                        gridLinkedListString = (String) gridLinkedList.getObject(gridLinkedList.peekNode((movesCount + 1)));
                    }
//                  Converts the string to array
                    grid1DArray = convertStringToIntArray(gridLinkedListString);
                    grid2DArray = convert1DArrayTo2DArray(grid1DArray, gridSize);
//                  Updates board
                    drawGrid(rootView, grid2DArray, gridSize, playerOneMarkerStr, playerTwoMarkerStr);
//                  Updates moves count and moves left depending on if playing AI or not
                    movesCount++;
                    movesLeft--;
                    if (playAI) {
                        movesCount++;
                        movesLeft--;
                    }
//                  Changes the turn to the opposition. This only applies in two player mode
                    if (whosTurn == 1) {
                        whosTurn++;
                    } else {
                        whosTurn--;
                    }
//                  Updates moves text
                    movesLeftText.setText(String.valueOf("Moves Left: " + movesLeft));
                    movesCountText.setText(String.valueOf("Moves Count: " + movesCount));
                }
            }
        });

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
//        //////////Settings button just resets the game right now
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        return rootView;
    }

//  Draws the grid with the correct information
    private void drawGrid(View rootView, int[][] grid2DArray, int gridSize, String playerOneMarkerStr, String playerTwoMarkerStr) {
        ImageView image;
        int index = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                image = rootView.findViewById(index + 1);
                if (grid2DArray[i][j] == 1) {
                    image.setImageResource(getResources().getIdentifier(playerOneMarkerStr, "drawable", getActivity().getPackageName()));
                } else if (grid2DArray[i][j] == 2) {
                    image.setImageResource(getResources().getIdentifier(playerTwoMarkerStr, "drawable", getActivity().getPackageName()));
                } else if (grid2DArray[i][j] == 0) {
                    image.setImageResource(R.drawable.transparent);
                }
                index++;
            }
        }
    }

//  Checks whether there is a winner
    private int checkWin(int[][] grid2DArray, int gridSize, int winCond) {
//      Check row win condition
//      Checks every grid on the board where subsequent elements in the row is more or equal to win condition
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j <= gridSize - winCond; j++) {
//              Get the assigned marker of each grid
                int grid = grid2DArray[i][j];
//              If the grid is not empty
                if (grid != 0) {
                    boolean isWin = true;
//                  Cycles through the row until win condition is met
                    for (int k = 1; k < winCond; k++) {
//                      If at any point the win condition is broken, there is no winner
                        if (grid2DArray[i][j + k] != grid) {
                            isWin = false;
                            break;
                        }
                    }
//                  If there is a winner, return the winner marker
                    if (isWin) {
                        return grid;
                    }
                }
            }
        }

//      Check column win condition
//      Checks every grid on the board where subsequent elements in the column is more or equal to win condition
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j <= gridSize - winCond; j++) {
//              Get the assigned marker of each grid
                int grid = grid2DArray[j][i];
//              If the grid is not empty
                if (grid != 0) {
                    boolean isWin = true;
//                  Cycles through the column until win condition is met
                    for (int k = 1; k < winCond; k++) {
//                      If at any point the win condition is broken, there is no winner
                        if (grid2DArray[j + k][i] != grid) {
                            isWin = false;
                            break;
                        }
                    }
//                  If there is a winner, return the winner marker
                    if (isWin) {
                        return grid;
                    }
                }
            }
        }

//      Check diagonal from top left to bottom right
//      Check all the grids that is possible to win with
        for (int i = 0; i <= gridSize - winCond; i++) {
            for (int j = 0; j <= gridSize - winCond; j++) {
//              Get the assigned marker of each grid
                int grid = grid2DArray[i][j];
//              If the grid is not empty
                if (grid != 0) {
                    boolean isWin = true;
//                  Cycles through the top left to bottom right diagonal until win condition is met
                    for (int k = 1; k < winCond; k++) {
//                      If at any point the win condition is broken, there is no winner
                        if (grid2DArray[i + k][j + k] != grid) {
                            isWin = false;
                            break;
                        }
                    }
//                  If there is a winner, return the winner marker
                    if (isWin) {
                        return grid;
                    }
                }

            }
        }

//      Check diagonal from bottom left to top right
//      Check all the grids that is possible to win with
        for (int i = 0; i <= gridSize - winCond; i++) {
            for (int j = gridSize - 1; j >= winCond - 1; j--) {
//              Get the assigned marker of each grid
                int grid = grid2DArray[i][j];
//              If the grid is not empty
                if (grid != 0) {
                    boolean isWin = true;
//                  Cycles through the top left to bottom right diagonal until win condition is met
                    for (int k = 1; k < winCond; k++) {
//                      If at any point the win condition is broken, there is no winner
                        if (grid2DArray[i + k][j - k] != grid) {
                            isWin = false;
                            break;
                        }
                    }
//                  If there is a winner, return the winner marker
                    if (isWin) {
                        return grid;
                    }
                }

            }
        }

//      Checks if all grids are filled to determine a draw
        boolean isDraw = true;
//      Cycles through all the grids
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
//              If any of the grid is empty, it is not a draw
                if (grid2DArray[i][j] == 0) {
                    isDraw = false;
                    break;
                }
            }
        }
//      Returns a draw
        if (isDraw) {
            return 3;
        }

//      If no winner is determined, the game continues
        return 0;
    }

//  Displays game over pop up
    private void displayGameOverMessage(int winner, Chronometer timer) {
        if (winner != 0) {
            timer.stop();

//          Creates dialog
            Dialog dialog = new Dialog(getActivity());
//          Set the layout for the dialog
            dialog.setContentView(R.layout.game_over_layout);
//          Makes the layout undismissable
            dialog.setCanceledOnTouchOutside(false);
//          Makes it so the rounded corners of the layout is transparent
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//          Finds the buttons and text on the layout
            Button restartButton = dialog.findViewById(R.id.restartButton);
            Button exitButton = dialog.findViewById(R.id.exitButton);
            TextView text = dialog.findViewById(R.id.gameOverMessage);

            String gameOverMessage;
//          Set the game over message depending on the outcome of the game
            if (winner == 1) {
                gameOverMessage = playerOneNameStr + " is the winner!";
            } else if (winner == 2) {
                gameOverMessage = playerTwoNameStr + " is the winner!";
            } else {
                gameOverMessage = "Game is a draw";
            }

//          Shows the dialog
            dialog.show();
            text.setText(gameOverMessage);

//          Restart game if restart button is pressed
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    restartGame();
                    dialog.dismiss();
                }
            });
//////////////////////////// CURRENTLY DOES NOTHING ////////////////////////////////////////////////////
//          Returns to menu
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text.setText("SUS");
                }
            });
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
    }

//  Converts a 1D array into a 2D array
    private static int[][] convert1DArrayTo2DArray(int[] DArray, int gridSize) {
//      Create a 2D array
        int[][] DDArray = new int[gridSize][gridSize];
        int index = 0;
//      Assigns each element in the 1D array into the 2D array
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                DDArray[i][j] = DArray[index];
                index++;
            }
        }
//      Returns the 2D array
        return DDArray;
    }

//  Resets all game parameters
///////////////////////// THIS MAY BE UNECESSARY AS SOMEONE ELSE MAY HAVE ALREADY IMPLEMENTED THIS ////////////////////
    public void restartGame() {
        LinearLayout playerOneLayout = getActivity().findViewById(R.id.playerOneLayout);
        LinearLayout playerTwoLayout = getActivity().findViewById(R.id.playerTwoLayout);
        TextView movesLeftText = getActivity().findViewById(R.id.movesLeft);
        TextView movesCountText = getActivity().findViewById(R.id.movesCount);

        grid1DArray = new int[gridSize * gridSize];
        grid2DArray = new int[gridSize][gridSize];
        movesLeft = gridSize * gridSize;
        movesCount = 0;
        maxMoveCount = 0;
        whosTurn = 1;
        winner = 0;

        gridLinkedList = new LinkedList();
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        playerOneLayout.setBackground(getActivity().getDrawable(R.drawable.purple_button_red_border));
        playerTwoLayout.setBackground(getActivity().getDrawable(R.drawable.purple_button_white_border));

        movesLeftText.setText(String.valueOf("Moves Left: " + movesLeft));
        movesCountText.setText(String.valueOf("Moves Count: " + movesCount));
        drawGrid(getView(), grid2DArray, gridSize, playerOneMarkerStr, playerTwoMarkerStr);

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//  Converts an array into a string
    public String convertArrayToString(int[] array)
    {
//      Convert the array to a string
        String tempString = Arrays.toString(array);
//      Removes the square brackets in the string
        String convertedString = tempString.substring(1, tempString.length() -1);
//      Returns the processed string
        return convertedString;
    }

//  Converts a string to an integer array
    public int[] convertStringToIntArray(String string)
    {
//      Split the string into a string array first
        String[] stringArray = string.split(", ");
        int[] intArray = new int[stringArray.length];
//      Converts the string array into an integer array
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
//      Returns the integer array
        return intArray;
    }
}