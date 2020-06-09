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
    `name`    VARCHAR(50)  NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `website` VARCHAR(45),
    `logo`    TEXT         NULL, -- Logos of Banks
    `created` DATETIME DEFAULT now(),
    `updated` DATETIME DEFAULT now(),
    INDEX (name),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `fm_bank_interest`
--   storing bank interest crawled from any reliable websites.
--
DROP
    TABLE IF EXISTS `fm_bank_interest`;
CREATE TABLE `fm_bank_interest`
(
    `id`               BIGINT AUTO_INCREMENT,
    `start_month`      INTEGER       NOT NULL,
    `end_month`        INTEGER       NOT NULL,
    `start_amount`     INTEGER       NULL,
    `end_amount`       INTEGER       NULL,
    `current_interest` DECIMAL(3, 2) NOT NULL,
    `interest`         DECIMAL(3, 2) NOT NULL,
    `url`              VARCHAR(100)  NOT NULL,
    `bank_id`          INTEGER       NOT NULL,
    `interest_type`    VARCHAR(50)   NULL, -- SAVING, LOAN, .... https://thebank.vn/blog/17518-lai-suat-la-gi-7-loai-lai-suat-pho-bien-tren-thi-truong-hien-nay.html
    `loan_type`        VARCHAR(50)   NULL, -- If it is Loan Interest, it's mandatory to put type for it.
    -- Example: Mortgage loan, housing-construction loan, study-oversea loan, etc...
    -- by default it's should be OTHERS or NULL, since NULL is allowed, so we considered NULL "OTHERS" in JAVA.
    `created`          DATETIME DEFAULT now(),
    `updated`          DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_bank_interest_unique` (`start_month`, `end_month`, `start_amount`, `end_amount`, `interest`, `url`),
    CONSTRAINT `fm_bank_interest_bank_id` FOREIGN KEY (`bank_id`) REFERENCES `fm_banks` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `fm_bank_interest_change_request`
-- this table keeps data of change requests from any user. User wants to change bank interest, since he finds it not correct
--   in the bank interests, so he would like to make change by requesting with new info of bank interests. then Admin will take a look and decide if it is approved or not.
--  200525: as discussed, we decide to keep official bank interest on the page, and let  "hint" button to display bank requests approved by Admin.
--
DROP
    TABLE IF EXISTS `fm_bank_interest_change_requests`;
CREATE TABLE `fm_bank_interest_change_requests`
(
    `id`           INTEGER AUTO_INCREMENT,
    `name`         VARCHAR(50)  NOT NULL,
    `start_month`  INTEGER      NOT NULL,
    `end_month`    INTEGER      NOT NULL,
    `start_amount` INTEGER      NOT NULL,
    `end_amount`   INTEGER      NOT NULL,
    `bank_id`      INTEGER      NOT NULL,
    `phone_number` VARCHAR(15)  NULL,
    `email`        VARCHAR(100) NULL,
    `url`          VARCHAR(200) NULL,
    `description`  VARCHAR(200) NOT NULL, -- keep reference like phone number, emails, officers contacts to let Admin verifies such information
    `status`       VARCHAR(10)  NOT NULL, -- SUBMITTED, PENDING, APPROVED, REFUSED.
    `created`      DATETIME DEFAULT now(),
    `updated`      DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    CONSTRAINT `fm_bank_interest_change_requests_bank_id` FOREIGN KEY (`bank_id`) REFERENCES `fm_banks` (`id`)
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
    `logo`    TEXT        NULL, # TODO: it should be binary type to store an image.
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
    `id`                BIGINT      NOT NULL AUTO_INCREMENT,
    `name`              VARCHAR(50) NULL, # HSBC, ANZ 123 or any name you like for such source.
    `start_date`        DATE        NULL,
    `expiry_date`       DATE        NULL,
    `card_number`       VARCHAR(50) NULL, # last 6 digits
    `amount`            DOUBLE      NOT NULL,
    `payment_method_id` INTEGER     NULL,
    `user_id`           INTEGER     NULL,
    `is_terminated`     BOOLEAN  DEFAULT FALSE,
    `bank_id`           INTEGER     NOT NULL,
    `created`           DATETIME DEFAULT now(),
    `updated`           DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_money_source_id_unique` (`card_number`),
    CONSTRAINT `fm_money_source_type_id` FOREIGN KEY (`payment_method_id`) REFERENCES `fm_payment_methods` (`id`),
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

--
-- Table structure for table `Notifications`
--   this keeps list of notifications of user wants to subscribe.
--   PENDING ..... I'M THINKING..... JUST WORK WITH SUBSCRIPTIONS first.
DROP
    TABLE IF EXISTS `fm_notifications`;
CREATE TABLE `fm_notifications`
(
    `id`      INTEGER AUTO_INCREMENT,
    `user_id` INTEGER NOT NULL,
    `created` DATETIME DEFAULT now(),
    `updated` DATETIME DEFAULT now(),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `Subscriptions`
--   this keeps list of subscriptions of user wants to subscribe.
--    In FE side, user or anonymous user can be able to see list of subscriptions on the page and choose which one they want to subscribe
DROP
    TABLE IF EXISTS `fm_subscriptions`;
CREATE TABLE `fm_subscriptions`
(
    `id`                BIGINT AUTO_INCREMENT,
    `user_id`           INTEGER      NULL,     -- If user_id is not null, this mean user is existing in our app, otherwise, user is outside wants to subscribe our info.
    `email`             VARCHAR(100) NOT NULL,
    `type`              VARCHAR(100) NOT NULL, -- BANK INTEREST, BANK PROMOTIONS, etc...
    `status`            VARCHAR(10)  NOT NULL, -- SUBMITTED, VERIFIED, STOPPED.
    `verification_code` VARCHAR(100) NOT NULL,
    `created`           DATETIME DEFAULT now(),
    `updated`           DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    INDEX (email),
    INDEX (type, status),
    UNIQUE KEY `fm_money_source_id_unique` (`email`, `type`, `verification_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `fm_sites_prices`
--
--
DROP
    TABLE IF EXISTS `fm_sites_prices`;
CREATE TABLE `fm_sites_prices`
(
    `id`             BIGINT AUTO_INCREMENT,
    `email`          VARCHAR(100)   NOT NULL,
    `url`            VARCHAR(500)   NOT NULL,
    `price`          DECIMAL(20, 5) NOT NULL,
    `expected_price` DECIMAL(20, 5) NULL,     -- If null, crawled price is lower than price, notify to the user. If not null, notify only when crawled price is lower than expected price
    `status`         VARCHAR(10)    NOT NULL, -- RUNNING, STOPPED.
    `created`        DATETIME DEFAULT now(),
    `updated`        DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    INDEX (email),
    INDEX (url),
    UNIQUE KEY `fm_sites_prices_email_url` (`email`, `url`, `price`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- Table structure for table `Email history`
--   this keeps emails sent to users from our system.
--
DROP
    TABLE IF EXISTS `fm_email_history`;
CREATE TABLE `fm_email_history`
(
    `id`      BIGINT AUTO_INCREMENT,
    `from`    VARCHAR(100) NOT NULL,
    `to`      VARCHAR(100) NOT NULL,
    `title`   VARCHAR(100) NOT NULL,
    `content` VARCHAR(100) NOT NULL,
    `status`  VARCHAR(10)  NOT NULL, -- SENT, ERROR
    `created` DATETIME DEFAULT now(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `fm_email_history_unique` (`from`, `to`, `title`, `content`, `status`, `created`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
