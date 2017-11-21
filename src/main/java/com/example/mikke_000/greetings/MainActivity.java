package com.example.mikke_000.greetings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView Name1;
    TextView Name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name1 = (TextView) findViewById(R.id.inputName1);
        Name2 = (TextView) findViewById(R.id.inputName2);
        Button button2 = (Button) findViewById(R.id.but2);
        Button button3 = (Button) findViewById(R.id.but3);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {goToGame();

            }
        });

        button3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGameAI();
            }
        }));


    }
private void goToGame(){
    Intent intent = new Intent(MainActivity.this,Game.class);
    intent.putExtra("n1",Name1.getText().toString());
    intent.putExtra("n2",Name2.getText().toString());
    startActivity(intent);
}
    private void goToGameAI(){
        Intent intent = new Intent(MainActivity.this,chooseAI.class);
        startActivity(intent);
    }
}
