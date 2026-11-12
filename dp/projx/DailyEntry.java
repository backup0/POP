/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 *
 * @author user
 */
public class DailyEntry
{
   /**
    * The daily entry id, which is a date.
    */
   private java.util.Date id = null;
   /**
    * The id of category.
    */
   private int category;
   /**
    * Notes attached to the entry.
    */
   private String note = null;
   private String text = "";
   /**
    * The connection to the database.
    */
   private java.sql.Connection db = null;

   /**
    * Create a new day entry.
    *
    * Create a new day entry and store it in the database.
    *
    * @param conn Connection to the database.
    * @param c Category to which this entry belongs.
    */
   public DailyEntry (java.sql.Connection conn, java.util.Date id, int c, byte type)
   {
      category = c; db = conn;
      // Store the entry
      if (type == 0)
      {
         storeEntry(id, c);
      }
      else
      {
         this.id  = id;
      }
   }

   /**
    * Create an entry object which contains existing entry. All we need is the id.
    *
    * @param conn Connection to the database.
    * @param d Date of the entry which serves as id.
    */
   public DailyEntry (java.sql.Connection conn, java.util.Date d)
   {
      id = d; //category = c; db = conn;
   }

   /**
    * Return the id as formatted dated.
    *
    * We need this because the date has to be displayed on the tree.
    *
    * @return Formatted date.
    */
   @Override
   public String toString () {return ZeebUtil.formatDate(id).substring(8);}
   /**
    * Return the date as string.
    *
    * @return The date (the entry's id)
    */
   public final String getIDS () {return ZeebUtil.formatDate(id); }
   /**
    * Return the date.
    *
    * @return The date (the entry's id)
    */
   public final java.util.Date getID () {return id;}
   /**
    * Get the notes attached to the entry.
    *
    * @return The node.
    */
   public final String getNote () {return note;}
   /**
    * Get the entry of the current entry.
    *
    * @return The id of the category.
    */
   public final int getCat () {return category;}
   /**
    * Get the id of the entry as String.
    *
    * @return The id (date)
    */
   private String getIDtext () {return ZeebUtil.formatDate(id); }

   /**
    * Create a new daily entry, and store it in the database.
    *
    * @param id The entry's id.
    * @param cat The entry's category.
    */
   private void storeEntry (java.util.Date id, int cat)
   {
      java.sql.PreparedStatement ps;

      if (id == null)
      {
         id = new java.util.Date();
      }

      try
      {
         ps = db.prepareStatement("insert into dailyentry values(?, ?, 1, '', '')");
         java.util.Date todayDate = new java.util.Date();
         ps.setDate(1, java.sql.Date.valueOf(ZeebUtil.formatDate(id)));
         ps.setInt(2, cat);
         ps.execute();
         ps.close();
         this.id = id;
         category = cat;
      }
      catch (java.sql.SQLException e){}
   }

   /**
    * Store the content of the entry.
    */
   public void upDateText (String text)
   {
      java.sql.PreparedStatement ps;

      try
      {
         ps = db.prepareStatement("update dailyentry set text = ? where id = ? and category = ?");
         ps.setString(1, text);
         ps.setDate(2, java.sql.Date.valueOf(getIDtext()));
         ps.setInt(3, category);
         ps.execute();
         ps.close();
      }
      catch (java.sql.SQLException e){}
   }

}
