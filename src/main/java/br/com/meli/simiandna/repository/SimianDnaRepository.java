package br.com.meli.simiandna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meli.simiandna.model.DnaSequence;

@Repository
public interface SimianDnaRepository extends JpaRepository<DnaSequence, Long>{
	
	int countByDnaType(int dnaType);
	
	int countByDna(String dna);
	
	int deleteByDna(String dna);
}
