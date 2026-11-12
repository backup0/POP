/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * Helper class?
 *
 * @author user
 */
public class ZeebUtil
{
   private java.sql.Connection dbConn = null;

   /**
    * Format date.
    *
    * @param date the date to format.
    * @return Formatted date.
    */
   public static String formatDate(java.util.Date date)
   {
      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
      return sdf.format(date.getTime());
   }

   public void setConnection (java.sql.Connection c)
   {
      dbConn = c;
   }

   public java.sql.Connection getConnection ()
   {
      return dbConn;
   }

}
