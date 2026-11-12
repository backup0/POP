/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projx;

/**
 * The text entry. NOT USED AS FOR NOW.
 * @author user
 */
public class TextEntry
{
   private String font;
   private int style;
   private int size;
   private String text;

   public TextEntry(String t)
   {
      setText(t);
   }

   public final void setText(String t)
   {
      text = t;
   }

   public String getText()
   {
      return text;
   }
}
