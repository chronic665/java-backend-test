## Introduction

Cleaning your room has never been easier. Forget old hoover models like like [Roomba](https://en.wikipedia.org/wiki/Roomba). With Edward J. Hoover you're room is finally home to real IoT.

Some rules that your amazing Edward will adhere to:

* room dimensions as [X and Y coordinates](https://en.wikipedia.org/wiki/Cartesian_coordinate_system), identifying the top right corner of the room rectangle. This room is divided up in a grid based on these dimensions; a room that has dimensions X: 5 and Y: 5 has 5 columns and 5 rows, so 25 possible hoover positions. The bottom left corner is the point of origin for our coordinate system, so as the room contains all coordinates its bottom left corner is defined by X: 0 and Y: 0.
* locations of patches of dirt, also defined by X and Y coordinates identifying the bottom left corner of those grid positions.
* an initial hoover position (X and Y coordinates like patches of dirt)
* driving instructions (as [cardinal directions](https://en.wikipedia.org/wiki/Cardinal_direction) where e.g. N and E mean "go north" and "go east" respectively) 

The room will be rectangular, has no obstacles (except the room walls), no doors and all locations in the room will be clean (hoovering has no effect) except for the locations of the patches of dirt presented in the program input.

Placing the hoover on a patch of dirt ("hoovering") removes the patch of dirt so that patch is then clean for the remainder of the program run. The hoover is always on - there is no need to enable it.

Driving into a wall has no effect (the robot skids in place).

## Input

Program input will be received as a HTTP POST request with a json payload with the format described here.

Example:

```javascript
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```
Example request:
```shell
http post http://localhost:8080/ roomSize:='[5, 5]' coords:='[1,2]' patches:='[[1,0], [2,2], [2,3]]' instructions="NNESEESWNWW"
```

## Output

Service output is returned as a json payload.

Example (matching the input above):

```javascript
{
  "coords" : [1, 3],
  "patches" : 1
}
```
Where `coords` are the final coordinates of the hoover and `patches` is the number of cleaned patches.
Moreover, the services persists every input and output to a database.

## Deliverable

The service:

* is a Spring Boot powered web service
* requires Java 8 (to run) and Maven (to build) 
* has two Spring profiles: 'default' and 'mongodb'
    * default does not persist any data
    * mongodb starts an embedded MongoDB with the service (for convenience) and persists all request and response data, together with a timestamp. **As the database is embedded all persisted data will be lost when you stop the service!** 

## Building and running the service
### Running the service directly from source
```
# non persistent version:
mvn spring-boot:run

# persistent version
mvn spring-boot:run -Dspring.profiles.active=mongodb
```

### Packaging the app and running it
```
mvn clean package
# runnable jar lies at ./target/hoover.jar
java -jar ./target/hoover.jar

# with persistence
java -jar -Dspring.profiles.active=mongodb ./target/hoover.jar
```

## Persistence profile

The persistence can be activated by running the service with the 'mongodb' profile (see Building and Running). 
The service will then start an embedded MongoDB server that will live as long as the service is running. This is
no solution for a production deployment, but brings the convenience of being able to run the service as a standalone app.

While the service is running all normal MongoDB clients can be used to access the database and it's data. The port the database
is listening to can be found in the startup output of the app.

For extra convenience, when run in the 'mongodb' profile, the app also provides a REST endpoint to access the MongoDB data. 
This endpoint is provided by Spring Data REST in a HAL format. The data can be retrieved under:
```
http get http://localhost:8080/history
```
**Warning** As this is a default Spring Data REST endpoint, it is possible to manipulate the data through POST and PUT requests.

