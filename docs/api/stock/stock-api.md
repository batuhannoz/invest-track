# Stock API Documentation

## Search Stock

**Endpoint:** `/stock/search`

**Method:** `GET`

**Query Parameters:**
- `keyword`: The keyword to search for stocks.

**Response:**
```json
"Search results as a string"
```

## Get Company Info

**Endpoint:** `/stock/{symbol}/company`

**Method:** `GET`

**Path Parameters:**
- `symbol`: The stock symbol.

**Response:**
```json
"Company information as a string"
```

## Get Stock Information

**Endpoint:** `/stock/{symbol}/price`

**Method:** `GET`

**Path Parameters:**
- `symbol`: The stock symbol.

**Response:**
```json
"Stock price information as a string"
```
