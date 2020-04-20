INSERT INTO `fm_user_roles` (id, role_name) VALUES (1, ''ADMIN''), (2, ''USER'');

INSERT INTO `fm_users` (id, user_name, password)
VALUES
  (1, ''admin'', ''admin''), (2, ''haho'', ''hoanhhao''), (3, ''hao'', ''hiep''), (4, ''hiep'', ''hiep''),
  (6, ''admin1'', ''admin''), (7, ''admin2'', ''admin''), (8, ''admin3'', ''admin''),
  (9, ''admin4'', ''admin''), (12, ''haho1'', ''hoanhhao''), (13, ''haho13'', ''hoanhhao''),
  (14, ''haho14'', ''hoanhhao''), (15, ''haho15'', ''hoanhhao''), (16, ''haho16'', ''hoanhhao''),
  (17, ''haho17'', ''hoanhhao''), (18, ''haho18'', ''hoanhhao''), (19, ''haho19'', ''hoanhhao''),
  (20, ''haho20'', ''hoanhhao''), (21, ''haho21'', ''hoanhhao''), (22, ''haho22'', ''hoanhhao''),
  (23, ''haho23'', ''hoanhhao''), (24, ''haho24'', ''hoanhhao''), (25, ''haho25'', ''hoanhhao''),
  (26, ''haho26'', ''hoanhhao''), (27, ''haho27'', ''hoanhhao''), (28, ''haho28'', ''hoanhhao''),
  (29, ''haho29'', ''hoanhhao''), (30, ''haho30'', ''hoanhhao''), (31, ''haho31'', ''hoanhhao''),
  (32, ''haho32'', ''hoanhhao''), (33, ''haho33'', ''hoanhhao''), (34, ''haho34'', ''hoanhhao''),
  (36, ''haho36'', ''hoanhhao''), (37, ''haho37'', ''hoanhhao''), (38, ''haho38'', ''hoanhhao''),
  (39, ''customer'', ''customer''), (40, ''staff'', ''staff''),
  (41, ''haho41'', ''hoanhhao''), (42, ''haho42'', ''hoanhhao''), (43, ''haho43'', ''hoanhhao''),
  (44, ''haho44'', ''hoanhhao''), (45, ''haho45'', ''hoanhhao''), (46, ''haho46'', ''hoanhhao''),
  (47, ''haho47'', ''hoanhhao''), (48, ''haho48'', ''hoanhhao''), (5, ''haho5'', ''hoanhhao''),
  (10, ''haho10'', ''hoanhhao''), (11, ''haho11'', ''hoanhhao''), (49, ''haho49'', ''hoanhhao''),
  (50, ''haho50'', ''hoanhhao''), (51, ''haho52'', ''hoanhhao''), (53, ''haho53'', ''hoanhhao''),
  (54, ''haho55'', ''hoanhhao''), (56, ''haho56'', ''hoanhhao''), (57, ''haho57'', ''hoanhhao''),
  (58, ''haho58'', ''hoanhhao''), (59, ''haho59'', ''hoanhhao''), (60, ''haho60'', ''hoanhhao''),
  (61, ''haho61'', ''hoanhhao''), (62, ''haho62'', ''hoanhhao''), (63, ''haho63'', ''hoanhhao'')
;

INSERT INTO `fm_user_role_details` (id, user_id, role_id) VALUES
  (1, 1, 1), (2, 2, 2)
;

INSERT INTO fm_payment_methods(`id`, `name`) VALUES (1, ''VISA CREDIT''), (2, ''VISA DEBIT''), (3, ''American Express'');

INSERT INTO fm_banks(`id`, `name`, `address`, `website`) VALUES (1, ''HSBC'', ''123 Dong Khoi'', ''hsbc.com.vn'');
INSERT INTO fm_banks(`id`, `name`, `address`, `website`) VALUES (2, ''VIB'', ''123 Dong Khoi'', ''hsbc.com.vn'');
INSERT INTO fm_banks(`id`, `name`, `address`, `website`) VALUES (3, ''SCB'', ''123 Dong Khoi'', ''hsbc.com.vn'');
INSERT INTO fm_banks(`id`, `name`, `address`, `website`) VALUES (4, ''Citi Bank'', ''123 Dong Khoi'', ''hsbc.com.vn'');

INSERT INTO fm_individuals (`id`, `first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `income`, `user_id`)
VALUES (1, ''Hao'', ''Ho'', ''Anh'', ''1988-04-19'', ''Male'', ''hoanhhao@gmail.com'', ''0906729775'', ''1000000000'', 2);

INSERT INTO fm_money_source (`id`, `name`, `start_date`, `expiry_date`, `amount`, `card_number`, `card_type_id`, `user_id`, `bank_id`)
VALUES (1, ''HSBC 2017'', ''2017-03-08'', ''2021-03-07'', 57500000, ''**49 5256'', 2, 2, 1);
INSERT INTO fm_money_source (`id`, `name`, `start_date`, `expiry_date`, `amount`, `card_number`, `card_type_id`, `user_id`, `bank_id`)
VALUES (2, ''VIB'', ''2017-03-08'', ''2021-03-07'', 57500000, ''**49 5256'', 2, 2, 2);
INSERT INTO fm_money_source (`id`, `name`, `start_date`, `expiry_date`, `amount`, `card_number`, `card_type_id`, `user_id`, `bank_id`)
VALUES (3, ''Citi Bank'', ''2017-03-08'', ''2021-03-07'', 57500000, ''**49 5256'', 2, 2, 3);

INSERT INTO fm_money_flow (`user_id`, `amount`, `name`, `date`, `is_deleted`, `money_source_id`)
VALUES
  (2, 37600, ''Grab'', ''2017-05-01'', FALSE, NULL),
  (2, 56000, ''Vinasun'', ''2017-05-02'', FALSE, NULL),
  (2, 78000, ''New Sun Hotel'', ''2017-05-03'', FALSE, NULL),
  (2, 90000, ''Bun Bo Ganh'', ''2017-05-04'', FALSE, NULL),
  (2, 120000, ''That s cafe'', ''2017-05-05'', FALSE, NULL),
  (2, 14000, ''Citibank annual fee'', ''2017-05-06'', FALSE, 1),
  (2, 45000, ''Maximax'', ''2017-05-07'', FALSE, 1),
  (2, 5000, ''Highlands Coffee'', ''2017-05-08'', FALSE, 1),
  (2, 70000, ''Year-end party'', ''2017-05-09'', FALSE, 1),
  (2, 990000, ''Hang Duong Quan'', ''2017-05-12'', FALSE, 1),
  (2, 15000, ''The Coffee House'', ''2017-06-01'', FALSE, NULL),
  (2, 45000, ''Gongcha'', ''2017-06-02'', FALSE, NULL),
  (2, 55000, ''KOI'', ''2017-06-03'', FALSE, NULL),
  (2, 50000, ''Watcha'', ''2017-06-04'', FALSE, NULL),
  (2, 20000, ''Nha Khoa Minh Khai'', ''2017-06-05'', FALSE, NULL),
  (2, 15000, ''CGV'', ''2017-06-06'', FALSE, 1),
  (2, 45000, ''AEON Mall'', ''2017-06-07'', FALSE, 1),
  (2, 55000, ''Tokio Deli'', ''2017-06-08'', FALSE, 1),
  (2, 50000, ''Traveloka'', ''2017-06-09'', FALSE, 1),
  (2, 20000, ''Uber'', ''2017-06-12'', FALSE, 1)
;

INSERT INTO fm_promotion_categories (name) VALUES ('Travel');
INSERT INTO fm_promotion_categories (name) VALUES ('Food');
INSERT INTO fm_promotion_categories (name) VALUES ('Shopping');
INSERT INTO fm_promotion_categories (name) VALUES ('Health');
INSERT INTO fm_promotion_categories (name) VALUES ('Education');
INSERT INTO fm_promotion_categories (name) VALUES('Electronics');
INSERT INTO fm_promotion_categories (name) VALUES ('Khác');
