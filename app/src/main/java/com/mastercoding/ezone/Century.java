package com.mastercoding.ezone;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mastercoding.ezone.R;

import java.util.ArrayList;
import java.util.Locale;

public class Century extends Fragment implements View.OnClickListener {
    public boolean playersAdd = false;
    public int seconds;
    public boolean running;
    public boolean wasRunning;
    int count = 0;
    public void sharedOnStartLogic(boolean run) {
        running = run;
    }

    TextView score, myPlayer, counter2, undo;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<PlayersName> arrayList = new ArrayList<>();
    ImageView red, yellow, green, brown, blue, pink, black, stop, play;
    PlayersName playersName = new PlayersName();
    Spinner spinner;
    String[] fouls = {"Foul", "-4", "-5", "-6", "-7", "-10"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_century, container, false);
        View view2 = inflater.inflate(R.layout.content_list_item, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Century: Scoreboard");

        recyclerView = view.findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(arrayList, getContext());

        red = view.findViewById(R.id.red);
        yellow = view.findViewById(R.id.yellow);
        green = view.findViewById(R.id.green);
        brown = view.findViewById(R.id.brown);
        blue = view.findViewById(R.id.blue);
        pink = view.findViewById(R.id.pink);
        black = view.findViewById(R.id.black);
        score = view2.findViewById(R.id.tvScoreOne);
        spinner = view.findViewById(R.id.foulSpinner);
        myPlayer = view2.findViewById(R.id.tvPlayerOne);
        counter2 = view.findViewById(R.id.counter2);
        undo = view.findViewById(R.id.undo);
        stop = view.findViewById(R.id.stop);
        play = view.findViewById(R.id.play);

        if ( count == 0 ) {
            showRedBallAlertDialog();
            count++;
        }

        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
        green.setOnClickListener(this);
        brown.setOnClickListener(this);
        blue.setOnClickListener(this);
        pink.setOnClickListener(this);
        black.setOnClickListener(this);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedOnStartLogic(false);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedOnStartLogic(true);
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, fouls);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedItemView.setBackgroundColor(Color.parseColor("#cccccc"));
                if (position == 0) {
                } else if (position == 1 && myAdapter.click1) {
                    updateScore(0, +4);
                    updateCount(0, -4);
                } else if (position == 2 && myAdapter.click1) {
                    updateScore(0, +5);
                    updateCount(0, -5);
                } else if (position == 3 && myAdapter.click1) {
                    updateScore(0, +6);
                    updateCount(0, -6);
                } else if (position == 4 && myAdapter.click1) {
                    updateScore(0, +7);
                    updateCount(0, -7);
                } else if (position == 5 && myAdapter.click1) {
                    updateScore(0, +10);
                    updateCount(0, -10);
                }
                if (position == 1 && myAdapter.click2) {
                    updateScore(1, +4);
                    updateCount(1, -4);
                } else if (position == 2 && myAdapter.click2) {
                    updateScore(1, +5);
                    updateCount(1, -5);
                } else if (position == 3 && myAdapter.click2) {
                    updateScore(1, +6);
                    updateCount(1, -6);
                } else if (position == 4 && myAdapter.click2) {
                    updateScore(1, +7);
                    updateCount(1, -7);
                } else if (position == 5 && myAdapter.click2) {
                    updateScore(1, +10);
                    updateCount(1, -10);
                }
                if (position == 1 && myAdapter.click3) {
                    updateScore(2, +4);
                    updateCount(2, -4);
                } else if (position == 2 && myAdapter.click3) {
                    updateScore(2, +5);
                    updateCount(2, -5);
                } else if (position == 3 && myAdapter.click3) {
                    updateScore(2, +6);
                    updateCount(2, -6);
                } else if (position == 4 && myAdapter.click3) {
                    updateScore(2, +7);
                    updateCount(2, -7);
                } else if (position == 5 && myAdapter.click3) {
                    updateScore(2, +10);
                    updateCount(2, -10);
                }
                if (position == 1 && myAdapter.click4) {
                    updateScore(3, +4);
                    updateCount(3, -4);
                } else if (position == 2 && myAdapter.click4) {
                    updateScore(3, +5);
                    updateCount(3, -5);
                } else if (position == 3 && myAdapter.click4) {
                    updateScore(3, +6);
                    updateCount(3, -6);
                } else if (position == 4 && myAdapter.click4) {
                    updateScore(3, +7);
                    updateCount(3, -7);
                } else if (position == 5 && myAdapter.click4) {
                    updateScore(3, +10);
                    updateCount(3, -10);
                }
                if (position == 1 && myAdapter.click5) {
                    updateScore(4, +4);
                    updateCount(4, -4);
                } else if (position == 2 && myAdapter.click5) {
                    updateScore(4, +5);
                    updateCount(4, -5);
                } else if (position == 3 && myAdapter.click5) {
                    updateScore(4, +6);
                    updateCount(4, -6);
                } else if (position == 4 && myAdapter.click5) {
                    updateScore(4, +7);
                    updateCount(4, -7);
                } else if (position == 5 && myAdapter.click5) {
                    updateScore(4, +10);
                    updateCount(4, -10);
                }
                if (position == 1 && myAdapter.click6) {
                    updateScore(5, +4);
                    updateCount(5, -4);
                } else if (position == 2 && myAdapter.click6) {
                    updateScore(5, +5);
                    updateCount(5, -5);
                } else if (position == 3 && myAdapter.click6) {
                    updateScore(5, +6);
                    updateCount(5, -6);
                } else if (position == 4 && myAdapter.click6) {
                    updateScore(5, +7);
                    updateCount(5, -7);
                } else if (position == 5 && myAdapter.click6) {
                    updateScore(5, +10);
                    updateCount(5, -10);
                }
                if (position == 1 && myAdapter.click7) {
                    updateScore(6, +4);
                    updateCount(6, -4);
                } else if (position == 2 && myAdapter.click7) {
                    updateScore(6, +5);
                    updateCount(6, -5);
                } else if (position == 3 && myAdapter.click7) {
                    updateScore(6, +6);
                    updateCount(6, -6);
                } else if (position == 4 && myAdapter.click7) {
                    updateScore(6, +7);
                    updateCount(6, -7);
                } else if (position == 5 && myAdapter.click7) {
                    updateScore(6, +10);
                    updateCount(6, -10);
                }
                if (position == 1 && myAdapter.click8) {
                    updateScore(7, +4);
                    updateCount(7, -4);
                } else if (position == 2 && myAdapter.click8) {
                    updateScore(7, +5);
                    updateCount(7, -5);
                } else if (position == 3 && myAdapter.click8) {
                    updateScore(7, +6);
                    updateCount(7, -6);
                } else if (position == 4 && myAdapter.click8) {
                    updateScore(7, +7);
                    updateCount(7, -7);
                } else if (position == 5 && myAdapter.click8) {
                    updateScore(7, +10);
                    updateCount(7, -10);
                }
                if (position == 1 && myAdapter.click9) {
                    updateScore(8, +4);
                    updateCount(8, -4);
                } else if (position == 2 && myAdapter.click9) {
                    updateScore(8, +5);
                    updateCount(8, -5);
                } else if (position == 3 && myAdapter.click9) {
                    updateScore(8, +6);
                    updateCount(8, -6);
                } else if (position == 4 && myAdapter.click9) {
                    updateScore(8, +7);
                    updateCount(8, -7);
                } else if (position == 5 && myAdapter.click9) {
                    updateScore(8, +10);
                    updateCount(8, -10);
                }
                if (position == 1 && myAdapter.click10) {
                    updateScore(9, +4);
                    updateCount(9, -4);
                } else if (position == 2 && myAdapter.click10) {
                    updateScore(9, +5);
                    updateCount(9, -5);
                } else if (position == 3 && myAdapter.click10) {
                    updateScore(9, +6);
                    updateCount(9, -6);
                } else if (position == 4 && myAdapter.click10) {
                    updateScore(9, +7);
                    updateCount(9, -7);
                } else if (position == 5 && myAdapter.click10) {
                    updateScore(9, +10);
                    updateCount(9, -10);
                }
                spinner.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // This method is called when nothing is selected
            }
        });
        initRecyclerView();
        if (savedInstanceState != null) {

            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");

        }

        runTimer();
        return view;
    }

    public void onStop(View view) {
        sharedOnStartLogic(false);
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
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d",
                        hours, minutes, secs);

                counter2.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void addPlayerName() {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_players, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(itemView);

        EditText playerName = itemView.findViewById(R.id.tvPlayerName);
        alertDialogBuilder.setCancelable(false).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
                String playerNameEdit = playerName.getText().toString().toUpperCase();
                if (!playerNameEdit.isEmpty()) {
                    sharedOnStartLogic(true);
                    addPlayer(playerNameEdit);
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Enter Player Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }


    private void addPlayer(String name) {
        arrayList.add(new PlayersName(name));
        myAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu_2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            addPlayerName();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int clickedImage = view.getId();

        if (clickedImage == R.id.red && myAdapter.click1) {
            updateScore(0, -10);
            updateCount(0, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click1) {
            updateScore(0, -2);
            updateCount(0, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click1) {
            updateScore(0, -3);
            updateCount(0, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click1) {
            updateScore(0, -4);
            updateCount(0, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click1) {
            updateScore(0, -5);
            updateCount(0, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click1) {
            updateScore(0, -6);
            updateCount(0, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click1) {
            updateScore(0,-7);
            updateCount(0, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click2) {
            updateScore(1,-10);
            updateCount(1, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click2) {
            updateScore(1,-2);
            updateCount(1, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click2) {
            updateScore(1,-3);
            updateCount(1, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click2) {
            updateScore(1, -4);
            updateCount(1, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click2) {
            updateScore(1,-5);
            updateCount(1, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click2) {
            updateScore(1,-6);
            updateCount(1, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click2) {
            updateScore(1,-7);
            updateCount(1, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click3) {
            updateScore(2,-10);
            updateCount(2, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click3) {
            updateScore(2,-2);
            updateCount(2, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click3) {
            updateScore(2,-3);
            updateCount(2, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click3) {
            updateScore(2,-4);
            updateCount(2, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click3) {
            updateScore(2,-5);
            updateCount(2, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click3) {
            updateScore(2,-6);
            updateCount(2, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click3) {
            updateScore(2,-7);
            updateCount(2, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click4) {
            updateScore(3,-10);
            updateCount(3, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click4) {
            updateScore(3,-2);
            updateCount(3, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click4) {
            updateScore(3,-3);
            updateCount(3, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click4) {
            updateScore(3,-4);
            updateCount(3, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click4) {
            updateScore(3,-5);
            updateCount(3, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click4) {
            updateScore(3,-6);
            updateCount(3, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click4) {
            updateScore(3,-7);
            updateCount(3, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click5) {
            updateScore(4,-10);
            updateCount(4, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click5) {
            updateScore(4,-2);
            updateCount(4, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click5) {
            updateScore(4,-3);
            updateCount(4, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click5) {
            updateScore(4,-4);
            updateCount(4, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click5) {
            updateScore(4,-5);
            updateCount(4, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click5) {
            updateScore(4,-6);
            updateCount(4, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click5) {
            updateScore(4,-7);
            updateCount(4, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click6) {
            updateScore(5,-10);
            updateCount(5, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click6) {
            updateScore(5,-2);
            updateCount(5, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click6) {
            updateScore(5,-3);
            updateCount(5, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click6) {
            updateScore(5,-4);
            updateCount(5, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click6) {
            updateScore(5,-5);
            updateCount(5, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click6) {
            updateScore(5,-6);
            updateCount(5, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click6) {
            updateScore(5,-7);
            updateCount(5, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click7) {
            updateScore(6,-10);
            updateCount(6, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click7) {
            updateScore(6,-2);
            updateCount(6, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click7) {
            updateScore(6,-3);
            updateCount(6, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click7) {
            updateScore(6,-4);
            updateCount(6, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click7) {
            updateScore(6,-5);
            updateCount(6, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click7) {
            updateScore(6,-6);
            updateCount(6, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click7) {
            updateScore(6,-7);
            updateCount(6, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click8) {
            updateScore(7,-10);
            updateCount(7, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click8) {
            updateScore(7,-2);
            updateCount(7, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click8) {
            updateScore(7,-3);
            updateCount(7, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click8) {
            updateScore(7,-4);
            updateCount(7, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click8) {
            updateScore(7,-5);
            updateCount(7, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click8) {
            updateScore(7,-6);
            updateCount(7, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click8) {
            updateScore(7,-7);
            updateCount(7, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click9) {
            updateScore(8,-10);
            updateCount(8, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click9) {
            updateScore(8,-2);
            updateCount(8, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click9) {
            updateScore(8,-3);
            updateCount(8, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click9) {
            updateScore(8,-4);
            updateCount(8, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click9) {
            updateScore(8,-5);
            updateCount(8, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click9) {
            updateScore(8,-6);
            updateCount(8, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click9) {
            updateScore(8,-7);
            updateCount(8, 7);
        }

        if (clickedImage == R.id.red && myAdapter.click10) {
            updateScore(9,-10);
            updateCount(9, 10);
        }
        if (clickedImage == R.id.yellow && myAdapter.click10) {
            updateScore(9,-2);
            updateCount(9, 2);
        }
        if (clickedImage == R.id.green && myAdapter.click10) {
            updateScore(9,-3);
            updateCount(9, 3);
        }
        if (clickedImage == R.id.brown && myAdapter.click10) {
            updateScore(9,-4);
            updateCount(9, 4);
        }
        if (clickedImage == R.id.blue && myAdapter.click10) {
            updateScore(9,-5);
            updateCount(9, 5);
        }
        if (clickedImage == R.id.pink && myAdapter.click10) {
            updateScore(9,-6);
            updateCount(9, 6);
        }
        if (clickedImage == R.id.black && myAdapter.click10) {
            updateScore(9,-7);
            updateCount(9, 7);
        }

    }
    public void updateCount(int value, int increaseBy) {
        int positionToUpdate = value;
        if (positionToUpdate < myAdapter.getItemCount()) {
            PlayersName player = myAdapter.arrayList.get(positionToUpdate);
            player.updateScore(increaseBy);
            myAdapter.notifyDataSetChanged();
        }
    }

    public void updateScore(int value, int decreaseBy) {
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positionToUpdate = value;
                if (positionToUpdate < myAdapter.getItemCount()) {
                    PlayersName player = myAdapter.arrayList.get(positionToUpdate);
                    player.updateScore(decreaseBy);
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void showRedBallAlertDialog() {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.redscore, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(itemView);

        alertDialogBuilder.setCancelable(false).setPositiveButton("10", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("1", new DialogInterface.OnClickListener() {
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
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (myAdapter.click1 ) {
                            updateCount(0, 1);
                            updateScore(0, -1);
                        }
                        if (myAdapter.click2) {
                            updateCount(1, 1);
                            updateScore(1, -1);
                        }
                        if (myAdapter.click3) {
                            updateCount(2, 1);
                            updateScore(2, -1);
                        }
                        if (myAdapter.click4) {
                            updateCount(3, 1);
                            updateScore(3, -1);
                        }
                        if (myAdapter.click5) {
                            updateCount(4, 1);
                            updateScore(4, -1);
                        }
                        if (myAdapter.click6) {
                            updateCount(5, 1);
                            updateScore(5, -1);
                        }
                        if (myAdapter.click7) {
                            updateCount(6, 1);
                            updateScore(6, -1);
                        }
                        if (myAdapter.click8) {
                            updateCount(7, 1);
                            updateScore(7, -1);
                        }
                        if (myAdapter.click9) {
                            updateCount(8, 1);
                            updateScore(8, -1);
                        }
                        if (myAdapter.click10) {
                            updateCount(9, 1);
                            updateScore(9, -1);
                        }
                    }
                });
            }
        });
    }
}