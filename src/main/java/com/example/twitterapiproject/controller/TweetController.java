package com.example.twitterapiproject.controller;

import com.example.twitterapiproject.model.Tweet;
import com.example.twitterapiproject.service.TweetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TweetController {
    final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("api/tweetTheMostPopularPersons")
    public List<Tweet> takeTheMostPopularTweets() throws JsonProcessingException {
        return tweetService.exchangeToResult();
    }
}
