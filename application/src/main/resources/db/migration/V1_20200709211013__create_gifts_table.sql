CREATE TABLE pending_gifts (
    pending_gift_id INT AUTO_INCREMENT PRIMARY KEY,
    pending_gift_uuid VARCHAR(30) NOT NULL,
    gifter_id INT NOT NULL,
    giftee_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);