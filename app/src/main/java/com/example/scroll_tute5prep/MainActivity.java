package com.example.scroll_tute5prep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.scroll_tute5prep.customise_add_profile.ProfileSelectionFragment;
import com.example.scroll_tute5prep.customise_add_profile.SelectToCustomiseFragment;

public class MainActivity extends AppCompatActivity {

    ProfileSelectionFragment profileSelectionFragment = new ProfileSelectionFragment();
    SelectToCustomiseFragment selectToCustomiseFragment = new SelectToCustomiseFragment();
    PlayerSelectionFragment selectPlayers = new PlayerSelectionFragment();
    AIOrPlayerFragment AIOrP = new AIOrPlayerFragment();
    HomeFragment home = new HomeFragment();
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

    }

    private void loadProfileSelectionFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.f_container); //find frame

        if(frag==null)
        {
            fm.beginTransaction().add(R.id.f_container, profileSelectionFragment).commit();
        }
        if(frag != null)
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
        if(frag != null)
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
        if(frag != null)
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
        if(frag != null)
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
        if(frag != null)
        {
            fm.beginTransaction().replace(R.id.f_container, home).commit();
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
        if(frag != null)
        {
            fm.beginTransaction().replace(R.id.f_container, AIOrP).commit();
        }
    }
}