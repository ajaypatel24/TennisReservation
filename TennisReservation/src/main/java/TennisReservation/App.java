package TennisReservation;

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

        System.setProperty("webdriver.chrome.driver",
                "/Users/ajaypatel/Desktop/TennisReservation/TennisReservation/src/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get(
                "https://loisirs.montreal.ca/IC3/#/U6510/search/?searchParam=%7B%22filter%22:%7B%22isCollapsed%22:false,%22value%22:%7B%22dates%22:%5B%222020-08-01T00:00:00.000-04:00%22%5D,%22boroughIds%22:%2217%22%7D%7D,%22search%22:%22tennis%22,%22sortable%22:%7B%22isOrderAsc%22:true,%22column%22:%22facility.name%22%7D%7D&bids=26,35,35");
        System.out.println(driver.getTitle());

        WebDriverWait wait = new WebDriverWait(driver, 15);

        String datexpath = "//*[@id='u6510_btnFacilityReservationSearchReserveDateCalendar']";
        
        String ParkDropdown = "//*[@id='u6510_selectFacilityReservationSearchSite']";
        String ParkMarcelLaurin = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[20]";
        String ParkMarlBorough = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[21]";
        String ParkNoelSud = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[25]";
        String specificdate = "//span[text()='12']";
        String removedate = "//*[@id='formSearch']/u2000-search-header/div/div[2]/div[2]/div/div/div[4]/ul/li/i";
        String specificdateselector = "button#datepicker-2748-6968-24";
//*[@id="datepicker-1405-2092-9"]/button
//*[@id="datepicker-640-9096-9"]/button
//*[@id="datepicker-640-2743-9"]/button
//*[@id="datepicker-640-1009-9"]/button
//*[@id="datepicker-637-812-9"]/button
//#datepicker-2748-6968-24 > button
        modalLoad(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(removedate)));
        WebElement dateChoice = driver.findElement(By.xpath(datexpath));
        // if (dateChoice.isDisplayed() && dateChoice.isEnabled()) {
        dateChoice.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(specificdate)));
        WebElement date = driver.findElement(By.xpath(specificdate));
        date.click();

        modalLoad(driver);
        // }
        /*
         * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
         * specificdate))); WebElement Date =
         * driver.findElement(By.xpath(specificdate)); Date.click();
         */
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        WebElement Site = driver.findElement(By.xpath(ParkDropdown));
        Site.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ParkMarcelLaurin)));
        WebElement Park = driver.findElement(By.xpath(ParkMarcelLaurin));
        Park.click();
        
        WebElement RemoveDate = driver.findElement(By.xpath(removedate));
        RemoveDate.click();

        //driver.close();


    }

    public static void modalLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        String modalloading = "//*[@id='U2000_BusyIndicator']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(modalloading)));
        System.out.println("modal visible");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(modalloading)));
    }
}
