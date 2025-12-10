# Food Ordering Application

A full-stack food ordering web application built with Java Spring Boot, MongoDB, and HTML/CSS/JavaScript.



## Project Overview

This is a complete food ordering system that allows:
- Users to browse food items, add to cart, place orders, and track order history
- Admins to manage food items and update order statuses
- Authentication with secure password encryption
- Real-time cart management and order processing

---

## Technologies Used

### Backend:
- Java 21
- Spring Boot 3.2.0 – REST API framework
- Spring Data MongoDB – Database operations
- Spring Security – Authentication and password encryption
- Maven – Dependency management

### Frontend:
- HTML5 – Structure
- CSS3 – Styling (Grid, Flexbox)
- JavaScript (ES6+) – Dynamic functionality
- Fetch API – HTTP requests

### Database:
- MongoDB – NoSQL database

---

## Project Structure

food-ordering-learning/
│
├── backend/
│ ├── src/main/java/com/foodapp/
│ │ ├── model/
│ │ ├── repository/
│ │ ├── service/
│ │ ├── controller/
│ │ ├── config/
│ │ └── FoodOrderingApplication.java
│ ├── src/main/resources/
│ │ └── application.properties
│ └── pom.xml
│
├── frontend/
│ ├── css/style.css
│ ├── js/
│ │ ├── auth.js
│ │ ├── app.js
│ │ ├── cart.js
│ │ ├── orders.js
│ │ └── admin.js
│ ├── login.html
│ ├── index.html
│ ├── cart.html
│ ├── orders.html
│ └── admin.html
│
└── documentation/
├── README.md
├── QUICK-START.md
├── COMPLETE-PROJECT-GUIDE.md
├── TESTING-GUIDE.md
├── INTERVIEW-PREP-GUIDE.md
├── LEARNING-GUIDE.md
├── REPOSITORY-GUIDE.md
├── SERVICE-GUIDE.md
├── CONTROLLER-GUIDE.md
└── PROJECT-PROGRESS.md



## Features

### User Features:
- User registration with encrypted passwords
- User login with authentication
- Browse all available food items
- Search food by name or description
- Filter food by category
- Add items to shopping cart
- Manage cart (increase/decrease quantity, remove items)
- Place orders with automatic total calculation
- View order history with status tracking
- Cancel pending orders

### Admin Features:
- Admin login with role-based access
- Add new food items
- Update food details
- Delete food items
- View all orders from all users
- Update order status (PENDING → CONFIRMED → DELIVERED)
- Manage food availability

### Technical Features:
- RESTful API design
- Password encryption with BCrypt
- CORS configuration for frontend–backend communication
- Error handling with try–catch blocks
- Data validation
- Responsive UI design
- LocalStorage for cart persistence



## Quick Start

### Prerequisites:
- Java 21 or higher
- MongoDB installed and running
- Maven
- VS Code or IntelliJ IDEA

### 1. Clone/Download Project

cd food-ordering



### 2. Start MongoDB

Windows
net start MongoDB

Mac/Linux
brew services start mongodb-community

text

### 3. Run Backend

cd backend
mvn spring-boot:run



Or run `FoodOrderingApplication.java` from the IDE.

### 4. Run Frontend

- Install “Live Server” extension in VS Code.
- Right-click `frontend/login.html`.
- Select “Open with Live Server”.

### 5. Access Application

- Frontend: http://127.0.0.1:5500  
- Backend: http://localhost:8080  
- MongoDB: localhost:27017

---

## REST API Endpoints

### Authentication

POST /api/users/register – Register new user
POST /api/users/login – Login user



### Food Management

GET /api/foods – Get all foods
GET /api/foods/available – Get available foods
POST /api/foods – Add food (Admin)
PUT /api/foods/{id} – Update food (Admin)
DELETE /api/foods/{id} – Delete food (Admin)



### Order Management

POST /api/orders – Place order
GET /api/orders/user/{userId} – Get user orders
GET /api/orders – Get all orders (Admin)
PUT /api/orders/{id}/status – Update status (Admin)
DELETE /api/orders/{id}/cancel – Cancel order



## Database Schema

### Users Collection

{
"_id": "ObjectId",
"name": "String",
"email": "String",
"password": "String",
"phone": "String",
"address": "String",
"role": "String"
}

text

### Foods Collection

{
"_id": "ObjectId",
"name": "String",
"description": "String",
"price": "Number",
"category": "String",
"imageUrl": "String",
"available": "Boolean"
}

text

### Orders Collection

{
"_id": "ObjectId",
"userId": "String",
"items": [
{
"foodId": "String",
"foodName": "String",
"quantity": "Number",
"price": "Number"
}
],
"totalAmount": "Number",
"status": "String",
"orderDate": "DateTime",
"deliveryAddress": "String"
}




## Architecture

Frontend (HTML/CSS/JS) → Controllers → Services → Repositories → MongoDB
┌─────────────┐
│  Frontend   │  HTML/CSS/JavaScript
│  (Browser)  │  Fetch API
└──────┬──────┘
       │ HTTP Requests (JSON)
       ↓
┌─────────────┐
│ Controller  │  @RestController
│   Layer     │  Receives HTTP requests
└──────┬──────┘
       │
       ↓
┌─────────────┐
│  Service    │  @Service
│   Layer     │  Business logic
└──────┬──────┘
       │
       ↓
┌─────────────┐
│ Repository  │  @Repository
│   Layer     │  Database operations
└──────┬──────┘
       │
       ↓
┌─────────────┐
│  MongoDB    │  NoSQL Database
│  Database   │  Collections: users, foods, orders
└─────────────┘

1. **Full-Stack Development** - Frontend + Backend + Database
2. **RESTful API Design** - Proper HTTP methods and status codes
3. **MVC Architecture** - Separation of concerns
4. **Authentication** - Secure password handling
5. **CRUD Operations** - Create, Read, Update, Delete
6. **Error Handling** - Try-catch blocks and validation
7. **Responsive Design** - Mobile-friendly UI
8. **State Management** - LocalStorage for cart
 Security Features

-  Password encryption with BCrypt
-  Role-based access control (USER/ADMIN)
-  CORS configuration
-  Input validation
-  Error message sanitization

  Future Enhancements

-  Payment gateway integration (Razorpay/Stripe)
-  Real-time order tracking with WebSockets
-  Email notifications
-  Image upload for food items
-  User reviews and ratings
-  Advanced search and filters
-  Order history export (PDF)
-  Deployment to cloud (AWS/Heroku)
-  Unit and integration tests

roject Statistics

- **Lines of Code**: 2000+
- **REST APIs**: 15+
- **Database Collections**: 3
- **Frontend Pages**: 5
- **Technologies**: 8+
- **Development Time**: 3-4 months
- **Completion**: 100%

  
