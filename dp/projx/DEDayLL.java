/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * A linked list w ..
 * 
 * @author user
 */
public class DEDayLL extends java.util.LinkedList
{
     private String month;

     public DEDayLL (String m) {month = m;}

     public String getMonth () {return month;}

     /**
      * Override the toString() method.
      *
      * @return Name of the month.
      */
   @Override
     public String toString ()
     {
         String[] mnth = new String[13];
         mnth[0] = "Be you crazy?";
         mnth[1] = "January";
         mnth[2] = "February";
         mnth[3] = "March";
         mnth[4] = "April";
         mnth[5] = "May";
         mnth[6] = "June";
         mnth[7] = "July";
         mnth[8] = "August";
         mnth[9] = "September";
         mnth[10] = "October";
         mnth[11] = "November";
         mnth[12] = "December";

         return mnth[Integer.parseInt(month)];
     }

}
