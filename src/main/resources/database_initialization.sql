# noinspection SqlWithoutWhereForFile

USE banca_sanguina_spring_v2;

DELETE
FROM app_admin
WHERE username = username;
DELETE
FROM app_appointment
WHERE uuid = uuid;
DELETE
FROM app_doctor
WHERE username = username;
DELETE
FROM app_donor
WHERE username = username;
DELETE
FROM app_location
WHERE uuid = uuid;
DELETE
FROM app_user
WHERE username = username;

INSERT INTO app_user
VALUES ("admin", "admin"),
       ("admin2", "admin2"),
       ("doctor", "doctor"),
       ("doctor2", "doctor2"),
       ("doctor3", "doctor3"),
       ("donor", "donor"),
       ("donor2", "donor2"),
       ("donor3", "donor3"),
       ("donor4", "donor4");

INSERT INTO app_admin
VALUES ("Gigel", "Gigi", "admin"),
       ("Robert", "Bob", "admin2");
INSERT INTO app_location
VALUES ("78bb89be-0719-4a50-9b66-71c7eed8c8ce", "Strada bla", "08:01", 10, "Regina Maria", "08:00"),
       ("c606f98f-5550-4669-b079-01ce08392eaf", "Ceva sat IDK", "20:00", 200, "MediCover", "06:00");
INSERT INTO app_doctor
VALUES ("5020506204963", "doctor@gmail.com", "Ion", "Ionel", "doctor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("2020506204962", "doctor2@gmail.com", "Ionica", "Georgica", "doctor2", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("4020506204963", "doctor3@gmail.com", "Ionela", "Vasilica", "doctor3", "c606f98f-5550-4669-b079-01ce08392eaf");
INSERT INTO app_donor
VALUES ("donor@gmail.com", "Ion", "+40762111001", "Ionel", "donor"),
       ("donor2@gmail.com","Andreea", "+40762111002", "Ionela", "donor2"),
       ("donor3@gmail.com","Lorena", "+40762111003", "Gigi", "donor3"),
       ("donor4@gmail.com","Gheorghe", "+40762111004", "Adrian", "donor4");
INSERT INTO app_appointment
VALUES ("db25d620-f9ec-4d06-911e-188104fbcb1e", "2023-05-03", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("72f11845-02e0-4602-af79-06da7b956074", "2023-05-03", true, false, true, "donor2", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("380ae17a-5c72-47ba-96ed-ef4d5eb5dfb2", "2023-05-03", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("0a7e3918-9cdc-49ce-9d09-6c83ec682764", "2023-05-03", true, false, true, "donor2", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("5f9b6291-98c4-4f9b-8a89-6354d23f114c", "2023-05-03", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("52eaaf6e-f2df-4e48-9036-531764df2378", "2023-05-03", true, false, true, "donor2", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("4773390a-e337-40bf-8c2c-26e9856a7437", "2023-05-03", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("6682683d-9bbe-421f-9ab9-ae8e5c84b665", "2023-05-03", true, false, true, "donor2", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("e20ba30b-59c6-4621-9e65-5618b4c43260", "2023-05-03", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("aee9fc34-e452-4350-91a7-6fa003b136f5", "2023-05-03", true, false, true, "donor2", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("8ee08351-6327-4647-bac8-ce858cd6c870", "2023-05-04", true, false, true, "donor3", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("068ad792-4edb-41ba-ad2a-b2cb54faac0c", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("98d887d5-ab1d-4dc8-b18a-35457882f8ca", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("5c3d9e04-6902-47cd-9531-bb975e14adb2", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("7910cc72-c93b-474b-85ea-3644617546bf", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("d8faa201-c888-4059-81fc-c98f7c34e4e9", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("0c6d2557-6f48-4c5e-90c6-b014e4f4f72e", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("f818e96e-1a56-4c03-a5c2-928ac36435b2", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("8559a121-a052-4007-85be-dace4695bd4c", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("1648df4d-1942-4e12-8192-e468960b1189", "2023-04-27", true, false, true, "donor", "78bb89be-0719-4a50-9b66-71c7eed8c8ce"),
       ("82ebf78e-0544-4d4b-b0d5-a2f70c59bcd1", "2023-04-27", true, false, true, "donor2", "78bb89be-0719-4a50-9b66-71c7eed8c8ce");