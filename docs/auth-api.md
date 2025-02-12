# Authentication API Documentation

## Sign Up
Register a new user in the system.

**Endpoint:** `POST /auth/signup`

### Request Body
```json
{
    "email": "string",
    "password": "string",
    "name": "string"
}
```

### Response
Returns the created User object.
```json
{
    "id": "number",
    "name": "string",
    "email": "string",
    "createdAt": "timestamp",
    "updatedAt": "timestamp"
}
```

### Status Codes
- 200 OK: Successfully registered
- 400 Bad Request: Invalid input
- 409 Conflict: Email already exists

## Login
Authenticate a user and receive a JWT token.

**Endpoint:** `POST /auth/login`

### Request Body
```json
{
    "email": "string",
    "password": "string"
}
```

### Login Response
```json
{
    "accessToken": "string",
    "refreshToken": "string",
    "expiresIn": "number"
}
```

### Status Codes
- 200 OK: Successfully authenticated
- 401 Unauthorized: Invalid credentials
- 400 Bad Request: Invalid input

### Authentication
After login, use the JWT token in the Authorization header for protected endpoints:
```
Authorization: Bearer <token>
```

### Example Requests

**Sign Up:**
```bash
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123",
    "name": "John Doe"
  }'
```

**Login:**
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

## Refresh Token
Get a new access token using a refresh token.

**Endpoint:** `POST /auth/refresh`

### Request Body
```json
{
    "refreshToken": "string"
}
```

### Response
```json
{
    "accessToken": "string",
    "expiresIn": "number"
}
```

### Status Codes
- 200 OK: Successfully refreshed access token
- 401 Unauthorized: Invalid or expired refresh token

### Example
```bash
curl -X POST http://localhost:8080/auth/refresh \
  -H "Content-Type: application/json" \
  -d '{
    "refreshToken": "your-refresh-token-here"
  }'
```

### Notes
- The refresh token has a longer lifespan than the access token
- Use the refresh token to obtain a new access token when the old one expires
- If the refresh token is expired, user needs to log in again
- Keep the refresh token secure and never send it in API requests except to the refresh endpoint
