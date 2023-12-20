import junit.framework.TestCase;

public class Q4Test extends TestCase {

    VigenereCipher myCipher = new VigenereCipher();

    /*private static String unEscapeString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++)
            switch (s.charAt(i)){
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                case '\b': sb.append("\\b"); break;
                case '\r': sb.append("\\r"); break;
                case '\f': sb.append("\\f"); break;
                case '\'': sb.append("\\'"); break;
                case '\"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                default: sb.append(s.charAt(i));
            }
        return sb.toString();
    }*/

    public void testEncryptBasic() {
        String messagePath = "Q4 Files/encrypt_check.txt";
        String keyPath = "Q4 Files/key_check.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        String expected = "SFB'K RAZTKI HU TQEB EDLF TOPQ!\n" +
                "UIYSXNIEQ LUJ KOCL NM BTIM UIEMXCUXEHKU.";
        assertEquals(expected, result);
    }

    public void testDecryptBasic() {
        String messagePath = "Q4 Files/decrypt_check.txt";
        String keyPath = "Q4 Files/key_check.txt";
        String result = myCipher.decrypt(messagePath, keyPath);
        String expected = "DON'T FORGET TO TEST YOUR CODE!\n" +
                "OTHERWISE YOU WILL BE VERY DISAPPOINTED.";
        assertEquals(expected, result);
    }

    //Tests each possible message letter with each possible key letter
    public void testEncryptAlphabet() {
        String messagePath = "Q4 Files/alphabet_message.txt";
        String keyPath = "Q4 Files/alphabet_key.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        StringBuilder expectedBuilder = new StringBuilder();
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 26; j++) {
                expectedBuilder.append((char) (65 + (j * 2 + i) % 26));
            }
            if (i < 51) {
                expectedBuilder.append(" ");
            }
        }
        String expected = expectedBuilder.toString();
        assertEquals(expected, result);
    }

    //Tests each possible message letter with each possible key letter
    public void testDecryptAlphabet() {
        String messagePath = "Q4 Files/alphabet_encrypted_message.txt";
        String keyPath = "Q4 Files/alphabet_key.txt";
        String result = myCipher.decrypt(messagePath, keyPath);
        StringBuilder expectedBuilder = new StringBuilder();
        for (int i = 0; i < 52; i++) {
            expectedBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            if (i < 51) {
                expectedBuilder.append(" ");
            }
        }
        String expected = expectedBuilder.toString();
        assertEquals(expected, result);
    }

    public void testEncryptEmptyMessage() {
        String messagePath = "Q4 Files/empty.txt";
        String keyPath = "Q4 Files/key_check.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        String expected = "";
        assertEquals(expected, result);
    }

    public void testEncryptMessageOnlySpecialCharacter() {
        String messagePath = "Q4 Files/only_special_characters.txt";
        String keyPath = "Q4 Files/key_check.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        String expected = "200&^%$^\"£\r\n" +
                "\r\n" +
                "\r\n" +
                "\r\n" +
                "     43728\r\n" +
                "\r\n" +
                "#\r\n" +
                "    ---------\r\n" +
                "    _____";
        assertEquals(expected, result);
    }

    public void testEncryptEmptyKey() {
        String messagePath = "Q4 Files/encrypt_check.txt";
        String keyPath = "Q4 Files/empty.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        assertNull(result);
    }

    public void testEncryptKeyLowerCase() {
        String messagePath = "Q4 Files/encrypt_check.txt";
        String keyPath = "Q4 Files/key_lower_case.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        String expected = "SFB'K RAZTKI HU TQEB EDLF TOPQ!\n" +
                "UIYSXNIEQ LUJ KOCL NM BTIM UIEMXCUXEHKU.";
        assertEquals(expected, result);
    }

    public void testEncryptKeySpecialCharacters() {
        String messagePath = "Q4 Files/encrypt_check.txt";
        String keyPath = "Q4 Files/key_special_characters.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        String expected = "SFB'K RAZTKT TO TEST YDLF TOPQ!\n" +
                "UTHERWISE YOJ KOCL NM BERY DISAPPOXEHKU.";
        assertEquals(expected, result);
    }

    public void testEncryptKeyOnlySpecialCharacters() {
        String messagePath = "Q4 Files/encrypt_check.txt";
        String keyPath = "Q4 Files/only_special_characters.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        assertEquals("DON'T FORGET TO TEST YOUR CODE!\n" +
                "OTHERWISE YOU WILL BE VERY DISAPPOINTED.", result);
    }

    public void testEncryptKeyLonger() {
        String messagePath = "Q4 Files/encrypt_check.txt";
        String keyPath = "Q4 Files/key_longer.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        String expected = "SFB'K RAZTKI HU TQEB EDLF TOPQ!\n" +
                "UIYSXNIEQ LUJ KOCL NM BTIM UIEMXCUXEHKU.";
        assertEquals(expected, result);
    }

    public void testMessageFileMissing() {
        String messagePath = "Q4 Files/missing_file.txt";
        String keyPath = "Q4 Files/key_check.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        assertNull(result);
    }

    public void testKeyFileMissing() {
        String messagePath = "Q4 Files/encrypt_check.txt";
        String keyPath = "Q4 Files/missing_file.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        assertNull(result);
    }

    public void testMessageFileNotText() {
        String messagePath = "Q4 Files/picture_of_a_dog.jpg";
        String keyPath = "Q4 Files/key_check.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        assertEquals("", result);
    }

    public void testKeyFileNotText() {
        String messagePath = "Q4 Files/encrypt_check.txt";
        String keyPath = "Q4 Files/picture_of_a_dog.jpg";
        String result = myCipher.encrypt(messagePath, keyPath);
        assertNull(result);
    }

    public void testBigMessage() {
        String messagePath = "Q4 Files/big_message.txt";
        String keyPath = "Q4 Files/big_message_key.txt";
        String result = myCipher.encrypt(messagePath, keyPath);
        String expectedSubstring = "LWLG POB SET QJPIHZLB ZLQK HDJDBOU O TSIXOHCNX RRFYKV JDUDL: TVHS B WQWQH SKDJELTK RCFKMVO FESJUD QP ALRLR, OOOQ V SGBHV MZTVRHKXW TYKNDAK QUPMCNK.";
        StringBuilder expectedBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            expectedBuilder.append(expectedSubstring);
            if (i < 9999) {
                expectedBuilder.append("\r\n");
            }
        }
        assertEquals(expectedBuilder.toString(), result);
    }

}