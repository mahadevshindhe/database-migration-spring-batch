
DROP TABLE IF EXISTS `subjects_learning`;
CREATE TABLE `subjects_learning` (
  `id` bigint NOT NULL,
  `sub_name` varchar(45) DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  `marks_obtained` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id_fk_idx` (`student_id`),
  CONSTRAINT `student_id_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `subjects_learning` WRITE;
UNLOCK TABLES;