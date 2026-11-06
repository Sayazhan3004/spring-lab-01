
-- Lab 10: Security users and roles
-- Student: Sayazhan Tileukozha, 2410 IS

-- USER: user / user123
-- ADMIN: admin / admin123

INSERT INTO app_user (username, password_hash, enabled)
VALUES
(
    'user',
    '$2a$10$4M6NjBSZ55D6eB5i2utwA0BCWvyqc1qDlMoQRnDxkfDwEhmYFec12',
    TRUE
),
(
    'admin',
    '$2a$10$F30k.6A9h61tnq4ny8FLTOoXbzoeNS3IdZV/0IH0nCGifaFT5sBf.',
    TRUE
);

-- Assign USER role
INSERT INTO user_role (user_id, role)
SELECT id, 'USER'
FROM app_user
WHERE username IN ('user', 'admin');

-- Assign ADMIN role
INSERT INTO user_role (user_id, role)
SELECT id, 'ADMIN'
FROM app_user
WHERE username = 'admin';
