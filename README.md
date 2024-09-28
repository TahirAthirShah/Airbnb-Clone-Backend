# Travelers Accommodations Service

## Overview
The Travelers Accommodations Service is an online marketplace for rental properties, built using Java, Spring Boot, Spring Security, MySQL, and AWS. This application provides a seamless experience for users to browse, book, and manage rental properties.

## Features
- **RESTful APIs**: Developed robust RESTful APIs to handle various functionalities of the rental marketplace.
- **Booking Module**: Implemented a booking system that allows users to reserve rental properties efficiently.
- **User Authentication & Authorization**: Utilized Spring Security to implement secure authentication and authorization mechanisms.
- **Secure Data Practices**: Employed JSON Web Tokens (JWT) for secure user sessions and password hashing for protecting user credentials.
- **Third-Party Integrations**:
  - **SMS Notifications**: Integrated with a third-party SMS API to send notifications to users.
  - **File Storage**: Utilized Amazon S3 for storing property images and user-uploaded files.
- **User Experience Enhancements**:
  - **Review System**: Implemented a comprehensive review system for properties, allowing users to share their experiences.
  - **Favoriting Feature**: Added the ability for users to favorite properties for easier access later.

## Technologies Used
- **Java**
- **Spring Boot**
- **Spring Security**
- **MySQL**
- **AWS (Amazon S3)**
- **Git**

## Getting Started

### Prerequisites
- Java 11 or higher
- MySQL Server
- AWS Account (for S3 storage)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Airbnb-Clone-Backend.git
Navigate to the project directory:

cd Travelers-Accommodations-Service
Configure your database connection in src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/yourdbname
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
Set up AWS S3 credentials in your environment variables or in the application.properties.

Run the application:

./mvnw spring-boot:run
API Documentation
Refer to the API documentation for detailed information on how to use the endpoints.

Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.


Feel free to modify any sections to better fit your project’s details or personal preferences!


