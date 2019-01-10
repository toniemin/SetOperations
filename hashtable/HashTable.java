
package hashtable;

import linkedlist.LinkedList;
import linkedlist.Node;

/**
 * Data structures 2017-2018 - Coursework
 * 
 * Topi Nieminen
 * nieminen.topi.m@student.uta.fi
 * 
 * Class that implements a hashtable that can store int key-value -pairs.
 */

public class HashTable {
    
    /*
     * Attributes.
     * 
     */
    
    /**
     * Table that stores linked lists that hold the
     * key-value pairs of the dictionary.
     */
    protected LinkedList[] table;
    
    /**
     * Number of entries in the hashtable.
     */
    protected int n;
    
    /**
     * Size of the hashtable.
     */
    protected int N;
    
    
    /*
     * Constructors.
     * 
     */
    
    /**
     * Create new hashtable of size N.
     */
    public HashTable()
    {
        N = 9999;
        table = new LinkedList[N];
        n = 0;
    }
    
    public HashTable(int size)
    {
        N = size;
        table = new LinkedList[N];
        n = 0;
    }
    
    /*
     * Methods.
     * 
     */
    
    public int size()
    {
        return n;
    }
    
    public int capasity()
    {
        return N;
    }
    
    /**
     * Collects all values from the set to an array and returns it.
     * Ignores keys.
     * @return All values from the set.
     */
    public int[] getAll()
    {
        // Array for the values.
        int[] all = new int[n];
        
        // Go through the hashtable...
        int j = 0; // Iterator for all[].
        for (int i = 0; i < N; i++)
        {
            // Check if there are values at [i].
            if ( table[i] != null )
            {
                // Linked list found. Go through linked list.
                Node node = table[i].head; // Access to the list.
                while ( node != null )
                {
                    // Read and save value.
                    all[j] = node.data;
                    j++;
                    node = node.next;
                }
            }
        }
        
        return all;
    }
    
    /**
     * Return boolean for emptiness of the hashtable.
     * @return True if empty, false otherwise.
     */
    public boolean empty()
    {
        return n == 0;
    }
    
    /**
     * Checks if the hashtable contains key.
     * 
     * @param key Key to be found.
     * @return True if key is in the dictionary and false otherwise.
     */
    public boolean contains(int key)
    {
        int idx = hash(key);
        
        if ( table[idx] != null )
            return table[idx].find(key) != null;
        else
            return false;
    }
    
    /**
     * Get the value of a key.
     * @param key Key of the key-value -pair.
     * @return Value of the key-value -pair. 0 if value not found.
     */
    public int getval(int key)
    {
        int idx = hash(key);
        
        if ( table[idx] != null && table[idx].find(key) != null )
            return table[idx].find(key).data;
        else
            return 0;
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
        // Hash the key to get the index where to
        // save the key-value-pair.
        int idx = hash(key);
        
        // If table is null at the hashed index,
        // create a new linked list.
        if ( table[idx] == null )
            table[idx] = new LinkedList();
        
        // Check if the list already contains the key.
        if ( table[idx].find(key) == null )
            // Update n.
            n++;
        
        // Add or update the key in the list.
        table[idx].add(key, data);
    }
    
    /**
     * Remove key-value-pair from the hashtable.
     * 
     * @param key Key to be removed.
     * @return True if removal was successful and false otherwise.
     */
    public boolean remove(int key)
    {
        // Get the index for the key.
        int idx = hash(key);
        
        
        if ( table[idx] != null && table[idx].remove(key) )
        {
            n--;
            return true;
        }
        else
            return false;
    }
    
    /**
     * Hash-funtion.
     * 
     * @param key Key to be hashed.
     * @return Hash of the key.
     */
    private int hash(int key)
    {
        int h = key % (N-1);
        
        return h < 0 ? h*-1 : h;
    }
    
}
