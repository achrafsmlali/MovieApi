# Movie API
To know before using the project:

 - This project uses the OMDb API an open Movie database. To be able to
 fetch data from this API you need to generate an ApiKey. Us this [Link](http://www.omdbapi.com/apikey.aspx). Once the key generated add it to in the application.properties : `omdb.apikey=<yourApiKey>`
 
 - For simplicity the project uses an in-memory h2 database (data will be lost after restarting the server).
 
 - Once the server is launched you will have accesses to:
    - the h2 database console via [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Use the default h2 username and password to connect to the database (the username is "sa" and password is empty). 
    - the Api Swagger UI via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html,) witch I advise you to use for this API testing (visual navigation, request body examples ...).
    
 - The project is launched by default in the port 8080.
 
 Here is a test scenario :
   1. Post request on `/movies` with the RequestBody :
        ```javascript
          {
            "title": "Joker"
          }
        ```
      then Batman, Hulk and SuperMan as a title.
   2. Get request on `/movies` to get all movies.
   3. Put request on `/movies/1/` to edit the Joker movie. It takes 
   as a Request body the movie with the update data (for example I'll edit the title):
   
         ```javascript
            
             {
               "id": 1,
               "Title": "Joker is amazing",
               "Year": 2019,
               "Genre": "Crime, Drama, Thriller",
               "Director": "Todd Phillips",
               "Language": "English",
               "Plot": "In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.",
               "Actors": "Joaquin Phoenix, Robert De Niro, Zazie Beetz, Frances Conroy",
               "Released": "2019-10-04"
             }
         ```
   4. Post request on `/comments` to add a comment. It takes as a Request body the comment and the movie Id to which it will be associated.
         ```javascript
              {
                "commentContent": "Joker is an amazing movie",
                "movieId": 1
              }
         ```
      add another comments to Joker and a second one To Batman.
      
   5. Get request on `/coments` to get all comments.
   6. Get request on `/coments?id=1` to get all Joker comments.
   7. Get request on `/top` to get movies ranking.
   8. Delete request on `/movies/1/` to delete Joker movie.
  

    
        