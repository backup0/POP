/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * Notes.
 *
 * @author Amril
 *
 * The class for the notes
 */

public class Note
{
   private int myId;
   private String myText;

  /**
   * Create a note object
   *
   * @param id The note's id, needed in order to identify it in the DB
   * @param text the note
   */
   public Note (int id, String text)
   { myId = id; myText = text;}

   /**
    * Return the note's id
    *
    * @return the note's id
    */
   public int getId ()
   {return myId;}

   /**
    * Return the note
    *
    * @return the note
    */
   public String getNote ()
   {return myText;}
}
