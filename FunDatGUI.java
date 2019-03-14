package assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FunDatGUI extends JFrame {
  /**
   * Variable to build the GUI
   */
  private PersonPanel personPanel;
  private ChangePanel changePanel;
  private JPanel buttonPanel; // To hold the output buttons
  private JButton addButton; // To calculate the cost
  private JButton modifyButton; // To exit the application
  
  /**
   * The constructor of the FunDatGUI will build the GUI
   * that will allow to change the file
   */
  public FunDatGUI()
  {
     // Display a title.
     setTitle("Fun.dat GUI");
  
     // Specify an action for the close button.
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
     // Create a BorderLayout manager.
     setLayout(new BorderLayout());

     // Create the custom panels.
     personPanel = new PersonPanel();
     changePanel = new ChangePanel();
  
     // Create the button panel.
     buildButtonPanel();
  
     // Add the components to the content pane.
     add(personPanel, BorderLayout.WEST);
     add(changePanel, BorderLayout.EAST);
     add(buttonPanel, BorderLayout.SOUTH);
  
     // Pack the contents of the window and display it.
     pack();
     setVisible(true);
  }
  
  /**
   * The buildButtonPanel method builds the button panel.
   */
  private void buildButtonPanel()
  {
     // Create a panel for the buttons.
     buttonPanel = new JPanel();
  
     // Create the buttons.
     addButton = new JButton("Add to File");
     modifyButton = new JButton("Modify File");
  
     // Register the action listeners.
     addButton.addActionListener(new AddButtonListener());
     modifyButton.addActionListener(new ModifyButtonListener());
  
     // Add the buttons to the button panel.
     buttonPanel.add(addButton);
     buttonPanel.add(modifyButton);
  }
  
  /**
  Private inner class that handles the event when
  the user clicks the Add to File button.
  */
  
  private class AddButtonListener implements ActionListener
  {
     public void actionPerformed(ActionEvent e)
     {
                    
        //open the file
        RandomAccessFile file;
        try {
          file = new RandomAccessFile("fun.dat", "rw");
         //Go to end of File and write Info entered
          file.seek(file.length());
          file.writeChars(personPanel.getTime() + personPanel.getAge() + personPanel.getPersonName());
          
          //Display Success message
          JOptionPane.showMessageDialog(null, "You added { " + personPanel.getTime() + personPanel.getAge() 
                                  + personPanel.getPersonName() + " } in the file fun.dat");
          
          //clear text fields and close file
          personPanel.clearText();
          file.close();
          
        } catch (FileNotFoundException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        } 
        
        
     }
  }
  
  /**
  Private inner class that handles the event when
  the user clicks the Modify File button.
  */
  
  private class ModifyButtonListener implements ActionListener {
     public void actionPerformed(ActionEvent e) {
       //open the file
       RandomAccessFile file;
       int position = changePanel.getEntryLocation() + changePanel.getSelectedChange();
       String input = changePanel.getNewValue();
       String oldValue = "";
       
       try {
         file = new RandomAccessFile("fun.dat", "rw");
        //Go to end of Position and change file
         file.seek(position);
         switch (changePanel.getSelectedChange()) {
           case 0: {
             //read the old value
             for(int i = 0; i < personPanel.TIME_LENGTH; i++) {
               oldValue += file.readChar();
             }
             
             //go back to position and replace with spaces
             file.seek(position);
             file.writeChars("     ");
             
             //go back and write data
             file.seek(position);
             file.writeChars(changePanel.formatNewValue(changePanel.getSelectedChange(), input));
           }
           break;
           case 10: {
               //read the old value
               for(int i = 0; i < personPanel.AGE_LENGTH; i++) {
                 oldValue += file.readChar();
               }
               //go back to position and replace with spaces
               file.seek(position);
               file.writeChars("   ");
               //go back and write new data
               file.seek(position);
               file.writeChars(changePanel.formatNewValue(changePanel.getSelectedChange(), input));
           }
           break;
           case 16: {
             //read the old value
               for(int i = 0; i < personPanel.NAME_LENGTH; i++) {
                 oldValue += file.readChar();
               }
               //go back to position and replace with spaces
               file.seek(position);
               file.writeChars("          ");
               //go back and write new data
               file.seek(position);
               file.writeChars(changePanel.formatNewValue(changePanel.getSelectedChange(), input));
           }
           break;
         }
         
         //Display success message
         JOptionPane.showMessageDialog(null, "You changed { " + oldValue + " } to { " 
             + changePanel.formatNewValue(changePanel.getSelectedChange(), input) + " } in the file fun.dat");
         
         //clear and close
         changePanel.clearText();
         file.close();
         
       } catch (FileNotFoundException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
       } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
       } 
       
       
     }
  }




}
