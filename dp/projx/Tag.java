/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * Class for entry tag.
 *
 * @author user
 */
public class Tag {

   /**
    * The tag's id.
    */
   private int id;
   /**
    * The tag.
    */
   private String tag;

   /**
    * Constructor.
    *
    * @param tagid The id.
    * @param tag The tag.
    */
   public Tag(int tagid, String tag)
   {
        id = tagid;
        this.tag = tag;
   }

   /**
    * Get the tag's id.
    *
    * @return Tag's id.
    */
   public int getID () {return id;}
   /**
    * Get the tag.
    *
    * @return The tag.
    */
   public String getTag () {return tag;}
   /**
    * Override the toString() method ..
    *
    * @return the tag.
    */
   @Override
   public String toString (){return tag;}

}
