package com.example.scroll_tute5prep;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainActivityData extends ViewModel {

    public MutableLiveData<ArrayList<User>> currUsers;
    public MutableLiveData<Integer> profileCoordinate; //coordinates profile pages :)
     //0 = Home Page
     //1 = Initial Page: to Choose User to modify or create new one - SelectToCustomiseFragment
     //2 = Second Page: to set Image + Name to existing or new user - ProfileSelectionFragment

    public MutableLiveData<Integer> startGameCoordinate; //coordinates start game pages
    //0 = Home Page
    //1 = AI or Player Page
    //2 = Select Player Page
    //3 = InGame Page

    public MutableLiveData<Integer> menuCoordinate; // coordinates settings/leaderboard/opponent selection screens
    // 0 = Home
    // 1 = Settings
    // 2 = Leaderboard
    // added by ZY

    public MutableLiveData<User> needToUpdate;
    //if this is set profile selection knows if it needs to
    //either modify an existing user or create a new one


    public MutableLiveData<User> playerOne;
    public MutableLiveData<User> playerTwo;
    //Users selected as players


    public MutableLiveData<Boolean> vsAI;

    public MutableLiveData<Integer> boardSize; // Determines the dimensions of the board; always a square
    // 2x2, 3x3 (default), 4x4, 5x5, 6x6; determined by 2, 3, 4, 5, 6 respectively
    public MutableLiveData<Integer> winCondition; // Determines how many in a row = a win
    // 2, 3, 4, 5, 6 in a row
    public MutableLiveData<Integer> markerP1; // Determines which marker P1 uses w.r.t. resourceID
    public MutableLiveData<Integer> markerP2; // Determines which marker P2 uses
    // the above 4 MutableLiveData<Integer> values added by ZY
    public MutableLiveData<Boolean> gameInProgress; // Keeps track of whether or not there is a game in progress
    // When gameInProgress is set to false, please reset the game progress entirely and set it to true once game begins again -ZY

    public MainActivityData()
    {
        currUsers = new MutableLiveData<ArrayList<User>>();
        profileCoordinate = new MutableLiveData<Integer>();
        startGameCoordinate = new MutableLiveData<Integer>();
        menuCoordinate = new MutableLiveData<Integer>();
        needToUpdate = new MutableLiveData<User>();
        playerOne = new MutableLiveData<User>();
        playerTwo = new MutableLiveData<User>();
        vsAI = new MutableLiveData<Boolean>();

        // variables changeable from Setting fragment
        boardSize = new MutableLiveData<Integer>();
        winCondition = new MutableLiveData<Integer>();
        markerP1 = new MutableLiveData<Integer>();
        markerP2 = new MutableLiveData<Integer>();

        gameInProgress = new MutableLiveData<Boolean>();

        currUsers.setValue(new ArrayList<>());
        needToUpdate.setValue(null);
        playerOne.setValue(null);
        playerTwo.setValue(null);
        vsAI.setValue(false);

        boardSize.setValue(3); // Default board size of 3x3
        winCondition.setValue(3); // Default win condition of 3 in a row
        markerP1.setValue(R.drawable.circle_marker_red); // Default markers; Circle for P1, Cross for P2
        markerP2.setValue(R.drawable.cross_marker_blue);
        gameInProgress.setValue(false);

        //for testing purposes create users to see if playerSelection works:)
        currUsers.getValue().add(new User("Janet", R.drawable.profile_2));
        currUsers.getValue().add(new User("Janet", R.drawable.profile_4));
        currUsers.getValue().add(new User("Janet", R.drawable.profile_1));
        currUsers.getValue().add(new User("Janet", R.drawable.profile_6));
        currUsers.getValue().add(new User("Janet", R.drawable.profile_3));

    }

    //methods relating to ArrayList of all Users
    public void addUser(User newUser) { currUsers.getValue().add(newUser); }
    public ArrayList<User> getCurrUserList() {  return currUsers.getValue(); }
    public void updateUserInList(User newUserDetails)
    {
        if (currUsers.getValue() != null) {
            for (User user : currUsers.getValue()) {
                if (user.getName().equals(needToUpdate.getValue().getName()))
                {
                    user.setName(newUserDetails.getName());
                    user.setResourceId(newUserDetails.getResourceId());
                    break;
                }
            }
        }
        needToUpdate.setValue(null);
    }
    public boolean nameExists(String pName)
    {
        boolean result = false;

        if (currUsers.getValue() != null) {
            for (User user : currUsers.getValue()) {
                if (user.getName().equals(pName))
                {
                    result = true;
                }
            }
        }
        return result;
    }




    /* methods for coordinating Profile Selection
     * by getting and setting profileCoordinate Variable
     * 0 = Home
     * 1 = Initial Page: to Choose User to modify or create new one - SelectToCustomiseFragment
     * 2 = Second Page: to set Image + Name to existing or new user - ProfileSelectionFragment
     */
    public int getProfileCoordinate() { return profileCoordinate.getValue(); }
    public void setProfileCoordinate(int newProfileFrag) { profileCoordinate.setValue(newProfileFrag); }




    /* methods for coordinating Profile Selection
     * by getting and setting profileCoordinate Variable
     * 0 = Home
     * 1 = AI or Player Page
     * 2 = Select Player Page
     * 3 = InGame Page
     */
    public int getStartGameCoordinate() { return startGameCoordinate.getValue(); }
    public void setStartGameCoordinate(int newGameCoordinate) { startGameCoordinate.setValue(newGameCoordinate);}

    /*  Methods for coordinating Menu, Settings, Leaderboard screens
        0 = Home
        1 = Settings
        2 = Leaderboard

        Added by ZY                                                             */
    public int getMenuCoordinate()
    {
        return menuCoordinate.getValue();
    }
    public void setMenuCoordinate(int newMenuCoordinate)
    {
        menuCoordinate.setValue(newMenuCoordinate);
    }

    //Methods for deciding to update or create a new user
    public User getUserToUpdate() {
       return needToUpdate.getValue();
    }
    public void setUserToUpdate(User toUpdate) { needToUpdate.setValue(toUpdate);}




    //Accessing players!
    //CAUTION: Please call resetPlayers() at the end of game
    public void setPlayerOne(User pOne){playerOne.setValue(pOne);}
    public void setPlayerTwo(User pTwo){playerTwo.setValue(pTwo);}

    public User getPlayerTwo(){return playerTwo.getValue();}
    public User getPlayerOne(){return playerOne.getValue();}
    public void resetPlayers(){ playerOne.setValue(null); playerTwo.setValue(null);}




    public void setVsAI(boolean x) {vsAI.setValue(x);}
    public boolean getVsAI() {return vsAI.getValue();}

    // Accessing Settings fragment variables; added by ZY
    // Board size
    public void setBoardSize(int pSize)
    {
        boardSize.setValue(pSize);
    }
    public int getBoardSize()
    {
        return boardSize.getValue();
    }
    // Win condition
    public void setWinCond(int pCond)
    {
        winCondition.setValue(pCond);
    }
    public int getWinCond()
    {
        return winCondition.getValue();
    }
    // Player 1's marker
    public void setMarkerP1(int pMarker)
    {
        markerP1.setValue(pMarker);
    }
    public int getMarkerP1()
    {
        return markerP1.getValue();
    }
    // Player 2's marker
    public void setMarkerP2(int pMarker)
    {
        markerP2.setValue(pMarker);
    }
    public int getMarkerP2()
    {
        return markerP2.getValue();
    }

    public void setGameInProgress(boolean pBool)
    {
        gameInProgress.setValue(pBool);
    }
    public boolean getGameInProgress()
    {
        return gameInProgress.getValue();
    }

}
