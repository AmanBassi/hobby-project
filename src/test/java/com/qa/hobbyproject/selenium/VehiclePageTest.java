package com.qa.hobbyproject.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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

	@Test
	void testRead() {
		driver.findElement(By.linkText("Enter system")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")));

		assertEquals("1", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[2]")).getText());
		assertEquals("PB08 BSB", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[3]")).getText());
		assertEquals("Porsche", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[4]")).getText());
		assertEquals("Macan", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[5]")).getText());
		assertEquals("Blue", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[6]")).getText());
		assertEquals("258", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[7]")).getText());
	}

	@Test
	void testUpdate() {
		driver.findElement(By.linkText("Enter system")).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")));
		driver.findElement(By.cssSelector(".btn-primary:nth-child(1)")).click();
		String originalRow = driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")).getText();

		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"updateVehicleModal\"]")));

		driver.findElement(By.id("updateVehicleModal")).click();
		driver.findElement(By.id("updateRegistration")).clear();
		driver.findElement(By.id("updateRegistration")).sendKeys("BP14 NRE");
		driver.findElement(By.id("updateMake")).clear();
		driver.findElement(By.id("updateMake")).sendKeys("Suzuki");
		driver.findElement(By.id("updateModel")).clear();
		driver.findElement(By.id("updateModel")).sendKeys("Swift");
		driver.findElement(By.id("updateColour")).clear();
		driver.findElement(By.id("updateColour")).sendKeys("Grey");
		driver.findElement(By.id("updateHorsePower")).clear();
		driver.findElement(By.id("updateHorsePower")).sendKeys("90");
		driver.findElement(By.id("updateVehicleButton")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/main/div[1]/table/tbody/tr"), originalRow));

		assertEquals("1", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[2]")).getText());
		assertEquals("BP14 NRE", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[3]")).getText());
		assertEquals("Suzuki", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[4]")).getText());
		assertEquals("Swift", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[5]")).getText());
		assertEquals("Grey", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[6]")).getText());
		assertEquals("90", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[7]")).getText());
	}

	@Test
	void testDelete() {
		driver.findElement(By.linkText("Enter system")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[9]/button")));

		String originalRow = driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")).getText();

		driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody/tr/td[9]/button")).click();

		new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/main/div[1]/table/tbody/tr"), originalRow));

		assertTrue(driver.findElements(By.xpath("/html/body/div/main/div[1]/table/tbody/tr")).size() < 1);
		assertEquals("", driver.findElement(By.xpath("/html/body/div/main/div[1]/table/tbody")).getText());
	}

}
