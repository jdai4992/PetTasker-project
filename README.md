# README #

This README documents whatever steps are necessary to get our application up and running.

### How do I set up Project? ###

* Clone this repository onto your local machine
* Open Spring (STS) application
* Go to 'File' and select 'Import'
* Expand the 'General' drop down menu and select 'Exisiting Projects into Workspace' and click 'Next'
* In 'Select root directory' select the location of where you clone this reporistory and select 'PetTaskerApp' folder and click 'Open'
* Then click 'Finish' and wait for the whole project to load in successfully
* Once, you have successfully loaded the whole project go to src/main/resources/database.properties file and update to have your MySQL password and username.

Note: let the project load properly (you will see a loading bar in the bottom right side of the window). Once, this loading bar disappears that means the project has been successfully loaded

* You now need to setup the database by following the steps below.

### How do I set up the database ###

* Go to terminal and type in the following command one by one:
	* cd /usr/local/mysql/bin
	* ./mysql -u root -p
* Type in your MySQL password
* Type in the following commands one by one:
	* CREATE DATABASE petTaskerDB;
	* USE petTaskerDB;

### Continue setting up Project after creating the database ###

* After the project fully loads, go to the PetTaskerApp/src/main/resources folder and place [this database.properties file](https://bitbucket.org/jpat9046/elec5619/downloads/database.properties) inside that resources folder. [So, its like PetTaskerApp/src/main/resources/database.properties]
* In the 'database.properties' file you need to change the 'jdbc.username' and 'jdbc.password' to your mySQL username and password
* Go back to Spring Application and right click the root directory folder i.e. 'PetTaskerApp' and click 'Refresh'.
* Create a new tomact server and import this project to that server.
* Right click on the PetTaskerApp and click 'Run As' and choose 'Run On Sever'

Other Useful Commands:

* To find out the different tables in the database, use command:
	* SHOW TABLES;
	
* To find out the rows in the database table, use command:
	* select * from users;
	
* To find out the scheme for the table users, use command:
	* DESCRIBE users;

Sample data Inserts:

* INSERT INTO **staffs**(id, firstName, lastName, email, password, phoneNumber, technical) VALUES(1, 'Riya', 'J', 'riya@pettasker.net', 'pswd', '3333333333', '1');

* INSERT INTO **reports**(reportId, reason, reportDetails, reportedPersonId, reporterId, title) VALUES(1, 'Account Deletion', 'I want to get rid of my account. I don't have a pet and I have no reason to use PetTasker', 1, 1, 'Delete my account!');