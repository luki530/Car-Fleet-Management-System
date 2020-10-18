# Car-Fleet-Management-System
It is an application that allows company (e.g. car rental, transport company etc.) to manage their fleet of vehicles. They can view logs of each car and driver. 
## How does it work ?
Every vehicle has installed logging device which sends their current state (location, speed, acceleration, driver, fuel level, errors and other informations) to http server. Those logs are saved in database. They can be accessed by company employee via the website.

## URLs
[Deployed front-end](https://carfleetmanagementsystem.pl "CFMS front-end URL")

[Back-end repository](https://github.com/luki530/Car-Fleet-Management-System "Back-end repository")

[Front-end repository](https://github.com/luki530/Car-Fleet-Management-System-FRONTEND "Front-end repository")

## Functionalities

<details><summary>Show</summary>
  <p>1. Login with 'remember me' function.</p>
  <p>2. Registration with email and phone number verification.</p>
  <p>3. Reset password.</p>
  <p>4. Viewing user profile.</p>
  <p>5. Autorization (each role has its own permissions).</p>
  <p>6. Listing, editing and deleting users. Assigning and changing roles.</p>
  <p>7. Adding, listing, editing, deleting cars and logger devices. Assigning logger devices to cars.</p>
  <p>8. Displaying car location on the map.</p>
  <p>9. Contact with developers through simple form.</p>
  <p>10. Scalable and responsive website on any device.</p>
  <p>11. Possibility to change theme and language.</p>
  <p>12. Animation while moving between components.</p>
  </p>
</details>

## Technologies

<details><summary>Show</summary>
  <p>
    
### Java
> Main back-end language.</p>
>#### Maven
>>Managing libraries used in project.
>#### SpringBoot
>>Responsible for HTTP server.
>#### Jakarta Persistence Api (JPA)
>>ORM standard
>#### Hibernate
>>Framework for mapping Java objects to MySQL database.
>#### JSON Web Token (JWT)
>>User authentication and autorization for application security. 
>#### JustSend SMS API
>>Sending SMS to verify user phone number while registration.
### MySQL
>Database service used to store objects.
### HTTP
>Application communication protocol.
### Google Cloud Platform
>Front-end and back-end server hosting.
>#### App Engine
>>Engine for running applications.
>#### Cloud SQL
>>Hosting MySQL database.
>#### Cloud Build
>>Dynamic and automatic code compilation and deployment after each push to master on GitHub.
>#### Google Maps
>>View vehicle on map.
### Angular
>>Main front-end technology.
>#### TypeScript
>>Language used in Angular logic.
>#### HTML
>>Display data in views.
>#### CSS
>>Styling components.
>#### Angular Material
>>Designing eye-friendly website.
  </p>
</details>

## Tools

<details><summary>Show</summary>
  <p>

### IntelliJ
>Java IDE
### Visual Studio Code
>Angular IDE
### Postman
>Sending raw http request. 
### Fiddler
>Catching http request to analyze.
### GIT
>Used for team-development. 
>#### GitHub
>>Choosen GIT hosting.

</p>
</details>


