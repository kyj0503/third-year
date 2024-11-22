CREATE TABLE Player (
                        player_id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(50) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Post (
                      post_id INT AUTO_INCREMENT PRIMARY KEY,
                      player_id INT NOT NULL,
                      content TEXT NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (player_id) REFERENCES Player(player_id) ON DELETE CASCADE
);

CREATE TABLE Comment (
                         comment_id INT AUTO_INCREMENT PRIMARY KEY,
                         post_id INT NOT NULL,
                         player_id INT NOT NULL,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (post_id) REFERENCES Post(post_id) ON DELETE CASCADE,
                         FOREIGN KEY (player_id) REFERENCES Player(player_id) ON DELETE CASCADE
);
