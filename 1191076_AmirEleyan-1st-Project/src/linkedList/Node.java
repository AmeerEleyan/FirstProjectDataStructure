package linkedList;

public class Node< T extends Comparable < T > > {

    private T data;

    private Node < T > next;

    // No argument constructor
    public Node( ){ }

    // Constructor with a specific data
    public Node( T data ){
        this.data = data;
    }

    // Return the data of this node
    public T getData( ){
        return this.data;
    }

    // Return the next node
    public Node < T > getNext( ){
        return next;
    }

    // Set a new data for this node
    public void setData( T data ){
        this.data = data;
    }

    // Set a new next node
    public void setNext( Node < T > next ){
        this.next = next;
    }

    // Return the data of the node as a string
    @Override
    public String toString( ){
        return this.data.toString();
    }

}

