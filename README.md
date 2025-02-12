# invest-track
A portfolio and stock market tracking app that provides real-time market data and investment performance analysis to help users make informed financial decisions.

## Prerequisites
- Docker
- Docker Compose

## Running with Docker
1. Clone the repository:
```bash
git clone https://github.com/batuhannoz/invest-track
cd invest-track
```

2. Build and start the containers:
```bash
docker compose up --build
```

The application will:
- Build the Spring Boot application
- Create and start MySQL container
- Create necessary database tables
- Start the application
- Listen on port 8080

## Verify Installation
Once started, you can verify the application is running by accessing:
```
http://localhost:8080
```

## Notes
- The MySQL database will be accessible at localhost:3306
- MySQL data is persisted using Docker volumes
- To stop the application use: `docker compose down`
- To remove all data including volumes use: `docker compose down -v`
