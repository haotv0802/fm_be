DROP DATABASE IF EXISTS `ht_db`;
CREATE DATABASE IF NOT EXISTS `ht_db`;
USE `ht_db`;

--
-- Table structure for table `user_role`
--
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id`        BIGINT      NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_id_unique` (`id`),
  UNIQUE KEY `user_role_role_name_unique` (`role_name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `user_role` VALUES (1, 'ADMIN'), (2, 'STAFF'), (3, 'CUSTOMER'), (4, 'USER');

--
-- Table structure for table `user_table`
--
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `id`        BIGINT      NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `password`  VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_table_id_unique` (`id`),
  UNIQUE KEY `user_table_user_name_unique` (`user_name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `user_table`
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

--
-- Table structure for table `user_role_details`
--
DROP TABLE IF EXISTS `user_role_details`;
CREATE TABLE `user_role_details` (
  `id`      BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_unique` (`user_id`, `role_id`),
  UNIQUE KEY `user_role_user_id_unique` (`user_id`), #  A user has just ONLY 1 role.
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_details_role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `user_role_details_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8;

# INSERT INTO `user_role_details` VALUES
#   (1, 1, 1), (10, 2, 1), (2, 2, 2), (3, 2, 3), (4, 2, 4), (9, 3, 1), (6, 4, 1),
#   (7, 39, 3), (8, 40, 2)
# ;

INSERT INTO `user_role_details` VALUES
  (1, 1, 1), (2, 2, 1), (3, 3, 1), (4, 4, 1),
  (5, 39, 3), (6, 40, 2), (7, 5, 2), (8, 6, 2)
#   (9, 7, 3), (10, 8, 2), (11, 9, 2), (12, 10, 2),
#   (13, 11, 3), (14, 12, 2), (15, 13, 2), (16, 14, 2),
#   (17, 15, 3), (18, 41, 2), (19, 16, 2), (20, 17, 2),
#   (21, 18, 3), (22, 19, 2), (23, 42, 2), (24, 20, 2),
#   (25, 21, 3), (26, 22, 2), (27, 23, 2), (28, 24, 2),
#   (29, 25, 3), (30, 26, 2), (31, 27, 2), (32, 28, 2),
#   (33, 29, 3), (34, 30, 2), (35, 31, 2), (36, 32, 2),
#   (37, 33, 3), (38, 34, 2), (39, 35, 2), (40, 36, 2),
#   (41, 37, 3), (42, 38, 2), (43, 39, 2), (44, 40, 2)
;

--
-- Table structure for table `messages`
--
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id`             BIGINT AUTO_INCREMENT,
  `role_id`        BIGINT       NULL, # NULL is for anyone
  `component_name` VARCHAR(45)  NOT NULL,
  `message_key`    VARCHAR(45)  NOT NULL,
  `message_en`     VARCHAR(100) NOT NULL,
  `message_fr`     VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `messages_id_unique` (`id`),
  UNIQUE KEY `messages_role_id_component_name_key_unique` (`role_id`, `component_name`, `message_key`),
  CONSTRAINT `messages_role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `auth_token`
--
DROP TABLE IF EXISTS `auth_token`;
CREATE TABLE `auth_token` (
  `id`          BIGINT AUTO_INCREMENT,
  `token_type`  VARCHAR(45) NOT NULL,
  `auth_object` BLOB        NOT NULL,
  `exp_date`    DATETIME    NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_token_id_unique` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `image`
--
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id`          BIGINT                AUTO_INCREMENT,
  `name`        VARCHAR(45)  NOT NULL,
  #   Promotion and Individual tables refer to this table. Name which is input helps Admin searches faster.
  `image_url`   VARCHAR(200) NOT NULL,
  `image_info`  VARCHAR(100) NOT NULL,
  `description` VARCHAR(100),
  `created_on`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on`  DATETIME     NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `image_id_unique` (`id`),
  UNIQUE KEY `image_name_unique` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 1', './app/assets/individuals&promotions/DSC_3997.JPG', 'info 1', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 2', './app/assets/individuals&promotions/DSC_4014.JPG', 'info 2', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 3', './app/assets/individuals&promotions/DSC_4038.JPG', 'info 3', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 4', './app/assets/individuals&promotions/DSC_4055.JPG', 'info 4', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 5', './app/assets/individuals&promotions/DSC_4108.JPG', 'info 5', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 6', './app/assets/individuals&promotions/DSC_4196.JPG', 'info 6', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 7', './app/assets/individuals&promotions/DSC_4252.JPG', 'info 7', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 8', './app/assets/individuals&promotions/DSC_4330.JPG', 'info 8', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 9', './app/assets/individuals&promotions/DSC_4336.JPG', 'info 9', 'description', sysdate());
INSERT INTO `image` (`name`, `image_url`, `image_info`, `description`, `updated_on`)
VALUES ('image 10', './app/assets/individuals&promotions/DSC_4377.JPG', 'info 10', 'description', sysdate());

--
-- Table structure for table `error_tracking`
--
DROP TABLE IF EXISTS `error_tracking`;
CREATE TABLE `error_tracking` (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT,
  `error_message` VARCHAR(100) NOT NULL,
  `stack_trace`   TEXT         NOT NULL,
  `user`          VARCHAR(50),
  `error_date`    DATETIME     NOT NULL DEFAULT now(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `error_tracking_id_unique` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# INSERT INTO `room_type` VALUES (1, 'Balcony room', 4, 2, "Double Bed & Single Bed");
# INSERT INTO `room_type` VALUES (2, 'Near-elevator room', 2, 1, "Double Bed");
# INSERT INTO `room_type` VALUES (3, 'Room 3', 2, 1, "Single Bed");
# INSERT INTO `room_type` VALUES (4, 'Family or big room', 4, 2, "2 Double Beds");

--
-- Table structure for table `room_type`
--
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type` (
  `id`            BIGINT      NOT NULL,
  `name`          VARCHAR(45) NOT NULL,
  `num_of_people` TINYINT     NOT NULL,
  `num_of_bed`    TINYINT     NOT NULL,
  `type_of_bed`   VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_type_id_unique` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `room_type` VALUES (1, 'Balcony room', 4, 2, "Double Bed & Single Bed");
INSERT INTO `room_type` VALUES (2, 'Near-elevator room', 2, 1, "Double Bed");
INSERT INTO `room_type` VALUES (3, 'Room 3', 2, 1, "Single Bed");
INSERT INTO `room_type` VALUES (4, 'Family or big room', 4, 2, "2 Double Beds");

--
-- Table structure for table `image`
--
DROP TABLE IF EXISTS `room_type_image`;
CREATE TABLE `room_type_image` (
  `id`           BIGINT   AUTO_INCREMENT,
  `room_type_id` BIGINT(45)   NOT NULL,
  `image_url`    VARCHAR(200) NOT NULL,
  `image_info`   VARCHAR(100) NULL,
  `description`  VARCHAR(100),
  `created_on`   DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_type_image_id_unique` (`id`),
  CONSTRAINT `room_type_image_image_id` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (1, './app/assets/Room1_01.jpg', 'Type 1, image 1');
INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (1, './app/assets/Room1_02.jpg', 'Type 1, image 2');

INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (2, './app/assets/Room2_01.jpg', 'Type 2, image 1');
INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (2, './app/assets/Room2_02.jpg', 'Type 2, image 2');

INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (3, './app/assets/Room3_01.jpg', 'Type 3, image 1');
INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (3, './app/assets/Room3_02.jpg', 'Type 3, image 2');
INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (3, './app/assets/Room3_03.jpg', 'Type 3, image 3');
INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (3, './app/assets/Room3_04.jpg', 'Type 3, image 4');

INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (4, './app/assets/Room3_03.jpg', 'Type 4, image 1');
INSERT INTO `room_type_image` (`room_type_id`, `image_url`, `image_info`)
VALUES (4, './app/assets/Room3_04.jpg', 'Type 4, image 2');

--
-- Table structure for table `room`
--
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id`               BIGINT  AUTO_INCREMENT,
  `name`             VARCHAR(45) NOT NULL,
  `floor_number`     TINYINT     NOT NULL,
  `number_of_people` TINYINT     NOT NULL,
  #   `is_occupied` BOOLEAN,
  #   `image_id`         BIGINT      NOT NULL,
  `room_type_id`     BIGINT      NOT NULL,
  `isOccupied`       BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_id_unique` (`id`),
  #   CONSTRAINT `room_image_id` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `room_room_type_id` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 101', 1, 4, 1);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 102', 1, 2, 2);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 103', 1, 2, 3);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 201', 2, 4, 1);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 202', 2, 2, 2);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 203', 2, 2, 3);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 301', 3, 4, 1);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 302', 3, 2, 2);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 303', 3, 2, 3);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 401', 4, 4, 1);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 402', 4, 2, 2);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 403', 4, 2, 3);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 501', 5, 4, 1);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 502', 5, 2, 2);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 503', 5, 2, 3);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 601', 6, 4, 4);
INSERT INTO `room` (`name`, `floor_number`, `number_of_people`, `room_type_id`)
VALUES ('Room 602', 6, 2, 4);

--
-- Table structure for table `room_price`
--    set price for each room_type.
--    price of weekdays can be different from weekend's
--
DROP TABLE IF EXISTS `room_price`;
CREATE TABLE `room_price` (
  `id`           BIGINT   AUTO_INCREMENT,
  `name`         VARCHAR(45) NOT NULL,
  `price`        DOUBLE      NOT NULL,
  `room_type_id` BIGINT      NOT NULL,
  `day`          TINYINT     NOT NULL,
  `created_on`   DATETIME DEFAULT now(),
  #   `end_date`     DATETIME    NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_price_id_unique` (`id`),
  UNIQUE KEY `room_price_room_type_id_day_unique` (`room_type_id`, `day`),
  CONSTRAINT `room_price_room_type_id` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 1', 300000, 1, 2);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 1', 300000, 1, 3);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 1', 300000, 1, 4);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 1', 300000, 1, 5);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 1', 500000, 1, 6);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 1', 500000, 1, 7);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 1', 500000, 1, 8);

INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 2', 200000, 2, 2);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 2', 200000, 2, 3);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 2', 200000, 2, 4);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 2', 200000, 2, 5);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 2', 400000, 2, 6);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 2', 400000, 2, 7);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 2', 400000, 2, 8);

INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 3', 200000, 3, 2);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 3', 200000, 3, 3);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 3', 200000, 3, 4);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 3', 200000, 3, 5);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 3', 400000, 3, 6);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 3', 400000, 3, 7);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 3', 400000, 3, 8);

INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 4', 300000, 4, 2);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 4', 300000, 4, 3);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 4', 300000, 4, 4);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 4', 300000, 4, 5);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 4', 600000, 4, 6);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 4', 600000, 4, 7);
INSERT INTO `room_price` (`name`, `price`, `room_type_id`, `day`)
VALUES ('Price set for room type 4', 600000, 4, 8);

--
-- Table structure for table `price_set`
--
DROP TABLE IF EXISTS `price_set`;
CREATE TABLE `price_set` (
  `id`           BIGINT  AUTO_INCREMENT,
  `name`         VARCHAR(45) NOT NULL,
  `price`        DOUBLE      NOT NULL,
  `room_type_id` BIGINT      NOT NULL,
  `is_active`    BOOLEAN DEFAULT FALSE,
  `day`          TINYINT     NOT NULL, # 1: Sunday, 2: Monday, 3: Tuesday, and so on
  `start_date`   DATE        NOT NULL,
  `end_date`     DATE        NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `price_set_id_unique` (`id`),
  CONSTRAINT `price_set_room_type_id` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


INSERT INTO `price_set` (`name`, `price`, `room_type_id`, `is_active`, `day`, `start_date`, `end_date`)
VALUES ('Price set for room type 1', 900000, 1, TRUE, 8 ,curdate(), curdate() + INTERVAL 2 DAY);
INSERT INTO `price_set` (`name`, `price`, `room_type_id`, `is_active`, `day`, `start_date`, `end_date`)
VALUES ('Price set for room type 1', 500000, 1, TRUE, 2 ,curdate(), curdate() + INTERVAL 2 DAY);
INSERT INTO `price_set` (`name`, `price`, `room_type_id`, `is_active`, `day`, `start_date`, `end_date`)
VALUES ('Price set for room type 1', 500000, 1, TRUE, 3 ,curdate(), curdate() + INTERVAL 2 DAY);
INSERT INTO `price_set` (`name`, `price`, `room_type_id`, `is_active`, `day`, `start_date`, `end_date`)
VALUES ('Price set for room type 1', 500000, 1, TRUE, 4 ,curdate(), curdate() + INTERVAL 2 DAY);
INSERT INTO `price_set` (`name`, `price`, `room_type_id`, `is_active`, `day`, `start_date`, `end_date`)
VALUES ('Price set for room type 1', 500000, 1, TRUE, 5 ,curdate(), curdate() + INTERVAL 2 DAY);
INSERT INTO `price_set` (`name`, `price`, `room_type_id`, `is_active`, `day`, `start_date`, `end_date`)
VALUES ('Price set for room type 1', 900000, 1, TRUE, 6 ,curdate(), curdate() + INTERVAL 2 DAY);
INSERT INTO `price_set` (`name`, `price`, `room_type_id`, `is_active`, `day`, `start_date`, `end_date`)
VALUES ('Price set for room type 1', 900000, 1, TRUE, 7 ,curdate(), curdate() + INTERVAL 2 DAY);

--
-- Table structure for table `individual`
--
DROP TABLE IF EXISTS `individual`;
CREATE TABLE `individual` (
  `id`           BIGINT AUTO_INCREMENT,
  `first_name`   VARCHAR(45) NOT NULL,
  `last_name`    VARCHAR(45) NOT NULL,
  `middle_name`  VARCHAR(45),
  `birthday`     DATE,
  `gender`       VARCHAR(10) NOT NULL,
  `email`        VARCHAR(50) NOT NULL,
  `phone_number` VARCHAR(50),
  `image_id`     BIGINT,
  `user_id`      BIGINT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `individual_id_unique` (`id`),
  UNIQUE KEY `individual_user_id_unique` (`user_id`), #  An individual has ONLY 1 user account.
  UNIQUE KEY `individual_email_unique` (`email`), #  One email is belong to only 1 individual.
  CONSTRAINT `individual_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`),
  CONSTRAINT `individual_image_id` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Administrator', 'Adminitrator', 'Adminitrator', '1988-04-19', 'Male', 'admin@huongtrang.com', '+84909909090', 1, 1);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao', 'Ho', 'Anh', '1988-04-19', 'Male', 'haho@huongtrang.com', '+84906729775', 1, 2);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hiep', 'Ho', 'Hoang', '1993-02-19', 'Male', 'hiep@huongtrang.com', '+84909909099', 2, 3);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Tan', 'Minh', 'Nguyen', '1994-04-19', 'Male', 'tan@huongtrang.com', '+84909909090', 2, 4);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hung', 'Ho', 'Thanh', '1959-04-19', 'Male', 'hung@huongtrang.com', '+84903810551', 2, 6);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Gai', 'Ngo', 'Thi', '1961-04-19', 'Female', 'gaingo@huongtrang.com', '+84916516697', 2, 7);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Quyen', 'Pham', 'Ngo', '1991-04-19', 'Male', 'quyen@huongtrang.com', '+84903225588', 2, 8);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Giao', 'Tran', 'My', '1989-04-19', 'Female', 'giao@huongtrang.com', '+84909456789', 3, 9);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Bao', 'Nguyen', 'Gia', '2016-04-19', 'Male', 'baonguyen@huongtrang.com', '+84987456312', 3, 39);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Giang', 'Pham', 'Minh', '1962-04-19', 'Male', 'giangpham@huongtrang.com', '+84907896540', 3, 40);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Chi', 'Ngo', 'Mai', '1969-04-19', 'Male', 'chingo@huongtrang.com', '+84909789156', 4, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Quy', 'Pham', 'Chanh', '1996-04-19', 'Male', 'quy@huongtrang.com', '+84909909090', 4, NULL);

INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 1', 'Adminitrator', 'Adminitrator', '1988-04-19', 'Male', 'hao1@huongtrang.com', '+84909909090', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 2', 'Ho', 'Anh', '1988-04-19', 'Male', 'hao2@huongtrang.com', '+84906729775', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 3', 'Ho', 'Hoang', '1993-02-19', 'Male', 'hao3@huongtrang.com', '+84909909099', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 4', 'Minh', 'Nguyen', '1994-04-19', 'Male', 'hao4@huongtrang.com', '+84909909090', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 5', 'Ho', 'Thanh', '1959-04-19', 'Male', 'hao5@huongtrang.com', '+84903810551', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 6', 'Ngo', 'Thi', '1961-04-19', 'Female', 'hao6@huongtrang.com', '+84916516697', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 7', 'Pham', 'Ngo', '1991-04-19', 'Male', 'hao7@huongtrang.com', '+84903225588', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 8', 'Tran', 'My', '1989-04-19', 'Female', 'hao8@huongtrang.com', '+84909456789', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 9', 'Nguyen', 'Gia', '2016-04-19', 'Male', 'hao9@huongtrang.com', '+84987456312', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hao 99', 'Pham', 'Minh', '1962-04-19', 'Male', 'hao99@huongtrang.com', '+84907896540', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hiep 1', 'Ngo', 'Mai', '1969-04-19', 'Male', 'hiep1@huongtrang.com', '+84909789156', NULL, NULL);
INSERT INTO `individual` (`first_name`, `last_name`, `middle_name`, `birthday`, `gender`, `email`, `phone_number`, `image_id`, `user_id`)
VALUES ('Hiep 2', 'Pham', 'Chanh', '1996-04-19', 'Male', 'hiep2@huongtrang.com', '+84909909090', NULL, NULL);

--
-- Table structure for table `expense_type`
--
DROP TABLE IF EXISTS `expense_type`;
CREATE TABLE `expense_type` (
  `id`   BIGINT AUTO_INCREMENT,
  `name` BIGINT NOT NULL,
  `cost` DOUBLE,
  PRIMARY KEY (`id`),
  UNIQUE KEY `expense_type_id_unique` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `payment_history`
--   data of this table and expense_history will be added when customer successfully book a room.
--
DROP TABLE IF EXISTS `payment_history`;
CREATE TABLE `payment_history` (
  `id`                       BIGINT            AUTO_INCREMENT,
  `user_id`                  BIGINT   NOT NULL,
  `date`                     DATETIME,
  `status`                   VARCHAR(45), #Staying, Not arrived yet, Finished
  `booking_method`           VARCHAR(45), #Phone contact with receptionist, website, booking info, etc
  `staff_id`                 BIGINT   NULL,
  #user_id is stored in case receptionist supports and books for customer. Staff can earn commission.
  #user_id can be any one who register their information in the system, such as, Security guards, taxi drivers, etc.
  #   commission will be sent to such person at the end of the month, 2 months, quarter, and so on.
  `payment_in_advanced`      DOUBLE, #by default, NULL. Customer paying in advanced will be served better.
  `payment_in_advanced_date` DATETIME NOT NULL DEFAULT now(),
  `payment`                  DOUBLE,
  `payment_date`             DATETIME,
  PRIMARY KEY (`id`),
  UNIQUE KEY `booking_history_id_unique` (`id`),
  CONSTRAINT `booking_history_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`)
#   CONSTRAINT `booking_history_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `user_table` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `expense_history`
--
DROP TABLE IF EXISTS `expense_history`;
CREATE TABLE `expense_history` (
  `id`                 BIGINT AUTO_INCREMENT,
  `room_id`            BIGINT NOT NULL,
  #   `other_expense_id` BIGINT NOT NULL,
  `cost`               DOUBLE,
  `arrival`            DATETIME,
  `leave`              DATETIME,
  `payment_history_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `expense_history_id_unique` (`id`),
  CONSTRAINT `expense_history_room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `expense_history_payment_history_id` FOREIGN KEY (`payment_history_id`) REFERENCES `payment_history` (`id`)
  #   CONSTRAINT `expense_history_other_expense_id` FOREIGN KEY (`other_expense_id`) REFERENCES `other_expense` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `other_expense`
--
DROP TABLE IF EXISTS `other_expense`;
CREATE TABLE `other_expense` (
  `id`                 BIGINT AUTO_INCREMENT,
  `expense_type_id`    BIGINT       NULL, # in case type id is 'other' or NULL, name should be put #this case happens when there's a new expense.
  # case: customer buy something, and have hotel pay for it
  `expense_history_id` BIGINT       NOT NULL,
  `quantity`           TINYINT      NOT NULL,
  `name`               VARCHAR(200) NOT NULL,
  `cost`               DOUBLE, # total cost is calculated by quantity and cost of specific expense.
  `date`               DATETIME,
  PRIMARY KEY (`id`),
  UNIQUE KEY `other_expense_id_unique` (`id`),
  CONSTRAINT `other_expense_expense_type_id` FOREIGN KEY (`expense_type_id`) REFERENCES `expense_type` (`id`),
  CONSTRAINT `other_expense_expense_history_id` FOREIGN KEY (`expense_history_id`) REFERENCES `expense_history` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `promotion`
--    for Customer
--
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `id`       BIGINT AUTO_INCREMENT,
  `title`    VARCHAR(100) NOT NULL,
  `content`  TEXT         NOT NULL,
  `image_id` BIGINT       NOT NULL,
  `user_id`  BIGINT       NOT NULL,
  `percent`  DOUBLE,
  `exp_date` DATETIME,
  PRIMARY KEY (`id`),
  UNIQUE KEY `promotion_id_unique` (`id`),
  CONSTRAINT `promotion_image_id` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
#   CONSTRAINT `promotion_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `promotion_details`
--    for Customer
--
DROP TABLE IF EXISTS `promotion_details`;
CREATE TABLE `promotion_details` (
  `id`           BIGINT AUTO_INCREMENT,
  `promotion_id` BIGINT NOT NULL,
  `user_id`      BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `promotion_details_id_unique` (`id`),
  CONSTRAINT `promotion_details_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`),
  CONSTRAINT `promotion_details_promotion_id` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `commission`
--    for Staff (earn commission)
--
DROP TABLE IF EXISTS `commission`;
CREATE TABLE `commission` (
  `id`         BIGINT AUTO_INCREMENT,
  `name`       VARCHAR(100) NOT NULL,
  `type`       VARCHAR(20)  NOT NULL, #Percent or Money
  `user_id`    BIGINT       NOT NULL, #specific staff has been offered commission
  `percent`    DOUBLE,
  `start_date` DATETIME,
  `exp_date`   DATETIME,
  PRIMARY KEY (`id`),
  UNIQUE KEY `commission_id_unique` (`user_id`, `name`),
  CONSTRAINT `commission_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `email_template`
--
# DROP TABLE IF EXISTS `email_template`;
# CREATE TABLE `email_template` (
#   `id`         BIGINT AUTO_INCREMENT,
#   `name`       VARCHAR(100) NOT NULL,
#   `type`       VARCHAR(20)  NOT NULL, #Percent or Money
#   `user_id`    BIGINT       NOT NULL, #specific staff has been offered commission
#   `percent`    DOUBLE,
#   `start_date` DATETIME,
#   `exp_date`   DATETIME,
#   PRIMARY KEY (`id`),
#   UNIQUE KEY `commission_id_unique` (`user_id`, `name`),
#   CONSTRAINT `commission_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`)
# )
#   ENGINE = InnoDB
#   DEFAULT CHARSET = utf8;

-- 14 tables

-- Table: Notification (sending email, let customer keep track of all notifications of promotions, wishes, etc)

-- Tables for managing customer's order and expectation time of cleaning service.

-- Tables for managing products, goods (beer, tissues, foods, etc).

-- Tables for managing customer's searching behaviors.

-- Tables for managing finance, stuff like drinks, tissues, and so on which are spent on Hotel business.
--    For example: box of Lavie's having 24 bottles. Such quantity will be put to specific table including price for 1 bottle.
--        Once, customer pay for a bottle, the quantity will be deduct by 1
--        This helps predict when we should buy new stuff.
--    In addition, there should be a table storing a scan of bill.

-- Tables for add bonus for Staff from customer's support.
--            For example: CommisstionDetails keep tracks what staff have been done