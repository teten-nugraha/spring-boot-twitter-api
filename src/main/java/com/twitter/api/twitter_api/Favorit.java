package com.twitter.api.twitter_api;

import com.twitter.api.twitter_api.entity.Tweet;
import com.twitter.api.twitter_api.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Favorit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH
    )
    @JoinColumn(
            name = "tweet_id",
            nullable = false
    )
    private Tweet tweet;

    private Date create_At;
    private Date update_At;

    public Favorit() {
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.update_At = new Date();
    }
}
