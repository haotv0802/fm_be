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
    `created`     DATETIME DEFAULT now(),
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
    `id`        INTEGER     NOT NULL,
    `role_name` VARCHAR(45) NOT NULL,
    `created`   DATETIME DEFAULT now(),
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
    `id`        INTEGER     NOT NULL,
    `user_name` VARCHAR(50) NOT NULL,
    `password`  VARCHAR(50) NOT NULL,
    `created`   DATETIME DEFAULT now(),
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
    `id`      INTEGER NOT NULL AUTO_INCREMENT,
    `user_id` INTEGER NOT NULL,
    `role_id` INTEGER NOT NULL,
    `created` DATETIME DEFAULT now(),
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


--
-- Table structure for table `Banks`
--
DROP
    TABLE IF EXISTS `fm_banks`;
CREATE TABLE `fm_banks`
(
    `id`      INTEGER AUTO_INCREMENT,
    `name`    VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `website` VARCHAR(45),
    `logo`    VARCHAR(45), -- Logos of Banks
    `created` DATETIME DEFAULT now(),
    INDEX (name),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `individuals`
--
DROP TABLE IF EXISTS `fm_individuals`;
CREATE TABLE `fm_individuals`
(
    `id`           INTEGER AUTO_INCREMENT,
    `first_name`   VARCHAR(30)  NOT NULL,
    `last_name`    VARCHAR(30)  NOT NULL,
    `middle_name`  VARCHAR(30),
    `birthday`     DATE,
    `gender`       VARCHAR(10),
    `email`        VARCHAR(100) NOT NULL,
    `phone_number` VARCHAR(20),
    `income`       DOUBLE,
    `user_id`      INTEGER      NOT NULL,
    `created`      DATETIME DEFAULT now(),
    `updated`      DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_individuals_id_unique` (`id`),
    UNIQUE KEY `fm_individuals_user_id_unique` (`user_id`), #  An individual has ONLY 1 user account.
    UNIQUE KEY `fm_individuals_email_unique` (`email`),     #  One email is belong to only 1 individual.
    CONSTRAINT `fm_individuals_user_id` FOREIGN KEY (`user_id`) REFERENCES `fm_users` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

--
-- Table structure for table `Promotion category`
--
DROP
    TABLE IF EXISTS `fm_promotion_categories`;
CREATE TABLE `fm_promotion_categories`
(
    `id`      INTEGER AUTO_INCREMENT,
    `name`    VARCHAR(30) NOT NULL,
    `created` DATETIME DEFAULT now(),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `Promotion`
--
DROP
    TABLE IF EXISTS `fm_promotions`;
CREATE TABLE `fm_promotions`
(
    `id`          BIGINT AUTO_INCREMENT,
    `title`       NVARCHAR(100)  NOT NULL,
    `content`     NVARCHAR(9000) NOT NULL, # NVARCHAR to store UTF-8 text
    `discount`    VARCHAR(50)    NULL,     # Could be % or specific amount
    `installment` VARCHAR(100)   NULL,     # Could be % or specific amount
    `start_date`  DATE           NOT NULL,
    `end_date`    DATE           NOT NULL,
    `url`         VARCHAR(200)   NOT NULL,
    `category_id` INTEGER        NOT NULL,
    `bank_id`     INTEGER        NOT NULL,
    `created`     DATETIME DEFAULT now(),
    `updated`     DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_promotions_url_unique` (`url`, `title`, `end_date`),
    INDEX (`url`),
    CONSTRAINT `fm_promotions_category_id` FOREIGN KEY (`category_id`) REFERENCES `fm_promotion_categories` (`id`),
    CONSTRAINT `fm_promotions_bank_id` FOREIGN KEY (`bank_id`) REFERENCES `fm_banks` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `fm_payment_methods`;
CREATE TABLE `fm_payment_methods`
(
    `id`      INTEGER     NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(50) NULL, # cash, credit card, debit card, master card, jbc, amex, visa credit
    `logo`    VARCHAR(50) NULL, # TODO: it should be binary type to store an image.
    `created` DATETIME DEFAULT now(),
    `updated` DATETIME DEFAULT now(),
    UNIQUE KEY `fm_payment_methods_name_unique` (`name`),
    INDEX (`name`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `fm_money_source`;
CREATE TABLE `fm_money_source`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(50) NULL, # HSBC, ANZ 123 or any name you like for such source.
    `start_date`    DATE        NULL,
    `expiry_date`   DATE        NULL,
    `card_number`   VARCHAR(50) NULL, # last 6 digits
    `amount`        DOUBLE      NOT NULL,
    `card_type_id`  INTEGER     NULL,
    `user_id`       INTEGER     NULL,
    `is_terminated` BOOLEAN  DEFAULT FALSE,
    `bank_id`       INTEGER     NOT NULL,
    `created`       DATETIME DEFAULT now(),
    `updated`       DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_money_source_id_unique` (`id`, `card_number`, `user_id`),
    CONSTRAINT `fm_money_source_type_id` FOREIGN KEY (`card_type_id`) REFERENCES `fm_payment_methods` (`id`),
    CONSTRAINT `fm_money_source_individual_id` FOREIGN KEY (`user_id`) REFERENCES `fm_users` (`id`),
    CONSTRAINT `fm_money_source_bank_id` FOREIGN KEY (`bank_id`) REFERENCES `fm_banks` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `fm_money_flow`;
CREATE TABLE `fm_money_flow`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT,
    `user_id`         INTEGER      NOT NULL,
    `amount`          DOUBLE       NULL, # if `is_an_event is TRUE, amount can be updated later when event is over
    `date`            DATE         NOT NULL,
    `name`            VARCHAR(45)  NOT NULL,
    `money_source_id` BIGINT       NULL, # if `is_an_event is TRUE, card_id is NULL, or card_id is NULL means CASH payment
    `is_deleted`      BOOLEAN  DEFAULT FALSE,
    `is_spending`     BOOLEAN  DEFAULT FALSE,
    `created`         DATETIME DEFAULT now(),
    `updated`         DATETIME DEFAULT now(),
    `note`            VARCHAR(200) NULL, # Notes for item
    `link`            VARCHAR(200) NULL, # URL for reference
    PRIMARY KEY (`id`),
    INDEX (`name`),
    INDEX (`date`),
    CONSTRAINT `fm_expenses_user_id` FOREIGN KEY (`user_id`) REFERENCES `fm_users` (`id`),
    CONSTRAINT `fm_expenses_money_source_id` FOREIGN KEY (`money_source_id`) REFERENCES `fm_money_source` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

--
-- Table structure for table `error_tracking`
--
DROP TABLE IF EXISTS `fm_error_tracking`;
CREATE TABLE `fm_error_tracking`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `error_message` VARCHAR(100) NOT NULL,
    `stack_trace`   TEXT         NOT NULL,
    `exception`     VARCHAR(100) NOT NULL,
    `user`          VARCHAR(50),
    `error_date`    DATETIME     NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`),
    INDEX (`exception`),
    INDEX (`error_date`),
    UNIQUE KEY `error_tracking_id_unique` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
