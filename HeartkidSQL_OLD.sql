-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.26 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table test.heartkidregistration
CREATE TABLE IF NOT EXISTS `heartkidregistration` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `referencenumber` varchar(100) NOT NULL,
  `usertype` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `birthdate` varchar(100) DEFAULT NULL,
  `postcode` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `conctagree` varchar(100) DEFAULT NULL,
  `contctviaphone` varchar(100) DEFAULT NULL,
  `contctviaemail` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `ethnicity` varchar(100) DEFAULT NULL,
  `countrybirth` varchar(100) DEFAULT NULL,
  `language` varchar(100) DEFAULT NULL,
  `carerfirstname` varchar(100) DEFAULT NULL,
  `carerlastname` varchar(100) DEFAULT NULL,
  `carertitle` varchar(100) DEFAULT NULL,
  `carerbirthdate` varchar(100) DEFAULT NULL,
  `carerphone` varchar(100) DEFAULT NULL,
  `careremail` varchar(100) DEFAULT NULL,
  `conditioncalld` varchar(100) DEFAULT NULL,
  `heartconds` varchar(100) DEFAULT NULL,
  `surgeryheld` varchar(100) DEFAULT NULL,
  `surgerydelay` varchar(100) DEFAULT NULL,
  `surgerydelaycount` varchar(100) DEFAULT NULL,
  `trvlsurg` varchar(100) DEFAULT NULL,
  `heartdoc` varchar(100) DEFAULT NULL,
  `localdoctorvisit` varchar(100) DEFAULT NULL,
  `emergdeptvisit` varchar(100) DEFAULT NULL,
  `careage16` varchar(100) DEFAULT NULL,
  `childtoadultdoc` varchar(100) DEFAULT NULL,
  `anxietycond` varchar(100) DEFAULT NULL,
  `anxietycondimpact` varchar(100) DEFAULT NULL,
  `curntwork` varchar(100) DEFAULT NULL,
  `worktime` varchar(100) DEFAULT NULL,
  `disabilityben` varchar(100) DEFAULT NULL,
  `chdlivingimpc` varchar(100) DEFAULT NULL,
  `changeimpactchd` varchar(100) DEFAULT NULL,
  `chdimpactwork` varchar(100) DEFAULT NULL,
  `missschooldays` varchar(100) DEFAULT NULL,
  `eductnchallng` varchar(100) DEFAULT NULL,
  `schoolgrd` varchar(100) DEFAULT NULL,
  `formalassess` varchar(100) DEFAULT NULL,
  `impactqol` varchar(100) DEFAULT NULL,
  `condimpactschl` varchar(100) DEFAULT NULL,
  `condimpactschooldesc` varchar(100) DEFAULT NULL,
  `moneyspentinyear` varchar(100) DEFAULT NULL,
  `frstsurgerysel` varchar(100) DEFAULT NULL,
  `hosptlsurgery` varchar(100) DEFAULT NULL,
  `educsupporthosp` varchar(100) DEFAULT NULL,
  `transpaedtoadult` varchar(100) DEFAULT NULL,
  `ratetransition` varchar(100) DEFAULT NULL,
  `feelsupport` varchar(100) DEFAULT NULL,
  `heartkidsupport` varchar(100) DEFAULT NULL,
  `socworker` varchar(100) DEFAULT NULL,
  `pstcologist` varchar(100) DEFAULT NULL,
  `familysuprt` varchar(100) DEFAULT NULL,
  `dedicatednurse` varchar(100) DEFAULT NULL,
  `dedicatedCHDnurse` varchar(100) DEFAULT NULL,
  `doctorcountsee` varchar(100) DEFAULT NULL,
  `traveldistdoc` varchar(100) DEFAULT NULL,
  `aftrsurgfeel` varchar(100) DEFAULT NULL,
  `heardheartkid` varchar(100) DEFAULT NULL,
  `memberheartkid` varchar(100) DEFAULT NULL,
  `supportheartkid` varchar(100) DEFAULT NULL,
  `useheartkid` varchar(100) DEFAULT NULL,
  `desccommentsany` varchar(100) DEFAULT NULL,
  `registrationdate` varchar(100) DEFAULT NULL,
  `surveystatus` varchar(255) DEFAULT NULL,
  `heartkidbirthdate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.oauth_access_token
CREATE TABLE IF NOT EXISTS `oauth_access_token` (
  `TOKEN_ID` varchar(40) NOT NULL DEFAULT '',
  `TOKEN` blob,
  `AUTHENTICATION_ID` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `CLIENT_ID` varchar(200) DEFAULT NULL,
  `AUTHENTICATION` blob,
  `REFRESH_TOKEN` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`TOKEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.oauth_client_details
CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id` varchar(80) NOT NULL,
  `RESOURCE_IDS` varchar(80) DEFAULT NULL,
  `client_secret` varchar(80) DEFAULT NULL,
  `scope` varchar(100) DEFAULT NULL,
  `AUTHORIZED_GRANT_TYPES` varchar(80) DEFAULT NULL,
  `WEB_SERVER_REDIRECT_URI` varchar(100) DEFAULT NULL,
  `AUTHORITIES` varchar(80) DEFAULT NULL,
  `ACCESS_TOKEN_VALIDITY` int(200) DEFAULT NULL,
  `REFRESH_TOKEN_VALIDITY` int(200) DEFAULT NULL,
  `ADDITIONAL_INFORMATION` varchar(200) DEFAULT NULL,
  `AUTOAPPROVE` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.oauth_client_token
CREATE TABLE IF NOT EXISTS `oauth_client_token` (
  `TOKEN_ID` varchar(40) NOT NULL DEFAULT '',
  `TOKEN` varchar(80) DEFAULT NULL,
  `AUTHENTICATION_ID` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `CLIENT_ID` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`TOKEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.oauth_jwt
CREATE TABLE IF NOT EXISTS `oauth_jwt` (
  `client_id` varchar(80) NOT NULL,
  `subject` varchar(80) DEFAULT NULL,
  `public_key` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.oauth_refresh_token
CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `TOKEN_ID` varchar(256) DEFAULT NULL,
  `TOKEN` blob,
  `AUTHENTICATION` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.oauth_scopes
CREATE TABLE IF NOT EXISTS `oauth_scopes` (
  `scope` text,
  `is_default` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.oauth_users
CREATE TABLE IF NOT EXISTS `oauth_users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(2000) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.tbl_adminuser_details
CREATE TABLE IF NOT EXISTS `tbl_adminuser_details` (
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `emailid` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userrole` varchar(255) DEFAULT NULL,
  `login_attempts` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table test.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
