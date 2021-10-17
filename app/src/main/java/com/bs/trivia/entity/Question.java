package com.bs.trivia.entity;


//{"category":"General Knowledge","type":"multiple","difficulty":"easy","question":"Virgin Trains, Virgin Atlantic and Virgin Racing, are all companies owned by which famous entrepreneur?   ","correct_answer":"Richard Branson","incorrect_answers":["Alan Sugar","Donald Trump","Bill Gates"]}

import java.util.List;

import lombok.Data;

@Data
public class Question {
    String category;
    String type;
    String difficulty;
    String question;
    String correct_answer;
    List<String> incorrect_answers;
}
