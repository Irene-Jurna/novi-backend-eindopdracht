INSERT INTO users (id, first_name, last_name, email, password, address, house_number, residence,
                   in_case_of_emergency_contact, emergency_contact_phone_number, preferred_hairdresser, notes)
VALUES (51, 'Martin', 'Jurna', 'mjurna@gmail.com', 'geheim123', 'Hogestraat', '23', 'Loppersum', 'Hanneke Abbring',
        '0634896721', '', 'Eigenaar kapsalon De Smidse');

INSERT INTO users (id, first_name, last_name, email, password, address, house_number, residence,
                   in_case_of_emergency_contact, emergency_contact_phone_number, preferred_hairdresser, notes)
VALUES (52, 'Gerdine', 'Smit', 'smitjegerdien@hotmail.com', 'wachtwoord123', 'Stedumerweg', '25',
        'Garrelsweer', 'Piet Brummer', '0629730278', '', 'Kapster');

INSERT INTO users (id, first_name, last_name, email, password, address, house_number, residence,
                   in_case_of_emergency_contact, emergency_contact_phone_number, preferred_hairdresser, notes)
VALUES (53, 'Ellen', 'Medema', 'emee@outlook.com', '123123', 'Kruisweg', '50', 'Wirdum', 'Sander Postjes',
        '062963008833', '', 'Kapster');

INSERT INTO users (id, first_name, last_name, email, password, address, house_number, residence,
                   in_case_of_emergency_contact,
                   emergency_contact_phone_number, preferred_hairdresser, notes)
VALUES (54, 'Ina', 'Korrema', 'ina75@yahoo.com', 'inlogcode1', 'Stationsstraat', '6', 'Loppersum', 'Sanne Koster',
        '0655443322', '', 'Kapster');

INSERT INTO users (id, first_name, last_name, email, password, address, house_number, residence,
                   in_case_of_emergency_contact, emergency_contact_phone_number, preferred_hairdresser, notes)
VALUES (55, 'Danielle', 'Diesman', 'dd@gmail.com', 'dd12345', 'Fruitlaan', '25', 'Zeerijp', 'Willem Scheepers',
        '0611223344', '', 'Kapster');

INSERT INTO users (id, first_name, last_name, email, password, address, house_number, residence,
                   in_case_of_emergency_contact, emergency_contact_phone_number, preferred_hairdresser, notes)
VALUES (56, 'Marianne', 'Timmer', 'marie@hotmail.com', 'liefdevoorschaatsen123', 'Lagestraat', 1, 'Stedum',
        'Anna Peters', '06123456', 'Gerdine', 'Klant');

INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (2, 'Knippen dames en heren', 25, 25);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (3, 'Knippen kinderen', 25, 23.50);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (32, 'Knippen minderharigen', 20, 20);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (33, 'Knippen wassen', 35, 33.50);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (34, 'Knippen coupe soleil', 150, 80);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (35, 'Knippen wassen watergolven', 120, 45);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (36, 'Omvorming', 160, 95);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (37, 'Omvorming lang haar', 180, 137.50);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (38, 'Verven vanaf', 60, 50);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (39, 'Spoeling vanaf', 50, 48);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (40, 'Wassen watergolven', 95, 33.50);
INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (41, 'Vlechten vanaf', 30, 28.50);

INSERT INTO products (id, name, purchase_price, price, in_stock)
VALUES (10, 'Wella SP shampoo', 5, 7.50, 50);
INSERT INTO products (id, name, purchase_price, price, in_stock)
VALUES (11, 'Wella EIMI haargel', 4, 6, 25);
INSERT INTO products (id, name, purchase_price, price, in_stock)
VALUES (12, 'Wella Elements conditioner', 5, 7, 40);
INSERT INTO products (id, name, purchase_price, price, in_stock)
VALUES (13, 'Wella Elements shampoobar', 8, 14, 30);