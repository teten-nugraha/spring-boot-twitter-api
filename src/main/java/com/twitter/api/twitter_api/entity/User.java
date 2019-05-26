package com.twitter.api.twitter_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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
