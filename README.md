# Introduction
## Goal
The goal of this exercise is to create a simple REST API that allows to fetch information about purchases made by customers.
The API should allow to:
- Calculate the most frequent age range of customers that made purchases in a given date range.
- Fetch the details of a purchase by the following criterias:
    - User who made the purchase
    - Total amount of the purchase

# Variables
- `query.minimumFrequencyTotalAmount`: Minimum total amount of a purchase to be considered for the most frequent age range calculation.
Default value is `100`.


# Decisions
## Hexagonal Architecture and DDD
The project is designed with Hexagonal Architecture in mind. This architecture allows to decouple
the business logic from the infrastructure. This allows to change the infrastructure without the need
of changing the business logic. At this moment only H2 database is used, but it would be easy to change
it to any other database. The same applies to the API. At this moment only REST API is implemented, but
it would be easy to implement a GraphQL API or any other API.

On the other hand I have decided not to use DDD. I think that DDD is a great tool to design complex domains,
but in this case the domain is very simple and I think that DDD would be an overkill. Other reason to not
use DDD in this case is the lack of domain knowledge. In my opinion, the most important part of DDD is the
strategic part. Keeping in touch with the domain expert and build a conceptual model to be represented in
our code. The tactical part is also important, but it does not make sense without the strategic one.

## API First
I have decided to use API First approach to design the API. This approach allows to design the API before
implementing it. This approach is very useful because we can share the API contract with the client and
get feedback before implementing it. Another advantage of this approach is that the documentation is
always up-to-date.

## Mapping Fields
Hexagonal architecture allows to decouple the business logic from the infrastructure. However, it is necessary
to map the fields from the domain model to the database model and vice versa. I have decided to use `mapstruct`
library to do this mapping. This library allows to generate the mapping code at compile time. This approach
allows to keep the code clean and avoid boiler-plate code.

## Database Design
I have decided to use a relational database to store the data. I have also decided to merge the `PURCHASE` date and time
into a single column. In this way queries can be simplified and also indexes can be used to improve performance.

In order to improve performance I also have decided to add an index on `PURCHASE_DATE` column. This index will be used
to improve the performance of the query that calculates the most frequent age range of customers that made purchases.

## Development Database
In order to make development easier, I have decided to use H2 database. This database is an in-memory database
that can be used during development. This database is not suitable for production environments, but it is
very useful during development. The database is configured to be created and populated with some data at
startup. This data is defined in `src/main/resources/schema-h2.sql` file.

In a real world scenario, the embedded database should be configured in a different spring profile. This is not
done in this project for simplicity.

## Github

I have decided to use GitHub as the version control system for this project. I would like to
configure
some protections for the `main` branch, but it cannot be donne with a free account:

- `Required approvals` is disabled for `main` branch. It would be a good practice to have at least
  one approval before merging a PR. However, in this case, I am the only developer, so it makes no
  sense.
- `Require status checks to pass before merging` is enabled for `main` branch.
- `Force push` and `Deletion` are discouraged and of course disabled for `main` branch.

Despite the fact that enforcement rules cannot be applied to `main` branch, I will try to enforce
them by myself.

## CI Pipeline
I have decided to use GitHub Actions as the CI tool for this project. I have configured a simple CI pipeline that runs
tests and generates a code coverage report when a PR is created. I have also configured Github to not allow merging
a PR if the pipeline is not green.
![github-pr-running](docs/images/github-pr-running.png)
![github-pr-success](docs/images/github-pr-success.png)



## Docker
I have added a dockerfile to the project in order to create a docker image. You can build the image with
the following command:

```shell
docker build -t inditex-exercise .
```
In order to run the image, you can use the following command:

```shell
docker run -p 8080:8080 inditex-exercise
```

With this command the application will be available on `http://localhost:8080`. You can access
the swagger-ui documentation at `http://localhost:8080/swagger-ui/index.html`.

## Sonar
I have added a sonar configuration to the project. You can run sonar with the following command:

```shell
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=<sonar-project-key> \
  -Dsonar.projectName=<sonar-project-name> \
  -Dsonar.host.url=<sonar-url> \
  -Dsonar.token=<sonar-token>
```

Take into account that you need a sonar server running in order to run this command. Docker can be used to set up a
local sonar instance. The following docker-compose file can be used to set up a local sonar instance:

```yaml
version: '2'
 
services:
  sonarqube:
    image: sonarqube
    ports:
      - "9000:9000"
    networks:
      - sonarnet
    environment:
      - SONARQUBE_JDBC_URL=jdbc:postgresql://db:5432/sonar
      - SONARQUBE_JDBC_USERNAME=sonar
      - SONARQUBE_JDBC_PASSWORD=sonar
    volumes:
      - sonarqube_conf:/opt/sonarqube/conf
      - sonarqube_data:/opt/sonarqube/data
      - sonarqube_extensions:/opt/sonarqube/extensions
      - sonarqube_bundled-plugins:/opt/sonarqube/lib/bundled-plugins
      
  db:
    image: postgres
    networks:
      - sonarnet
    environment:
      - POSTGRES_USER=sonar
      - POSTGRES_PASSWORD=sonar
    volumes:
      - postgresql:/var/lib/postgresql
      - postgresql_data:/var/lib/postgresql/data
      
networks:
  sonarnet:
    driver: bridge
 
volumes:
  sonarqube_conf:
  sonarqube_data:
  sonarqube_extensions:
  sonarqube_bundled-plugins:
  postgresql:
  postgresql_data:
```

Example of sonar output:
![sonar-run-example](docs/images/sonar.png)

Ideally sonar should be integrated with the CI pipeline. However, I have not done it in this project for the lack of time.

### Alternatives
Intellj IDEA has a sonar plugin that can be used to run sonar analysis. However, this plugin is slow and I would not
recommend to use it for large projects.

## Health Check
With Spring boot actuator a health endpoint is added to the application. This endpoint can be used
to check if the application is up and running. The endpoint is available by default at `/actuator/health`.
This endpoint can be used by kubernetes or any other platform to check if the application is ready to receive
traffic.


## Changelog

I have decided to use [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) as the format for the
changelog.


## Exception Handling
The exceptions will be handled by a controller advice. This controller advice will catch all the exceptions
and return a proper response to the client. This approach allows to centralize exception handling and avoid code duplication.


## Testing
### Unit Testing
At the time of writing a test, I personally prefer write a test method self-contained avoiding
calling
auxiliary methods and using magic numbers. Test code is not like production code and the main goal
of
the unit test is to check if a part of the system is working properly and helps developers to
understand
what is happening if something goes wrong. Self-Containing methods will provide developers with a
cleaner view on what is being tested without the need on jumping to other parts of the code.

The following article discusses the subject in more depth:

* [Why Good Developers Write Bad Unit Tests](https://mtlynch.io/good-developers-bad-tests/)

### Integration Testing
Integration tests allows the test of REST API endpoints by using spring-boot-test framework with MockMvc. With this
approach it is possible to test the API endpoints without the need of starting the application.

### Test Mutation
[PIT](https://pitest.org/) framework is used to introduce mutations testing on the project. As said
on the official project web page:

```
Faults (or mutations) are automatically seeded into your code, then your tests are run.
If your tests fail then the mutation is killed, if your tests pass then the mutation lived. 
```

This means that PIT runs unit test against automatically modified versions of the code. This
modification
should produce different result and cause the unit test to fail. If the test does not fail, maybe
test
suite is not exhaustive enough.

The following maven goal can be used to generate a mutation testing report of this project code
base:

```shell
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```

A report will be generated in `target/pit-reports/index.html` file.

![mutation-testing-example](docs/images/pi-test-with-reported.png)

NOTE: Mutation testing will be skipped on model classes. Many of those classes contains boiler-plate
code that is normally not tested.

### Code Coverage
Cover code with unit tests is a good practice. However, it is also important to measure the code coverage
in order to check if the test suite is exhaustive enough. I have decided to use `jacoco` library to generate
a code coverage report. The report can be generated using the following maven goal:

```shell
mvn clean verify jacoco:report
```

Results can be found in `target/site/jacoco/index.html` file. I also have configured the CI pipeline to
publish a message on the PR with the code coverage report.


## Request Trace
In order to help developers to trace requests through the system, I have decided to make use of `micrometer`
library to generate a unique trace id for each request. This trace id will be included in the response headers:
* `X-B3-TraceId`
* `X-B3-SpanId`
This traces will help developers to trace requests through a distributed system. It is also possible to use
some tools like [Zipkin](https://zipkin.io/) to visualize the traces.

![b3-trace-example](docs/images/b3-headers.png)

# Testing the Application
## Run the application
In order to run the application, you can use the following command:

```shell
mvn spring-boot:run
```

The project also includes a dockerfile that can be used to create a docker image. You can build the image and run
it on docker. See the `Docker` section for more information.

## Available operations
You can go to `http://localhost:8080/swagger-ui/index.html` to see the swagger-ui documentation.

In `docs` folder it is also available a postman collection that can be used to test the application.

# Further Information
For further information, please contact me at `carlos.bouzon.garcia@gmail.com`. I would be delighted to answer
any question you may have.