
/*
Password za sve naloge je 123.
Komunikacija se odvija preko e-mail naloga  no.reply.medclinic@gmail.com, password je sifra123.
*/
INSERT INTO ADDRESS (id, city, latitude, longitude, street) VALUES (1, 'Novi Sad', 45.26125996629874, 19.834538133520123, 'Bulevar Kralja Petra I 11'); 
INSERT INTO ADDRESS (id, city, latitude, longitude, street) VALUES (2, 'Novi Sad', 45.24608736237126, 19.830979426522536, 'Puškinova 2');
INSERT INTO ADDRESS (id, city, latitude, longitude, street) VALUES (3, 'Novi Sad', 45.26420970934681, 19.817092640017577, 'Rumenacka 34');

INSERT INTO LOYALTY_CATEGORY (id, discount, points_border, type) VALUES (1, 0, 250, 'REGULAR');
INSERT INTO LOYALTY_CATEGORY (id, discount, points_border, type) VALUES (2, 20, 500, 'SILVER');
INSERT INTO LOYALTY_CATEGORY (id, discount, points_border, type) VALUES (3, 30, 1000, 'GOLD');

INSERT INTO APPOINTMENT_LOYALTY(id, points, type) VALUES (1, 20, 'EXAMINATION');
INSERT INTO APPOINTMENT_LOYALTY(id, points, type) VALUES (2, 15, 'COUNCELING');

INSERT INTO PHARMACY (id, address, description, name, rating, add_id) VALUES (1, 'Bulevar Oslobodjenja 56, Novi Sad', 'Otvorena 24/7', 'Jankovic DOO',0,1);
INSERT INTO PHARMACY (id, address, description, name, rating, add_id) VALUES (2, 'Lasla Gala 12, Novi Sad', 'Sirok asortiman proizvoda', 'BENU apoteka',0,2);
INSERT INTO PHARMACY (id, address, description, name, rating, add_id) VALUES (3, 'Kisacka 27, Novi Sad', 'Sirok asortiman proizvoda', 'Zegin',0,3);


INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, loyalty_points, password, penalty, phone_number, username, version, loyalty_category_id) values ('USER_PATIENT',1,'Bulevar Kralja Petra I 12, Novi Sad',true,'no.reply.medclinic@gmail.com',false,'Ivana','Tomic',0,'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0,'0601234567','ivanatomic',0,1);
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, loyalty_points,  password, penalty, phone_number, username, version, loyalty_category_id, pharmacy_admin_id) values ('USER_PHARMA_ADMIN', 2, 'Narodnog fronta 34, Novi Sad', true, 'no.reply.medclinic@gmail.com', false, 'Marko', 'Markovic',0, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0,'0633216547', 'adminjankovic',0,1,1);
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, loyalty_points,  password, penalty, phone_number, username, version, rating, loyalty_category_id) values ('USER_DERMATOLOGIST',3,'Bulevar Oslobodjenja 56, Novi Sad',true,'no.reply.medclinic@gmail.com',false,'Pera','Peric',0,'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0,'0601234488','peraperic',0,0,1);
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name,loyalty_points,  password, penalty, phone_number, username, version, rating, loyalty_category_id) values ('USER_DERMATOLOGIST',4,'Jevrejska 33, Novi Sad',true,'no.reply.medclinic@gmail.com',false,'Mika','Mikic',0,'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0,'0601277567','mikamikic',0,0,1);
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name,loyalty_points,  password, penalty, phone_number, username, version, loyalty_category_id) values ('USER_SYS_ADMIN',5,'Masarikova 7, Novi Sad',true,'no.reply.medclinic@gmail.com',false,'Petar','Petrovic',0,'$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0,'0601277444','adminsistema',0,1);
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name,loyalty_points,  password, penalty, phone_number, username, version, loyalty_category_id, pharmacy_admin_id) values ('USER_PHARMA_ADMIN', 6, 'Branka Bajica 13, Novi Sad', true, 'no.reply.medclinic@gmail.com', false, 'Drugi admin', 'Apoteke',0, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0, '0644567894','adminbenu',0,1,2 );
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name,loyalty_points,  password, penalty, phone_number, username, version, loyalty_category_id) VALUES ('USER_SUPPLIER', 7, 'Bulevar Evrope 67, Novi Sad', true, 'no.reply.medclinic@gmail.com', false, 'Dobavljac', 'Dobavljacic',0, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0,'0601234444','dobavljac',0,1);
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, loyalty_points,  password, penalty, phone_number, username, version, loyalty_category_id, pharmacy_admin_id) values ('USER_PHARMA_ADMIN', 8, 'Gunduliceva 12, Novi Sad', true, 'no.reply.medclinic@gmail.com', false, 'Admin Zegin', 'Zegin Admin',0, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0,'0633216588', 'adminzegin',0,1,3);
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, loyalty_points,  password, penalty, phone_number, username, version, loyalty_category_id, pharmacy_admin_id) values ('USER_PHARMA_ADMIN', 9, 'Drage Spasic 4, Novi Sad', true, 'no.reply.medclinic@gmail.com', false, 'Admin Jankovic 2', 'Admin Jankovic 2',0, '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra',0,'0633277588', 'adminjankovic2',0,1,1);

INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_PATIENT');
INSERT INTO AUTHORITY (id, name) VALUES (2,'ROLE_PHARMACIST');
INSERT INTO AUTHORITY (id, name) VALUES (3, 'ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (id, name) VALUES (4, 'ROLE_PHARMACY_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (5, 'ROLE_SYS_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (6, 'ROLE_SUPPLIER');



INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1,1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2,4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5,5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6,4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (7,6);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (8,4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (9,4);



INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, loyalty_points, med_type, medicine_code, mode, name, note, producer, rating,shape,  structure, version) VALUES (1, 'None', '3', 'AVAILABLE',5, 'Humani lekovi','111','NO_RECIPE','Strepsils','Pogodno za decu','RECKITT BENCKISER HEALTHCARE',0,'Lozenga', '2,4-dihlorbenzilalkohol, amilmetakrezol',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, loyalty_points, med_type, medicine_code, mode, name, note, producer,rating, shape,  structure, version) VALUES (2, 'None', '4', 'AVAILABLE',10,'Humani lekovi','112','NO_RECIPE','Andol','Za sve vrste bolova u telu','Pliva Hrvatska',0,'Tableta', 'acetilsalicilna kiselina',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, loyalty_points, med_type, medicine_code, mode, name, note, producer,rating, shape,  structure, version) VALUES (3, 'None', '2','AVAILABLE',25,'Humani lekovi','113','NO_RECIPE','Aspirin 500mg','Za jaku glavobolju','	BAYER BITTERFELD GMBH',0,'Tableta','acetilsalicilna kiselina',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,loyalty_points, med_type, medicine_code, mode, name, note, producer, rating,shape, structure, version) VALUES (4, 'Alergies', '1','AVAILABLE',20,'Humani lekovi','114','RECIPE','	Enalapril Remedica 10mg','Za kontrolu povišenog krvnog pritiska','REMEDICA LTD',0,'Tableta', 'enalapril',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,loyalty_points, med_type, medicine_code, mode, name, note, producer, rating,shape,  structure, version) VALUES (5, 'Heart problems', '2','AVAILABLE',35,'Humani lekovi','115','NO_RECIPE','Brufen 100mg','Za prehlade, kasalj i temepraturu','ABBVIE S.R.L.',0,'Sirup', 'ibuprofen',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,loyalty_points, med_type, medicine_code, mode, name, note, producer, rating,shape,  structure, version) VALUES (6, 'None', '2','AVAILABLE',15,'Homeopatski lekovi','116','NO_RECIPE','Influcid','Koristi se u terapiji infekcija slicnih gripu','DEUTSCHE HOMOOPATHIE-UNION',0,'Tableta','Aconitum trit. D3 25 mg Gelsemium trit. D3 ',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,loyalty_points, med_type, medicine_code, mode, name, note, producer, rating,shape, structure, version) VALUES (7, 'None', '1','AVAILABLE',18,'Homeopatski lekovi','117','NO_RECIPE','Polinol','Za terapiju alergijskih reakcija disajnih puteva','DEUTSCHE HOMOOPATHIE-UNION ',0,'Tableta', 'luffa operculata trit. D4, galphimia glauca trit. D3, cardiospermum trit. D3',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,loyalty_points, med_type, medicine_code, mode, name, note, producer,rating, shape, structure, version) VALUES (8, 'Alergies', '2','AVAILABLE',8,'Humani lekovi','118','RECIPE','Kardioprotekt','Za smanjenje rizika smrtnosti kod osoba koje su preživele infarkt kao i za sprečavanje moždanog udara','AD JAKA 80 RADOVIŠ',0,'Gastrorezistentna tableta', 'acetilsalicilna kiselina',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, loyalty_points, med_type, medicine_code, mode, name, note, producer, rating,shape,  structure, version) VALUES (9, 'Not for children', '1','AVAILABLE',12,'Humani lekovi','119','RECIPE','Xanax','Namenjen za lečenje anksioznosti, napetosti ili drugih somatskih (telesnih) ili psihijatrijskih simptoma povezanih sa anksioznošću','PFIZER ITALIA S.R.L.',0,'Tableta', 'alprazolam',0);
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, loyalty_points, med_type, medicine_code, mode, name, note, producer,rating, shape,  structure, version) VALUES (10, 'None', '2','AVAILABLE',22,'Humani lekovi','120','RECIPE','Rantudil forte 60mg','Koristi se u terapiji bola i zapaljenja kod reumatoidnog artritisa (hronični poliartritis)','MEDA MANUFACTURING GMBH',0,'Kapsula', 'acemetacin',0);

INSERT INTO PHARMACY_MEDICINES (id, price, price_lasts_to, quantity, status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (1, 150,  '2021-02-06', 5, 'AVAILABLE',0, 1,1);
INSERT INTO PHARMACY_MEDICINES (id, price, price_lasts_to, quantity,status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (2, 230,  '2021-01-06', 5, 'AVAILABLE',0, 2,1);
INSERT INTO PHARMACY_MEDICINES (id, price, price_lasts_to, quantity,status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (3,175, '2021-02-06', 5, 'AVAILABLE', 0,3,1);
INSERT INTO PHARMACY_MEDICINES (id, price,  price_lasts_to,quantity,status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (4,165, '2021-02-06', 5,  'AVAILABLE',0, 4,1);
INSERT INTO PHARMACY_MEDICINES (id, price, price_lasts_to, quantity, status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (5,155, '2021-02-06', 5, 'AVAILABLE',0, 1,2);
INSERT INTO PHARMACY_MEDICINES (id, price, price_lasts_to, quantity, status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (6,145, '2021-02-06', 5, 'AVAILABLE',0, 4,2);
INSERT INTO PHARMACY_MEDICINES (id,price, price_lasts_to, quantity, status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (7,280, '2021-02-06', 5, 'AVAILABLE',0, 6,3);
INSERT INTO PHARMACY_MEDICINES (id,price, price_lasts_to, quantity, status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (8,300, '2021-02-06', 5,'AVAILABLE', 0,7,3);
INSERT INTO PHARMACY_MEDICINES (id,price, price_lasts_to, quantity, status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (9, 210, '2021-02-06',5,'AVAILABLE',0, 8,3);
INSERT INTO PHARMACY_MEDICINES (id, price, price_lasts_to, quantity, status_in_pharmacy ,version,medicine_id, pharmacy_id) VALUES (10,225, '2021-02-06', 5, 'AVAILABLE',0, 2,3);




INSERT INTO EMPLOYED_DERMATOLOGIST (id, date_from, date_to, price, rating, work_from, work_to, dermatologist_id, pharmacy_id) VALUES (1, '2020-11-01', '2021-02-01', 120, 0,'06:00:00', '14:00:00', 3, 1 );
INSERT INTO EMPLOYED_DERMATOLOGIST (id, date_from, date_to, price, rating, work_from, work_to, dermatologist_id, pharmacy_id) VALUES (2, '2020-11-01', '2021-02-01', 100, 0, '18:00:00', '21:00:00', 3, 2);
INSERT INTO EMPLOYED_DERMATOLOGIST (id, date_from, date_to, price, rating, work_from, work_to, dermatologist_id, pharmacy_id) VALUES (3, '2020-11-01', '2021-02-01', 110, 0, '07:00:00', '15:00:00', 4, 1);

INSERT INTO APPOINTMENT (id, date_of_appointment, duration, loyalty_points, meeting_time, price, status, type, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (1, '2021-01-20', 2,0, '08:22:00', 50, 'RESERVED', 'EXAMINATION',0, 1, 1, NULL, 1);
INSERT INTO APPOINTMENT (id, date_of_appointment, duration, loyalty_points, meeting_time, price, status, type, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (2, '2021-01-23', 2, 0,'10:22:00', 50, 'RESERVED', 'EXAMINATION',0, 2, 1, NULL, 1);
INSERT INTO APPOINTMENT (id, date_of_appointment, duration, loyalty_points, meeting_time, price, status, type, version, dermatologist_id, patient_id, pharmacist_id, pharmacy_id) VALUES (3, '2021-02-25', 1, 0, '09:00:00', 50, 'FREE', 'EXAMINATION',   0, 1,NULL, NULL, 1);
INSERT INTO VACATION (id, status, vacation_from, vacation_to, dermatologist_id, pharmacist_id) VALUES (1, 'WAITING_FOR_APPROVAL', '2021-01-15', '2021-01-25', 1, NULL);
INSERT INTO VACATION (id, status, vacation_from, vacation_to, dermatologist_id, pharmacist_id) VALUES (2, 'WAITING_FOR_APPROVAL', '2021-01-15', '2021-01-25', 2, NULL);

INSERT INTO PURCHASE_ORDER (id, date, status,version, pharmacy_administrator_id, pharmacy_id) VALUES (1, '2021-02-26', 'WAITING_FOR_OFFER',0, 2, 1);


INSERT INTO ORDER_ITEM (id, quantity, medicine_id, purchase_order_id ) VALUES (1, 5, 2, 1);
INSERT INTO ORDER_ITEM (id, quantity, medicine_id, purchase_order_id ) VALUES (2, 10, 5, 1);