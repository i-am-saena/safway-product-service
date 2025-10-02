
## ⚠️ Important Note

> - Do not change anything and do not use classes present inside `donottouch` package.
> - The file **`data.sql`** contains insert queries and inserts data into **`H2 DB`**.
> - Once you start the application it will run on port 8080 and H2 DB will get created with data.
> - Enter **http://localhost:8080/h2-console** in browser to access H2 DB.
> - Make sure  **JDBC URL** has value `jdbc:h2:mem:testdb`, use **`username = sa`** and  **`password = sa`** to login to DB.

---

---
### ✅ `Requirements`
You are required to implement a class called CuponServiceCaller in package `servicecaller` that will call
the provided APIs using RestTemplate.
## 🛠️ Tasks

### 1️⃣ 🔧 Task 1: Implement getAllCupons() method
<details>
Description: Create a method in CuponServiceCaller that calls the /api/coupons endpoint to fetch all available coupons.

####

🔁 Sample Request (no request body)
``` 
GET http://localhost:8080/api/coupons
```

📥 Sample Response
```
[
  {
    "couponName": "SAVE10",
    "couponCategory": "Electronics",
    "discountedPrice": 90.0
  },
  {
    "couponName": "FASHION20",
    "couponCategory": "Clothing",
    "discountedPrice": 80.0
  }
]

```
If no coupons exist, the response will be:
```
[]
```
#### 🔹 Write test cases covering all scenarios as well

</details>

### 2️⃣ 🔧 Task 2: Implement getAllCupons() method
<details>
Description: Create a method in CuponServiceCaller that calls the /api/coupons/{cuponName} endpoint 
to fetch coupons by name.

####

🔁 Sample Request (no request body)
``` 
GET http://localhost:8080/api/coupons/SAVE10
```

📥 Sample Response
```
[
  {
    "couponName": "SAVE10",
    "couponCategory": "Electronics",
    "discountedPrice": 90.0
  }
]


```
If no coupons exist, the response will be:
```
[]
```
#### 🔹 Write test cases covering all scenarios as well

</details>

### 3️⃣ 🔧 Task 3: Add create product endpoint

<details>

This module exposes an API endpoint to create and store a new product in the database.

### 🔹 `/api/product/createProduct`

Create a REST API to accept product details and save them to the database. All fields are mandatory,
and category must be one of the allowed values defined in the ItemCategory enum.

---

### 📥 Request Body

All fields are **mandatory**.

| Field    | Type     | Description                                  |
|----------|----------|----------------------------------------------|
| name     | String   | Name of the product                          |
| price    | Double   | Price of the product                         |
| category | String   | Category of the product (must match enum)    |
</details>

### 4️⃣ 🔧 Task 4: Add endpoint to get all items

<details>

### 🔹 `/api/product/getAll`

Create a REST API endpoint to return all products stored in the database.

---

Returns a JSON array of product objects.

### 📦 Sample Response

```json
[
  {
    "itemId": 1,
    "name": "Wireless Mouse",
    "price": 25.99,
    "category": "Electronics"
  },
  {
    "itemId": 2,
    "name": "Men's T-Shirt",
    "price": 15.50,
    "category": "Clothing"
  }
]
```
</details>

### 5️⃣ 🔧 Task 5: 🛒 Prepare Cart API

<details>

---

### 🔹 POST `/api/cart/prepareCart`

This API endpoint allows clients to get cart calculations by supplying a list of item IDs with quantities,
along with an optional coupon code. It returns a summary of the cart with pricing details,
including any applied discount.

---

## 📥 Request Body


| Field     | Type              | Description                                  | Required |
|-----------|-------------------|----------------------------------------------|----------|
| items     | Map<Long, Integer> | A map of `itemId` to quantity                | ✅ Yes   |
| coupon    | String             | Optional coupon code (e.g., "SAVE10")        | ❌ No    |

### 📝 Example Request

```json
{
  "items": {
    "101": 2,
    "102": 1,
    "222": 4,
    "222": 1
  },
  "coupon": "SAVE10"
}
```
### 📝 Example Response

```json
{
  "itemCount": 7,
  "totalPrice": 320.75,
  "discountedPrice": 0.0,
  "finalPrice": 320.75,
  "isCouponApplied": true/false,
  "errorItemIdList": [101, 102],
  "processedItemList": [222, 111],
  "validItemCount": 2,
  "invalidItemCount": 2
}
```

### ⚙️ Logic Summary

- Fetch item details from the database for each itemId.
     - If item id is invalid then set those id's to errorItemIdList.
     - If item is valid then set them to processedItemList.
- Multiply each valid item's price by the given quantity and then calculate their sum to get totalPrice.
- Get sum of invalid items to get validItemCount and sum of valid items will be validItemCount
- If a valid coupon is passed:
- Make an HTTP GET request to /api/coupons/{couponName}.
  - If cupon is found, Use the discountedPrice from the response.
    Calculate finalPrice = totalPrice - discountedPrice.
    isCouponApplied = true
  - If cupon is not found, finalPrice = totalPrice.
    isCouponApplied = false

</details>
