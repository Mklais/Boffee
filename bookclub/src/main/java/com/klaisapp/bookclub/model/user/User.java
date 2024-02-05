package com.klaisapp.bookclub.model.user;

import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    @Column(name = "activity_points")
    private int activityPoints;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "emoji")
    private String emoji;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "profile_id")
    private UserProfile userProfile;

    public User() {

    }

    public User(String username,
                String password,
                int active,
                int activityPoints,
                String firstName,
                String lastName,
                String email,
                String emoji,
                Set<Authority> authorities,
                UserProfile userProfile) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.activityPoints = activityPoints;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emoji = emoji;
        this.authorities = authorities;
        this.userProfile = userProfile;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getActivityPoints() {
        return activityPoints;
    }

    public void setActivityPoints(int activityPoints) {
        this.activityPoints = activityPoints;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        String authoritiesString = (authorities != null) ?
                authorities.stream()
                        .map(authority -> String.valueOf(authority.getAuthorityId()))
                        .collect(Collectors.joining(", ")) : "null";

        String userProfileSummary = (userProfile != null) ? userProfile.getProfileSummary() : "null";


        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", active=" + active +
                ", activityPoints=" + activityPoints +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", emoji='" + emoji + '\'' +
                ", authorities=" + authoritiesString +
                ", userProfile=" + userProfileSummary +
                '}';
    }
}
