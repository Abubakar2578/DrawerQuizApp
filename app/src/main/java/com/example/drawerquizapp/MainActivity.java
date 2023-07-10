package com.example.drawerquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView question , result;
    Button sky , grass , root;

    private char[] skyLetters = {'b' , 'd' , 'f' , 'h' ,'k' , 'l' , 't'};
    private char[] grassLetters= {'a' , 'c'  , 'e' , 'i' , 'm' ,'n' , 'o','r' ,'s' , 'u', 'v' ,'w' ,'x' ,'z'};
    private char[] rootLetters = {'g' , 'j' , 'p', 'q', 'y'};
    private String answer = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.textView);
        result = findViewById(R.id.textView2);
        sky = findViewById(R.id.button);
        grass = findViewById(R.id.button2);
        root = findViewById(R.id.button3);

        question.setText(getRandomLetter());


        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answer == "Sky Letters"){
                    result.setText("Awesome Answer is Correct");
                }
                else{
                    result.setText("Incorrect Answer!");
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        question.setText(getRandomLetter());
                        result.setText("");
                    }
                },5000);

            }
        });


        grass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answer == "Grass Letters"){
                    result.setText("Awesome your Answer is Correct");
                }
                else {
                    result.setText("Incorrect Answer");
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        question.setText(getRandomLetter());
                        result.setText("");
                    }
                },5000);


            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer == "Root Letters"){
                    result.setText("Awesome your Answer is Correct");
                }
                else {
                    result.setText("Incorrect Answer");
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        question.setText(getRandomLetter());
                        result.setText("");
                    }
                },5000);
            }
        });

    }

    private String getRandomLetter(){

        Random random = new Random();
        int category  = random.nextInt(2);
        char letter;

        switch (category){
            case 1:
                answer = "Sky Letters";
                letter = skyLetters[random.nextInt(skyLetters.length )];
                break;
            case 2:
                answer = "Grass Letters";
                letter = grassLetters[random.nextInt(grassLetters.length )];
                break;
            default:
                answer = "Root Letters";
                letter = rootLetters[random.nextInt(rootLetters.length )];
                break;
        }
        return String.valueOf(letter);
    }

}