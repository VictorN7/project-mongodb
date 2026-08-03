# 📌 REST API com Spring Boot + MongoDB

Backend REST API desenvolvido com **Spring Boot e MongoDB**, com foco em **modelagem NoSQL e construção de APIs escaláveis**.

O principal objetivo deste projeto foi consolidar conceitos de modelagem com MongoDB, utilização de DTOs, consultas personalizadas e arquitetura em camadas, seguindo boas práticas de desenvolvimento backend.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Lombok
- Maven
- Postman e HTTPie (terminal)
- RESTful API
- ControllerAdvice (tratamento global de exceções)

## 🏗️ Arquitetura do Projeto

**O projeto segue uma arquitetura em camadas:**

```txt
domain        → Entidades do sistema
dto           → Objetos de transferência de dados
repository    → Camada de acesso ao banco de dados (MongoRepository)
service       → Camada de regras de negócio
resources     → Camada REST (Controllers)
exception     → Tratamento global de erros
config        → Instanciação dos dados iniciais
