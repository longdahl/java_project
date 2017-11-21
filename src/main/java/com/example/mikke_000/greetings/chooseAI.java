package com.example.mikke_000.greetings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class chooseAI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ai);

        Button button1 = (Button) findViewById(R.id.but1);
        Button button2 = (Button) findViewById(R.id.but2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGameAI();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGameAIFirst();
            }
        });
    }

    private void goToGameAI(){
        Intent intent = new Intent(chooseAI.this,gameAI.class);
        startActivity(intent);
    }
    private void goToGameAIFirst(){
        Intent intent = new Intent(chooseAI.this,GameAIFirst.class);
        startActivity(intent);
    }
}

