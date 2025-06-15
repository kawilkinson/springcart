# Spring Cart

## 📄 What is Spring Cart?
Spring Cart is a backend REST API paired with a MySQL database that includes user accounts and a shopping cart all secured using JWT for access/refresh tokens and Spring Boot Security. 

## 🏆 Goal
The main goal of this project was for me to practice OOP with Java and to familiarize myself with Spring Boot, the focus was on the backend which is why there is no frontend for this web application.

## ✏️ Getting Started 
This project is currently hosted on Railway! To test out the API just make calls to the endpoints starting with this URL: 
```
https://springcart-production.up.railway.app
```

## 📜 API documentation
All API documentation can be found [here](https://springcart-production.up.railway.app/swagger-ui/index.html)

## 💻 Local Setup
For getting this to run locally on your machine, once you've cloned the repository to your machine go to the .env.example file and configure the following environment variables:

### JWT_SECRET
Generate a 32 length secure random key via this command and use it for the `JWT_SECRET`:
```
openssl rand -base64 32
```
If openssl is not available, go to generate-random.org, click on Strings > API Tokens, and generate a secure token.

### STRIPE_SECRET_KEY
1. First create a free account at [stripe.com](https://stripe.com)
2. On your dashboard, go to Developers > API Keys. You can use the search bar for quick access.
3. Copy the value of the Secret Key.

### STRIPE_WEBHOOK_SECRET_KEY
1. Install the Stripe CLI [here](https://docs.stripe.com/stripe-cli)
2. Log in and start the webhook listener:
```
stripe login
stripe listen --forward-to http://localhost:8080/checkout/webhook
```
3. Copy the signing secret from the terminal output and use it as the value for `STRIPE_WEBHOOK_SECRET_KEY`

### MySQL
Since this backend relies on a database to handle and store data you'll also want to install MySQL to your machine and make sure the port, username, and password line up with these fields in the `src/main/resources/application-dev.yaml` file or change them to match what you set it as:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/store_api?createDatabaseIfNotExist=true
    username: root
    password: Password!123
```
No need to worry about populating the database apart from making a user account, there are Flyway migration scripts that will do that for you.

## ▶️ How To Run
Since this is a Maven project, it uses a Maven wrapper to make starting the project seamless and easy, in the root run this command if on Mac/Linux:
```
./mvnw spring-boot:run
```
If on Windows:
```
mvnw.cmd spring-boot:run
```

Once fully running the app will be accessible at `http://localhost:8080`

## 🔍 API Flow
Below is some steps to smoothly interact with most of the endpoints:

### 1. Get All Products (no login needed)
```
GET /products
```

### 2. Create a Shopping Cart (no login needed)
```
POST /carts
```
**Response body**
```json
{
    "cartId": "cart ID"
}
```

### 3. Add Item to Cart (no login needed)
```
POST /carts/{cartId}/items
```
**Request body**
```json
{
    "productId": 1
}
```

### 4. Register a New User
```
POST /users
```
**Request body**
```json
{
    "name": "Bob Ross",
    "email": "bobross@domain.com",
    "password": "123456"
}
```

### 5. Login
```
POST /auth/login
```

**Request body**
```json
{
    "email": "bobross@domain.com",
    "password": "123456"
}
```
**Response body**
```json
{
    "token": "json-web-token"
}
```

### 6. Checkout (login needed)
```
POST /checkout
```
**Headers**
```
Authorization: Bearer json-web-token
```
**Request body**
```json
{
    "cartId": "cart ID"
}
```
When you check out you will get a Stripe checkout URL, this is a test URL so no real transactions are happening, feel free to use these payment details:
```
Card: 4242 4242 4242 4242
Expiry: Any future date
CVC: Any 3 digits
```

### 7. Webhook and Order Status
Once you've done a fake payment Stripe triggers a webhook call to here:
```
POST /checkout/webhook
```
The backend server listens for this event and the order status will become updated depending on a successful or failed payment.