CREATE SCHEMA IF NOT EXISTS springtestapp DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE springtestapp ;

-- -----------------------------------------------------
-- Table mydb.letters
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS letters (
  id BIGINT NOT NULL AUTO_INCREMENT,
  number VARCHAR (20),
  dt DATE NOT NULL ,
  subject VARCHAR(250) NOT NULL ,
  published BIT NOT NULL DEFAULT 0 ,
  filename VARCHAR(200) NOT NULL ,
  description VARCHAR(1000) NULL ,
  PRIMARY KEY (id) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX i_letters_numbers ON letters (number);