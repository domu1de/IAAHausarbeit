// Drop old state
DELETE FROM EXAM_PERFORMANCE_PROTOCOL_ITEM;
DELETE FROM EXAM_PERFORMANCE;
DELETE FROM EXAM_LECTURERS;
DELETE FROM EXAM;
DELETE FROM COURSE_LECTURERS;
DELETE FROM EMPLOYEE;
DELETE FROM COURSE;
DELETE FROM STUDENT;
DELETE FROM MANIPLE;
DELETE FROM FIELD_OF_STUDY;
DELETE FROM USER_SESSION;
DELETE FROM ACTIVATION;
DELETE FROM PASSWORD_RESET;
DELETE FROM USER;
DELETE FROM ROLE_PERMISSIONS;
DELETE FROM ROLE;

// Insert roles
INSERT INTO ROLE (ID, CREATED_AT, UPDATED_AT, NAME) VALUES
  (1, now(), now(), 'guest'),
  (2, now(), now(), 'admin'),
  (3, now(), now(), 'lecturer'),
  (4, now(), now(), 'student'),
  (5, now(), now(), 'management');

// Insert role permissions
INSERT INTO ROLE_PERMISSIONS (ROLE, PERMISSIONS) VALUES
  (2, 'EDIT_USER'),
  (2, 'CREATE_USER'),
  (2, 'DELETE_USER'),
  (2, 'SHOW_USER');

// Insert users
INSERT INTO USER (ID, CREATED_AT, UPDATED_AT, ACTIVATED, EMAIL, FULL_NAME, PASSWORD, USERNAME, ROLE) VALUES
  (1, NOW(), NOW(), TRUE, 'admin@iaahausarbeit.de', 'Administrator',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', 'admin', 2),
  (2, NOW(), NOW(), TRUE, 'alexander.mersmann@gmx.de', 'Alexander Mersmann',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', '12832', 4),
  (3, NOW(), NOW(), TRUE, 'domenic@muskulus.eu', 'Domenic Muskulus',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', '12838', 4),
  (4, NOW(), NOW(), TRUE, 'stephan.anft@nordakademie.de', 'Stephan Anft',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', 'anft', 3),
  (5, NOW(), NOW(), TRUE, 'stefan.reichert@nordakademie.de', 'Stefan Reichert',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', 'reichert', 3),
  (6, NOW(), NOW(), TRUE, 'management@iaahausarbeit.de', 'Prüfungsamt',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', 'mgmt', 5);

// Insert fields of studies
INSERT INTO FIELD_OF_STUDY (ID, CREATED_AT, UPDATED_AT, ABBREVIATION, NAME) VALUES
  (1, NOW(), NOW(), 'I', 'Wirtschaftsinformatik'),
  (2, NOW(), NOW(), 'W', 'Wirtschaftsingenieurwesen'),
  (3, NOW(), NOW(), 'A', 'Angewandte Informatik'),
  (4, NOW(), NOW(), 'B', 'Betriebswirtschaftslehre');

// Insert maniples
INSERT INTO MANIPLE (ID, CREATED_AT, UPDATED_AT, YEAR, FIELD_OF_STUDY) VALUES
  (1, NOW(), NOW(), '2011-01-01', 1),
  (2, NOW(), NOW(), '2011-01-01', 2),
  (3, NOW(), NOW(), '2011-01-01', 4),
  (4, NOW(), NOW(), '2012-01-01', 1),
  (5, NOW(), NOW(), '2015-01-01', 1),
  (6, NOW(), NOW(), '2015-01-01', 2),
  (7, NOW(), NOW(), '2015-01-01', 3),
  (8, NOW(), NOW(), '2015-01-01', 4);

// Insert students;
INSERT INTO STUDENT (ID, CREATED_AT, UPDATED_AT, TITLE, FIRST_NAME, LAST_NAME, STUDENT_ID, MANIPLE, USER) VALUES
  (1, NOW(), NOW(), '', 'Alexander', 'Mersmann', 5037, 1, 2),
  (2, NOW(), NOW(), '', 'Domenic', 'Muskulus', 5043, 1, 3),
  (3, NOW(), NOW(), '', 'DummyStudentI11', '1', 1001, 1, NULL),
  (4, NOW(), NOW(), '', 'DummyStudentI11', '2', 1002, 1, NULL),
  (5, NOW(), NOW(), '', 'DummyStudentI11', '3', 1003, 1, NULL),
  (6, NOW(), NOW(), '', 'DummyStudentI11', '4', 1004, 1, NULL),
  (7, NOW(), NOW(), '', 'DummyStudentI11', '5', 1005, 1, NULL),
  (8, NOW(), NOW(), '', 'DummyStudentI11', '6', 1006, 1, NULL),
  (9, NOW(), NOW(), '', 'DummyStudentI11', '7', 1007, 1, NULL),
  (10, NOW(), NOW(), '', 'DummyStudentI11', '8', 1008, 1, NULL),
  (11, NOW(), NOW(), '', 'DummyStudentI11', '9', 1009, 1, NULL),
  (12, NOW(), NOW(), '', 'DummyStudentI11', '10', 1010, 1, NULL),
  (13, NOW(), NOW(), '', 'DummyStudentW11', '1', 1011, 2, NULL),
  (14, NOW(), NOW(), '', 'DummyStudentW11', '2', 1012, 2, NULL),
  (15, NOW(), NOW(), '', 'DummyStudentW11', '3', 1013, 2, NULL),
  (16, NOW(), NOW(), '', 'DummyStudentW11', '4', 1014, 2, NULL),
  (17, NOW(), NOW(), '', 'DummyStudentW11', '5', 1015, 2, NULL),
  (18, NOW(), NOW(), '', 'DummyStudentW11', '6', 1016, 2, NULL),
  (19, NOW(), NOW(), '', 'DummyStudentW11', '7', 1017, 2, NULL),
  (20, NOW(), NOW(), '', 'DummyStudentW11', '8', 1018, 2, NULL),
  (21, NOW(), NOW(), '', 'DummyStudentW11', '9', 1019, 2, NULL),
  (22, NOW(), NOW(), '', 'DummyStudentW11', '10', 1020, 2, NULL),
  (23, NOW(), NOW(), '', 'DummyStudentB11', '1', 1021, 3, NULL),
  (24, NOW(), NOW(), '', 'DummyStudentB11', '2', 1022, 3, NULL),
  (25, NOW(), NOW(), '', 'DummyStudentB11', '3', 1023, 3, NULL),
  (26, NOW(), NOW(), '', 'DummyStudentB11', '4', 1024, 3, NULL),
  (27, NOW(), NOW(), '', 'DummyStudentB11', '5', 1025, 3, NULL),
  (28, NOW(), NOW(), '', 'DummyStudentB11', '6', 1026, 3, NULL),
  (29, NOW(), NOW(), '', 'DummyStudentB11', '7', 1027, 3, NULL),
  (30, NOW(), NOW(), '', 'DummyStudentB11', '8', 1028, 3, NULL),
  (31, NOW(), NOW(), '', 'DummyStudentB11', '9', 1029, 3, NULL),
  (32, NOW(), NOW(), '', 'DummyStudentB11', '10', 1030, 3, NULL),
  (33, NOW(), NOW(), '', 'DummyStudentI12', '1', 1031, 4, NULL),
  (34, NOW(), NOW(), '', 'DummyStudentI12', '2', 1032, 4, NULL),
  (35, NOW(), NOW(), '', 'DummyStudentI12', '3', 1033, 4, NULL),
  (36, NOW(), NOW(), '', 'DummyStudentI12', '4', 1034, 4, NULL),
  (37, NOW(), NOW(), '', 'DummyStudentI12', '5', 1035, 4, NULL),
  (38, NOW(), NOW(), '', 'DummyStudentI12', '6', 1036, 4, NULL),
  (39, NOW(), NOW(), '', 'DummyStudentI12', '7', 1037, 4, NULL),
  (40, NOW(), NOW(), '', 'DummyStudentI12', '8', 1038, 4, NULL),
  (41, NOW(), NOW(), '', 'DummyStudentI12', '9', 1039, 4, NULL),
  (42, NOW(), NOW(), '', 'DummyStudentI12', '10', 1040, 4, NULL),
  (43, NOW(), NOW(), '', 'DummyStudentI15', '1', 1041, 5, NULL),
  (44, NOW(), NOW(), '', 'DummyStudentI15', '2', 1042, 5, NULL),
  (45, NOW(), NOW(), '', 'DummyStudentI15', '3', 1043, 5, NULL),
  (46, NOW(), NOW(), '', 'DummyStudentI15', '4', 1044, 5, NULL),
  (47, NOW(), NOW(), '', 'DummyStudentI15', '5', 1045, 5, NULL),
  (48, NOW(), NOW(), '', 'DummyStudentI15', '6', 1046, 5, NULL),
  (49, NOW(), NOW(), '', 'DummyStudentI15', '7', 1047, 5, NULL),
  (50, NOW(), NOW(), '', 'DummyStudentI15', '8', 1048, 5, NULL),
  (51, NOW(), NOW(), '', 'DummyStudentI15', '9', 1049, 5, NULL),
  (52, NOW(), NOW(), '', 'DummyStudentI15', '10', 1050, 5, NULL),
  (53, NOW(), NOW(), '', 'DummyStudentW15', '1', 1051, 6, NULL),
  (54, NOW(), NOW(), '', 'DummyStudentW15', '2', 1052, 6, NULL),
  (55, NOW(), NOW(), '', 'DummyStudentW15', '3', 1053, 6, NULL),
  (56, NOW(), NOW(), '', 'DummyStudentW15', '4', 1054, 6, NULL),
  (57, NOW(), NOW(), '', 'DummyStudentW15', '5', 1055, 6, NULL),
  (58, NOW(), NOW(), '', 'DummyStudentW15', '6', 1056, 6, NULL),
  (59, NOW(), NOW(), '', 'DummyStudentW15', '7', 1057, 6, NULL),
  (60, NOW(), NOW(), '', 'DummyStudentW15', '8', 1058, 6, NULL),
  (61, NOW(), NOW(), '', 'DummyStudentW15', '9', 1059, 6, NULL),
  (62, NOW(), NOW(), '', 'DummyStudentW15', '10', 1060, 6, NULL),
  (63, NOW(), NOW(), '', 'DummyStudentA15', '1', 1061, 7, NULL),
  (64, NOW(), NOW(), '', 'DummyStudentA15', '2', 1062, 7, NULL),
  (65, NOW(), NOW(), '', 'DummyStudentA15', '3', 1063, 7, NULL),
  (66, NOW(), NOW(), '', 'DummyStudentA15', '4', 1064, 7, NULL),
  (67, NOW(), NOW(), '', 'DummyStudentA15', '5', 1065, 7, NULL),
  (68, NOW(), NOW(), '', 'DummyStudentA15', '6', 1066, 7, NULL),
  (69, NOW(), NOW(), '', 'DummyStudentA15', '7', 1067, 7, NULL),
  (70, NOW(), NOW(), '', 'DummyStudentA15', '8', 1068, 7, NULL),
  (71, NOW(), NOW(), '', 'DummyStudentA15', '9', 1069, 7, NULL),
  (72, NOW(), NOW(), '', 'DummyStudentA15', '10', 1070, 7, NULL),
  (73, NOW(), NOW(), '', 'DummyStudentB15', '1', 1071, 8, NULL),
  (74, NOW(), NOW(), '', 'DummyStudentB15', '2', 1072, 8, NULL),
  (75, NOW(), NOW(), '', 'DummyStudentB15', '3', 1073, 8, NULL),
  (76, NOW(), NOW(), '', 'DummyStudentB15', '4', 1074, 8, NULL),
  (77, NOW(), NOW(), '', 'DummyStudentB15', '5', 1075, 8, NULL),
  (78, NOW(), NOW(), '', 'DummyStudentB15', '6', 1076, 8, NULL),
  (79, NOW(), NOW(), '', 'DummyStudentB15', '7', 1077, 8, NULL),
  (80, NOW(), NOW(), '', 'DummyStudentB15', '8', 1078, 8, NULL),
  (81, NOW(), NOW(), '', 'DummyStudentB15', '9', 1079, 8, NULL),
  (82, NOW(), NOW(), '', 'DummyStudentB15', '10', 1080, 8, NULL);

// Insert courses
INSERT INTO COURSE (ID, CREATED_AT, UPDATED_AT, DESCRIPTION, TITLE, MANIPLE) VALUES
  (1, NOW(), NOW(), 'Beschreibungstext IAA 2011', 'Internet Anwendungsarchitekturen', 1),
  (2, NOW(), NOW(), 'Beschreibungstext IAA 2015', 'Internet Anwendungsarchitekturen', 7),
  (3, NOW(), NOW(), 'Beschreibungstext Prog 1 2011', 'Programmierung 1', 1),
  (4, NOW(), NOW(), 'Beschreibungstext Prog 2 2011', 'Programmierung 2', 1),
  (5, NOW(), NOW(), 'Beschreibungstext Prog 1 2015', 'Programmierung 1', 7),
  (6, NOW(), NOW(), 'Dummy Kurs I11', 'Dummy Kurs I11', 1),
  (7, NOW(), NOW(), 'Dummy Kurs W11', 'Dummy Kurs W11', 2),
  (8, NOW(), NOW(), 'Dummy Kurs B11', 'Dummy Kurs B11', 3),
  (9, NOW(), NOW(), 'Dummy Kurs I12', 'Dummy Kurs I12', 4),
  (10, NOW(), NOW(), 'Dummy Kurs I15', 'Dummy Kurs I15', 5),
  (11, NOW(), NOW(), 'Dummy Kurs W15', 'Dummy Kurs W15', 6),
  (12, NOW(), NOW(), 'Dummy Kurs A15', 'Dummy Kurs A15', 7),
  (13, NOW(), NOW(), 'Dummy Kurs B15', 'Dummy Kurs B15', 8);

// Insert employees
INSERT INTO EMPLOYEE (ID, CREATED_AT, UPDATED_AT, FIRST_NAME, LAST_NAME, TITLE, USER, EMPLOYEE_NUMBER) VALUES
  (1, NOW(), NOW(), 'Stephan', 'Anft', '', 4, 1001),
  (2, NOW(), NOW(), 'Stefan', 'Reichert', '', 5, 1002),
  (3, NOW(), NOW(), 'Ulrike', 'Heinrich', '', 6, 1003),
  (4, NOW(), NOW(), 'Johannes', 'Brauer', 'Prof. Dr.-Ing.', NULL, 1004),
  (5, NOW(), NOW(), 'Dummy', 'Dozent', '', NULL, 1005);

// Insert employee-course-mapping
INSERT INTO COURSE_LECTURERS (COURSE, LECTURERS) VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (2, 2),
  (3, 4),
  (4, 4),
  (5, 4),
  (6, 5),
  (7, 5),
  (8, 5),
  (9, 5),
  (10, 5),
  (11, 5),
  (12, 5),
  (13, 5);