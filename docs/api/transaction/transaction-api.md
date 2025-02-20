# Transaction API Documentation

## Get User Transactions

**Endpoint:** `GET /transaction`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
[
    {
        "id": 1,
        "stock": {
            "symbol": "AAPL",
            "name": "Apple Inc.",
            "exchange": "NASDAQ Global Select"
        },
        "transactionType": "BUY",
        "quantity": 10.00,
        "price": 150.25,
        "createdAt": "2024-02-14T10:00:00Z",
        "updatedAt": "2024-02-14T10:00:00Z"
    }
]
```

## Get User Transactions By Stock

**Endpoint:** `GET /transaction/{stockSymbol}`

**Method:** `GET`

**Path Parameters:**
- `stockSymbol`: Symbol of the stock (e.g., AAPL)

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
[
    {
        "id": 1,
        "stock": {
            "symbol": "AAPL",
            "name": "Apple Inc.",
            "exchange": "NASDAQ Global Select"
        },
        "transactionType": "BUY",
        "quantity": 10.00,
        "price": 150.25,
        "createdAt": "2024-02-14T10:00:00Z",
        "updatedAt": "2024-02-14T10:00:00Z"
    }
]
```

## Buy Stock

**Endpoint:** `POST /transaction/{stockSymbol}/buy`

**Method:** `POST`

**Path Parameters:**
- `stockSymbol`: Symbol of the stock to buy

**Query Parameters:**
- `quantity`: Number of shares to buy (decimal value)

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
    "id": 1,
    "stock": {
        "symbol": "AAPL",
        "name": "Apple Inc.",
        "exchange": "NASDAQ Global Select"
    },
    "transactionType": "BUY",
    "quantity": 10.00,
    "price": 150.25,
    "createdAt": "2024-02-14T10:00:00Z",
    "updatedAt": "2024-02-14T10:00:00Z"
}
```

## Sell Stock

**Endpoint:** `POST /transaction/{stockSymbol}/sell`

**Method:** `POST`

**Path Parameters:**
- `stockSymbol`: Symbol of the stock to sell

**Query Parameters:**
- `quantity`: Number of shares to sell (decimal value)

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
    "id": 1,
    "stock": {
        "symbol": "AAPL",
        "name": "Apple Inc.",
        "exchange": "NASDAQ Global Select"
    },
    "transactionType": "SELL",
    "quantity": 5.00,
    "price": 155.50,
    "createdAt": "2024-02-14T10:00:00Z",
    "updatedAt": "2024-02-14T10:00:00Z"
}
```

## Error Responses

All endpoints may return the following error responses:

### 400 Bad Request
```json
{
    "error": "Invalid request",
    "message": "Quantity must be positive"
}
```

### 404 Not Found
```json
{
    "error": "Not Found",
    "message": "Stock not found: INVALID"
}
```

### 409 Conflict
```json
{
    "error": "Insufficient Balance",
    "message": "Insufficient funds to complete the transaction"
}
```

### 500 Internal Server Error
```json
{
    "error": "Internal Server Error",
    "message": "An unexpected error occurred"
}
```
