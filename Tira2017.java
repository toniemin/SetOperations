
/**
 * Data structures 2017-2018 - Coursework
 * 
 * Topi Nieminen
 * nieminen.topi.m@student.uta.fi
 * 
 * Program that reads two input files (must be named setA.txt and setB.txt)
 * that contain int-values and creates three files: or.txt, and.txt and xor.txt. 
 * These files contain the union, intersection and complement
 * of values in input files (set theory).
 * 
 * Note: this program only supports int values!
 */
import set.*;
import java.io.*;
import java.util.Scanner;

public class Tira2017 {

    /**
     * Counts number of lines in a file.
     * 
     * @param filename File to be read.
     * @return Number of lines in the file.
     * @throws FileNotFoundException 
     */
    private static int countLines(String filename)
        throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(filename));

        int lines = 0;
        while ( sc.hasNextLine() )
        {
            lines++;
            sc.nextLine();
        }
            
        return lines;
    }
    
    /**
     * Saves input from file to sets in the parameter list.
     * @param filename File with input.
     * @param set All values encountered in the file.
     * @param occs Occurences of each value in the file.
     * @param linenums Line numbers of each value in the file.
     * @param files File identifier for each value.
     * @throws FileNotFoundException 
     */
    private static void readInput (
        String filename, 
        Set set,
        Set occs, 
        Set linenums, 
        Set files
        )
         throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(filename));
        
        int value, linenum = 1;
        while (sc.hasNextLine() )
        {
            // Get value from file.
            String temp = sc.nextLine();
            value = Integer.parseInt( temp.trim() );

            // Save value into set. Use the value itself as the key.
            set.add(value, value);

            /* 
             * Update occurences for that value.
             */
            if ( occs.contains(value) )
                // Value already present, updating occurence.
                occs.add(value, occs.getval(value) + 1);
            else
                // First occurence of the value.
                occs.add(value, 1);
            
            /*
             * Update linenumber for that value.
             */
            if ( ! linenums.contains( value ) )
            {
                linenums.add(value, linenum);
            }
            
            
            /* 
             * Update file identifier for that value.
             * 1 for setA.txt
             * 2 for setB.txt
             */
            if ( filename.compareTo("setA.txt") == 0 )
            {
                // File present in file setA.txt.
                files.add(value, 1);
            }
            else
            {
                // File present in file setB.txt.
                files.add(value, 2);
            }
            
            // Update line.
            linenum++;
        }
        
    }

    /**
     * Reads a single character from the user.
     * @param q String displayed for the user.
     * @return User input character.
     */
    private static char getChar(String q)
    {
        Scanner sc = new Scanner(System.in);
        
        boolean done = false;
        char val = ' ';
        
        // Read input until correct value given.
        while (true)
        {
            try
            {
                // Display string to user.
                System.out.println(q);
                
                // Read character.
                String temp = sc.nextLine();
                val = temp.charAt(0);
                
                // Accept only 'y' and 'n'.
                if ( val == 'y' | val == 'n' )
                    done = true;
            }
            catch (Exception e)
            {
                System.out.println("Error!");
            }
            
            // Character succesfully read.
            if ( done )
            {
                return val;
            }
        }
    }
    
    /**
     * Read an integer from the user and return it.
     * @param q String displayed for the user.
     * @return User input int.
     */
    private static int getInt(String q)
    {
        Scanner sc = new Scanner(System.in);
        
        boolean done = false;
        int val = 0;
        
        // Read input until correct value given.
        while ( true )
        {
            try
            {
                // Display string to user.
                System.out.println(q);
                
                // Read integer.
                String temp = sc.nextLine();
                val = Integer.parseInt(temp);
                
                // Reading integer succesful.
                done = true;
            }
            catch (Exception e)
            {
                System.out.println("Error!");
            }
            
            // Success. Return int.
            if ( done )
                return val;
        }
    }
    
    /**
     * Writes two columns into output file according to parameters.
     * @param filename Output file.
     * @param col1 First column in the file.
     * @param col2 Second column in the file.
     * @throws Exception 
     */
    private static void writeOutput(String filename, int[] col1, Set col2)
            throws Exception
    {
        // Create new file according to filename.
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        
        // Write until there's no more output.
        int i = 0;
        while ( i < col1.length )
        {
            // Row to be written.
            String row = col1[i] + " " + col2.getval(col1[i]);
            
            // Writing row.
            bw.write(row);
            bw.newLine();
            
            i++;
        }
        
        // Success.
        bw.close();
    }
    
    public static void main(String[] args)
    {   
        System.out.println("Welcome! This program supports int-values. "
            + "(Min:-2,147,483,648 Max:2,147,483,647)");
        /*
         * Create empty sets for both files setA.txt and setB.txt.
         */
        Set setA = null, setB = null;
        try
        {
            setA = new Set( countLines("setA.txt") );
            setB = new Set( countLines("setB.txt") );
        }
        catch (Exception e)
        {
            System.out.println("Error accessing file(s).");
            System.exit(1);
        }
            
        /*
         * Create three helper sets.
         */
        // Counts the occurences of element in files.
        Set occurences = new Set(9999);
        
        // Saves the line number in which element was first discovered.
        Set lineNumbers = new Set(9999);
        
        // Tracks in which file the element is 
        // (1 for setA.txt and 2 for setB.txt).
        Set files = new Set(9999);
        
        System.out.println("Reading files..."); // Update.
        
        try
        {
            /*
             * Read input from the files and save in sets.
             */
            readInput("setA.txt", setA, occurences, lineNumbers, files);
            readInput("setB.txt", setB, occurences, lineNumbers, files);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error reading file(s).");
            System.exit(1);
        }
        
        System.out.println("Files read."); // Update.
        
        System.out.println("The number of values contained in sets:\n  setA: " 
            + setA.size() + "\n  setB: " + setB.size());
        
        // Ask for removal of values from the sets.
        boolean remove = true;
        while (remove)
        {
            // Getting yes or no from user.
            char answer = getChar("Remove elements? y/n:");
            
            if ( answer == 'y' )
            {
                // Reading value to be removed.
                int value = getInt("Enter value:");
                
                // Trying to remove value from setA and setB.
                boolean success = setA.remove(value);
                success = setB.remove(value) | success;
                
                if ( success )
                {
                    // Value removed. Removing value from other sets.
                    occurences.remove(value);
                    lineNumbers.remove(value);
                    files.remove(value);
                    
                    System.out.println("Value removed.");
                }
                else
                    // Value could not be found.
                    System.out.println("Value not removed (not found).");
            }
            else
                // Done.
                remove = false;
        }
        
        /*
         * Create main sets.
         */
        System.out.println("Creating sets...");
        
        Set or = setA.union(setB);
        Set and = setA.intersection(setB);
        Set xor = setA.complement(setB);
        
        /*
         * Begin writing files.
         */
        System.out.println("Sets created.");
        
        System.out.println("The number of values contained in sets:\n  or: " 
            + or.size() + "\n  and: " + and.size() + "\n  xor: " + xor.size());
        
        System.out.println("Writing files...");
        try
        {
            /*
             * or.txt: 1st column element value,
             * 2nd column element occurences.
             */
            writeOutput("or.txt", or.getAll(), occurences);
            /*
             * and.txt: 1st col element value,
             * 2nd column line number of the first occurence of the value.
             */
            writeOutput("and.txt", and.getAll(), lineNumbers);
            /*
             * xor.txt: 1st col element value,
             * 2nd col file number (1 or 2).
             */
            writeOutput("xor.txt", xor.getAll(), files);
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong when writing files!\n" + e);
            System.exit(1);
        }
        
        System.out.println("Files written. See ya!");
    }
}
