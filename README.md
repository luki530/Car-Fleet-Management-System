# Car-Fleet-Management-System
It is an application that allows company (e.g. car rental, transport company etc.) to manage their fleet of vehicles. They can view logs of each car and driver. 
## How does it work ?
Every vehicle has installed logging device which sends their current state (location, speed, acceleration, driver, fuel level, errors and other informations) to http server. Those logs are saved in database. They can be accessed by company employee via the website.
## Technologies
### Java
Main back-end language.
#### Maven
Managing libraries used in project.
#### SpringBoot
Responsible for HTTP server.
#### Jakarta Persistence Api (JPA)
ORM standard
##### Hibernate
Framework for mapping Java objects to MySQL database.
#### JSON Web Token (JWT)
User authentication and autorization for application security. 
#### JustSend SMS API
Sending SMS to verify user phone number while registration.
### MySQL
Database service used to store objects.
### HTTP
Application communication protocol.
### Google Cloud Platform
Front-end and back-end server hosting.
#### App Engine
Engine for running applications.
#### Cloud SQL
Hosting MySQL database.
#### Cloud Build
Dynamic and automatic code compilation and deployment after each push to master on GitHub.
#### Google Maps
View vehicle on map.
### Angular
Main front-end technology.
#### TypeScript
Language used in Angular logic.
#### HTML
Display data in views.
#### CSS
Styling components.
#### Angular Material
Designing eye-friendly website.
## Tools
### IntelliJ
Java IDE
### Visual Studio Code
Angular IDE
### Postman
Sending raw http request. 
### Fiddler
Catching http request to analyze.
### GIT
Used for team-development. 
#### GitHub
Choosen GIT hosting.
