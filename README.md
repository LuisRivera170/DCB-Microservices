# Daily Code Buffer Microservices Course

# Development

### Microservices connections

| Microservice     | Port |
|------------------|------|
| Service registry | 8761 |
| Config service   | 9296 |
| Product service  | 8080 |
| Order service    | 8081 |
| Payment service  | 8082 |

### Resources connections

> Use the docker-compose file to build the required resources

| Tool      | Type                       | Port |
|-----------|----------------------------|------|
| ProductDB | Mysql-database             | 3306 |
| OrderDB   | Mysql-database             | 3307 |
| PaymentDB | Mysql-database             | 3308 |
| Zipkin    | Distributed tracing system | 9411 |