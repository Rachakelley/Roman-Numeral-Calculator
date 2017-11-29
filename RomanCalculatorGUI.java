/*
 * Rachel Kelley
 * This class contains all the code/logic for the roman calculator GUI
 * and it's functions.
 * The roman numeral calculator allows you to add, subtract, divide,
 * mod, and multiply roman numerals. The integer representation of each
 * operand as well as the result is shown in addition to the roman numeral
 * representations.
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RomanCalculatorGUI extends JFrame implements ActionListener {
    private JTextField r1TextField = null, r2TextField = null, resultTextField = null,
                    int1TextField = null, int2TextField = null, int3TextField = null;
    private JButton[] arrOfButtons = null;
    private JPanel jp1 = null, jp2 = null;
    private final String[] buttonNames = {"I", "V", "X", "L", "C", "D", "M",
                    "CE", "-", "+", "/", "%", "*", "="};
    private char operation = ' ';
    private String romanResult = "";
    private int romanAsInteger1 = 0, romanAsInteger2 = 0, romanAsIntegerResult = 0;
    private String roman1 = "", roman2 = "";
    private boolean isOpPressed = false;
    private final int jp1row = 4, jp1col = 4; // rows and columns of gridlayout
    private final int jp2row = 3, jp2col = 2;
    final int width = 500, height = 500; // width and height of calculator
    private final RomanCalculator romanCalc;

    public RomanCalculatorGUI() {
        super();

        this.setTitle("Roman Numeral Calculator");
        this.setLocationRelativeTo(null);	// center window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); // scales calculator window
        this.setVisible(true); // make jFrame visible
        this.setSize(width, height);

        romanCalc = new RomanCalculator();
        r1TextField = new JTextField();
        r2TextField = new JTextField();
        resultTextField = new JTextField();
        int1TextField = new JTextField();
        int2TextField = new JTextField();
        int3TextField = new JTextField();
        arrOfButtons = new JButton[buttonNames.length]; // creating array space for 4 buttons
        jp1 = new JPanel();	// holds buttons
        jp2 = new JPanel();	// holds text boxes

        this.setLayout(new BorderLayout());	// main panel, holds everything in a border layout.
        this.add(jp1, BorderLayout.CENTER);
        this.add(jp2, BorderLayout.NORTH);

        jp1.setLayout(new GridLayout(jp1row, jp1col));	// creates grid layout to hold buttons	
        jp2.setLayout(new GridLayout(jp2row, jp2col));	// creates grid layout to hold text boxes

        // sets the sizes and placement of text boxes inside its layout
        Dimension tBoxSize = r1TextField.getPreferredSize();
        jp2.setPreferredSize(new Dimension((int)(tBoxSize.getWidth() * 2.5),
            (int)(tBoxSize.getHeight() * 3.5) * 2));

        jp2.add(r1TextField); // roman1, editing enabled 
        jp2.add(int1TextField); // integer1, editing disabled
        jp2.add(r2TextField); // roman2, editing enabled 
        jp2.add(int2TextField); // integer2, editing disabled	
        jp2.add(resultTextField); // roman result, editing disabled
        jp2.add(int3TextField); // integer3, shows roman result as an integer

        r1TextField.setHorizontalAlignment(JTextField.RIGHT);	// sets values in text box to be right aligned
        r2TextField.setHorizontalAlignment(JTextField.RIGHT);
        resultTextField.setHorizontalAlignment(JTextField.RIGHT);
        int1TextField.setHorizontalAlignment(JTextField.RIGHT);
        int2TextField.setHorizontalAlignment(JTextField.RIGHT);
        int3TextField.setHorizontalAlignment(JTextField.RIGHT);	

        resultTextField.setEditable(false); // disables the user from editing the content in this box
        int1TextField.setEditable(false);
        int2TextField.setEditable(false);
        int3TextField.setEditable(false);

        for(int i = 0; i < arrOfButtons.length; i++) { // adds all the buttons in the array to the jp1 (a JPanel) and adds an action listener to each button
            arrOfButtons[i] = new JButton(buttonNames[i]);
            arrOfButtons[i].addActionListener(this);
            jp1.add(arrOfButtons[i]);
        }	
    }

    @Override
    public void actionPerformed(ActionEvent e) { // when buttons are pressed, this method is called	
        try {
            if(e.getActionCommand().equals("D"))	// if D is pressed, roman numerals > D will disable. (M)
                arrOfButtons[6].setEnabled(false);
            
            if(e.getActionCommand().equals("C")) {	// if C is pressed, roman numerals > C will disable. (M, D)
                arrOfButtons[5].setEnabled(false);
                arrOfButtons[6].setEnabled(false);
            }
            if(e.getActionCommand().equals("L")) {	// if L is pressed, roman numerals > C will disable (M, D, C)
                for(int i = 4; i <= 6; i++)
                    arrOfButtons[i].setEnabled(false);
            }
            if(e.getActionCommand().equals("X")) {	// if L X pressed, roman numerals > X will disable (M, D, C, L)
                for(int i = 3; i <= 6; i++)
                    arrOfButtons[i].setEnabled(false);
            }
            if(e.getActionCommand().equals("V")) {	// if V is pressed, roman numerals > V will disable (M, D, C, L, X)
                for(int i = 2; i <= 6; i++)
                    arrOfButtons[i].setEnabled(false);
            }
            if(e.getActionCommand().equals("I")) {	// if I is pressed, roman numerals > I will disable (M, D, C, L, X, V)
                for(int i = 1; i <= 6; i++)
                    arrOfButtons[i].setEnabled(false);
            }

            if(e.getActionCommand().equals("CE")) {	// clears the calculator
                r1TextField.setText("");	// clears the text boxes
                r2TextField.setText("");
                resultTextField.setText("");
                int1TextField.setText("");
                int2TextField.setText("");
                int3TextField.setText("");		
                roman1 = "";	// sets the values to empty
                roman2 = "";
                romanResult = "";
                romanAsInteger1 = 0;
                romanAsInteger2 = 0;
                romanAsIntegerResult = 0;
                
                for(int i = 0; i <= 13; i++)
                    arrOfButtons[i].setEnabled(true); // enables the operations
                isOpPressed = false;
            }
            if(e.getActionCommand().equals("-")) {
                operation = e.getActionCommand().charAt(0);	// gets char action command so char operations may be performed
                roman1 = r1TextField.getText();		// get roman numeral input up to press of an operation
                romanAsInteger1 = romanCalc.convert_Roman_To_Int(roman1);
                int1TextField.setText("" + romanAsInteger1);	// set integer1 text field to integer conversion of roman numeral input 1
                isOpPressed = true;
                
                for(int i = 8; i <= 12; i++)
                    arrOfButtons[i].setEnabled(false);	// operation is now disabled
               
            }
            else if(e.getActionCommand().equals("+")) {
                operation = e.getActionCommand().charAt(0);
                roman1 = r1TextField.getText();
                romanAsInteger1 = romanCalc.convert_Roman_To_Int(roman1);
                int1TextField.setText("" + romanAsInteger1);
                isOpPressed = true;
                
                for(int i = 8; i <= 12; i++)
                    arrOfButtons[i].setEnabled(false);
            }
            else if(e.getActionCommand().equals("/")) {
                operation = e.getActionCommand().charAt(0);
                roman1 = r1TextField.getText();
                romanAsInteger1 = romanCalc.convert_Roman_To_Int(roman1);
                int1TextField.setText("" + romanAsInteger1);
                isOpPressed = true;
                
                for(int i = 8; i <= 12; i++)
                    arrOfButtons[i].setEnabled(false);
            }
            else if(e.getActionCommand().equals("%")) {
                operation = e.getActionCommand().charAt(0);
                roman1 = r1TextField.getText();
                romanAsInteger1 = romanCalc.convert_Roman_To_Int(roman1);
                int1TextField.setText("" + romanAsInteger1);
                isOpPressed = true;
                
                for(int i = 8; i <= 12; i++)
                    arrOfButtons[i].setEnabled(false);
            }
            else if(e.getActionCommand().equals("*")) {
                operation = e.getActionCommand().charAt(0);
                roman1 = r1TextField.getText();
                romanAsInteger1 = romanCalc.convert_Roman_To_Int(roman1);
                int1TextField.setText("" + romanAsInteger1);
                isOpPressed = true;
                
                for(int i = 8; i <= 12; i++)
                    arrOfButtons[i].setEnabled(false);
            }
            else if(e.getActionCommand().equals("=")) {
                roman2 = r2TextField.getText();
                romanAsInteger2 = romanCalc.convert_Roman_To_Int(roman2);
                int2TextField.setText("" + romanAsInteger2);

                for(int i = 0; i <= 6; i++)
                    arrOfButtons[i].setEnabled(false);
                
                for(int i = 8; i <= 13; i++)
                    arrOfButtons[i].setEnabled(false);

                switch(operation) {
                // need integer values of roman to do operations
                    case '+': romanAsIntegerResult = romanAsInteger1 + romanAsInteger2; break; 
                    case '*': romanAsIntegerResult = romanAsInteger1 * romanAsInteger2; break;
                    case '-': romanAsIntegerResult = romanAsInteger1 - romanAsInteger2; break;
                    case '/': romanAsIntegerResult = romanAsInteger1 / romanAsInteger2; break;
                    case '%': romanAsIntegerResult = romanAsInteger1 % romanAsInteger2; break;
                }

                if(romanAsIntegerResult < 0)
                    romanResult = "Invalid Result";
                else
                    romanResult = romanCalc.convert_Int_To_Roman(romanAsIntegerResult);
                resultTextField.setText(romanResult);	// places input of roman1 and roman2 together and prints to roman result text box
                int3TextField.setText("" + romanAsIntegerResult);
            }
            else {
                if(isOpPressed) {	
                    r2TextField.setText(r2TextField.getText() + e.getActionCommand());
                    isOpPressed = true;
                }
                else if(e.getActionCommand().equals("CE")) {
                    r1TextField.setText(""); // clears the text boxes
                    r2TextField.setText("");
                    resultTextField.setText("");
                    int1TextField.setText("");
                    int2TextField.setText("");
                    int3TextField.setText("");
                    roman1 = "";	// sets the values to empty
                    roman2 = "";
                    romanResult = "";
                    romanAsInteger1 = 0;
                    romanAsInteger2 = 0;
                    romanAsIntegerResult = 0;
                    isOpPressed = false;
                }
                else {
                    r1TextField.setText(r1TextField.getText() + e.getActionCommand());
                    isOpPressed = false;
                }
            }
        } catch(NumberFormatException c) {
            System.out.println("Invalid input");
        }
    }
}