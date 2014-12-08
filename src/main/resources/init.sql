DELETE FROM COURSE_LECTURERS;
DELETE FROM EMPLOYEE;
DELETE FROM COURSE;
DELETE FROM STUDENT;
DELETE FROM FIELD_OF_STUDY;
DELETE FROM MANIPLE;
DELETE FROM USER;
DELETE FROM ROLE_PERMISSIONS;
DELETE FROM ROLE;
INSERT INTO ROLE (ID, CREATED_AT, UPDATED_AT, NAME) VALUES
  (1, now(), now(), 'guest'),
  (2, now(), now(), 'admin'),
  (3, now(), now(), 'lecturer'),
  (4, now(), now(), 'student'),
  (5, now(), now(), 'management');
INSERT INTO ROLE_PERMISSIONS (ROLE, PERMISSIONS) VALUES
  (2, 'EDIT_USER'),
  (2, 'CREATE_USER'),
  (2, 'DELETE_USER'),
  (2, 'SHOW_USER');
INSERT INTO USER (ID, CREATED_AT, UPDATED_AT, ACTIVATED, EMAIL, FULL_NAME, PASSWORD, USERNAME, ROLE) VALUES
  (1, NOW(), NOW(), TRUE, 'admin@iaahausarbeit.de', 'Administrator',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', 'admin', 2),
  (2, NOW(), NOW(), TRUE, 'alexander.mersmann@nordakademie.de', 'Alexander Mersmann',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', '12832', 4),
  (3, NOW(), NOW(), TRUE, 'domenic.muskulus@nordakademie.de', 'Domenic Muskulus',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', '12838', 4),
  (4, NOW(), NOW(), TRUE, 'stephan.anft@nordakademie.de', 'Stephan Anft',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', 'anft', 3),
  (5, NOW(), NOW(), TRUE, 'stefan.reichert@nordakademie.de', 'Stefan Reichert',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', 'reichert', 3),
  (6, NOW(), NOW(), TRUE, 'management@iaahausarbeit.de', 'Prüfungsamt',
   '$2a$10$.p3HZN.0FySMsam8Yz7er.2NlbBzQPXQYUCt.karL3F6ycXSvAaBy', 'mgmt', 5);
INSERT INTO FIELD_OF_STUDY (ID, CREATED_AT, UPDATED_AT, ABBREVIATION, NAME) VALUES
  (1, NOW(), NOW(), 'I', 'Wirtschaftsinformatik'),
  (2, NOW(), NOW(), 'W', 'Wirtschaftsingenieurwesen'),
  (3, NOW(), NOW(), 'A', 'Angewandte Informatik'),
  (4, NOW(), NOW(), 'B', 'Betriebswirtschaftslehre');
INSERT INTO MANIPLE (ID, CREATED_AT, UPDATED_AT, YEAR, FIELD_OF_STUDY) VALUES
  (1, NOW(), NOW(), '2011-01-01', 1),
  (2, NOW(), NOW(), '2011-01-01', 2),
  (3, NOW(), NOW(), '2011-01-01', 3),
  (4, NOW(), NOW(), '2012-01-01', 1),
  (5, NOW(), NOW(), '2015-01-01', 1),
  (6, NOW(), NOW(), '2015-01-01', 2),
  (7, NOW(), NOW(), '2015-01-01', 3),
  (8, NOW(), NOW(), '2015-01-01', 4);
INSERT INTO STUDENT (ID, CREATED_AT, UPDATED_AT, FIRST_NAME, LAST_NAME, STUDENT_ID, MANIPLE, USER) VALUES
  (1, NOW(), NOW(), 'Alexander', 'Mersmann', 5037, 1, 2),
  (2, NOW(), NOW(), 'Domenic', 'Muskulus', 5043, 1, 3),
  (3, NOW(), NOW(), 'DummyStudent', '1', 1001, 7, NULL),
  (4, NOW(), NOW(), 'DummyStudent', '2', 1002, 7, NULL),
  (5, NOW(), NOW(), 'DummyStudent', '3', 1003, 7, NULL),
  (6, NOW(), NOW(), 'DummyStudent', '4', 1004, 7, NULL),
  (7, NOW(), NOW(), 'DummyStudent', '5', 1005, 7, NULL),
  (8, NOW(), NOW(), 'DummyStudent', '6', 1006, 7, NULL),
  (9, NOW(), NOW(), 'DummyStudent', '7', 1007, 7, NULL),
  (10, NOW(), NOW(), 'DummyStudent', '8', 1008, 7, NULL),
  (11, NOW(), NOW(), 'DummyStudent', '9', 1009, 7, NULL),
  (12, NOW(), NOW(), 'DummyStudent', '10', 1010, 7, NULL);
INSERT INTO COURSE (ID, CREATED_AT, UPDATED_AT, DESCRIPTION, TITLE, MANIPLE) VALUES
  (1, NOW(), NOW(), 'Beschreibungstext IAA 2011', 'Internet Anwendungsarchitekturen', 1),
  (2, NOW(), NOW(), 'Beschreibungstext IAA 2015', 'Internet Anwendungsarchitekturen', 7),
  (3, NOW(), NOW(), 'Beschreibungstext Prog 1 2011', 'Programmierung 1', 1),
  (4, NOW(), NOW(), 'Beschreibungstext Prog 2 2011', 'Programmierung 2', 1),
  (5, NOW(), NOW(), 'Beschreibungstext Prog 1 2015', 'Programmierung 2', 7);
INSERT INTO EMPLOYEE (ID, CREATED_AT, UPDATED_AT, FIRST_NAME, LAST_NAME, TITLE, USER) VALUES
  (1, NOW(), NOW(), 'Stephan', 'Anft', '', 4),
  (2, NOW(), NOW(), 'Stefan', 'Reichert', '', 5),
  (3, NOW(), NOW(), 'Ulrike', 'Heinrich', '', 6),
  (4, NOW(), NOW(), 'Johannes', 'Brauer', 'Prof. Dr.-Ing.', NULL);
INSERT INTO COURSE_LECTURERS (COURSE, LECTURERS) VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (2, 2),
  (3, 4),
  (4, 4),
  (5, 4);