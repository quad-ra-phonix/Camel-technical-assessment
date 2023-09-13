# Camel-technical-assessment
Spring boot application using camel aphace with rabbitMQ.

## Tech Stack
```
Maven
SpringBoot
Apache Camel
RabbitMQ – you can install this natively on your machine, or use a container or cloud instance
Postgres – you can install this natively on your machine, or use a container or cloud instance
```

## Usage

```
using maven build to compile to jar, then run jar or
You can import maven project to IDE.

to prepopulate rabbitMQ queue with messages run ./mvnw verify


# database setup
start postgres database and create cameldb
Hibernate will create the schema at application startup.

# To view the swagger documentation
while project is running, open on browser: http://localhost:8080/swagger-ui/index.html#

# Postman scripts
I have included the postman scripts inside the 'postmanScript' folder. Import to local instance of postman and run the scripts.

```
