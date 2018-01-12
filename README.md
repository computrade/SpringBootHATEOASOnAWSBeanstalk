
In this sample I want to deploy a sample Spring Boot application using AWS Elastic Beanstalk and how to customize the Spring Boot configuration through the use of environment variables.

Spring Boot is often described as a quick and easy way of building production-grade Spring Framework-based applications. To accomplish this, Spring Boot comes prepackaged with auto configuration modules for most libraries typically used with the Spring Framework. This is often referred to as “convention over configuration.”

AWS Elastic Beanstalk offers a similar approach to application deployment. It provides convention over configuration while still giving you the ability to dig under the hood to make adjustments, as needed. This makes Elastic Beanstalk a perfect match for Spring Boot.

The sample application used in this blog post is the gs-accessing-data-rest sample project provided as part of the Accessing JPA Data with REST topic in the Spring Getting Started Guide. The repository is located in GitHub at https://github.com/spring-guides/gs-accessing-data-rest.


Anatomy of the Sample Application

The sample application is a very simple Spring Boot-based application that leverages the spring-data and spring-data-rest projects. The default configuration uses the H2 in-memory database. For this post, I will modify the build steps to include the mysql-connector library, which is required for persisting data to MySQL.

The application exposes a REST-based API with features such as pagination, JSON Hypertext Application Language (HAL), Application-Level Profile Semantics (ALPS), and Hypermedia as the Engine of Application State (HATEOAS). It has defined one model named “Person” with the following properties: id, firstName, and lastName. The defined repository interface exposes a function to find a “Person” by last name. This function is called “findByLastName.”
