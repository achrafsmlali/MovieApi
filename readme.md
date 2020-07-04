# Movie API
To know before using the project:

 - This project uses the OMDb API an open Movie database. To be able to
 fetch data from this API you need to generate an ApiKey. Us this [Link](http://www.omdbapi.com/apikey.aspx). Once the key generated add it as value to in the application.properties : `omdb.apikey=<yourApiKey>`
 
 - For simplicity the project uses an in-memory h2 database (data will be lost after restarting the server).
 
 - Once the server is launched you will have accesses to:
    - the h2 database console via [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Use the default h2 username and password to connect to the database (the username is "sa" and password is empty). 
    - the Api Swagger UI via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html,) witch I advise you to uses for this API testing (visual navigation, request body examples ...).
    
 - The project is launched by default in the port 8080.
        