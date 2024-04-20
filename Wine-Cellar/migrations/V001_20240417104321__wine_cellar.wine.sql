CREATE TABLE `wine` (
  `wine_id` int NOT NULL AUTO_INCREMENT,
  `wine_name` varchar(256) NOT NULL,
  `vintage_year` int DEFAULT NULL,
  `varietal` varchar(128) NOT NULL,
  `color` varchar(128) NOT NULL,
  `appellation` varchar(256) NOT NULL,
  `quantity` int DEFAULT NULL,
  `bottle_price` decimal(38,2) DEFAULT NULL,
  `tasting_note` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`wine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci