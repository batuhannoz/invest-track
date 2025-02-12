# invest-track
A portfolio and stock market tracking app that provides real-time market data and investment performance analysis to help users make informed financial decisions.

## Prerequisites
- Java Development Kit (JDK) 23 or later
- Maven 3.6 or later
- Docker (for MySQL container)

## Setup & Running
1. Clone the repository:
```bash
git clone https://github.com/batuhannoz/invest-track
cd invest-track
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will:
- Automatically start MySQL in a Docker container (using compose.yaml)
- Create necessary database tables
- Start the Spring Boot application
- Listen on port 8080

## Verify Installation
Once started, you can verify the application is running by accessing:
```
http://localhost:8080
```

## Notes
- The MySQL database will be accessible at localhost:3306
- Default database credentials are in application.properties
- The Docker container for MySQL is managed automatically by Spring Boot
