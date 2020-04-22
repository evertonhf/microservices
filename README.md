# Microserviços
Esse projeto tem como objetivo inicial demonstrar o uso do Spring boot + mongodb + Junit + Eureka.

## Descrição do problema
Descreve-se a seguir um cenário fictício com um problema específico a ser resolvido. 

Uma empresa de desenvolvimento de software trabalha com soluções computacionais voltadas para UI - Users interface. Para atender uma demanda do mercado, necessita de uma solução backend que permita gerenciar "modelos/entidades persistentes" dinâmicamente. Os modelos serão representados através de um(a) metadata/estrutura que defina as características do modelo, tal como o "nome" e suas "propriedades", como pode ser visto a seguir.
 ````
 {
    "resource": "nome do modelo",
    "properties":
        [
            {
            "name":"nome",
            "type":"tipo de dados",
            "required":true/false
            },
            ...
    ]
}
```
**Resource"" representa o nome do modelo/classe.
**properties"" representas as propriedades/atributos. Cada propriedade consta de um "name", que representa o nome da propriedade/atributo; "type" que representa o tipo de dados, sendo informado o caminho completo; e "required" que represanta true/false indicando se o campo é obrigatório ou não.

Uma vez fornecido as características do modelo, tem-se a necessidade de persistir dados relacionado ao modelo criado. Tais dados para persistência serão fornecidos no formato "propriedade":"valor", como pode ser visto a seguir.
```
{
   "propriedade":"valor",
   ....
}
```

##Objetivos a serem alcançados
Espera-se como solução um back-end que permita gerenciar(CRUD - CREATE, READ, UPDATE, DELETE) tanto os modelos, quanto as persistências dos dados em relação aos modelos criados.

## Solução proposta
Para resolver o problema proposto serão desenvolvidos 2 back-ends independentes. O back-end "entities-structure", que permitira gerenciar os modelos dinâmicos; e "entities-service" que permitirá gerenciar a persistências dos dados em relação ao modelos. Ambos serão microserviços independentes que se comunicam entre si, e para utiliza-los, basta consumi-los por uma UI. 

##Solução técnica
A solução será desenvolvida com tecnologias baseadas na linguagem Java. Serão utilizadas soluções spring(REST+JPA+mongodb), para camada de controle e persistência, Junit nos testes unitários e de integração, e Eureka para descoberta dos serviços.

Veja a abaixo a representação da arquitetura da solução.

###########

O Cliente solici
