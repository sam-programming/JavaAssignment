/* Author: Samuel Warner
   Date: 12/11/2018 (Version 2)
   
   Purpose
   -------
   
   Create a program for West Coast Computer rentals.
   Allow the user to specify in a text field the number of 
   hours they wish to rent a particular peice of hardware, 
   with an option to pay for a lesson.  Display the information
   of the purchase, and a graphic.  Each aspect should be displa-
   yed on a different panel.
   
   Program Functions
   -----------------
   
   addEventListeners();
   addButtons();
   setFonts();
   + constructor WestCoastComputerRentals();
   
   Required File/s
   --------------
   
   \SamsWestCoastLogoPanel.java
   \RentLog\rentLog.txt
   
*/
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color.*;
import java.awt.geom.*;
import static java.nio.file.StandardOpenOption.*;
public class WestCoastComputerRentals extends JFrame
   implements ItemListener, ActionListener 
{
   // - Constant per hour fees
   private final double DESKTOP_RENT = 30.00;
   private final double PORTABLE_RENT = 20.00;
   private final double AUX_RENT = 7.00;
   private final double LESSON = 5.00;
   private double hoursRented = 0.00;
   private double totalCost = 0.00;
   // - Instantiate Text Feild and Radio buttons 
   JTextField userInput = new JTextField(10);
   JRadioButton pc = new JRadioButton("PC Desktop");
   JRadioButton mac = new JRadioButton("Mac Desktop");
   JRadioButton laptop = new JRadioButton("Laptop");
   JRadioButton tablet = new JRadioButton("Tablet");
   JRadioButton iPad = new JRadioButton("iPad");
   JRadioButton printer = new JRadioButton("Laser Printer");
   JRadioButton scanner = new JRadioButton("Scanner");
   JRadioButton lessonYes = new JRadioButton("Yes");
   JRadioButton lessonNo = new JRadioButton("No", true);
   
   // - Two button groups to allow only two choices
   ButtonGroup rentals = new ButtonGroup();
   ButtonGroup lessons = new ButtonGroup();
   
   // -Panels to seperate content
   JPanel titlePane = new JPanel();
   JPanel userEntry = new JPanel();
   JPanel rentalButtons = new JPanel();
   JPanel lessonButtons = new JPanel();
   JPanel displayCost = new JPanel();
   // - My custom JPanel containing the company logo
   SamsWestCoastLogoPanel logo = new SamsWestCoastLogoPanel();
   // - JPanel to contain heading elements
   JPanel heading = new JPanel();
   // - JPanel to contain the content.
   JPanel mainContent = new JPanel();
   
   // - Get the date ad format it
   Date dNow = new Date( );
   SimpleDateFormat ft = new SimpleDateFormat ("E yyyy/MM/dd");
      
   //Instantiate labels      
   JLabel title = new JLabel("West Coast Computer Rentals");
   JLabel entryPrompt = new JLabel("Please enter hours for rental: ");   
   JLabel output = new JLabel(" Total Cost: $" + totalCost);
   JLabel toRent = new JLabel("   Devices Available to Rent");
   JLabel lessonQ = new JLabel("   Do you need a lesson?");
   JLabel date = new JLabel("Current Date: " + ft.format(dNow));
   
   // - Instantiate new fonts
   Font titleFont = new Font("Consolas", Font.BOLD, 24);
   Font headings = new Font("Helvetica", Font.PLAIN, 20);
   //derive font copies another font with specified changes (smaller font here)
   Font text = headings.deriveFont(16f);
   
   // - Set up grid layouts
   GridLayout uList = new GridLayout(4, 2, 1, 1);
   GridLayout twoOptionGrid = new GridLayout(1, 2, 1, 1);
   GridLayout columnGrid = new GridLayout(7, 1, 2, 2);
   
   // - Get content pane
   Container contentPane = getContentPane();
   
   // - Some nice colours
   // - The fourth parameter - Alpha - will make the colour opaque
   Color paleBlue = new Color(153, 191, 199, 50);
   Color richBlue = new Color(78, 137, 171);
   // - And a border - Using borderFactory standards
   Border raisedBevel = BorderFactory.createRaisedBevelBorder();
   Border loweredBevel = BorderFactory.createLoweredBevelBorder();    
   Border compound = BorderFactory.createCompoundBorder(
      raisedBevel, loweredBevel);
   // - Instantiate a nice looking content separator
   JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);  
   
   // - Method to add item and actionListeners
   public void addEventListeners()
   {
      
      pc.addItemListener(this);
      mac.addItemListener(this);
      laptop.addItemListener(this);
      tablet.addItemListener(this);
      iPad.addItemListener(this);
      printer.addItemListener(this);
      scanner.addItemListener(this);
      lessonYes.addItemListener(this);
      lessonNo.addItemListener(this);
      userInput.addActionListener(this);
   }      
   // - Method to add buttons to ButtonGroup
   public void addButtons()
   {
      rentals.add(pc);
      rentals.add(mac);
      rentals.add(laptop);
      rentals.add(tablet);
      rentals.add(iPad);
      rentals.add(printer);
      rentals.add(scanner);
      lessons.add(lessonYes);
      lessons.add(lessonNo);      
   }   
   // - Method to set fonts
   public void setFonts()
   {
      title.setFont(titleFont);
      output.setFont(headings);
      toRent.setFont(headings);
      lessonQ.setFont(headings);
      entryPrompt.setFont(text);
      pc.setFont(text);
      mac.setFont(text);
      laptop.setFont(text);
      tablet.setFont(text);
      iPad.setFont(text);
      printer.setFont(text);
      scanner.setFont(text);
      lessonYes.setFont(text);
      lessonNo.setFont(text);
      lessonQ.setFont(text.deriveFont(Font.BOLD));
      toRent.setFont(text.deriveFont(Font.BOLD));
   }   
   // -Our Constructor method
   public WestCoastComputerRentals()
   {
      super("West Coast Computer Rentals");
      contentPane.setBackground(richBlue);
      // - Setting the size of the content panel
      // - Everything will fit into that Dimension
      mainContent.setPreferredSize(new Dimension(550, 750));
      // - Add the border we created earlier
      mainContent.setBorder(compound);
      // - Set layout for mainContent
      mainContent.setLayout(columnGrid);
      // - Centralise content in content pane
      setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
      add(date);
      // - My methods
      addButtons();
      addEventListeners();
      setFonts();
      // - Add title and text field to seperate panels, then to heading panel
      titlePane.add(title);
      heading.add(titlePane);
      // - Add separator - if we don't set size it won't show
      // default separator length is 0
      separator.setPreferredSize(new Dimension(300, 2)); 
      heading.add(separator);    
      userEntry.add(entryPrompt);
      userEntry.add(userInput);      
      heading.add(userEntry);      
      // - Start adding content to our main content panel      
      mainContent.add(heading);
      mainContent.add(toRent);
      // - Add Buttons
      rentalButtons.add(pc);
      rentalButtons.add(mac);
      rentalButtons.add(laptop);
      rentalButtons.add(tablet);
      rentalButtons.add(iPad);
      rentalButtons.add(scanner);
      rentalButtons.add(printer);
      // - Layout buttons in a grid
      rentalButtons.setLayout(uList);
      // - Add button groups
      mainContent.add(rentalButtons);      
      mainContent.add(lessonQ);
      // - Align buttons to the left and add
      lessonButtons.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
      lessonButtons.add(lessonYes);
      lessonButtons.add(lessonNo);
      mainContent.add(lessonButtons);
      displayCost.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
      displayCost.add(output);
      mainContent.add(displayCost);
      // - Add bespoke logo and set its background and border
      logo.setBackground(paleBlue);      
      logo.setBorder(raisedBevel);
      mainContent.add(logo);
      add(mainContent);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
   }
   // - Get file path and instantiate I/O variables
   Path filePath = Paths.get("RentLog\\rentLog.txt");
   OutputStream outputStream = null;  
   String sourceStr = "";
   String lessonStr = "No";
   
   
   // - This method gets user input from the JTextField 
   @Override
   public void actionPerformed(ActionEvent ev)
   {
      //- Get the source of action (our JTextField)
      Object source = ev.getSource();
      
      if(source == userInput)
      {
         // - We use try here to catch a NumberFormatException
        try
        {
          // - Parse string input to double
          hoursRented = Double.parseDouble(userInput.getText());  
        }
        catch(Exception exc)
        {
          // - Handle exception with message to user
          hoursRented = 0;
          userInput.setText(null);
          JOptionPane.showMessageDialog(null, "Hours required to rent is non-numeric" +
            "\nPlease re-enter");
        }
        if (hoursRented > 0)
        {
           // - Set the output using our newly acquired user input
         output.setText("Total Cost: $" + (totalCost * hoursRented));
         // - Show a JOptionPane message containing the rental information
         JOptionPane.showMessageDialog(null, "You rented a/n " + 
            sourceStr + "\nFor " + hoursRented + " hours.\nLesson: " +
            lessonStr + "\n" + output.getText(), 
            "Rental Information",
            JOptionPane.INFORMATION_MESSAGE);
         // - Write rental information to rentLog.txt        
         String fileString = sourceStr + " , Hours: " +
         hoursRented + ", Lesson: " + lessonStr + ", " + output.getText() + 
            ", " + dNow.toString();
        
         try
         {            
            // - File Writer
            outputStream = 
               new BufferedOutputStream(Files.newOutputStream(filePath, APPEND));
            BufferedWriter writer = 
               new BufferedWriter(new OutputStreamWriter(outputStream));           
           
            writer.write(fileString, 0, fileString.length());
            writer.newLine();
            writer.close();          
         }
         catch (IOException ex)
         {
            System.out.println("did not write");
         }
        }      
      }
   }
   // - This method reacts to any button clicks and acts accordingly
   @Override
   public void itemStateChanged(ItemEvent event)
   {
      //get source of action
      JRadioButton source = (JRadioButton) event.getSource();
      int select = event.getStateChange();
      //determine totalCost by item selected
      if (source == pc || source == mac)
      {
         if (select == ItemEvent.SELECTED)
            totalCost += DESKTOP_RENT;
         else
            totalCost -= DESKTOP_RENT;
      }
      else if (source == laptop || source == tablet || source == iPad)
      {
         if (select == ItemEvent.SELECTED)
            totalCost += PORTABLE_RENT;
         else
            totalCost -= PORTABLE_RENT;
      }
      else if (source == scanner || source == printer)
      {
         if (select == ItemEvent.SELECTED)
            totalCost += AUX_RENT;
         else
            totalCost -= AUX_RENT;
      }  
      //Add additional cost for lesson
      if (source == lessonYes)
      {
         if (select == ItemEvent.SELECTED)
            totalCost += LESSON;
         else
            totalCost -= LESSON;
      } 
      // - Set the output dynamically
      output.setText("Total Cost: $" + totalCost);
      
      if (source != lessonYes && source != lessonNo)
      {
         sourceStr = source.getText();
      }  
      else if (source == lessonYes || source == lessonNo)
      {
         lessonStr = source.getText();
      }    
   }
   public static void main(String[] args)
   {
      // -Main method to run our program
      JFrame.setDefaultLookAndFeelDecorated(true);
      WestCoastComputerRentals frame = new WestCoastComputerRentals();      
      frame.setSize(630, 900);     
      frame.setVisible(true);
   }
}   