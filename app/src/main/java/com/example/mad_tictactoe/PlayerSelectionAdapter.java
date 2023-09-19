package com.example.mad_tictactoe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_tictactoe.customise_add_profile.SelectToCustomiseViewHolder;

import java.util.ArrayList;

/**
 * Select To Player Selection Adapter
 * By Janet Joy (20342054)
 */
public class PlayerSelectionAdapter extends RecyclerView.Adapter<SelectToCustomiseViewHolder> {
    ArrayList<User> data;
    MainActivityData mainActivityDataViewModel;

    public PlayerSelectionAdapter(MainActivityData viewMo){
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
                holder.profile.setColorFilter(Color.argb(90, 0, 0, 20));
                if(mainActivityDataViewModel.getPlayerOne() == null)
                {
                    mainActivityDataViewModel.setPlayerOne(singleData);

                    if(mainActivityDataViewModel.getVsAI())
                    {
                        mainActivityDataViewModel.setStartGameCoordinate(3);
                    }
                }
                else if(mainActivityDataViewModel.getPlayerTwo() == null)
                {

                    if (!mainActivityDataViewModel.getPlayerOne().isEqual(singleData)) {
                        mainActivityDataViewModel.setPlayerTwo(singleData);
                        //load next activity here
                        mainActivityDataViewModel.setStartGameCoordinate(3);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
