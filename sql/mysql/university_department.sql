
DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint NOT NULL,
  `dept_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `department` WRITE;
INSERT INTO `department` VALUES (1,'Computer Science'),(2,'Electronics & Communication'),(3,'Mechanical'),(4,'Electrical'),(5,'Civil');
UNLOCK TABLES;
