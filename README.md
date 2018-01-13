
### Spring Boot on AWS Beanstalk

In this sample I want to deploy a sample Spring Boot application which expose ReST services using AWS Elastic Beanstalk / S2 / RDS.

**Java Server side:**

The sample application is a very simple Spring Boot-based application that leverages the spring-data and spring-data-rest projects and use MySQL for data persistency. 

The application exposes a REST-based API with features such as pagination, JSON Hypertext Application Language (HAL), Application-Level Profile Semantics (ALPS), and Hypermedia as the Engine of Application State (HATEOAS). 
The idea of hypermedia-driven interface is how you can discover all RESTful endpoints using curl (or whatever REST client you are using). There is no need to exchange a formal contract or interface document with your customers.

This application has defined two models:
1. “Person” with the following properties: id, firstName, and lastName.
2. "Picture" with the following properties: id, url, and metadata.

ReST also exposes end points to find a “Person” by last name (“findByLastName”) and another end point to find a “Picture” by metadata. (“findByMetadata”) - see sample below.

**AWS side:**

  *Elastic Beanstalk* was used to deploy a Java web applications based on Spring Boot.

  *RDS* was used to create mySQL DB for my application data persistency.

  *S3* bucket was used to store some pictures object into it.

**Sample ReST Calls:**

All available REST end points can be self investigated using the application base URL:
```
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

```

**People ReST samples:**

Create new people:

`curl -X POST -H "Content-Type: application/json" -d '{ "firstName": "Yuval", "lastName": "Ishay" }' http://yuvalishay-test.us-east-2.elasticbeanstalk.com/people`

Get all peoples:

`curl -X GET http://yuvalishay-test.us-east-2.elasticbeanstalk.com/people`

Query specific people:

`curl -X GET http://yuvalishay-test.us-east-2.elasticbeanstalk.com/people/2`

Query by Last Name:

`curl -X GET http://yuvalishay-test.us-east-2.elasticbeanstalk.com/people/search/findByLastName?name=Ishay`

**Picture ReST samples:**

Create new picture:

`curl -X POST -H "Content-Type: application/json" -d '{ "url": "https://s3.us-east-2.amazonaws.com/somatix-test/success.jpg", "metadata": "Success" }' http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture`

Get all pictures:

`curl -X GET http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture/`

Query specific picture:

`curl -X GET http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture/4`

Response:
```
{
    "url": "https://s3.us-east-2.amazonaws.com/somatix-test/success.jpg",
    "metadata": "Success",
    "_links": {
        "self": {
            "href": "http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture/4"
        },
        "picture": {
            "href": "http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture/4"
        }
    }
}
```

The URL returned is a picture which was uploaded to AWS S3 - here it is:

![Image from AWS S3](https://s3.us-east-2.amazonaws.com/somatix-test/success.jpg)

Query by metadata:

`curl -X GET http://yuvalishay-test.us-east-2.elasticbeanstalk.com/picture/search/findByMetadata?metadata=Success`

**You can also issue PUT, PATCH, and DELETE REST calls to either replace, update, or delete existing records.**



