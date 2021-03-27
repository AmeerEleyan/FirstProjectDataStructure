package linkedList;

public interface Listable<T extends Comparable<T>> {

    /**
     * Add element in the first of the list
     */
    void addFirst(T element);

    /**
     * Return the first element
     */
    T getFirst();

    /**
     * Return and remove the first element
     */
    T removeFirst();

    /**
     * Add element in the last of the list
     */
    void addLast(T element);

    /**
     * Return the last element
     */
    T getLast();

    /**
     * Return and remove the last element
     */
    T removeLast();

    /**
     * Add an element to the list with specific sort
     */
    void insert(T element, int type);


    /**
     * Add an element to the list with default sort
     */
    void insert(T element);


    /**
     * Add element add specific index
     */
    void addAtIndex(int index, T element);


    /**
     * Return and remove specific element
     */
    T removeElement(T element);


    /**
     * Search for the specific element
     */
    T search(T element);

    /**
     * Print the list
     */
    void printList();

    /**
     * Print the list as revers
     */
    void printListReverse(Node<T> current);


    /**
     * Return the length of the list
     */
    int length();

    /**
     * Return status of the list (empty or not)
     */
    boolean isEmpty();

    /**
     * Merge two linked list together
     */
    void merge(LinkedList<T> linkedList);


    /**
     * To clear all node form list
     */
    void clear();

}

