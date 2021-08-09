# x-y-z-corporation-company(system design)

## Technology used
-	Java: Spring boot framework
-	Database: mysql
-	Documentation: Swagger API Documentation
- Rate limiter to perfom throttling

## End point


| Methods       | Endpoints                      | Actions                   |
| ------------- |:----------------------------:  | -------------------------:|
| POST          |/api/notification/createClient  | Register new Client       |
| GET           |/api/notification/clientRequest | Find Client information   |
| PUT           |/api/notification/sendRequest   | send notification request |
| PUT           |/api/notification/renewRequest  | update Request            |

## project installation
- install docker in your local machine and apache maven
- switch to project directory
- `mvn clean install` to download dependencies
- `mvn spring-boot:run` to run project

## deployment process
- `docker pull mysql:latest` to create database image
- `-docker network create my-network` to create network connection
- `docker container run --name mysqldb --network mysqldb -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=notifier
 -e MYSQL_PASSWORD=admin -d mysql:latest` to create mysql container
- `docker image build -t notification .` to create project image
- `docker container run --network my-network --name notification-container -p 9090:9090 -d notification` to create project container
- `docker container logs -f notification-container` to run project

- And use swagger documentation UI for full documentations and api testing `http://localhost:9090/swagger-ui.html`
