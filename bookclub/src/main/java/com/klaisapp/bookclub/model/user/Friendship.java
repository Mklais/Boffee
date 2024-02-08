package com.klaisapp.bookclub.model.user;

import com.klaisapp.bookclub.common.FriendshipStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "friendship")
@Data
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private int friendshipId;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @Column(name = "sent_at")
    private Date sentAt;

    @Column(name = "responded_at")
    private Date respondedAt;


    public Friendship() {
    }

    public Friendship(User sender, User receiver, FriendshipStatus status, Date sentAt, Date respondedAt) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.sentAt = sentAt;
        this.respondedAt = respondedAt;
    }
}
