TRUNCATE TABLE messages;
INSERT INTO `messages` (`role_id`, `component_name`, `message_key`, `message_en`, `message_fr`)
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
  (null, 'login', 'language', 'Language', 'Langue'),
  (3, 'roomsList', 'name', 'Name', 'NOM'),
  (3, 'roomsList', 'numOfPeople', 'Num of people', 'Nombre de personnes'),
  (3, 'roomsList', 'numOfBeds', 'Num of beds', 'Nombre de lits'),
  (3, 'roomsList', 'typeOfBed', 'Type of bed', 'Type de lit')