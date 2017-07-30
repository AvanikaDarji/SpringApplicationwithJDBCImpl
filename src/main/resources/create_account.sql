CREATE DATABASE  IF NOT EXISTS `cs548_lab5`;
USE `cs548_lab5`;

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `accountID` varchar(45) NOT NULL,
  `balanceDue` double DEFAULT '0',
  `dueDate` date DEFAULT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

