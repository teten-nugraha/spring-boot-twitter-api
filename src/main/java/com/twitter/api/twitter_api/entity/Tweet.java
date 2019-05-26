package com.twitter.api.twitter_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Tweet {

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

    @NotBlank(message = "Tweet is required")
    private String text;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "tweet",
            orphanRemoval = true
    )
    private List<Reply> replies = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "tweet",
            orphanRemoval = true
    )
    private List<Reply> favorites = new ArrayList<>();

    private Date create_At;
    private Date update_At;

    public Tweet() {
    }

    @PrePersist
    protected void onCreate() {
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.create_At = new Date();
    }
}
