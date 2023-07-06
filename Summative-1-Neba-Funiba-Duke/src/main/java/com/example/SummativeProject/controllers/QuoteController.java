package com.example.SummativeProject.controllers;

import com.example.SummativeProject.models.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/quote")
@ResponseStatus(value = HttpStatus.OK)
public class QuoteController {

    private List<Quote> quoteList;

    private static int idCounter = 1;

    public QuoteController(){
        quoteList = new ArrayList<>();

        quoteList.add(new Quote(idCounter++, "Kobe Bryant", "If you're afraid to fail, then you're probably going to fail."));
        quoteList.add(new Quote(idCounter++, "Nike", "Just Do It."));
        quoteList.add(new Quote(idCounter++, "Emmanuel Etoo", "Nobody likes a smarta**."));
        quoteList.add(new Quote(idCounter++, "Franklin D. Roosevelt", "The only thing we have to fear is fear itself."));
        quoteList.add(new Quote(idCounter++, "Groot", "I am Groot!"));
        quoteList.add(new Quote(idCounter++, "Michael Corleone", "Keep your friends close, but your enemies closer."));
        quoteList.add(new Quote(idCounter++, "Forrest Gump", "Life is like a box of chocolates. You never know what you’re gonna get."));
        quoteList.add(new Quote(idCounter++, "Rafiki The Baboon", "The past can hurt. But from the way I see it, you can either run from it, or learn from it."));
        quoteList.add(new Quote(idCounter++, "Oprah Winfrey", "Where there is no struggle, there is no strength."));
        quoteList.add(new Quote(idCounter++, "Thomas Jefferson", "Never spend your money before you have it."));

    }

    @GetMapping
    public Quote getRandomQuote(){
        Random random = new Random();
        int randomId = random.nextInt(quoteList.size());
        return quoteList.get(randomId);
    }
}
