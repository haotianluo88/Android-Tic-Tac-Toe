package com.example.mad_tictactoe.customise_add_profile;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_tictactoe.R;

/**
 * Profile Selection View Holder
 * By Janet Joy (20342054)
 */
public class ProfileSelectionViewHolder extends RecyclerView.ViewHolder {

    public ImageView pic;
    public ProfileSelectionViewHolder(@NonNull View itemView, ViewGroup parent) {
        super(itemView);
        int hSize = parent.getMeasuredHeight() /3;
        ViewGroup.LayoutParams lp = itemView.getLayoutParams();
        lp.height = hSize;
        pic = itemView.findViewById(R.id.pic);
    }
}
