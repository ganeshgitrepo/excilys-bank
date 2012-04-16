* have a Postgresql server on localhost, with a gatling/gatling scheme

* start the server with -Dspring.profiles.active=initDB in order to reset the database (
don't forget to increase tomcat startup timeout to 10')

* example of datasource configuration for postgres/tomcat :

```xml
<Resource name="ds" auth="Container" type="javax.sql.DataSource"
			   factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
               maxActive="100" maxIdle="30" maxWait="10000"
               username="gatling" password="gatling" driverClassName="org.postgresql.Driver"
               url="jdbc:postgresql:gatling"/>

```