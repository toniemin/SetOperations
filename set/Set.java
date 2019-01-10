
package set;

import hashtable.HashTable;

/**
 * Data structures 2017-2018 - Coursework
 * 
 * Topi Nieminen
 * nieminen.topi.m@student.uta.fi
 * 
 * 
 * Class that represents a set from Set theory.
 * Implements set methods union, intersection and complement.
 * 
 * Data stored in hashtable.
 */


public class Set extends HashTable {
    
    /*
     * Constructors.
     * 
     */
    
    public Set()
    {
        super();
    }
    
    public Set(int size)
    {
        super(size);
    }
    
    /*
     * Methods.
     * 
     */
    
    /**
     * Creates the union of this and set b.
     * 
     * @param b Other operand of union.
     * @return Set that contains all the values from both this and b.
     */
    public Set union( Set b )
    {
        int[] allA = this.getAll();
        int[] allB = b.getAll();
        
        Set c = new Set( this.n + b.n );
        for (int i = 0; i < allA.length || i < allB.length; i++)
        {
            if ( i < allA.length )
                c.add( allA[i], allA[i] );
            if ( i <  allB.length )
                c.add( allB[i], allB[i] );
        }
        
        return c;
    }
    
    /**
     * Creates the intersectin of this and set b.
     * 
     * @param b Other operand of intersection.
     * @return Set that contains values that are common to this and b.
     */
    public Set intersection( Set b )
    {
        int[] allA = this.getAll();
        
        
        Set c = new Set( this.n > b.n ? this.n : b.n );
        for ( int i = 0; i < allA.length; i++ )
        {
            if ( b.contains( allA[i] ) )
                c.add( allA[i], allA[i] );
        }
        
        return c;
    }
    
    /**
     * Creates the complement of this and set b.
     * 
     * @param b Other operand of complement.
     * @return Set that contains values that are unique to either this or b.
     */
    public Set complement( Set b )
    {
        int[] allA = this.getAll();
        int[] allB = b.getAll();
        
        Set c = new Set( allA.length + allB.length );
        for ( int i = 0; i < allA.length || i < allB.length; i++ )
        {
            if ( i < allB.length && ! this.contains( allB[i] ) )
                c.add( allB[i], allB[i] );
            if ( i < allA.length && i < allB.length && ! b.contains( allA[i] ) )
                c.add( allA[i], allA[i]  );
        }
        
        return c;
    }
    
}
