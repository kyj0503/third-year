-- Player 테이블에 데이터 삽입
INSERT INTO Player (username, password, email, created_at)
VALUES
    ('user1', 'password1', 'user1@example.com', CURRENT_TIMESTAMP),
    ('user2', 'password2', 'user2@example.com', CURRENT_TIMESTAMP),
    ('user3', 'password3', 'user3@example.com', CURRENT_TIMESTAMP),
    ('user4', 'password4', 'user4@example.com', CURRENT_TIMESTAMP),
    ('user5', 'password5', 'user5@example.com', CURRENT_TIMESTAMP);

-- Post 테이블에 데이터 삽입
INSERT INTO Post (player_id, content, created_at)
VALUES
    (1, '첫 번째 포스트 내용입니다.', CURRENT_TIMESTAMP),
    (2, '두 번째 포스트 내용입니다.', CURRENT_TIMESTAMP),
    (3, '세 번째 포스트 내용입니다.', CURRENT_TIMESTAMP),
    (4, '네 번째 포스트 내용입니다.', CURRENT_TIMESTAMP),
    (5, '다섯 번째 포스트 내용입니다.', CURRENT_TIMESTAMP);

-- Comment 테이블에 데이터 삽입
INSERT INTO Comment (post_id, player_id, content, created_at)
VALUES
    (1, 2, '첫 번째 포스트에 대한 첫 번째 댓글입니다.', CURRENT_TIMESTAMP),
    (2, 3, '두 번째 포스트에 대한 두 번째 댓글입니다.', CURRENT_TIMESTAMP),
    (3, 4, '세 번째 포스트에 대한 세 번째 댓글입니다.', CURRENT_TIMESTAMP),
    (4, 5, '네 번째 포스트에 대한 네 번째 댓글입니다.', CURRENT_TIMESTAMP),
    (5, 1, '다섯 번째 포스트에 대한 다섯 번째 댓글입니다.', CURRENT_TIMESTAMP);
