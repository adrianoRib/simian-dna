CREATE TABLE IF NOT EXISTS `dna_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT null,
  PRIMARY KEY (`id`)
);

INSERT INTO simian_dna_db.dna_type (id, description) VALUES(1, 'Human');
INSERT INTO simian_dna_db.dna_type (id, description) VALUES(2, 'Simian');
commit;

CREATE TABLE IF NOT EXISTS `dna` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_dna_type` bigint(20) NOT null,
  `dna_sequence` varchar(255) NOT null,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_dna` FOREIGN KEY (`id_dna_type`) REFERENCES `dna_type` (`id`),
  CONSTRAINT unique_dna_sequence UNIQUE (dna_sequence)
);