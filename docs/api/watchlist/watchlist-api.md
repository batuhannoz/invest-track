# Watchlist API Documentation

## Create Watchlist

**Endpoint:** `POST /watchlist`

**Method:** `POST`

**Query Parameters:**
- `name`: Name of the watchlist

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
    "id": 1,
    "name": "My Watchlist",
    "stocks": [],
    "createdAt": "2024-02-14T10:00:00Z",
    "updatedAt": "2024-02-14T10:00:00Z"
}
```

## Get User Watchlists

**Endpoint:** `GET /watchlist`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
[
    {
        "id": 1,
        "name": "My Watchlist",
        "stocks": [
            {
                "symbol": "AAPL",
                "name": "Apple Inc."
            }
        ],
        "createdAt": "2024-02-14T10:00:00Z",
        "updatedAt": "2024-02-14T10:00:00Z"
    }
]
```

## Add Stock to Watchlist

**Endpoint:** `/watchlist/{watchlistId}/stock/{symbol}`

**Method:** `POST`

**Path Parameters:**
- `watchlistId`: ID of the watchlist
- `symbol`: Stock symbol to add

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
    "id": 1,
    "name": "My Watchlist",
    "stocks": [
        {
            "symbol": "AAPL",
            "name": "Apple Inc."
        }
    ],
    "createdAt": "2024-02-14T10:00:00Z",
    "updatedAt": "2024-02-14T10:00:00Z"
}
```

## Remove Stock from Watchlist

**Endpoint:** `/watchlist/{watchlistId}/stock/{symbol}`

**Method:** `DELETE`

**Path Parameters:**
- `watchlistId`: ID of the watchlist
- `symbol`: Stock symbol to remove

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
    "id": 1,
    "name": "My Watchlist",
    "stocks": [],
    "createdAt": "2024-02-14T10:00:00Z",
    "updatedAt": "2024-02-14T10:00:00Z"
}
```

## Update Watchlist Name

**Endpoint:** `/watchlist/{watchlistId}`

**Method:** `PUT`

**Path Parameters:**
- `watchlistId`: ID of the watchlist

**Query Parameters:**
- `name`: New name for the watchlist

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
    "id": 1,
    "name": "Updated Watchlist Name",
    "stocks": [],
    "createdAt": "2024-02-14T10:00:00Z",
    "updatedAt": "2024-02-14T10:00:00Z"
}
```

## Get Watchlist Details

**Endpoint:** `/watchlist/{watchlistId}`

**Method:** `GET`

**Path Parameters:**
- `watchlistId`: ID of the watchlist

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
    "id": 1,
    "name": "My Watchlist",
    "stocks": [
        {
          "id": 1,
          "symbol": "AAPL",
          "name": "Apple Inc.",
          "exchange": "NASDAQ Global Select",
          "imageUrl": "https://images.financialmodelingprep.com/symbol/AAPL.png",
          "createdAt": "2025-02-18T12:01:21.849122",
          "updatedAt": "2025-02-18T12:01:21.849181"
        }
    ],
    "createdAt": "2024-02-14T10:00:00Z",
    "updatedAt": "2024-02-14T10:00:00Z"
}
```

## Delete Watchlist

**Endpoint:** `/watchlist/{watchlistId}`

**Method:** `DELETE`

**Path Parameters:**
- `watchlistId`: ID of the watchlist

**Headers:**
- `Authorization`: Bearer token required