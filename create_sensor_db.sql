CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `task`;
DROP TABLE IF EXISTS `sensor`;


CREATE TABLE `sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `production_order` int(11) NOT NULL,
  `serial_number` int(6) NOT NULL,
  `sensor_type` varchar(45) DEFAULT NULL,
  `sensor_flow` int(3) NOT NULL,
  `part_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`serial_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `sensor_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_SENSOR_ID_idx` (`sensor_id`),
  
  CONSTRAINT `FK_SENSOR`
  FOREIGN KEY (`sensor_id`)
  REFERENCES `sensor`(`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Data for table `employee`
--

INSERT INTO `sensor` VALUES 
	(1,12345678,121456,'STD',3,'PZN-SS-003-00'),
	(2,12345678,124988,'HP',15,'PZN-SS-XXX-HP'),
	(3,12345678,125994,'LP',5,'PZN-SS-XXX-LP'),
	(4,12345678,131789,'VLP',15,'PZN-SS-XXX-VLP'),
	(5,12345678,121188,'HT',3,'PZN-SS-XXX-HT');
    
    

