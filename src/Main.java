import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SortedLinkedList myList = new SortedLinkedList();
        myList.add("SUCH A LUST FOR REVENGE");
        myList.add("WHO???");
        myList.print();
        System.out.println(myList.getFirst().getString());
        System.out.println(myList.getLast().getString());
    }
}