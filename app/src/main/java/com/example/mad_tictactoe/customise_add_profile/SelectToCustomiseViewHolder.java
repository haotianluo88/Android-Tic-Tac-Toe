package com.example.mad_tictactoe.customise_add_profile;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_tictactoe.R;

/**
 * Select To Customise View Holder
 * By Janet Joy (20342054)
 */
public class SelectToCustomiseViewHolder extends RecyclerView.ViewHolder {
    public ImageView profile;
    public Button userButton;
    public SelectToCustomiseViewHolder(@NonNull View itemView) {
        super(itemView);
        profile = itemView.findViewById(R.id.image);
        userButton = itemView.findViewById(R.id.createUser);
    }
}
