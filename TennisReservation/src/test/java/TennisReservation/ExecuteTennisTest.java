package TennisReservation;

import static org.junit.Assert.assertEquals;
import java.io.Console;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Collection;

import com.google.common.base.Ticker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ExecuteTennisTest {
    private String password;
    private String park ;
    private String day ;
    private String timeStart ;
    private String timeEnd;

    private TennisTest performTest;


    @Before
    public void initialize() {
        performTest = new TennisTest();
    }


    public ExecuteTennisTest(String password, String park, String day, String timeStart, String timeEnd) {
        this.password = password;
        this.park = park;
        this.day = day;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
    

    @Parameterized.Parameters
    public static Collection arguements() {
        return Arrays.asList(new Object[][] {
            {"----", "Marcel", "13", "18", "19"}
        });
    }
    
    @Test
    public void tennisTest() {
        boolean result = performTest.TennisTestExecution(password, park, day, timeStart, timeEnd);
        assertEquals(true, result);
    }
}