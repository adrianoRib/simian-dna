package br.com.meli.simiandna.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.meli.simiandna.vo.SimianVO;

@Service
public class SimianDnaService {

	private static final Logger logger = LoggerFactory.getLogger(SimianDnaService.class);

	// Padroes do DNA dos Simios
	private static final List<String> SIMIAN_PATTERNS = Arrays.asList("AAAA","TTTT","CCCC","GGGG");
	
	// Tamanhos linha e colunas da matriz
	private int rowLength, colLength;

	/**
	 * Valida DNA Simio ou Humano e armazena DNA Unico
	 * 
	 * @author Adriano Ribeiro
	 * @return boolean
	 */
	public boolean isSimian(SimianVO simian) {

		logger.info("Checking for Simian DNA: " + simian);

		String[] dnaSeq = simian.getDna();

		if (!isValidDna(dnaSeq)) {
			logger.error("Invalid DNA Sequence");
			return false;
		}

		if (findSimian(dnaSeq)) {

			// @TODO implemntar aqui o armazenamento Simio
			logger.info("Inserting a Simian DNA ");
			return true;
		}

		// @TODO implementar aqui o armazenamento Humano
		logger.info("Inserting a Human DNA");
		return false;
	}

	/**
	 * Verifica se é um DNA valido
	 * 
	 * @author Adriano Ribeiro
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
	 * @author Adriano Ribeiro
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
	 * Busca na matriz o termo do DNA em 4 direcoes a partir da direita 
	 * no sentido horario do prmeiro caractere encontrado
	 *
	 * @author Adriano Ribeiro
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
	 * @author Adriano Ribeiro
	 * @return boolean
	 */
	private boolean findSimianPattern(char[][] grid, String pattern) {
		
		for (int row = 0; row < rowLength; row++) {
			for (int col = 0; col < colLength; col++) {
				if (findInGrid(grid, row, col, pattern)) {
					System.out.println("pattern found at " + row + ", " + col);
					return true;
				}
			}
		}
		
		return false;
	}
}
