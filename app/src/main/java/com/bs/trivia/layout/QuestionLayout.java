package com.bs.trivia.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bs.trivia.MainActivity;
import com.bs.trivia.R;
import com.bs.trivia.entity.Question;
import com.bs.trivia.util.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionLayout extends CardView {

    View view;
    LayoutInflater mInflater;
    Integer index = 0;
    List<Question> questionList = new ArrayList<>();
    CorrectAnswerLayout correctDialog;
    WrongAnswerLayout wrongDialog;
    TimesUpLayout timesUpLayout;
    OnNextQuestionEvent onNextQuestion;
    OnStartEvent onStart;
    OnSuccessEvent onSuccess;
    OnFailureEvent onFailure;
    OnResetEvent onReset;

    public interface OnNextQuestionEvent {
        void trigger(int index);
    }
    public interface OnSuccessEvent {
        void trigger();
    }
    public interface OnFailureEvent {
        void trigger();
    }
    public interface OnResetEvent {
        void trigger();
    }
    public interface OnStartEvent {
        void trigger();
    }

    public QuestionLayout(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public QuestionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public QuestionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        correctDialog = new CorrectAnswerLayout(getContext());
        correctDialog.setNextQuestionButtonClickEvent((e) -> {
            index ++;
            onNextQuestion.trigger(index);
            render(questionList.get(index));
            correctDialog.hide();
        });

        wrongDialog = new WrongAnswerLayout(getContext());
        wrongDialog.setGoToWelcomePageEvent((e) -> {
            onReset.trigger();
            wrongDialog.hide();
        });
        wrongDialog.setTryAgainEvent((e) -> {
            onFailure.trigger();
            wrongDialog.hide();
        });

        timesUpLayout = new TimesUpLayout(getContext());
        timesUpLayout.setNextQuestionButtonClickEvent((e) -> {
            index ++;
            onNextQuestion.trigger(index);
            render(questionList.get(index));
            timesUpLayout.hide();
        });

        view = mInflater.inflate(R.layout.question_layout, this, true);
        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        view.animate()
                .translationY(100)
                .alpha(0f);
    }

    public void generateQuestions(String result) throws Exception {
        JSONArray arr = new JSONObject(result).getJSONArray("results");
        for (int i=0; i < arr.length(); i++) {
            Question question = new Question();
            question.setCategory((String) arr.getJSONObject(i).get("category"));
            question.setDifficulty((String) arr.getJSONObject(i).get("difficulty"));
            question.setQuestion((String) arr.getJSONObject(i).get("question"));
            question.setCorrect_answer((String) arr.getJSONObject(i).get("correct_answer"));
            question.setType((String) arr.getJSONObject(i).get("type"));
            question.setIncorrect_answers(new ArrayList<>());
            JSONArray subArr = arr.getJSONObject(i).getJSONArray("incorrect_answers");
            for (int j=0; j < subArr.length(); j ++) {
                question.getIncorrect_answers().add((String)subArr.get(j));
            }
            questionList.add(question);
        }
    }

    public void startGame() {
        new Thread(() -> {
            Request
                .get("https://opentdb.com/api.php?amount=10&category=9&difficulty=easy&type=multiple")
                .then((result) -> {
                        try {
                            generateQuestions(result);
                            if (questionList.size() > 0) {
                                render(questionList.get(index));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                });
        }).start();
    }

    private void render(Question questionObj) {
        MainActivity.getActivity().runOnUiThread(() -> {
            TextView questionView = findViewById(R.id.question);
            questionView.setText(questionObj.getQuestion());

            Button[] selectionList = {
                    findViewById(R.id.selection1),
                    findViewById(R.id.selection2),
                    findViewById(R.id.selection3),
                    findViewById(R.id.selection4),
            };

            List<Integer> randomList = new ArrayList<>();
            for (int i = 0; i <= 3; i++) {
                randomList.add(i);
            }
            Collections.shuffle(randomList);

            selectionList[randomList.get(0)].setText(questionObj.getIncorrect_answers().get(0));
            selectionList[randomList.get(0)].setOnClickListener((e) -> {
                wrongDialog.show();
            });
            selectionList[randomList.get(1)].setText(questionObj.getIncorrect_answers().get(1));
            selectionList[randomList.get(1)].setOnClickListener((e) -> {
                wrongDialog.show();
            });
            selectionList[randomList.get(2)].setText(questionObj.getIncorrect_answers().get(2));
            selectionList[randomList.get(2)].setOnClickListener((e) -> {
                wrongDialog.show();
            });
            selectionList[randomList.get(3)].setText(questionObj.getCorrect_answer());
            selectionList[randomList.get(3)].setOnClickListener((e) -> {
                correctDialog.show();
                onSuccess.trigger();
            });

            onStart.trigger();

            view.animate()
                .translationY(0)
                .alpha(1f);
        });
    }

    public void finishGame() {
        timesUpLayout.show();
    }

    public void onNextQuestion(OnNextQuestionEvent onNextQuestion) {
        this.onNextQuestion = onNextQuestion;
    }

    public void onStart(OnStartEvent onStart) {
        this.onStart = onStart;
    }

    public void onSuccess(OnSuccessEvent onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void onFailure(OnFailureEvent onFailure) {
        this.onFailure = onFailure;
    }

    public void onReset(OnResetEvent onReset) {
        this.onReset = onReset;
    }

}
