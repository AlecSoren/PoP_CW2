import junit.framework.TestCase;

public class Q1MainTest extends TestCase {
    public void testBasic() {
//        Tests the core functionality of the algorithm
        String inputString = "Hello, boy!";
        String[] redactedWords = new String[]{"boy"};
        String result = "Hello, ***!";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testFirstFinal() {
//        First and last characters are not alphabetic
        String inputString = "?What in Gods name is happening right now?";
        String[] redactedWords = new String[]{"God","Happening","IN"};
        String result = "?What ** Gods name is ********* right now?";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testCaps() {
//        Test that the algorithm handles capitalisation correctly
        String inputString = "I am Alec Mason and I have literally done NOTHING wrong in my ENTIRE LIFE AT ALL just ask Sean Hannity and Jordan B. Peterson thats right Im ALEC mason seaN";
        String[] redactedWords = new String[]{"i","AM","anD","Have","NOTHING","entirE","Life","at","Alec","mason","SEAN","hannitY"};
        String result = "* ** **** ***** *** * **** literally done ******* wrong in my ****** **** ** ALL just ask **** ******* *** Jordan B. Peterson thats right Im **** ***** ****";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testAlphabet() {
//        Test that all characters of the alphabet are handled correctly
        String inputString = "SPHINX OF BLACK QUARTZ, JUDGE MY VOW. the quick brown fox jumps over the lazy dog!";
        String[] redactedWords = new String[]{"sphinx","of","black","quartz","judge","my","vow","THE","QUICK","BROWN","FOX","JUMPS","OVER","THE","LAZY","DOG"};
        String result = "****** ** ***** ******, ***** ** ***. *** ***** ***** *** ***** **** *** **** ***!";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testScunthorpe() {
//        Scunthorpe problem, i.e. a harmless word which contains a redacted word
        String inputString = "Welcome to Scunthorpe, cunt!";
        String[] redactedWords = new String[]{"cunt"};
        String result = "Welcome to Scunthorpe, ****!";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testEmptyInput() {
//        Empty input string and no redacted words
        String inputString = "";
        String[] redactedWords = new String[]{};
        String result = "";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testEmptyInputString() {
//        Empty input string, with redacted words
        String inputString = "";
        String[] redactedWords = new String[]{"Mike","Tyson"};
        String result = "";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testEmptyRedactedWords() {
//        Non-empty input string, with no redacted words
        String inputString = "Here I am dont tread on me";
        String[] redactedWords = new String[]{};
        String result = "Here I am dont tread on me";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testSingleWordInput() {
//        The input string is only a single word and it is not redacted
        String inputString = "antidisestablishmentarianism";
        String[] redactedWords = new String[]{"pope"};
        String result = "antidisestablishmentarianism";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testSingleWordInputRedacted() {
//        The input string is only a single word and it is redacted
        String inputString = "Supercalifragilisticexpialidocious";
        String[] redactedWords = new String[]{"Supercalifragilisticexpialidocious"};
        String result = "**********************************";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testIrrelevantRedactions() {
//        None of the redacted words are in the input string
        String inputString = "Sing once again with me, our strange duet!";
        String[] redactedWords = new String[]{"bomb","gun","plot"};
        String result = "Sing once again with me, our strange duet!";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testRepetitiveInput() {
//        The input string contains duplicate words
        String inputString = "All the faith that he had had had had no effect on the outcome of his life. Buffalo buffalo buffalo buffalo buffalo buffalo buffalo buffalo";
        String[] redactedWords = new String[]{"had","buffalo"};
        String result = "All the faith that he *** *** *** *** no effect on the outcome of his life. ******* ******* ******* ******* ******* ******* ******* *******";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testRepetitiveRedactions() {
//        redactedWords contains duplicated words
        String inputString = "Now there will be fury";
        String[] redactedWords = new String[]{"there","There","FURY","FURY","fury"};
        String result = "Now ***** will be ****";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testRedactEmptyWord() {
//        redactedWords contains an empty string
        String inputString = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?";
        String[] redactedWords = new String[]{"","","woodchuck"};
        String result = "How much wood would a ********* chuck if a ********* could chuck wood?";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }
    public void testWeirdPunctuation() {
//        Input string contains a variety of esoteric characters which we should interpret as punctuation
        String inputString = "\uD83D\uDC7D\uD83D\uDC80\uD83DBoiled onion\uDE08\uD83Dshould be tasty\uDD25\uD83D\uDC2E\uD83D\uDC7B";
        String[] redactedWords = new String[]{"onion"};
        String result = "\uD83D\uDC7D\uD83D\uDC80\uD83DBoiled *****\uDE08\uD83Dshould be tasty\uDD25\uD83D\uDC2E\uD83D\uDC7B";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }

    public void testChatGPTBasicRedaction() {
//        A basic redaction test suggested by ChatGPT
        String inputString = "The quick brown fox jumps over the lazy dog!";
        String[] redactedWords = new String[]{"fox", "jumps", "dog"};
        String result = "The quick brown *** ***** over the lazy ***!";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }

    public void testChatGPTCaseInsensitivity() {
//        A case insensitivity test suggested by ChatGPT
        String inputString = "The Fox jumps over the DOG!";
        String[] redactedWords = new String[]{"fox", "jumps", "dog"};
        String result = "The *** ***** over the ***!";
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }

    public void testBigInput() {
//        Very large inputString and redactedWords
        String substring = "Crazy? I was crazy once. They put me in a room. A rubber room. With rats. And rats make me crazy. ";
        String redactedSubstring = "*****? I was ***** once. They put me in a ****. A rubber ****. With ****. And **** make me *****. ";
        String[] redactedWords = new String[10000];
        for (int i = 0; i < redactedWords.length; i++) {
            if (i%3 == 0) {
                redactedWords[i] = "crazy";
            } else if (i%3 == 1) {
                redactedWords[i] = "room";
            } else {
                redactedWords[i] = "rats";
            }
        }

        String inputString = substring.repeat(10000);
        String result = redactedSubstring.repeat(10000);
        assertEquals(result, Q1Main.redact(inputString, redactedWords));
    }

    public void testInputStringNull() {
        String[] redactedWords = new String[]{"fox", "jumps", "dog"};
        assertNull(Q1Main.redact(null, redactedWords));
    }

    public void testRedactWordsNull() {
        String input = "hello!";
        assertNull(Q1Main.redact(input, null));
    }

    public void testWordNull() {
        String input = "I really can't stay\nBut baby it's cold outside\nThe cops are on their way\nBut baby it's cold outside";
        String[] redactedWords = new String[]{null, "cold", null, "cops", null};
        String result = "I really can't stay\nBut baby it's **** outside\nThe **** are on their way\nBut baby it's **** outside";
        assertEquals(result, Q1Main.redact(input, redactedWords));
    }
}