/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * This is a specialize linked list which stores the entries. It is basically the standard linked
 * list, but an entry is associated with it, it is separate from the items in the list. This entry
 * defines the entire list. This class is required since there are parent entries, the parent entries
 * have entries under it - this linked list stores the parent entry and entries under it.
 *
 * @author amril.
 */
public class EntryLinkedList extends java.util.LinkedList
{
     /**
      * This the entry which serves as the parent.
      */
     private FFEntry entry;

     /**
      * Constructor.
      */
     public EntryLinkedList()
     {
         super();
     }

     /**
      * Get the entry (the parent).
      *
      * @return The parent entry (FFEntry).
      */
     public FFEntry getEntry()
     { return entry;}

     /**
      * Set the parent entry.
      *
      * @param e Instance of an FFEntry which serves as the parent.
      */
     public void setEntry (FFEntry e)
      { entry = e;}

     
   @Override
     public String toString()
     {
         String rtn = "No entry";
         if (entry != null)
            rtn = entry.getName();

         return rtn;
     }

}
