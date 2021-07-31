package br.com.meli.simiandna.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meli.simiandna.vo.SimianVO;
import br.com.meli.simiandna.vo.StatsVO;
import io.swagger.annotations.Api;

@Api(tags = "SimianDnaEndpoint")
@RestController
@RequestMapping("/api-dna/v1")
public class SimianDnaController {
	
	@PostMapping("/simian")
    public ResponseEntity<?> isSimian(@RequestBody SimianVO simianRequest){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsVO> findStats(){
        return ResponseEntity.ok(new StatsVO());
    }

}
