DROP DATABASE IF EXISTS `finance_management`;
CREATE DATABASE IF NOT EXISTS `finance_management`;
USE `finance_management`;

--
-- Table structure for table `auth_token`
--
DROP TABLE IF EXISTS `fm_auth_token`;
CREATE TABLE `fm_auth_token`
(
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
-- Table structure for table `user_role`
--
DROP TABLE IF EXISTS `fm_user_roles`;
CREATE TABLE `fm_user_roles`
(
    `id`        BIGINT      NOT NULL,
    `role_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_user_role_id_unique` (`id`),
    UNIQUE KEY `fm_user_role_role_name_unique` (`role_name`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

--
-- Table structure for table `user_table`
--
DROP TABLE IF EXISTS `fm_users`;
CREATE TABLE `fm_users`
(
    `id`        BIGINT      NOT NULL,
    `user_name` VARCHAR(45) NOT NULL,
    `password`  VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_user_table_id_unique` (`id`),
    UNIQUE KEY `fm_user_table_user_name_unique` (`user_name`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

--
-- Table structure for table `user_role_details`
--
DROP TABLE IF EXISTS `fm_user_role_details`;
CREATE TABLE `fm_user_role_details`
(
    `id`      BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_user_role_unique` (`user_id`, `role_id`),
    UNIQUE KEY `fm_user_role_user_id_unique` (`user_id`), #  A user has just ONLY 1 role.
    KEY `role_id` (`role_id`),
    CONSTRAINT `fm_user_role_details_role_id` FOREIGN KEY (`role_id`) REFERENCES `fm_user_roles` (`id`),
    CONSTRAINT `fm_user_role_details_user_id` FOREIGN KEY (`user_id`) REFERENCES `fm_users` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 11
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `fm_payment_methods`;
CREATE TABLE `fm_payment_methods`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL, # cash, credit card, debit card, master card, jbc, amex, visa credit
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_event_types_id_unique` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `fm_money_source`;
CREATE TABLE `fm_money_source`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(45) NULL, # HSBC, ANZ 123
    `start_date`    DATE        NULL,
    `expiry_date`   DATE        NULL,
    `card_number`   VARCHAR(45) NULL, # last 6 digits
    `amount`        DOUBLE      NOT NULL,
    `card_type_id`  BIGINT      NULL,
    `user_id`       BIGINT      NULL,
    `is_terminated` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_cards_information_id_unique` (`id`, `card_number`, `user_id`),
    CONSTRAINT `fm_cards_information_card_type_id` FOREIGN KEY (`card_type_id`) REFERENCES `fm_payment_methods` (`id`),
    CONSTRAINT `fm_cards_information_card_user_id` FOREIGN KEY (`user_id`) REFERENCES `fm_users` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `fm_money_flow`;
CREATE TABLE `fm_money_flow`
(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `user_id`         BIGINT      NOT NULL,
    `amount`          DOUBLE      NULL, # if `is_an_event is TRUE, amount can be updated later when event is over
    `date`            DATETIME DEFAULT now(),
    `name`            VARCHAR(45) NOT NULL,
    `money_source_id` BIGINT      NULL, # if `is_an_event is TRUE, card_id is NULL, or card_id is NULL means CASH payment
    `is_deleted`      BOOLEAN  DEFAULT FALSE,
    `is_spending`     BOOLEAN  DEFAULT FALSE,
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_expenses_id_unique` (`id`),
    CONSTRAINT `fm_expenses_user_id` FOREIGN KEY (`user_id`) REFERENCES `fm_users` (`id`),
    CONSTRAINT `fm_expenses_money_source_id` FOREIGN KEY (`money_source_id`) REFERENCES `fm_money_source` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
