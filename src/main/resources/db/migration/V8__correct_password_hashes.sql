
-- Lab 10: Correct BCrypt password hashes
-- Student: Sayazhan Tileukozha, 2410 IS

-- USER password: user123
UPDATE app_user
SET password_hash =
'$2a$10$o5rVP7aN/WkFMfFWCoJLD.39Y/GqLho5RC3TzOmB7Lsuwl7mFzwnK'
WHERE username = 'user';

-- ADMIN password: admin123
UPDATE app_user
SET password_hash =
'$2a$10$vW31XcsRcXGhl07mIIvy7.8RdynG8bhqgxPmVjocT0HmVvcmvw0VS'
WHERE username = 'admin';
