# Wallet API Documentation

## Get Wallet
**Endpoint:** `/wallet`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "balance": 50.00,
  "currency": "USD"
}
```

## Deposit to Wallet
**Endpoint:** `/wallet/deposit`

**Method:** `POST`

**Request Parameters:**
- `amount` (BigDecimal): The amount to deposit.

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "balance": 100.00,
  "currency": "USD"
}
```
## Withdraw from Wallet
**Endpoint:** `/wallet/withdraw`

**Method:** `POST`

**Request Parameters:**
- `amount` (BigDecimal): The amount to withdraw.

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "balance": 50.00,
  "currency": "USD"
}
```

## Get Transaction History
**Endpoint:** `/wallet/transactions`

**Method:** `GET`

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
[
  {
    "amount": 100.00,
    "type": "DEPOSIT",
    "description": "Deposit to wallet",
    "balanceAfterTransaction": 595.83,
    "createdAt": "2023-08-10T14:30:00Z"
  },
  {
    "amount": -50.00,
    "type": "WITHDRAWAL",
    "description": "Withdrawal from wallet",
    "balanceAfterTransaction": 495.83,
    "createdAt": "2023-08-10T15:00:00Z"
  },
  {
    "amount": 245.83,
    "balanceAfterTransaction": 545.83,
    "type": "STOCK_SALE",
    "description": "Stock sale: AAPL",
    "createdAt": "2025-02-21T07:22:20.957+00:00"
  },
  {
    "amount": -577.70,
    "balanceAfterTransaction": 300,
    "type": "STOCK_PURCHASE",
    "description": "Stock purchase: AAPL",
    "createdAt": "2025-02-21T07:22:00.974+00:00"
  }
]
```