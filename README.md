# "Boffee"
## Contact information

Thank you for checking out my project!
Feel free to contact me through any means:
- Marten Kustav Klais
- 53089133
- marten.kustav@gmail.com
- https://www.linkedin.com/in/marten-kustav-klais/


## Introduction

Boffee is a web application crafted to manage and interact with user profiles within a club.
With its user-friendly interface, Boffee makes registration and profile management a breeze, 
inviting users to delve into a world of personal expression and discovery.

## Features

### User Authentication & Registration
- Secure login & registration system for users.

### Authority and Role Management
- Advanced role-based access control. Users can be assigned different authorities and roles, affecting what they can see and do within the application.

### Dynamic Friendship Management
- Users can manage friendships directly from their profile or a dedicated friendship page.
- The system supports adding friends, accepting friend requests, and viewing current friends and pending requests.
- Friendship status is dynamically updated and reflected in the user interface, enhancing user interaction and engagement.

### Personalized Profiles
- Users can create and update personalized profiles, enhancing their ability to express themselves and engage with the community.

### Read & Recommend
- A platform for book enthusiasts, Boffee encourages users to share their reading experiences, recommend books, and contribute to a growing database of literary treasures.

### Community Engagement
- Users can explore profiles of fellow bookworms, learn about their book preferences, and connect with a community of like-minded individuals.

### Review and Influence
- Reviewers can contribute by giving thoughtful reviews on books, influencing the reading choices of the community.

### Deletion Strategies for Boffee Entities
- The application ensures that the deletion of entities such as authors, genres, and books does not leave orphan records or an inconsistent state. Database cascading and application-level safeguards are employed to:
  - Maintain Data Integrity
  - Preserve User Experience


## Currently in Development

### TODO List

- **Password Security Enhancement**
  - Implement BCrypt for encoding and storing passwords in the database securely.

- **Endpoint Security**
  - Implement access control to secure user-specific endpoints, ensuring users can only access their own profile information and restricting unauthorized access to sensitive data.

- **Authentication and Authorization Improvements**
  - Enhance authentication and authorization mechanisms by implementing role-based access control (RBAC) to manage user permissions effectively.
  - Secure critical endpoints based on user roles to prevent unauthorized actions.

- **Improved Error Handling for Login**
  - Develop and integrate more informative error handlers for login failures to enhance user experience and troubleshooting.

### Recently Completed Items

- [x] **Liquibase Migration (13/02/2024)**
  - Successfully integrated Liquibase to manage database schema changes effectively. This ensures seamless updates and version control for the database, facilitating easier evolution of the database schema as the application develops.

- [x] **Dynamic Friendship Model**
  - Introduced a comprehensive friendship management system, supporting friend requests, a friend-list, and an interactive system for adding friends.
  - Established a two-way agreement mechanism for friendships, ensuring mutual consent.
  - Implemented dynamic updates of friendship status in the user interface, significantly enhancing user interaction and engagement within the platform.

## Technologies Used

The project leverages a robust stack of technologies to deliver a seamless and secure user experience:

- **Backend**
  - Java: Core programming language.
  - Spring Boot: Primary framework for creating stand-alone, production-grade Spring-based applications easily.
  - Spring Data JPA: For database integration and manipulation using Java Persistence API.
  - Spring Security: For authentication and authorization.
  - Spring Boot Logging: For logging purposes.

- **Database**
  - MySQL: The primary relational database management system.

- **Development Tools**
  - Liquibase: Tracking, managing, and applying database schema changes.
  - Spring Boot Devtools: To increase development productivity.
  - AspectJ: For aspect-oriented programming including logging and transaction management.

- **Frontend**
  - Thymeleaf: Server-side Java template engine for web applications.
  - HTML/CSS: Markup and styling.
  - JavaScript: Client-side scripting.
  - Bootstrap: Frontend framework for responsive design.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for testing purposes.

**Note**: I have migrated database setup process to use Liquibase for managing schema changes and versioning. 
For those who prefer or need to set up the database without Liquibase, I have included a folder with full SQL scripts for MySQL Workbench.
This can be a useful alternative for initial setup or in scenarios where Liquibase is not available. 
However, Liquibase is still recommended!

Remember,
if you opt to use the SQL scripts,
ensure that your database state matches what Liquibase would set up to avoid discrepancies between environments.

#### 1) Prerequisites:

- Java 17 or newer
- Maven
- MySQL Server & Workbench for database management
- Lombok Plugin (with annotation processing enabled in your IDE settings)
- Liquibase for managing database migrations

#### 2) Set up the Database

##### 1. Start your MySQL server
   - Ensure the server is running and accessible
##### 2. Create a new database
   - This can be done with the provided script in the project folder.
     - 'sql-database-setup' -> '00_00 - create-database.sql'
   - Or just create a new schema in the workbench.
##### 3. Configure application properties
   - Navigate to 'src/main/resources/application.properties' file
   - Update this file with your MySQL database connection details, including the database name, username, and password.
##### 4. Liquibase Migration
  - Liquibase is configured to run automatically when the application starts.
It will create and populate tables.
##### 5. Run the application

#### 3) Run the application & access it

- Open your web browser and navigate to 'http://localhost:8080'
- You can now register as a new user or log in with a existing user from the database
  - For example:
  - un: Laura pw: test123 (authority: admin)
  - un: Mary pw: test123 (authority: reader)

## Contributing

**Note:** This project is under development, and aspects of it may change. Your feedback is invaluable to me.

I welcome all forms of feedback and contributions. If you have suggestions, questions, or want to contribute to the project, please don't hesitate to reach out.

- **LinkedIn**: [Marten Kustav Klais](https://www.linkedin.com/in/marten-kustav-klais/)
- **Email**: [marten.kustav@gmail.com](mailto:marten.kustav@gmail.com)

I look forward to hearing from you!

