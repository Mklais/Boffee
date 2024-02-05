USE bookclub_database;

-- Create the friendship table with indexes
CREATE TABLE IF NOT EXISTS friendship (
    friendship_id INT AUTO_INCREMENT PRIMARY KEY,
    user1_id INT NOT NULL,
    user2_id INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    CONSTRAINT fk_friendship_user1 FOREIGN KEY (user1_id) 
        REFERENCES users (user_id) 
        ON DELETE CASCADE,
    CONSTRAINT fk_friendship_user2 FOREIGN KEY (user2_id) 
        REFERENCES users (user_id) 
        ON DELETE CASCADE,
    CHECK (user1_id <> user2_id) -- Ensure users are not friends with themselves
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Add indexes to improve search performance
CREATE INDEX idx_friendship_user1 ON friendship (user1_id);
CREATE INDEX idx_friendship_user2 ON friendship (user2_id);
CREATE INDEX idx_friendship_status ON friendship (status);
