
-- Lab 10: Fix BCrypt password hashes
-- Student: Sayazhan Tileukozha, 2410 IS

-- Password: user123
UPDATE app_user
SET password_hash =
'$2a$10$4M6NjBSZ55D6eB5i2utwA0BCWvyqc1qDlMoQRnDxkfDwEhmYFec12'
WHERE username = 'user';

-- Password: admin123
UPDATE app_user
SET password_hash =
'$2a$10$F30k.6A9h61tnq4ny8FLTOoXbzoeNS3IdZV/0IH0nCGifaFT5sBf.'
WHERE username = 'admin';
