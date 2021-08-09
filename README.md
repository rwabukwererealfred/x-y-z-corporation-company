# x-y-z-corporation-company(system design)

## Technology used
-	Java: Spring boot framework
-	Database: mysql
-	Documentation: Swagger API Documentation
- Rate liminter to perfom throttling

## End point

| Methods       | Endpoints                      | Actions                           |
| ------------- |:----------------------------:  | ---------------------------------:|
| POST          |/api/notification/createClient  | Register new Client               |
| GET           |/api/notification/clientRequest | Find Client information           |
| PUT           |/api/notification/sendRequest   | send notification request         |
| PUT           |/api/notification/renewRequest  | update Request                    |

## project installation
-install docker in your local machine
-swith to project directory and write below command in your terminal

- `docker pull mysql:latest`
- `-docker network create my-network`
- `docker container run --name mysqldb --network mysqldb -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=notifier -e MYSQL_PASSWORD=admin -d mysql:latest`
- `docker image build -t notification .`
- `docker container run --network my-network --name notification-container -p 9090:9090 -d notification`
- `docker container logs -f notification-container`

