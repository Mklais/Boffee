# "Boffee"
## Contact information

Thank you for checking out my project!
Feel free to contact me through any means:
- Marten Kustav Klais
- 53089133
- marten.kustav@gmail.com
- https://www.linkedin.com/in/marten-kustav-klais/

## Todo List / Currently in development:

- Securing endpoints
- **Endpoint Security:** Implementing access control to secure user-specific endpoints.
  - Ensuring users can only access their own profile information.
  - Restricting unauthorized access to sensitive user data.
- **Authentication and Authorization:** Enhancing user authentication and authorization mechanisms.
  - Implementing role-based access control to manage different user permissions.
  - Securing critical endpoints based on user roles.
- Login invalid error handlers

## Recently completed TODO List:

- **Dynamic Friendship model**
  - Manage friendships
  - system supported friend system with friend requests, friend-list and a interactive system for adding friends
  - 2-way agreement for a friendship
  - Dynamically updated friendship statud reflected in the user interface, enhancing user interaction and engagement.

## Introduction

Boffee is a web application crafted to manage and interact with user profiles within a club. 
With its user-friendly interface, Boffee makes registration and profile management a breeze,
inviting users to delve into a world of personal expression and discovery.

## Features

- Personalized Profiles
  - Users can create comprehensive profiles, highlighting their personality, hobbies and interests. 
  - This makes for a more connected and engaging community experience.
- Read & Recommend
  - A platform for book enthusiasts, Boffee encourages users to share their reading experiences, recommend books and contribute to a growing database of literary treasures.
- Community Engagement
  - Users can explore profiles of fellow bookworms, learn about their book preferences
- Review and influence
  - Reviewers can contribute by giving thoughtful reviews on books, influencing the reading choices of the community
  - 

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Boot Devtools
- Spring Boot logging
- AspectJ
- MySQL
- Thymeleaf
- HTML/CSS
- JavaScript
- Bootstrap

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for testing purposes.

#### 1) Ensure you have the following installed:

- Java 17
- Maven
- MySQL Server & Workbench for database management
- Lombok Plugin (Enable annotation processing in IDE settings)

#### 2) Set up the Database

See the sql-scripts folder in the project folder for more.
- Start your MySQL server
- Create a new database
- Update the 'application.properties' file with your MySQL username & password

#### 3) Run the application & access it

- Open your web browser and navigate to 'http://localhost:8080'
- You can now register as a new user or log in with a existing user from the database
  - For example:
  - un: Laura pw: test123 (authority: admin)
  - un: Mary pw: test123 (authority: reader)

## Contributing

Note: This is a developing project, things and topics are a subject to change
All feedback is welcomed and I invite you to message me via linkedin:
https://www.linkedin.com/in/marten-kustav-klais/
or send me email
marten.kustav@gmail.com
