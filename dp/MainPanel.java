
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainPanel.java
 *
 * Nice.
 *
 * Created on May 13, 2012, 8:06:08 PM
 */

/**
 * This is the panel which displays the day journal, and also the task list.
 * 
 * @author user
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * The database connection.
     */
    private java.sql.Connection db = null;
    /**
     * A linked list which holds the entries.
     */
    private java.util.LinkedList entry = new java.util.LinkedList();
    // private java.util.Date x_currentEntry = null;
    private projx.DayJnlCategory currentCat;
    /**
     * Current entry, and instance of DailyEntry class.
     */
    private projx.DailyEntry currentEntry = null;
    /**
     * Bookmark dialog.
     */
    private BookmarkDialog bmDialog = null;

    /** Creates new form MainPanel */
    public MainPanel(java.sql.Connection conn, TaskPanelAux todoPanel, BookmarkDialog bd) {
        initComponents();
        //jSplitPane2.setDividerLocation(jSplitPane2.getWidth() - 200);
        db = conn;
        // Create an entry for TODAY if it doesn't yet exist ..
        prepareDailyEntry();
        // Create the list of entries
        createList();
        // load the categories ...
        loadCats();
        // Now load the actual entry ...
        getSelectedEntry();
        bmDialog = bd;
        jSplitPane2.setRightComponent(todoPanel);
        
        // Load .. the entry category
    }

    /**
     * Load the content of the entry selected by the user.
     *
     * Load the content of the entry selected by the user. Selection is made on the tree and the
     * list.
     *
     * @param id The id of the entry to load.
     * @param cat The category of the id to load.
     */
    private void loadEntry (java.util.Date id, int cat)
    {
         java.sql.PreparedStatement ps;
         java.sql.ResultSet result;
         String query  = "select text from dailyentry where id = ? and category = ? ";

         try
         {
            ps = db.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf(projx.ZeebUtil.formatDate(id)));
            ps.setInt(2, cat);
            result = ps.executeQuery();
            ps.close();
            if (result.next()) // if it's not empty
            {
               // Create a new entry object ....
               currentEntry = new projx.DailyEntry(db, id, cat, (byte) 1);
               text.setText(result.getString(1));
            }
            else // .. we're going to need to craete anew one.'
            {
                  projx.DailyEntry de =
                          new projx.DailyEntry(db, id, cat, (byte) 0);
                  text.setText("");
                  currentEntry = de;
            }
            query = "select * from dbookmark where id = ? and cat = ?";
            ps = db.prepareStatement(query);
            ps.setDate(1, java.sql.Date.valueOf(projx.ZeebUtil.formatDate(id)));
            ps.setInt(2, cat);
            result = ps.executeQuery();
            if (result.next())
            {
               buttonBookmark.setEnabled(false);
               buttonBookmark.setText("(Bookmarked)");
            }
            else
            {
               buttonBookmark.setEnabled(true);
               buttonBookmark.setText("Bookmark");
            }

         }
         catch (java.sql.SQLException e) { 
            javax.swing.JOptionPane.showMessageDialog(this, "loadEntry" + "\n" +e.getMessage());}
    }

    /**
     * Load all categories for the day journal and display them in the list.
     */
    private void loadCats ()
    {
         java.sql.PreparedStatement ps;
         java.sql.ResultSet result;
         list.setModel(new javax.swing.DefaultListModel());
         javax.swing.DefaultListModel lm =
                 (javax.swing.DefaultListModel) list.getModel();

         try
         {
            ps = db.prepareStatement("select * from dailycat;");
            result = ps.executeQuery();
            ps.close();
            while (result.next())
            {
                  lm.addElement(new projx.DayJnlCategory(result.getInt(1), result.getString(2)));
            }
            list.setSelectedIndex(0);
         }
         catch (java.sql.SQLException e){}
    }

    /**
     * Create a new entry for 'today' if it doesn't exist.
     *
     * When the application loads, check to see if an entry for 'today' exists - if it doesn't
     * create one.
     */
    private void prepareDailyEntry ()
    {
       // Check if entry for today exist - if it doens't then create one ...
       java.sql.PreparedStatement ps;
       
       try
       {
          ps = db.prepareStatement("select id from dailyentry where id = ?");
          ps.setDate(1, java.sql.Date.valueOf(projx.ZeebUtil.formatDate(new java.util.Date())));
          java.sql.ResultSet rs = ps.executeQuery();
          ps.close();
          // if en try for today doesn't exist - create one.
          if (!rs.next())
          {
            projx.DailyEntry de =
                          new projx.DailyEntry(db, null, 0, (byte) 0);
            currentEntry = de;
          }
       }
       catch (java.sql.SQLException e)
       {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "I can't create a new entry for today .. \n" + e.getMessage());

       }
    }

    /**
     * Get records from the database and fill the linked list.
     *
     * First load all the years.
     * Then load all the months for each year.
     * Lastly load all the days per each year + month.
     */
    private void createList ()
    {
       // Load year ...
       loadYear();
       // Now we load the month into the linked list .. We already have the year, so what we need is
       // the month for each year ... ... Let's do that.
       loadMonth();
       // Then we load the day .... Alrite.
       loadDay();
       // Now we have the list .. Next .. Load the list into a tree.
       createTree();
    }

    /**
     * Use the linked list and create the entry tree.
     */
    private void createTree()
    {
         tree.setRootVisible(false);
         javax.swing.tree.DefaultTreeModel tm =
                 (javax.swing.tree.DefaultTreeModel) tree.getModel();
         javax.swing.tree.DefaultMutableTreeNode yearNode = null;
         javax.swing.tree.DefaultMutableTreeNode monthNode = null;
         javax.swing.tree.DefaultMutableTreeNode root =
                 (javax.swing.tree.DefaultMutableTreeNode) tm.getRoot();
         projx.DEMonthLL year = null;
         projx.DEDayLL month = null;

         // Now let's load the stuff .. great.
         // Start by loading the root ..
         int i = 0; int o = 0; int p = 0;
         while (i < entry.size())
         {
            // Add year ...
            year = (projx.DEMonthLL) entry.get(i);
            yearNode = new javax.swing.tree.DefaultMutableTreeNode(year);
            tm.insertNodeInto(yearNode, root, root.getChildCount());
            tree.scrollPathToVisible(new javax.swing.tree.TreePath(yearNode.getPath()));
            // Now add month ...
            o = 0;
            while (o < year.size())
            {
                month = (projx.DEDayLL) year.get(o);
                monthNode = new javax.swing.tree.DefaultMutableTreeNode(month);
                tm.insertNodeInto(monthNode, yearNode, yearNode.getChildCount());
                if (entry.size() - i == 1)
                   tree.scrollPathToVisible(new javax.swing.tree.TreePath(monthNode.getPath()));
                // Now we add the day ... This one shouldn't be hard ..
                p = 0;
                while (p < month.size())
                {
                   projx.DailyEntry de = (projx.DailyEntry) month.get(p);
                   javax.swing.tree.DefaultMutableTreeNode dayNode =
                           new javax.swing.tree.DefaultMutableTreeNode(de);
                   tm.insertNodeInto(dayNode, monthNode, monthNode.getChildCount());
                   if ((year.size() - o == 1) && (entry.size() - i == 1))
                      tree.scrollPathToVisible(new javax.swing.tree.TreePath(dayNode.getPath()));
                   p++;
               }
               o++;
            }
            i++;
         }

         // Select the last node - this one is "TODAY"
         tree.setSelectionRow(tree.getRowCount()-1);
    }

    /**
     * Load day is in a specific year and month in which there are entries and store them in the
     * linked list.
     */
    private void loadDay ()
    {
       // This is awesome - we need to loop through 3 different level of stuff .. Yeah, list.
       String query = "select distinct id from dailyentry where to_char(id, 'YYYY-MM-DD') like ?";
       String cond = null;
       java.sql.PreparedStatement ps = null;
       java.sql.ResultSet result = null;
       projx.DEMonthLL year = null;
       projx.DEDayLL month = null;

       try
       {
            ps = db.prepareCall(query);
            // We need to loop through the first list ...
            int i = 0;
            while (i < entry.size())
            {
               // So, we retrieve the month thing
               year = (projx.DEMonthLL) entry.get(i);
               // now we need to loop though that ..
               int o = 0;
               while (o < year.size())
               {
                  month = (projx.DEDayLL) year.get(o);
                  // Set up the condition ...
                  cond = year.getYear() + "-" + month.getMonth() + "%";
                  ps.setString(1, cond);
                  result = ps.executeQuery();
                  while (result.next())
                  {
                     month.add(new projx.DailyEntry(db, result.getDate(1)));
                  }
                  o++;
               }
               i++;
            }

       }
       catch (java.sql.SQLException e) { text.setText("LOAD DAY \n" + e.getMessage());}

    }

    /**
     * Load month in a year in which entries exist and store them in the linked list.
     */
    private void loadMonth ()
    {

       String query =
           "select distinct to_char(id, 'MM') from dailyentry where to_char(id, 'YYYY-MM-DD') like ?";
       String cond = null;
       projx.DEMonthLL year = null;
       java.sql.PreparedStatement ps;
       java.sql.ResultSet result;

       // next?
       try
       {
          // set up the statement
          ps = db.prepareStatement(query);
          // Now we start looping ... We loop through the entry list
          int i = 0;
          while (i < entry.size())
          {
             // get the object ... on the list
             year = (projx.DEMonthLL) entry.get(i);
             // set up the condition ... it has to be soemthign like this .. year-%%
             // We need to retrieve the months here ..
             cond = year.getYear() + "-%";
             // Execute the statament ..
             ps.setString(1, cond);
             result = ps.executeQuery();
             // Create a new month object and attach to the list ...
             while (result.next())
             {
                  year.add(new projx.DEDayLL(result.getString(1)));
             }
             i++;
          }
       }
       catch (java.sql.SQLException e){ text.setText("LOAD MONTHS \n" + e.getMessage());}

    }
    /**
     * Load the year in which entries exist and store them in the linked list.
     */
    private void loadYear ()
    {
         java.sql.PreparedStatement ps;
         java.sql.ResultSet result; // loadData(
         String query = "select distinct to_char(id, 'YYYY') from dailyentry";
         // Load those into the linkedList
         try
         {
              // get all the year ....
              ps = db.prepareStatement(query);
              result = ps.executeQuery();
              // Load those into a linked list ..
              ps.close();
              while (result.next())
              {
                 // create a new month object ... and add it to the linked list ...
                 // ..the list is emplty at this point ..
                 entry.add(new projx.DEMonthLL(result.getString(1)));
              }

         }catch (java.sql.SQLException e){ text.setText("LOAD YEAR \n" + e.getMessage()); }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jSplitPane1 = new javax.swing.JSplitPane();
      jSplitPane2 = new javax.swing.JSplitPane();
      jPanel1 = new javax.swing.JPanel();
      jToolBar1 = new javax.swing.JToolBar();
      buttonSave = new javax.swing.JButton();
      buttonBookmark = new javax.swing.JButton();
      jSeparator1 = new javax.swing.JToolBar.Separator();
      labelCurrent = new javax.swing.JLabel();
      jScrollPane1 = new javax.swing.JScrollPane();
      text = new javax.swing.JTextPane();
      jPanel3 = new javax.swing.JPanel();
      jPanel2 = new javax.swing.JPanel();
      jScrollPane2 = new javax.swing.JScrollPane();
      tree = new javax.swing.JTree();
      jPanel4 = new javax.swing.JPanel();
      jLabel1 = new javax.swing.JLabel();
      jScrollPane3 = new javax.swing.JScrollPane();
      list = new javax.swing.JList();
      jToolBar2 = new javax.swing.JToolBar();
      buttonLoadEntry = new javax.swing.JButton();
      buttonCreateCat = new javax.swing.JButton();

      addComponentListener(new java.awt.event.ComponentAdapter() {
         public void componentHidden(java.awt.event.ComponentEvent evt) {
            formComponentHidden(evt);
         }
         public void componentShown(java.awt.event.ComponentEvent evt) {
            formComponentShown(evt);
         }
      });
      setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

      jSplitPane1.setDividerLocation(180);

      jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
         public void componentHidden(java.awt.event.ComponentEvent evt) {
            jPanel1ComponentHidden(evt);
         }
      });
      jPanel1.addContainerListener(new java.awt.event.ContainerAdapter() {
         public void componentRemoved(java.awt.event.ContainerEvent evt) {
            jPanel1ComponentRemoved(evt);
         }
      });
      jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

      jToolBar1.setFloatable(false);
      jToolBar1.setRollover(true);
      jToolBar1.setMaximumSize(new java.awt.Dimension(32767, 32));
      jToolBar1.setMinimumSize(new java.awt.Dimension(11, 32));
      jToolBar1.setPreferredSize(new java.awt.Dimension(100, 32));

      buttonSave.setText("Save");
      buttonSave.setFocusable(false);
      buttonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      buttonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
      buttonSave.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonSaveActionPerformed(evt);
         }
      });
      jToolBar1.add(buttonSave);

      buttonBookmark.setText("Bookmark");
      buttonBookmark.setFocusable(false);
      buttonBookmark.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      buttonBookmark.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
      buttonBookmark.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonBookmarkActionPerformed(evt);
         }
      });
      jToolBar1.add(buttonBookmark);
      jToolBar1.add(jSeparator1);

      labelCurrent.setFont(new java.awt.Font("sansserif", 2, 12));
      labelCurrent.setText("jLabel1");
      jToolBar1.add(labelCurrent);

      jPanel1.add(jToolBar1);

      text.setPreferredSize(new java.awt.Dimension(600, 24));
      jScrollPane1.setViewportView(text);

      jPanel1.add(jScrollPane1);

      jSplitPane2.setLeftComponent(jPanel1);

      jPanel3.setMaximumSize(new java.awt.Dimension(250, 32767));
      jPanel3.setMinimumSize(new java.awt.Dimension(250, 10));
      jPanel3.setPreferredSize(new java.awt.Dimension(250, 100));
      jSplitPane2.setRightComponent(jPanel3);

      jSplitPane1.setRightComponent(jSplitPane2);

      jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

      javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
      tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
      jScrollPane2.setViewportView(tree);

      jPanel2.add(jScrollPane2);

      jPanel4.setMaximumSize(new java.awt.Dimension(32767, 24));
      jPanel4.setMinimumSize(new java.awt.Dimension(10, 24));
      jPanel4.setPreferredSize(new java.awt.Dimension(10, 24));
      jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

      jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
      jLabel1.setText("Category");
      jPanel4.add(jLabel1);

      jPanel2.add(jPanel4);

      list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      jScrollPane3.setViewportView(list);

      jPanel2.add(jScrollPane3);

      jToolBar2.setFloatable(false);
      jToolBar2.setRollover(true);
      jToolBar2.setMaximumSize(new java.awt.Dimension(32767, 32));
      jToolBar2.setMinimumSize(new java.awt.Dimension(15, 32));
      jToolBar2.setPreferredSize(new java.awt.Dimension(15, 32));

      buttonLoadEntry.setText("Load entry");
      buttonLoadEntry.setFocusable(false);
      buttonLoadEntry.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      buttonLoadEntry.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
      buttonLoadEntry.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonLoadEntryActionPerformed(evt);
         }
      });
      jToolBar2.add(buttonLoadEntry);

      buttonCreateCat.setText("Create Category");
      buttonCreateCat.setFocusable(false);
      buttonCreateCat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      buttonCreateCat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
      buttonCreateCat.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCreateCatActionPerformed(evt);
         }
      });
      jToolBar2.add(buttonCreateCat);

      jPanel2.add(jToolBar2);

      jSplitPane1.setLeftComponent(jPanel2);

      add(jSplitPane1);
   }// </editor-fold>//GEN-END:initComponents

    /**
     * Create a new category.
     *
     * Simply ask the user for the name of the new category and then store the record in the
     * database.
     * @param evt
     */
    private void buttonCreateCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateCatActionPerformed
       // TODO add your handling code here:
       String op = javax.swing.JOptionPane.showInputDialog("Category name");
       java.sql.PreparedStatement ps;
       java.sql.ResultSet result;
       int catid;
       javax.swing.DefaultListModel lm =
               (javax.swing.DefaultListModel) list.getModel();

       try
       {
            // Create the new category
            ps = db.prepareStatement("select max(catid) from dailycat");
            result = ps.executeQuery();
            result.next();
            catid = result.getInt(1) + 1;
            ps.close();
            ps = db.prepareStatement("insert into dailycat values(?, ?)");
            ps.setInt(1, catid);
            ps.setString(2, op);
            ps.execute();
            ps.close();
            // Add the thign to the list .. Now this is a lzy way to do it, IRL you shoudl reload
            // the list of categories from the DB, and reconstruct the list ... It's a bit tricky
            // so we're not doign it now.. Bye.
            lm.addElement(new projx.DayJnlCategory(catid, op));
       }
       catch (java.sql.SQLException e){text.setText(e.getMessage());}
    }//GEN-LAST:event_buttonCreateCatActionPerformed

    /**
     * Store the current entry, and get the newly selected entry.
     *
     * This method is triggered when the use tries to load a new entry. Before the new entry is
     * loaded the contents of the current entry has to be stored first.
     * 
     * @param evt
     */
    private void buttonLoadEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoadEntryActionPerformed
       // TODO add your handling code here:
       // Save the content first
       currentEntry.upDateText(text.getText());
       getSelectedEntry();
    }//GEN-LAST:event_buttonLoadEntryActionPerformed

    /**
     * Get the selected entry, made of the combination of date on the tree and category. Then load
     * the content.
     */
    private void getSelectedEntry ()
    {
        javax.swing.tree.DefaultMutableTreeNode node;
       projx.DailyEntry sEntry;
       node =
           (javax.swing.tree.DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
       java.text.SimpleDateFormat df =
               new java.text.SimpleDateFormat("EEEE - MMMM d, yyyy");
       // This one's a dirty solution .. better solution later, if the selected node isn't a leaf
       // do nothing ...
       try
       {
            sEntry = (projx.DailyEntry) node.getUserObject();
            // Get category
            javax.swing.DefaultListModel lm =
                     (javax.swing.DefaultListModel) list.getModel();
            projx.DayJnlCategory dCat = (projx.DayJnlCategory) list.getSelectedValue();
            loadEntry(sEntry.getID(), dCat.getID());
            currentCat = dCat;
            labelCurrent.setText
                    (df.format(currentEntry.getID()) + " / Category: " + dCat.getName());
        }
        catch (java.lang.ClassCastException e){}
    }

    /**
     * Set the divider location when the panel is shown.
     *
     * @param evt
     */
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
       // TODO add your handling code here:
       // This one should be properly set -- since I don't have much time to do that, let's just
       // accept that for now
       jSplitPane2.setDividerLocation(700);
    }//GEN-LAST:event_formComponentShown

    /**
     * Store the content in the database.
     *
     * @param evt
     */
    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
       // TODO add your handling code here:
       currentEntry.upDateText(text.getText());
    }//GEN-LAST:event_buttonSaveActionPerformed

    /**
     * Bookmark this entry.
     *
     * @param evt
     */
    private void buttonBookmarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookmarkActionPerformed
       // TODO add your handling code here:
       java.sql.PreparedStatement ps = null;

       try
       {
            ps = db.prepareStatement("insert into dbookmark(id, cat) values (?, ?)");
            ps.setDate(1, java.sql.Date.valueOf(currentEntry.getIDS()));
            ps.setInt(2, currentEntry.getCat());
            ps.execute();
            ps.close();
            bmDialog.reload();
            buttonBookmark.setEnabled(false);
            buttonBookmark.setText("(Bookmarked)");
       }
       catch (java.sql.SQLException e)
       {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
       }

    }//GEN-LAST:event_buttonBookmarkActionPerformed

    private void jPanel1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentHidden
       // TODO add your handling code here:
       //javax.swing.JOptionPane.showMessageDialog(this, "This one's hidden");
    }//GEN-LAST:event_jPanel1ComponentHidden

    private void jPanel1ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jPanel1ComponentRemoved
       // TODO add your handling code here:
       //javax.swing.JOptionPane.showMessageDialog(this, "This one's hidden");
    }//GEN-LAST:event_jPanel1ComponentRemoved

    /**
     * Store the content of the panel is hidden.
     *
     * We store the content automatically whenever the panel is hidden, e.g. when the user opens
     * a different tab.
     *
     * @param evt
     */
    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
       // TODO add your handling code here:
       if (currentEntry != null)
       {
            currentEntry.upDateText(text.getText());
       }

    }//GEN-LAST:event_formComponentHidden


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton buttonBookmark;
   private javax.swing.JButton buttonCreateCat;
   private javax.swing.JButton buttonLoadEntry;
   private javax.swing.JButton buttonSave;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JToolBar.Separator jSeparator1;
   private javax.swing.JSplitPane jSplitPane1;
   private javax.swing.JSplitPane jSplitPane2;
   private javax.swing.JToolBar jToolBar1;
   private javax.swing.JToolBar jToolBar2;
   private javax.swing.JLabel labelCurrent;
   private javax.swing.JList list;
   private javax.swing.JTextPane text;
   private javax.swing.JTree tree;
   // End of variables declaration//GEN-END:variables

}
