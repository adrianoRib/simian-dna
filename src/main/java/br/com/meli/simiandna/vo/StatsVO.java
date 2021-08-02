package br.com.meli.simiandna.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatsVO {

    private int count_mutant_dna;
    private int count_human_dna;
    
    public double getRatio() {
    	
    	if(count_human_dna <= 0 || count_mutant_dna <= 0) return 0d;
		
		return new BigDecimal((double)count_mutant_dna / (double)count_human_dna).setScale(6,RoundingMode.HALF_UP).doubleValue();
    }
}
