package com.example.mad_tictactoe.customise_add_profile;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_tictactoe.R;

import java.util.ArrayList;

/**
* Adapter for Profile Selection Fragment
* Don't touch :(
* By Janet Joy (20342054)
*/
public class ProfileSelectionAdapter extends RecyclerView.Adapter<ProfileSelectionViewHolder> {

    ArrayList<Integer> data;
    private OnItemClickListener itemClickListener;
    int selectedItem = -1;
    int pastPosition = -1;
    //private View view;
    public ProfileSelectionAdapter(ArrayList<Integer> pData) {
        data = pData;
        //view = pView;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ProfileSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_image_item_layout, parent,false);
        ProfileSelectionViewHolder psVH = new ProfileSelectionViewHolder(view, parent);
        return psVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileSelectionViewHolder holder, int position) {
        holder.pic.setImageResource(data.get(position));

        if(selectedItem == position){
            holder.pic.setColorFilter(Color.argb(90, 0, 0, 20));
        }
        else{
            holder.pic.setColorFilter(Color.argb(0, 0, 0, 0));

        }
        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pastPosition = selectedItem;
                selectedItem = holder.getBindingAdapterPosition();

                if(pastPosition>=0) {
                    notifyItemChanged(pastPosition);
                }
                notifyItemChanged(selectedItem);

                if (itemClickListener != null) {
                    itemClickListener.onItemClicked(data.get(selectedItem));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
