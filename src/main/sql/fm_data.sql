INSERT INTO `fm_user_roles` VALUES (1, 'ADMIN'), (2, 'USER');

INSERT INTO `fm_users`
VALUES
  (1, 'admin', 'admin'), (2, 'haho', 'hoanhhao'), (3, 'hao', 'hiep'), (4, 'hiep', 'hiep'),
  (6, 'admin1', 'admin'), (7, 'admin2', 'admin'), (8, 'admin3', 'admin'),
  (9, 'admin4', 'admin'), (12, 'haho1', 'hoanhhao'), (13, 'haho13', 'hoanhhao'),
  (14, 'haho14', 'hoanhhao'), (15, 'haho15', 'hoanhhao'), (16, 'haho16', 'hoanhhao'),
  (17, 'haho17', 'hoanhhao'), (18, 'haho18', 'hoanhhao'), (19, 'haho19', 'hoanhhao'),
  (20, 'haho20', 'hoanhhao'), (21, 'haho21', 'hoanhhao'), (22, 'haho22', 'hoanhhao'),
  (23, 'haho23', 'hoanhhao'), (24, 'haho24', 'hoanhhao'), (25, 'haho25', 'hoanhhao'),
  (26, 'haho26', 'hoanhhao'), (27, 'haho27', 'hoanhhao'), (28, 'haho28', 'hoanhhao'),
  (29, 'haho29', 'hoanhhao'), (30, 'haho30', 'hoanhhao'), (31, 'haho31', 'hoanhhao'),
  (32, 'haho32', 'hoanhhao'), (33, 'haho33', 'hoanhhao'), (34, 'haho34', 'hoanhhao'),
  (36, 'haho36', 'hoanhhao'), (37, 'haho37', 'hoanhhao'), (38, 'haho38', 'hoanhhao'),
  (39, 'customer', 'customer'), (40, 'staff', 'staff'),
  (41, 'haho41', 'hoanhhao'), (42, 'haho42', 'hoanhhao'), (43, 'haho43', 'hoanhhao'),
  (44, 'haho44', 'hoanhhao'), (45, 'haho45', 'hoanhhao'), (46, 'haho46', 'hoanhhao'),
  (47, 'haho47', 'hoanhhao'), (48, 'haho48', 'hoanhhao'), (5, 'haho5', 'hoanhhao'),
  (10, 'haho10', 'hoanhhao'), (11, 'haho11', 'hoanhhao'), (49, 'haho49', 'hoanhhao'),
  (50, 'haho50', 'hoanhhao'), (51, 'haho52', 'hoanhhao'), (53, 'haho53', 'hoanhhao'),
  (54, 'haho55', 'hoanhhao'), (56, 'haho56', 'hoanhhao'), (57, 'haho57', 'hoanhhao'),
  (58, 'haho58', 'hoanhhao'), (59, 'haho59', 'hoanhhao'), (60, 'haho60', 'hoanhhao'),
  (61, 'haho61', 'hoanhhao'), (62, 'haho62', 'hoanhhao'), (63, 'haho63', 'hoanhhao')
;

INSERT INTO `fm_user_role_details` VALUES
  (1, 1, 1), (2, 2, 2)
;

INSERT INTO `fm_messages` (`role_id`, `component_name`, `message_key`, `message_en`, `message_fr`)
VALUES
  (1, 'roomsList', 'name', 'Name', 'NOM'),
  (1, 'roomsList', 'numOfPeople', 'Num of people', 'Nombre de personnes'),
  (1, 'roomsList', 'numOfBeds', 'Num of beds', 'Nombre de lits'),
  (1, 'roomsList', 'typeOfBed', 'Type of bed', 'Type de lit'),
  (1, 'roomUpdate', 'name', 'Name', 'NOM'),
  (1, 'roomUpdate', 'numOfPeople', 'Num of people', 'Nombre de personnes'),
  (1, 'roomUpdate', 'numOfBeds', 'Num of beds', 'Nombre de lits'),
  (1, 'roomUpdate', 'typeOfBed', 'Type of bed', 'Type de lit'),
  (null, 'login', 'userName', 'User name', 'Nom d''utilisateur'),
  (null, 'login', 'password', 'Password', 'Mot de passe'),
  (null, 'login', 'loginButton', 'Login', 'Soumettre'),
  (null, 'login', 'registerButton', 'Register', 'Registre'),
  (null, 'login', 'loginTitle', 'Login', 'S''identifier'),
  (null, 'login', 'language', 'Language', 'Langue')
;

INSERT INTO fm_payment_methods(`id`, `name`) VALUES (1, 'VISA CREDIT'), (2, 'VISA DEBIT'), (3, 'American Express');

INSERT INTO fm_cards_information (`id`, `name`, `start_date`, `expiry_date`, `amount`, `card_number`, `card_type_id`, `user_id`)
VALUES (1, 'HSBC 2017', '2017-03-08', '2021-03-07', 57500000, '**49 5256', 2, 2);

INSERT INTO `fm_earnings` (`user_id`, `amount`, `description`) VALUES (2, 20000000, 'monthly salary');

INSERT INTO fm_expenses (`user_id`, `amount`, `place`, `date`, `for_person`, `is_an_event`, `card_id`)
VALUES
  (2, 37600, 'Grab', '2017-05-01',NULL, FALSE, NULL),
  (2, 56000, 'Vinasun', '2017-05-02', NULL, FALSE, NULL),
  (2, 78000, 'New Sun Hotel', '2017-05-03', NULL, FALSE, NULL),
  (2, 90000, 'Bun Bo Ganh', '2017-05-04', NULL, FALSE, NULL),
  (2, 120000, 'That\'s cafe', '2017-05-05', NULL, FALSE, NULL),
  (2, 14000, 'Citibank annual fee', '2017-05-06', NULL, FALSE, 1),
  (2, 45000, 'Maximax', '2017-05-07', NULL, FALSE, 1),
  (2, 5000, 'Highlands Coffee', '2017-05-08', NULL, FALSE, 1),
  (2, 70000, 'Year-end party', '2017-05-09', NULL, FALSE, 1),
  (2, 990000, 'Hang Duong Quan', '2017-05-12', NULL, FALSE, 1),

  (2, 15000, 'The Coffee House', '2017-06-01',NULL, FALSE, NULL),
  (2, 45000, 'Gongcha', '2017-06-02', NULL, FALSE, NULL),
  (2, 55000, 'KOI', '2017-06-03', NULL, FALSE, NULL),
  (2, 50000, 'Watcha', '2017-06-04', NULL, FALSE, NULL),
  (2, 20000, 'Nha Khoa Minh Khai', '2017-06-05', NULL, FALSE, NULL),
  (2, 15000, 'CGV', '2017-06-06', NULL, FALSE, 1),
  (2, 45000, 'AEON Mall', '2017-06-07', NULL, FALSE, 1),
  (2, 55000, 'Tokio Deli', '2017-06-08', NULL, FALSE, 1),
  (2, 50000, 'Traveloka', '2017-06-09', NULL, FALSE, 1),
  (2, 20000, 'Uber', '2017-06-12', NULL, FALSE, 1)
;
