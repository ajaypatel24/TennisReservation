package com.example.TennisReservation.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Reservation Controller
 */
@RestController
public class TennisReservationController extends AppController {

    
    @RequestMapping("/Run/{Park}/{Day}/{Month}/{StartTime}/{EndTime}")
    public Map<String, String> execute(@PathVariable("Park") String park, @PathVariable("Day") String day,
            @PathVariable("StartTime") String timeStart, @PathVariable("EndTime") String timeEnd, @PathVariable("Month") String month) {

        String password = this.sendPass();
        
        System.setProperty("webdriver.chrome.driver", "/Users/ajaypatel/Desktop/TennisReservation/TennisReservation/src/chromedriver");
  
        WebDriver driver = new ChromeDriver();
        driver.get("https://loisirs.montreal.ca/IC3/#/U6510/search/?searchParam=%7B%22filter%22:%7B%22isCollapsed%22:false,%22value%22:%7B%22dates%22:%5B%222020-08-01T00:00:00.000-04:00%22%5D,%22boroughIds%22:%2217%22%7D%7D,%22search%22:%22tennis%22,%22sortable%22:%7B%22isOrderAsc%22:true,%22column%22:%22facility.name%22%7D%7D&bids=26,35,35");

        Map<String,String> res = new HashMap<>();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebDriverWait RapidCheck = new WebDriverWait(driver, 3);

        //Filters
        String datexpath = "//*[@id='u6510_btnFacilityReservationSearchReserveDateCalendar']";
        String Monthxapth = "//*[@id='formSearch']/u2000-search-header/div/div[2]/div[2]/div/div/div[4]/div/div/ul/li/div/table/thead/tr[1]/th[2]";
        String September = "//span[text()='" + month + "']";
        String ParkDropdown = "//*[@id='u6510_selectFacilityReservationSearchSite']";

        String SelectedPark = "";
        String ParkName = "";
        String ParkMarcelLaurin = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[20]";
        String ParkMarlBorough = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[21]";
        String ParkNoelSud = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[25]";

        switch(park) {
            case "marcel":
                SelectedPark = ParkMarcelLaurin;
                ParkName = "Marcel-Laurin";
                break;
            case "marl":
                SelectedPark = ParkMarlBorough;
                ParkName = "Marlborough";
                break;
            case "noel":
                SelectedPark = ParkNoelSud;
                ParkName = "Noel-Sud";
                break;
        }
        
        String specificdate = "//span[text()='" + day + "']";
        String removedate = "//*[@id='formSearch']/u2000-search-header/div/div[2]/div[2]/div/div/div[4]/ul/li/i";
        String NoResult = "//*[@id='wrapper']/section/div/main/ng-view/otium-facility-reservation-search/otium-search-footer/div/h5";
        String StartTime = "//*[@id='u6510_edFacilityReservationSearchStartTime']/tbody/tr[2]/td[1]/input";
        String EndTime = "//*[@id='u6510_edFacilityReservationSearchEndTime']/tbody/tr[2]/td[1]/input";

        //Login
        String Connexion = "//*[@id='u2000_btnSignIn']";
        String Courriel = "//*[@id='loginForm:username']";
        String MotDePasse = "//*[@id='loginForm:password']";
        String MeConnecter = "//*[@id='loginForm:loginButton']";

        //Select Court
        int count = 1;
        String Court = "//*[@id='searchResult']/div[2]/div/table/tbody/tr[" + count + "]/td[3]";

        //Select Card and Confirm
        String ConfirmerPanier = "//*[@id='u3600_btnCartMemberCheckout']";

        //Confirmation Page 1
        String SectionPanierTerminee = "//*[@id='u3600_btnCartShoppingCompleteStep']";

        //Confirmation Page 2
        String ConditionOne = "//*[@id='u3600_chkElectronicPaymentCondition']";
        String ConditionTwo = "//*[@id='u3600_chkLocationCondition']";
        String FinalConfirmation = "//*[@id='u3600_btnCartPaymentCompleteStep']";
        String pdfName = "//*[@id='wrapper']/section/div/main/ng-view/div/dl/dd[1]";
       
        String okButton = "/html/body/div[4]/div/div/div[3]/button";
        String ContinueSearch = "//*[@id='u3600_btnCartMemberContinue']";

        modalLoad(driver);

        
        waitThenClick(driver, datexpath);
        waitThenClick(driver, Monthxapth);
        waitThenClick(driver, September);

        //gets list of all parts of calendar that match, in the case of two that match, create List and choose last occurence
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(specificdate)));
        List<WebElement> dateChoice = driver.findElements(By.xpath(specificdate)); 

        if (Integer.parseInt(dateChoice.get(0).getText()) >= 26) {
            dateChoice.get(dateChoice.size()-1).click();
        }
        else { 
            dateChoice.get(0).click();
        }

        modalLoad(driver);
        
        waitThenClick(driver, ParkDropdown);
        waitThenClick(driver, SelectedPark);
        
        WebElement StartRange = driver.findElement(By.xpath(StartTime));
        StartRange.sendKeys(timeStart);
        WebElement EndRange = driver.findElement(By.xpath(EndTime));
        EndRange.sendKeys(timeEnd);

        waitThenClick(driver, removedate);

        modalLoad(driver);
        
        try {
            RapidCheck.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NoResult)));
            res.put("Status", "Failed");
            return res;
        }
        catch (Exception e) {
        }

        //Login flow
        waitThenClick(driver, Connexion);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Courriel)));
        WebElement Email = driver.findElement(By.xpath(Courriel));
        WebElement Pass = driver.findElement(By.xpath(MotDePasse));
        Email.sendKeys("ajaypatel24@hotmail.com");
        Pass.sendKeys(password);
        waitThenClick(driver, MeConnecter);



        modalLoad(driver);

        //reserve court
        List<String> DateCourtConfirmation = new ArrayList<>();
        while (DateCourtConfirmation.size() == 0) {
        
        DateCourtConfirmation = reserveFlow(driver, Court);
        //if reserved court violates back to back booking
        try {
            
            RapidCheck.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(okButton)));
            driver.findElement(By.xpath(okButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ContinueSearch)));
            driver.findElement(By.xpath(ContinueSearch)).click();
            driver.navigate().back();
            DateCourtConfirmation.clear();

        }
        catch (Exception e) {

        }
            count++;
            Court = "//*[@id='searchResult']/div[2]/div/table/tbody/tr[" + count + "]/td[3]";
        } 

        //Confirm booking
        waitThenClick(driver, ConfirmerPanier);
       
        modalLoad(driver);

        waitThenClick(driver, SectionPanierTerminee);

        modalLoad(driver);

        waitThenClick(driver, ConditionOne);
        waitThenClick(driver, ConditionTwo);

        waitThenClick(driver, FinalConfirmation);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pdfName)));
        String currenturl = driver.getCurrentUrl();
        String pdf = currenturl.substring(currenturl.lastIndexOf('=') + 1);
        driver.close();
        String string = DateCourtConfirmation.get(0);
        DateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        res.put("date", targetFormat.format(date));
        res.put("court", DateCourtConfirmation.get(1));
        res.put("time", DateCourtConfirmation.get(2));
        res.put("park", ParkName);
        res.put("confirmationpdf", pdf + ".pdf");
        res.put("Status", "Successfully reserved");
        return res;
        
    }

    public static List<String> reserveFlow(WebDriver driver, String Court) {
        String DateInformation = "//*[@id='wrapper']/section/div/main/ng-view/div/otium-facility-reservation-view/div[1]/div[2]/div[2]/div[1]/div/p/span[1]";
        String CourtInformation = "//*[@id='wrapper']/section/div/main/ng-view/div/ul/li[2]/span";
        String Reserver = "//*[@id='u6510_btnReserveSecond']";
        String User = "//*[@id='u3600_btnSelect0']";
        String time="";
        List<String> res = new ArrayList<>();
        waitThenClick(driver, Court);
        modalLoad(driver);
        String info = driver.findElement(By.xpath(DateInformation)).getText();
        String CourtNumber = driver.findElement(By.xpath(CourtInformation)).getText().replaceAll("\\D+","");
        waitThenClick(driver, Reserver);
        modalLoad(driver);
        waitThenClick(driver, User);
        modalLoad(driver);
        info = info.substring(info.indexOf(",")+1).trim();
        time = info.substring(info.indexOf(",")+1).trim();
        String result = info.substring(0, info.indexOf(","));
        res.add(result);
        res.add(CourtNumber);
        res.add(time);
        return res;
    }
    public static void modalLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String modalloading = "//*[@id='U2000_BusyIndicator']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(modalloading)));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(modalloading)));
    }

    public static void waitThenClick(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement dateChoice = driver.findElement(By.xpath(xpath));
        dateChoice.click();
    }



}
