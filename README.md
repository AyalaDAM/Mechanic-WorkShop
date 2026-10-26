# Mechanic Workshop Management System

An integral desktop application built with JavaFX and Hibernate for managing the daily operations of a mechanic workshop. This project follows the Model-View-Controller (MVC) architecture and uses a containerized MySQL database to ensure an isolated and standardized environment.

## Key Features

*   **Comprehensive Entity Management:** Full CRUD operations and relational mapping for Workshops, Employees, Clients, Vehicles, Services, Parts (Inventory), and Appointments.
*   **Automated Data Seeding:** Upon startup, the `DatabaseManager` automatically populates the database with dummy data (clients, vehicles, scheduled appointments, parts, etc.) so the application can be tested immediately without manual data entry.
*   **Clean Teardown:** The application automatically clears the database records upon a graceful shutdown to maintain a pristine testing state for future executions.
*   **Relational Mapping:** Advanced ORM implementation handling One-to-Many and Many-to-Many entity relationships using Hibernate.

## Tech Stack

*   **Frontend:** JavaFX with FXML and custom CSS styling.
*   **Backend:** Java, Hibernate ORM (v6.3), Jakarta Persistence API.
*   **Database:** MySQL 8 running via Docker.
*   **Build Tool:** Maven.

## Prerequisites

*   JDK 25
*   Docker and Docker Compose

## Getting Started

**1. Start the Database**
Navigate to the Docker directory and spin up the MySQL container. The provided `docker-compose.yml` and `init.sql` files will automatically create the `hibernate` database and configure the required user credentials.
```bash
cd "Docker MySQL"
docker-compose up -d
```

**2. Run the Application**
Return to the root directory and use the Maven Wrapper to compile and launch the JavaFX application.
```bash
cd ..
./mvnw clean javafx:run
```
*(On Windows, you can use `mvnw.cmd clean javafx:run`)*

**3. Access the System**
The application will launch a login screen. Click the login button to access the main dashboard and interact with the pre-loaded data.

## Architecture Overview

The codebase is organized into functional layers separating UI from business logic:
*   **`models/`**: Contains the JPA/Hibernate annotated entities (`Cliente`, `Vehiculo`, `Cita`, `Pieza`, `Servicio`, `Taller`, `Trabajador`).
*   **`resources/.../listViews/` & `addViews/`**: FXML files for the user interface, separating data tables from creation forms.
*   **`listControllers/` & `addControllers/`**: JavaFX controllers handling the view logic, table population, and user interactions.
*   **`DatabaseManager.java`**: Centralized Hibernate session management, generic CRUD operations, and automated data initialization.
