package com.bs.trivia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.bs.trivia.R;

public class MainFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        view.findViewById(R.id.fragment_fragment1_gotofragment2).setOnClickListener((e) -> {
            Navigation.findNavController(view).navigate(R.id.action_navFragment_to_questionFragment);
        });

        LottieAnimationView animationView = view.findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.welcome);
        animationView.playAnimation();

        Spinner difficulty = view.findViewById(R.id.difficulty);
        String[] difficulties = new String[]{
                "Easy",
                "Medium",
                "Hard"
        };
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout, difficulties);
        difficulty.setAdapter(difficultyAdapter);
        difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                QuestionFragment.difficulty = difficulties[position].toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Spinner category = view.findViewById(R.id.category);
        String[] items = new String[]{
                "General Knowledge",
                "Entertainment: Books",
                "Entertainment: Film",
                "Entertainment: Music",
                "Entertainment: Theatres",
                "Entertainment: Television",
                "Entertainment: Video Games",
                "Entertainment: Board Games",
                "Science & Nature",
                "Science: Computers",
                "Science: Mathematics",
                "Mythology",
                "Sports",
                "Geography",
                "History",
                "Politics",
                "Art",
                "Celebrities",
                "Animals",
                "Vehicles",
                "Entertainment: Comics",
                "Science: Gadgets",
                "Entertainment: Manga",
                "Entertainment: Cartoons"
        };
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_layout, items);
        category.setAdapter(categoryAdapter);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                QuestionFragment.category = position + 9;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return view;
    }
}
