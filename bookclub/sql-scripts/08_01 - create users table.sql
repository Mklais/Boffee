USE bookclub_database;

-- Create Users table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    profile_id INT UNIQUE,
    username VARCHAR(50) NOT NULL,
    password CHAR(68) NOT NULL,
    active TINYINT NOT NULL,
    activity_points INT NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    emoji VARCHAR(10),
    UNIQUE KEY username_unique (username),
    FOREIGN KEY (profile_id) REFERENCES user_profile(profile_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;