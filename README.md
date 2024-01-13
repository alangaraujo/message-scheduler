# Message Scheduler API - 07/11/2021

## Apresentação

A aplicação disponibiliza uma API para agendamento de envio de mensagens, podendo especificar qual meio utilizado (e-mail, sms, push, whatsapp).

## Executando o projeto

Após clonar o projeto, utilize algum dos meios abaixo para executá-lo:

> Docker Compose (É efetuado o build da aplicação durante construção do Compose + MySQL, e aplicação já estará disponível)
> 
> * cd .docker
> * chmod +x run.sh
> * ./run.sh

> Banco de Dados MySQL em Docker, e aplicação em separado
> * docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=message -d -p 3306:3306 mysql:8.0.23 mysqld --lower_case_table_names=1
> * ./mvnw clean package spring-boot:run

> Caso possua um Banco de Dados MySQL em execução (versão 8.0.23+), basta incluir variáveis de ambiente, criar um novo Database (com nome 'message') e executar a aplicação:
> * DB_USERNAME=usuário
> * DB_PASSWORD=senha
> * DB_URL=<jdbc:mysql://endereço:3306/message>
> * ./mvnw clean package spring-boot:run

## Consumo da API

A aplicação pode ser testada atráves de uma Collection, para ser importada pelo [Postman](https://www.postman.com/downloads/), disponível no diretório:

> .postman/Message Scheduling Collection.json

Ou, através da aplicação Swagger, através da URL:

> http://localhost:8080/swagger-ui.html
