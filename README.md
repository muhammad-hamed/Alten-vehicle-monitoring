# Alten Vehicle Monitoring

[![build status](https://travis-ci.org/muhammad-hamed/Alten-vehicle-monitoring.svg?branch=master "build status")](https://travis-ci.org/muhammad-hamed/Alten-vehicle-monitoring "build status") ![Code quality](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Avehicle&metric=alert_status "Code Quality") ![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=coverage "Code Coverage") ![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=sqale_rating "Code Coverage") ![Loc](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=ncloc "Lines of Code") ![Code duplication](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=duplicated_lines_density)


![Alt text](images/overview.png?raw=true "Angular app")


## Scenario:
Imagine you are an Alten consultant and you got assigned to a project at one of our top partners.
They have a number of connected vehicles that belongs to a number of customers.
They have a need to be able to view the status of the connection among these vehicles on a monitoring display.

The vehicles send the status of the connection one time per minute.
The status can be compared with a ping (network trace); no request from the vehicle means no connection.

## Task:
Your task will be to create a data store that keeps these vehicles with their status and the customers who own them, as well as a GUI (preferably web-based) that displays the status.
How the GUI is designed is up to you, as well as how you chose to implement the features and use suitable technologies.

Obviously, for this task, there are no real vehicles available that can respond to your "ping" request.
This can either be solved by using static values or ​​by creating a separate machinery that returns random fake status.

## Requirements
1. Web GUI (Single Page Application Framework/Platform).
 - An overview of all vehicles should be visible on one page (full-screen display), together with their status.
 - It should be able to filter, to only show vehicles for a specific customer.
 - It should be able to filter, to only show vehicles that have a specific status.
2. Random simulation to vehicles status sending.
3. If database design will consume a lot of time, use data in-memory representation.
4. Unit Testing.
5. .NET Core, Java or any native language.
6. Complete analysis for the problem.
 - Full architectural sketch to solution.
 - Analysis behind the solution design, technologies,....
 - How the solution will make use of cloud.
 - Deployment steps.

## Optional Requirements
1. Write an integration test.
2. Write an automation test.
3. Use CI(Travis, Circle, TeamCity...) to verify your code (Static analysis,..) and tests.
4. Dockerize the whole solution.
5. Microservices architecture for driver, vehicle and FaaS APIs.
 - Use any Microservices Chassis Framework.
6. Explain if it is possible to be in Serverless architecture and how?

## Data:
Below you have all customers from the system; their addresses and the vehicles they own.


 Customer name: **Kalles Grustransporter AB**
 
 Customer address: **Cementvägen 8, 111 11 Södertälje**
 
 |  VIN (VehicleId)    |   Reg. nr.   | 
 | --- | --- |
 |  YS2R4X20005399401  |   ABC123       | 
 |  VLUR4X20009093588  |   DEF456       | 
 |  VLUR4X20009048066   |  GHI789       | 

 Customer name: **KJohans Bulk AB** 
 
 Customer address: **Balkvägen 12, 222 22 Stockholm**
 
 |  VIN (VehicleId)   |    Reg. nr.     | 
 | --- | --- |
 |  YS2R4X20005388011  |   JKL012       | 
 |  YS2R4X20005387949  |   MNO345       | 

 Customer name: **Haralds Värdetransporter AB**
 
 Customer address: **Budgetvägen 1, 333 33 Uppsala** 
 
 | VIN (VehicleId)   |    Reg. nr. | 
 | --- | --- |
 |  VLUR4X20009048066  |   PQR678       | 
 |  YS2R4X20005387055  |  STU901       | 


# Solution Architecture

## Architecture Selection

In our project the partner's business is highly dynamic. Also the business scaling is promising. So we recommed that 
the application will be cloud compliant. The selection of the archicture here will be a **Microservice Architecure** it
will be optimal for this business agility. The load on the application endpoints is not close. ex:ping service will be 
hitted more that the custoemr search so scalling this endpoint only will be cost saving compared to replicaing the whole application. 
Also the give the flexability to add new small and independant components to enrich the customer business.

![Alt text](images/reference-architecture.png?raw=true "Micoservice architecture")

## Technologies

The related technologies to this *Reference Architecture* mentioned in the below diagram. 

![Alt text](images/reference-architecture-tech.png?raw=true "Micoservice architecture technologies")


The mentioned technologies are used in the impelemantation of this project into the below modules.
- **discovery-service** : build over Zuul to load balance and hide the service complexity.
- **gateway-service** : build over Eureka to keep registery with the service instances, also used by the gateway to load balance.
- **customer** : Spring boot (web, Data) ,flywayDB, h2-database, swagger and jacoco. Customer store management.
- **vehicle** : Spring boot (web, Data) ,flywayDB, h2-database, Swagger and jacoco. Vehicle store management.
- **vehicle-monitoring**: Angular frontend app.
- **vehicle-signal-generator** : Spring boot (web) , util to demo the signal status.


## Deployment (Development env)

first of all you need to build the mentioned projects (each project has it's own pipeline):

1. discovery-service
   ```shell
   cd discovery-service
   mvn package
   ```
2. gateway-service
   ```shell
   cd gateway-service
   mvn package
   ```
3. customer
   ```shell
   cd customer
   mvn package
   ```
4. vehicle
   ```shell
   cd vehicle
   mvn package
   ```
5. vehicle-monitoring
   ```shell
   cd vehicle-monitoring
   npm install
   ng build
   ```
6. vehicle-signal-generator
   ```shell
   cd vehicle-signal-generator
   mvn package
   ```
   
 **now** got to the workspace root to run the whole environment locally using **docker-compose**
 ```shell
 docker-compose build
 docker-compose up
 ```
 or 
 
 ```shell
 docker-compose up --build
 ```
 
 Check up the components
 
  - **vehicle-monitoring** : open the Webapp http://localhost:8080/ui/
    ![Alt text](images/overview.png?raw=true "Angular app")
 - **customer service** : check the swagger of the customer service  http://localhost:8080/customer/swagger-ui.html#
   ![Alt text](images/customer-swagger.png?raw=true "Customer's API swagger")
 - **vehicle service** : check the swagger of the customer service  http://localhost:8080/vehicle/swagger-ui.html#
   ![Alt text](images/vehicle-swagger.png?raw=true "Customer's API swagger")
 - **discovery-serice** : check the *Eureka* dashboard http://localhost:8090/

## CI/CD
  travis-ci and AWS
  AWS CodeBuild is used to achieve:
  - build the application.
  - build the Docker image.
  - publish it to the AWS ECR.
  *\*) check the ```${app.dir}/buildspec.yml``` * 
 
 AWS Codepipe line
  - Trigger the build
  - Deploy the a task. 
 
 ECS is used to achive:
  - runtime enviroment.
 

## Static Code Analysis

  I've integrated travis-ci to SonarCloud to publish the Code analysis for Two Services
  - **Customer Rest**
     SonarCloud public link : https://sonarcloud.io/dashboard?id=com.alten%3Acustomer 
  ![Code quality](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=alert_status "Code Quality") ![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=coverage "Code Coverage") ![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=sqale_rating "Code Coverage") ![Loc](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=ncloc "Lines of Code") ![Code duplication](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=duplicated_lines_density) ![Vul](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Acustomer&metric=vulnerabilities)
  
  - **Vehicle Rest**
   SonarCloud public link : https://sonarcloud.io/dashboard?id=com.alten%3Avehicle
  ![Code quality](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Avehicle&metric=alert_status "Code Quality") ![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Avehicle&metric=coverage "Code Coverage") ![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Avehicle&metric=sqale_rating "Code Coverage") ![Loc](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Avehicle&metric=ncloc "Lines of Code") ![Code duplication](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Avehicle&metric=duplicated_lines_density) ![Vul](https://sonarcloud.io/api/project_badges/measure?project=com.alten%3Avehicle&metric=vulnerabilities)
  
  
  

 
 
 
 










