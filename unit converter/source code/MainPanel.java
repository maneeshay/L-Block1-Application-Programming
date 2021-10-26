//import required packages
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * The main graphical panel used to display conversion components.
 * 
 * This is the starting point for the assignment.
 * 
 * The variable names have been deliberately made vague and generic, and most comments have been removed.
 * 
 * You may want to start by improving the variable names and commenting what the existing code does.
 * 
 * @author mdixon
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	
	//private variables
	private final static String[] list = { "inches/cm" , "Pounds/Kilogram" , "Degrees/Radians" , "Acres/Hectares" , "Miles/Kilometers", "Yeards/meters", "Celsius/Fahrenheit"};
	private JTextField textField;        //declare text field component
	private JLabel label ,counter;       //declare label component
	private JCheckBox reverseCheckBox;   //declare check box component
	private JComboBox<String> combo;
	int count=0;            //counter value



	@SuppressWarnings("deprecation")
	JMenuBar setupMenu() {
		//add menu bar to the frame
		JMenuBar menuBar = new JMenuBar();
		
		//create menu 
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		
		//add menu  to menu bar
		menuBar.add(file);
		menuBar.add(help);
		
		
	    //create menu_item exit
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(e->System.exit(0));		//add action listener exit menu item to terminate the program
		exit.setIcon(new ImageIcon(("C:/Users/Dell/eclipse-workspace/Converter/exit.png")));   //set icon
		exit.setToolTipText("press here to exit program"); //set tool tip
		
		//create menu_item new
		JMenuItem New = new JMenuItem("New");
		New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)); //add shortcut key
		New.setIcon(new ImageIcon(("C:/Users/Dell/eclipse-workspace/Converter/New.png"))); //add icon
		New.setToolTipText("Press here to create new file"); //set relevant tool tip
		
		//create menu_item open
		JMenuItem open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK)); //add shortcut
		open.setIcon(new ImageIcon(("C:/Users/Dell/eclipse-workspace/Converter/open.png"))); //add icon
		open.setToolTipText("press here to open the existing file"); //set relevant tool tip
		
		//create menu_item save
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK)); //add shortcut
		save.setIcon(new ImageIcon(("C:/Users/Dell/eclipse-workspace/Converter/save.png"))); //set relevant tool tip
		save.setToolTipText("press here to save program");
		
		//separator line within the menu item
		JSeparator sp = new JSeparator();
		
		//add sub_menu to file menu
		file.add(New);
		file.add(open);
		file.add(save);
		file.add(sp);
		file.add(exit);
		
		
		//create menu item about
		JMenuItem about= new JMenuItem("About");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));  //adding shortcut key to menu item about
		about.setIcon(new ImageIcon(("C:/Users/Dell/eclipse-workspace/Converter/about.png"))); //add icon to menu item
		about.setToolTipText("click here to find about the program");  //adding tooltip
		
		
		//create menu_item edit
		JMenuItem edit = new JMenuItem("Edit");		
		edit.setIcon(new ImageIcon(("C:/Users/Dell/eclipse-workspace/Converter/edit.png")));  //add shortcut key to menu item edit
		edit.setToolTipText("press here to edit program");	//adding tooltip
		
		//add menu item to help menu
		help.add(about);
		help.add(edit);
		
		//add action listener to about to show message dialog box
		about.addActionListener(e->
		{JOptionPane.showMessageDialog(this, "Simple unit conversion application for common users with 7 categories of units.\n@Manisha 2020\n@TBC Kathmandu ");});
		
		return menuBar;  //display menu_bar
	}


	MainPanel() {

		ActionListener listener = new ConvertListener();

		combo = new JComboBox<String>(list);
		combo.addActionListener(listener); //convert values when option changed

		JLabel inputLabel = new JLabel("Enter value:");

		JButton convertButton = new JButton("Convert");
		convertButton.addActionListener(listener); // convert values when pressed
		convertButton.setToolTipText("press here to convert the unit");
		
		
		counter = new JLabel(String.valueOf("Conversion count :" +count));

		label = new JLabel("---");
		textField = new JTextField(5);
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER)
					convertButton.doClick();
			}
		});
		
		//create clear button
		JButton clear = new JButton("Clear");
		clear.setToolTipText("press here clear");
		
		//add action listener to the clear button to clear the values
		clear.addActionListener(e->{
			textField.setText(null);
			label.setText(null);
			count=0;
			counter.setText("0");
		});
		

		//reverse check box
		reverseCheckBox = new JCheckBox("Reverse Conversion");
	
		//reverseCheckBox.addItemListener(null);
		//adding component to the panel
		add(combo);
		add(inputLabel);
		add(textField);
		add(convertButton);
		add(label);
		add(reverseCheckBox);
		add(clear);
		add(counter);
		
		setPreferredSize(new Dimension(800, 80));
		setBackground(Color.LIGHT_GRAY);
	}

	private class ConvertListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			String text = textField.getText().trim();
			try {
			
			if(textField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(textField, "Enter any number");
			}

			if (text.isEmpty() == false) {
				
				
				double value = Double.parseDouble(text);

				// the factor applied during the conversion
				double factor = 0;

				// the offset applied during the conversion.
				double offset = 0;

				// Setup the correct factor/offset values depending on required conversion
				switch (combo.getSelectedIndex()) {

				case 0: //cm/ inches
					factor = 0.3937;
					break;
				
				case 1: //kilogram/pounds
					factor = 2.205;
					break;
					
				case 2: //Radians/Degrees
					factor= 57.296;
					break;
					
				case 3: //Hectares/Acres
					factor = 2.471;
					break;
				
				case 4: //Kilometers/Miles
					factor = 0.621;
					break;
					
				case 5: //Meters/Yards
					factor = 1.094;
					break;
					
				case 6: //Fahreinheit/celsius
					factor = (value-1.8)*0.556;
					break;
					
			}

				double result = factor * value + offset;
				String s = new DecimalFormat("0.00").format(result);
				label.setText(s);

				//label.setText(Double.toString(result));
				counter.setText("Conversion count: " + Integer.toString(++count));
			}
				
				//calculation when reverse check box
				if (reverseCheckBox.isSelected()) {
					if(text.isEmpty() == false) {
						double value = Double.parseDouble(text);

						// the factor applied during the conversion
						double factor = 0;

						// the offset applied during the conversion.
						double offset = 0;

						// Setup the correct factor/offset values depending on required conversion
						switch (combo.getSelectedIndex()) {

						case 0: // inches/cm
							factor = 2.54;
							break;
						
						case 1: //pounds/kilogram
							factor = 0.454;
							break;
							
						case 2: //Degrees/Radians
							factor= 0.0175;
							break;
							
						case 3: //Acres/Hectares
							factor = 0.405;
							break;
						
						case 4: //Miles/Kilometers
							factor = 1.61;
							break;
							
						case 5: //Yards/Meters
							factor = 0.914;
							break;
							
						case 6: //celsius/Fahreinheit
							factor = (value*1.8)+32;
							break;
							
					}

						double result = factor * value + offset;
						String s = new DecimalFormat("0.00").format(result);  //showing result in only two decimal place
						label.setText(s);
					}
				}
				
				}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,"Enter a valid number");
			}
		}
	}

}
