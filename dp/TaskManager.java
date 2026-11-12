
import java.sql.SQLException;
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class which manages the tasks.
 * @author user
 */
public class TaskManager
{
   // We add the panels to this tab ...
   private javax.swing.JTabbedPane tab = null;
   /**
    * Tabbed pane to attach TaskPanel to.
    */
   javax.swing.JTabbedPane pane = null;
   /**
    * Connection to the database.
    */
   java.sql.Connection db = null;
   /**
    * TasPanel instance.
    */
   TaskPanel panel = null;
   /**
    * TaskPanelAux instance (mini todo panel)
    */
   TaskPanelAux mainTodoPanel = null;

   /**
    * .
    *
    * @param tp Tabbed pane where TabPanel will be attached to.
    * @param con Connection to the database.
    */
   public TaskManager (javax.swing.JTabbedPane tp, java.sql.Connection con)
   {
      pane = tp;
      db = con;
      loadTasks();
   }

   /**
    * Remove task.
    *
    * @param id Task id.
    */
   public void deleteTask (int id)
   {
      // Today's date ...
      java.sql.PreparedStatement ps;

      try
      {
           ps = db.prepareStatement("delete from todo where id = ?");
           ps.setInt(1, id);
           ps.execute();
           ps.close();
           this.reLoadTasks();
      }
      catch (java.sql.SQLException e)
      {

      }

   }

   /**
    * Mark a task as done.
    * @param id Task id.
    */
   public void markAsDone (int id)
   {
      // Today's date ...
      java.util.Date todayDate= new java.util.Date();
      java.sql.PreparedStatement ps;

      try
      {
           ps = db.prepareStatement("update todo set donedate = ? where id = ?");
           ps.setDate(1, java.sql.Date.valueOf(projx.ZeebUtil.formatDate(todayDate)));
           ps.setInt(2, id);
           ps.execute();
           ps.close();
           this.reLoadTasks();
      }
      catch (java.sql.SQLException e)
      {

      }

   }

   /**
    * Load tasks into linked list and create display panels.
    */
   private void loadTasks ()
   {
      java.sql.Statement stmt = null;
      java.sql.ResultSet result = null;
      java.util.LinkedList tList = null;

      try
      {
          stmt = db.createStatement();
          result = stmt.executeQuery("select * from todo");
          // Load the tasks into a linked list..
          tList = new java.util.LinkedList();
          while (result.next())
          {
               tList.add
                       (new projx.Task
                           (
                           result.getInt(1),
                           result.getString(2),
                           result.getString(3),
                           result.getDate(4),
                           result.getDate(5),
                           result.getDate(6),
                           result.getBoolean(7),
                           null
                           )
                       );
          }
          // Create a panel ... and load the list into the panel
          panel = new TaskPanel(0, "<html><head></head><body><b>Task Manager</b></body></html>", tList, this);
          // Add the panel into the tab pane thing...
          mainTodoPanel =  new TaskPanelAux(0, "", tList, this);
          pane.add(panel, panel.getGroupName(), 0);

      }
      catch (java.sql.SQLException e){}
   }

   /**
    * Get mini-todo panel, one which is displayed on the main window.
    * @return TaskPanelAux instance./
    */
   public TaskPanelAux getTodoPanel ()
   {
      return mainTodoPanel;
   }

   /**
    * Load the list of tasks again.
    */
   private void reLoadTasks ()
   {
      java.sql.Statement stmt = null;
      java.sql.ResultSet result = null;
      java.util.LinkedList tList = null;

      try
      {
          stmt = db.createStatement();
          result = stmt.executeQuery("select * from todo");
          // Load the tasks into a linked list..
          tList = new java.util.LinkedList();
          while (result.next())
          {
               tList.add
                       (new projx.Task
                           (
                           result.getInt(1),
                           result.getString(2),
                           result.getString(3),
                           result.getDate(4),
                           result.getDate(5),
                           result.getDate(6),
                           result.getBoolean(7),
                           null
                           )
                       );
          }
          // Create a panel ... and load the list into the panel
          panel.reloadTable(tList);
          mainTodoPanel.reloadTable(tList);
      }
      catch (java.sql.SQLException e){}
   }

   /**
    * Store a task as a record in the database.
    * @param task TaskTemp instance.
    */
   public void storeTask (projx.TaskTemp task)
   {
      String conf;

      conf = "Title : " + task.getTitle() + "\n";
      if (task.getStartDate() == null)
         conf = conf + "Start date not set \n";
      else
         conf = conf + "Start date : " + task.getStartDate().toString() + "\n";
      if (task.getDueDate() == null)
         conf = conf + "Due date not set \n";
      else
         conf = conf + "Due date : " + task.getDueDate().toString() + "\n";
      if (task.carryOver() == null)
         conf = conf + "Carry over is set to null, only consistent if due date is not set -\n    otherwise something's wrong";
      else
         conf = conf + "Carry over is : " + task.carryOver().toString();

      java.sql.PreparedStatement ps;
      String query = "insert into todo(title, descr, startdate, duedate, donedate, carryover, parent) "
              + "values (?, ?, ?, ?, ?, ?, ?)";

      try
      {
          ps =  db.prepareStatement(query);
          ps.setString(1, task.getTitle());
          ps.setString(2, null);
          if (task.getStartDate() == null)
             ps.setDate(3, null);
          else
             ps.setDate(3, java.sql.Date.valueOf(projx.ZeebUtil.formatDate(task.getStartDate())));

          if (task.getDueDate() == null)
             ps.setDate(4, null);
          else
             ps.setDate(4, java.sql.Date.valueOf(projx.ZeebUtil.formatDate(task.getDueDate())));

          ps.setDate(5, null);
          if (task.carryOver() == null)
               // Since we can't set null to boolean field for some reason - if due date is not se
               // then carry over defaults to false.
               ps.setBoolean(6, false);
          else
             ps.setBoolean(6, task.carryOver());
          ps.setInt(7, 0);
          ps.execute();
          ps.close();
      }
      catch (java.sql.SQLException e){
          javax.swing.JOptionPane.showMessageDialog(pane, e.getMessage());
      }

      reLoadTasks();
   }

}
