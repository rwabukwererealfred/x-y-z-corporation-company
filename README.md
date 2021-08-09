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
- install apache maven
- switch to project directory
- `mvn clean install` to download dependencies
- `mvn spring-boot:run` to run project

- And use swagger documentation UI for full documentations and api testing `http://localhost:9090/swagger-ui.html`
