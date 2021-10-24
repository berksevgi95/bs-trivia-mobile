package com.bs.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.bs.trivia.fragments.QuestionFragment;

public class MainActivity extends AppCompatActivity {

    private static Activity activity;

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        QuestionFragment.cancelTimer();
    }
}