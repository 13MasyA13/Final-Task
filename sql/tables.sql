/* Creating Cars Table*/
CREATE TABLE `car_rental`.`cars_ru` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `car_class` VARCHAR(45) NOT NULL,
  `car_name` VARCHAR(45) NOT NULL,
  `car_mark` VARCHAR(45) NOT NULL,
  `car_price_from_30` INT(5) NOT NULL,
  `car_price_from10_to30` INT(5) NOT NULL,
  `car_price_from4_to9` INT(5) NOT NULL,
  `car_price_from2_to3` INT(5) NOT NULL,
  `car_image` BLOB NOT NULL,
  `car_description` TEXT(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `car_name_UNIQUE` (`car_name` ASC));
  
  /* Creating All Users Table */
CREATE TABLE `car_rental`.`all_users_ru` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(16) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `user_type` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC));
  
  /* Creating Users Table */