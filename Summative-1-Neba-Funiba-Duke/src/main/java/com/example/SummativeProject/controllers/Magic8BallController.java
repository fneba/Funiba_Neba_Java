package com.example.SummativeProject.controllers;

import com.example.SummativeProject.models.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/magic")
@ResponseStatus(value = HttpStatus.CREATED)
public class Magic8BallController {

    private List<Answer> answerList;

    public Magic8BallController (){
        answerList = new ArrayList<>();

        answerList.add(createAnswer(1,"Yes."));
        answerList.add(createAnswer(2,"No."));
        answerList.add(createAnswer(3,"Ask again."));
        answerList.add(createAnswer(4,"Maybe."));
        answerList.add(createAnswer(5,"Your Parent knows the answer."));
        answerList.add(createAnswer(6,"Your professor may know."));
    }




    @PostMapping
    public Answer getRandomAnswer(@RequestBody Answer question){
        Random random = new Random();
        int randomIndex = random.nextInt(answerList.size());
        Answer magicAnswer = answerList.get(randomIndex);
        magicAnswer.setQuestion(question.getQuestion());
        return magicAnswer;
    }


    private Answer createAnswer(int id, String answer) {
        Answer AnswerObj = new Answer();
        AnswerObj.setId(id);
        AnswerObj.setAnswer(answer);
        return AnswerObj;
    }

}
