CREATE TABLE vehicle (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration_number VARCHAR(50),
    owner_name VARCHAR(100),
    vehicle_type VARCHAR(50),
    insured BOOLEAN,
    inspection_due_date DATE
);
