package com.viveksunny.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static int presses=1;
    public static int tappedcoin=0;
    public static int[] gamestate={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    public static boolean won=false;
    public static boolean check=true;
    public void ShowRestart() {
        Button button = (Button)findViewById(R.id.Restart);
        button.setTranslationX(-1000);
        button.animate().translationXBy(1000);
    }
    public void Starter(View view){
        check=true;
        won=false;
        for(int i=0;i<9;i++){
            gamestate[i]=-1;
        }
        ImageView img1=(ImageView)findViewById(R.id.imageView1);
        ImageView img2=(ImageView)findViewById(R.id.imageView2);
        ImageView img3=(ImageView)findViewById(R.id.imageView3);
        ImageView img4=(ImageView)findViewById(R.id.imageView4);
        ImageView img5=(ImageView)findViewById(R.id.imageView5);
        ImageView img6=(ImageView)findViewById(R.id.imageView6);
        ImageView img7=(ImageView)findViewById(R.id.imageView7);
        ImageView img8=(ImageView)findViewById(R.id.imageView8);
        ImageView img9=(ImageView)findViewById(R.id.imageView9);
        img1.setImageDrawable(null);
        img2.setImageDrawable(null);
        img3.setImageDrawable(null);
        img4.setImageDrawable(null);
        img5.setImageDrawable(null);
        img6.setImageDrawable(null);
        img7.setImageDrawable(null);
        img8.setImageDrawable(null);
        img9.setImageDrawable(null);

    }
    public void Fall(View view) {
        ImageView coin = (ImageView) view;
        tappedcoin = Integer.parseInt(coin.getTag().toString());
        if (!won && gamestate[tappedcoin-1]==-1) {
            check=true;

            presses++;
            int[][] WinCombo = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};


            coin.setTranslationY(-1000);


            if (presses % 2 == 0) {
                coin.setImageResource(R.drawable.red);
                gamestate[tappedcoin - 1] = 0;
            } else {
                coin.setImageResource(R.drawable.yellow);
                gamestate[tappedcoin - 1] = 1;
            }
            Log.i("info", coin.getTag().toString());
            coin.animate().translationYBy(1000).setDuration(300);
            System.out.println(tappedcoin);
            System.out.println(Arrays.toString(gamestate));
            for (int i = 0; i < 8; i++) {
                if (gamestate[WinCombo[i][0]] != -1) {
                    if (gamestate[WinCombo[i][0]] == gamestate[WinCombo[i][1]] && gamestate[WinCombo[i][1]] == gamestate[WinCombo[i][2]]) {
                        if (gamestate[WinCombo[i][0]] == 0) {
                            won = true;
                            Toast.makeText(this, "Winner is Red!!", Toast.LENGTH_SHORT).show();
                            ShowRestart();
                            break;
                        }
                        if (gamestate[WinCombo[i][0]] == 1) {
                            won = true;
                            Toast.makeText(this, "Winner is Yellow", Toast.LENGTH_SHORT).show();
                            ShowRestart();
                            break;
                        }
                    }
                }
            }
            for(int i=0;i<9;i++){
                if(gamestate[i]==-1){
                    check=false;
                }
            }
            if(check && !won){
                Toast.makeText(this, "It's a draw", Toast.LENGTH_SHORT).show();
                won=true;
                return;
            }
        }
    }
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

}

