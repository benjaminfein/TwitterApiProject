package com.example.twitterapiproject.model;

public class Tweet {
    private String author;
    private String creationDate;
    private String textOfTweet;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getTextOfTweet() {
        return textOfTweet;
    }

    public void setTextOfTweet(String textOfTweet) {
        this.textOfTweet = textOfTweet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || o.getClass() != getClass()) { return false; }
        Tweet tweet = (Tweet) o;
        return (author == tweet.author
                || (author != null && author.equals(tweet.getAuthor()))) &&
                (creationDate == tweet.creationDate
                        || (creationDate != null && creationDate.equals(tweet.getCreationDate()))) &&
                (textOfTweet == tweet.textOfTweet
                        || (textOfTweet != null && textOfTweet.equals(tweet.getTextOfTweet())));
    }
}
