CREATE TABLE item_details (
    item_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50) NOT NULL CHECK (category IN ('Electronics', 'Clothing', 'Travel', 'Books', 'Groceries'))
);

