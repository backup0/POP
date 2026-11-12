package projx;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Task for the task manager.
 * 
 * @author user
 */
public class Task
{
   // ID
   /**
    * Id.
    */
   private int id;
   // The to human stuff
   /**
    * Short description.
    */
   private String title = null;
   /**
    * Full description.
    */
   private String descriptions = null;
   // The date stuff ..
   /**
    * Start date.
    */
   private java.util.Date startDate = null;
   /**
    * Due date.
    */
   private java.util.Date dueDate = null;
   /**
    * Done date.
    */
   private java.util.Date doneDate = null; // done if it's not null - also record the date on which
   // the Task is completed ..
   /**
    * Carry over or don't.
    */
   private Boolean carryOver = null;
   // ... parent.
   /**
    * Category to which this task belongs.
    */
   private ZeebID parent = null;
  
   /**
    * Constructor.
    *
    * @param id Id
    * @param title Short description.
    * @param descriptions Full description.
    * @param startDate Start date.
    * @param dueDate Due date.
    * @param doneDate Done date.
    * @param carryOver If task should be carried over past it's due date if not done.
    * @param parent (??)
    */
   public Task (
           int id,
           String title,
           String descriptions,
           java.util.Date startDate,
           java.util.Date dueDate,
           java.util.Date doneDate,
           Boolean carryOver,
           ZeebID parent
           )
   {
         this.id = id;
         this.title = title;
         this.descriptions = descriptions;
         this.startDate = startDate;
         this.dueDate = dueDate;
         this.doneDate = doneDate;
         this.carryOver = carryOver;
         this.parent = parent;
   }

   // The accessors ..
   /**
    * Get the id.
    *
    * @return id.
    */
   public int getId () {return id;}
   /**
    * Get the short description.
    *
    * @return SHort description.
    */
   public String getTitle () {return title;}
   /**
    * Get the full description.
    *
    * @return The full description.
    */
   public String getDesc () {return descriptions;}
   /**
    * Get start date.
    *
    * @return Start date.
    */
   public java.util.Date getStartDate () {return startDate;}
   /**
    * Get the due date.
    *
    * @return Due date.
    */
   public java.util.Date getDueDate () {return dueDate;}
   /**
    * Get the done date.
    *
    * @return Done date.
    */
   public java.util.Date getDoneDate () {return doneDate;}
   /**
    * Whether the task should be carried over.
    *
    * @return Carry over status.
    */
   public Boolean carryOver () {return carryOver;}

   private String createStatus ()
   {
      String returnText = "Open";
      java.util.Date todayDate = new java.util.Date();

      if (doneDate != null) // If the task is done, then .. What's the problem?
      {
           returnText =
                   "<font color=\"navy\"><b>Done</b></font>";
      }
      else // It's not done ...
      {
         if (dueDate != null && todayDate.after(dueDate)) // if the due date is before today and
            //it's not done .. Then the atsk is overdue.
         {
            returnText =
                    "<font color=\"red\"><b>Overdue</b></font>";
         }
      }
      return returnText;
   }

   /**
    * Override the toString()
    *
    * @return Formatted .. string.
    */
   @Override
   public String toString () 
   {
      String text =
            "<html><head></head><body>" + createStatus() + "</body></html>";
      return text;
   }
}
