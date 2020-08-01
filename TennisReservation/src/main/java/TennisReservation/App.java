package TennisReservation;

import java.io.Console;
import java.sql.Date;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Console console = System.console();
        String password = new String(console.readPassword("Enter Password: "));

        System.setProperty("webdriver.chrome.driver", "/Users/ajaypatel/Desktop/TennisReservation/TennisReservation/src/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://loisirs.montreal.ca/IC3/#/U6510/search/?searchParam=%7B%22filter%22:%7B%22isCollapsed%22:false,%22value%22:%7B%22dates%22:%5B%222020-08-01T00:00:00.000-04:00%22%5D,%22boroughIds%22:%2217%22%7D%7D,%22search%22:%22tennis%22,%22sortable%22:%7B%22isOrderAsc%22:true,%22column%22:%22facility.name%22%7D%7D&bids=26,35,35");
        
        System.out.println(driver.getTitle());

        WebDriverWait wait = new WebDriverWait(driver, 15);

        //Filters
        String datexpath = "//*[@id='u6510_btnFacilityReservationSearchReserveDateCalendar']";
        
        String ParkDropdown = "//*[@id='u6510_selectFacilityReservationSearchSite']";

        String ParkMarcelLaurin = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[20]";
        String ParkMarlBorough = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[21]";
        String ParkNoelSud = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[25]";

        String specificdate = "//span[text()='" + 27 + "']";
        String removedate = "//*[@id='formSearch']/u2000-search-header/div/div[2]/div[2]/div/div/div[4]/ul/li/i";

        String StartTime = "//*[@id='u6510_edFacilityReservationSearchStartTime']/tbody/tr[2]/td[1]/input";
        String EndTime = "//*[@id='u6510_edFacilityReservationSearchEndTime']/tbody/tr[2]/td[1]/input";

        //Login
        String Connexion = "//*[@id='u2000_btnSignIn']";
        String Courriel = "//*[@id='loginForm:username']";
        String MotDePasse = "//*[@id='loginForm:password']";
        String MeConnecter = "//*[@id='loginForm:loginButton']";

        //Select Court
        String Court = "//*[@id='u6510_btnButtonReservation1']";

        //Select Card and Confirm
        String User = "//*[@id='u3600_btnSelect0']";
        String ConfirmerPanier = "//*[@id='u3600_btnCartMemberCheckout']";

        //Confirmation Page 1
        String SectionPanierTerminee = "//*[@id='u3600_btnCartShoppingCompleteStep']";

        //Confirmation Page 2
        String ConditionOne = "//*[@id='u3600_chkElectronicPaymentCondition']";
        String ConditionTwo = "//*[@id='u3600_chkLocationCondition']";
        String FinalConfirmation = "//*[@id='u3600_btnCartPaymentCompleteStep']";

        
        modalLoad(driver);

        waitThenClick(driver, datexpath);
        waitThenClick(driver, specificdate);

        modalLoad(driver);
        
        waitThenClick(driver, ParkDropdown);
        waitThenClick(driver, ParkMarcelLaurin);
        

        WebElement StartRange = driver.findElement(By.xpath(StartTime));
        StartRange.sendKeys("17");
        WebElement EndRange = driver.findElement(By.xpath(EndTime));
        EndRange.sendKeys("20");

        waitThenClick(driver, removedate);
        waitThenClick(driver, Connexion);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Courriel)));
        WebElement Email = driver.findElement(By.xpath(Courriel));
        WebElement Pass = driver.findElement(By.xpath(MotDePasse));
        Email.sendKeys("ajaypatel24@hotmail.com");
        Pass.sendKeys(password);
        
        waitThenClick(driver, MeConnecter);

        modalLoad(driver);

        waitThenClick(driver, Court);

        modalLoad(driver);

        waitThenClick(driver, User);

        modalLoad(driver);

        waitThenClick(driver, ConfirmerPanier);
       
        modalLoad(driver);

        waitThenClick(driver, SectionPanierTerminee);

        modalLoad(driver);

        waitThenClick(driver, ConditionOne);
        waitThenClick(driver, ConditionTwo);

        WebElement ConfirmTennisCourt = driver.findElement(By.xpath(FinalConfirmation));
        //ConfirmTennisCourt.click(); //keep this commented out





    }

    public static void modalLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        String modalloading = "//*[@id='U2000_BusyIndicator']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(modalloading)));
        System.out.println("modal visible");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(modalloading)));
    }

    public static void waitThenClick(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement dateChoice = driver.findElement(By.xpath(xpath));
        dateChoice.click();
    }


}
