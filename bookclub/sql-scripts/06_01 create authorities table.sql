USE bookclub_database;

-- Create Authorities table
CREATE TABLE IF NOT EXISTS authorities (
    `authority_id` INT AUTO_INCREMENT PRIMARY KEY,
    `authority_name` VARCHAR(50) NOT NULL,
    UNIQUE KEY authorities_idx_1 (authority_id),
    UNIQUE KEY authorities_idx_2 (authority_name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
