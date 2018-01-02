package com.example.mikke_000.greetings;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

public class gameMP extends AppCompatActivity {
    private int width;
    private int height;
    private int player = 1;
    private Board board;
    public int boardSize;
    public int numFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        boardSize = getIntent().getExtras().getInt("boardS", 0); // send this with intent when you create slider
        numFields = boardSize*boardSize;
        board = new Board(this, boardSize);
        GridLayout layout = board.createLayout(this, width);
        layout.setY((width - height / 2));

        setContentView(layout);
    }

    public void onButtonClick(int x, int y) {
        try {
            makeMove(x, y, player);
            if (player < boardSize -1){
                player += 1;
            }
            else{
                player = 1;
            }
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
        }



    }

    public void makeMove(int x, int y, int player) {
        Cell target = this.board.getCell(x, y);
        if (!target.isEmpty()) {
            throw new IllegalArgumentException("This cell has already been played on");
        }
        if (!this.board.coordinateIsOnBoard(x, y)) {
            throw new IllegalArgumentException("Cell must be on board");
        }

        target.setPlayer(player);

    }

    public void checkWinCon(int x, int y, int player) {
        for (int k = x - 1; k < x + 2; k++) {
            for (int i = y - 1; i < y + 2; i++) {
                if (k == x & i == y || i < 0 || k < 0 || i > boardSize - 1 || k > boardSize - 1) {
                    continue;
                }
                Cell target;
                target = this.board.getCell(k, i);
                if (target.getPlayer() == player) {
                    // check if player has third in row
                    int dx = x - k; //change in x and y
                    int dy = y - i;
                    int cdx = x + (dx * 1); //  other direction y
                    int odx = x + (dx * -2); // continued direction y
                    int cdy = y + (dy * 1); // other direction x
                    int ody = y + (dy * -2); // continued direction y
                    Cell cdtarget;
                    Cell odtarget;
                    if (odx > -1 & odx < boardSize & ody > -1 & ody < boardSize) {
                        odtarget = this.board.getCell(odx, ody);
                        if (odtarget.getPlayer() == player) {
                            String p = Integer.toString(player);
                            thanksForPlaying(p);
                        }
                    }
                    if (cdx > -1 & cdx < boardSize & cdy > -1 & cdy < boardSize) {
                        cdtarget = this.board.getCell(cdx, cdy);
                        if (cdtarget.getPlayer() == player) {
                            String p = Integer.toString(player);
                            thanksForPlaying(p);
                        }
                    }
                }
            }
        }
        numFields -= 1;
        if (numFields == 0){
            thanksForPlaying("draw");
        }
    }
    public void thanksForPlaying(String p){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(gameMP.this);
        if (p.equals("draw")){
            mBuilder.setTitle("The game ended in a draw");
        }
        else{
            mBuilder.setTitle("player " + p + " won the game");
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
                Intent intent = new Intent(gameMP.this,MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = mBuilder.create();
        alertDialog.show();
    }
}


