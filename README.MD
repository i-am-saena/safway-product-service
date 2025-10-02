# 🚗 Vehicle Search Service

A **Spring Boot service** that manages vehicle owner details.

---

## ⚠️ Important Note

> - The file **`data.sql`** contains insert queries and inserts data into **`H2 DB`**.
> - Once you start the application it will run on port 8080 and H2 DB will get created with data.
> - Enter **http://localhost:8080/h2-console** in browser to access H2 DB.
> - Make sure  **JDBC URL** has value `jdbc:h2:mem:testdb`, use **`username = sa`** and  **`password = sa`** to login to DB.

---

## 🛠️ Tasks

### 1️⃣ `GET /api/vehicle/search`
<details>
Create a search API to return vehicle details. User can apply various filters while searching for vehicles. If no filter is applied then return
all vehicles available in  DB. If no record is present in DB or No matching record is found with supplied filter then return error msg
with status code as 404 with an error msg "No vehicle details found with supplied filter"

#### 🔹 Inputs and Response
- `registrationNumber` → (Optional)  Return vehicles matching with registrationNumber if supplied.
-  `owner_name` → (Optional)  Return vehicles matching with registrationNumber if supplied.
- `vehicle_type` → (Optional)  Return vehicles matching with registrationNumber if supplied.
- `owner_name` → (Optional)  Return vehicles matching with registrationNumber if supplied.
- `insured` (`true`/`false`, optional) →
    - If `true`, include vehicles with insured as true.
    - If `false`, include vehicles with insured as false.
    - If not supplied include vehicles irrespective of its insured or not.


#### 🔹 Write test cases covering all scenarios as well

</details>

### 2️⃣ `Scheduler To Update Customer DB`
<details>
Create one method which will execute every minute, it will fetch all records from vehicle table and insert record into
customer table.
- For each owner we will create only one record in DB. For example if vehicle table has more than one record with
  owner_name as `Dipak`, will insert only one record in customer DB.

####
-  Customer table will have 2 columns: →
    - I`customer_name` ->  will hold vehicle owners name from vehicle table
    - `is_premium_customer` ->  will contain true/false
        - `true`- If customer has 3 or more cars.
        - `false`- If customer has less than 3 cars.
####
- If no record is present in `customer` table for a customer then insert a new record
####
- If customer record is present for a customer then update the value of `is_premium_customer` column
  true or false depending upon the number of cars he owns.


#### 🔹 Write test_ cases covering all scenarios as well

</details>