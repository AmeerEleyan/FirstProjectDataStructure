/**
 * @author: Amir Eleyan
 * ID: 1191076
 * Time:  17/3/2021  9:17 PM
 */
package linkedList;

public class Node<T extends Comparable<T>> {

    private T data;

    private Node<T> next;

    // No argument constructor
    public Node() {
    }

    // Constructor with a specific data
    public Node(T data) {
        this.data = data;
    }

    // Return the data of this node
    public T getData() {
        return this.data;
    }

    // Set a new data for this node
    public void setData(T data) {
        this.data = data;
    }


    // Return the next node
    public Node<T> getNext() {
        return this.next;
    }


    // Set a new next node
    public void setNext(Node<T> next) {
        this.next = next;
    }

    // Return the data of the node as a string
    @Override
    public String toString() {
        if (this.data == null) return "null";
        return this.data.toString();
    }

}

