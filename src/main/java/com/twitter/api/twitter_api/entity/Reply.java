package com.twitter.api.twitter_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Setter
@Getter
public class Reply {

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

    @NotBlank(message = "reply is required")
    private String reply;

    private Date create_At;
    private Date update_At;


    public Reply() {
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
