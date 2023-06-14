INSERT INTO roles (role_name)
VALUES ('ROLE_OWNER'),
       ('ROLE_HAIRDRESSER'),
       ('ROLE_CUSTOMER');

INSERT INTO users (id, first_name, last_name, username, email, password, address, house_number, residence,
                   in_case_of_emergency_contact, emergency_contact_phone_number, preferred_hairdresser, notes,
                   role_name)
VALUES (51, 'Martin', 'Jurna', 'martinjurna', 'mjurna@gmail.com', '$2a$12$WrVbQhx2py/.WfVKSry/C.jH58INLyIEdReDYTfEUwl4TtFWRxlkC', 'Hogestraat', '23', 'Loppersum', 'Hanneke Abbring',
        '0634896721', '', 'Eigenaar kapsalon De Smidse', 'ROLE_OWNER'),
--     Wachtwoord geheim123
       (52, 'Gerdine', 'Smit', 'gerdinesmit', 'smitjegerdien@hotmail.com', '$2a$12$ZnYR5vBigU3.WjfyX7CVFeT2Jr3ZN896H3rO5wMEATnz9lvUJ6HrS', 'Stedumerweg', '25',
        'Garrelsweer', 'Piet Brummer', '0629730278', '', '', 'ROLE_HAIRDRESSER'),
--     Wachtwoord wachtwoord123
       (53, 'Ellen', 'Medema', 'ellenmedema', 'emee@outlook.com', '$2a$12$5EsVOcNDe6P4iwm7murvK.VvBK5GJM7QlCOBtITDJyJlMyL0BGwhO
', 'Kruisweg', '50', 'Wirdum', 'Sander Postjes',
        '062963008833', '', '', 'ROLE_HAIRDRESSER'),
--     Wachtwoord 123123
       (54, 'Ina', 'Korrema', 'inakorrema', 'ina75@yahoo.com', '$2a$12$9vZYv5wfP40uK4OYO4QwbufRMEY0poFDKDTl1ZtH.olwOTOLRwHlC', 'Stationsstraat', '6', 'Loppersum', 'Sanne Koster',
        '0655443322', '', '', 'ROLE_HAIRDRESSER'),
--     Wachtwoord inlogcode1
       (55, 'Danielle', 'Diesman', 'daniellediesman', 'dd@gmail.com', '$2a$12$s3ed7yyPU65FCRXE0bQ9DeQgNCiXlAb057FKCK5t25lfCQ8gim016', 'Fruitlaan', '25', 'Zeerijp', 'Willem Scheepers',
        '0611223344', '', '', 'ROLE_HAIRDRESSER'),
--     Wachtwoord dd12345
       (56, 'Marianne', 'Timmer', 'mariannetimmer', 'marie@hotmail.com', '$2a$12$YSGQRPZkYbIc2mF.6Iiv6eez.X0pELZ9z0FbooG9OSUxOTHkxHZYi', 'Lagestraat', 1, 'Stedum',
        'Anna Peters', '06123456', 'Gerdine', '', 'ROLE_CUSTOMER'),
--     Wachtwoord liefdevoorschaatsen123
       (57, 'Piet', 'Pieters', 'pietpieters', 'petitpiet@hotmail.com', '$2a$12$mDTige0D5GvHyRGPQ.bGyelN8a0BkYLYIxf9/rMPM4cFSLuwK926m', 'Pruimenhof', 19, 'Loppersum',
        'Sanne Woldring', '06947289567', 'Martin', '', 'ROLE_CUSTOMER'),
--     Wachtwoord klompenpad4
       (58, 'Louise', 'Keimpema', 'louisekeimpema' , 'loui@gmail.com', '$2a$12$hkFXj3BdyOpmBUdRSI8/mO7MWOeEF.xm/zDZHgfsZuDK2/e162eQq', 'Fruitlaan', 16, 'Loppersum',
        'Joris Jurna', '0612378965', 'Danielle', '', 'ROLE_CUSTOMER'),
--     Wachtwoord moosie456
       (59, 'Jade', 'Mens', 'jademens', 'jmens@yahoo.com', '$2a$12$vKsIKJ2EY2htNHOvJm./outIh414YOIFTju22vsuDk3HtmTO9POHq', 'Badweg', 100, 'Loppersum',
        'Peter Heres', '0612345678', 'Ellen', '', 'ROLE_CUSTOMER'),
--     Wachtwoord sinaasappel1!
       (60, 'Peter', 'Heres', 'peterheres', 'pheres@hotmail.com', '$2a$12$YhAtdbwaMBg4jInuK4ixBu6SI2zT.dJUXC/9BiclLO892uolim2xW', 'Badweg', 100, 'Loppersum',
        'Jade Mens', '0612345678', 'Ellen', '', 'ROLE_CUSTOMER');
--     Wachtwoord jadehartje89

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