package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int[] gameState={0,0,0,0,0,0,0,0,0};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean isSomebodyWon=false;
    int playerTurn=1;
    public void appear(View view){
        ImageView coin = (ImageView) view;
        int tappedCounter=Integer.parseInt(coin.getTag().toString());
        if(gameState[tappedCounter]==0&&!isSomebodyWon) {
            gameState[tappedCounter] = playerTurn;
            coin.setTranslationY(-1500);
            if (playerTurn == 1) {
                coin.setImageResource(R.drawable.red);
                playerTurn = 2;
            } else if (playerTurn == 2) {
                coin.setImageResource(R.drawable.yellow);
                playerTurn = 1;
            }
            coin.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPositions : winningPositions) {
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]]
                        && gameState[winningPositions[1]] == gameState[winningPositions[2]]
                        && gameState[winningPositions[0]] != 0) {
                    String winner = "";
                    if (playerTurn == 1) winner = "Yellow";
                    else if (playerTurn == 2) winner = "Red";
                    isSomebodyWon=true;
                    TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
                    Button playAgainButton=(Button) findViewById(R.id.playAgainButton);

                    winnerTextView.setText(winner+" has won");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        isSomebodyWon=false;
        playerTurn=1;
        for(int i=0; i<gameState.length;i++){
            gameState[i]=0;
        }
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < grid.getChildCount(); i++) {
            Log.i("tag","x"+i);
            ImageView coin = (ImageView) grid.getChildAt(i);
            coin.setImageDrawable(null);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}