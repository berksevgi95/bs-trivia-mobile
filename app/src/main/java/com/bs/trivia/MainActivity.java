package com.bs.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.bs.trivia.layout.QuestionLayout;

public class MainActivity extends AppCompatActivity {

    private static Activity activity;

    private CountDownTimer timer;
    private long countdown;
    private int points;

    public MainActivity() {
        activity = this;
    }

    public static Activity getActivity() {
        return activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView index = findViewById(R.id.index);
        index.setText("1/10");

        TextView points = findViewById(R.id.points);
        points.setText("0");

        QuestionLayout questionLayout = findViewById(R.id.question_layout);
        questionLayout.startGame();
        questionLayout.onNextQuestion((i) -> {
            index.setText(i + 1 + "/10");
            this.points = this.points + ((int)countdown * 1000);
            points.setText(this.points + "");
        });
        questionLayout.onStart(() -> {
            TextView time = findViewById(R.id.time);
            timer = new CountDownTimer(16000, 1000) {
                public void onTick(long millisUntilFinished) {
                    countdown = millisUntilFinished / 1000;
                    time.setText(countdown + "");
                }

                public void onFinish() {
                    countdown = 0;
                    time.setText(countdown + "");
                    questionLayout.finishGame();
                }
            };
            timer.start();
        });
        questionLayout.onSuccess(() -> {
            timer.cancel();
        });
        questionLayout.onFailure(() -> {

        });
        questionLayout.onReset(() -> {

        });
    }

}