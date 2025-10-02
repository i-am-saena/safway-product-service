# 📮 Address Search Service

A **Spring Boot service** that manages addresses and supports **blacklisting logic**.

---

## ⚠️ Important Note

> - The class **`BlacklistAddress`** already contains logic for handling blacklisted addresses.
> - **Do not modify** the `getAll()` method inside `BlacklistAddress`.
> - You may add new classes or update existing ones if required, but the **core logic** inside `BlacklistAddress.getAll()` should remain unchanged.

---

## 🛠️ Tasks

### 1️⃣ `GET /api/address/search`

#### 🔹 Inputs (query params, optional)
- `postalCode` → If supplied, return only addresses with the same postal code.
- `blacklistedAddress` (`true`/`false`, optional) →
    - If `true`, include blacklisted addresses in the response.
    - If not supplied or `false`, exclude blacklisted addresses.

#### 🔹 Behavior
- If `postalCode` is provided → filter by postal code.
- If not provided → return **all available addresses**.
- Respect the `blacklistedAddress` flag as per above rules.

<details>
<summary>📌 Example Request/Response</summary>  

**Request:**
```http GET /api/address/search?postalCode=456%20VTG&blacklistedAddress=true ```

**Sample Response:**
```[
{
    "postalCode": "456 VTG",
    "street": "Beta Street",
    "city": "Some City",
    "blacklisted": true
  }
]
```
#### 🔹 Write test cases covering all scenarios as well

