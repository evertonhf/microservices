# Microserviços
Esse projeto tem como objetivo inicial demonstrar o uso do Spring boot + spring cloud + mongodb + Junit + Eureka.

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
´´´
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
A solução será desenvolvida com tecnologias baseadas na linguagem Java. O desenvolvimento será feito com spring boot, Spring MVC, spring Cloud, library Ribbon, Eureka, spring data, mongodb e Junit. Veja abaixo a representação da arquitetura da solução.

!["Modelo conceitual"](https://github.com/evertonhf/microservices/blob/master/imgs/modelo.png)

O Cliente atraves de uma solução front-end, solicita informações sobre os serviços ao Eureka, servidor de descoberta de serviços; As configurações do Eureka e dos microserviços são armazenadas de forma centralizada no servidor Spring Cloud, servidor de configurações que está sendo utilizado localmente, porém, tem-se como alternativa fazer o uso de serviços da nuvem; a comunicação do microserviço "entities-service" e o "entity-service" é feita através do Ribbon, uma bliblioteca que permite selecionar uma instância do microserviço através de regras bem definidas. Cada microserviço utiliza mongodb como banco de dados não relacional, independente, mapeados com JPA, que estão sendo executados embutidos na plataforma; por fim, os testes unitários e de integração, desenvolvidos nos microserviçõs são realizados pelo Junit.

##Melhorias

Para alcançar um ambiente mais completo pode-se utilizar o Docker para gerenciamento e padronização do ambiente de execução, dentre outras alternativas.

Este é um conceito inicial que envolve a utilização de tecnologias java, e que passará por futuras alterações para sua melhoria... aguarde.
