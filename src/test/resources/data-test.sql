INSERT INTO authority (name)
VALUES ('ROLE_CCADMIN');
INSERT INTO authority (name)
VALUES ('ROLE_ADMINC');
INSERT INTO authority (name)
VALUES ('ROLE_DOCTOR');
INSERT INTO authority (name)
VALUES ('ROLE_NURSE');
INSERT INTO authority (name)
VALUES ('ROLE_PATIENT');
INSERT INTO authority (name)
VALUES ('ROLE_PERSONNEL');

INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password)
VALUES (1, 'Miroslav', 'Tomic', 'tomic.miroslav97@gmail.com', true, false, '2019-11-20 11:00:00',
        '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra');


INSERT INTO user_authority (user_id, authority_id)
VALUES (1, 1);

INSERT INTO clinic_center_admin (predefined, id)
VALUES (true, 1);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'miroslavtomic@outlook.com', 'Miki', 'Peric', '123', '123', '065987654',
        '1234543',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 16', 'Bijeljina', 'Bosna', 'roncevic1996@gmail.com', 'Jovica', 'Roncevic', '321', '321', '345435345',
        '432132',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 16', 'Bijeljina', 'Bosna', 'sspasoje3@gmail.com', 'Spasoje', 'Simic', '321', '321', '345435345',
        '432132',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'tomic.miroslav97@gmail.com', 'Miki', 'Peric', '123', '123', '065987654',
        '1234543',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'sre123@gmail.com', 'Miki', 'Peric', '123', '123', '065987654',
        '1234543',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'asddf@gmail.com', 'Miki', 'Peric', '123', '123', '065987654',
        '1234543',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'asddsam', 'Miki', 'Peric', '123', '123', '065987654',
        '1234543',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'tomic.miroslav97@gmail.com', 'Miki', 'Peric', '123', '123', '065987654',
        '1234543',0);