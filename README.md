# Simian DNA Check API

Projeto para expor serviços necessários para detectar se uma sequência de DNA pertence a um humano ou a um símio.

## Documentação da API:

- Swagger: http://simian-dna-app.rj.r.appspot.com/swagger-ui.html

## Tecnologias

- Java 11
- Maven
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Test
- MySql
- Swagger
- Lombok
- Log4j

## Iniciando o sistema local
#### Eclipse

- Botão direito na classe `SimianDnaApplication.java` e selecionar a opção `Run as > Java Application`

#### Maven

- Abrir o prompt de comando(terminal) na pasta raiz do projeto e digitar `mvnw spring-boot:run`

## Buscar estatísticas de verificações de DNA:

### Request

`GET https://simian-dna-app.rj.r.appspot.com/api-dna/v1/stats/`

### Response

* **Success Response:**

  * **Code:** HTTP 200-OK <br />
    **Content:** `{"count_mutant_dna": 0, "count_human_dna": 0: "ratio": 0}`
 
* **Error Response:**

  * **Code:** HTTP 403-FORBIDDEN

## Checar sequência de DNA:

### Request

`POST https://simian-dna-app.rj.r.appspot.com/api-dna/v1/simian/`

Exemplo Request body:

    {
      "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
    }

Exemplo CURL:

    curl -X POST "https://simian-dna-app.rj.r.appspot.com/api-dna/v1/simian" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"dna\": [\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}"

### Response

* **Success Response:**

  * **Code:** HTTP 200-OK
 
* **Error Response:**

  * **Code:** HTTP 403-FORBIDDEN

  