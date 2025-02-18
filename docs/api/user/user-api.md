# User API Documentation

## Profile Operations

### Update User

**Endpoint:** `/user/update`

**Method:** `PUT`

**Headers:**
- `Authorization`: Bearer token required

**Request Body:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "createdAt": "2023-01-01T00:00:00Z",
  "updatedAt": "2023-01-01T00:00:00Z"
}
```

### Get User Profile

**Endpoint:** `/user/profile`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "createdAt": "2023-01-01T00:00:00Z",
  "updatedAt": "2023-01-01T00:00:00Z"
}
```

## Card Operations

### List User Cards

**Endpoint:** `/user/card`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
[
  {
    "id": 1,
    "cardNumber": "4532123456789012",
    "cardHolderName": "John Doe",
    "expirationDate": "12/27",
    "cvv": "123",
    "createdAt": "2023-01-01T00:00:00Z",
    "updatedAt": "2023-01-01T00:00:00Z"
  }
]
```

### Get Specific Card

**Endpoint:** `/user/card/{cardId}`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required

**Parameters:**
- `cardId`: Card ID (path parameter)

**Response:**
```json
{
  "id": 1,
  "cardNumber": "4532123456789012",
  "cardHolderName": "John Doe",
  "expirationDate": "12/27",
  "cvv": "123",
  "createdAt": "2023-01-01T00:00:00Z",
  "updatedAt": "2023-01-01T00:00:00Z"
}
```

### Create New Card

**Endpoint:** `/user/card`

**Method:** `POST`

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "id": 1,
  "cardNumber": "4532123456789012",
  "cardHolderName": "John Doe",
  "expirationDate": "12/27",
  "cvv": "123",
  "createdAt": "2023-01-01T00:00:00Z",
  "updatedAt": "2023-01-01T00:00:00Z"
}
```

### Delete Card

**Endpoint:** `/user/card/{cardId}`

**Method:** `DELETE`

**Headers:**
- `Authorization`: Bearer token required

**Parameters:**
- `cardId`: Card ID (path parameter)

**Response:**
- Status: 200 OK
