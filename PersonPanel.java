package assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PersonPanel extends JPanel {
  /**
   * The following variables are used to construct the GUI
   */
  private JTextField time;    // To reference a textfield 
  private JTextField age;    // To reference a textfield 
  private JTextField name;    // To reference a textfield 
  private JLabel messageTime;            // To reference a label  
  private JLabel messageAge;            // To reference a label  
  private JLabel messageName;            // To reference a label  
  
  /**
   * These final variables are for data requirements
   */
  public final int TIME_LENGTH = 5; // time will hold 5 character space
  public final int AGE_LENGTH = 3; // age will hold 3 character space
  public final int NAME_LENGTH = 10; // time will hold 10 character space
  
  
  /**
   * The constructor of the PersonPanel will build the GUI
   * that will allow to change the file
   */
  
  public PersonPanel() {
     // Create a GridLayout manager with
     // six rows and one column.
     setLayout(new GridLayout(6, 0));
     
     // Create a label to display instructions.
     messageTime = new JLabel("Time in format ##:##");
     messageAge = new JLabel("Age");
     messageName = new JLabel("Name");
     
     // Create 3 text for time, age, name.
     time = new JTextField(5);
     age = new JTextField(5);
     name = new JTextField(5);
     
     // Add a border around the panel.
     setBorder(BorderFactory.createTitledBorder("Person Data"));
     
     // Add the text label and the text field in the panel
     add(messageTime);
     add(time);
     add(messageAge);
     add(age);
     add(messageName);
     add(name);
  }
  
  /**
  The getTime method gets the time and format it
  @return The formatted time ##:##
  */
  
  public String getTime() {
     String input;
     // Get the time and format it
     input = time.getText();
     input.trim();
     //only format time that is 4 char length 
     if(input.charAt(0) != 1 && input.length() < TIME_LENGTH) {
       input = "0" + input;
     }
     return input;
  }

  /**
   * the getAge method get the age of the person and format it to hold
   * three character space by adding extra spaces to age value less than 3 digits
   * @return age in correct format
   */
  public String getAge() {
     String input;
     
     // Get the age entered by the user into the
     // text field.
     input = age.getText();
     input.trim();
     //add 2 spaces to 1 digits
     if(input.length() == 1) {
       input += "  ";
     } else if(input.length() == 2) { //1 space to 2 digits
       input += " ";
     }
     return input;
  }
  /**
   * the getPersonName format the 
   * @return
   */
  public String getPersonName() {
     String input;
     
     // Get the name entered by the user into the
     // text field.
     input = name.getText();
     input.trim();
     //if input is less than 10 add extra spaces
     if(input.length() < NAME_LENGTH) {
       for(int i = input.length(); i < NAME_LENGTH; i++) {
         input += " ";
       }
     } else if (input.length() > NAME_LENGTH) { //just take the 10 char if name greater than 10 character
       input = input.substring(0, 9);
     }  
     return input;
  }
  
  /**
   * method to clear text fields
   */
  public void clearText() {
    time.setText("");
    age.setText("");
    name.setText("");
  }

}
