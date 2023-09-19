package com.example.mad_tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mad_tictactoe.customise_add_profile.ProfileSelectionFragment;
import com.example.mad_tictactoe.customise_add_profile.SelectToCustomiseFragment;
import com.example.mad_tictactoe.menu_interfaces.LeaderboardFragment;
import com.example.mad_tictactoe.menu_interfaces.MenuFragment;
import com.example.mad_tictactoe.menu_interfaces.ModeSelectFragment;
import com.example.mad_tictactoe.menu_interfaces.SettingsFragment;
import com.example.mad_tictactoe.game.InGameFragment;

public class MainActivity extends AppCompatActivity {
    ProfileSelectionFragment profileSelectionFragment = new ProfileSelectionFragment();
    SelectToCustomiseFragment selectToCustomiseFragment = new SelectToCustomiseFragment();
    PlayerSelectionFragment selectPlayers = new PlayerSelectionFragment();
    ModeSelectFragment AIOrP = new ModeSelectFragment();
    MenuFragment home = new MenuFragment();
    SettingsFragment settings = new SettingsFragment();
    LeaderboardFragment leaderboards = new LeaderboardFragment();
    InGameFragment game = new InGameFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(this).get(MainActivityData.class);

        loadHomeFragment();

        mainActivityDataViewModel.profileCoordinate.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(mainActivityDataViewModel.getProfileCoordinate() == 0) {
                    loadHomeFragment();
                }
                else if(mainActivityDataViewModel.getProfileCoordinate() == 1){
                    loadSelectToCustomiseFragment();
                }
                else if(mainActivityDataViewModel.getProfileCoordinate() == 2) {
                    loadProfileSelectionFragment();
                }
            }
        });

        mainActivityDataViewModel.startGameCoordinate.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(mainActivityDataViewModel.getStartGameCoordinate() == 0) {
                    loadHomeFragment();
                }
                else if(mainActivityDataViewModel.getStartGameCoordinate() == 1) {
                    loadAIOrPlayerFragment();
                }
                else if(mainActivityDataViewModel.getStartGameCoordinate() == 2) {
                    loadPlayerSelectionFragment();
                }
                else if(mainActivityDataViewModel.getStartGameCoordinate() == 3){
                    loadInGameFragment();
                }
            }
        });

        mainActivityDataViewModel.menuCoordinate.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer)
            {
                if(mainActivityDataViewModel.getMenuCoordinate() == 0)
                {
                    loadHomeFragment();
                }
                else if (mainActivityDataViewModel.getMenuCoordinate() == 1)
                {
                    loadSettingsFragment();
                }
                else if (mainActivityDataViewModel.getMenuCoordinate() == 2)
                {
                    loadLeaderboardsFragment();
                }
                else if (mainActivityDataViewModel.getMenuCoordinate() == 3)
                {
                    loadInGameFragment();
                }
            }
        });

    }

    private void loadProfileSelectionFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container); //find frame

        if(frag==null)
        {
            fm.beginTransaction().add(R.id.f_container, profileSelectionFragment).commit();
        }
        else
        {
            fm.beginTransaction().replace(R.id.f_container, profileSelectionFragment).commit();
        }
    }

    private void loadSelectToCustomiseFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container); //find frame

        if(frag==null)
        {
            fm.beginTransaction().add(R.id.f_container, selectToCustomiseFragment).commit();
        }
        else
        {
            fm.beginTransaction().replace(R.id.f_container, selectToCustomiseFragment).commit();
        }
    }

    private void loadPlayerSelectionFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container); //find frame

        if(frag==null)
        {
            fm.beginTransaction().add(R.id.f_container, selectPlayers).commit();
        }
        else
        {
            fm.beginTransaction().replace(R.id.f_container, selectPlayers).commit();
        }
    }

    private void loadInGameFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container); //find frame

        if(frag==null)
        {
            fm.beginTransaction().add(R.id.f_container, game).commit();
        }
        else
        {
            fm.beginTransaction().replace(R.id.f_container, game).commit();
        }
    }

    private void loadHomeFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container); //find frame

        if(frag==null)
        {
            fm.beginTransaction().add(R.id.f_container, home).commit();
        }
        else
        {
            fm.beginTransaction().replace(R.id.f_container, home).commit();
        }
    }

    private void loadSettingsFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container);

        if(frag == null)
        {
            fm.beginTransaction().add(R.id.f_container, settings).commit();
        }
        else
        {
            fm.beginTransaction().replace(R.id.f_container, settings).commit();
        }
    }

    private void loadLeaderboardsFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container);

        if(frag == null)
        {
            fm.beginTransaction().add(R.id.f_container, leaderboards).commit();
        }
        else
        {
            fm.beginTransaction().replace(R.id.f_container, leaderboards).commit();
        }
    }

    private void loadAIOrPlayerFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container); //find frame

        if(frag==null)
        {
            fm.beginTransaction().add(R.id.f_container, AIOrP).commit();
        }
        else
        {
            fm.beginTransaction().replace(R.id.f_container, AIOrP).commit();
        }
    }
}