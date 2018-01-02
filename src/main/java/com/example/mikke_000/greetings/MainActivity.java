package com.example.mikke_000.greetings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button2 = (Button) findViewById(R.id.but2);
        Button button3 = (Button) findViewById(R.id.but3);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBoardSizeSelect();
            }
        });

        button3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToGameAI();
            }
        }));


    }
private void goToBoardSizeSelect(){
        Intent intent = new Intent(MainActivity.this,boardSizeSelect.class);
        startActivity(intent);
    }
    private void goToGameAI(){
        Intent intent = new Intent(MainActivity.this,chooseAI.class);
        startActivity(intent);
    }
}
