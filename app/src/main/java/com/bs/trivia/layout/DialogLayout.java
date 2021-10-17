package com.bs.trivia.layout;

import android.content.Context;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.bs.trivia.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public abstract class DialogLayout extends BottomSheetDialog {

    public DialogLayout(Context context) {
        super(context, R.style.ThemeOverlay_App_BottomSheetDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LottieAnimationView animationView = findViewById(R.id.animation_view);
        assert animationView != null;
        animationView.setProgress(0);
        animationView.playAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LottieAnimationView animationView = findViewById(R.id.animation_view);
        assert animationView != null;
        animationView.setProgress(0);
    }
}
