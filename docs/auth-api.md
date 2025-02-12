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
```json
{
    "id": 2,
    "name": "string",
    "email": "string",
    "password": "encrypted-password",
    "createdAt": "2025-02-12T09:16:13.642+00:00",
    "updatedAt": "2025-02-12T09:16:13.642+00:00",
    "enabled": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "accountNonLocked": true,
    "authorities": [],
    "username": "string"
}
```

### Example
```bash
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123",
    "name": "John Doe"
  }'
```

## Login
Authenticate an existing user and receive a JWT token.

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
    "expiresIn": "number"
}
```

### Example
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

### Notes
- All requests must include `Content-Type: application/json` header
- The token received from login should be included in subsequent requests as a Bearer token:
  `Authorization: Bearer <token>`
- Token expiration time (expiresIn) is returned in milliseconds
