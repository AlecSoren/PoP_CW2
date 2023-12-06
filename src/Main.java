import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        VigenereCipher myCipher = new VigenereCipher();
        String result = myCipher.decrypt("decrypt_check.txt", "key_check.txt");
        System.out.println(result);
    }
}