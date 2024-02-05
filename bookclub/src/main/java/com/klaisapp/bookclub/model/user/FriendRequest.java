package com.klaisapp.bookclub.model.user;

import com.klaisapp.bookclub.common.FriendshipStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "friend_requests")
@Data
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    public FriendRequest() {
    }

    public FriendRequest(User sender, User receiver, FriendshipStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }
}