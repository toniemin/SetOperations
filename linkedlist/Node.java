
package linkedlist;

/**
 * Data structures 2017-2018 - Coursework
 * 
 * Topi Nieminen
 * nieminen.topi.m@student.uta.fi
 * 
 * Class that implements a node to be used in a linked list.
 * Can store int values.
 */

public class Node {
    
    /*
     * Attributes.
     * 
     */
    
    /**
     * Unique key for the node.
     */
    public int key;
    /**
     * Data of the node.
     */
    public int data;
    /**
     * Reference to the next node in a linked list.
     */
    public Node next;
    
    /*
     * Constructors.
     * 
     */
    
    public Node(int key, int data)
    {
        this.key = key;
        this.data = data;
        this.next = null;
    }
    
}
