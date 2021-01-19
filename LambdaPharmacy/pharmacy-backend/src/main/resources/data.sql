
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_PHARMA_ADMIN', 2, 'Narodnog fronta 34, Novi Sad', true, 'admin@gmail.com', false, 'Marko', 'Markovic', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '0633216547', 'markomarkovic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_PATIENT',1,'Bulevar Kralja Petra I 12, Novi Sad',true,'ivana@gmail.com',false,'Ivana','Tomic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','0601234567','ivanatomic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_DERMATOLOGIST',3,'Bulevar Oslobodjenja 56, Novi Sad',true,'derma@gmail.com',false,'Pera','Peric','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','0601234488','peraperic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_DERMATOLOGIST',4,'Jevrejska 33, Novi Sad',true,'dermamika@gmail.com',false,'Mika','Mikic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','0601277567','mikamikic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_SYS_ADMIN',5,'Masarikova 7, Novi Sad',true,'adminsistema@gmail.com',false,'Petar','Petrovic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','0601277444','petarpetrovic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_PHARMA_ADMIN', 6, 'Branka Bajica 13, Novi Sad', true, 'pharmadmin@gmail.com', false, 'Drugi admin', 'Apoteke', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '0644567894','adminapoteke' );



INSERT INTO PHARMACY (id, address, description, name, rating) VALUES (1, 'Bulevar Oslobodjenja 56, Novi Sad', 'Otvorena 24/7', 'Jankovic DOO',0);
INSERT INTO PHARMACY (id, address, description, name, rating) VALUES (2, 'Lasla Gala 12, Novi Sad', 'Sirok asortiman proizvoda', 'BENU apoteka',0);
INSERT INTO PHARMACY (id, address, description, name, rating) VALUES (3, 'Kisacka 27, Novi Sad', 'Sirok asortiman proizvoda', 'Zegin',0);

INSERT INTO PHARMACY_PHARMACY_ADMINISTRATORS (pharmacy_id, pharmacy_administrators_id) VALUES (1, 2);
INSERT INTO PHARMACY_PHARMACY_ADMINISTRATORS (pharmacy_id, pharmacy_administrators_id) VALUES (2, 6);



INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (1, 'None', '3', 'AVAILABLE', 'Humani lekovi','111','NO_RECIPE','Strepsils','Pogodno za decu','RECKITT BENCKISER HEALTHCARE','Lozenga', '2,4-dihlorbenzilalkohol, amilmetakrezol');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,  med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (2, 'None', '4', 'AVAILABLE','Humani lekovi','112','NO_RECIPE','Andol','Za sve vrste bolova u telu','Pliva Hrvatska','Tableta', 'acetilsalicilna kiselina');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,  med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (3, 'None', '2','AVAILABLE','Humani lekovi','113','NO_RECIPE','Aspirin 500mg','Za jaku glavobolju','	BAYER BITTERFELD GMBH','Tableta','acetilsalicilna kiselina');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, med_type, medicine_code, mode, name, note, producer, shape, structure) VALUES (4, 'Alergies', '1','AVAILABLE','Humani lekovi','114','RECIPE','	Enalapril Remedica 10mg','Za kontrolu povišenog krvnog pritiska','REMEDICA LTD','Tableta', 'enalapril');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (5, 'Heart problems', '2','AVAILABLE','Humani lekovi','115','NO_RECIPE','Brufen 100mg','Za prehlade, kasalj i temepraturu','ABBVIE S.R.L.','Sirup', 'ibuprofen');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (6, 'None', '2','AVAILABLE','Homeopatski lekovi','116','NO_RECIPE','Influcid','Koristi se u terapiji infekcija slicnih gripu','DEUTSCHE HOMOOPATHIE-UNION','Tableta','Aconitum trit. D3 25 mg Gelsemium trit. D3 ');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, med_type, medicine_code, mode, name, note, producer, shape, structure) VALUES (7, 'None', '1','AVAILABLE','Homeopatski lekovi','117','NO_RECIPE','Polinol','Za terapiju alergijskih reakcija disajnih puteva','DEUTSCHE HOMOOPATHIE-UNION ','Tableta', 'luffa operculata trit. D4, galphimia glauca trit. D3, cardiospermum trit. D3');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status, med_type, medicine_code, mode, name, note, producer, shape, structure) VALUES (8, 'Alergies', '2','AVAILABLE','Humani lekovi','118','RECIPE','Kardioprotekt','Za smanjenje rizika smrtnosti kod osoba koje su preživele infarkt kao i za sprečavanje moždanog udara','AD JAKA 80 RADOVIŠ','Gastrorezistentna tableta', 'acetilsalicilna kiselina');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,  med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (9, 'Not for children', '1','AVAILABLE','Humani lekovi','119','RECIPE','Xanax','Namenjen za lečenje anksioznosti, napetosti ili drugih somatskih (telesnih) ili psihijatrijskih simptoma povezanih sa anksioznošću','PFIZER ITALIA S.R.L.','Tableta', 'alprazolam');
INSERT INTO MEDICINE (id, contraindications, daily_dose, default_status,  med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (10, 'None', '2','AVAILABLE','Humani lekovi','120','RECIPE','Rantudil forte 60mg','Koristi se u terapiji bola i zapaljenja kod reumatoidnog artritisa (hronični poliartritis)','MEDA MANUFACTURING GMBH','Kapsula', 'acemetacin');

INSERT INTO PHARMACY_MEDICINES (id, price, quantity,rating, status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (1, 150, 5, 4.8, 'AVAILABLE', 1,1);
INSERT INTO PHARMACY_MEDICINES (id, price, quantity, rating,status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (2, 230, 5, 4.2,'AVAILABLE', 2,1);
INSERT INTO PHARMACY_MEDICINES (id, price, quantity, rating,status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (3,175, 5, 3.1, 'AVAILABLE', 3,1);
INSERT INTO PHARMACY_MEDICINES (id, price, quantity,rating, status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (4,165, 5, 3.6, 'AVAILABLE', 4,1);
INSERT INTO PHARMACY_MEDICINES (id, price,  quantity,rating, status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (5,155, 5, 4.7, 'AVAILABLE', 1,2);
INSERT INTO PHARMACY_MEDICINES (id, price, quantity,rating, status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (6,145, 5, 5, 'AVAILABLE', 4,2);
INSERT INTO PHARMACY_MEDICINES (id,price, quantity,rating, status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (7,280, 5, 3.8,'AVAILABLE', 6,3);
INSERT INTO PHARMACY_MEDICINES (id,price, quantity,rating, status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (8,300, 5, 4.9,'AVAILABLE', 7,3);
INSERT INTO PHARMACY_MEDICINES (id,price, quantity, rating,status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (9, 210,5, 4.0,'AVAILABLE', 8,3);
INSERT INTO PHARMACY_MEDICINES (id, price, quantity,rating, status_in_pharmacy ,medicine_id, pharmacy_id) VALUES (10,225, 5,4.6, 'AVAILABLE', 2,3);



INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_PATIENT');
INSERT INTO AUTHORITY (id, name) VALUES (2,'ROLE_PHARMACIST');
INSERT INTO AUTHORITY (id, name) VALUES (3, 'ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (id, name) VALUES (4, 'ROLE_PHARMACY_ADMIN');
INSERT INTO AUTHORITY (id, name) VALUES (5, 'ROLE_SYS_ADMIN');



INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1,1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2,4);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (3,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (4,3);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (5,5);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (6,4);