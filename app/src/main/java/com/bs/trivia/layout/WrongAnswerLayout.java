package com.bs.trivia.layout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bs.trivia.R;

public class WrongAnswerLayout extends DialogLayout {

    View.OnClickListener tryAgainEventListener;
    View.OnClickListener goToWelcomePageEventListener;

    public WrongAnswerLayout(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.wrong);

        LinearLayout button = findViewById(R.id.button);
        button.setVisibility(View.GONE);

        LinearLayout buttonGroup = findViewById(R.id.button_group);
        buttonGroup.setVisibility(View.VISIBLE);

        TextView correctAnswer = findViewById(R.id.correct_answer);
        correctAnswer.setVisibility(View.GONE);

        TextView timesup = findViewById(R.id.times_up);
        timesup.setVisibility(View.GONE);

        TextView wrongAnswer = findViewById(R.id.wrong_answer);
        wrongAnswer.setVisibility(View.VISIBLE);

        Button goToWelcomePageButton = findViewById(R.id.goToWelcomePage);
        goToWelcomePageButton.setOnClickListener(goToWelcomePageEventListener);
        Button tryAgainButton = findViewById(R.id.tryAgain);
        tryAgainButton.setOnClickListener(tryAgainEventListener);
    }

    public void setTryAgainEvent(View.OnClickListener listener) {
        this.tryAgainEventListener = listener;
    }

    public void setGoToWelcomePageEvent(View.OnClickListener listener) {
        this.goToWelcomePageEventListener = listener;
    }
}
