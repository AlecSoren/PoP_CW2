import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Q2Test extends TestCase {
    public void testSizeBasic() {
        SortedLinkedList myList = new SortedLinkedList();
        assertEquals(myList.size(), 0);
        myList.add("qwerty");
        myList.add("hopeless");
        myList.add("Congrats, your legacy's in ashes! Remembered as the fascist-sympathising cause of climate change and car crashes");
        myList.add("bamboozled");
        String myString = null;
        myList.add(myString);
        Node myNode = new Node("traditional");
        myList.add(myNode);
        assertEquals(myList.size(), 6);
        myList.remove("bamboozled");
        myList.removeFirst();
        myList.removeLast();
        myList.remove(1);
        assertEquals(myList.size(), 2);
    }

    public void testSizeDuplicate() {
        SortedLinkedList myList = new SortedLinkedList();
        myList.add("7up");
        myList.add("France");
        myList.add("france");
        myList.add("7UP");
        assertEquals(myList.size(), 2);
    }

    public void testSizeLarge() {
        SortedLinkedList myList = new SortedLinkedList();
        for (int i = 0; i < 10000; i++) {
            myList.add(Integer.toString(i));
        }
        assertEquals(myList.size(), 10000);
    }

    public void testAddString() {
        SortedLinkedList myList = new SortedLinkedList();
        myList.add("SUCH A LUST FOR REVENGE");
        myList.add("WHO");
        myList.add("Wait");
        myList.add("who's doing this?");
        myList.add("RESTRAIN YOURSELF!");
        myList.add("okey dokey");
        assertEquals(myList.get(0).getString(), "okey dokey");
        assertEquals(myList.get(1).getString(), "RESTRAIN YOURSELF!");
        assertEquals(myList.get(2).getString(), "SUCH A LUST FOR REVENGE");
        myList.add("kaan");
        myList.add("KAAN");
        myList.add("kAAN");
        myList.add("kaaN");
        assertEquals(myList.getFirst().getString(), "kaan");
        assertEquals(myList.size(), 7);
        String nullString = null;
        myList.add(nullString);
        myList.add(nullString);
        assertNull(myList.getFirst().getString());
        assertEquals(myList.size(), 8);
        myList.remove(null);
        myList.add("");
        myList.add("");
        assertEquals(myList.getFirst().getString(), "");
        assertEquals(myList.size(), 8);
        myList.add(" ");
        myList.add("  ");
        assertEquals(myList.get(2).getString(), "  ");
        assertEquals(myList.size(), 10);
        myList.add("@.lasd\tjfdh\n78123;];#");
        myList.add("@.lASd\tjfdh\n78123;];#");
        assertEquals(myList.get(3).getString(), "@.lasd\tjfdh\n78123;];#");
        assertEquals(myList.size(), 11);
        myList.orderDescending();
        myList.add("lovely dear sir");
        assertEquals("lovely dear sir", myList.get(6).getString());
        myList.add(nullString);
        assertNull(myList.getLast().getString());
    }

    public void testAddStringBig() {
        SortedLinkedList myList = new SortedLinkedList();
        myList.add("balustrade");
        myList.add("zamboni");
        myList.add("microfiche");
        for (int i = 0; i < 5000; i++) {
            myList.add("c" + Integer.toString(i));
            myList.add("y" + Integer.toString(i));
        }
        assertEquals(myList.get(5001).getString(), "microfiche");
        assertEquals(myList.getFirst().getString(), "balustrade");
        assertEquals(myList.getLast().getString(), "zamboni");
    }

    public void testAddNode() {
        SortedLinkedList myList = new SortedLinkedList();
        Node myNode = new Node("au revoir");
        myList.add(myNode);
        assertEquals(myList.getFirst(), myNode);
        myList.add(myNode);
        assertEquals(myList.size(), 1);
        myList.add("au revoir");
        assertEquals(myList.size(), 1);
        myNode.setString("until we meet again");
        assertEquals(myList.getFirst().getString(), "until we meet again");

        Node blueNode = new Node("blue");
        Node greenNode = new Node("green");
        Node redNode = new Node(blueNode, "z", greenNode);
        myList.add(redNode);
        assertEquals(myList.getLast().getPrev(), myNode);
        assertEquals("until we meet again", myList.getLast().getNext().getString());
    }

    public void testGetFirst() {
        SortedLinkedList myList = new SortedLinkedList();
        assertNull(myList.getFirst());
        myList.add("See the sunset");
        myList.add("The day is ending");
        assertEquals("See the sunset", myList.getFirst().getString());
        myList.orderDescending();
        assertEquals("The day is ending", myList.getFirst().getString());
    }

    public void testGetLast() {
        SortedLinkedList myList = new SortedLinkedList();
        assertNull(myList.getLast());
        myList.add("Let that yawn out");
        myList.add("There's no pretending");
        assertEquals("There's no pretending", myList.getLast().getString());
        myList.orderDescending();
        assertEquals("Let that yawn out", myList.getLast().getString());
    }

    public void testGet() {
        SortedLinkedList myList = new SortedLinkedList();
        assertNull(myList.get(0));
        assertNull(myList.get(100));
        assertNull(myList.get(-100));
        myList.add("Whatever the weather");
        assertEquals("Whatever the weather", myList.get(0).getString());
        assertNull(myList.get(1));
        assertNull(myList.get(-1));
        myList.add("Come rain or shine");
        assertEquals("Come rain or shine", myList.get(0).getString());
        assertEquals("Whatever the weather", myList.get(1).getString());
        assertNull(myList.get(2));

//        Second verse, same as the first but now in reverse
        myList = new SortedLinkedList();
        myList.orderDescending();
        assertNull(myList.get(0));
        assertNull(myList.get(100));
        assertNull(myList.get(-100));
        myList.add("Blather");
        assertEquals("Blather", myList.get(0).getString());
        assertNull(myList.get(1));
        assertNull(myList.get(-1));
        myList.add("Bluster");
        assertEquals("Bluster", myList.get(0).getString());
        assertEquals("Blather", myList.get(1).getString());
        assertNull(myList.get(2));
    }

    public void testIsPresent() {
        SortedLinkedList myList = new SortedLinkedList();
        assertFalse(myList.isPresent("hellooooo"));

        myList.add("master yoda");
        assertTrue(myList.isPresent("master yoda"));
        assertFalse(myList.isPresent("you survived"));

        assertFalse(myList.isPresent("MASTER YODA"));
        assertFalse(myList.isPresent("Master yoda"));
        assertFalse(myList.isPresent("master yodA"));
        assertFalse(myList.isPresent(" master yoda"));
        assertFalse(myList.isPresent("\nmaster yoda"));

        assertFalse(myList.isPresent(""));
        myList.add("");
        assertTrue(myList.isPresent(""));
        assertFalse(myList.isPresent(" "));
        myList.add(" ");
        assertTrue(myList.isPresent(" "));
        assertFalse(myList.isPresent(null));
        myList.add(new Node(null));
        assertTrue(myList.isPresent(null));

        for (int i = 0; i < 5000; i++) {
            myList.add("c" + Integer.toString(i));
            myList.add("y" + Integer.toString(i));
        }
        assertTrue(myList.isPresent("y4999"));
    }

    public void testRemoveFirst() {
        SortedLinkedList myList = new SortedLinkedList();
        assertFalse(myList.removeFirst());
        myList.add("hello there");
        assertTrue(myList.removeFirst());
        assertFalse(myList.removeFirst());

        myList.add("abracadabra");
        myList.add("hocus pocus");
        assertTrue(myList.removeFirst());
        assertEquals("hocus pocus", myList.getFirst().getString());

        myList.orderDescending();
        myList.add("bibbity bobbity boo");
        assertTrue(myList.removeFirst());
        assertEquals("bibbity bobbity boo", myList.getFirst().getString());
        assertTrue(myList.removeFirst());
        assertFalse(myList.removeFirst());

        for (int i = 0; i < 5000; i++) {
            myList.add("c" + Integer.toString(i));
            myList.add("y" + Integer.toString(i));
        }
        for (int i = 0; i < 10000; i++) {
            assertTrue(myList.removeFirst());
        }
        assertFalse(myList.removeFirst());
    }

    public void testRemoveLast() {
        SortedLinkedList myList = new SortedLinkedList();
        assertFalse(myList.removeLast());
        myList.add("bleak");
        assertTrue(myList.removeLast());
        assertFalse(myList.removeLast());

        myList.add("dreary");
        myList.add("grim");
        assertTrue(myList.removeLast());
        assertEquals("dreary", myList.getFirst().getString());

        myList.orderDescending();
        myList.add("dire");
        assertTrue(myList.removeLast());
        assertEquals("dreary", myList.getFirst().getString());
        assertTrue(myList.removeLast());
        assertFalse(myList.removeLast());

        for (int i = 0; i < 5000; i++) {
            myList.add("c" + Integer.toString(i));
            myList.add("y" + Integer.toString(i));
        }
        for (int i = 0; i < 10000; i++) {
            assertTrue(myList.removeLast());
        }
        assertFalse(myList.removeLast());
    }

    public void testRemoveIndex() {
        SortedLinkedList myList = new SortedLinkedList();
        assertFalse(myList.remove(0));
        assertFalse(myList.remove(100));
        assertFalse(myList.remove(-10));
        myList.add("Kazuo Ishiguro");
        assertFalse(myList.remove(1));
        assertFalse(myList.remove(-1));
        assertTrue(myList.remove(0));
        assertFalse(myList.remove(0));
        myList.add("David Mitchell");
        myList.add("Hilary Mantel");
        myList.add("Margaret Atwood");
        myList.add("Naomi Alderman");
        assertTrue(myList.remove(0));
        assertEquals("Hilary Mantel", myList.getFirst().getString());
        assertTrue(myList.remove(2));
        assertEquals("Margaret Atwood", myList.getLast().getString());
        assertFalse(myList.remove(2));

//        Second verse, same as the first but now in reverse
        myList = new SortedLinkedList();
        myList.orderDescending();
        assertFalse(myList.remove(0));
        assertFalse(myList.remove(100));
        assertFalse(myList.remove(-10));
        myList.add("Never Let Me Go");
        assertFalse(myList.remove(1));
        assertFalse(myList.remove(-1));
        assertTrue(myList.remove(0));
        assertFalse(myList.remove(0));
        myList.add("Cloud Atlas");
        myList.add("The Handmaid's Tale");
        myList.add("The Power");
        myList.add("Wolf Hall");
        assertTrue(myList.remove(0));
        assertEquals("The Power", myList.getFirst().getString());
        assertTrue(myList.remove(2));
        assertEquals("The Handmaid's Tale", myList.getLast().getString());
        assertFalse(myList.remove(2));

        for (int i = 0; i < 5000; i++) {
            myList.add("c" + Integer.toString(i));
            myList.add("y" + Integer.toString(i));
        }
        for (int i = 10001; i > -1; i--) {
            assertTrue(myList.remove(i));
        }
        assertFalse(myList.remove(0));
    }

    public void testRemoveString() {
        SortedLinkedList myList = new SortedLinkedList();
        assertFalse(myList.remove("content"));

        myList.add("bacon");
        assertFalse(myList.remove("seagulls"));
        assertFalse(myList.remove("BACON"));
        assertFalse(myList.remove("Bacon"));
        assertFalse(myList.remove("bacoN"));
        assertFalse(myList.remove(" bacon"));
        assertFalse(myList.remove("\nbacon"));
        assertTrue(myList.remove("bacon"));

        assertFalse(myList.remove(""));
        myList.add("");
        assertTrue(myList.remove(""));
        assertFalse(myList.remove(" "));
        myList.add(" ");
        assertTrue(myList.remove(" "));
        assertFalse(myList.remove(null));
        myList.add(new Node(null));
        assertTrue(myList.remove(null));

        for (int i = 0; i < 5000; i++) {
            myList.add("c" + Integer.toString(i));
            myList.add("y" + Integer.toString(i));
        }
        assertTrue(myList.remove("y4999"));
    }

    public void testOrder() {
        SortedLinkedList myList = new SortedLinkedList();
        myList.add("I'm number one");
        myList.add("I'm number two");
        assertEquals("I'm number one", myList.getFirst().getString());
        myList.orderDescending();
        assertEquals("I'm number two", myList.getFirst().getString());
        myList.orderAscending();
        assertEquals("I'm number one", myList.getFirst().getString());
        for (int i = 0; i < 5000; i++) {
            myList.orderDescending();
            myList.orderAscending();
        }
        assertEquals("I'm number one", myList.getFirst().getString());
        myList.orderAscending();
        assertEquals("I'm number one", myList.getFirst().getString());

        myList.orderDescending();
        assertEquals("I'm number one", myList.getLast().getString());
        assertEquals("I'm number one", myList.get(1).getString());
        assertEquals("I'm number two", myList.get(0).getString());
    }

    public void testPrintGeneral() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        System.out.println("hello");
        assertEquals("hello\r\n", outContent.toString());

        System.setOut(originalOut);
    }

    public void testPrintBasic() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        SortedLinkedList myList = new SortedLinkedList();
        myList.print();
        assertEquals("", outContent.toString());

        System.setOut(originalOut);
    }

    public void testPrintAscending() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        SortedLinkedList myList = new SortedLinkedList();
        myList.add("\nThis string starts with a newline character");
        myList.add("\tThis string starts with a tab character");
        myList.add("0 This string starts with a digit");
        myList.add("5 This string starts with a digit");
        myList.add("10 This string starts with a digit");
        myList.add(" This string starts with a space");
        myList.add("this string starts with a lowercase letter");
        myList.add("yo");
        myList.add("AAAAAAA");
        myList.add("bbbbbbb");
        myList.add("Zebra!");
        myList.add("This string starts with an uppercase letter");
        myList.add("!@[] Punctuation is fun!");
        myList.add("#######~~~~~///////");
        myList.add("aa");
        myList.add("ab");
        myList.add("Aa");
        myList.add("Ab");
        myList.add("a");
        myList.add("");
        myList.add(new Node(null));
        myList.print();
        String expectedResult = "\n\n" +
                "\tThis string starts with a tab character\n" +
                "\nThis string starts with a newline character\n" +
                " This string starts with a space\n" +
                "!@[] Punctuation is fun!\n" +
                "#######~~~~~///////\n" +
                "0 This string starts with a digit\n" +
                "10 This string starts with a digit\n" +
                "5 This string starts with a digit\n" +
                "a\n" +
                "aa\n" +
                "AAAAAAA\n" +
                "ab\n" +
                "bbbbbbb\n" +
                "this string starts with a lowercase letter\n" +
                "This string starts with an uppercase letter\n" +
                "yo\n" +
                "Zebra!\r\n";
        assertEquals(expectedResult, outContent.toString());

        System.setOut(originalOut);
    }

    public void testPrintDescending() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        SortedLinkedList myList = new SortedLinkedList();
        myList.add("\nThis string starts with a newline character");
        myList.add("\tThis string starts with a tab character");
        myList.add("0 This string starts with a digit");
        myList.add("5 This string starts with a digit");
        myList.add("10 This string starts with a digit");
        myList.add(" This string starts with a space");
        myList.add("this string starts with a lowercase letter");
        myList.add("yo");
        myList.add("AAAAAAA");
        myList.add("bbbbbbb");
        myList.add("Zebra!");
        myList.add("This string starts with an uppercase letter");
        myList.add("!@[] Punctuation is fun!");
        myList.add("#######~~~~~///////");
        myList.add("aa");
        myList.add("ab");
        myList.add("Aa");
        myList.add("Ab");
        myList.add("a");
        myList.add("");
        myList.add(new Node(null));

        myList.orderDescending();
        myList.print();
        String expectedResult = "Zebra!\n" +
                "yo\n" +
                "This string starts with an uppercase letter\n" +
                "this string starts with a lowercase letter\n" +
                "bbbbbbb\n" +
                "ab\n" +
                "AAAAAAA\n" +
                "aa\n" +
                "a\n" +
                "5 This string starts with a digit\n" +
                "10 This string starts with a digit\n" +
                "0 This string starts with a digit\n" +
                "#######~~~~~///////\n" +
                "!@[] Punctuation is fun!\n" +
                " This string starts with a space\n" +
                "\n" +
                "This string starts with a newline character\n" +
                "\tThis string starts with a tab character\n\n\r\n";
        assertEquals(expectedResult, outContent.toString());

        System.setOut(originalOut);
    }
}