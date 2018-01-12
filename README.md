
In this sample I want to deploy a sample Spring Boot application which expose ReST servicesusing AWS Elastic Beanstalk / S2 / RDS.

Java Server side:

The sample application is a very simple Spring Boot-based application that leverages the spring-data and spring-data-rest projects and use MySQL for data persitancy. 

The application exposes a REST-based API with features such as pagination, JSON Hypertext Application Language (HAL), Application-Level Profile Semantics (ALPS), and Hypermedia as the Engine of Application State (HATEOAS). 
The idea of hypermedia-driven interface is how you can discover all the RESTful endpoints using curl (or whatever REST client you are using). There is no need to exchange a formal contract or interface document with your customers.

This application has defined two model:
1. “Person” with the following properties: id, firstName, and lastName.
2. "Picutre" with the following properties: id, url, and metadata.

Rest also exposes end points to find a “Person” by last name.(“findByLastName.”) and a function to find a “Picture” by metadata. (“findByMetadata.”)


AWS side:

S3 bucket was used to store some pictures object into it.
Elastic Beanstalk was used to deploy a Java web applications based on Spring Boot.
RDS to create mySQL DB for my application data persistancy.


Sample ReST Call:

All available REST end point can be self investigated using the application base URL:
curl -X GET -i http://yuvalishay-test.us-east-2.elasticbeanstalk.com/

{
    "_links": {
        "people": {
            "href": "http://yuvalishay-test.us-east-2.elasticbeanstalk.com/people{?page,size,sort}",
            "templated": true
        },
        "picture": {
            "href": "http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://yuvalishay-test.us-east-2.elasticbeanstalk.com/profile"
        }
    }
}

create new poeple:
curl -X POST -H "Content-Type: application/json" -d '{ "firstName": "Yuval", "lastName": "Ishay" }' http://yuvalishay-test.us-east-2.elasticbeanstalk.com/people

get all poeple:
curl -X GET http://springbooteb-web-prod.us-east-1.elasticbeanstalk.com/people
query specific people:
curl -X GET http://springbooteb-web-prod.us-east-1.elasticbeanstalk.com/people/1
query by Last Name:
curl -X GET http://springbooteb-web-prod.us-east-1.elasticbeanstalk.com/people/search/findByLastName?name=Ishay

create new picture:
curl -X POST -H "Content-Type: application/json" -d '{ "url": "https://s3.us-east-2.amazonaws.com/somatix-test/success.jpg", "metadata": "Success" }' http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture

Get all picture:
curl -X GET http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture/
Query specific picture:
curl -X GET http://springbooteb-web-prod.us-east-1.elasticbeanstalk.com/picture/1
Query by metadata:
curl -X GET http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture/search/findByMetadata?metadata=Yes

You can also issue PUT, PATCH, and DELETE REST calls to either replace, update, or delete existing records.


