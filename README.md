# WASI Backend
Backend for WASI, a smart Real Estate web application, which utilizes user data to predict fitness to locations in accordance to places of interest (e.g Bank, pharmacy, school, etc) 

Technologies used: Java, Spring, JPA, MySQL, Azure ML model, Google Maps API, Python, IBM Watson

1. User interacts with IBM Watson Chatbot in the front end and gets asked a bunch of questions (whether or not they have kids, gender, etc).

2. When the chat is over, front end client sends data retrieved from IBM Watson to an endpoint in the Spring Application.

3. The Spring application connects to an ML model residing in Azure, sending user data and recieving the predicted fitness to different places of interest.

4. Using user data and the prediction made by the ML model, the Spring application retrieves a list of real estate from the database and uses an algorithm to calculate the fitness to each individual real estate property. 

5. The list of real estate with their fitness percentage is sent in a response to the frontend client. 

Structure: 
/Wasi: Spring Application that handles business logic, database connection and serves as the middle point between the frontend client and the ML model residing in Azure. Utilizes Clean Architecture which separates the app in 3 layers: Infrastructure, Use Cases and Entities.

/SQL Inserts: Here we will find the initial inserts for the database.

/Scripts, The following notebooks will be found: 

    1) GENERATE_REAL_ESTATE_IMAGES_RANDOM: Used to assign random dummy images to Real Estate records and generate the SQL statements to insert them. Real images were not required for the proyect. 

    2) GENERATE_PLACES_OF_INTEREST: Connects to the Google Maps API and searches for places of interest within 1km of each location, linking the real estate to the place of interest if at least one location is found (e.g if one bank is found within 1km of distance of real estate with id 10, there will be a relationship between that real estate record and the bank record, resulting in a SQL insert statement which is also generated in the notebook).
