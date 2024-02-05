USE bookclub_database;

-- Create UserAuthorities table to establish a many-to-many relationship
CREATE TABLE IF NOT EXISTS user_authorities (
    `user_id` INT NOT NULL,
    `authority_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `authority_id`),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
