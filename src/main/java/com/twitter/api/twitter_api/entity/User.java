package com.twitter.api.twitter_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.twitter.api.twitter_api.Favorit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "fullname is required")
    private String fullname;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    private String location;
    private String websiteUrl;
    private String bio;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<Tweet> tweets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<Follower> following = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<Reply> replies = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<Favorit> favorites = new ArrayList<>();

    @Transient
    private String confirmPassword;
    private Date create_At;
    private Date update_At;

    public User() {
    }

    @PrePersist
    protected void onCreate() {
        this.create_At = new Date();
    }

    @PreUpdate
    protected  void onUpdate() {
        this.update_At = new Date();
    }
}
