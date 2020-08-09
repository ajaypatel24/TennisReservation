package com.example.TennisReservation.Controller;

import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.Cipher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Base64;

/**
 * Reservation Controller
 */
@RestController
public class TennisReservationController extends AppController {

    
    @RequestMapping("/Run/{Park}/{Day}/{StartTime}/{EndTime}")
    public Map<String, String> execute(@PathVariable("Park") String park, @PathVariable("Day") String day,
            @PathVariable("StartTime") String timeStart, @PathVariable("EndTime") String timeEnd) {

        String password = this.sendPass();
        
        System.setProperty("webdriver.chrome.driver", "/Users/ajaypatel/Desktop/TennisReservation/TennisReservation/src/chromedriver");
  
        WebDriver driver = new ChromeDriver();
        driver.get("https://loisirs.montreal.ca/IC3/#/U6510/search/?searchParam=%7B%22filter%22:%7B%22isCollapsed%22:false,%22value%22:%7B%22dates%22:%5B%222020-08-01T00:00:00.000-04:00%22%5D,%22boroughIds%22:%2217%22%7D%7D,%22search%22:%22tennis%22,%22sortable%22:%7B%22isOrderAsc%22:true,%22column%22:%22facility.name%22%7D%7D&bids=26,35,35");
        
        System.out.println(driver.getTitle());

        Map<String,String> res = new HashMap<>();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebDriverWait RapidCheck = new WebDriverWait(driver, 3);

        //Filters
        String datexpath = "//*[@id='u6510_btnFacilityReservationSearchReserveDateCalendar']";
        
        String ParkDropdown = "//*[@id='u6510_selectFacilityReservationSearchSite']";

        String SelectedPark = "";
        String ParkMarcelLaurin = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[20]";
        String ParkMarlBorough = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[21]";
        String ParkNoelSud = "//*[@id='u6510_selectFacilityReservationSearchSite']/option[25]";

        switch(park) {
            case "Marcel":
                SelectedPark = ParkMarcelLaurin;
                break;
            case "Marl":
                SelectedPark = ParkMarlBorough;
                break;
            case "Noel":
                SelectedPark = ParkNoelSud;
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

       
        String okButton = "/html/body/div[4]/div/div/div[3]/button";
        String ContinueSearch = "//*[@id='u3600_btnCartMemberContinue']";

        modalLoad(driver);

        waitThenClick(driver, datexpath);

        //gets list of all parts of calendar that match, in the case of two that match, create List and choose last occurence
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(specificdate)));
        List<WebElement> dateChoice = driver.findElements(By.xpath(specificdate)); 
        //dateChoice.get(dateChoice.size()-1).click();
        dateChoice.get(0).click();

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
            res.put("Status", "No Courts");
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
        reserveFlow(driver, Court);

        //if reserved court violates back to back booking
        try {
            RapidCheck.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(okButton)));
            driver.findElement(By.xpath(okButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ContinueSearch)));
            driver.findElement(By.xpath(ContinueSearch)).click();
            driver.navigate().back();
            count++;
            Court = "//*[@id='searchResult']/div[2]/div/table/tbody/tr[" + count + "]/td[3]";
            reserveFlow(driver, Court);

        }
        catch (Exception e) {

        }

        //Confirm booking
        waitThenClick(driver, ConfirmerPanier);
       
        modalLoad(driver);

        waitThenClick(driver, SectionPanierTerminee);

        modalLoad(driver);

        waitThenClick(driver, ConditionOne);
        waitThenClick(driver, ConditionTwo);

        waitThenClick(driver, FinalConfirmation);
        driver.close();
        
        res.put("Status", "Success");
        return res;
        
    }

    public static void reserveFlow(WebDriver driver, String Court) {
        String DateInformation = "//*[@id='wrapper']/section/div/main/ng-view/div/otium-facility-reservation-view/div[1]/div[2]/div[2]/div[1]/div/p/span[1]";
        String Reserver = "//*[@id='u6510_btnReserveSecond']";
        String User = "//*[@id='u3600_btnSelect0']";
        
        waitThenClick(driver, Court);
        modalLoad(driver);
        String info = driver.findElement(By.xpath(DateInformation)).getText();
        System.out.println(info);
        waitThenClick(driver, Reserver);
        modalLoad(driver);
        waitThenClick(driver, User);
        modalLoad(driver);
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