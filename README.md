# 📌 REST API with Spring Boot + MongoDB

Backend REST API built with Spring Boot and MongoDB, focused on NoSQL modeling and scalable API design.

The main goal of this project was to consolidate MongoDB modeling concepts, DTO usage, custom queries, and layered architecture while following backend best practices.

## 🚀 Technologies Used

- Java 21
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Lombok
- Maven
- Postman & HTTPie (terminal)
- RESTful API
- ControllerAdvice (global exception handling)

## 🏗️ Project Architecture

**The project follows a layered architecture:**

```txt
domain        → System entities
dto           → Data Transfer Objects
repository    → Database access layer (MongoRepository)
service       → Business logic layer
resources     → REST layer (Controllers)
exception     → Global error handling
config        → Initial data instantiation
```

## 🧩 Architecture Diagram

```
Client → Controller → Service → Repository → MongoDB
```

## 🧠 NoSQL Modeling

**The data modeling was designed following MongoDB best practices:**

### 📌 Post

- Contains embedded author data (AuthorDTO)
- Contains embedded comments list (CommentDTO)
- Uses `@Indexed` to optimize frequently searched fields

### 📌 User

- Maintains references to posts using `@DBRef` annotation
- Uses encapsulation to protect the posts list


## 🖥️ Running Application

- Spring Boot backend running
- MongoDB database integrated
- REST requests executed via HTTPie and Postman
- HTTP logs confirming 200 OK responses

<img width="1919" height="1027" alt="Mongo - MongoSpring" src="https://github.com/user-attachments/assets/a6a18321-f179-4ce3-86ad-ac9047c7cdfc" />


## 🔍 Implemented Features

### 👤 Users

- Create user
- List all users
- Find user by ID
- Update user
- Delete user
- List posts from a specific user

**Endpoints:**

```txt
GET    /api/v1/users
GET    /api/v1/users/{id}
GET    /api/v1/users/{id}/posts
POST   /api/v1/users
PUT    /api/v1/users/{id}
DELETE /api/v1/users/{id}
```

## 📄 Post Document Example

### 🔎 Fetch posts by user

**Request**

GET `/api/v1/users/{id}/posts`

Example:

GET `http://localhost:8080/api/v1/users/699b5ec2b6a8fc7b1d0359f5/posts`

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

- Find post by ID
- Search by title (case insensitive)
- Full search with:
    - Text (title, body, comments)
    - Date range
    - Combination of `$and` + `$or` operators


**Endpoints:**
```txt 
GET /api/v1/posts/{id}
GET /api/v1/posts/titlesearch?txt=texto
GET /api/v1/posts/fullsearch?txt=texto&minDate=yyyy-MM-dd&maxDate=yyyy-MM-dd
```

## 🔎 Advanced Search (Full Search)

**Implemented using a custom @Query with MongoDB:**

- Case-insensitive regex  
- Minimum and maximum date filtering  
- Search inside embedded fields (comments.text)  
- Combination of `$and` and `$or` operators  

**Example request:**

```txt
GET /api/v1/posts/fullsearch?txt=data&minDate=2026-02-01&maxDate=2026-02-28
```

This request performs a text search for the term "data" in the fields `title`, `body`, and `comments.text`, filtering results within the provided date range using MongoDB logical operators.


**Example response:**

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

## 🛡️ Exception Handling

The project uses `ControllerAdvice` for global exception handling.

**Example error response:**


```json
{
  "timestamp": "2026-02-21T21:30:00Z",
  "error": "Not found",
  "status": 404,
  "message": "Object not found",
  "path": "/api/v1/posts/999"
}
```

## 🧪 Testing the API

The API can be tested using:

- Postman
- HTTPie (terminal)

Example via HTTPie:

```bash
$ http GET :8080/api/v1/users/699b5ec2b6a8fc7b1d0359f6/posts
```


## 📊 Applied Concepts

- Hybrid modeling (embedded + reference)
- DTO Pattern for payload control
- Encapsulation with `Collections.unmodifiableList`
- MongoDB regex queries
- Indexing with `@Indexed`
- API versioning (`/api/v1`)
- Proper HTTP status usage
- `ResponseEntity`
- Java Stream API
- Layered architecture organization


## ▶️ How to Run the Project

### 1️⃣ Clone the repository

```
git clone https://github.com/VictorN7/project-mongodb.git
```

### 2️⃣ Configure MongoDB

**Make sure MongoDB is running locally on the default port:**

```
mongodb://localhost:27017
```

### 3️⃣ Run the application

```
./mvnw spring-boot:run
```

Or run directly from your IDE.

## 📌 Initial Data

The project uses `CommandLineRunner` to automatically populate the database at startup.

Users, posts, and comments are generated automatically.

## 🎯 About the Project

This project demonstrates:

- Efficient NoSQL database modeling
- REST API construction following best practices
- Clear separation of responsibilities using layered architecture
- Advanced querying techniques with MongoDB

## 🚀 Possible Future Improvements

- Implement pagination
- Add Bean Validation
- Add unit tests
- Dockerize the application
- Integrate Swagger / OpenAPI

## 👨‍💻 Author

Victor Hugo Nogueira Santos
Backend Developer
