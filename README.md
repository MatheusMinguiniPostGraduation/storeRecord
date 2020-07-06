# Store Record
 Backend application that aims to keep track of costumer purchases and billing for a clothing store
 
## Stack
- Java 8
- Maven
- Spring Boot
- Spring Data

## Documetation
### Docker
[Docker official site](https://www.docker.com/) 

- Download Docker imagem following these links steps: 
```
https://hub.docker.com/_/mysql
```
- Run docker image:
```
docker run --name store-db -p "3306:3306" -e MYSQL_ROOT_PASSWORD=[YOUR PASSWORD] -d mysql
```
- Connect in the Mysql using the password
```
docker exec -it [CONTAINER_NAME] mysql -uroot -p
```
