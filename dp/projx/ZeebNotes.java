/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 *
 * @author user
 */
public class ZeebNotes
{
     String text = null;
     int id;

     public ZeebNotes (String t, int i)
     {
         text = t;
         id = i;
     }
     
   @Override
     public String toString() {return text;}
     public int getId () {return id;}
}
