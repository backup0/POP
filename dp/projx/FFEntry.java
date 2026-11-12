/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

import java.sql.SQLException;

/**
 * The free-form entry class.
 * 
 * @author user
 */
public class FFEntry
{

      // The big list of variables
      // 1 & 2 The ID, two parts in one object .. COOL!
      /**
       * The entry's id.
       */
      private projx.ZeebID id = null;
      // 3. The name
      /**
       * The entry's name.
       */
      private String name = null;
      // 4. The icon - make it pretty huh?
      /**
       * Icon? [NOT IMPLEMENTED YET.]
       */
      private String icon = null;
      // 5. The note
      /**
       * Note(s) attached to the entry.
       */
      private String note = null;
      // 6. The type
      /**
       * The type.
       */
      private byte type = 0;
      // 7 & 8 The parent id
      /**
       * Id of this entry's parent.
       */
      private projx.ZeebID parentId = null;
      // We've got 8, alright ...

      // Now the tags ..
      /**
       * Tags.
       */
      private java.util.LinkedList tags = null;

      // New or old? Very descriptive
      private byte age;

      // Utility ...
      /**
       * Connection to the db.
       */
      private java.sql.Connection db = null;

      // Other stuff, the constants ...
      public static final byte TEXT = 1; // Text type
      public static final byte LIST = 2; // List type
      // Is this an existing entry, or a new entry and will be stored ...
      public static final byte OLD = 1;
      public static final byte NEW = 2;

      // Database thingy
      private final String EDATE = "edate"; // Mandatory - Auto if it's new
      private final String ENUMB = "enumb"; // Mandatory - Auto if it's new
      // Name .. The name of the entry
      private final String ENAME = "ename"; // Mandatory
      // Journal
      private final String EPARENTDATE = "eparentdate"; // Mandatory
      private final String EPARENTNUMB = "eparentnumb"; // Mandatory
      // Icon
      private final String EICON = "eicon"; // Optional
      // Type: Text or list
      private final String ETYPE = "etype"; // Mandatory - Sweet!!!
      // Master Note (wot?):
      private final String EMNOTE = "emnote"; // Optional
      // The table ...
      private final String ETABLE = "zfentry"; // This is not a field.
      private final String TTABLE = "ztext";


      // This one's the constructor for an existing entry ...
      /**
       * Constructor.
       *
       * Constructor for an existing entry.
       *
       * @param db Connection to the db.
       * @param cId Id.
       * @param cName Name.
       * @param cNote Note.
       * @param cType Type.
       * @param cPId Parent's id.
       * @param cIcon Icon.
       */
      public FFEntry(
            java.sql.Connection db,
            projx.ZeebID cId,
            String cName,  //m'apple
            String cNote,
            byte cType,
            projx.ZeebID cPId,
            String cIcon)
      {
         id = cId;
         name = cName;
         icon = cIcon;
         note = cNote;
         type = cType;
         parentId = cPId;
         age = OLD;
         this.db = db;

      }

      // This one's the constructor for a new entry ... Notice we don't ask for ID, because we're
      // to create one for it.
      /**
       * Constructor.
       *
       * Constructor for a new entry, when this one is called - a new record is stored in the db.
       *
       * @param db Connection to the db.
       * @param cName Name.
       * @param cNote Note.
       * @param cType Type.
       * @param cPId Parent's id.
       * @param cIcon Icon.
       */
      public FFEntry(
            java.sql.Connection db,
            String cName,  //m'apple
            String cNote,
            byte cType,
            projx.ZeebID cPId,
            String cIcon)
      {
         name = cName;
         icon = cIcon;
         note = cNote;
         type = cType;
         parentId = cPId;
         age = NEW;
         this.db = db;
         storeEntry();

      }

      // This one's the constructor for a new entry ... Notice we don't ask for ID, because we're
      // to create one for it. The icon is null here.
      /**
       * Constructor.
       *
       * Constructor for a new entry, when this one is called - a new record is stored in the db.
       * Use this if the icon is null.
       *
       * @param db Connection to the db.
       * @param cName Name.
       * @param cNote Note.
       * @param cType Type.
       * @param cPId Parent's id.
       */
      public FFEntry(
            java.sql.Connection db,
            String cName,  //m'apple
            String cNote,
            byte cType,
            projx.ZeebID cPId)
      {
         name = cName;
         note = cNote;
         type = cType;
         parentId = cPId;
         age = NEW;
         this.db = db;
         storeEntry();
      }

      /**
       * Override the toString() method.
       *
       * @return The name.
       */
   @Override
      public String toString()
      {
         return name;
      }
      // Start with accessors first
      /**
       * Get the entry's id.
       *
       * @return Entry's id.
       */
      public projx.ZeebID getID()
      {  return id;}
      /**
       * Get the entry's name.
       *
       * @return The entry's name.
       */
      public String getName()
      {  return name;}
      /**
       * Get the label.
       *
       * @return Label.
       */
      public String getLabel()
      {  return note;}
      /**
       * Get the path to the icon.
       *
       * @return Path to the icon.
       */
      public String getIcon()
      {  return icon;}
      /**
       * Get entry's type.
       *
       * @return The type.
       */
      public byte getType()
      {  return type;}
      /**
       * Get the entry's parent's id.
       *
       * @return the parent's id.
       */
      public projx.ZeebID getParent()
      {  return parentId;}
      
      public boolean isOld()
      {
         if (age == OLD)
            return true;
          else
             return false;
      }

      /**
       * Get the content of the entry.
       *
       * @return The content.
       */
      public String getTextContent ()
      {
         // We return the text content of this entry - if thsi one contains that
         // .. Well?
         // Craete a query first
         String query = "SELECT etext FROM " + TTABLE + " WHERE edate = '";
         query = query + ZeebUtil.formatDate(id.getDate()) + "' AND enumb = " + String.valueOf(id.getNumber());
         try
         {
            java.sql.Statement stmt = db.createStatement();
            java.sql.ResultSet result = stmt.executeQuery(query);
            result.next();
            query = result.getString("etext");
            stmt.close();
         }
         catch (java.sql.SQLException e){}
         return query;
      }

      /**
       * Get the connection to the db.
       *
       * @return DB connection.
       */
      public java.sql.Connection getDB()
      {
         return db;
      }

      // Now the mutators ..
      // These methods allow the use to modify things ...
      // Things that cannot be chnaged ..
      // * ID
      // * Type
      /**
       * Get tags.
       *
       * @return Tags.
       */
      public java.util.LinkedList getTags()
      {  return tags;}
      /**
       * Attach the tag list to the entry.
       *
       * @param l Tag list.
       */
      public void attachTags (java.util.LinkedList l)
      {  tags = l;}

      /**
       * Change the name of the entry.
       *
       * @param n The new name.
       * @return ?
       */
      public String changeName (String n)
      {
         name = n;
         // Need to commit the change here ..
         String query;
         query = "UPDATE " + ETABLE + " SET " + ENAME + " = '" + name.replace("'", "''") + "' ";
         query = query + "WHERE " + EDATE + " = '" + ZeebUtil.formatDate(id.getDate()) + "' " +
                 "and "+ ENUMB + " = " + id.getNumber() + ";";
         return query;
      }

      /**
       * Change the label.
       *
       * @param n Label.
       * @return ?
       */
      public String changeNote (String n)
      {
         note = n;
         // Need to commit the change here ..
         String query;
         query = "UPDATE " + ETABLE + " SET " + EMNOTE + " = '" + note.replace("'", "''") + "' ";
         query = query + "WHERE " + EDATE + " = '" + ZeebUtil.formatDate(id.getDate()) + "' " +
                 "and "+ ENUMB + " = " + id.getNumber() + ";";
         return query;
      }

      /**
       * Change the icon.
       *
       * @param n Path to the icon.
       * @return ?
       */
      public String changeIcon (String n)
      {
           icon = n;
           // Need to commit the change here ..
           String query;
           if (icon != null)
               query = "UPDATE " + ETABLE + " SET " + EICON + " = '" + icon + "' ";
           else
              query = "UPDATE " + ETABLE + " SET " + EICON + " = null ";

           query = query + "WHERE " + EDATE + " = '" + ZeebUtil.formatDate(id.getDate()) + "' " +
                 "and "+ ENUMB + " = " + id.getNumber() + ";";
           return query;
      }

      /**
       * Change the parent of the entry.
       *
       * @param p Parent it.
       * @return ?
       */
      public String changeParent (ZeebID p)
      {
         parentId = p;
         // Need to commit the change here ..
         String query;
         query = "UPDATE " + ETABLE + " SET ";
         query = query + EPARENTDATE + " = date '" + ZeebUtil.formatDate(parentId.getDate()) +"', ";
         query = query + EPARENTNUMB + " = " + String.valueOf(parentId.getNumber()) + " ";
         query = query + "WHERE " + EDATE + " = '" + ZeebUtil.formatDate(id.getDate()) + "' " +
                 "and "+ ENUMB + " = " + id.getNumber() + ";";
         return query;
      }

      /**
       * Update the entry's content.
       *
       * @param nt The text.
       * @return ?
       */
      public String updateText (String nt)
      {
         String query;
         query = "UPDATE " + TTABLE + " SET etext" + " = ? ";
         query = query + "WHERE " + EDATE + " = ? AND " + ENUMB + " = ?;";
         try
         {
            java.sql.PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, nt);
            ps.setDate(2, java.sql.Date.valueOf(ZeebUtil.formatDate(id.getDate())));
            ps.setInt(3, id.getNumber());
            ps.executeUpdate();
            ps.close();
         }
         catch (java.sql.SQLException e){ query = e.getMessage();}
         return query + "\n"; //commitChange(nt);
      }

      // Create the entry here -- At this point we create the entries ..
      // Do this only if this one's new .. Otherwise, well we don't.
      /**
       * Create a new entry and store it in the db.
       *
       * @return
       */
      private String storeEntry ()
      {
           java.util.Calendar m = java.util.Calendar.getInstance();
           java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
           //String currentDate = df.format(m.getTime());
           id = new ZeebID(m.getTime(), 0);
           String ultQ;
           // Set up SQL stuff.
           java.sql.Statement statement = null;
           java.sql.ResultSet result = null;
           int numb = 0;
           // Let's set up some SQL statement
           String zSQL = "SELECT MAX(" + ENUMB + ") AS cono FROM " + ETABLE;
           zSQL = "SELECT MAX(";
           zSQL = zSQL + ENUMB + ") AS cono FROM ";
           zSQL = zSQL + ETABLE;
           zSQL = zSQL + " WHERE " + EDATE + "=";
           zSQL = zSQL + "'" + ZeebUtil.formatDate(id.getDate()) + "'";
           try
           {
             // Statement thing
               statement = db.createStatement();
             // Figure out what's the MAX number is using MAX in an SQL statement. Why? The ID for each
               // entry is made of the date (on which it's craeted) and a number (the first entry to be
               // created on a date gets 1, the second gets 2 .. and so forth). This way we can provide
               // each entry with a unique ID
               // Retrieve the data
               result = statement.executeQuery(zSQL);
               // Get the number and get the number up by one
               result.next();
               numb = result.getInt("cono") + 1;
            }
            catch (java.sql.SQLException e){}
           id.setNumber(numb);
           ultQ = zSQL;
           // Store the entry
           String query;
           query = "INSERT INTO " + ETABLE + " VALUES(";
           query = query + "date '" + ZeebUtil.formatDate(id.getDate()) + "', "
                   + String.valueOf(numb) + ", ";
           query = query + "'" + name.replace("'", "''") + "', ";
           if (parentId != null)
           {
               query = query + "date '" + ZeebUtil.formatDate(parentId.getDate())+ "', " +
                     String.valueOf(parentId.getNumber()) + ", ";
           }
           else
           {
               query = query + "null, null, ";
           }
           if (icon == null)
              query = query + "null, ";
           else
              query = query + "'" + icon + "', ";

           query = query + String.valueOf(type) + ", '" + note.replace("'", "''") + "')";

           ultQ = ultQ + "\n\n" + query;
           // Now that we have the query .. We need to craete the actual entry isnide the other
           // table this will hold the actual content - the text one, just create a blank one ..
           // blank one. It should be pretty simple .. .. Of course this shouln only be if this one
           // who gives a shit ..  The textJust do it.
           if (type == TEXT)
           {
               String query2;
               query2 = "INSERT INTO " + TTABLE + " VALUES (date '";
               query2 = query2 + ZeebUtil.formatDate(id.getDate()) + "', " + String.valueOf(numb) + ", '', null, null, null)";
               commitChange(query + ";" + query2);
           }
           else
           {
               commitChange(query);

           }
           age = OLD;
           return ultQ + "\n\n" + query;
      }

      public String commitChange2 (String q)
      {
         return q;
      }

      /**
       * Store data in the db.
       *
       * @param query Query to execute.
       * @return The query of error message (if operation fails).
       */
      public String commitChange (String query)
      {
         java.sql.Statement stmt = null;
         try
         {
            stmt = db.createStatement();
            stmt.execute(query);
            stmt.close();
            return query;
         }
         catch (java.sql.SQLException e)
         {  return e.getMessage();}
      }
} // Class end FFEntry
