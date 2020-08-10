ALTER TABLE gifter
    ADD COLUMN bank_account_id INT NOT NULL;

ALTER TABLE gifter
    ADD FOREIGN KEY (bank_account_id) REFERENCES bank_account(account_id);
