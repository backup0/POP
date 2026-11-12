/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * Day journal category.
 *
 * @author user
 */
public class DayJnlCategory
{
      /**
       * The category's id.
       */
      private int id;
      /**
       * The category's name.
       */
      private String name;

      /**
       * Constructor.
       *
       * @param i The id.
       * @param n The name.
       */
      public DayJnlCategory (int i, String n)
      {
          id = i; name = n;
      }

      /**
       * Get category's id.
       *
       * @return Id.
       */
      public int getID () {return id;}
      /**
       * Get category's name.
       *
       * @return The name.
       */
      public String getName () {return name;}
      /**
       * Override the toString() method.
       *
       * @return The name of the category.
       */
   @Override
      public String toString () {return name;}
}
