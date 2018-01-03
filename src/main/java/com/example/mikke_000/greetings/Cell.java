package com.example.mikke_000.greetings;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.apache.poi.hssf.util.HSSFColor;

import java.util.ArrayList;
import java.util.HashMap;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

/**
 * A cell on a Go board.
 * The cell must know where it is placed on the board
 */
public class Cell {
    private Board board;
    private int x, y, player;
    private ImageButton button;

    public Cell(Board board, int x, int y) {
        this.board = board;
        this.player = 0;
        this.x = x;
        this.y = y;
    }

    public boolean isEmpty() {
        return this.player == 0;
    }

    public void setPlayer(int player) {

        this.player = player;

        if (this.button != null) {
            int newBg;
            if (player == 1){
                newBg = R.drawable.red;
            }
            else if(player == 2){
                newBg = R.drawable.blue;
            }
            else if(player == 3){
                newBg = R.drawable.green;
            }
            else if(player == 4){
                newBg = R.drawable.yellow;
            }
            else if(player == 5){
                newBg = R.drawable.brown;
            }
            else{
                newBg = R.drawable.grey;
            }
            this.button.setImageResource(newBg);
        }
    }

    public ImageButton createButton(Context context) {
        ImageButton btn = new ImageButton(context);
        btn.setPadding(0, 0, 0, 0);
        btn.setImageResource(R.drawable.white);
        btn.setScaleType(ImageView.ScaleType.FIT_XY);
        btn.setAdjustViewBounds(true);

        this.button = btn;
        return btn;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getPlayer() { return player; }
    public ImageButton getButton() { return this.button; }
    public void setButton(ImageButton button) { this.button = button; }
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }
}
