package com.mastercoding.ezone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private boolean shouldReplaceFragment = true;
    public boolean centuryClick = false;
    public int seconds;
    public boolean running;
    public boolean wasRunning;
    private int count = 0;
    public void sharedOnStartLogic(boolean run) {
        running = run;
    }

    FrameLayout frameLayout;
    BottomNavigationView navBar;
    Snooker snooker = new Snooker();
    Century century = new Century();
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        navBar = findViewById(R.id.navBar);
        navBar.setOnItemSelectedListener(this);
        navBar.setSelectedItemId(R.id.snooker);
        if (savedInstanceState != null) {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");

        }
        runTimer();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onStop(View view) {
        running = false;
    }

    public void onReset(View view) {
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
        if (wasRunning) {
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
        TextView counter = findViewById(R.id.counter);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.snooker && centuryClick) {
            century = new Century();
            if (isCenturyFragmentVisible()) {
                showSnookerAlertDialog();
            }
            return true;
        } else if (id == R.id.century) {
            centuryClick = true;
            if (shouldReplaceFragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, century).commit();
            }
            count++;
            return true;
        } else if (id == R.id.snooker) {
            century = new Century();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, snooker).commit();
            return true;
        }
        return false;
    }
    private void showSnookerAlertDialog() {
        View itemView = LayoutInflater.from(this).inflate(R.layout.shifttosnooker, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(itemView);
        alertDialogBuilder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#B68585"));
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#B68585"));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                shouldReplaceFragment = true;
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, snooker).commit();
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                shouldReplaceFragment = false;
            }
        });
    }

    private boolean isCenturyFragmentVisible() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if ( currentFragment instanceof Century ) {
            return true;
        } else {
            return false;
        }
    }

}