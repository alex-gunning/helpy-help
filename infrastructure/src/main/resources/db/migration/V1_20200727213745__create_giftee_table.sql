CREATE TABLE giftee (
    giftee_id INT AUTO_INCREMENT PRIMARY KEY,
    giftee_uuid VARCHAR(50) NOT NULL DEFAULT UUID(),
    firstname VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    id_number VARCHAR(18) NOT NULL
)
