USE bookclub_database;

-- Create Friendship table
CREATE TABLE IF NOT EXISTS friendship (
    friendship_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    sent_at TIMESTAMP NOT NULL,
    responded_at TIMESTAMP NULL,
    CONSTRAINT fk_sender_id FOREIGN KEY (sender_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_receiver_id FOREIGN KEY (receiver_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CHECK (sender_id != receiver_id) -- Ensure a user cannot send a friend request to themselves
) ENGINE=InnoDB DEFAULT CHARSET=latin1;