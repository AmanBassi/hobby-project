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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:vehicle-schema.sql", "classpath:vehicle-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class VehiclePageTest {

	private static WebDriver driver;

	@LocalServerPort
	int serverPort;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() throws InterruptedException {
		driver.get("http://localhost:" + serverPort + "/");

		assertEquals("Hobby Project", driver.getTitle());

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

		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[2]")).getText(), "2");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[3]")).getText(), "FE65 PKK");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[4]")).getText(), "VW");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[5]")).getText(), "Golf");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[6]")).getText(), "Black");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[2]/td[7]")).getText(), "220");
	}

	@Test
	void testRead() {
		driver.get("http://localhost:" + serverPort + "/");

		assertEquals("Hobby Project", driver.getTitle());

		driver.findElement(By.linkText("Enter system")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")));

		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[2]")).getText(), "1");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[3]")).getText(), "PB08 BSB");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[4]")).getText(), "Porsche");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[5]")).getText(), "Macan");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[6]")).getText(), "Blue");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[7]")).getText(), "258");
	}

}
