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

    public MutableLiveData<User> needToUpdate;
    //if this is set profile selection knows if it needs to
    //either modify an existing user or create a new one


    public MutableLiveData<User> playerOne;
    public MutableLiveData<User> playerTwo;
    //Users selected as players


    public MutableLiveData<Boolean> vsAI;


    public MainActivityData()
    {
        currUsers = new MutableLiveData<ArrayList<User>>();
        profileCoordinate = new MutableLiveData<Integer>();
        startGameCoordinate = new MutableLiveData<Integer>();
        needToUpdate = new MutableLiveData<User>();
        playerOne = new MutableLiveData<>();
        playerTwo = new MutableLiveData<>();
        vsAI = new MutableLiveData<>();

        currUsers.setValue(new ArrayList<>());
        needToUpdate.setValue(null);
        playerOne.setValue(null);
        playerTwo.setValue(null);
        vsAI.setValue(false);

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
}
