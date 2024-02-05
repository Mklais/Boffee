USE bookclub_database;

-- Insert data into the UserAuthorities table
INSERT INTO user_authorities (user_id, authority_id) VALUES
(1, 1), -- Laura has ROLE_ADMIN
(2, 2), -- Leslie has ROLE_BOOKLOVER
(3, 3), -- Mary has ROLE_READER
(4, 2), -- John has ROLE_BOOKLOVER
(5, 3), -- Alice has ROLE_READER
(6, 2), -- Bob has ROLE_BOOKLOVER
(7, 1), -- Eva has ROLE_ADMIN
(8, 3); -- Michael has ROLE_READER