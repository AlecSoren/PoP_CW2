public class SortedLinkedList implements SortedList {
    private Node firstNode;
    private boolean orderIsAscending;

    public SortedLinkedList () {
        firstNode = null;
        orderIsAscending = true;
    }

    @Override
    public int size() {
        if (firstNode == null) {
            return 0;
        }
        int size = 1;
        Node currentNode = firstNode.getNext();
        while (currentNode != firstNode) {
            size++;
            currentNode = currentNode.getNext();
        }
        return size;
    }

    @Override
    public void add(String string) {
        Node newNode = new Node(string);
        add(newNode);
    }

    @Override
    public void add(Node node) {

        if (firstNode == null) {
            firstNode = node;
            node.setNext(node);
            node.setPrev(node);
            return;
        }

        String newNodeString = node.getString();
        Node nodeAfter = firstNode;
        do {
            int comparisonResult = compareStrings(newNodeString, nodeAfter.getString(), false);
            if (comparisonResult == 0) {return;}
            if (!orderIsAscending) {
                comparisonResult = -comparisonResult;
            }
            if (comparisonResult < 0) {
                if (nodeAfter == firstNode) {
                    firstNode = node;
                }
                break;
            }
            nodeAfter = nodeAfter.getNext();
        }
        while (nodeAfter != firstNode);

        Node nodeBefore = nodeAfter.getPrev();
        node.setNext(nodeAfter);
        node.setPrev(nodeBefore);
        nodeBefore.setNext(node);
        nodeAfter.setPrev(node);
    }

    @Override
    public Node getFirst() {
        return firstNode;
    }

    @Override
    public Node getLast() {
        return (firstNode == null) ? null : firstNode.getPrev();
    }

    @Override
    public Node get(int index) {
        if (firstNode == null || index < 0) {
            return null;
        }
        Node currentNode = firstNode;
        for (int i = 0; i != index; i += (int) Math.signum(index)) {
            currentNode = currentNode.getNext();
            if (currentNode == firstNode) {
                return null;
            }
        }
        return currentNode;
    }

    @Override
    public boolean isPresent(String string) {
        return getNodeByString(string) != null;
    }

    @Override
    public boolean removeFirst() {
        return removeNode(firstNode);
    }

    @Override
    public boolean removeLast() {
        return (firstNode == null) ? false : removeNode(firstNode.getPrev());
    }

    @Override
    public boolean remove(int index) {
        return removeNode(get(index));
    }

    @Override
    public boolean remove(String string) {
        return removeNode(getNodeByString(string));
    }

    @Override
    public void orderAscending() {
        changeOrder(true);
    }

    @Override
    public void orderDescending() {
        changeOrder(false);
    }

    @Override
    public void print() {
        if (firstNode == null) {
            return;
        }
        StringBuilder outputString = null;
        Node node = firstNode;
        do {
            if (node.getString() != null) {
                if (outputString == null) {
                    outputString = new StringBuilder(node.getString());
                } else {
                    outputString.append("\n").append(node.getString());
                }
            }
            node = node.getNext();
        } while (node != firstNode);
        if (outputString != null) {
            System.out.println(outputString);
        }
    }

    /**
     * Determines which string comes first alphabetically.
     *
     * @param string1 the first string
     * @param string2 the second string
     * @param caseSensitive if false, the comparison will ignore case
     * @return A negative number if string1 comes before string2, a positive number if it comes after, or 0 if they are
     * the same
     */
    private int compareStrings(String string1, String string2, boolean caseSensitive) {
        if (string1 == null) {
            return (string2 == null) ? 0 : -1;
        }
        if (string2 == null) {
            return 1;
        }
        return caseSensitive ? string1.compareTo(string2) : string1.compareToIgnoreCase(string2);
    }

    /**
     * Gets the node with the specified string.
     *
     * @param string the string to be found
     * @return The node with the specified string, or null if it isn't in the list
     */
    private Node getNodeByString(String string) {
        if (firstNode == null) {
            return null;
        }
        Node currentNode = firstNode;
        do {
            if (compareStrings(currentNode.getString(), string, true) == 0) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        while (currentNode != firstNode);
        return null;
    }

    /**
     * Removes a specified node from the list.
     *
     * @param node the node to be removed
     * @return true if the node was successfully removed, false if it did not exist
     */
    private boolean removeNode(Node node) {
        if (node == null) {
            return false;
        }
        if (node == firstNode) {
            firstNode = (node.getNext() == node) ? null : node.getNext();
        }
        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());
        node.setNext(null);
        node.setPrev(null);
        return true;
    }

    /**
     * Sets the list order to ascending or descending, and reverses the list if necessary.
     *
     * @param newOrderIsAscending true if you are setting the order to ascending, false if you are setting the order to
     *                            descending
     */
    private void changeOrder(boolean newOrderIsAscending) {
        if (orderIsAscending != newOrderIsAscending && firstNode != null) {
            firstNode = firstNode.getPrev();
            Node currentNode = firstNode;
            do {
                Node previousNode = currentNode;
                currentNode = currentNode.getNext();
                previousNode.setNext(previousNode.getPrev());
                previousNode.setPrev(currentNode);
            }
            while (currentNode != firstNode);
        }
        orderIsAscending = newOrderIsAscending;
    }
}
