package com.bs.trivia.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bs.trivia.R;
import com.bs.trivia.layout.QuestionLayout;

public class QuestionFragment extends Fragment {

    private static CountDownTimer timer;
    public static Integer category;
    public static String difficulty;

    private long countdown;
    private int points;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_fragment, container, false);

        TextView index = view.findViewById(R.id.index);
        index.setText("1/10");

        TextView points = view.findViewById(R.id.points);
        points.setText("0");

        QuestionLayout questionLayout = view.findViewById(R.id.question_layout);
        questionLayout.startGame(category, difficulty);
        questionLayout.onNextQuestion((args) -> {
            int i = (int) args[0];
            index.setText(i + 1 + "/10");
            this.points = this.points + ((int)countdown * 1000);
            points.setText(this.points + "");
        });
        questionLayout.onStart((args) -> {
            TextView time = view.findViewById(R.id.time);
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
        questionLayout.onSuccess((args) -> {
            timer.cancel();
        });
        questionLayout.onFailure((args) -> {

        });
        questionLayout.onReset((args) -> {
            timer.cancel();
        });

        return view;
    }

    public static void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

}