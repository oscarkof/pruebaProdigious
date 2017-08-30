# PruebaProdigious - Oscar Martínez 
Project with API REST:

Festivities are special times different places have to celebrate their local habits, manners, historical dates and other events. These events always have a name, a start date and end date, and the place where they are celebrated.

Design a RESTful API that allows clients to manage festivities. It should allow clients to:
 - Query for all existent festivities
 - Query festivities by: name, start date, data range, name of the place where they are celebrated
 - Create new festivities
 - Update existent festivities
   - It should allow clients to change the festivity name, start date and end date

Steps to configure the environment

1. Download and Configure DB
   - Download the Derby embebbed database from https://db.apache.org/derby/derby_downloads.html 
   - Startup the database service for schema APP
   - create the table Festivities from SQL code in resources folder https://github.com/oscarkof/pruebaProdigious/blob/master/PruebaApiRest/resources/createFestivityTable.sql
   
2. Setup the configuration files 
   - Modify the festivities.properties https://github.com/oscarkof/pruebaProdigious/blob/master/PruebaApiRest/src/main/resources/festivities.properties to set the file with the initial XML Data to be load in the database and the dateformat to query in the GET operations
   - Modify the hibernate.cfg.xml https://github.com/oscarkof/pruebaProdigious/blob/master/PruebaApiRest/src/main/resources/hibernate.cfg.xml to set up the database connection and properties
   
   
How to consume the service festivity 

  - this services has four operations that respont to the HTTP methods (GET, POST, PUT)
    - GET:  to retreive information from database you can use this url http://localhost:8080/PruebaApiRest/service/festivity this url show all festivities that are stored in the database
    
     - to get the information in XML format you can add the header **accept** with the value **application/xml** 
       Example:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<festivities>
  <festivity>
      <name>Jimmy's event</name>
      <start>2016-02-22T14:16:01.001-05:00</start>
      <end>2016-07-30T14:16:01.001-05:00</end>
      <place>Clark's castle</place>
  </festivity>
  <festivity>
      <name>Craig's recognition</name>
      <start>2015-10-27T09:01:09.009-05:00</start>
      <end>2015-12-12T09:01:09.009-05:00</end>
      <place>West's joint</place>
  </festivity>
  <festivity>
      <name>Edna's event</name>
      <start>2015-11-09T13:28:16.016-05:00</start>
      <end>2016-08-28T13:28:16.016-05:00</end>
      <place>Coleman's joint</place>
  </festivity>              
</festivities>
```
When have no data the server it shows a response like this:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<responseCustom>
    <statusCode>404</statusCode>
    <statusMessage>Data not found</statusMessage>
</responseCustom>
```
  - to get the information in JSON format you can add the header **accept** with the vaue **application/json**
  Example

```json
{
    "festivities": {
        "festivity": [
            {
                "name": "Jimmy's event",
                "start": "2016-02-22T14:16:01.001-05:00",
                "end": "2016-07-30T14:16:01.001-05:00",
                "place": "Clark's castle"
            },
            {
                "name": "Craig's recognition",
                "start": "2015-10-27T09:01:09.009-05:00",
                "end": "2015-12-12T09:01:09.009-05:00",
                "place": "West's joint"
            },
            {
                "name": "Edna's event",
                "start": "2015-11-09T13:28:16.016-05:00",
                "end": "2016-08-28T13:28:16.016-05:00",
                "place": "Coleman's joint"
            },
            {
                "name": "Howard's special time",
                "start": "2016-06-30T07:46:01.001-05:00",
                "end": "2016-07-02T07:46:01.001-05:00",
                "place": "Alvarez's castle"
            }
		]
    }
}
```
When have no data the server it shows a response like this:
```json
{
    "responseCustom": {
        "statusCode": 404,
        "statusMessage": "Data not found"
    }
}
```
- GET with query params: you can use the same url http://localhost:8080/PruebaApiRest/service/festivity but you can use the path query to get more specific information with the wildcards:

  - **name** you can filter the name of the festivity that you are asking
  - **place** you can filter the place of the festivity that you are asking
  - **start** you can filter the start date of the festivity that you are asking
  - **min** you can filter the min range of start date of the festivity that you are asking
  - **max** you can filter the max range of start date of the festivity that you are asking
  
  #NOTE: for the dates in queryParams is necesary go to festivities.properties to get with date format is used to parse the dates default value is: **ddMMyyHHmmssSSS**
  
    Example:
    - http://localhost:8080/PruebaApiRest/service/festivity/query?name=Sophie's Event
    - http://localhost:8080/PruebaApiRest/service/festivity/query?place=Coleman's joint
    - http://localhost:8080/PruebaApiRest/service/festivity/query?start=230216100449049
    - http://localhost:8080/PruebaApiRest/service/festivity/query?min=211215000000000&max=211215235959999
    
    shows: 
```json
{
    "festivities": {
        "festivity": {
            "name": "Sophie's Event",
            "start": "2017-08-30T14:00:00.231-05:00",
            "end": "2017-08-31T13:00:00.231-05:00",
            "place": "CHIA"
        }
    }
}
```
or in XML format
```XML
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<festivities>
    <festivity>
        <name>Sophie's Event</name>
        <start>2017-08-30T14:00:00.231-05:00</start>
        <end>2017-08-31T13:00:00.231-05:00</end>
        <place>CHIA</place>
    </festivity>
</festivities>
```
- POST Method: is used to insert new festivities to the database, for this method is necesary send in the body the XML format entity with the information to store
     Example:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<festivity>
    <name>Sophie's Event</name>
    <start>2017-08-30T14:00:00.231-05:00</start>
    <end>2017-08-31T13:00:00.231-05:00</end>
    <place>CHIA</place>
</festivity>
```
   The Answer for this post can be one of these:
      - Status code: **200** and message **Created sucessfully**
      - Status code: **400** and message **Missing Data**
      - Status code: **400** and message **Dates are not consistent**
      - Status code: **500** and message **Error happen in a layer below the REST API layer - Insert**
   
- PUT Method: Is used to update some information in the database from an entity shown in XML or JSON, is like the POST operation with the porpuse to update information.
     Example:
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<festivity>
    <name>Sophie's Event</name>
    <start>2017-08-30T14:00:00.231-05:00</start>
    <end>2017-08-31T13:00:00.231-05:00</end>
    <place>Saint Row</place>
</festivity>
```
   The Answer for this post can be one of these:
      - Status code: **200** and message **Created sucessfully**
      - Status code: **400** and message **Missing Data**
      - Status code: **400** and message **Dates are not consistent**
      - Status code: **500** and message **Error happen in a layer below the REST API layer - Insert**
      
      
      
- i18N: All serve messages are avaliable in two languages, english(default), english and spanish, to use this change of language you can set the header attribute **Accept-Language** with the value **es-CO** or **en-US** depend the situation.

Example:

```json
{
    "responseCustom": {
        "statusCode": 404,
        "statusMessage": "Data not found"
    }
}

{
    "responseCustom": {
        "statusCode": 404,
        "statusMessage": "No se encontraron datos"
    }
}
```

in XML Format

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<responseCustom>
    <statusCode>404</statusCode>
    <statusMessage>No se encontraron datos</statusMessage>
</responseCustom>

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<responseCustom>
    <statusCode>404</statusCode>
    <statusMessage>Data not found</statusMessage>
</responseCustom>
```
