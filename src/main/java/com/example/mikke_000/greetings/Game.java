package com.example.mikke_000.greetings;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Game extends AppCompatActivity {
    public int count = 0;

    public String name1;
    public String name2;
    public String but;
    public String board;
    public Map<Integer, String> dict = new HashMap<Integer, String>();
    public Map<Integer,String> movemap = new HashMap<Integer,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        name1 = intent.getExtras().getString("n1");
        name2 = intent.getExtras().getString("n2");
        board = "000000000";
        getMovemap();




        final TextView mtextView = (TextView) findViewById(R.id.textView);
        mtextView.setText("It is " + name1 +"'s turn");

        final String butprefix = "ImgB";
        int resID;
        final ImageButton btn[] = new ImageButton[9];
        for (int i = 0; i < 9; i++) {
            final int _i = i;
            but = movemap.get(_i);
            resID = getResources().getIdentifier(butprefix + but, "id", getPackageName());
            btn[i] = (ImageButton) findViewById(resID);
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    takeATurn(_i, btn[_i],mtextView);
                }
            });
        }
    }
    private void getMovemap(){
        int c = 0;
        for (int i = 1; i < 4; i++){
            for (int k = 1; k < 4; k++){
                String stri = Integer.toString(i);
                String strk = Integer.toString(k);
                String in = stri + strk;
                movemap.put(c,in);
                c += 1;
            }
        }
    }

    public void takeATurn(Integer i,ImageButton b, TextView w) {

        getMovemap();
        if (!dict.containsKey(i)) {
            if (count % 2 == 0) {
                board = board.substring(0,i) + "1" + board.substring(i+1);
                b.setImageResource(R.drawable.ttto);
                count = count + 1;
                dict.put(i, "1");
                checkIfGameOver("1");
                w.setText("It is " + name2 + "'s turn");


            } else {
                board = board.substring(0,i) + "2" + board.substring(i+1);
                b.setImageResource(R.drawable.tttx);
                count = count + 1;
                dict.put(i, "2");
                checkIfGameOver("2");
                w.setText("It is " + name1 + "'s turn");
            }

        } else {
            Toast.makeText(getApplicationContext(), "This field has already been crossed off", Toast.LENGTH_LONG)
                    .show();
        }
    }
    public void checkIfGameOver(String player) {
        for (int i = 0; i < 9; i += 3) {
            if (board.substring(0 + i, 1 + i).equals("0")) {
                continue;
            }
            if (board.substring(0 + i, 1 + i).equals(board.substring(1 + i, 2 + i)) & board.substring(1 + i, 2 + i).equals(board.substring(2 + i, 3 + i))) {
                thanksForPlaying(player);
                return;
            }
        }
        for (int k = 0; k < 3; k++) {
            if (board.substring(0 + k, 1 + k).equals("0")) {
                continue;
            }
            if (board.substring(0 + k, 1 + k).equals(board.substring(3 + k, 4 + k)) & board.substring(3 + k, 4 + k).equals(board.substring(6 + k, 7 + k))) {
                thanksForPlaying(player);
                return;
            }
        }
        if (board.substring(4, 5).equals("0")) {
        } else {
            if (board.substring(0, 1).equals(board.substring(4, 5)) & board.substring(4, 5).equals(board.substring(8, 9))) {
                thanksForPlaying(player);
                return;
            }
            if (board.substring(2, 3).equals(board.substring(4, 5)) & board.substring(4, 5).equals(board.substring(6, 7))) {
                thanksForPlaying(player);
                return;
            }
            int count = 0;
            for (int i = 0; i < 9; i++) {
                if (board.substring(i, i + 1).equals("0")) {
                    break;
                } else {
                    count = i;
                }
                if (count == 8) {
                    thanksForPlaying("draw");
                    return;
                }
            }
        }
        return;
    }


    public void thanksForPlaying(String p){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Game.this);
        if (p.equals("1")){
            mBuilder.setTitle(name1 + " won the game");
        }
        if (p.equals("2")){
            mBuilder.setTitle(name2 + " won the game");
        }
        if (p.equals("draw")){
            mBuilder.setTitle("The game ended in a draw");
        }
        mBuilder.setMessage("Thanks for playing");
        mBuilder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = getIntent();
                startActivity(intent);
            }
        });
        mBuilder.setNegativeButton("Main menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Game.this,MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = mBuilder.create();
        alertDialog.show();
    }
}