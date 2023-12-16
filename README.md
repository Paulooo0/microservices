<h1 align="center">Spring Microservices</h1>

Microservices project, simulating a sale system

### Summary
- [The Project](#the-project)
- [Features](#features)
- [Technologies](#technologies)

## The Project

This project was created to demonstrate software architecture, design pattern and integration. Using modern tecnologies and practices.
The application flux is create clients and products to realize sells, with authentications and validations between microsservices and databases, making then independent.

## Features

* Hidden authentication. The database credentials are armazened in a private repository, anyone sensitive credentials can be found in the entire code.
* HTTP comunication. This allows to use any database, language, etc.
* Config Server. Here has been armazened the core of the application, and this is the server that is connected with the private repository.
* Service registry. Netflix Eureka is a framework that can register the microservices and discover than, and has load balancer, check healt and etc. I setted the microservices ports manually for demonstration pourposes.
* Isolation. With HTTP comunication, each service and database are isolated and works for itself in cases that not requires to get data from other services. This means that if a service breaks, the rest of the application don't stop.

## Technologies

* Java
* Spring Boot
* Spring Config Server
* Netflix Eureka
* MongoDB
* PostgreSQL
* Docker

## Examples

* Use a API client to make the requisitions, such as [Postman](https://www.postman.com) or [APIDog](https://apidog.com/?utm_source=google_search&utm_medium=g&utm_campaign=18544428894&utm_content=153517438552&utm_term=apidog&gad=1&gclid=CjwKCAjw-KipBhBtEiwAWjgwrDGW3LOJOzo7sHEut6kaW9wXcKOh_U_DZoSVMImBJoHvZkzunzeC2xoCvN4QAvD_BwE).

Creating a client:
<br>
`http://localhost:8081/client`
```
{
  "first_name": "paulo",
  "last_name": "h nunes",
  "cpf": 11111111111,
  "balance": 100.0
}
```

Creating a product:
<br>
`http://localhost:8082/product`
```
{
  "name": "prod1",
  "price": 10.0,
  "description": "description"
}
```

Opening a sale:
<br>
`http://localhost:8083/sale`
```
{
    "clientCpf": "11111111111",
    "saleProductsDtoList": [
        {
            "name": "prod1",
            "price": 10.00,
            "quantity": 1
        }
    ]
}
```

All services has standard endpoints, like `http://localhost:8083/sale/all` to get all sales, or `http://localhost:8081/client/cpf/11111111111` to find a client by cpf.
