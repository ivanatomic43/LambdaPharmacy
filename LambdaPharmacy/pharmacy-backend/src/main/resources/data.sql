
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_PHARMA_ADMIN', 2, 'Narodnog fronta 34, Novi Sad', true, 'admin@gmail.com', false, 'Marko', 'Markovic', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '0633216547', 'markomarkovic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_PATIENT',1,'Bulevar Kralja Petra I 12, Novi Sad',true,'ivana@gmail.com',false,'Ivana','Tomic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','0601234567','ivanatomic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_DERMATOLOGIST',3,'Bulevar Oslobodjenja 56, Novi Sad',true,'derma@gmail.com',false,'Pera','Peric','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','0601234488','peraperic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_DERMATOLOGIST',4,'Jevrejska 33, Novi Sad',true,'dermamika@gmail.com',false,'Mika','Mikic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','0601277567','mikamikic');
INSERT INTO USER (user_type, id, address, approved, email, first_login, first_name, last_name, password, phone_number, username) values ('USER_SYS_ADMIN',5,'Masarikova 7, Novi Sad',true,'adminsistema@gmail.com',false,'Petar','Petrovic','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','0601277444','petarpetrovic');



INSERT INTO PHARMACY (id, address, description, name, rating) VALUES (1, 'Bulevar Oslobodjenja 56, Novi Sad', 'Otvorena 24/7', 'Jankovic DOO',0);
INSERT INTO PHARMACY (id, address, description, name, rating) VALUES (2, 'Lasla Gala 12, Novi Sad', 'Sirok asortiman proizvoda', 'BENU apoteka',0);
INSERT INTO PHARMACY (id, address, description, name, rating) VALUES (3, 'Kisacka 27, Novi Sad', 'Sirok asortiman proizvoda', 'Zegin',0);

INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (1, 'None', '3', 'Humani lekovi','111','NO_RECIPE','Strepsils','Pogodno za decu','RECKITT BENCKISER HEALTHCARE','Lozenga', '2,4-dihlorbenzilalkohol, amilmetakrezol');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (2, 'None', '4','Humani lekovi','112','NO_RECIPE','Andol','Za sve vrste bolova u telu','Pliva Hrvatska','Tableta', 'acetilsalicilna kiselina');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (3, 'None', '2','Humani lekovi','113','NO_RECIPE','Aspirin 500mg','Za jaku glavobolju','	BAYER BITTERFELD GMBH','Tableta','acetilsalicilna kiselina');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape, structure) VALUES (4, 'Alergies', '1','Humani lekovi','114','RECIPE','	Enalapril Remedica 10mg','Za kontrolu povišenog krvnog pritiska','REMEDICA LTD','Tableta', 'enalapril');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (5, 'Heart problems', '2','Humani lekovi','115','NO_RECIPE','Brufen 100mg','Za prehlade, kasalj i temepraturu','ABBVIE S.R.L.','Sirup', 'ibuprofen');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (6, 'None', '2','Homeopatski lekovi','116','NO_RECIPE','Influcid','Koristi se u terapiji infekcija slicnih gripu','DEUTSCHE HOMOOPATHIE-UNION','Tableta','Aconitum trit. D3 25 mg Gelsemium trit. D3 ');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape, structure) VALUES (7, 'None', '1','Homeopatski lekovi','117','NO_RECIPE','Polinol','Za terapiju alergijskih reakcija disajnih puteva','DEUTSCHE HOMOOPATHIE-UNION ','Tableta', 'luffa operculata trit. D4, galphimia glauca trit. D3, cardiospermum trit. D3');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape, structure) VALUES (8, 'Alergies', '2','Humani lekovi','118','RECIPE','Kardioprotekt','Za smanjenje rizika smrtnosti kod osoba koje su preživele infarkt kao i za sprečavanje moždanog udara','AD JAKA 80 RADOVIŠ','Gastrorezistentna tableta', 'acetilsalicilna kiselina');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (9, 'Not for children', '1','Humani lekovi','119','RECIPE','Xanax','Namenjen za lečenje anksioznosti, napetosti ili drugih somatskih (telesnih) ili psihijatrijskih simptoma povezanih sa anksioznošću','PFIZER ITALIA S.R.L.','Tableta', 'alprazolam');
INSERT INTO MEDICINE (id, contraindications, daily_dose, med_type, medicine_code, mode, name, note, producer, shape,  structure) VALUES (10, 'None', '2','Humani lekovi','120','RECIPE','Rantudil forte 60mg','Koristi se u terapiji bola i zapaljenja kod reumatoidnog artritisa (hronični poliartritis)','MEDA MANUFACTURING GMBH','Kapsula', 'acemetacin');

INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (1,1);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (1,2);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (1,3);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (1,4);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (2,1);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (2,4);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (3,6);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (3,7);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (3,8);
INSERT INTO PHARMACY_MEDICINE (pharmacy_id, medicine_id) VALUES (3,2);

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