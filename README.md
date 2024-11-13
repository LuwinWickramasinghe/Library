# BookShelf

BookShelf is a digital library management application designed to facilitate book cataloging, lending, and tracking within a community or organization. It features a Spring Boot backend API to handle data storage, book records, and user management.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- Add, update, and delete book entries
- User authentication and role management
- Search and filter functionality for books
- Borrowing and returning book management

## Technologies Used

- **Backend:** Spring Boot
- **Database:** H2 Database (for testing), MySQL/PostgreSQL (production-ready)
- **IDE Configuration:** IntelliJ IDEA project files included for quick setup

## Installation

### Prerequisites

- **Java 11** or higher
- **Maven** (for building and running the backend)

### Backend Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/BookShelf.git
   cd BookShelf/02-backend/springboot-lib
   ```

2. Build and run the Spring Boot application:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. The backend should now be running on `http://localhost:8080`.

4. [Optional] Configure the database connection settings in `application.properties` if using a different database.

## Usage

- **API Endpoints**: 
  - To add or view book records, send requests to the `/books` endpoint.
  - User authentication and role-based access endpoints can be accessed via `/auth`.
  
- **Postman Collection**:
  - You may use a tool like Postman to interact with the API for testing. Make sure to set up authentication headers if required.

## Project Structure

- `02-backend/springboot-lib`: Contains the Spring Boot backend, with controllers, services, and models organized into packages.
- `.idea`: IntelliJ IDEA project files.
- `.gitignore`: Git ignore file for handling unnecessary files.
