CREATE TABLE pending_gifts (
    pending_gift_id INT AUTO_INCREMENT PRIMARY KEY,
    pending_gift_uuid VARCHAR(30) NOT NULL,
    gifter_id INT NOT NULL,
    giftee_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_pending TINYINT(1) DEFAULT 0 NOT NULL
);