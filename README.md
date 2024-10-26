# Customer API

A simple Java API for managing customers using Spring Boot and H2 in-memory database.

## Prerequisites

- Java 21
- Gradle
- IDE (e.g., IntelliJ IDEA, Eclipse)

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/KamtoOkafor/Dunder-Mifflin-Paper-Company-Customer.git
   cd customer
2. **Build the application**
    ```bash
   ./gradlew build

3. **Run the application**
    ```bash
   ./gradlew bootRun

4. **Testing**
    ```bash
    ./gradlew test

5. **To access the in memory database**
    ```bash
    http://localhost:8080/h2-console.
   
    JDBC URL: jdbc:h2:mem:testCustomerDB
    Username: sa
    Password: (n/a) (change this in application.yml if needed)

### How to Access the Application
- After running the application, you can access the API at `http://localhost:8080/api/customers`.
- You can visually view the data in the H2 console at `http://localhost:8080/h2-console`.
  ![img_6.png](img_6.png)
  ![img_5.png](img_5.png)


- You can access the swagger page at `http://localhost:8080/swagger-ui/index.html`.
  ![img_4.png](img_4.png)

### Using postman
![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_3.png](img_3.png)

### Note
- You can change the database password in `application.yml` under `spring.datasource.password`.
- The H2 console will allow you to view and manipulate the in-memory database visually.

With this setup, you should have a fully functional Java API for managing customers with unit and integration tests included! Let me know if you have any questions or need further assistance!

### If you were to scale your customer API service to millions of customers how would you build it differently?
To scale and secure the application for a high volume of users, I would implement the following improvements:

1. **Use a Persistent SQL Database:** Replace the in-memory database with a production-grade SQL database like PostgreSQL or MySQL to ensure data persistence, consistency, and scalability.

2. **Add a Caching Layer:** Implement a caching layer (e.g., Redis or Memcached) with an appropriate Time-to-Live (TTL) configuration to reduce database load by caching frequently accessed data, such as lists of customers or individual customer details.

3. **Enhance Security with Role-Based Access Control (RBAC):** Introduce RBAC to manage permissions effectively, ensuring only authorized users can perform critical actions like deleting customer records. Add a confirmation screen to the delete endpoint to prevent accidental deletions.

4. **Deploy on Cloud Infrastructure with Database Replication:** Host the application on scalable cloud infrastructure (AWS or Azure) and distribute read-heavy workloads across database replicas to improve response times and support a large user base.

5. **Enable Auto-Scaling:** Configure auto-scaling groups within the cloud environment (AWS or Azure) to dynamically add or remove instances based on traffic demands, ensuring optimal performance and cost-efficiency.

6. **Implement Rate Limiting, Throttling, and Tracking:** Enforce rate limits and throttling on API endpoints to protect against abuse and excessive usage, and track endpoint access to monitor user behavior and improve application reliability.

7. **Emit Metrics to a Monitoring Dashboard:** Set up a comprehensive monitoring system to emit key performance metrics (e.g., response times, error rates) to a real-time dashboard, enabling prompt detection and resolution of issues.

8. **Expand Test Coverage:** Enhance test coverage by incorporating additional testing scenarios beyond the “happy path,” including edge cases and failure conditions, to ensure robust and reliable functionality across different conditions.
