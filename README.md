# Microserviços
Esse projeto tem como objetivo inicial demonstrar o uso do Spring boot + spring cloud + mongodb + Junit + Eureka...

## Descrição do problema
Descreve-se a seguir um cenário fictício com um problema específico a ser resolvido. 

Uma empresa de desenvolvimento de software trabalha com soluções computacionais voltadas para UI - Users interface. Para atender uma demanda do mercado, necessita de uma solução backend que permita gerenciar "modelos/entidades persistentes" dinâmicamente. Os modelos serão representados através de um(a) metadata/estrutura que defina as características do modelo, tal como o "nome" e suas "propriedades", como pode ser visto a seguir.
```
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
A solução será desenvolvida com tecnologias baseadas na linguagem Java. O desenvolvimento será feito com spring boot, Spring MVC, spring Cloud, library Ribbon, Eureka, spring data, mongodb e Junit. Veja abaixo a representação da arquitetura da solução.

!["Modelo conceitual"](https://github.com/evertonhf/microservices/blob/master/imgs/modelo.png)

O Cliente atraves de uma solução front-end, solicita informações sobre os serviços ao Eureka, servidor de descoberta de serviços; As configurações do Eureka e dos microserviços são armazenadas de forma centralizada no servidor Spring Cloud, servidor de configurações que está sendo utilizado localmente, porém, tem-se como alternativa fazer o uso de serviços da nuvem; a comunicação do microserviço "entities-service" e o "entity-service" é feita através do Ribbon, uma bliblioteca que permite selecionar uma instância do microserviço através de regras bem definidas. Cada microserviço utiliza mongodb como banco de dados não relacional, independente, mapeados com JPA, que estão sendo executados embutidos na plataforma; por fim, os testes unitários e de integração, desenvolvidos nos microserviçõs são realizados pelo Junit.

## Execução
Para realizar a execução do projeto, após importar os projetos para IDE,neste caso a IDE eclipse foi a utilizada, copie a pasta "FileTemp" deste repositório para dentro da unidade "c:\", local previamente configurado na aplicação. Nesta pasta contém os arquivos de inicialização dos serviços, e caso o caminho de destino seja outro,  diferente da unidade "c:\", deve-se alterar o aquivo de configuração no projeto "Cloud-service". Após, deve-se iniciar o servido Cloud-service, Eureka-service, entities-struct e entities-service, nesta respectiva ordem. 
Após pode-se utilizar uma extensão do browser, como postman, para consumir o serviço. Acesse o microserviço "entities-struct" através da url "http://localhost:8081/", método de envio "POST", e  cabeçalho "Content-Type" = Application/Json, com a entrada válida que representa o modelo, como a seguir:

```
{
    "resource": "Peoples",
    "properties":
        [
            {
            "name":"name",
            "type":"java.lang.String",
            "required":true
            },
            {
            "name":"email",
            "type":"java.lang.String",
            "required":false
            },
            {
            "name":"idade",
            "type":"java.lang.Integer",
            "required":false
            }
    ]
}

```
Posteriormente, para criar entidades persistentes relacionado ao modelo criado, acesse o microserviço "entities-service"  através da url "http://localhost:8082/Peoples", método de envio "POST", e  cabeçalho "Content-Type" = Application/Json, com a entrada válida que representa os dados de um modelo já cadastrado, como a seguir:

```
{
   "name":"Joao",
   "email":"jo@gmail.com",
   "idade":12
}
```




## Melhorias

Para alcançar um ambiente mais completo pode-se utilizar o Docker para gerenciamento e padronização do ambiente de execução, dentre outras alternativas.

Este é um conceito inicial que envolve a utilização de tecnologias java, e que passará por futuras alterações para sua melhoria... aguarde.
