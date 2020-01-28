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
https://hub.docker.com/r/mysql/mysql-server/
```
- Run docker image:
```
docker run --name=mysql-server -p "3306:3306" -d mysql/mysql-server:5.5
```
- Get generated password
```
docker logs mysql-server 2>&1 | grep GENERATED
```
- Connect in the Mysql using the generated password
```
docker exec -it mysql-server mysql -uroot -p
```
### MySQL
[MySQL official site](https://dev.mysql.com/doc/)

- Change root password
```
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('new_password')
```
- In order to be able to connect into the database from outside the 
container. To get it done, simply apply the followings commands once connected 
into the container (Not sure why)
```
GRANT ALL ON *.* to root@'172.17.0.1' IDENTIFIED BY 'Newton';
FLUSH PRIVILEGES;
```