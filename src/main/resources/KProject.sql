CREATE TABLE User (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Place (
                       place_id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       address VARCHAR(255) NOT NULL,
                       description TEXT,
                       opening_hours VARCHAR(100),
                       latitude DECIMAL(18, 15) NOT NULL,
                       longitude DECIMAL(18, 15) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Review (
                        review_id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id INT,
                        place_id INT,
                        rating INT CHECK(rating BETWEEN 1 AND 10),
                        comment TEXT,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
                        FOREIGN KEY (place_id) REFERENCES Place(place_id) ON DELETE CASCADE
);

CREATE TABLE Favorite (
                          favorite_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT NOT NULL,
                          place_id INT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
                          FOREIGN KEY (place_id) REFERENCES Place(place_id) ON DELETE CASCADE
);
