/**
 * @author: Amir Eleyan
 * ID: 1191076
 * At:  17/3/2021  11:35 PM
 */

package linkedList;

public interface Listable<T extends Comparable<T>> {

    /**
     * Add element in the first of the list
     */
    void insertAtFirst(T element);

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
    void insertAtLast(T element);

    /**
     * Return the last element
     */
    T getLast();

    /**
     * Return and remove the last element
     */
    T removeLast();


    /**
     * Add an element to the list with default sort
     */
    void addBySort(T element);

    /**
     * Add new element to the list after specific elementl
     */
    boolean insertAfter(T element, T afterObject);

    /**
     * Return and remove specific element
     */
    T remove(T element);

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

