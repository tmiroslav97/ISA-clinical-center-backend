--podaci za autorizaciju
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

--podaci za klinike
INSERT INTO clinic (address, cnt_rating, description, name , sum_rating)
VALUES ('Vlasenica 15', 0, 'Clinic for cardiovascular disease', 'Clinic Vlasenica', 0);

INSERT INTO clinic (address, cnt_rating, description, name , sum_rating)
VALUES ('Kikinda 66', 0, 'Clinic for neurology', 'Clinic Kikinda', 0);

INSERT INTO clinic (address, cnt_rating, description, name , sum_rating)
VALUES ('Bijeljina 42', 0, 'Clinic for dermatology', 'Clinic Bijeljina', 0);

--podaci administratora klinickog centra
--predefinisani
INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (1, 'Miroslav', 'Tomic', 'tomic.miroslav97@gmail.com', true, false, '2019-11-20 11:00:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0 );

INSERT INTO user_authority (user_id, authority_id)
VALUES (1, 1);

INSERT INTO clinic_center_admin (predefined, id)
VALUES (true, 1);

--nisu predefinisani
INSERT INTO users (id,first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (8,'Petar', 'Peric', 'pero@gmail.com', true, false, '2019-11-20 11:00:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);

INSERT INTO user_authority (user_id, authority_id)
VALUES (8,1);

INSERT INTO clinic_center_admin (predefined, id)
VALUES (false, 8);

--treba da promjeni password
INSERT INTO users (id,first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (9,'Miki', 'Mitrovic', 'miki@miki.com', true, true, '2019-11-20 11:00:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);

INSERT INTO user_authority (user_id, authority_id)
VALUES (9,1);

INSERT INTO clinic_center_admin (predefined, id)
VALUES (false, 9);

--podaci za pacijente
INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (2, 'Jovana', 'Lakic', 'jovana.lakic8@gmail.com', true, false, '2019-11-20 11:25:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);

INSERT INTO patient (address, city, country, is_activated, phone_num, unoip, id)
VALUES ('Ilije Bircanina', 'Vlasenica', 'Bosna i Hercegovina', true, '065987544', '1234567890', 2);

INSERT INTO user_authority (user_id, authority_id)
VALUES (2, 5);

INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (6, 'Stefan', 'Peric', 'stefan@stefan.com', true, false, '2019-11-20 11:25:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);
INSERT INTO patient (address, city, country, is_activated, phone_num, unoip, id)
VALUES ('Masarikova', 'Novi Sad', 'Srbija', true, '065312532', '4351233', 6);

INSERT INTO user_authority (user_id, authority_id)
VALUES (6,2);

--podaci administratora klinike
INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (5, 'Jecko', 'Pecko', 'jecko@gmail.com', true, false, '2019-11-20 11:25:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);
INSERT INTO user_authority (user_id, authority_id)
VALUES (5, 2);

INSERT INTO clinic_admin(id, clinic_id)
VALUES (5, 1);

INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (10, 'Milena', 'Milic', 'mila@gmail.com', true, false, '2019-11-20 11:25:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);
INSERT INTO user_authority (user_id, authority_id)
VALUES (10, 2);

INSERT INTO clinic_admin(id, clinic_id)
VALUES (10, 1);

--treba da promjeni password
INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (11, 'Stevka', 'Stevic', 'stevka@gmail.com', true, true, '2019-11-20 11:25:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);
INSERT INTO user_authority (user_id, authority_id)
VALUES (11, 2);

INSERT INTO clinic_admin(id, clinic_id)
VALUES (11, 1);

--podaci za doktore
INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (3, 'Nevena', 'Djukin', 'nvndjukin97@gmail.com', true, false, '2019-11-20 11:30:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);

INSERT INTO personnel(id, clinic_id)
VALUES (3, 1);

INSERT INTO doctor(cnt_rating, sum_rating, start_time, end_time, id, clinic_id)
VALUES (0, 0, 8, 16, 3,1);

INSERT INTO user_authority (user_id, authority_id)
VALUES (3, 3);


INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (12, 'Doca', 'Doktor', 'doktor1@doktor1', true, false, '2019-11-20 11:30:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);

INSERT INTO personnel(id, clinic_id)
VALUES (12, 1);

INSERT INTO doctor(cnt_rating, sum_rating, start_time, end_time, id, clinic_id)
VALUES (0, 0, 7, 15, 12,1);

INSERT INTO user_authority (user_id, authority_id)
VALUES (12, 3);

INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (13, 'Stojanka', 'Skoric', 'doktor2@doktor2', true, false, '2019-11-20 11:30:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);

INSERT INTO personnel(id, clinic_id)
VALUES (13, 1);

INSERT INTO doctor(cnt_rating, sum_rating, start_time, end_time, id, clinic_id)
VALUES (0, 0, 11, 19, 13,1);

INSERT INTO user_authority (user_id, authority_id)
VALUES (13, 3);

--podaci za medicinsku sestru
INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)
VALUES (4, 'Milena', 'Milic', 'nvn@gmail.com', true, false, '2019-11-20 11:30:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);
INSERT INTO personnel(id, clinic_id)
VALUES (4, 1);

INSERT INTO nurse(id, clinic_id)
VALUES (4,1);

INSERT INTO user_authority (user_id, authority_id)
VALUES (4, 4);


INSERT INTO users (id, first_name, last_name, email, enabled, first_log,
                   last_password_reset_date, password, version)

VALUES (7, 'Ana', 'Andjusic', 'ana@gmail.com', true, false, '2020-01-24 3:50:00',
        '$2a$10$VSlWn0nzWDB2Jxv7cx.sf.NakwjllWrSjdkWi66g2dMM.OdBGThlS',0);

INSERT INTO personnel(id, clinic_id)
VALUES (7, 2);

INSERT INTO nurse(id, clinic_id)
VALUES (7,1);

INSERT INTO user_authority (user_id, authority_id)
VALUES (7, 3);



--registration requirements
INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'miroslavtomic@outlook.com', 'Miki', 'Peric', '12345', '12345', '065987654',
        '1234543',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 16', 'Bijeljina', 'Bosna', 'roncevic1996@gmail.com', 'Jovica', 'Roncevic', '12345', '12345', '345435345',
        '432132',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 16', 'Bijeljina', 'Bosna', 'roncevic1996@gmail.com', 'Jovica', 'Roncevic', '12345', '12345', '345435345',
        '432132',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'primjer@primjer', 'Miroslav', 'Tomic', '12345', '12345', '45454545',
        '432132',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Bijeljina', 'Bosna', 'primjer1@primjer1', 'Petar', 'Peric', '12345', '12345', '14773442',
        '432132',0);

INSERT INTO registration_requirement (address, city, country, email, first_name, last_name, password, password2,
                                      phone_num, unoip,version)
VALUES ('Safarikova 31', 'Ugljevik', 'Bosna', 'primjer2@primjer2', 'Mitar', 'Miric', '12345', '12345', '14773442',
        '432132',0);

INSERT INTO clinic_patients(clinic_id, patients_id)
VALUES (1, 2);

INSERT INTO clinic_patients(clinic_id, patients_id)
VALUES (1, 6);

--ubacivanje soba na prvu kliniku
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (1,FALSE ,'SUR','For heart',1,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (2,FALSE ,'SUR','For head',2,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (3,FALSE ,'SUR','For brain',3,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (4,FALSE ,'SUR','For skin',4,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (5,FALSE ,'SUR','For legs',5,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (6,FALSE ,'SUR','For old',6,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (7,FALSE ,'APP','For appointment',7,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (8,FALSE ,'APP','For Ah1',8,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (9,FALSE ,'APP','For heart',9,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (10,FALSE ,'APP','For Ah3',10,1,0);
INSERT INTO room(id,reserved,room_type,name,room_num,clinic_id,version) VALUES (11,FALSE ,'APP','For heart',11,1,0);


--simuliranje da je neka soba zauzeta odredjenog termina
INSERT INTO roomcalendar(date, termin, room_id) VALUES ('2020-01-22',7,1);
INSERT INTO roomcalendar(date, termin, room_id) VALUES ('2020-01-22',10,1);
INSERT INTO roomcalendar(date, termin, room_id) VALUES ('2020-01-22',16,1);
INSERT INTO roomcalendar(date, termin, room_id) VALUES ('2020-01-23',7,1);


--zahtjevi za operaciju
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-09',7,1,2,3, 'Jovana Lakic','Nevena Djukin',0);
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-10',10,1,2,3,'Jovana Lakic','Nevena Djukin',0);
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-24',13,1,2,3,'Jovana Lakic','Nevena Djukin',0);
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-25',7,1,2,3, 'Jovana Lakic','Nevena Djukin',0);
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-26',10,1,2,3,'Jovana Lakic','Nevena Djukin',0);
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-27',13,1,2,3,'Jovana Lakic','Nevena Djukin',0);
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-28',7,1,2,3, 'Jovana Lakic','Nevena Djukin',0);
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-29',10,1,2,3,'Jovana Lakic','Nevena Djukin',0);
INSERT INTO surgery_requirement(date,termin,clinic_id,patient_id,doctor_id,patient_name,doctor_name,version)
    VALUES ('2020-02-29',10,1,2,12,'Jovana Lakic','Doca Doktor',0);

--tipovi pregleda
INSERT INTO appointment_type(id, type, clinic_id) VALUES (1,'General examination',1);
INSERT INTO appointment_type(id, type, clinic_id) VALUES (2,'Heart examination',1);
INSERT INTO appointment_type(id, type, clinic_id) VALUES (3,'Stomach examination',1);


--pregledi
INSERT INTO appointment(app_state, date_time, discount, price, clinic_id, doctor_id, patient_id, room_id, type_id)
    VALUES(1, '2020-02-09 09:00:00', 0,0,1,3,2,1,1);
INSERT INTO appointment(app_state, date_time, discount, price, clinic_id, doctor_id, patient_id, room_id, type_id)
    VALUES(1, '2020-02-10 09:00:00', 0,0,1,3,2,1,1);
INSERT INTO appointment(app_state, date_time, discount, price, clinic_id, doctor_id, patient_id, room_id, type_id)
    VALUES(1, '2020-02-24 09:00:00', 0,0,1,3,2,1,1);
INSERT INTO appointment(app_state, date_time, discount, price, clinic_id, doctor_id, patient_id, room_id, type_id)
    VALUES(1, '2020-02-25 09:00:00', 0,0,1,3,2,1,1);
INSERT INTO appointment(app_state, date_time, discount, price, clinic_id, doctor_id, patient_id, room_id, type_id)
    VALUES(1, '2020-02-26 09:00:00', 0,0,1,3,2,1,1);
INSERT INTO appointment(app_state, date_time, discount, price, clinic_id, doctor_id, patient_id, room_id, type_id)
    VALUES(1, '2020-02-27 09:00:00', 0,0,1,3,2,1,1);
INSERT INTO appointment(app_state, date_time, discount, price, clinic_id, doctor_id, patient_id, room_id, type_id)
    VALUES(1, '2020-02-28 09:00:00', 0,0,1,3,2,1,1);
INSERT INTO appointment(app_state, date_time, discount, price, clinic_id, doctor_id, patient_id, room_id, type_id)
    VALUES(1, '2020-02-29 09:00:00', 0,0,1,3,2,1,1);

--calendar
INSERT INTO calendar(id, personnel_id)
VALUES (1, 4);

UPDATE personnel
SET calendar_id=1
WHERE id = 4;

INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type)
VALUES ('2020-02-09 10:00:00', '2020-02-05 09:00:00', 'Winter holiday', 'N', 0, 1,'ABS');

INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type)
VALUES ('2020-02-14 19:00:00', '2020-02-14 07:00:00', 'Slava', 'N', 0, 1,'ABS');

INSERT INTO calendar(id, personnel_id)
VALUES (3, 12);

UPDATE personnel
SET calendar_id=3
WHERE id = 12;

INSERT INTO calendar(id, personnel_id)
VALUES (4, 13);

UPDATE personnel
SET calendar_id=4
WHERE id = 13;

INSERT INTO calendar(id, personnel_id)
VALUES (2, 3);

INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type,typeid)
    VALUES ('2020-02-09 10:00:00', '2020-02-09 09:00:00', 'First examination', 'N', 3, 2,'APP',1);
INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type,typeid)
    VALUES ('2020-02-10 10:00:00', '2020-02-10 09:00:00',  'Second examination', 'N', 4, 2,'APP',2);
INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type,typeid)
    VALUES ('2020-02-24 10:00:00', '2020-02-24 09:00:00',  'Third examination', 'N', 5, 2,'APP',3);
INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type,typeid)
    VALUES ('2020-02-25 10:00:00', '2020-02-25 09:00:00', 'First examination', 'N', 6, 2,'APP',1);
INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type,typeid)
    VALUES ('2020-02-26 10:00:00', '2020-02-26 09:00:00',  'Second examination', 'N', 7, 2,'APP',2);
INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type,typeid)
    VALUES ('2020-02-27 10:00:00', '2020-02-27 09:00:00',  'Third examination', 'N', 8, 2,'APP',3);
INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type,typeid)
    VALUES ('2020-02-28 10:00:00', '2020-02-28 09:00:00', 'First examination', 'N', 9, 2,'APP',1);
INSERT INTO calendar_item(end, start, title, all_day, id, calendar_id,type,typeid)
    VALUES ('2020-02-29 10:00:00', '2020-02-29 09:00:00',  'Second examination', 'N', 10, 2,'APP',2);

UPDATE personnel
SET calendar_id=2
WHERE id = 3;

--poraci o lijekovima i dijagnozama
INSERT INTO medicine(code,description,name) VALUES('12545','For head', 'Brufen');
INSERT INTO medicine(code,description,name) VALUES('4315','For head', 'Paracetamol');
INSERT INTO medicine(code,description,name) VALUES('3h46','For head', 'Cafetin');
INSERT INTO medicine(code,description,name) VALUES('asd123','For stomach', 'Probiotik');
INSERT INTO medicine(code,description,name) VALUES('3433','For stomach', 'Ranisan');
INSERT INTO medicine(code,description,name) VALUES('1423','For liver', 'Liver forte');

INSERT INTO diagnose(code,description,name) VALUES('s5sg4','Strong pain in head', 'Migren');
INSERT INTO diagnose(code,description,name) VALUES('d3y5','Pain in head', 'Head ache');
INSERT INTO diagnose(code,description,name) VALUES('3453','Strong pain in stomach', 'Mononucelosis');
INSERT INTO diagnose(code,description,name) VALUES('54451','Pain in stomach', 'Stomach');


--Medicinski kartoni od pacijenata
INSERT INTO medical_record(blood_type, description, height, weight, patient_id) VALUES ('Nepoznato','Nema opisa', 0.0, 0.0, 2);
INSERT INTO medical_record(blood_type, description, height, weight, patient_id) VALUES ('Nepoznato','Nema opisa', 0.0, 0.0, 6);

UPDATE patient
SET medical_record_id=1
WHERE id=2;

UPDATE patient
SET medical_record_id=2
WHERE id=6;
