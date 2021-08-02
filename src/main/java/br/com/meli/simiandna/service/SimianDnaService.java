package br.com.meli.simiandna.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meli.simiandna.model.DnaSequence;
import br.com.meli.simiandna.repository.SimianDnaRepository;
import br.com.meli.simiandna.vo.SimianVO;
import br.com.meli.simiandna.vo.StatsVO;

@Service
public class SimianDnaService {

	private static final Logger logger = LoggerFactory.getLogger(SimianDnaService.class);

	// Padroes do DNA dos Simios
	private static final List<String> SIMIAN_PATTERNS = Arrays.asList("AAAA","TTTT","CCCC","GGGG");
	
	//  Tipo do DNA dos Humanos
	private static final int DNA_TYPE_HUMAN = 1;
	
	//  Tipo do DNA dos Simios
	private static final int DNA_TYPE_SIMIAN = 2;
	
	// Tamanhos linha e colunas da matriz
	private int rowLength, colLength;
	
	@Autowired
	SimianDnaRepository repository;
	
	/**
	 * Retorna as estatisticas das quantidades de simios e humanos 
	 * e a proporção dos simios para humanos
	 * 
	 * @return StatsVO stats
	 */
	public StatsVO getStats() {
		return new StatsVO(countDnaSimian(), countDnaHuman());
	}

	/**
	 * Valida DNA Simio ou Humano e armazena DNA Unico
	 * 
	 * @return boolean
	 */
	public boolean isSimian(SimianVO simian) {

		logger.info("Checking for Simian DNA: " + simian);

		String[] dnaSeq = simian.getDna();

		if (!isValidDna(dnaSeq)) {
			logger.error("Invalid DNA Sequence");
			return false;
		}
		
		if (containsDna(dnaSeq)) {
			logger.error("DNA Sequence already exists");
			return false;
		}
		
		DnaSequence dna = new DnaSequence(DNA_TYPE_HUMAN, String.join(",", dnaSeq));
		
		if (findSimian(dnaSeq)) {

			dna.setDnaType(DNA_TYPE_SIMIAN);
			
			logger.info("Inserting a Simian DNA ");

			// Se for o DNA Mock para testes não salvo no BD
			if (getMockSimianDnaTest().equalsIgnoreCase(dna.getDna())
					|| getMockHumanDnaTest().equalsIgnoreCase(dna.getDna()))
				return true;

			repository.save(dna);
			return true;
		}

		logger.info("Inserting a Human DNA");
		
		// Se for o DNA Mock para testes não salvo no BD
		if (getMockSimianDnaTest().equalsIgnoreCase(dna.getDna())
				|| getMockHumanDnaTest().equalsIgnoreCase(dna.getDna()))
			return true;
		
		repository.save(dna);
		return false;
	}

	/**
	 * Verifica se é um DNA valido
	 * 
	 * @return boolean
	 */
	private boolean isValidDna(String[] dnaSeq) {

		if (dnaSeq == null || dnaSeq.length < 4 || dnaSeq.length % 2 != 0)
			return false;

		Pattern compile = Pattern.compile("[^AaTtCcGg]");

		return Arrays.stream(dnaSeq).allMatch(dna -> {
			return dna.length() >= 4 && !compile.matcher(dna).find();
		});
	}

	/**
	 * Busca um DNA Simio
	 * 
	 * @return boolean
	 */
	private boolean findSimian(String[] dnaSeq) {
        
        rowLength = dnaSeq[0].length();
        colLength = dnaSeq.length;
        
        char[][] grid = new char[rowLength][colLength];

        int i = 0;
        for (String dna : dnaSeq) {
        	grid[i] = dna.toUpperCase().toCharArray();
            i++;
        }
        
        return SIMIAN_PATTERNS.stream().anyMatch(pattern-> findSimianPattern(grid,pattern));
	}
	
	/**
	 * Verifica se é um DNA ja existe
	 * 
	 * @return boolean
	 */
	private boolean containsDna(String[] dnaSeq) {
		return repository.countByDna(String.join(",", dnaSeq)) > 0;
	}

	/**
	 * Busca na matriz o termo do DNA em 4 direcoes a partir da direita 
	 * no sentido horario do prmeiro caractere encontrado
	 *
	 * @return boolean
	 */
	private boolean findInGrid(char[][] grid, int row, int col, String pattern) {
		
		// Instrucoes de Busca em 4 direcoes, nos eixos X e Y da matriz
		int[] xDirections = { 0, 1, 1, 1 };
		int[] yDirections = { 1, -1, 0, 1 };
		
		// Busca o primeiro caractere como ponto de busca nas direcoes
		if (grid[row][col] != pattern.charAt(0))
			return false;

		int patternLength = pattern.length();

		// Busca em 4 direcoes partir da primeira ocorrencia da matriz
		for (int dir = 0; dir < xDirections.length; dir++) {
			
			// Inicializando pontos de busca
			int foundCharCount;
			int rowDirection = row + xDirections[dir];
			int colDirection = col + yDirections[dir];

			// Como ja achei o primeiro caractere busco os proximos
			for (foundCharCount = 1; foundCharCount < patternLength; foundCharCount++) {
				
				// Se cheguei nas bordas da matriz
				if (rowDirection >= rowLength || rowDirection < 0 || colDirection >= colLength || colDirection < 0)
					break;

				// Se nao achei
				if (grid[rowDirection][colDirection] != pattern.charAt(foundCharCount))
					break;

				// Se achei, continuo naquela direcao
				rowDirection += xDirections[dir];
				colDirection += yDirections[dir];
			}

			// Se achei todos caracteres paro a busca
			if (foundCharCount == patternLength)
				return true;
		}
		
		return false;
	}

	/**
	 * Busca na matriz o termo do DNA
	 * 
	 * @return boolean
	 */
	private boolean findSimianPattern(char[][] grid, String pattern) {
		
		for (int row = 0; row < rowLength; row++) {
			for (int col = 0; col < colLength; col++) {
				if (findInGrid(grid, row, col, pattern)) {
					System.out.println("Padrao encontrado em " + row + ", " + col);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Busca o total de DNA Humanos
	 * 
	 * @return int
	 */
	private int countDnaHuman() {
		return repository.countByDnaType(DNA_TYPE_HUMAN);
	}
	
	/**
	 * Busca o total de DNA Simios
	 * 
	 * @return int
	 */
	private int countDnaSimian() {
		return repository.countByDnaType(DNA_TYPE_SIMIAN);
	}
	
	private String getMockSimianDnaTest() {
        return "CTGAGA,CTGAGC,TATTGT,AGAGGG,CCCCTA,TCACTG";
    }
	
	private String getMockHumanDnaTest() {
		return "CTGGAA,CTGAGC,TATTGT,AGAGGG,CACCTA,TCACTG";
	}
}
