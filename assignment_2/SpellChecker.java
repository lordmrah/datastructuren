/*
 * File: SpellChecker.java
 * Collaborators: Maartje ter Hoeve (10190015), Harm Manders (10677186)
 * Course: Datastructuren KI 2015
 *
 * Class to check the spelling of text files, given a word list
 *
 */

import java.util.*;
import java.io.*;

/* Main class, to check the spelling of the words in a text file */
class SpellChecker {
    public static void main(String[] args) {
        int hash_size, hash_strategy;
        int count = 0, typo = 0;
        long start = 0, end = 0;
        String wordfile, textfile;
        HashTableOpen table;
        Compressable function;

        /* Shared token to store for every word in the hash table. */
        String placeholder = "a";

        if (!(args.length == 4) ) {
            System.out.println("Usage: java SpellChecker <wordfile> <text> <size> <hash_strategy>");
            System.exit(0);
        }
        wordfile = args[0];
        textfile = args[1];
        hash_size = Integer.parseInt(args[2]);
        hash_strategy = Integer.parseInt(args[3]);
        if(hash_strategy != 1 && hash_strategy != 2 ) {
            System.out.println("You did not enter a valid hash protocol!");
            System.out.println("Append 1 for Collision Chaining, 2 for Linear Probing");
            System.exit(1);
        }
        System.out.printf("Selected table size: %d\n", hash_size);
        function = new Division(hash_size);
        table = new HashTableOpen(hash_size, function);

        /* Read wordfile, and insert every word into the hash table. */
        try {
            BufferedReader in = new BufferedReader(new FileReader(wordfile));
            start = System.currentTimeMillis();
            String str, copy;
            while ((str = in.readLine()) != null) {
                copy = str.toLowerCase();
                table.put(copy, placeholder, hash_strategy); //added hashStrategy
            }
            end = System.currentTimeMillis();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("java opbouwen Hashtable in " + (end - start) + " ms");

        // Read text file, and lookup every word in the hash table.
        try {
            BufferedReader src = new BufferedReader(new FileReader(textfile));
            start = System.currentTimeMillis();
            String str = src.readLine();
            while (str != null) {
                String copy = str.toLowerCase();

                StringTokenizer st = new StringTokenizer(copy, " ,.:;\"-_(){}[]?!*^&'\n\t");
                while(st.hasMoreTokens()) {
                    String word = st.nextToken();                    
                    if (!contains_numbers(word) && table.get(word, hash_strategy) == null) {                        
                        //System.out.printf("Not found: [%s]\n", word);
                        typo++;
                    }                    
                    count++;
                }
                str = src.readLine();
                
            }
            end = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Hash table contains %d words\n", table.size(hash_strategy));
        System.out.printf("Hash table load factor %f\n",
               (double)table.size(hash_strategy)/hash_size);

        System.out.printf("Text contains %d words\n", count);
        System.out.printf("typo's %d\n", typo);

        System.out.println("zoeken woorden in " + (end - start) + " ms");
    }
    
    /* Checks if word contains digits. So it can be ignored for spell
     * checking. */
    static boolean contains_numbers(String str) {
        for (int i = 0 ; i < str.length() ; i++) 
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') 
                return true;

        return false;
    }
}
