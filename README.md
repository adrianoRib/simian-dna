# Simian DNA Check API

Projeto para expor serviços necessários para detectar se uma sequência de DNA pertence a um humano ou a um símio.


## Buscar estatísticas de verificações de DNA:

### Request

Exemplo: http://localhost:8080/api-dna/v1/stats/

`GET /api-dna/v1/stats/`

### Response

* **Success Response:**

  * **Code:** HTTP 200-OK <br />
    **Content:** `{"count_mutant_dna": 0, "count_human_dna": 0: "ratio": 0}`
 
* **Error Response:**

  * **Code:** HTTP 403-FORBIDDEN

## Checar sequência de DNA:

### Request

Exemplo: http://localhost:8080/api-dna/v1/simian/

`POST /api-dna/v1/simian/`

Exemplo CURL:

    curl -X POST "http://localhost:8080/api-dna/v1/simian" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"dna\": [\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}"

Exemplo Request body:

    {
      "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
    }

### Response

* **Success Response:**

  * **Code:** HTTP 200-OK
 
* **Error Response:**

  * **Code:** HTTP 403-FORBIDDEN

  