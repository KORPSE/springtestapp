CREATE SCHEMA IF NOT EXISTS `springtestapp` DEFAULT CHARACTER SET utf8 ;
USE `springtestapp` ;

-- -----------------------------------------------------
-- Table `springtestapp`.`files`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `springtestapp`.`files` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `data` MEDIUMBLOB NOT NULL ,
  `name` VARCHAR(100) NOT NULL ,
  `contentType` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `springtestapp`.`letters`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `springtestapp`.`letters` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `number` VARCHAR(20) NULL DEFAULT NULL ,
  `dt` DATE NOT NULL ,
  `subject` VARCHAR(250) NOT NULL ,
  `published` BIT(1) NOT NULL DEFAULT b'0' ,
  `description` VARCHAR(1000) NULL DEFAULT NULL ,
  `file_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `file_id`) ,
  UNIQUE INDEX `i_letters_numbers` (`number` ASC) ,
  INDEX `fk_letters_files` (`file_id` ASC) ,
  CONSTRAINT `fk_letters_files`
    FOREIGN KEY (`file_id` )
    REFERENCES `springtestapp`.`files` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

