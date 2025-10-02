INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES ('OD-05-1234', 'Rahul Sharma', 'CAR', true, '2025-12-10');

INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES ('OD-07-4321', 'Priya Singh', 'BIKE', false, '2025-12-11');

INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES ('OD-09-8765', 'Rahul Sharma', 'TRUCK', true, '2025-12-12');

INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES ('OD-01-5555', 'Amit Kumar', 'CAR', true, '2025-12-13');

INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES ('OD-02-6666', 'Neha Verma', 'BIKE', false, '2025-12-14');

-- Eligible person (owns 4 cars)
INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES
('OD-10-1000', 'Raj Malhotra', 'CAR', true, '2025-12-15'),
('OD-10-1001', 'Raj Malhotra', 'CAR', true, '2025-12-16'),
('OD-10-1002', 'Raj Malhotra', 'CAR', false, '2025-12-17'),
('OD-10-1003', 'Raj Malhotra', 'CAR', true, '2025-12-18');

-- Not eligible (owns 3 cars)
INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES
('OD-11-2000', 'Sunita Rao', 'CAR', true, '2025-12-19'),
('OD-11-2001', 'Sunita Rao', 'CAR', false, '2025-12-20'),
('OD-11-2002', 'Sunita Rao', 'CAR', true, '2025-12-21');

-- Not eligible (owns 5 vehicles, only 2 are cars)
INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES
('OD-12-3000', 'Arjun Das', 'CAR', true, '2025-12-22'),
('OD-12-3001', 'Arjun Das', 'BIKE', true, '2025-12-23'),
('OD-12-3002', 'Arjun Das', 'TRUCK', false, '2025-12-24'),
('OD-12-3003', 'Arjun Das', 'CAR', false, '2025-12-25'),
('OD-12-3004', 'Arjun Das', 'BIKE', true, '2025-12-26');

-- Not eligible (only owns bikes)
INSERT INTO vehicle (registration_number, owner_name, vehicle_type, insured, inspection_due_date)
VALUES
('OD-13-4000', 'Meena Gupta', 'BIKE', true, '2025-12-27'),
('OD-13-4001', 'Meena Gupta', 'BIKE', false, '2025-12-28');

