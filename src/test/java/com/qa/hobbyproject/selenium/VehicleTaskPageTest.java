package com.qa.hobbyproject.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:task-schema.sql", "classpath:task-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class VehicleTaskPageTest {

	private static WebDriver driver;

	@LocalServerPort
	int serverPort;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions config = new ChromeOptions().setHeadless(true);
		driver = new ChromeDriver(config);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get("http://localhost:" + serverPort + "/");
		assertEquals("Hobby Project", driver.getTitle());
		driver.findElement(By.linkText("Enter system")).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")));
		driver.findElement(By.linkText("Open")).click();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() {
		driver.findElement(By.xpath("/html/body/div/main/button")).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"createVehicleTaskModal\"]")));
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"name\"]")));
		dropdown.selectByValue("Insurance");
		driver.findElement(By.xpath("//*[@id=\"dueDate\"]")).sendKeys("01122021");
		driver.findElement(By.xpath("//*[@id=\"createVehicleTaskModal\"]/div/div/div[3]/button[2]")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[3]")));

		assertEquals("3", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[3]/td[1]")).getText());
		assertEquals("Insurance", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[3]/td[2]")).getText());
		assertEquals("2021-12-01", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[3]/td[3]")).getText());
	}

	@Test
	void testRead() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]")));

		assertEquals(2, driver.findElements(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")).size());

		assertEquals("1", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]/td[1]")).getText());
		assertEquals("2", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[1]")).getText());
		assertEquals("MOT", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]/td[2]")).getText());
		assertEquals("Service", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[2]")).getText());
		assertEquals("2021-07-01", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]/td[3]")).getText());
		assertEquals("2021-08-02", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[3]")).getText());
	}
	
	@Test
	void testUpdate() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]")));
		String originalRow = driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]")).getText();
		driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]/td[4]/button")).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"updateVehicleTaskModal\"]")));
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"updateName\"]")));
		dropdown.selectByValue("Road Tax");
		driver.findElement(By.xpath("//*[@id=\"updateDueDate\"]")).sendKeys("03092021");
		driver.findElement(By.xpath("//*[@id=\"updateVehicleTaskButton\"]")).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]"), originalRow));
		
		assertEquals("1", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]/td[1]")).getText());
		assertEquals("Road Tax", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]/td[2]")).getText());
		assertEquals("2021-09-03", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]/td[3]")).getText());
	}
	
	@Test
	void testDelete() {
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]")));
		String originalRow = driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]")).getText();
		assertEquals(2, driver.findElements(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")).size());
		
		driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[1]/td[5]/button")).click();
		
		new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/main/div[1]/table/tbody/tr"), originalRow));
		assertEquals(1, driver.findElements(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")).size());
	}

}
