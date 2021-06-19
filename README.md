Coverage: 86.6%

# Hobby Project

A application which helps keep track of vehicle maintenance.  A Web app with a HTML front end which make API requests with JavaScript.  API created with Java which saves data to a MySQL database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need to install the following.

Get [MySQL](https://dev.mysql.com/downloads/mysql/8.0.html)

Get [Java Developement Kit](https://adoptopenjdk.net/)

Get [Eclipse](https://www.eclipse.org/downloads/)

Get [Maven](https://maven.apache.org/download.cgi)

Get [Spring Tools 4](https://spring.io/tools)

## Installing

Clone the repository to your computer.  Run the following command in your Eclipse workspace.

```
git clone https://github.com/AmanBassi/hobby-project
```

### Importing Project

* Open Eclipse

* Right Click in Package Explorer > Import > Existing Maven Projects

* Change Root Directory to the new folder Cloned from GitHub containing the project

* Ensure `pom.xml` file is selected then click finish

### Setting Up Database

* Create a MySQL schema e.g. `CREATE SCHEMA `hobby_project` ;`

* In Eclipse open `HobbyProject\src\main\resources\application-production.properties`

* Update database credentials so the application can connect to your newly created schema

```
spring.datasource.url=jdbc:mysql:YOURSQLDATABASEANDSCHEMA
spring.datasource.username=YOURUSERNAME
spring.datasource.password=YOURPASSWORD
```

* Using the Boot Dashboard in Eclipse start the project

* The applcaiton should be hosted locally on port 8080 by default

* Using a web browser navigate to `http://localhost:8080/`

* If the port is in use you can change the port by updating the `server.port` in the `application-production.properties` file

## Running Tests

To run all the application tests.

* Right Click Project Folder in Package Explorer > Coverage As > JUnit Test

### Unit and Integration Tests 

All tests use a local H2 database which is reset before each test case, this insures test cases do not affect each other.

```
	@BeforeEach
	void setUp() throws Exception {
		task = new Task(1L, "MOT", LocalDate.of(2021, 7, 1), "Due", new Vehicle());
	}
	
	@Test
	void testSetId() {
		task.setId(2L);
		assertThat(task.getId()).isEqualTo(2L);
	}
```
Simple JUnit tests are used to test base class like the `Task` class.  Above it shows a task object is created before every test case ensuring tests dont affect other tests.

```
	@Test
	void testGetVehicleById() {
		Optional<Vehicle> optionalVehicle = Optional.ofNullable(new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220));
		Vehicle foundVehicle = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		VehicleDTO vehicleDTO = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		// GIVEN
		Long id = 1L;
		// WHEN
		Mockito.when(this.repository.findById(id)).thenReturn(optionalVehicle);
		Mockito.when(this.mapper.map(foundVehicle, VehicleDTO.class)).thenReturn(vehicleDTO);
		// THEN
		assertThat(this.service.getVehicleById(1L)).isEqualTo(vehicleDTO);
	}
```
Above Mockito is used to mock the controller methods. This test does not fail if the controller methods does not work.  As this test is only ensuring the service method works which is why Mockito is used

```
	@Test
	void testGetVehicleById() throws Exception {
		Vehicle vehicle = new Vehicle(1L, "PB08 BSB", "Porsche", "Macan", "Blue", 258);
		String vehicleAsJSON = this.mapper.writeValueAsString(vehicle);

		RequestBuilder mockRequest = get("/vehicle/getById/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(vehicleAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
```
Above is a controller test for getting a vehicle by ID.  This test uses a H2 database which is reset between test cases.  The test cases can make requests to the application controllers and check what is returned to see if the request was successful.

```
	@Test
	void testCreate() {
		driver.findElement(By.linkText("Enter system")).click();
		driver.findElement(By.cssSelector("main > .btn")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"createVehicleModal\"]")));

		driver.findElement(By.id("registration")).sendKeys("FE65 PKK");
		driver.findElement(By.id("make")).sendKeys("VW");
		driver.findElement(By.id("model")).sendKeys("Golf");
		driver.findElement(By.id("colour")).sendKeys("Black");
		driver.findElement(By.id("horsePower")).sendKeys("220");
		driver.findElement(By.cssSelector("#createVehicleModal .btn-primary")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]")));

		assertEquals("2", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[2]")).getText());
		assertEquals("FE65 PKK", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[3]")).getText());
		assertEquals("VW", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[4]")).getText());
		assertEquals("Golf", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[5]")).getText());
		assertEquals("Black", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[6]")).getText());
		assertEquals("220", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[7]")).getText());
	}
```
Above Selenium is used to test the web front end by simulating a person using the site.  The above test clicks on buttons and inputs data into a form to create a new vehicle record.  After entering a new record the test looks at the HTML to see if the new record has been displayed.

### Sonarqube

Sonarqube was used to help detect bugs and application security vulnerabilities.  Sonarqube found 0 bugs, 4 vulnerabilities and 35 different code smells which can be fixed and refactored.  I fixed the vulnerabilities and 29 code smells.  A code smell and vulnerability sonarqube found is shown below.

```
final int prime = 31;
Declare this local variable with "var" instead.
```
```
public VehicleDTO addVehicle(@RequestBody Vehicle vehicle) {
Replace this persistent entity with a simple POJO or DTO object.
```

## Deployment

The following steps create a jar file and run the application in the git bash window. 

* Open git bash in the application root folder

* Type `mvn clean package`

* Using git bash navigate to the target folder

* Type `java -jar HobbyProject-0.0.1-SNAPSHOT.jar`

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring](https://spring.io/) - API Development Platform
* [SonarQube](https://www.sonarqube.org/) - Static Analysis
* [JUnit](https://junit.org/junit5/) - Unit Testing
* [Mockito](https://site.mockito.org/) - Unit Testing
* [Selenium](https://www.selenium.dev/) - User-Acceptance Testing

## Versioning

* [Git](https://git-scm.com/) - Version Control System
* [GitHub](https://github.com/) - Source Code Management
* [Jira](https://www.atlassian.com/software/jira) - Kanban Board

## Authors

* **Amandeep Bassi** - *Initial work* - [AmanBassi](https://github.com/AmanBassi)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Jordan Harrison
* Alan Davis
* Team ELM
* Amandeep Bassi



