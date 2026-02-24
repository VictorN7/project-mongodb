# 📌 API REST com Spring Boot + MongoDB

Projeto desenvolvido como estudo prático para aprofundar modelagem NoSQL e construção de APIs REST escaláveis com Spring Boot.

O objetivo deste projeto foi revisar conceitos de modelagem em MongoDB, aplicação de DTOs, queries personalizadas e organização em camadas seguindo boas práticas de arquitetura backend.


## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Lombok
- Maven
- Postman e HTTPie terminal
- RESTful API
- ControllerAdvice (tratamento global de exceções)

## 🏗️ Arquitetura do Projeto

**O projeto segue organização em camadas:**

```txt
domain        → Entidades do sistema
dto           → Objetos de transferência de dados
repository    → Acesso ao banco (MongoRepository)
service       → Regras de negócio
resources     → Camada REST (Controllers)
exception     → Tratamento global de erros
config        → Instanciação inicial de dados
```

## 🧩 Diagrama

```
Client → Controller → Service → Repository → MongoDB
```

## 🧠 Modelagem NoSQL

**A modelagem foi pensada considerando boas práticas do MongoDB:**

### 📌 Post

- Contém dados do autor (embedado via AuthorDTO)
- Contém lista de comentários (embedado via CommentDTO)
- Utilização de `@Indexed` para otimização de consultas por campos frequentemente buscados.

### 📌 User

- Mantém referência aos posts via @DBRef
- Utiliza encapsulamento para proteger a lista de posts

## 🔍 Funcionalidades Implementadas

### 👤 Usuários

- Criar usuário
- Listar todos os usuários
- Buscar usuário por ID
- Atualizar usuário
- Remover usuário
- Listar posts de um usuário

**Endpoints:**

```txt
GET    /api/v1/users
GET    /api/v1/users/{id}
GET    /api/v1/users/{id}/posts
POST   /api/v1/users
PUT    /api/v1/users/{id}
DELETE /api/v1/users/{id}
```

## 📄 Exemplo de Documento Post

Busca de posts por usuário:

**GET -** `http://localhost:8080/api/v1/users/699b5ec2b6a8fc7b1d0359f5/posts`

```
[
    {
        "id": "699b5ec2b6a8fc7b1d0359fc",
        "date": "2026-02-17T00:00:00.000Z",
        "title": "Partiu viagem!",
        "body": "Vou viajar para Pernambuco. Até mais!",
        "author": {
            "id": "699b5ec2b6a8fc7b1d0359f5",
            "name": "Marcelo da Silva"
        },
        "comments": [
            {
                "text": "Boa viagem meu parceiro!",
                "date": "2026-02-17T00:00:00.000Z",
                "author": {
                    "id": "699b5ec2b6a8fc7b1d0359fb",
                    "name": "Alessandro Souza"
                }
            },
            {
                "text": "Aproveite bem rsrs",
                "date": "2026-02-17T00:00:00.000Z",
                "author": {
                    "id": "699b5ec2b6a8fc7b1d0359fa",
                    "name": "Maria Luiza"
                }
            }
        ]
    }
]
```

## 📝 Posts

- Buscar post por ID
- Buscar por título (case insensitive)
- Busca completa com:
  - Texto (title, body, comments)
  - Intervalo de datas
  - Combinação de `$and` + `$or`

**Endpoints:**
```txt 
GET /api/v1/posts/{id}
GET /api/v1/posts/titlesearch?txt=texto
GET /api/v1/posts/fullsearch?txt=texto&minDate=yyyy-MM-dd&maxDate=yyyy-MM-dd
```

## 🔎 Busca Avançada (Full Search)

**Implementação utilizando @Query personalizada do MongoDB:**

- Regex case insensitive
- Filtro por data mínima e máxima
- Busca dentro de campos embedados (comments.text)
- Combinação de $and + $or

**Exemplo de chamada:**
```txt
GET /api/v1/posts/fullsearch?txt=data&minDate=2026-02-01&maxDate=2026-02-28
```
Essa chamada realiza uma busca textual pelo termo "data" nos campos `title`, `body` e `comments.text`, filtrando os resultados dentro do intervalo informado entre `minDate` e `maxDate`, utilizando combinação de operadores `$and` e `$or` no MongoDB.

**Exemplo de retorno da chamada:**

```
[
    {
        "id": "699b5ec2b6a8fc7b1d0359fe",
        "date": "2026-02-25T00:00:00.000Z",
        "title": "Estudos a todo vapor",
        "body": "Revisando Spring Data, DTOs e boas práticas de arquitetura.",
        "author": {
            "id": "699b5ec2b6a8fc7b1d0359f5",
            "name": "Marcelo"
        },
        "comments": [
            {
                "text": "E como foi esse desafio?",
                "date": "2026-03-01T00:00:00.000Z",
                "author": {
                    "id": "699b5ec2b6a8fc7b1d0359fa",
                    "name": "Luiza"
                }
            }
        ]
    },
    {
        "id": "699b5ec2b6a8fc7b1d0359ff",
        "date": "2026-03-01T00:00:00.000Z",
        "title": "Final de semana produtivo",
        "body": "Refatorei o código e melhorei a organização do backend.",
        "author": {
            "id": "699b5ec2b6a8fc7b1d0359f7",
            "name": "Matheus"
        },
        "comments": [
            {
                "text": "Bora marcar de estudar junto, mano? Estou nessa pegada de Spring Data e arquitetura limpa...",
                "date": "2026-02-26T00:00:00.000Z",
                "author": {
                    "id": "699b5ec2b6a8fc7b1d0359fb",
                    "name": "Alessandro"
                }
            }
        ]
    }
]
```

## 🛡️ Tratamento de Exceções

O projeto utiliza @ControllerAdvice para tratamento global.

**Exemplo de resposta de erro:**

```json
{
  "timestamp": "2026-02-21T21:30:00Z",
  "error": "Not found",
  "status": 404,
  "message": "Object not found",
  "path": "/api/v1/posts/999"
}
```

## 🧪 Testando a API

A API pode ser testada utilizando:

- Postman
- HTTPie (via terminal)

Exemplo via HTTPie terminal:

```bash
$ http GET :8080/api/v1/users/699b5ec2b6a8fc7b1d0359f6/posts
```


## 📊 Conceitos Aplicados

- Modelagem híbrida (embed + referência)
- DTO Pattern para controle de payload
- Encapsulamento com Collections.unmodifiableList
- Regex no MongoDB
- Indexação com @Indexed
- Versionamento de API (/api/v1)
- Uso correto de Status HTTP
- ResponseEntity
- Stream API
- Organização em camadas

## ▶️ Como Executar o Projeto

### 1️⃣ Clonar o repositório
```
git clone https://github.com/seu-usuario/seu-repo.git
```

### 2️⃣ Configurar MongoDB

**Certifique-se de que o MongoDB esteja rodando localmente na porta padrão:**
```
mongodb://localhost:27017
```

### 3️⃣ Executar aplicação

```
./mvnw spring-boot:run
```

Ou execute pela sua IDE.

## 📌 Dados Iniciais

O projeto utiliza CommandLineRunner para popular o banco automaticamente ao iniciar.

Usuários, posts e comentários são criados automaticamente.

## 🎯 Sobre o Projeto

Este projeto demonstra:

- Modelagem eficiente em banco NoSQL
- Construção de API REST seguindo boas práticas
- Separação de responsabilidades em camadas
- Implementação de buscas avançadas com MongoDB

## 🚀 Próximas Evoluções (Possíveis Melhorias)

- Implementar paginação
- Adicionar validações com Bean Validation
- Adicionar testes unitários
- Dockerizar aplicação
- Implementar Swagger/OpenAPI

## 👨‍💻 Autor

Victor Hugo Nogueira Santos
Backend Developer
