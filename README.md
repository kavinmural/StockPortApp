# StockPortApp
Stock Portfolio API

# Running the application
Dependencies: ```Spring Boot```, ```Maven```, ```Java 12```, ```SQLite3```

After cloning the project, import it as a maven project using the pom.xml file.
Ensure you setup Java 12 JRE and classify the project as a Spring Boot app.

Run the commands to download all the maven dependencies needed to run the project:

```mvn clean```

```mvn package```

Now you are ready to run the application:

```mvn spring-boot:run```

# Using the API

```PUT localhost:8080/login?username=testUser1&password=p``` - Endpoint used to authenticate user (Login)

```GET localhost:8080/stock/user/findAll``` - Endpoint used (ADMIN) to find all registered users

```PUT localhost:8080/stock/portfolio/myself/create``` - Endpoint used (USER) to create a new stock portfolio (Requires request body)

Example:
```
{
	"name": "My Portfolio",
  "description": "Test portfolio"
	"cost": "0",
	"cashPosition": "100000",
	"numOfPos": "0"
}
```

```GET localhost:8080/stock/portfolio/myself/all``` - Endpoint to get all user's portfolios

```PUT localhost:8080/stock/position/myself/buy``` - Endpoint to buy a stock (Requires request body)

Example:
```
{
	"type": "BUY",
	"ticker": "AAPL",
	"quantity": "10",
	"portId": "1"
}
```

```PUT localhost:8080/stock/position/myself/sell``` - Endpoint to sell a stock (Requires request body)

Example:
```
{
	"type": "SELL",
	"ticker": "AAPL",
	"quantity": "10",
	"portId": "1"
}
```

```GET localhost:8080/stock/position/myself/all``` - Endpoint to see all user's stock positions

Note: There are many more endpoints that can be used in the application, I just listed some here to try out!
