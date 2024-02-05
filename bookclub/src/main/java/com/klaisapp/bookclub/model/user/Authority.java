package com.klaisapp.bookclub.model.user;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private int authorityId;

    @Column(name = "authority_name")
    private String authorityName;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private Set<User> users;

    public Authority() {

    }

    public Authority(String authorityName, Set<User> users) {
        this.authorityName = authorityName;
        this.users = users;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                ", authorityName='" + authorityName + '\'' +
                ", users=" + users +
                '}';
    }
}
