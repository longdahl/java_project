package com.example.mikke_000.greetings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class boardSizeSelect extends AppCompatActivity {

    public int boardsize = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_size_select);
        Button button1 = (Button) findViewById(R.id.but1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGame();
            }
        });

        final TextView tw = (TextView) findViewById(R.id.textView5);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final String text = Integer.toString(boardsize);
        tw.setText("The boardsize is set to " + text + "x" + text);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                boardsize = 3 + i;
                final String text = Integer.toString(boardsize);
                tw.setText("The boardsize is set to " + text + "x" + text);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void goToGame(){
        Intent intent = new Intent(boardSizeSelect.this,gameMP.class);
        intent.putExtra("boardS",boardsize);
        startActivity(intent);
    }
}
