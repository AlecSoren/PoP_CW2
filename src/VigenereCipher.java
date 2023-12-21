import java.io.File;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class VigenereCipher implements Cipher {

    @Override
    public String encrypt(String message_filename, String key_filename) {
        return encryptOrDecrypt(message_filename, key_filename, true);
    }

    @Override
    public String decrypt(String message_filename, String key_filename){
        return encryptOrDecrypt(message_filename, key_filename, false);
    }

    private String encryptOrDecrypt(String messageFilename, String keyFilename, boolean isEncrypting) {

        String message = readFileAsString(messageFilename);
        if (message == null) {
            return null;
        }

        String key = readFileAsString(keyFilename);
        if (key == null || key.isEmpty()) {
            return null;
        }
        key = key.toUpperCase();

        StringBuilder outputStringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char messageCharacter = message.charAt(i);
            char keyCharacter = key.charAt(i % key.length());
            char shiftedCharacter = shiftCharacter(messageCharacter, keyCharacter, isEncrypting);
            outputStringBuilder.append(shiftedCharacter);
        }
        return outputStringBuilder.toString();
    }

    private String readFileAsString(String filename) {
        String outputString;
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            fileScanner.useDelimiter("\\Z");
            outputString = fileScanner.next();
        } catch (NoSuchElementException e) {
            outputString = "";
        } catch (Exception e) {
            outputString = null;
        }
        return outputString;
    }

    private char shiftCharacter(char messageCharacter, char keyCharacter, boolean isEncrypting) {
        if (!(Character.isAlphabetic(messageCharacter) && Character.isAlphabetic(keyCharacter))) {
            return Character.toUpperCase(messageCharacter);
        }
        if (messageCharacter >= 97) {
            messageCharacter -= 32;
        }
        int alphabetShift;
        if (isEncrypting) {
            alphabetShift = keyCharacter - 65;
        } else {
            alphabetShift = 91 - keyCharacter;
        }
        return (char) ((messageCharacter - 65 + alphabetShift) % 26 + 65);
    }
}