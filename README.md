# Alten Challenge
[![build status](https://travis-ci.org/eGhazawy/alten-challenge.svg?branch=master "build status")](https://travis-ci.org/eGhazawy/alten-challenge "build status")
## Challenge Description
### Scenario:
Imagine you are an Alten consultant and you got assigned to a project at one of our top partners.
They have a number of connected vehicles that belongs to a number of customers.
They have a need to be able to view the status of the connection among these vehicles on a monitoring display.

The vehicles send the status of the connection one time per minute.
The status can be compared with a ping (network trace); no request from the vehicle means no connection.

### Task:
Your task will be to create a data store that keeps these vehicles with their status and the customers who own them, as well as a GUI (preferably web-based) that displays the status.
How the GUI is designed is up to you, as well as how you chose to implement the features and use suitable technologies.

Obviously, for this task, there are no real vehicles available that can respond to your "ping" request.
This can either be solved by using static values or ​​by creating a separate machinery that returns random fake status.

## Projects Description
**monitoring-discovery-server** - Eureka server for handling service discovery.
**VehicleManagement** - Handle Vehicle/Acknowledgmenets related operations.
**CustomerManagement** - Handle Customers (vehicles' owners) related operations.
**StatusAcknowledgementsGenerator** - send acknowledgements to some vehicles for testing purpose.
**vehicle-monitoring-app** - Web GUI (Single Page Application Framework/Platform).

## Documentation
Please see [wiki](https://github.com/eGhazawy/alten-challenge/wiki "wiki") for detailed documentation.