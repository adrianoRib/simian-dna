package br.com.meli.simiandna;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SimianDnaApplicationTests {
	
	@Autowired
    private MockMvc mvc;

	@Test
    public void whenGetStatsSuccessfully_thenReturns200isOk() throws Exception {
		
        mvc.perform(get("/api-dna/v1/stats")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
	
	@Test
    public void whenPostSimianDnaSuccessfully_thenReturns200isOk() throws Exception {
		
		mvc.perform(post("/api-dna/v1/simian")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getMockSimianDnaRequest()))
                .andExpect(status().isOk());

    }
	
	@Test
    public void whenPostHumanDnaSuccessfully_thenReturns200isOk() throws Exception {
		
		mvc.perform(post("/api-dna/v1/simian")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getMockHumanDnaRequest()))
                .andExpect(status().isOk());

    }
	
	@Test
    public void whenPostSimianDnaAlreadyExists_thenReturns403isForbidden() throws Exception {
		
		mvc.perform(post("/api-dna/v1/simian")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getMockSimianDnaDuplicatedRequest()))
                .andExpect(status().isForbidden());

    }
	
	@Test
    public void whenPostHumanDnaAlreadyExists_thenReturns403isForbidden() throws Exception {
		
		mvc.perform(post("/api-dna/v1/simian")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getMockHumanDnaDuplicatedRequest()))
                .andExpect(status().isForbidden());

    }
	
	@Test
    public void whenPostInvalidDna_thenReturns403isForbidden() throws Exception {
		
		mvc.perform(post("/api-dna/v1/simian")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getMockInvalidDnaRequest()))
                .andExpect(status().isForbidden());

    }
	
	@Test
    public void whenPostInvalidRequesBody_thenReturns403isForbidden() throws Exception {
		
		mvc.perform(post("/api-dna/v1/simian")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getMockInvalidRequestBody()))
                .andExpect(status().isForbidden());

    }
	
	@Test
    public void whenPostUnknowRequesBody_thenReturns400isBadRequest() throws Exception {
		
		mvc.perform(post("/api-dna/v1/simian")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getMockUnknowRequestBody()))
                .andExpect(status().isBadRequest());

    }
	
	private String getMockSimianDnaDuplicatedRequest() {
        return "{\"dna\": [\"CTGTGA\", \"CCGAGC\", \"TATTGT\", \"AGAGGG\", \"CCCCTA\", \"TCACTG\"]}";
    }
	
	private String getMockHumanDnaDuplicatedRequest() {
        return "{\"dna\": [\"CTGTGA\", \"GCGAGC\", \"TATTGT\", \"AGAGAG\", \"CTCCTA\", \"TCACTG\"]}";
    }
	
	private String getMockSimianDnaRequest() {
        return "{\"dna\": [\"CTGAGA\", \"CTGAGC\", \"TATTGT\", \"AGAGGG\", \"CCCCTA\", \"TCACTG\"]}";
    }
	
	private String getMockHumanDnaRequest() {
        return "{\"dna\": [\"CTGGAA\",\"CTGAGC\",\"TATTGT\",\"AGAGGG\",\"CACCTA\",\"TCACTG\"]}";
    }
	
	private String getMockInvalidDnaRequest() {
        return "{\"dna\": [\"XPTO\",\"INVALID\",\"DNA\",\"SEQUENCE\"]}";
    }
	
	private String getMockInvalidRequestBody() {
        return "{\"invalid_object\": [\"XPTO\",\"INVALID\",\"DNA\",\"SEQUENCE\"]}";
    }
	
	private String getMockUnknowRequestBody() {
        return "{\"dna\": 12345}";
    }

}
