# Stock API Documentation

## Search Stock

**Endpoint:** `/stock/search`

**Method:** `GET`

**Query Parameters:**
- `keyword`: The keyword to search for stocks.

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
[
  {
    "symbol": "AAPL",
    "name": "Apple Inc.",
    "currency": "USD",
    "stockExchange": "NASDAQ Global Select",
    "exchangeShortName": "NASDAQ"
  }
]
```

## Get Company Info

**Endpoint:** `/stock/{symbol}/company`

**Method:** `GET`

**Path Parameters:**
- `symbol`: The stock symbol.

**Headers:**
- `Authorization`: Bearer token required


**Response:**
```json
[
  {
    "symbol": "AAPL",
    "price": 241.53,
    "beta": 1.24,
    "volAvg": 49078624.1,
    "mktCap": 3628287813000,
    "lastDiv": 1,
    "range": "164.08-260.1",
    "changes": 4.66,
    "companyName": "Apple Inc.",
    "currency": "USD",
    "cik": "0000320193",
    "isin": "US0378331005",
    "cusip": "037833100",
    "exchange": "NASDAQ Global Select",
    "exchangeShortName": "NASDAQ",
    "industry": "Consumer Electronics",
    "website": "https://www.apple.com",
    "description": "Apple Inc. designs, manufactures, and markets smartphones, personal computers, tablets, wearables, and accessories worldwide. The company offers iPhone, a line of smartphones; Mac, a line of personal computers; iPad, a line of multi-purpose tablets; and wearables, home, and accessories comprising AirPods, Apple TV, Apple Watch, Beats products, and HomePod. It also provides AppleCare support and cloud services; and operates various platforms, including the App Store that allow customers to discover and download applications and digital content, such as books, music, video, games, and podcasts, as well as advertising services include third-party licensing arrangements and its own advertising platforms. In addition, the company offers various subscription-based services, such as Apple Arcade, a game subscription service; Apple Fitness+, a personalized fitness service; Apple Music, which offers users a curated listening experience with on-demand radio stations; Apple News+, a subscription news and magazine service; Apple TV+, which offers exclusive original content; Apple Card, a co-branded credit card; and Apple Pay, a cashless payment service, as well as licenses its intellectual property. The company serves consumers, and small and mid-sized businesses; and the education, enterprise, and government markets. It distributes third-party applications for its products through the App Store. The company also sells its products through its retail and online stores, and direct sales force; and third-party cellular network carriers, wholesalers, retailers, and resellers. Apple Inc. was founded in 1976 and is headquartered in Cupertino, California.",
    "ceo": "Mr. Timothy D. Cook",
    "sector": "Technology",
    "country": "US",
    "fullTimeEmployees": "164000",
    "phone": "(408) 996-1010",
    "address": "One Apple Park Way",
    "city": "Cupertino",
    "state": "CA",
    "zip": "95014",
    "dcfDiff": 95.48029,
    "dcf": 146.04971420588694,
    "image": "https://images.financialmodelingprep.com/symbol/AAPL.png",
    "ipoDate": "1980-12-12",
    "defaultImage": false,
    "isEtf": false,
    "isActivelyTrading": true,
    "isAdr": false,
    "isFund": false
  }
]
```

## Get Stock Information

**Endpoint:** `/stock/{symbol}/price`

**Method:** `GET`

**Path Parameters:**
- `symbol`: The stock symbol.

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
[
  {
    "symbol": "AAPL",
    "name": "Apple Inc.",
    "price": 241.53,
    "changesPercentage": 1.96732,
    "change": 4.66,
    "dayLow": 235.57,
    "dayHigh": 242.3399,
    "yearHigh": 260.1,
    "yearLow": 164.08,
    "marketCap": 3628287813000,
    "priceAvg50": 240.3224,
    "priceAvg200": 222.2454,
    "exchange": "NASDAQ",
    "volume": 53614054,
    "avgVolume": 49078624.1,
    "open": 236.91,
    "previousClose": 236.87,
    "eps": 6.97,
    "pe": 34.65,
    "earningsAnnouncement": "2025-04-30T10:59:00.000+0000",
    "sharesOutstanding": 15022100000,
    "timestamp": 1739494798
  }
]
```

## Get Stock Historical Data (Last Week)

**Endpoint:** `/stock/{symbol}/historical/last-week`

**Method:** `GET`

**Path Parameters:**
- `symbol`: The stock symbol.

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "symbol": "AAPL",
  "historical": [
    {
      "date": "2025-02-13",
      "open": 236.91,
      "high": 242.34,
      "low": 235.57,
      "close": 241.53,
      "adjClose": 241.53,
      "volume": 52138208,
      "unadjustedVolume": 52138208,
      "change": 4.62,
      "changePercent": 1.95,
      "vwap": 239.0875,
      "label": "February 13, 25",
      "changeOverTime": 0.0195
    },
    {...}
  ]
}
```

## Get Stock Historical Data (Last Month)

**Endpoint:** `/stock/{symbol}/historical/last-month`

**Method:** `GET`

**Path Parameters:**
- `symbol`: The stock symbol.

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "symbol": "AAPL",
  "historical": [
    {
      "date": "2025-02-13",
      "open": 236.91,
      "high": 242.34,
      "low": 235.57,
      "close": 241.53,
      "adjClose": 241.53,
      "volume": 52138208,
      "unadjustedVolume": 52138208,
      "change": 4.62,
      "changePercent": 1.95,
      "vwap": 239.0875,
      "label": "February 13, 25",
      "changeOverTime": 0.0195
    },
    {...}
  ]
}
```

## Get Stock Historical Data (Last Year)

**Endpoint:** `/stock/{symbol}/historical/last-year`

**Method:** `GET`

**Path Parameters:**
- `symbol`: The stock symbol.

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "symbol": "AAPL",
  "historical": [
    {
      "date": "2025-02-13",
      "open": 236.91,
      "high": 242.34,
      "low": 235.57,
      "close": 241.53,
      "adjClose": 241.53,
      "volume": 52138208,
      "unadjustedVolume": 52138208,
      "change": 4.62,
      "changePercent": 1.95,
      "vwap": 239.0875,
      "label": "February 13, 25",
      "changeOverTime": 0.0195
    },
    {...}
  ]
}
```

## Get Stock Historical Data (Last Five Years)

**Endpoint:** `/stock/{symbol}/historical/last-five-years`

**Method:** `GET`

**Path Parameters:**
- `symbol`: The stock symbol.

**Headers:**
- `Authorization`: Bearer token required

**Response:**
```json
{
  "symbol": "AAPL",
  "historical": [
    {
      "date": "2025-02-13",
      "open": 236.91,
      "high": 242.34,
      "low": 235.57,
      "close": 241.53,
      "adjClose": 241.53,
      "volume": 52138208,
      "unadjustedVolume": 52138208,
      "change": 4.62,
      "changePercent": 1.95,
      "vwap": 239.0875,
      "label": "February 13, 25",
      "changeOverTime": 0.0195
    },
    {...}
  ]
}
```
