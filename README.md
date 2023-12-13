# Introduction
## Goal
The goal of this exercise is to create a simple REST API that allows to fetch information about purchases made by customers.
The API should allow to:
- Calculate the most frequent age range of customers that made purchases in a given date range.
- Fetch the details of a purchase by the following criterias:
    - User who made the purchase
    - Total amount of the purchase

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