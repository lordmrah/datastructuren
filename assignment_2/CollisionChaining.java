/*
 * File: SpellChecker.java
 * Collaborators: Maartje ter Hoeve (10190015), Harm Manders (10677186)
 * Course: Datastructuren KI 2015
 *
 * Class to construct a object that can be used as instance/node of a single linked list.
 *
 */

import java.util.*;
import java.io.*;

/* Class that can be used to construct nodes of a single linked list */
public class CollisionChaining {
    
    private String key;
    private String value;
    private CollisionChaining next;
    
    /* Constructor; new nodes are added at the end, so they shouldn't be able to refer to a next node */
    CollisionChaining(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
    
    /* Set method: A single linked list needs a reference to the next node in the list */
    public void setNext(CollisionChaining nextNode) {
        next = nextNode;
    }
    
    /* Get the next node in the single linked list (null if node is last node) */
    public CollisionChaining getNext() {
        return next;
    }
    
    /* Set the key of the object */
    public void setKey(String newKey) {
        key = newKey;
    }
    
    /* Get the key of the object */
    public String getKey() {
        return key;
    }
}
