# E-Commerce Microservices Application

A **microservices-based e-commerce application** designed to streamline online shopping processes. This project demonstrates the implementation of a scalable, modular, and distributed system using **Spring Boot**, **Spring Cloud**, and other modern technologies.

---

## **Features**

- **Customer Management**: Register and manage customer details (name, email, address).
- **Product Catalog**: Browse and manage products with details like name, description, price, and availability.
- **Order Management**: Place, track, and manage orders with real-time updates.
- **Payment Processing**: Secure payment processing with support for multiple payment methods.
- **Notifications**: Send email notifications for order confirmations, payment status, and more.
- **Scalable Architecture**: Built using microservices for independent scaling and deployment.
- **Asynchronous Communication**: Uses **RabbitMQ** for reliable message queuing between services.
- **Service Discovery**: **Eureka Server** for dynamic service registration and discovery.
- **API Gateway**: Centralized entry point for all microservices with routing and load balancing.
- **Distributed Tracing**: Integrated with **Zipkin** for monitoring and debugging microservices.

---

## **Technologies Used**

### **Backend**
- **Spring Boot 3**: Core framework for building microservices.
- **Spring Cloud**: For service discovery, configuration, and API gateway.
- **Spring Data JPA**: For database interactions.
- **Spring Security**: For secure authentication and authorization.
- **RabbitMQ**: Message broker for asynchronous communication.
- **Eureka Server**: Service discovery and registration.
- **Zipkin**: Distributed tracing for monitoring microservices.
- **Docker**: Containerization for easy deployment.

### **Database**
- **PostgreSQL**: Relational database for storing customer, product, and order data.
- **MongoDB**: NoSQL database for storing notifications and other unstructured data.

### **Tools**
- **Docker Compose**: For orchestrating multi-container applications.
- **Postman**: For API testing during development.

---

## **Architecture Overview**

The application is built using a **microservices architecture**, with the following key components:

1. **Customer Service**: Manages customer information and authentication.
2. **Product Service**: Handles product catalog and inventory management.
3. **Order Service**: Manages order creation, tracking, and updates.
4. **Payment Service**: Processes payments and updates payment status.
5. **Notification Service**: Sends email notifications for order and payment updates.
6. **API Gateway**: Routes requests to the appropriate microservices.
7. **Eureka Server**: Service discovery for dynamic service registration.
8. **Config Server**: Centralized configuration management for all microservices.

---

## **Installation**

### **Prerequisites**
- **Java 17**: Ensure Java 17 is installed.
- **Docker**: Install Docker and Docker Compose for containerization.
- **PostgreSQL**: Set up a PostgreSQL database.
- **RabbitMQ**: Install RabbitMQ for message queuing.

### **Steps to Run the Application**

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/zohidjonyuldashev/e-commerce.git
   cd e-commerce
   ```
2. **Set Up Environment Variables**
  - Create a .env file in the root directory and add the following:
  ```
  DATABASE_URL=your_postgres_url
  DATABASE_USERNAME=your_postgres_username
  DATABASE_PASSWORD=your_postgres_password
  RABBITMQ_HOST=your_rabbitmq_host
  RABBITMQ_USERNAME=your_rabbitmq_username
  RABBITMQ_PASSWORD=your_rabbitmq_password
  ```
3. **Run Docker Compose**
   ```
   docker-compose up -d
   ```
4. **Access the Application**
   - The API Gateway will be available at http://localhost:8080.
   - Eureka Server dashboard: http://localhost:8761.
   - Zipkin for distributed tracing: http://localhost:9411.
## API Documentation
   The API documentation is available via Swagger UI:
   - Access Swagger UI at http://localhost:8080/swagger-ui.html.
## Contributing
   Contributions are welcome! If you'd like to contribute to this project, please follow these steps:
   1. Fork the repository.
   2.  Create a new branch (git checkout -b feature/YourFeatureName).
   3.  Commit your changes (git commit -m 'Add some feature').
   4.  Push to the branch (git push origin feature/YourFeatureName).
   5.  Open a pull request.
## License
   This project is licensed under the MIT License. See the [LICENSE](https://github.com/zohidjonyuldashev/e-commerce/blob/master/LICENSE) file for details.
   
