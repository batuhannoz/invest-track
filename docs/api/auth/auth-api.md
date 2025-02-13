# Authentication API Documentation

## Sign Up
Register a new user in the system.

**Endpoint:** `POST /auth/signup`

### Request Body
```json
{
    "email": "string",
    "password": "string",
    "name": "string",
    "surname": "string"
}
```

### Response
Returns the created User object.
```json
{
    "id": "number",
    "name": "string",
    "surname": "string",
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

### Response
```json
{
    "token": "string",
    "expiresIn": "number",
    "refreshToken": "string",
    "refreshExpiresIn": "string"
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
    "name": "John"
    "surname": "Doe",

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
Get a new access token using the current token before it expires.

**Endpoint:** `POST /auth/refresh`

### Request Headers
```
Authorization: Bearer <current-token>
```

### Response
```json
{
    "token": "string",
    "expiresIn": "number"
}
```

### Status Codes
- 200 OK: Successfully refreshed token
- 401 Unauthorized: Invalid or expired token

### Example
```bash
curl -X POST http://localhost:8080/auth/refresh \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIs..."
```

### Notes
- The refresh token endpoint should be called before the current token expires
- The new token will have a new expiration time
- If the current token is already expired, user needs to log in again
