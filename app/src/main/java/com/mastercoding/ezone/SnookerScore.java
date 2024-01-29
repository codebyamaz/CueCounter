package com.mastercoding.ezone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SnookerScore extends AppCompatActivity implements View.OnClickListener {
    public boolean redClick = false;
    public boolean yellowClick = false;
    public boolean greenClick = false;
    public boolean brownClick = false;
    public boolean blueClick = false;
    public boolean orangeClick = false;
    public boolean blackClick = false;
    String playerNew, playerOld;
    public int seconds;
    public boolean running;
    public boolean wasRunning;
    int top1, top2;
    private void sharedOnStartLogic () {
        running = true;
    }
    private boolean clickedText1 = false;
    private boolean clickedText2 = false;
    TextView player1, player2, score1, score2, counter, top, undo, finish;
    ImageView red, yellow, green, brown, blue, pink, black;
    int counter1, counter2;
    Spinner spinner;
    String[] fouls = {"Foul", "-4", "-5", "-6", "-7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snookar_score);

        getSupportActionBar().setTitle("Snooker: Scoreboard");
        player1 = findViewById(R.id.playerOne);
        player2 = findViewById(R.id.playerTwo);
        score1 = findViewById(R.id.tvScore1);
        score2 = findViewById(R.id.tvScore2);
        red = findViewById(R.id.red);
        yellow = findViewById(R.id.yellow);
        green = findViewById(R.id.green);
        brown = findViewById(R.id.brown);
        blue = findViewById(R.id.blue);
        pink = findViewById(R.id.pink);
        black = findViewById(R.id.black);
        spinner = findViewById(R.id.foulSpinner);
        counter = findViewById(R.id.counter);
        top = findViewById(R.id.top);
        undo = findViewById(R.id.undo);
        finish = findViewById(R.id.finish);

        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
        green.setOnClickListener(this);
        brown.setOnClickListener(this);
        blue.setOnClickListener(this);
        pink.setOnClickListener(this);
        black.setOnClickListener(this);
        player1.setOnClickListener(this);
        player2.setOnClickListener(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fouls);
        spinner.setAdapter(arrayAdapter);

        Intent intent = getIntent();
        String playerOne = intent.getStringExtra("player1").toUpperCase();
        String playerTwo = intent.getStringExtra("player2").toUpperCase();
        player1.setText(playerOne);
        player2.setText(playerTwo);
        top1 = counter1 - counter2;
        top2 = counter2 - counter1;

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!clickedText1) {
                    player1.setText( "• " + player1.getText().toString());
                    player2.setText(player2.getText().toString().replace("• ",""));
                }
                clickedText1 = true;
                clickedText2 = false;
            }
        });

        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!clickedText2) {
                    player2.setText( "• " + player2.getText().toString());
                    player1.setText(player1.getText().toString().replace("• ",""));
                }
                clickedText2 = true;
                clickedText1 = false;
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showFinishAlertDialog();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedItemView.setBackgroundColor(Color.parseColor("#cccccc"));
                if (clickedText1) {
                    if (position == 0) {
                    } else if ( position == 1 ) {
                        score1.setText("" + decreaseBrownScore());
                    } else if ( position == 2 ) {
                        score1.setText("" + decreaseBlueScore());
                    } else if ( position == 3 ) {
                        score1.setText("" + decreaseOrangeScore());
                    } else if ( position == 4 ) {
                        score1.setText("" + decreaseBlackScore());
                    }
                }
                if (clickedText2) {
                    if (position == 0) {
                    } else if ( position == 1 ) {
                        score2.setText("" + decreaseBrown2Score());
                    } else if ( position == 2 ) {
                        score2.setText("" + decreaseBlue2Score());
                    } else if ( position == 3 ) {
                        score2.setText("" + decreaseOrange2Score());
                    } else if ( position == 4 ) {
                        score2.setText("" + decreaseBlack2Score());
                    }
                }
                spinner.setSelection(0);
                if ( position == 1 && clickedText1 ) {
                    updateScore1(+4);
                }
                if ( position == 2 && clickedText1 ) {
                    updateScore1(+5);
                }
                if ( position == 3 && clickedText1 ) {
                    updateScore1(+6);
                }
                if ( position == 4 && clickedText1 ) {
                    updateScore1(+7);
                }
                if ( position == 1 && clickedText2 ) {
                    updateScore2(+4);
                }
                if ( position == 2 && clickedText2 ) {
                    updateScore2(+5);
                }
                if ( position == 3 && clickedText2 ) {
                    updateScore2(+6);
                }
                if ( position == 4 && clickedText2 ) {
                    updateScore2(+7);
                }
                playerNew = player1.getText().toString();
                playerOld = player2.getText().toString();
                if ( counter1 == counter2 && clickedText1 == false && clickedText2 == false ) {
                    top.setTextColor(Color.parseColor("#000000"));
                    top.setText("");
                } else if ( counter1 > counter2 ) {
                    top.setTextColor(Color.parseColor("#008000"));
                    top.setText(playerNew.replace("•" , "") + ": " + onTop());
                } else if ( counter2 > counter1 ) {
                    top.setTextColor(Color.parseColor("#008000"));
                    top.setText(playerOld.replace("•" , "") + ": " + onTop());
                } else if ( counter1 == counter2 && clickedText1 || clickedText2 ) {
                    top.setTextColor(Color.parseColor("#000000"));
                    top.setText("Draw");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // This method is called when nothing is selected
            }
        });

        if ( savedInstanceState != null ) {
            savedInstanceState.getInt( "seconds" );
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");

        }
        runTimer();
    }

    @Override
    protected void onStart() {
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
        TextView counter = findViewById(R.id.counter);
        Handler handler  = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = ( seconds % 3600 ) / 60;
                int secs = seconds % 60;

                String time = String.format( Locale.getDefault(),
                        "%d:%02d:%02d",
                        hours, minutes, secs);

                counter.setText(time);
                if ( running ) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int clickedImage = view.getId();
        if ( clickedImage == R.id.red && clickedText1 ) {
            updateScore1(-1);
            score1.setText("" + increaseRedScore());
        } else if ( clickedImage == R.id.red && clickedText2 ) {
            score2.setText("" + increaseRed2Score());
            updateScore2(-1);
        } else if ( clickedImage == R.id.yellow && clickedText1 ) {
            updateScore1(-2);
            score1.setText("" + increaseYellowScore());
        } else if ( clickedImage == R.id.yellow && clickedText2 ) {
            updateScore2(-2);
            score2.setText("" + increaseYellow2Score());
        } else if ( clickedImage == R.id.green && clickedText1 ) {
            updateScore1(-3);
            score1.setText("" + increaseGreenScore());
        } else if ( clickedImage == R.id.green && clickedText2 ) {
            updateScore2(-3);
            score2.setText("" + increaseGreen2Score());
        } else if ( clickedImage == R.id.brown && clickedText1 ) {
            updateScore1(-4);
            score1.setText("" + increaseBrownScore());
        } else if ( clickedImage == R.id.brown && clickedText2 ) {
            updateScore2(-4);
            score2.setText("" + increaseBrown2Score());
        }  else if ( clickedImage == R.id.blue && clickedText1 ) {
            updateScore1(-5);
            score1.setText("" + increaseBlueScore());
        }  else if ( clickedImage == R.id.blue && clickedText2 ) {
            updateScore2(-5);
            score2.setText("" + increaseBlue2Score());
        } else if ( clickedImage == R.id.pink && clickedText1 ) {
            updateScore1(-6);
            score1.setText("" + increaseOrangeScore());
        } else if ( clickedImage == R.id.pink && clickedText2 ) {
            updateScore2(-6);
            score2.setText("" + increaseOrange2Score());
        } else if ( clickedImage == R.id.black && clickedText1 ) {
            updateScore1(-7);
            score1.setText("" + increaseBlackScore());
        } else if ( clickedImage == R.id.black && clickedText2 ) {
            updateScore2(-7);
            score2.setText("" + increaseBlack2Score());
        }
        playerNew = player1.getText().toString();
        playerOld = player2.getText().toString();
        if ( counter1 == counter2 && clickedText1 == false && clickedText2 == false ) {
            top.setTextColor(Color.parseColor("#000000"));
            top.setText("");
        } else if ( counter1 > counter2 ) {
            top.setTextColor(Color.parseColor("#008000"));
            top.setText(playerNew.replace("•" , "") + ": " + onTop());
        } else if ( counter2 > counter1 ) {
            top.setTextColor(Color.parseColor("#008000"));
            top.setText(playerOld.replace("•" , "") + ": " + onTop());
        } else if ( counter1 == counter2 && clickedText1 || clickedText2 ) {
            top.setTextColor(Color.parseColor("#000000"));
            top.setText("Draw");
        }
    }
    public int increaseRedScore() {
        return ++counter1;
    }

    public int decreaseRedScore() {
        return --counter1;
    }

    public int increaseRed2Score() {
        return ++counter2;
    }

    public int decreaseRed2Score() {
        return --counter2;
    }

    public int increaseYellowScore() {
        return counter1 += 2;
    }

    public int decreaseYellowScore() {
        return counter1 -= 2;
    }

    public int increaseYellow2Score() {
        return counter2 += 2;
    }

    public int decreaseYellow2Score() {
        return counter2 -= 2;
    }

    public int increaseGreenScore() {
        return counter1 += 3;
    }

    public int decreaseGreenScore() {
        return counter1 -= 3;
    }

    public int increaseGreen2Score() {
        return counter2 += 3;
    }

    public int decreaseGreen2Score() {
        return counter2 -= 3;
    }

    public int increaseBrownScore() {
        return counter1 += 4;
    }

    public int decreaseBrownScore() {
        return counter1 -= 4;
    }

    public int increaseBrown2Score() {
        return counter2 += 4;
    }

    public int decreaseBrown2Score() {
        return counter2 -= 4;
    }

    public int increaseBlueScore() {
        return counter1 += 5;
    }

    public int decreaseBlueScore() {
        return counter1 -= 5;
    }

    public int increaseBlue2Score() {
        return counter2 += 5;
    }

    public int decreaseBlue2Score() {
        return counter2 -= 5;
    }

    public int increaseOrangeScore() {
        return counter1 += 6;
    }

    public int decreaseOrangeScore() {
        return counter1 -= 6;
    }

    public int increaseOrange2Score() {
        return counter2 += 6;
    }

    public int decreaseOrange2Score() {
        return counter2 -= 6;
    }

    public int increaseBlackScore() {
        return counter1 += 7;
    }

    public int decreaseBlackScore() {
        return counter1 -= 7;
    }

    public int increaseBlack2Score() {
        return counter2 += 7;
    }

    public int decreaseBlack2Score() {
        return counter2 -= 7;
    }

    public int onTop() {
        if ( counter1  > counter2 ) {
            return counter1 - counter2;
        } else {
            return counter2 - counter1;
        }
    }

    public int decreaseScore(int scoreOne) {
        return counter1 += scoreOne;
    }

    public void updateScore1(int scoreOne) {
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score1.setText("" + decreaseScore(scoreOne));
                playerNew = player1.getText().toString();
                playerOld = player2.getText().toString();
                if ( counter1 == counter2 && clickedText1 == false && clickedText2 == false ) {
                    top.setTextColor(Color.parseColor("#000000"));
                    top.setText("");
                } else if ( counter1 > counter2 ) {
                    top.setTextColor(Color.parseColor("#008000"));
                    top.setText(playerNew.replace("•" , "") + ": " + onTop());
                } else if ( counter2 > counter1 ) {
                    top.setTextColor(Color.parseColor("#008000"));
                    top.setText(playerOld.replace("•" , "") + ": " + onTop());
                } else if ( counter1 == counter2 && clickedText1 || clickedText2 ) {
                    top.setTextColor(Color.parseColor("#000000"));
                    top.setText("Draw");
                }
            }
        });
    }

    public int decreaseScore2(int scoreTwo) {
        return counter2 += scoreTwo;
    }

    public void updateScore2(int scoreTwo) {
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score2.setText("" + decreaseScore2(scoreTwo));
                playerNew = player1.getText().toString();
                playerOld = player2.getText().toString();
                if ( counter1 == counter2 && clickedText1 == false && clickedText2 == false ) {
                    top.setTextColor(Color.parseColor("#000000"));
                    top.setText("");
                } else if ( counter1 > counter2 ) {
                    top.setTextColor(Color.parseColor("#008000"));
                    top.setText(playerNew.replace("•" , "") + ": " + onTop());
                } else if ( counter2 > counter1 ) {
                    top.setTextColor(Color.parseColor("#008000"));
                    top.setText(playerOld.replace("•" , "") + ": " + onTop());
                } else if ( counter1 == counter2 && clickedText1 || clickedText2 ) {
                    top.setTextColor(Color.parseColor("#000000"));
                    top.setText("Draw");
                }
            }
        });
    }

    private void showFinishAlertDialog() {
        View itemView = LayoutInflater.from(this).inflate(R.layout.framefinish, null);
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
                running = false;
                playerNew = player1.getText().toString();
                playerOld = player2.getText().toString();
                if ( counter1 == counter2 && clickedText1 == false && clickedText2 == false ) {
                    top.setTextColor(Color.parseColor("#000000"));
                    top.setText("");
                } else if ( counter1 > counter2 ) {
                    top.setTextColor(Color.parseColor("#008000"));
                    top.setText( "\uD83D\uDC51" + "\n" + playerNew.replace("• " , "") + " WON");
                    top.setGravity(Gravity.CENTER);
                    Toast.makeText(SnookerScore.this, playerNew.replace("• ", "") + " WON", Toast.LENGTH_SHORT).show();
                } else if ( counter2 > counter1 ) {
                    top.setTextColor(Color.parseColor("#008000"));
                    top.setText( "\uD83D\uDC51" + "\n" + playerOld.replace("• " , "") + " WON");
                    top.setGravity(Gravity.CENTER);
                    Toast.makeText(SnookerScore.this, playerOld.replace("• ", "") + " WON", Toast.LENGTH_SHORT).show();
                } else if ( counter1 == counter2 && clickedText1 || clickedText2 ) {
                    top.setTextColor(Color.parseColor("#000000"));
                    top.setText("Draw");
                    Toast.makeText(SnookerScore.this, "Match has been Drawn!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

}