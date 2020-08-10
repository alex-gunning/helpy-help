CREATE TABLE bank_account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    provider ENUM('ABSA', 'Capitec', 'Standard Bank', 'First National Bank'), -- We could migrate this to its own table eventually
    branch_code VARCHAR(20),
    account_number VARCHAR(20)
)