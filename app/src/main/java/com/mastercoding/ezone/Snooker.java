package com.mastercoding.ezone;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Snooker extends Fragment {
    public int seconds;
    public boolean running;
    public boolean wasRunning;
    private void sharedOnStartLogic () {}
    Button createButton;
    EditText player1;
    EditText player2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snooker, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Snooker");
        createButton = view.findViewById(R.id.btnCreate);
        player1 = view.findViewById(R.id.playerOne);
        player2 = view.findViewById(R.id.playerTwo);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerOne = player1.getText().toString();
                String playerTwo = player2.getText().toString();
                Intent intent = new Intent(getContext(), SnookerScore.class);
                intent.putExtra("player1", playerOne);
                intent.putExtra("player2", playerTwo);
                onStart();
                startActivity(intent);
            }
        });
        if ( savedInstanceState != null ) {
            savedInstanceState.getInt( "seconds" );
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        sharedOnStartLogic();
    }

    public void onStop (View view ) {
        running = false;
    }

    public void onReset ( View view ) {
        running = false;
        seconds = 0;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if ( wasRunning ) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);

    }

    public void runTimer() {
        Handler handler  = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = ( seconds % 3600 ) / 60;
                int secs = seconds % 60;

                if ( running ) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

}