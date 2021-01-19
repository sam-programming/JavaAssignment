/* Author: Samuel Warner
   Date: 8/11/2018
   Purpose: Create a logo by extending JPanel
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
public class SamsWestCoastLogoPanel extends JPanel
{
   //our paramaters in integers - must be variab
   int x = 75, y = 10, width = 100, height = 85;
   int count;
   Font logoFont = new Font("Consolas", Font.ITALIC, 100);
   String logo = "WCCR";
   Color lavender = new Color(124, 143, 205);
   Color royalBlue = new Color(19, 62, 202);
   Color paleBlue = new Color(153, 191, 199);   
      
   //paint component
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawRect(x, y, width, height);
      //contentPane.setBackground(Color.
      for(count = 0; count < 12; count++)
      { 
         //temp variables
         int xCo, yCo, widthX, heightY;
         //x and y move in relative to the height divided by double 
         //the amount of rectangles we need, incremented by the counter
         xCo = x + (count * (width / 24));
         yCo = y + (count * (height / 24));
         //width and height are subtracted by themselves minus number of
         //rectangles multiplied by the counter 
         widthX = width - count * (width / 12);
         heightY = height - count * (height / 12);
         if (count == 0)
         {
            g.setColor(Color.DARK_GRAY);
            g.fill3DRect(xCo, yCo, widthX, heightY, true); 
         }   
         else if (count % 2 == 0)
         {
            g.setColor(Color.LIGHT_GRAY);
            g.draw3DRect(xCo, yCo, widthX, heightY, false);
         }     
         else
         { 
            g.setColor(lavender);
            g.fill3DRect(xCo, yCo, widthX, heightY, false);
         }        
      } 
      // - Set font, color and draw logo
      g.setFont(logoFont);
      g.setColor(Color.DARK_GRAY);       
      g.drawString(logo, 185, 85);     
   }
   
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("West Coast Logo");
      frame.add(new SamsWestCoastLogoPanel());
      frame.setSize(500, 220);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }   

}