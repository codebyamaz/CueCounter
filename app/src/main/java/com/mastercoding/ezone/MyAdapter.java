package com.mastercoding.ezone;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public int count = 0;
    private int selectedPosition = -1;
    public boolean color = true;
    public boolean click1 = false;
    public boolean click2 = false;
    public boolean click3 = false;
    public boolean click4 = false;
    public boolean click5 = false;
    public boolean click6 = false;
    public boolean click7 = false;
    public boolean click8 = false;
    public boolean click9 = false;
    public boolean click10 = false;
    public ArrayList<PlayersName> arrayList;
    private Context context;
    public MyAdapter(ArrayList<PlayersName> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lisiItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_item, parent, false);
        return new MyViewHolder(lisiItem);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PlayersName playersName = arrayList.get(position);
        holder.playerName.setText(playersName.getPlayerName());
        holder.playerScore.setText("" + playersName.getScore());
        holder.playerName.setText( selectedPosition == position? "• " + playersName.getPlayerName() : playersName.getPlayerName());
        if (playersName.getScore()==100) {
            holder.itemView.setBackgroundColor(Color.parseColor("#008000"));
            if ( count == 0 ) {
                Toast.makeText(context, "" + holder.playerName.getText().toString().replace("• ", "") + " WON", Toast.LENGTH_SHORT).show();
                count++;
            }
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.playerName.setText( "• " + playersName.getPlayerName());
                int clickedPosition = holder.getAdapterPosition();
                if ( selectedPosition != -1 && selectedPosition != clickedPosition ) {
                    notifyItemChanged(selectedPosition);
                }
                selectedPosition = clickedPosition;
                if (clickedPosition == 0) {
                    click1 = true;
                    click9 = false;
                    click8 = false;
                    click7 = false;
                    click6 = false;
                    click3 = false;
                    click2 = false;
                    click10 = false;
                    click4 = false;
                    click5 = false;
                } else if ( clickedPosition == 1 ) {
                    color = false;
                    click2 = true;
                    click9 = false;
                    click8 = false;
                    click7 = false;
                    click6 = false;
                    click3 = false;
                    click10 = false;
                    click1 = false;
                    click4 = false;
                    click5 = false;
                }  else if (clickedPosition == 2) {
                    click3 = true;
                    click9 = false;
                    click8 = false;
                    click7 = false;
                    click6 = false;
                    click10 = false;
                    click2 = false;
                    click1 = false;
                    click4 = false;
                    click5 = false;
                } else if ( clickedPosition == 3 ) {
                    click4 = true;
                    click9 = false;
                    click8 = false;
                    click7 = false;
                    click6 = false;
                    click3 = false;
                    click2 = false;
                    click1 = false;
                    click10 = false;
                    click5 = false;
                } else if ( clickedPosition == 4 ) {
                    click5 = true;
                    click9 = false;
                    click8 = false;
                    click7 = false;
                    click6 = false;
                    click3 = false;
                    click2 = false;
                    click1 = false;
                    click4 = false;
                    click10 = false;
                } else if ( clickedPosition == 5 ) {
                    click6 = true;
                    click9 = false;
                    click8 = false;
                    click7 = false;
                    click10 = false;
                    click3 = false;
                    click2 = false;
                    click1 = false;
                    click4 = false;
                    click5 = false;
                } else if ( clickedPosition == 6 ) {
                    click7 = true;
                    click9 = false;
                    click8 = false;
                    click10 = false;
                    click6 = false;
                    click3 = false;
                    click2 = false;
                    click1 = false;
                    click4 = false;
                    click5 = false;
                } else if ( clickedPosition == 7 ) {
                    click8 = true;
                    click9 = false;
                    click10 = false;
                    click7 = false;
                    click6 = false;
                    click3 = false;
                    click2 = false;
                    click1 = false;
                    click4 = false;
                    click5 = false;
                } else if ( clickedPosition == 8 ) {
                    click9 = true;
                    click10 = false;
                    click8 = false;
                    click7 = false;
                    click6 = false;
                    click3 = false;
                    click2 = false;
                    click1 = false;
                    click4 = false;
                    click5 = false;
                } else if ( clickedPosition == 9 ) {
                    click10 = true;
                    click9 = false;
                    click8 = false;
                    click7 = false;
                    click6 = false;
                    click3 = false;
                    click2 = false;
                    click1 = false;
                    click4 = false;
                    click5 = false;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;
        TextView playerScore;
        public MyViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.tvPlayerOne);
            playerScore = itemView.findViewById(R.id.tvScoreOne);
        }
    }
}
