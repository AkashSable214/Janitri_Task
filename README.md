Project overview: This project is a Spring Boot-based web application to manage patient information, including adding, updating, and retrieving patient details.

Technology used:
Programming  Language : Java
Framework: Spring Boot
  Database: MySQL
  Other Libraries/Tools: Hibernate, JPA, etc.

API Documentation
Base URL
http://localhost:8080/patients
Endpoints
1. Get all patient
•	URL: /patients
•	Method: GET
•	Description: Fetches all the patient information from the database.
•	Response:

[
    {
        "id": 1,
        "name": "Des",
        "age": 30.0,
        "heartRate": 0,
        "registrationDate": "2024-12-13",
        "userId": 2,
        "userName": "ds214"
    },
    
    {
        "id": 2,
        "name": "Nov",
        "age": 30.0,
        "heartRate": 41,
        "registrationDate": "2024-12-14",
        "userId": 2,
        "userName": "bv215"
    }
]


2. Get item by ID
•	URL: /patients/{id}
•	Method: GET
•	Description: Fetches a specific item by its ID.
•	Response:

{
    "id": 2,
    "name": "Nov",
    "age": 30.0,
    "heartRate": 42,
    "registrationDate": "2024-12-14",
    "userId": 2,
    "userName": "bv215"
}


3. Create a new item
•	URL: /patients
•	Method: POST
•	Request:

{
  "name": "Nov",
  "age": 30.0,
  "heartRate": 42,
  "user": {
    "id": 2
  }
}

•	Response:

{
    "id": 2,
    "name": "Nov",
    "age": 30.0,
    "heartRate": 42,
    "registrationDate": "2024-12-14",
    "user": {
        "id": 2,
        "name": "bv215",
        "email": "ab@gmail.com",
        "password": "123"
    }
}



4. Update an item
•	URL: /patients/{id}
•	Method: PUT
•	Request:


{
  "name": "AS Updated",
  "age": 35,
  "heartRate": 75
}

•	Response:
{
    "id": 2,
    "name": "AS Updated",
    "age": 35.0,
    "heartRate": 75,
    "registrationDate": "2024-12-14",
    "user": {
        "id": 1,
        "name": "A1",
        "email": "abc@gmail.com",
        "password": "123"
    }
}


________________________________________
Additional Information
•	Testing: Unit tests are included in the src/test directory. You can run them using:
mvn test
