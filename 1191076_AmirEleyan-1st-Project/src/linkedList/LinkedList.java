package linkedList;

public class LinkedList<T extends Comparable<T>> implements Listable<T> {

    private Node<T> head, tail;

    private int length = 0;

    public LinkedList() {
        this.head = this.tail = null;
    }

    public Node<T> getHead() {
        return this.head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    private void setHead(Node<T> head) {
        this.head = head;
    }

    private void setTail(Node<T> tail) {
        this.tail = tail;
    }

    @Override
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            this.tail = newNode;
        } else {
            newNode.setNext(this.head);
        }
        this.head = newNode;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return null;
        }
        return this.head.getData();
    }

    @Override
    public T removeFirst() {
        return removeElement(this.head.getData());
    }

    @Override
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            this.head = newNode;
        } else {
            this.tail.setNext(newNode);
        }
        this.tail = newNode;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return null;
        }
        return this.tail.getData();
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return null;
        }
        return removeElement(this.tail.getData());
    }

    @Override
    public void insert(T element, int sortType) {

        if (isEmpty()) {
            addFirst(element);
        } else {
            Node<T> newNode = new Node<>(element);
            Node<T> current = this.head;
            Node<T> previous = null;
            TRecord.setWhichSort(sortType);
            if (element.compareTo(current.getData()) < 0) {
                addFirst(element);
            } else {
                while ((current != null) && (element.compareTo(current.getData()) > 0)) {
                    previous = current;
                    current = current.getNext();
                }
                if (current == null) {
                    this.tail = newNode;
                } else {
                    previous.setNext(newNode);
                }
                newNode.setNext(current);
            }
        }
    }

    /**
     * Insert element with a default sort depend on a compareTo methode implementation
     */
    @Override
    public void insert(T element) {
        if (isEmpty()) { // list is empty
            addFirst(element);
        } else {
            if (element != null) {
                Node<T> newNode = new Node<>(element);
                Node<T> current = this.head;
                Node<T> previous = null;
                if (element.compareTo(current.getData()) < 0) { // data for element less than data of head
                    addFirst(element);
                } else {
                    // data for element larger than data of head
                    while ((current != null) && (element.compareTo(current.getData()) >= 0)) {
                        previous = current;
                        current = current.getNext();
                    }
                    previous.setNext(newNode);
                    if (current == null) {//The current reached the end of the list and the element larger than current
                        this.tail = newNode;
                    }
                    newNode.setNext(current);

                }
            }

        }
    }

    @Override
    public void addAtIndex(int index, T element) {
        if (index >= length) {
            addLast(element);
        } else if (index < 0) {
            addFirst(element);
        } else {
            Node<T> current = this.head;
            for (int i = 0; i < index - 1; ++i) {
                current = current.getNext();
            }
            Node<T> newNode = new Node<>(element);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        length++;
    }


    /**
     * To remove a specific element in the list
     */
    @Override
    public T removeElement(T element) {
        if (isEmpty()) return null;
        Node<T> prevPrevious, previous = null, current = this.head;
        if (current.getData().equals(element)) { // if element is a first node
            this.head = head.getNext();
            return current.getData();
        } else {
            boolean ok = false;
            while (current != null) {
                if (ok) prevPrevious = previous.getNext();
                else prevPrevious = previous;

                previous = current;
                current = current.getNext();

                if (current == null && previous.getData().equals(element)) { // if element it is a last node
                    prevPrevious.setNext(null);
                    return previous.getData();
                } else if (current == null) //The current reached the end of the list and the element does not exist
                    return null;
                else if (current.getData().equals(element)) { // the element is found
                    previous.setNext(current.getNext());
                    return current.getData();
                }
                current = current.getNext();
                ok = true;
            }
            return null;
        }
    }


    /**
     * To search and return a specific element
     */
    @Override
    public T search(T element) {
        if (isEmpty()) return null; // No node in list
        Node<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(element)) return current.getData();
            current = current.getNext();
        }
        return null;
    }


    /**
     * To merge this list with another list
     */
    @Override
    public void merge(LinkedList<T> linkedList) {
        if (isEmpty()) this.head = linkedList.getHead();
        else {
            this.tail.setNext(linkedList.getHead());
            this.tail = linkedList.getTail();
        }
    }

    /**
     * Traverse list
     */
    @Override
    public void printList() {
        if (isEmpty()) System.out.println("List is empty");
        else {
            Node<T> current = this.head;
            while (current != null) {
                System.out.print(current);
                current = current.getNext();
            }
        }
    }

    /**
     * Traverse list reverse
     */
    @Override
    public void printListReverse(Node<T> current) {
        if (isEmpty()) System.out.println("List is empty");
        else if (current == null) {
        } else {
            printListReverse(current.getNext());
            System.out.print(current);
        }
    }

    /**
     * To get the length of the list
     */
    @Override
    public int length() {
        if (isEmpty()) return 0; // list is empty and return 0
        else {
            Node<T> current = this.head;
            int count = 0;
            while (current != null) {
                count++;
                current = current.getNext();
            }
            return count; // return the length if the list is not empty
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.head == null);
    }

    /**
     * To set head = null and we can't access to another nodes(clear list(another node will be garbage by java))
     */
    @Override
    public void clear() {
        this.head = null;
    }


    /**
     * tTo return tha data in the list as a string
     */
    @Override
    public String toString() {
        if (isEmpty()) return "The list is empty\n";
        String str = "";
        Node<T> strNode = this.head;
        while (strNode != null) {
            str += strNode.getData();
            strNode = strNode.getNext();
        }
        return str;
    }
}
