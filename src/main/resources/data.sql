INSERT INTO roles (role_name)
VALUES ('Owner'),
       ('Hairdresser'),
       ('Customer');

INSERT INTO users (id, first_name, last_name, username, email, password, address, house_number, residence,
                   in_case_of_emergency_contact, emergency_contact_phone_number, preferred_hairdresser, notes,
                   role_name)
VALUES (51, 'Martin', 'Jurna', 'martinjurna', 'mjurna@gmail.com', 'geheim123', 'Hogestraat', '23', 'Loppersum', 'Hanneke Abbring',
        '0634896721', '', 'Eigenaar kapsalon De Smidse', 'Owner'),
       (52, 'Gerdine', 'Smit', 'gerdinesmit', 'smitjegerdien@hotmail.com', 'wachtwoord123', 'Stedumerweg', '25',
        'Garrelsweer', 'Piet Brummer', '0629730278', '', '', 'Hairdresser'),
       (53, 'Ellen', 'Medema', 'ellenmedema', 'emee@outlook.com', '123123', 'Kruisweg', '50', 'Wirdum', 'Sander Postjes',
        '062963008833', '', '', 'Hairdresser'),
       (54, 'Ina', 'Korrema', 'inakorrema', 'ina75@yahoo.com', 'inlogcode1', 'Stationsstraat', '6', 'Loppersum', 'Sanne Koster',
        '0655443322', '', '', 'Hairdresser'),
       (55, 'Danielle', 'Diesman', 'daniellediesman', 'dd@gmail.com', 'dd12345', 'Fruitlaan', '25', 'Zeerijp', 'Willem Scheepers',
        '0611223344', '', '', 'Hairdresser'),
       (56, 'Marianne', 'Timmer', 'mariannetimmer', 'marie@hotmail.com', 'liefdevoorschaatsen123', 'Lagestraat', 1, 'Stedum',
        'Anna Peters', '06123456', 'Gerdine', '', 'Customer'),
       (57, 'Piet', 'Pieters', 'pietpieters', 'petitpiet@hotmail.com', 'klompenpad4', 'Pruimenhof', 19, 'Loppersum',
        'Sanne Woldring', '06947289567', 'Martin', '', 'Customer'),
       (58, 'Louise', 'Keimpema', 'louisekeimpema' , 'loui@gmail.com', 'moosie456', 'Fruitlaan', 16, 'Loppersum',
        'Joris Jurna', '0612378965', 'Danielle', '', 'Customer'),
       (59, 'Jade', 'Mens', 'jademens', 'jmens@yahoo.com', 'sinaasappel1!', 'Badweg', 100, 'Loppersum',
        'Peter Heres', '0612345678', 'Ellen', '', 'Customer'),
       (60, 'Peter', 'Heres', 'peterheres', 'pheres@hotmail.com', 'jadehartje89', 'Badweg', 100, 'Loppersum',
        'Jade Mens', '0612345678', 'Ellen', '', 'Customer');

INSERT INTO treatments (id, name, duration_in_minutes, price)
VALUES (30, 'Knippen dames en heren', 25, 25),
       (31, 'Knippen kinderen', 25, 23.50),
       (32, 'Knippen minderharigen', 20, 20),
       (33, 'Knippen wassen', 35, 33.50),
       (34, 'Knippen coupe soleil', 150, 80),
       (35, 'Knippen wassen watergolven', 120, 45),
       (36, 'Omvorming', 160, 95),
       (37, 'Omvorming lang haar', 180, 137.50),
       (38, 'Verven vanaf', 60, 50),
       (39, 'Spoeling vanaf', 50, 48),
       (40, 'Wassen watergolven', 95, 33.50),
       (41, 'Vlechten vanaf', 30, 28.50);

INSERT INTO products (id, name, purchase_price, price, in_stock)
VALUES (10, 'Wella SP shampoo', 5, 7.50, 50),
       (11, 'Wella EIMI haargel', 4, 6, 25),
       (12, 'Wella Elements conditioner', 5, 7, 40),
       (13, 'Wella Elements shampoobar', 8, 14, 30),
       (14, 'Wella Elements Beard wax', 10, 15, 20),
       (15, 'Wella Elements Baard olie', 11, 16, 10);