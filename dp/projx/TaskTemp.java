/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * Class for a new task. Instantiate this to create a new task.
 *
 * @author user
 */
public class TaskTemp
{
   /**
    * Short description.
    */
   private String title = null;
   /**
    * Full description.
    */
   private String description = null;
   /**
    * Start date.
    */
   private java.util.Date startDate = null;
   /**
    * Due date.
    */
   private java.util.Date dueDate = null;
   /**
    * Carry over or don't.
    */
   private Boolean carryOver = null;

   /**
    * Constructor.
    */
   public TaskTemp(){}

   /**
    * Set the short description
    * @param t The short description.
    */
   public void setTitle (String t) {title = t;}
   /**
    * Set the full description/task details.
    * @param d The full description.
    */
   public void setDescription (String d) {description = d;}
   /**
    * Set the start date.
    * @param sd Start date.
    */
   public void setStartDate (java.util.Date sd) {startDate = sd;}
   /**
    * Set the due date.
    * @param dd Due date.
    */
   public void setDueDate (java.util.Date dd) {dueDate = dd;}
   /**
    * Set the carry over flag.
    * @param co Carry over or don't.
    */
   public void setCarryOver (Boolean co) {carryOver = co;}

   /**
    * Get start date.
    * @return Start date.
    */
   public java.util.Date getStartDate () {return startDate;}
   /**
    * Get the due date
    * @return Due date.
    */
   public java.util.Date getDueDate () {return dueDate;}
   /**
    * Get the short description.
    * @return The short description.
    */
   public String getTitle () {return title;}
   /**
    * Carry over status.
    * @return Carry over status.
    */
   public Boolean carryOver () {return carryOver;}

   @Override
   public String toString () {return "Blah! Blah! Blah!";}


}
