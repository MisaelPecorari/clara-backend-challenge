# Project setup

### Prerequisites
Ensure to have the following installed in your local environment:
- JDK-17
- Maven

### How to execute the project?

Simply run it from your preferred IDE, or execute 
`mvn spring-boot:run` in your console.

The app will be available at port 8080.

### How to reach the API?

The list of available endpoints is as follows:
- GET http://localhost:8080/search?name=nirvana
- GET http://localhost:8080/artists/{artistId}/releases
- GET http://localhost:8080/comparison?ids=125246&ids=125249

You can execute them using the curl command as follows:
`curl 'http://localhost:8080/search?name=nirvana'`


