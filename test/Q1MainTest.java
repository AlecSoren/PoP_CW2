import junit.framework.TestCase;

import java.util.ArrayList;

public class Q1MainTest extends TestCase {
    public void testBasic() {
        String inputString = "Hello, boy!";
        String[] redactedWords = new String[]{"boy"};
        String result = "Hello, ***!";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
}