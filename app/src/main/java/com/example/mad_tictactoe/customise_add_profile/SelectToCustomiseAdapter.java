package com.example.mad_tictactoe.customise_add_profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_tictactoe.MainActivityData;
import com.example.mad_tictactoe.R;
import com.example.mad_tictactoe.User;

import java.util.ArrayList;

/**
 * Select To Customise Adapter
 * By Janet Joy (20342054)
 */
public class SelectToCustomiseAdapter extends RecyclerView.Adapter<SelectToCustomiseViewHolder> {

    ArrayList<User> data;
    MainActivityData mainActivityDataViewModel;

    public SelectToCustomiseAdapter(MainActivityData viewMo){
        mainActivityDataViewModel = viewMo;
        data = viewMo.getCurrUserList();
    }

    @NonNull
    @Override
    public SelectToCustomiseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_user_item_layout,parent,false);

        SelectToCustomiseViewHolder myViewHolder = new SelectToCustomiseViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectToCustomiseViewHolder holder, int position) {

        User singleData = data.get(position);
        holder.profile.setImageResource(singleData.getResourceId());
        holder.userButton.setText(singleData.getName());

        holder.userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setUserToUpdate(singleData);
                mainActivityDataViewModel.setProfileCoordinate(2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
