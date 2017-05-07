package edu.umd.cs.officeours;

import org.junit.Test;

import edu.umd.cs.officeours.model.Day;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testMilToStandard(){
        assertEquals("11:30PM", Day.milToStandard(2330));
        assertEquals("12:30AM", Day.milToStandard(30));
        assertEquals("3:30AM", Day.milToStandard(330));
    }
}