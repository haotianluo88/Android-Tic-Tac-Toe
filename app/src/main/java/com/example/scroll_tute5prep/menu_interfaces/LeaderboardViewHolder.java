package com.example.scroll_tute5prep.menu_interfaces;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scroll_tute5prep.R;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
    public ImageView profileImage;
    public TextView winRate, countWin, countLose, countDraw, countAll;

    public LeaderboardViewHolder(@NonNull View itemView)
    {
        super(itemView);
        profileImage = itemView.findViewById(R.id.leaderboardItemImage);
        winRate = itemView.findViewById(R.id.textWinRatePercent);
        countWin = itemView.findViewById(R.id.textWins);
        countLose = itemView.findViewById(R.id.textLosses);
        countDraw = itemView.findViewById(R.id.textDraws);
        countAll = itemView.findViewById(R.id.textTotal);
    }

}
