package com.example.scroll_tute5prep.menu_interfaces;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.scroll_tute5prep.R;
import com.example.scroll_tute5prep.User;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardViewHolder> {
    ArrayList<User> userData;

    public LeaderboardAdapter(ArrayList<User> pUserList)
    {
        this.userData = pUserList;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.leaderboard_item_layout, parent, false);
        LeaderboardViewHolder theViewHolder = new LeaderboardViewHolder(view);

        return theViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        User theUser = userData.get(position);
        holder.profileImage.setImageResource(theUser.getResourceId());
        holder.winRate.setText(String.format("%.0f",(theUser.getGameStat()).getWinRate() * 100) + "%");
        holder.countWin.setText("Wins: " + (theUser.getGameStat()).getWins());
        holder.countLose.setText("Losses: " + (theUser.getGameStat()).getLoses());
        holder.countDraw.setText("Draws: " + (theUser.getGameStat()).getDraws());
        holder.countAll.setText("Total Games: " + (theUser.getGameStat()).getTotalGames());
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}
