# Log Ingestor

Log Ingestor is a Spring Boot application designed to ingest logs over HTTP and store them in a MySQL database. This README provides a detailed guide on setting up, running, and using the Log Ingestor.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Setup](#setup)
- [Usage](#usage)
  - [Ingesting Logs](#ingesting-logs)
  - [Searching Logs](#searching-logs)
- [System Architecture](#system-architecture)
- [Bonus Features](#bonus-features)

## Getting Started

### Prerequisites

Before you begin, ensure you have the following tools installed:

- [Spring Tool Suite](https://spring.io/tools) or any Java IDE
- [MySQL](https://www.mysql.com/) (installed and running)

### Setup

1. **Clone the repository:**

    ```bash
    git clone <repository-url>
    ```

2. **Open the project in your preferred IDE.**

3. **Configure MySQL:**
   - Create a MySQL database named log_database.
   - Update `src/main/resources/application.properties` with your MySQL credentials.

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/log_database
     spring.datasource.username=root
     spring.datasource.password=
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.jpa.hibernate.ddl-auto=update
     server.port=3000
     // Set the server port according to your system.
     ```

     

4. **Run the application.**

## Usage

### Ingesting Logs

To ingest logs, send a POST request to `http://localhost:3000/logs/ingest` with a JSON payload.You can use postman or any other, Here's an example:

```json
{
    "level": "error",
    "message": "Critical error occurred",
    "resourceId": "server-456",
    "timestamp": "2024-05-17T14:30:00Z",
    "traceId": "def-123-456",
    "spanId": "span-789",
    "commit": "a1b2c3d4",
    "metadata": {
        "parentResourceId": "server-789"
    },
    "date": null
}
```

### Searching Logs

Search logs by sending a GET request to http://localhost:3000/logs/search with query parameters for filtering.You can use postman or any other, Here's an example:
http://localhost:3000/logs/search?level=level
http://localhost:3000/logs/search?searchTerm=example&startDate=2023-11-17

I have also design frontend Where You can ingest log and search by using Seacrhing options and terms, you can also add more search field in option.


## 5. System Architecture
Log Ingestor uses the following technologies:
```
Spring Boot for the backend.
MySQL for data storage.
Features
Ingest logs over HTTP.
Store logs in a MySQL database.
Search logs based on various parameters.
Bonus Features
Search within date ranges.
Combine multiple filters.

```


 
## 6. Bonus Features:
Search within Date Ranges:

Users can search for logs within specific date ranges, enhancing the flexibility of log retrieval.
Combine Multiple Filters:

The application supports combining multiple filters, allowing users to perform more complex searches based on various parameters simultaneously.
The README effectively communicates the essential information for getting started, and the features and bonus features provide a comprehensive overview of the application's capabilities. If there are specific aspects you would like to emphasize or if you have additional details to include, feel free to let me know!

I have attached some Output Images in OutputImage folder, You can also check it for reference.

I've attached Output Images in the OutputImage folder for your reference.
Please review and let me know if there are any further adjustments or additions you'd like!