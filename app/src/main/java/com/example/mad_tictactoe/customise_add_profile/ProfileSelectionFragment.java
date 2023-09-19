package com.example.mad_tictactoe.customise_add_profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mad_tictactoe.MainActivityData;
import com.example.mad_tictactoe.R;
import com.example.mad_tictactoe.User;

/**
 * Second Page: Sets the new Image and name to an existing user
 * or creates a new user with Image and name selected
 * Usernames are considered unique hence no two users are allowed to have
 * the same Username
 */
public class ProfileSelectionFragment extends Fragment implements ProfileSelectionAdapter.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int newImage = 0;

    public ProfileSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileSelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileSelectionFragment newInstance(String param1, String param2) {
        ProfileSelectionFragment fragment = new ProfileSelectionFragment();
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
        View pView = inflater.inflate(R.layout.fragment_profile_selection, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        //elements from the layout
        EditText editName = pView.findViewById(R.id.name);
        ImageView back = pView.findViewById(R.id.arrow);
        ImageView profile = pView.findViewById(R.id.profile);
        RecyclerView recycler = pView.findViewById(R.id.recycler);

        //set up the pictures and adapter
        ProfilePicData data = ProfilePicData.getInstance();
        int spanCount = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(gridLayoutManager);
        ProfileSelectionAdapter adapter = new ProfileSelectionAdapter(data.getData());
        adapter.setOnItemClickListener(this);
        recycler.setAdapter(adapter);

        //see if there's a user to update or create a new user otherwise

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(newImage != 0) //_________If a profile pic is set___________
                {
                    User oldUser = mainActivityDataViewModel.getUserToUpdate();
                    User dummy = new User(editName.getText().toString(), newImage);

                    //______________________Update Profile__________________
                    if(mainActivityDataViewModel.getUserToUpdate() != null)
                    {
                        if(oldUser.getName().equals(dummy.getName()) || (!mainActivityDataViewModel.nameExists(dummy.getName()))) {
                            mainActivityDataViewModel.updateUserInList(dummy);
                            mainActivityDataViewModel.setProfileCoordinate(1);
                            newImage = 0;
                        }
                        else
                        {
                            Toast.makeText(pView.getContext(), "Username already exists!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else //_________________Add new User____________________
                    {
                        if (!mainActivityDataViewModel.nameExists(dummy.getName())) {

                            mainActivityDataViewModel.addUser(dummy);
                            mainActivityDataViewModel.setProfileCoordinate(1);
                            newImage = 0;
                        }
                        else
                        {
                            Toast.makeText(pView.getContext(), "Username already exists!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } //_________No Changes Made just go back to previous fragment______
                else if (editName.getText().toString().equals("Name"))
                {
                    mainActivityDataViewModel.setProfileCoordinate(1);
                }
                else //________New Name given but no image__________
                {
                    Toast.makeText(pView.getContext(), "Please choose a profile pic!",
                            Toast.LENGTH_SHORT).show();
                }
        }
        });

        return pView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        View view = getView();
        EditText editName = view.findViewById(R.id.name);
        ImageView profile = view.findViewById(R.id.profile);


        MainActivityData mainActivityData = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        if(mainActivityData.getUserToUpdate() == null){
            editName.setText("Name");
        }
        else {
            User u = mainActivityData.getUserToUpdate();
            editName.setText(u.getName());
            profile.setImageResource(u.getResourceId());
            newImage = u.getResourceId();

        }

    }

    @Override
    public void onItemClicked(int position) {
        ImageView pic = getView().findViewById(R.id.profile);
        pic.setImageResource(position);
        newImage = position;
    }

}