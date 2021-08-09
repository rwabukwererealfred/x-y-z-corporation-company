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
| PUT           |/api/notification/sendRequest   | make notification request         |
| PUT           |/api/notification/renewRequest  | update Request                    |
