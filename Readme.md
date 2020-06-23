## Star Wars com Clean Architecture

Nossos associados são aficionados por Star Wars e com isso, queremos criar um jogo com algumas informações da franquia.



Para possibilitar a equipe de front criar essa aplicação, queremos desenvolver uma API que contenha os dados dos planetas.



Requisitos:

- A API deve ser REST

- Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, sendo inserido manualmente:

Nome
Clima
Terreno


- Para cada planeta também devemos ter a quantidade de aparições em filmes, que podem ser obtidas pela API pública do Star Wars: https://swapi.dev/about



Funcionalidades desejadas: 



- Adicionar um planeta (com nome, clima e terreno)

- Listar planetas

- Buscar por nome

- Buscar por ID

- Remover planeta



## Arquitetura

A arquitetura do sistema segue fielmente o Clean Architeture, seguindo o diagrama abaixo e aplicando os conceitos do S.O.L.I.D
![enter image description here](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)


## Camadas

A arquitetura esta dividida em dois módulos Core e Infrastructure. 

## Core

Esse módulo contém as entidades, os casos de uso e o gateways para o repositório e para apis de terceiros. Nesse módulo temos toda a regra de negócio essencial para aplicação e não temos dependências de infraestrutura ou frameworks.


## Infrastructure

Nesse módulo temos a estrutura que são definidas como detalhes no Clean Architeture e darão suporte para que o módulo Core funcione perfeitamente.

### Repository

Repositório são as interfaces para obter entidades, bem como criar e alterá-las. Eles mantêm uma lista de métodos usados para se comunicar com fontes de dados e retornar uma única entidade ou uma lista de entidades.

### Configuration

É a parte do sistema que compõe os diferentes componentes em um sistema em execução. Por exemplo: configuração dos beans do Spring, drivers de comunicação, Swagger e etc

### Delivery

É o pacote onde encontramos os adaptadores de interface que recebem solicitações de fora por exemplo nossas controllers que expõem nossa Rest API.


### Cache

O acesso a API da SWAPI é cacheado para que consultas que foram feitas anteriormente não precise consultar a API para extrair o mesmo dado.

### Logging com AOP

Utilizei o spring-aop para logar as execuções dos use cases, chamadas aos endpoints e exceções no projeto. 


### Executando os testes

mvn test

### Rodando a aplicação

mvn spring-boot:run

### Rodando com docker-compose

A partir da raiz do projeto execute esses comandos. 

    mvn package -Dmaven.test.skip=true

    cp ./target/starwars-0.0.1-SNAPSHOT.jar ./infra/docker
    
    cd infra/docker

    docker-compose build

    docker-compose up
    
    
### Usando a API

O jeito mais fácil de testar e navegar nos endpoints da API é utilizando o swagger pela url 
http://localhost:8080/swagger-ui.html
