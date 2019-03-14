package assignment1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangePanel extends JPanel {
  //These three buttons will allow user to select what to change
  private JRadioButton timeChange;
  private JRadioButton ageChange;
  private JRadioButton nameChange;
  /**
   * ENTRY_SIZE each entry is 18 character and a character is
   * stored as 2 bytes in binary which makes 36
   */
  public final int ENTRY_SIZE = 36; 
  
  private ButtonGroup changes;                 // Radio button group
  
  private JTextField location;    // To reference a textfield 
  private JLabel messageLocation;            // To reference a label 
  private JTextField newValue;    // To reference a textfield 
  private JLabel messageNewValue;            // To reference a label 
  
  /**
   * These final variables are for data requirements
   */
  public final int TIME_LENGTH = 5; // time will hold 5 character space
  public final int AGE_LENGTH = 3; // age will hold 3 character space
  public final int NAME_LENGTH = 10; // time will hold 10 character space
  
  /**
   * The constructor of the ChangePanel will build the GUI
   * that will allow to modify the data
   */
  public ChangePanel() {
     // Create a GridLayout manager with
     // 7 rows and one column.
     setLayout(new GridLayout(7, 0));
     
     // Add a border around the panel.
     setBorder(BorderFactory.createTitledBorder("Modify Data"));

     
     /**
      * Radio Buttons will be used to get value to be modified
      */
     timeChange = new JRadioButton("Select to change time.", true);
     ageChange = new JRadioButton("Select to change age.");
     nameChange = new JRadioButton("Select to change name.");
     
     // Create a label to display instructions.
     messageLocation = new JLabel("Please enter the location you wish to change.");
     location = new JTextField(5);
     
     // Create a label to display instructions.
     messageNewValue = new JLabel("Please enter the new value");
     newValue = new JTextField(5);
     
     // Group the radio buttons.
     changes = new ButtonGroup();
     changes.add(timeChange);
     changes.add(ageChange);
     changes.add(nameChange);
     
     // Add the components to the panel.
     add(messageLocation);
     add(location);
     add(timeChange);
     add(ageChange);
     add(nameChange);
     add(messageNewValue);
     add(newValue);
  }
  
  /**
  The getSelectedChange method 
  @return The change to make to the file.
  */
  
  public int getSelectedChange()
  {
     int change = 0;
  
     if (timeChange.isSelected())
        change = 0;
     else if (ageChange.isSelected())
        change = 10;
     else if (nameChange.isSelected())
        change = 16;   
  
  return change;
  }

  /**
   * In the file the first position is 0 which requires to do substract 1 to get the correct data
   * there is an offset of 36 bytes to get to the next location
   * @return the position entered by user converted to the actual location in the dat file
   */
  public int getEntryLocation() {
    int position;
    position = (Integer.parseInt(location.getText()) -1) * ENTRY_SIZE;
    return position;  
  }
  
  /**
   * 
   * @return the new value to modify the file
   */
  public String getNewValue() {
    return newValue.getText();
  }
  
  public String formatNewValue(int changeType, String str) {
    String input = "";
    if(changeType == 0) {
        input = newValue.getText().trim();
        //only format time that is 4 char length 
        if(input.charAt(0) != 1 && input.length() < TIME_LENGTH) {
          input = "0" + input;
        
      }
    } else if (changeType == 10) {
      
        input = newValue.getText().trim();
        //add 2 spaces to 1 digits
        if(input.length() == 1) {
        input += "  ";
        
        } else if(input.length() == 2) { //1 space to 2 digits
          input += " ";
      }
    } else if (changeType == 16) {
      input = newValue.getText().trim();
      //if input is less than 10 add extra spaces
      if(input.length() < NAME_LENGTH) {
          for(int i = input.length(); i < NAME_LENGTH; i++) {
            input += " ";
          }
       } else if (input.length() > NAME_LENGTH) { //just take the 10 char if name greater than 10 character
          input = input.substring(0, 9);
       }
    }
    return input;
    
  }
  
  /**
   * clear textfields
   */
  public void clearText() {
    location.setText("");
    newValue.setText("");
  }
}
 