package com.klaisapp.bookclub.model.user;

import com.klaisapp.bookclub.common.FriendshipStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "friendship")
@Data
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private int friendshipId;


    @ManyToOne
    @JoinColumn(name = "user1_id", referencedColumnName = "user_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", referencedColumnName = "user_id")
    private User user2;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    public Friendship() {
    }

    public Friendship(User user1, User user2, FriendshipStatus status) {
        this.user1 = user1;
        this.user2 = user2;
        this.status = status;
    }
}
