/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 *
 * @author user
 */
public class DEMonthLL extends java.util.LinkedList
{
     private String year;

     public DEMonthLL (String y)
     {
         year = y;
     }

     public String getYear () {return year;}

   @Override
     public String toString() {return year;}


}
