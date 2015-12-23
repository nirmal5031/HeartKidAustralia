-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.6.26 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.1.0.4926
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
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
  `sex` varchar(100) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

-- Dumping data for table test.heartkidregistration: ~23 rows (approximately)
/*!40000 ALTER TABLE `heartkidregistration` DISABLE KEYS */;


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

-- Dumping data for table test.oauth_access_token: ~5 rows (approximately)
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;


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

-- Dumping data for table test.oauth_client_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` (`client_id`, `RESOURCE_IDS`, `client_secret`, `scope`, `AUTHORIZED_GRANT_TYPES`, `WEB_SERVER_REDIRECT_URI`, `AUTHORITIES`, `ACCESS_TOKEN_VALIDITY`, `REFRESH_TOKEN_VALIDITY`, `ADDITIONAL_INFORMATION`, `AUTOAPPROVE`) VALUES
	('clientapp', 'restservice', 'clientpass', 'read,write', 'password,refresh_token', NULL, NULL, 1800, 120, NULL, NULL);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;


-- Dumping structure for table test.oauth_client_token
CREATE TABLE IF NOT EXISTS `oauth_client_token` (
  `TOKEN_ID` varchar(40) NOT NULL DEFAULT '',
  `TOKEN` varchar(80) DEFAULT NULL,
  `AUTHENTICATION_ID` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `CLIENT_ID` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`TOKEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table test.oauth_client_token: ~0 rows (approximately)
/*!40000 ALTER TABLE `oauth_client_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_token` ENABLE KEYS */;


-- Dumping structure for table test.oauth_jwt
CREATE TABLE IF NOT EXISTS `oauth_jwt` (
  `client_id` varchar(80) NOT NULL,
  `subject` varchar(80) DEFAULT NULL,
  `public_key` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table test.oauth_jwt: ~0 rows (approximately)
/*!40000 ALTER TABLE `oauth_jwt` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_jwt` ENABLE KEYS */;


-- Dumping structure for table test.oauth_refresh_token
CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `TOKEN_ID` varchar(256) DEFAULT NULL,
  `TOKEN` blob,
  `AUTHENTICATION` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table test.oauth_refresh_token: ~26 rows (approximately)
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;


-- Dumping structure for table test.oauth_scopes
CREATE TABLE IF NOT EXISTS `oauth_scopes` (
  `scope` text,
  `is_default` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table test.oauth_scopes: ~0 rows (approximately)
/*!40000 ALTER TABLE `oauth_scopes` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_scopes` ENABLE KEYS */;


-- Dumping structure for table test.oauth_users
CREATE TABLE IF NOT EXISTS `oauth_users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(2000) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table test.oauth_users: ~0 rows (approximately)
/*!40000 ALTER TABLE `oauth_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_users` ENABLE KEYS */;


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
  `loginflag` tinyint(1) DEFAULT '0',
  `newpassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table test.tbl_adminuser_details: ~7 rows (approximately)
/*!40000 ALTER TABLE `tbl_adminuser_details` DISABLE KEYS */;
INSERT INTO `tbl_adminuser_details` (`firstname`, `lastname`, `username`, `password`, `emailid`, `phone`, `status`, `userrole`, `login_attempts`, `loginflag`, `newpassword`) VALUES
	('Admin', 'admin', 'admin', 'PGytuvRI/Jmicfl8uOgxqQ==', 'admin@admin.com', '42342343', NULL, 'Viewer', NULL, 1, NULL),
	('Flagtest', 'Nirmal', 'flagtest', 'EPsnmHHUky3UqPkPxXbSxw==', 'nirmal@fsd.vom', '4234234', NULL, 'Admin', NULL, 1, NULL),
	('Nirmal', 'Nirmal', 'Nirmal', 'NvbA5gFsfYGfaQcPXMcbjw==', 'nirmal@gmail.com', '435345343645', NULL, 'Author', NULL, 1, NULL),
	('Nirmal', 'Kumaravel', 'nirmal5031', 'lrZNofhNfwtCCupoVU9eJw==', 'nirmal.k1@tcs.com', '324234234', NULL, 'Admin', NULL, 1, NULL),
	('test', 'test', 'test', '7TRVtu3p9mXTwnEzGdYeIw==', 'test', '435345', NULL, 'Admin', NULL, 1, NULL),
	('cvsjkvnsdjk', 'bjkvdfbvjk', 'test003', 'kNnbgLLssNd+9HWf04fjYw==', 'fsdjk@sdfhslk.com', NULL, NULL, 'Admin', NULL, 0, NULL),
	('view', 'view', 'view', 'NvbA5gFsfYGfaQcPXMcbjw==', 'dsfnj@sdfv.om', '3432', NULL, 'Viewer', NULL, 1, NULL);
/*!40000 ALTER TABLE `tbl_adminuser_details` ENABLE KEYS */;


-- Dumping structure for table test.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `heartkid_trigger` (
  `action_performed` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,`lastname` varchar(100) NOT NULL,`usertype` varchar(100) NULL,referencenumber varchar(100))
ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TRIGGER heartkid_updtrigger AFTER UPDATE ON `heartkidregistration` FOR EACH ROW insert into heartkid_trigger(action_performed,firstname,usertype,referencenumber) values('update','new.firstname','new.usertype','new.referencenumber') ;


CREATE TRIGGER heartkid_deltrigger AFTER DELETE ON `heartkidregistration` FOR EACH ROW insert into heartkid_trigger(action_performed,firstname,usertype,referencenumber) values('delete','old.firstname','old.usertype','old.referencenumber');


-- Dumping data for table test.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
