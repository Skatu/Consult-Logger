# Consult-Logger

## Using the App

### How to run
Inside the folder [docker](docker) you will find two files: a Dockerfile and a jar to build the docker image. 
The image will run on port 8080. If you wish to run on another port, you can change this line in the Dockerfile,
 
>EXPOSE 8080

to the port you wish to use.

To run the app in docker, first start docker and run the command:
>docker build -t koerber .

If you wish to give the image another name, just change "koerber" to the name you desire.

after the build is complete, just run,
>docker run -p 8080:8080 koerber

And it should be running with no problems.

## Calling the endpoints

This project has 4 endpoints:
- PUT /consults
- GET patients/{patientId}/consultsAndSymptoms
- GET patients/all
- GET specialties/topSpecialties

For more detail on each endpoint, I've provided an [openAPI](docs/OpenAPI/openapi.yaml) file inside the folder [docs/OpenAPI](docs/OpenAPI).

### Curl commands

I've also provided curl commands for each endpoint:
- Create consult:
>curl --location --request PUT 'http://localhost:8080/consults' \
--header 'Content-Type: application/json' \
--data '{
"doctorId": {{doctorId}},
"patientId":{{patientId}},
"specialtyId":{{specialtyId}}
}'

Here you substitute {{doctorId}}, {{patientId}} and {{specialtyId}} for the respective doctor, patient and specialty id.
The same logic will apply for the subsequent curl requests.

- Get patient consults and symptoms:
> curl --location 'http://localhost:8080/patients/{{patientId}}/consultsAndSymptoms'

- Get Top Specialists:
> curl --location 'http://localhost:8080/specialties/topSpecialties'

- Get all patients:
> curl --location 'http://localhost:8080/patients/all?pageNumber={{pageNumber}}&pageSize={pageSize}}&(age={{age}} | name={{name}})&sortBy=(age|name)&sorting=(asc|desc)'
 
This last curl command requires more explanation. 

In the queries params, we have the following:
- **pageNumber and pageSize** are used for pagination. If omitted, the default values are 0 and 5, respectively.
- **sortBy** decides the parameter to which sort the data by. It can have the value "age" or "name". If omitted, the default value is "age".
- **sorting** decides how to sort the data, in either ascending or descending order. This param takes the value 'asc' or 'desc'. If omitted, the default value is asc.
- **age** and **name**: we can filter the data to match a certain name or age. We can only filter by either name or age, not both.
This parameter is optional and does not assume a default value.
If we add both, the service will respond with a corresponding error message.
For example, we can have this as the url,
>http://localhost:8080/patients/all?age=25

or this,
> http://localhost:8080/patients/all

but not this,
>http://localhost:8080/patients/all?age=25&name=Jose

because we have both name and age as filters.


## Structure
This project is divided into a classic controller, service, repository, model and DTO layered approach.
It's currently configured to use in-memory H2 database, but you can use any db by changing the application.properties file and adding the necessary dependencies.
For persistence, I've used JPA.

On startup, the database is populated with pre-existing data. 
You can turn off this feature in the application.properties file, by setting the property "loadTemplateData" to false.

Below I've attached a class diagram detailing my approach to designing the model.
![alt text](docs/class_diagram.png)

Some observations:
- I've decided to make the ids Long instead of UUID because Long has less memory overhead, is faster, and for this exercise, the IDs won't be generated/shared across multiple services. 
- Consult contains a list of symptoms because I thought it makes sense that, when a patient goes to a consult, the doctor registers all the symptoms noted in that specific consult.
- Pathology associated with the consult is optional, since it's not always possible to diagnose in one consult the pathology.
- I've used Lombok for getters/setters and logging, to reduce boilerplate code.
- For DTOs, I decided to use records to reduce boilerplate code as well.
- For the endpoint "get all patients", I used a class "PaginationParams" to facilitate the addition of future query params in case there was a need (so we don't have to change the java method signature).
Currently, the object is created manually. I know we can have Spring auto create the object as a parameter of the HTTP request, but due to lack of time, I decided to not make that change.
- In the "create consult" endpoint, we pass the specialty id, but currently it's unecessary because the doctor only has 1 specialty. The reason I left it is so, if we ever have a doctor with more than 1 specialty, this system (and any other system that calls this one) is more ready for the change.
