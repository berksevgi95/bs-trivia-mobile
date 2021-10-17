package com.bs.trivia.layout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bs.trivia.R;

public class TimesUpLayout extends DialogLayout {

    View.OnClickListener nextQuestionClickListener;

    public TimesUpLayout(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.timesup);

        LinearLayout button = findViewById(R.id.button);
        button.setVisibility(View.VISIBLE);

        LinearLayout buttonGroup = findViewById(R.id.button_group);
        buttonGroup.setVisibility(View.GONE);

        TextView correctAnswer = findViewById(R.id.correct_answer);
        correctAnswer.setVisibility(View.GONE);

        TextView timesup = findViewById(R.id.times_up);
        timesup.setVisibility(View.VISIBLE);

        TextView wrongAnswer = findViewById(R.id.wrong_answer);
        wrongAnswer.setVisibility(View.GONE);

        Button nextQuestionButton = findViewById(R.id.next_question);
        nextQuestionButton.setOnClickListener(nextQuestionClickListener);
    }

    public void setNextQuestionButtonClickEvent(View.OnClickListener listener) {
        this.nextQuestionClickListener = listener;
    }
}
