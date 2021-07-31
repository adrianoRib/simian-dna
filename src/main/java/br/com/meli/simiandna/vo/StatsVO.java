package br.com.meli.simiandna.vo;

import java.math.BigDecimal;

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

    private BigDecimal count_mutant_dna;
    private BigDecimal count_human_dna;
    private BigDecimal ratio;

}
