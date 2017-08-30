/**
 * Script to create Festivity table in jersey Database
 * Author:  osmar
 * Created: 30/08/2017
 */

create table "APP".FESTIVITY
(
	PLACE VARCHAR(50),
	START TIMESTAMP,
	END_ TIMESTAMP,
	NAME VARCHAR(50) default 'NEW' not null primary key
)

