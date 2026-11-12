
import java.awt.Color;
import java.util.Locale;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ZCalendar.java
 *
 * Created on Jun 12, 2012, 9:34:31 PM
 */

/**
 * Calendar display.
 *
 * @author user
 */
public class ZCalendar extends javax.swing.JPanel {

    private javax.swing.JLabel sun = new javax.swing.JLabel(" Su ", javax.swing.JLabel.RIGHT);
    private javax.swing.JLabel mon = new javax.swing.JLabel(" Mo ", javax.swing.JLabel.RIGHT);
    private javax.swing.JLabel tue = new javax.swing.JLabel(" Tu ", javax.swing.JLabel.RIGHT);
    private javax.swing.JLabel wed = new javax.swing.JLabel(" We ", javax.swing.JLabel.RIGHT);
    private javax.swing.JLabel thu = new javax.swing.JLabel(" Th ", javax.swing.JLabel.RIGHT);
    private javax.swing.JLabel fri = new javax.swing.JLabel(" Fr ", javax.swing.JLabel.RIGHT);
    private javax.swing.JLabel sat = new javax.swing.JLabel(" Sa ", javax.swing.JLabel.RIGHT);
    private javax.swing.JLabel[] days = null;
    private java.util.Calendar cal = null;

    /** Creates new form ZCalendar */
    public ZCalendar() {
        initComponents();
        createCalendar();
        cal = java.util.Calendar.getInstance();
        labelCYear.setText(String.valueOf(cal.get(java.util.Calendar.YEAR)));
        jList1.setSelectedIndex(cal.get(java.util.Calendar.MONTH));
        reloadCalendar(cal);
    }

    /**
     * Build the calendar display.
     */
    private void createCalendar()
    {
         sun.setFont(new java.awt.Font("san serif", java.awt.Font.BOLD, 12));
         cPanel.add(sun);
         mon.setFont(new java.awt.Font("san serif", java.awt.Font.BOLD, 12));
         cPanel.add(mon);
         tue.setFont(new java.awt.Font("san serif", java.awt.Font.BOLD, 12));
         cPanel.add(tue);
         wed.setFont(new java.awt.Font("san serif", java.awt.Font.BOLD, 12));
         cPanel.add(wed);
         thu.setFont(new java.awt.Font("san serif", java.awt.Font.BOLD, 12));
         cPanel.add(thu);
         fri.setFont(new java.awt.Font("san serif", java.awt.Font.BOLD, 12));
         cPanel.add(fri);
         sat.setFont(new java.awt.Font("san serif", java.awt.Font.BOLD, 12));
         cPanel.add(sat);
         days = new javax.swing.JLabel[42];
         days[0] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[0].setBackground(Color.white);
         days[0].setOpaque(true);
         days[0].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[0].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[0]);
         days[1] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[1] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[1].setBackground(Color.white);
         days[1].setOpaque(true);
         days[1].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[1].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[1]);
         days[2] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[2] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[2].setBackground(Color.white);
         days[2].setOpaque(true);
         days[2].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[2].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[2]);
         days[3] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[3] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[3].setBackground(Color.white);
         days[3].setOpaque(true);
         days[3].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[3].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[3]);
         days[4] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[4] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[4].setBackground(Color.white);
         days[4].setOpaque(true);
         days[4].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[4].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[4]);
         days[5] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[5] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[5].setBackground(Color.white);
         days[5].setOpaque(true);
         days[5].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[5].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[5]);
         days[6] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[6] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[6].setBackground(Color.white);
         days[6].setOpaque(true);
         days[6].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[6].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[6]);
         days[7] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[7] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[7].setBackground(Color.white);
         days[7].setOpaque(true);
         days[7].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[7].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[7]);
         days[8] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[8] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[8].setBackground(Color.white);
         days[8].setOpaque(true);
         days[8].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[8].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[8]);
         days[9] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[9] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[9].setBackground(Color.white);
         days[9].setOpaque(true);
         days[9].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[9].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[9]);
         days[10] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[10] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[10].setBackground(Color.white);
         days[10].setOpaque(true);
         days[10].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[10].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[10]);
         days[11] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[11] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[11].setBackground(Color.white);
         days[11].setOpaque(true);
         days[11].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[11].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[11]);
         days[12] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[12] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[12].setBackground(Color.white);
         days[12].setOpaque(true);
         days[12].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[12].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[12]);
         days[13] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[13] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[13].setBackground(Color.white);
         days[13].setOpaque(true);
         days[13].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[13].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[13]);
         days[14] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[14] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[14].setBackground(Color.white);
         days[14].setOpaque(true);
         days[14].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[14].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[14]);
         days[15] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[15] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[15].setBackground(Color.white);
         days[15].setOpaque(true);
         days[15].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[15].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[15]);
         days[16] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[16] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[16].setBackground(Color.white);
         days[16].setOpaque(true);
         days[16].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[16].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[16]);
         days[17] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[17] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[17].setBackground(Color.white);
         days[17].setOpaque(true);
         days[17].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[17].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[17]);
         days[18] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[18] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[18].setBackground(Color.white);
         days[18].setOpaque(true);
         days[18].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[18].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[18]);
         days[19] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[19] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[19].setBackground(Color.white);
         days[19].setOpaque(true);
         days[19].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[19].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[19]);
         days[20] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[20] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[20].setBackground(Color.white);
         days[20].setOpaque(true);
         days[20].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[20].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[20]);
         days[21] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[21] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[21].setBackground(Color.white);
         days[21].setOpaque(true);
         days[21].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[21].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[21]);
         days[22] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[22] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[22].setBackground(Color.white);
         days[22].setOpaque(true);
         days[22].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[22].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[22]);
         days[23] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[23] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[23].setBackground(Color.white);
         days[23].setOpaque(true);
         days[23].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[23].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[23]);
         days[24] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[24] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[24].setBackground(Color.white);
         days[24].setOpaque(true);
         days[24].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[24].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[24]);
         days[25] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[25] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[25].setBackground(Color.white);
         days[25].setOpaque(true);
         days[25].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[25].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[25]);
         days[26] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[26] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[26].setBackground(Color.white);
         days[26].setOpaque(true);
         days[26].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[26].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[26]);
         days[27] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[27] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[27].setBackground(Color.white);
         days[27].setOpaque(true);
         days[27].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[27].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[27]);
         days[28] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[28] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[28].setBackground(Color.white);
         days[28].setOpaque(true);
         days[28].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[28].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[28]);
         days[29] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[29] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[29].setBackground(Color.white);
         days[29].setOpaque(true);
         days[29].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[29].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[29]);
         days[30] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[30] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[30].setBackground(Color.white);
         days[30].setOpaque(true);
         days[30].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[30].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[30]);
         days[31] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[31] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[31].setBackground(Color.white);
         days[31].setOpaque(true);
         days[31].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[31].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[31]);
         days[32] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[32] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[32].setBackground(Color.white);
         days[32].setOpaque(true);
         days[32].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[32].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[32]);
         days[33] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[33] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[33].setBackground(Color.white);
         days[33].setOpaque(true);
         days[33].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[33].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[33]);
         days[34] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[34] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[34].setBackground(Color.white);
         days[34].setOpaque(true);
         days[34].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[34].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[34]);
          days[35] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[35] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[35].setBackground(Color.white);
         days[35].setOpaque(true);
         days[35].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[35].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[35]);
          days[36] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[36] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[36].setBackground(Color.white);
         days[36].setOpaque(true);
         days[36].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[36].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[36]);
          days[37] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[37] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[37].setBackground(Color.white);
         days[37].setOpaque(true);
         days[37].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[37].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[37]);
          days[38] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[38] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[38].setBackground(Color.white);
         days[38].setOpaque(true);
         days[38].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[38].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[38]);
          days[39] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[39] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[39].setBackground(Color.white);
         days[39].setOpaque(true);
         days[39].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[39].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[39]);
          days[40] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[40] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[40].setBackground(Color.white);
         days[40].setOpaque(true);
         days[40].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[40].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[40]);
          days[41] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
                  days[41] = new javax.swing.JLabel("", javax.swing.JLabel.RIGHT);
         days[41].setBackground(Color.white);
         days[41].setOpaque(true);
         days[41].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
         days[41].setVerticalAlignment(javax.swing.SwingConstants.TOP);
         cPanel.add(days[41]);
         cPanel.updateUI();
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
      jPanel1 = new javax.swing.JPanel();
      jToolBar1 = new javax.swing.JToolBar();
      buttonPrevYear = new javax.swing.JButton();
      labelCYear = new javax.swing.JLabel();
      buttonNextYear = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      jList1 = new javax.swing.JList();
      cPanel = new javax.swing.JPanel();

      setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

      jSplitPane1.setDividerLocation(120);

      jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

      jToolBar1.setFloatable(false);
      jToolBar1.setRollover(true);
      jToolBar1.setBorderPainted(false);
      jToolBar1.setMaximumSize(new java.awt.Dimension(32767, 32));
      jToolBar1.setMinimumSize(new java.awt.Dimension(15, 32));
      jToolBar1.setPreferredSize(new java.awt.Dimension(100, 32));

      buttonPrevYear.setText("<");
      buttonPrevYear.setFocusable(false);
      buttonPrevYear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      buttonPrevYear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
      buttonPrevYear.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonPrevYearActionPerformed(evt);
         }
      });
      jToolBar1.add(buttonPrevYear);

      labelCYear.setText("0000");
      jToolBar1.add(labelCYear);

      buttonNextYear.setText(">");
      buttonNextYear.setFocusable(false);
      buttonNextYear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      buttonNextYear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
      buttonNextYear.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonNextYearActionPerformed(evt);
         }
      });
      jToolBar1.add(buttonNextYear);

      jPanel1.add(jToolBar1);

      jList1.setModel(new javax.swing.AbstractListModel() {
         String[] strings = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
         public int getSize() { return strings.length; }
         public Object getElementAt(int i) { return strings[i]; }
      });
      jList1.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseReleased(java.awt.event.MouseEvent evt) {
            jList1MouseReleased(evt);
         }
      });
      jList1.addKeyListener(new java.awt.event.KeyAdapter() {
         public void keyReleased(java.awt.event.KeyEvent evt) {
            jList1KeyReleased(evt);
         }
      });
      jScrollPane1.setViewportView(jList1);

      jPanel1.add(jScrollPane1);

      jSplitPane1.setLeftComponent(jPanel1);

      cPanel.setLayout(new java.awt.GridLayout(7, 7, 2, 2));
      jSplitPane1.setRightComponent(cPanel);

      add(jSplitPane1);
   }// </editor-fold>//GEN-END:initComponents

    private void buttonPrevYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrevYearActionPerformed
       // TODO add your handling code here:
       cal.set(java.util.Calendar.YEAR, cal.get(java.util.Calendar.YEAR) - 1);
       labelCYear.setText(String.valueOf(cal.get(java.util.Calendar.YEAR)));
       jList1.setSelectedIndex(0);
       getCal();

    }//GEN-LAST:event_buttonPrevYearActionPerformed

    private void jList1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyReleased
       // TODO add your handling code here:
       getCal();
    }//GEN-LAST:event_jList1KeyReleased

    /**
     * Show calendar for the selected month.
     */
    private void getCal ()
    {
         int sel = jList1.getSelectedIndex();
         cal.set(java.util.Calendar.MONTH, sel);
         reloadCalendar(cal);
    }

    private void jList1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseReleased
       // TODO add your handling code here:
       getCal();
    }//GEN-LAST:event_jList1MouseReleased

    /**
     * Show calendar for the next/previous year.
     *
     * @param evt
     */
    private void buttonNextYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextYearActionPerformed
       // TODO add your handling code here:
       cal.set(java.util.Calendar.YEAR, cal.get(java.util.Calendar.YEAR) + 1);
       labelCYear.setText(String.valueOf(cal.get(java.util.Calendar.YEAR)));
       jList1.setSelectedIndex(0);
       getCal();
    }//GEN-LAST:event_buttonNextYearActionPerformed

    /**
     * Update the calendar.
     *
     * @param cal Calendar instance to be displayed
     */
   private void reloadCalendar(java.util.Calendar cal)
   {
      // Set the day day to teh first of the month, so that we can find out what day it is.
      cal.set(cal.get(java.util.Calendar.YEAR),
              cal.get(java.util.Calendar.MONTH), 1);

      // Now we need some math ... Let's start.
      // Get - what day to start
      int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK) - 1; // What day?
      // get how many days we ahve for this particular month
      int maxDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
      // Remember we use 7 * 6 matrix here ..
      // Now how do we fill the stuff? Figure out where to start .. We always start at the first
      // week .. so between 0 and 6 ... .. len just loop until we get to teh final day, well that's
      // easy
      // let's start... What we need is a while
      // So, we have the day of week .. tahts' where we start .. So, if it's 1 then it's on sunday
      // 2 then it's on ...
      //javax.swing.JOptionPane.showMessageDiardalog(null, String.valueOf(dayOfWeek));
      String start = null;
      String end = null;
      String startA = "<html><head></head><body><font size=\"3\">";
      String startB = "<html><head></head><body><font size=\"3\" color=\"red\">";
      String endA = "</font>&nbsp;&nbsp;<body>";
      String endB = " **</font>&nbsp;&nbsp;<body>";
      int o = 0;
      while (o < 42)
      {
         days[o].setText("");
         o++;
      }
      o = 0;
      while( o < maxDay)
      {
         start = startA; end = endA;
         days[dayOfWeek+o].setText(start + String.valueOf(o+1) + end);
         o++;
      }

   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton buttonNextYear;
   private javax.swing.JButton buttonPrevYear;
   private javax.swing.JPanel cPanel;
   private javax.swing.JList jList1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JSplitPane jSplitPane1;
   private javax.swing.JToolBar jToolBar1;
   private javax.swing.JLabel labelCYear;
   // End of variables declaration//GEN-END:variables

}
