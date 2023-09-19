package com.example.mad_tictactoe.customise_add_profile;


import com.example.mad_tictactoe.R;

import java.util.ArrayList;

/**
 * Holds all the profile pics in an array
 * So I can display them during ProfileSelection!
 * By Janet Joy (20342054)
 */

public class ProfilePicData {
    private static ProfilePicData single_instance = null;
    private ArrayList<Integer> pics;
    private static final int noOfPics = 21;


    private ProfilePicData()
    {
        pics = new ArrayList<Integer>();
        fill();
    }

    public static ProfilePicData getInstance()
    {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new ProfilePicData();
        }
        return single_instance;
    }

    private void fill()
    {
        //manual labour it is ;-; please don't touch this >:/
        pics.add(R.drawable.profile_1);
        pics.add(R.drawable.profile_2);
        pics.add(R.drawable.profile_3);
        pics.add(R.drawable.profile_4);
        pics.add(R.drawable.profile_5);
        pics.add(R.drawable.profile_6);
        pics.add(R.drawable.profile_7);
        pics.add(R.drawable.profile_8);
        pics.add(R.drawable.profile_9);
        pics.add(R.drawable.profile_10);
        pics.add(R.drawable.profile_11);
        pics.add(R.drawable.profile_12);
        pics.add(R.drawable.profile_13);
        pics.add(R.drawable.profile_14);
        pics.add(R.drawable.profile_15);
        pics.add(R.drawable.profile_16);
        pics.add(R.drawable.profile_17);
        pics.add(R.drawable.profile_18);
        pics.add(R.drawable.profile_19);
        pics.add(R.drawable.profile_20);
        pics.add(R.drawable.profile_21);
    }

    public ArrayList<Integer> getData()
    {
        return pics;
    }
}
