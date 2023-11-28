import junit.framework.TestCase;

public class Q1MainTest extends TestCase {
    public void testName() {
        String inputString = "Hello, boy!";
        String[] redactedWords = new String[]{"boy"};
        String result = "Hello, ***!";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
}