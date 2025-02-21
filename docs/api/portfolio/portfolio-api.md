# Portfolio API Documentation

## Get User Portfolio
**Endpoint:** `/portfolio`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
[
  {
    "id": 1,
    "stock": {
      "id": 1,
      "symbol": "AAPL",
      "name": "Apple Inc.",
      "currentPrice": 150.50
    },
    "quantity": 10.00,
    "averagePrice": 145.30,
    "createdAt": "2023-08-15T10:30:00",
    "updatedAt": "2023-08-15T10:30:00"
  },
  {
    "id": 2,
    "stock": {
      "id": 2,
      "symbol": "GOOGL",
      "name": "Alphabet Inc.",
      "currentPrice": 2800.00
    },
    "quantity": 5.00,
    "averagePrice": 2750.00,
    "createdAt": "2023-08-15T10:30:00",
    "updatedAt": "2023-08-15T10:30:00"
  }
]
```
