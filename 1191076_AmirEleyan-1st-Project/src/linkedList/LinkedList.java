/**
 * @author: Amir Eleyan
 * ID: 1191076
 * Time:  18/3/2021  3:40 AM
 */
package linkedList;

public class LinkedList<T extends Comparable<T>> implements Listable<T> {

    private Node<T> head, tail;

    public LinkedList() {
        this.head = this.tail = null;
    }

    public Node<T> getHead() {
        return this.head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    @Override
    public void insertAtFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            // Head and tail on the same node(list empty)
            this.tail = newNode;
        } else {
            newNode.setNext(this.head); // set the next of newNode to tha head
        }
        this.head = newNode; // head point to the new node
    }

    @Override
    public T getFirst() {
        if (isEmpty()) { // there is no nodes in list
            System.out.println("The list is empty");
            return null;
        }
        return this.getHead().getData();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        // if we not add the previous condition will be thread exception
        // because the head is null thus head.getData thread exception
        return remove(this.head.getData());
    }

    /**
     * Add new element for this list
     */
    @Override
    public void insertAtLast(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            // Head and tail on the same node(list empty)
            this.head = newNode;
        } else {
            // Tail on a specific node
            this.tail.setNext(newNode); // next of the node point to new node
        }
        this.tail = newNode; // Tail point to the new node
    }

    /**
     * Return tha first element for this list
     */
    @Override
    public T getLast() {
        if (isEmpty()) { // there is no nodes in list
            System.out.println("The list is empty");
            return null;
        }
        return this.getTail().getData();
    }

    /**
     * Remove last element of this list
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return null;
        }
        // if we not add the previous condition will be thread exception
        // because the tail is null thus tail.getData thread exception
        return remove(this.tail.getData());
    }


    /**
     * Insert element with a specific sort depend on a compareTo methode implementation
     */
    @Override
    public void addBySort(T element, int sortType) {
        if (isEmpty()) { // list is empty
            insertAtFirst(element);
        } else {
            if (element != null) {
                Node<T> newNode = new Node<>(element);
                Node<T> current = this.head;
                Node<T> previous = null;
                if (element instanceof TRecord) TRecord.setWhichSort(sortType);
                if (element.compareTo(current.getData()) < 0) { // data for element less than data of head
                    insertAtFirst(element);
                } else {
                    // data for element larger than data of head
                    while ((current != null)) {

                        previous = current;
                        current = current.getNext();

                    }
                    //The current reached the end of the list and the element larger than current
                    previous.setNext(newNode);
                    if (current == null) {
                        this.tail = newNode;
                    }
                    newNode.setNext(current);

                }
            }

        }
    }

    /**
     * Insert element with a default sort depend on a compareTo methode implementation
     */
    @Override
    public void addBySort(T element) {
        if (isEmpty()) { // list is empty
            insertAtFirst(element);
        } else {
            if (element != null) {
                Node<T> newNode = new Node<>(element);
                Node<T> current = this.head;
                Node<T> previous = null;
                if (element.compareTo(current.getData()) < 0) { // data for element less than data of head
                    insertAtFirst(element);
                } else {
                    // data for element larger than data of head
                    while ((current != null)) {
                        if (element.compareTo(current.getData()) > 0) break;
                        previous = current;
                        current = current.getNext();
                        if (element.compareTo(current.getData()) == 0) continue;
                    }
                    //The current reached the end of the list and the element larger than current

                    if (current == null) {
                        this.tail = newNode;
                    }else{
                        previous.setNext(newNode);
                        newNode.setNext(current);
                    }

                }
            }

        }
    }

    @Override
    public boolean insertAfter(T element, T afterObject) {
        if (isEmpty()) return false;
        Node<T> current = this.head;
        Node<T> newNode = new Node<>(element);
        while (current != null) {
            if (current.getData().equals(afterObject)) {

                // set next of the new node to the next of the current
                newNode.setNext(current.getNext());

                // list contains one node or current point to last node
                if (current.getNext() == null) this.tail = newNode;

                current.setNext(newNode); // set next of the current to the newNode
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    /**
     * To remove a specific element in the list
     */
    @Override
    public T remove(T element) {
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
     * To return tha data in the list as a string
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
