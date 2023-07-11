package com.example.drawerquizapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class Fragment1 extends Fragment {

    private TextView question, result;
    private Button sky, grass, root;

    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] grassLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private char[] rootLetters = {'g', 'j', 'p', 'q', 'y'};
    private String answer = "";
    private int questionCount = 0;
    private DBHelper dbHelper;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        question = view.findViewById(R.id.textView);
        result = view.findViewById(R.id.textView2);
        sky = view.findViewById(R.id.button);
        grass = view.findViewById(R.id.button2);
        root = view.findViewById(R.id.button3);

        dbHelper = new DBHelper(getActivity());

        question.setText(getRandomLetter());

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer.equals("Sky Letters")) {
                    result.setText("Awesome Answer is Correct");
                } else {
                    result.setText("Incorrect Answer!");
                }

                handleNextQuestion();
            }
        });

        grass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer.equals("Grass Letters")) {
                    result.setText("Awesome your Answer is Correct");
                } else {
                    result.setText("Incorrect Answer");
                }

                handleNextQuestion();
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer.equals("Root Letters")) {
                    result.setText("Awesome your Answer is Correct");
                } else {
                    result.setText("Incorrect Answer");
                }

                handleNextQuestion();
            }
        });
    }

    private void handleNextQuestion() {
        questionCount++;

        if (questionCount < 5) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    question.setText(getRandomLetter());
                    result.setText("");
                }
            }, 5000);
        } else {
            // Store result in the database
            storeResult();

            // Load Fragment2
            Fragment2 fragment2 = new Fragment2();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, fragment2)
                    .commit();
        }
    }

    private void storeResult() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.ResultEntry.COLUMN_RESULT, result.getText().toString());
        db.insert(DBContract.ResultEntry.TABLE_NAME, null, values);
        db.close();
    }

    private String getRandomLetter() {
        Random random = new Random();
        int category = random.nextInt(3);
        char letter;

        switch (category) {
            case 0:
                answer = "Sky Letters";
                letter = skyLetters[random.nextInt(skyLetters.length)];
                break;
            case 1:
                answer = "Grass Letters";
                letter = grassLetters[random.nextInt(grassLetters.length)];
                break;
            default:
                answer = "Root Letters";
                letter = rootLetters[random.nextInt(rootLetters.length)];
                break;
        }

        return String.valueOf(letter);
    }
}
