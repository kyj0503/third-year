INSERT INTO User (username, password, email, created_at) VALUES
('user1', 'hashed_password1', 'user1@example.com', CURRENT_TIMESTAMP),
('user2', 'hashed_password2', 'user2@example.com', CURRENT_TIMESTAMP),
('user3', 'hashed_password3', 'user3@example.com', CURRENT_TIMESTAMP),
('user4', 'hashed_password4', 'user4@example.com', CURRENT_TIMESTAMP),
('user5', 'hashed_password5', 'user5@example.com', CURRENT_TIMESTAMP),
('user6', 'hashed_password6', 'user6@example.com', CURRENT_TIMESTAMP),
('user7', 'hashed_password7', 'user7@example.com', CURRENT_TIMESTAMP),
('user8', 'hashed_password8', 'user8@example.com', CURRENT_TIMESTAMP),
('user9', 'hashed_password9', 'user9@example.com', CURRENT_TIMESTAMP),
('user10', 'hashed_password10', 'user10@example.com', CURRENT_TIMESTAMP);

INSERT INTO Place (name, address, description, opening_hours, latitude, longitude, created_at) VALUES
('Cafe A', '123 Main St', 'A cozy cafe with a nice atmosphere.', '9:00 - 21:00', 37.5665, 126.9780, CURRENT_TIMESTAMP),
('Cafe B', '456 Maple Ave', 'Great coffee and pastries.', '7:00 - 19:00', 37.5700, 126.9820, CURRENT_TIMESTAMP),
('Library C', '789 Oak Rd', 'Quiet library with a lot of study space.', '8:00 - 22:00', 37.5712, 126.9845, CURRENT_TIMESTAMP),
('Workspace D', '101 Pine St', 'Modern workspace with high-speed internet.', '8:00 - 18:00', 37.5675, 126.9750, CURRENT_TIMESTAMP),
('Cafe E', '202 Birch Ln', 'A cafe with a view of the river.', '10:00 - 20:00', 37.5690, 126.9760, CURRENT_TIMESTAMP),
('Cafe F', '303 Cedar Blvd', 'Organic coffee and a great ambiance.', '8:00 - 22:00', 37.5645, 126.9795, CURRENT_TIMESTAMP),
('Library G', '404 Spruce Dr', 'Public library with various resources.', '9:00 - 18:00', 37.5630, 126.9770, CURRENT_TIMESTAMP),
('Workspace H', '505 Palm Ct', 'Perfect for freelancers and remote workers.', '7:00 - 22:00', 37.5660, 126.9800, CURRENT_TIMESTAMP),
('Cafe I', '606 Elm Ave', 'Best espresso in town.', '6:00 - 18:00', 37.5625, 126.9815, CURRENT_TIMESTAMP),
('Library J', '707 Redwood St', 'Library with private study rooms.', '10:00 - 20:00', 37.5685, 126.9825, CURRENT_TIMESTAMP);

INSERT INTO Review (user_id, place_id, rating, comment, created_at) VALUES
(1, 1, 4, 'Nice and cozy cafe with good coffee.', CURRENT_TIMESTAMP),
(2, 2, 5, 'Best pastries I have ever had!', CURRENT_TIMESTAMP),
(3, 3, 3, 'Quiet but could use more study tables.', CURRENT_TIMESTAMP),
(4, 4, 5, 'Great workspace, fast internet, and good lighting.', CURRENT_TIMESTAMP),
(5, 5, 4, 'Beautiful view but coffee is just average.', CURRENT_TIMESTAMP),
(6, 6, 5, 'Loved the organic coffee here!', CURRENT_TIMESTAMP),
(7, 7, 4, 'Good resources but a bit crowded.', CURRENT_TIMESTAMP),
(8, 8, 5, 'Perfect for freelancers, great vibe.', CURRENT_TIMESTAMP),
(9, 9, 3, 'Espresso was okay, but a bit overpriced.', CURRENT_TIMESTAMP),
(10, 10, 4, 'Nice study rooms, good for group study.', CURRENT_TIMESTAMP);
