
package linkedlist;

/**
 * Data structures 2017-2018 - Coursework
 * 
 * Topi Nieminen
 * nieminen.topi.m@student.uta.fi
 * 
 * Class that implements a linked list that can store int-values.
 */

public class LinkedList {
    
    /*
     * Attributes.
     * 
     */
    
    /**
     * Head of the linked list.
     * 
     */
    public Node head;
    
    /*
     * Constructors.
     * 
     */
    
    public LinkedList()
    {
        head = null;
    }
    
    /*
     * Methods.
     * 
     */
    
    /**
     * Checks if the list is empty.
     * 
     * @return true if the list is empty and false otherwise.
     */
    public boolean empty()
    {
        return head == null;
    }
    
    /**
     * Adds a new key-value pair if key is not found.
     * Updates key's value if key is found.
     * 
     * @param key Unique key for the key-value pair.
     * @param data Value to be stored with the key.
     */
    public void add(int key, int data)
    {
        // Check if key is already present.
        if ( find(key) == null )
        {
            // Not present, adding a new key-value-pair to the list.
            if ( empty() )
            {
                head = new Node(key, data);
            }
            else
            {
                Node c = head;
                while ( c.next != null )
                    c = c.next;
                c.next = new Node(key, data);
            }
        }
        else
        {
            // Key already present. Updating the value of said key.
            Node n = find(key);
            n.data = data;
        }
    }
    
    /**
     * Removes a key-value pair from the list.
     * 
     * @param key Key of the to-be-removed pair.
     * @return True if removing was successful and false if it was not.
     */
    public boolean remove(int key)
    {
        // Empty check.
        if ( ! empty() )
        {
            // Search for the node with the correct key 
            //and it's predecessor in the list.
            Node p = null; // Predecessor.
            Node c = head;
            while (c != null)
            {
                if ( c.key == key)
                {
                    if ( c == head )
                    {
                        head = head.next;
                        c = null;
                    }
                    else
                    {
                        p.next = c.next;
                        c = null;
                    }
                    
                    return true;
                }
                p = c;
                c = c.next;
            }
        }
        
        // List empty.
        return false;
    }
    
    /**
     * Finds the node with key in the list.
     * @param key Key of the node.
     * @return The node with key if found and null if not found.
     */
    public Node find(int key)
    {
        if ( ! empty() )
        {
            Node current = head;
            while ( current != null && current.key != key )
            {
                current = current.next;
            }
            
            return current;
        }
        else
            return null;
    }
}
