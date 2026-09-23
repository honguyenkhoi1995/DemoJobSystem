-- Add these fields to your existing users table.
ALTER TABLE users ADD failed_attempts INT DEFAULT 0;
ALTER TABLE users ADD locked_until DATETIME NULL;
ALTER TABLE users ADD account_status VARCHAR(20) DEFAULT 'ACTIVE';
ALTER TABLE users ADD role VARCHAR(20);

-- Example:
-- UPDATE users SET role = 'ADMIN' WHERE username = 'admin';
