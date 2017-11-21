package com.example.mikke_000.greetings;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class GameAIFirst extends AppCompatActivity {

    public String SDtest;
    public String board;
    public String but;
    public Map<Integer, String> dict = new HashMap<Integer, String>();
    public Map<String, String> solvedict = new HashMap<String, String>();
    public Map<Integer,String> movemap = new HashMap<Integer,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_ai);
        List<String> boards = Arrays.asList("100000000","010000000","001000000","000100000","000010000","000001000","000000100","000000010","000000001");
        SDtest = "";
        int randomNum;
        Random rand = new Random();
        randomNum = rand.nextInt(9);
        getMovemap();
        board = boards.get(randomNum);

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
                    takeATurn(_i, btn[_i]);
                }
            });
        }

        String b = movemap.get(randomNum);
        String butS = "ImgB"+b;
        resID = getResources().getIdentifier(butS, "id", getPackageName());
        ImageButton but = ((ImageButton) findViewById(resID));
        but.setImageResource(R.drawable.tttx);
        dict.put(randomNum, "1");
    }
    private void readSolvedict() {
        InputStream is = getResources().openRawResource(R.raw.solvedict);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                //split by commas
                String[] token = line.split(";");

                //read the data into a hashmap
                solvedict.put(token[0], token[1]);
            }
        } catch (IOException e) {
            Log.wtf("my activity", "error reading datafile on line" + line, e);
            e.printStackTrace();
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

    public void takeATurn(Integer i,ImageButton b) {

        if (!dict.containsKey(i)) {
            b.setImageResource(R.drawable.ttto);
            dict.put(i, "2");
            int r = checkIfGameOver("2");
            if (r==0){
                board = board.substring(0,i) + "2" + board.substring(i+1);
                AImove();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "This field has already been crossed off", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void AImove() {


        readSolvedict();
        String move = solvedict.get(board);

        int moveint = 0;
        int index = 0;

        if (board.length() == 9) {
            for(char c: board.toCharArray()) {

                if (move.charAt(index) != c) {
                    moveint = index;
                }
                index += 1;
            }
        }
        board = move;
        int resID;
        getMovemap();
        String b = movemap.get(moveint);
        String butS = "ImgB"+b;
        resID = getResources().getIdentifier(butS, "id", getPackageName());
        ImageButton but = ((ImageButton) findViewById(resID));
        but.setImageResource(R.drawable.tttx);
        dict.put(moveint, "1");
        int r = checkIfGameOver("1");

    }
    public int checkIfGameOver(String player) {
        for (int i = 0; i < 9; i += 3) {
            if (board.substring(0+i,1+i).equals("0")){
                continue;
            }
            if (board.substring(0 + i, 1 + i).equals(board.substring(1 + i, 2 + i)) & board.substring(1 + i, 2 + i).equals(board.substring(2 + i, 3 + i))) {
                thanksForPlaying(player);
                return -1;
            }
        }
        for (int k = 0; k < 3; k++) {
            if (board.substring(0+k,1+k).equals("0")) {
                continue;
            }
            if (board.substring(0 + k, 1 + k).equals(board.substring(3 + k, 4 + k)) & board.substring(3 + k, 4 + k).equals(board.substring(6 + k, 7 + k))) {
                thanksForPlaying(player);
                return -1;
            }
        }
        if (board.substring(4,5).equals("0")){}
        else{
            if (board.substring(0, 1).equals(board.substring(4, 5)) & board.substring(4, 5).equals(board.substring(8, 9))) {
                thanksForPlaying(player);
                return -1;
            }
            if (board.substring(2,3).equals(board.substring(4, 5)) & board.substring(4, 5).equals(board.substring(6, 7))) {
                thanksForPlaying(player);
                return -1;
            }
        int count = 0;
        for (int i = 0; i < 9; i++){
                if (board.substring(i,i+1).equals("0")) {
                    break;
                }
                else{
                    count = i;
            }
            if (count == 8){
                    thanksForPlaying("draw");
                    return -1;
            }
        }
        }
        return 0;
    }
    public void thanksForPlaying(String p){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameAIFirst.this);
        if (p.equals("2")){
            mBuilder.setTitle("You won the game");
            mBuilder.setMessage("Thanks for playing");
        }
        if (p.equals("1")){
            mBuilder.setTitle("The AI won");
            mBuilder.setMessage("Robotic beings will rule the world");
        }
        if (p.equals("draw")){
            mBuilder.setTitle("The game ended in a draw");
            mBuilder.setMessage("... the limiting constraints of the game, saved you from the AI's superiority");
        }
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
                Intent intent = new Intent(GameAIFirst.this,MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = mBuilder.create();
        alertDialog.show();
    }
}