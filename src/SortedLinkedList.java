public class SortedLinkedList implements SortedList {
    final Node sentinelNode;
    private boolean orderIsAscending;
    private int size;

    public SortedLinkedList () {
        sentinelNode = new Node(null);
        sentinelNode.setPrev(sentinelNode);
        sentinelNode.setNext(sentinelNode);
        orderIsAscending = true;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(String string) {
        Node newNode = new Node(string);
        add(newNode);
    }

    @Override
    public void add(Node node) {
        String newNodeString = node.getString();
        Node nodeAfter = sentinelNode.getNext();
        while (nodeAfter != sentinelNode) {
            String stringAfter = nodeAfter.getString();
            if (stringAfter == null) {
                if (newNodeString == null) {
                    return;
                } else {
                    break;
                }
            } else if (newNodeString == null) {
                nodeAfter = nodeAfter.getNext();
            } else {
                int stringComparisonResult = stringAfter.compareToIgnoreCase(newNodeString);
                if (stringComparisonResult == 0) {
                    return;
                } else if (stringComparisonResult < 0) {
                    nodeAfter = nodeAfter.getNext();
                } else {
                    break;
                }
            }
        }
        Node nodeBefore = nodeAfter.getPrev();
        node.setNext(nodeAfter);
        node.setPrev(nodeBefore);
        nodeBefore.setNext(node);
        nodeAfter.setPrev(node);
        size++;
    }

    @Override
    public Node getFirst() {
        if (size == 0) {
            return null;
        }
        if (orderIsAscending) {
            return sentinelNode.getNext();
        } else {
            return sentinelNode.getPrev();
        }
    }

    @Override
    public Node getLast() {
        if (size == 0) {
            return null;
        }
        if (orderIsAscending) {
            return sentinelNode.getPrev();
        } else {
            return sentinelNode.getNext();
        }
    }

    @Override
    public Node get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node node = sentinelNode;
        for (int i = 0; i < index + 1; i++) {
            if (orderIsAscending) {
                node = node.getNext();
            } else {
                node = node.getPrev();
            }
        }
        return node;
    }

    @Override
    public boolean isPresent(String string) {
        Node currentNode = sentinelNode.getNext();
        while (currentNode != sentinelNode) {
            String currentString = currentNode.getString();
            if (string == null) {
                if (currentString == null) {
                    return true;
                }
            } else if (currentString != null && currentString.equals(string)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public boolean removeFirst() {
        return remove(0);
    }

    @Override
    public boolean removeLast() {
        return remove(size - 1);
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        Node node = sentinelNode;
        for (int i = 0; i < index + 1; i++) {
            if (orderIsAscending) {
                node = node.getNext();
            } else {
                node = node.getPrev();
            }
        }
        Node nodeBefore = node.getPrev();
        Node nodeAfter = node.getNext();
        nodeBefore.setNext(nodeAfter);
        nodeAfter.setPrev(nodeBefore);
        size--;
        return true;
    }

    @Override
    public boolean remove(String string) {
        Node currentNode = sentinelNode.getNext();
        while (currentNode != sentinelNode) {
            String currentString = currentNode.getString();
            if (string == null) {
                if (currentString == null) {
                    break;
                }
            } else if (currentString != null && currentString.equals(string)) {
                break;
            }
            currentNode = currentNode.getNext();
        }
        if (currentNode == sentinelNode) {
            return false;
        }
        Node nodeBefore = currentNode.getPrev();
        Node nodeAfter = currentNode.getNext();
        nodeBefore.setNext(nodeAfter);
        nodeAfter.setPrev(nodeBefore);
        size--;
        return true;
    }

    @Override
    public void orderAscending() {
        orderIsAscending = true;
    }

    @Override
    public void orderDescending() {
        orderIsAscending = false;
    }

    @Override
    public void print() {
        StringBuilder outputString = null;
        Node node = sentinelNode;
        while (true) {
            if (orderIsAscending) {
                node = node.getNext();
            } else {
                node = node.getPrev();
            }
            if (node == sentinelNode) {
                break;
            }
            if (node.getString() != null) {
                if (outputString == null) {
                    outputString = new StringBuilder(node.getString());
                } else {
                    outputString.append("\n").append(node.getString());
                }
            }
        }
        if (outputString != null) {
            System.out.println(outputString);
        }
    }
}
