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
   
   
