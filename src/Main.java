public class Main {
    public static void main(String[] args) {
        SortedLinkedList myList = new SortedLinkedList();
        myList.orderDescending();
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

    }
}