package br.com.meli.simiandna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meli.simiandna.service.SimianDnaService;
import br.com.meli.simiandna.vo.SimianVO;
import br.com.meli.simiandna.vo.StatsVO;
import io.swagger.annotations.Api;

@Api(tags = "SimianDnaEndpoint")
@RestController
@RequestMapping("/api-dna/v1")
public class SimianDnaController {
	
	@Autowired
	SimianDnaService service;
	
	@PostMapping("/simian")
    public ResponseEntity<?> isSimian(@RequestBody SimianVO simian){
		
		if(service.isSimian(simian))
	        return ResponseEntity.ok().build();
			
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsVO> findStats(){
		return ResponseEntity.ok(service.getStats());
    }

}
