# Currency API Documentation

## Get Top Currencies Exchange Rate
**Endpoint:** `/currency/top`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required
**Response:**
```json
[
  {
    "currencyCode": "USD",
    "exchangeRate": 1
  },
  {
    "currencyCode": "EUR",
    "exchangeRate": 0.95
  },
  {
    "currencyCode": "TRY",
    "exchangeRate": 36.23
  },
  {...}
]
```
