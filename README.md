# invest-track
A portfolio and stock market tracking app that provides real-time market data and investment performance analysis to help users make informed financial decisions.

## Database
- [Database Schema](docs/db/db-schema.png)

## API Documentation
- Auth
  - [Authentication Flow](docs/api/auth/auth-flow.png)
  - [Authentication API](docs/api/auth/auth-api.md)
- [Currency API](docs/api/currency/currency-api.md)
- [Stock API](docs/api/stock/stock-api.md)
- [User API](docs/api/user/user-api.md)
- [Wallet API](docs/api/wallet/wallet-api.md)
- [Watchlist API](docs/api/watchlist/watchlist-api.md)
- [Transaction API](docs/api/transaction/transaction-api.md)


## Prerequisites
- Docker
- Docker Compose

## Running the Application

### Development Mode (MySQL only)
If you want to run only MySQL and develop the application locally:
```bash
docker compose up -d
```

Then run the Spring Boot application using your IDE or:
```bash
./mvnw spring-boot:run
```

### Complete Stack Test Mode
To run both MySQL and the Spring Boot application in containers:
```bash
docker compose -f compose.test.yaml up --build
```

## Verify Installation
Once started, you can verify the application is running by accessing:
```
http://localhost:8080
```

## Notes
- The MySQL database will be accessible at localhost:3306
- MySQL data is persisted using Docker volumes
- Development mode: `docker compose down`
- Production mode: `docker compose -f compose.prod.yaml down`
- To remove all data including volumes add `-v` flag to down commands
