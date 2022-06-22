package com.example.twitterapiproject;

import com.example.twitterapiproject.model.Tweet;
import com.example.twitterapiproject.service.TweetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TwitterApiProjectApplicationTests {

    @Autowired
    private TweetService tweetService;
    private Tweet tweet;

    @Test
    void exchangeToResult() throws IOException {
        List<Tweet> supposedTweets = tweetService.exchangeToResult();

        List<Tweet> rightTweets = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("jsonFiles\\rightTweets.json");
        tweet = objectMapper.readValue(file, Tweet.class);
        for (int i = 0; i < 6; ) {
            rightTweets.add(tweet);
            i++;
        }

        Assert.assertTrue(supposedTweets.equals(rightTweets));
    }
}
