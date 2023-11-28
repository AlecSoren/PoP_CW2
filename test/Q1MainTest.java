import junit.framework.TestCase;

import java.util.ArrayList;

public class Q1MainTest extends TestCase {
    public void testBasic() {
        TestData testData = new TestData();
        for (int i = 0; i < testData.length; i++) {
            String inputString = testData.inputStringsArray.get(i);
            String[] redactedWords = testData.redactedWordsArray.get(i);
            String result = testData.expectedResultsArray.get(i);
            assertEquals(result, Q1Main.redact(inputString, redactedWords));
        }
    }
}

class TestData {
    public ArrayList<String> inputStringsArray = new ArrayList<>();
    public ArrayList<String[]> redactedWordsArray = new ArrayList<>();
    public ArrayList<String> expectedResultsArray = new ArrayList<>();
    public int length;

    void addTestCase (String inputString, String[] redactedWords, String expectedResult) {
        inputStringsArray.add(inputString);
        redactedWordsArray.add(redactedWords);
        expectedResultsArray.add(expectedResult);
    }

    TestData () {
        addTestCase("Hello, boy!", new String[]{"boy"}, "Hello, ***!");
        addTestCase("Hello, girl!", new String[]{"girl"}, "Hello, ****!");

        length = inputStringsArray.size();
    }
}