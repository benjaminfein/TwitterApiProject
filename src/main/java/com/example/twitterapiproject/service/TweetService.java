package com.example.twitterapiproject.service;

import com.example.twitterapiproject.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TweetService {
    public JsonNode findQueryTweets() throws JsonProcessingException {
        RestTemplate currentTweets = new RestTemplate();
        String fooTweet
                = "https://api.twitter.com/2/tweets/search/recent?tweet.fields=created_at,author_id&query=price";
//                = "http://localhost:8080/api/tweets";
        HttpHeaders headersTweets = new HttpHeaders();
        headersTweets.setBearerAuth("");

        HttpEntity<String> entity = new HttpEntity<>("body", headersTweets);

        ResponseEntity<String> responseForTweet
                = currentTweets.exchange(fooTweet, HttpMethod.GET, entity, String.class);

        ObjectMapper mapperTweet = new ObjectMapper();
        JsonNode rootTweet = mapperTweet.readTree(responseForTweet.getBody());

        return rootTweet;
    }

    public JsonNode findQueryUsers() throws JsonProcessingException {
        RestTemplate currentUsers = new RestTemplate();
        String fooUser
                = "https://api.twitter.com/2/users/" + findQueryTweets().findPath("author_id").asText();
        HttpHeaders headersUsers = new HttpHeaders();
        headersUsers.setBearerAuth("");

        HttpEntity<String> entity = new HttpEntity<>("body", headersUsers);

        ResponseEntity<String> responseForUser
                = currentUsers.exchange(fooUser, HttpMethod.GET, entity, String.class);

        ObjectMapper mapperUser = new ObjectMapper();
        JsonNode rootUser = mapperUser.readTree(responseForUser.getBody());

        return rootUser;
    }

    public List<Tweet> exchangeToResult() throws JsonProcessingException {
        Tweet tweet = new Tweet();
        List<Tweet> tweets = new ArrayList<>();
        JsonNode nodeTweet = findQueryTweets().findPath("data");
        Iterator<JsonNode> iteratorOfNodeTweet = nodeTweet.iterator();
        while (iteratorOfNodeTweet.hasNext()) {
            String textValue = iteratorOfNodeTweet.next().findPath("text").textValue();
            if (textValue.contains("gas") || textValue.contains("ukr")) {
                tweet.setTextOfTweet(textValue);
                tweet.setCreationDate(findQueryTweets().findPath("created_at").asText());
                tweet.setAuthor(findQueryUsers().findPath("username").asText());
                tweets.add(tweet);
            }
        }
        return tweets;
    }
}
