# Wallet API Documentation
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