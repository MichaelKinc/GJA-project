# GJA project - LetMeIn

### Authors:
* Michael Kinc
* Marek Šťastný
* Petr Vrtal

### requirements:
* JDK 17
* Maven
* MySQL server
* npm

### setup 
* create MySQL database ${dbName} and grant all privileges to ${userName}
* go to the home directory of project
* update these values in the ```src/main/resources/application.properties```
  * ```spring.datasource.url=jdbc:mysql://localhost:${MySQL_port}/${dbName}?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC```
  * ``` spring.datasource.username=${userName}```
  * ```spring.datasource.password=${dbUserPassword}```
  * in this file, there also can be configured e-mail client
* in the home directory of the project run ```mvn spring-boot:run```
* application is now running at the url ```http://localhost:8080/login```

### Usage
After the first startup, there are pre-generated three users with different roles:
* Admin
  * username: admin@admin.cz
  * password: 123456
* User
  * username: user@user.cz
  * password: 123456
* Kiosk
  * username: kiosk@kiosk.cz
  * password: 123456