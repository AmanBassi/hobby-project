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
		driver = new ChromeDriver();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get("http://localhost:" + serverPort + "/");
		assertEquals("Hobby Project", driver.getTitle());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate() {
		driver.findElement(By.linkText("Enter system")).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")));
		driver.findElement(By.linkText("Open")).click();
		driver.findElement(By.xpath("/html/body/div/main/button")).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"createVehicleTaskModal\"]")));
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"name\"]")));
		dropdown.selectByValue("Insurance");
		driver.findElement(By.xpath("//*[@id=\"dueDate\"]")).sendKeys("01122021");
		driver.findElement(By.xpath("//*[@id=\"createVehicleTaskModal\"]/div/div/div[3]/button[2]")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[3]")));

		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[3]/td[1]")).getText(), "3");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[3]/td[2]")).getText(), "Insurance");
		assertEquals(driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr[3]/td[3]")).getText(), "2021-12-01");
	}

}
