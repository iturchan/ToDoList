import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorCheckTest {
    @Test
    public void isValidDateTest() {
        assertFalse(ErrorCheck.isValidDateCheck("2024"));
        assertTrue(ErrorCheck.isValidDateCheck("2024-12-12"));
    }
    @Test
    public void idUpdateErrorCheckTest(){

    }

}