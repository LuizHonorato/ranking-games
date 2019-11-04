CREATE TABLE `ranking_games`.`player` (
 `id` INT NOT NULL,
 `name` VARCHAR(255) NOT NULL,
 `matches` INT NOT NULL,
 `victories` INT NOT NULL,
 PRIMARY KEY (`id`));

 ALTER TABLE `ranking_games`.`player`
 CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ,
 ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
 