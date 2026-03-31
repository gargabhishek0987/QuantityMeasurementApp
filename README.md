# ⚖️ Quantity Measurement System
 
<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-8.x-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" />
  <img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" />
</p>
 
<p align="center">
  A Spring Boot REST API for performing operations on quantities like <strong>Length</strong>, <strong>Weight</strong>, <strong>Volume</strong>, and <strong>Temperature</strong>.
</p>
 
---
 
## 📋 Table of Contents
 
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Setup Instructions](#-setup-instructions)
- [API Endpoints](#-api-endpoints)
- [Swagger UI](#-swagger-ui)
- [Supported Measurement Types](#-supported-measurement-types)
- [Future Improvements](#-future-improvements)
 
---
 
## ✨ Features
 
- 🔄 Compare two quantities
- 🔁 Convert units
- ➕ Add quantities
- ➖ Subtract quantities
- ➗ Divide quantities
- 📜 Operation history tracking
- ⚠️ Error handling with logging
- 📊 Operation count & filters
 
---
 
## 🛠️ Tech Stack
 
| Layer        | Technology              |
|--------------|-------------------------|
| Backend      | Spring Boot             |
| Database     | MySQL                   |
| ORM          | Spring Data JPA         |
| API Docs     | Swagger (OpenAPI)       |
| Build Tool   | Maven                   |
 
---
 
## 📁 Project Structure
 
```
src/
└── main/
    ├── java/com/app/quantitymeasurement/
    │   ├── controller/       # REST Controllers
    │   ├── service/          # Business Logic
    │   ├── repository/       # JPA Repositories
    │   ├── model/            # Entity Classes
    │   └── unit/             # Unit Enums & Definitions
    └── resources/
        └── application.properties
pom.xml
```
 
---
 
## 🚀 Setup Instructions
 
### 1. Clone the Repository
 
```bash
git clone <repo-url>
cd QuantityMeasurementApp
```
 
### 2. Configure the Database
 
Update `src/main/resources/application.properties`:
 
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/quantityMeasurement
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
 
### 3. Run the Application
 
```bash
mvn spring-boot:run
```
 
The server will start at `http://localhost:8080`
 
---
 
## 📡 API Endpoints
 
**Base URL:** `http://localhost:8080/api/quantities`
 
| Method | Endpoint                         | Description              |
|--------|----------------------------------|--------------------------|
| POST   | `/add`                           | Add two quantities       |
| POST   | `/subtract`                      | Subtract quantities      |
| POST   | `/divide`                        | Divide quantities        |
| POST   | `/compare`                       | Compare two quantities   |
| POST   | `/convert`                       | Convert a unit           |
| GET    | `/history/operation/{operation}` | Get history by operation |
| GET    | `/count/{operation}`             | Get count of an operation|
| GET    | `/history/errored`               | Get error history        |
 
---
 
### 📌 Example Request — Add Quantities
 
**`POST /add`**
 
```json
{
  "thisQuantityDTO": {
    "value": 5,
    "unit": "FEET",
    "measurementType": "LengthUnit"
  },
  "thatQuantityDTO": {
    "value": 12,
    "unit": "INCHES",
    "measurementType": "LengthUnit"
  }
}
```
 
---
 
## 📖 Swagger UI
 
Explore and test all endpoints via the interactive API documentation:
 
```
http://localhost:8080/swagger-ui/index.html
```
 
---
 
## 📐 Supported Measurement Types
 
| Type              | Example Units                        |
|-------------------|--------------------------------------|
| `LengthUnit`      | `FEET`, `INCHES`, `METERS`, `CM`     |
| `WeightUnit`      | `KG`, `GRAMS`, `POUNDS`              |
| `VolumeUnit`      | `LITERS`, `ML`, `GALLONS`            |
| `TemperatureUnit` | `CELSIUS`, `FAHRENHEIT`, `KELVIN`    |
 
> ⚠️ **Note:** Both quantities in an operation must be of the **same measurement type**.
 
---
 
## 🔮 Future Improvements
 
- [ ] JWT Authentication & Authorization
- [ ] Deploy on AWS (EC2 / Elastic Beanstalk)
- [ ] Angular Frontend Integration
- [ ] Analytics Dashboard
 
---
 
<p align="center">Made with ❤️ using Spring Boot</p>
 
