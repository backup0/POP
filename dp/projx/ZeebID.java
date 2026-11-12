/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * Entry's ID class for the free-form entry.
 *
 * @author user
 */
public class ZeebID
{
      /**
       * The date which forms part of the id.
       */
      java.util.Date IDdate = null;
      /**
       * Number, another part of the id.
       */
      int IDnum = 0;

      /**
       * Create one.
       *
       * @param d Date.
       * @param numb Number.
       */
      public ZeebID (java.util.Date d, int numb)
      {
            IDdate = d;
            IDnum = numb;
      }

      /**
       * Get the date portion of the id.
       *
       * @return The date.
       */
      public java.util.Date getDate ()
      {
         return IDdate;
      }

      /**
       * Get the number portion of the id.
       * @return The number.
       */
      public int getNumber ()
      {
         return IDnum;
      }

      /**
       * Set the number portion of the id.
       * @param i The number.
       */
      public void setNumber (int i)
      { IDnum = i;}

   @Override
      public String toString()
      {
         return ZeebUtil.formatDate(IDdate) + "-" + String.valueOf(IDnum);
      }

}
