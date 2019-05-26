package com.connect.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    // 0 = yellow, 1 = red
    int activePlayer = 0;
    boolean gameIsActive = true;

    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);

    }

    public void dropIn(View view)
    {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive)
        {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions)
            {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2)
                {
                    // Someone has won!

                    gameIsActive = false;

                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0)
                    {

                        winner = "Yellow";

                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);
                    if (winner == "Red")
                    {
                        layout.setBackgroundResource(R.color.Red);
                    }
                    if (winner == "Yellow")
                    {
                        layout.setBackgroundResource(R.color.Yellow);
                    }

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameState) {

                        if (counterState == 2) gameIsOver = false;

                    }

                    if (gameIsOver)
                    {

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);
                        layout.setBackgroundResource(R.color.Green);

                    }
                }

            }
        }
    }

    public void playAgain(View view)
    {
        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++)
        {

            gameState[i] = 2;

        }

        imageView.setImageResource(0);
        imageView2.setImageResource(0);
        imageView3.setImageResource(0);
        imageView4.setImageResource(0);
        imageView5.setImageResource(0);
        imageView6.setImageResource(0);
        imageView7.setImageResource(0);
        imageView8.setImageResource(0);
        imageView9.setImageResource(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_exit)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

