USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `task`;

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

