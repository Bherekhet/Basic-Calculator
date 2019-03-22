package basiccalculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Calculator extends JFrame implements ActionListener {

    //to initialize the panels that holds the buttons
    private JPanel maybePanel, numpadPanel, operation1Panel, operation2Panel, equalSignPanel;
    //to initialize the buttons in each panel
    private JButton oneButton, twoButton, threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton, nineButton, zeroButton, plusButton,
            minusButton, multiplyButton, divideButton, equalButton, dotButton, clearButton,
            clearAllButton, signButton, backButton, divideByXButton, squareButton,
            squareRootButton, percentButton;

    //holds corresponding values to button from 0 - 9
    int values[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    //holds the entire button on the calculator
    JButton Buttons[] = new JButton[24];

    String inputFromUser = ""; //gets the user input and also holds the value to display on the textfield of the calculator
    String operator = "";      //stores what operator was selected by the user
    String firstNum = "";      //stores the first of the two numbers being computed
    String secondNum = "";     //stores the second of the two numbers being computed
    boolean computed = false;  //keeps track of the clear all button
    int gotFirstNum = 0;       //keeps track if the user is done inputting the first number

    //text field to show the inputs and computed values to user
    private JTextField computeOperationField;

    //this method creates the user interface
    public void createCalculatorGUI() {
        //.setBounds(int x, int y, width, height)
        Container containerPane = getContentPane();
        containerPane.setBackground(Color.lightGray);
        containerPane.setLayout(null);

        //create number input interface
        computeOperationField = new JTextField();
        computeOperationField.setFont(new Font("SansSerif", Font.BOLD, 35));
        computeOperationField.setText("0");
        computeOperationField.setEditable(false);
        computeOperationField.setHorizontalAlignment(JLabel.RIGHT);
        computeOperationField.setBounds(0, 0, 395, 80);
        computeOperationField.setBackground(Color.white);
        containerPane.add(computeOperationField);

        //create maybe panel interface
        maybePanel = new JPanel();
        maybePanel.setLayout(null);
        maybePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        maybePanel.setBounds(2, 85, 388, 40);
        maybePanel.setBackground(Color.green);
        containerPane.add(maybePanel);

        //create display operation process
        /*
        operationProcessField = new JTextField();
        operationProcessField.setFont(new Font("Cambria", Font.ROMAN_BASELINE, 30));
        operationProcessField.setText("0");
        operationProcessField.setEditable(false);
        operationProcessField.setHorizontalAlignment(JLabel.RIGHT);
        operationProcessField.setBounds(0, 0, 388, 40);
        operationProcessField.setBackground(Color.green);
        maybePanel.add(operationProcessField);
         */
        //create numbers pad panel
        numpadPanel = new JPanel();
        numpadPanel.setLayout(null);
        numpadPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        numpadPanel.setBounds(2, 130, 286, 358);
        numpadPanel.setBackground(Color.white);
        containerPane.add(numpadPanel);

        //button %
        percentButton = new JButton();
        percentButton.setBounds(5, 5, 90, 67);
        percentButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        percentButton.setText("%");
        percentButton.addActionListener(this);
        numpadPanel.add(percentButton);
        Buttons[15] = percentButton;

        // button _/
        squareRootButton = new JButton();
        squareRootButton.setBounds(98, 5, 90, 67);
        squareRootButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        squareRootButton.setText("\u221A");
        squareRootButton.addActionListener(this);
        numpadPanel.add(squareRootButton);
        Buttons[16] = squareRootButton;

        // button X^2
        squareButton = new JButton();
        squareButton.setBounds(191, 5, 90, 67);
        squareButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        squareButton.setText("X\u00B2");
        squareButton.addActionListener(this);
        numpadPanel.add(squareButton);
        Buttons[17] = squareButton;

        //button CE
        clearButton = new JButton();
        clearButton.setBounds(5, 75, 90, 67);
        clearButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        clearButton.setText("CE");
        //oneButton.setBackground(Color.CYAN);
        clearButton.addActionListener(this);
        numpadPanel.add(clearButton);
        Buttons[12] = clearButton;

        //button C
        clearAllButton = new JButton();
        clearAllButton.setBounds(98, 75, 90, 67);
        clearAllButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        clearAllButton.setText("C");
        //twoButton.setBackground(Color.CYAN);
        clearAllButton.addActionListener(this);
        numpadPanel.add(clearAllButton);
        Buttons[13] = clearAllButton;

        //button <
        backButton = new JButton();
        backButton.setBounds(191, 75, 90, 67);
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        backButton.setText("<");
        //twoButton.setBackground(Color.CYAN);
        backButton.addActionListener(this);
        numpadPanel.add(backButton);
        Buttons[14] = backButton;

        //button 7
        sevenButton = new JButton();
        sevenButton.setBounds(5, 145, 90, 67);
        sevenButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        sevenButton.setText("7");
        //twoButton.setBackground(Color.CYAN);
        sevenButton.addActionListener(this);
        numpadPanel.add(sevenButton);
        Buttons[7] = sevenButton;

        //button 8
        eightButton = new JButton();
        eightButton.setBounds(98, 145, 90, 67);
        eightButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        eightButton.setText("8");
        eightButton.addActionListener(this);
        numpadPanel.add(eightButton);
        Buttons[8] = eightButton;

        nineButton = new JButton();
        nineButton.setBounds(191, 145, 90, 67);
        nineButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        nineButton.setText("9");
        nineButton.addActionListener(this);
        numpadPanel.add(nineButton);
        Buttons[9] = nineButton;

        //button 4
        fourButton = new JButton();
        fourButton.setBounds(5, 215, 90, 67);
        fourButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        fourButton.setText("4");
        //twoButton.setBackground(Color.CYAN);
        fourButton.addActionListener(this);
        numpadPanel.add(fourButton);
        Buttons[4] = fourButton;

        //button 5
        fiveButton = new JButton();
        fiveButton.setBounds(98, 215, 90, 67);
        fiveButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        fiveButton.setText("5");
        fiveButton.addActionListener(this);
        numpadPanel.add(fiveButton);
        Buttons[5] = fiveButton;

        //button 6
        sixButton = new JButton();
        sixButton.setBounds(191, 215, 90, 67);
        sixButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        sixButton.setText("6");
        sixButton.addActionListener(this);
        numpadPanel.add(sixButton);
        Buttons[6] = sixButton;

        //button 1
        oneButton = new JButton();
        oneButton.setBounds(5, 285, 90, 67);
        oneButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        oneButton.setText("1");
        //twoButton.setBackground(Color.CYAN);
        oneButton.addActionListener(this);
        numpadPanel.add(oneButton);
        Buttons[1] = oneButton;

        //button 2
        twoButton = new JButton();
        twoButton.setBounds(98, 285, 90, 67);
        twoButton.setFont(new Font("sansSerif", Font.PLAIN, 30));
        twoButton.setText("2");
        twoButton.addActionListener(this);
        numpadPanel.add(twoButton);
        Buttons[2] = twoButton;

        //button 3
        threeButton = new JButton();
        threeButton.setBounds(191, 285, 90, 67);
        threeButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        threeButton.setText("3");
        threeButton.addActionListener(this);
        numpadPanel.add(threeButton);
        Buttons[3] = threeButton;

        //create operation panel
        operation1Panel = new JPanel();
        operation1Panel.setLayout(null);
        operation1Panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        operation1Panel.setBounds(291, 130, 98, 358);
        operation1Panel.setBackground(Color.white);
        containerPane.add(operation1Panel);

        //button 1/x
        divideByXButton = new JButton();
        divideByXButton.setBounds(5, 5, 90, 67);
        divideByXButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        divideByXButton.setText("1/x");
        divideByXButton.addActionListener(this);
        operation1Panel.add(divideByXButton);
        Buttons[18] = divideByXButton;

        //button /
        divideButton = new JButton();
        divideButton.setBounds(5, 75, 90, 67);
        divideButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        divideButton.setText("\u00f7");
        divideButton.addActionListener(this);
        operation1Panel.add(divideButton);
        Buttons[19] = divideButton;

        //button *
        multiplyButton = new JButton();
        multiplyButton.setBounds(5, 145, 90, 67);
        multiplyButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        multiplyButton.setText("\u00d7");
        multiplyButton.addActionListener(this);
        operation1Panel.add(multiplyButton);
        Buttons[20] = multiplyButton;

        //button -
        minusButton = new JButton();
        minusButton.setBounds(5, 215, 90, 67);
        minusButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        minusButton.setText("\u002D");
        minusButton.addActionListener(this);
        operation1Panel.add(minusButton);
        Buttons[21] = minusButton;

        //button +
        plusButton = new JButton();
        plusButton.setBounds(5, 285, 90, 67);
        plusButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        plusButton.setText("\u002B");
        plusButton.addActionListener(this);
        operation1Panel.add(plusButton);
        Buttons[22] = plusButton;

        //create operation panel 2
        operation2Panel = new JPanel();
        operation2Panel.setLayout(null);
        operation2Panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        operation2Panel.setBackground(Color.white);
        operation2Panel.setBounds(2, 493, 286, 75);
        containerPane.add(operation2Panel);

        //button -/+
        signButton = new JButton();
        signButton.setBounds(5, 5, 90, 67);
        signButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        signButton.setText("-/+");
        //twoButton.setBackground(Color.CYAN);
        signButton.addActionListener(this);
        operation2Panel.add(signButton);
        Buttons[11] = signButton;

        //button 0
        zeroButton = new JButton();
        zeroButton.setBounds(98, 5, 90, 67);
        zeroButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        zeroButton.setText("0");
        zeroButton.addActionListener(this);
        operation2Panel.add(zeroButton);
        Buttons[0] = zeroButton;

        //button .
        dotButton = new JButton();
        dotButton.setBounds(191, 5, 90, 67);
        dotButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
        dotButton.setText(".");
        dotButton.addActionListener(this);
        operation2Panel.add(dotButton);
        Buttons[10] = dotButton;

        //create a panel for = sign
        equalSignPanel = new JPanel();
        equalSignPanel.setLayout(null);
        equalSignPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        equalSignPanel.setBackground(Color.white);
        equalSignPanel.setBounds(293, 493, 92, 75);
        containerPane.add(equalSignPanel);

        //button =
        equalButton = new JButton();
        equalButton.setBounds(2, 2, 90, 73);
        equalButton.setFont(new Font("SansSerif", Font.PLAIN, 80));
        equalButton.setText("=");
        equalButton.addActionListener(this);
        equalSignPanel.add(equalButton);
        Buttons[23] = equalButton;

        //sets the main container(the actual calculator view)
        setSize(400, 610);
        setTitle("Basic Calculator");
        setVisible(true);
        super.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //this method executed the actions when each button is selected
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        //hold value of input prier to modification
        String holdOldValue;
        //iterates through array of buttons to pick the button pressed
        for (int i = 0; i < Buttons.length; i++) {
            //if button pressed matches one of our buttons
            if (src == Buttons[i]) {
                switch (i) {
                    case 10: //when . button is pressed 
                        if ("".equals(inputFromUser)) {
                            inputFromUser = "0";
                        }
                        inputFromUser = inputFromUser + "."; //adds . to user input value
                        computeOperationField.setText(inputFromUser);//displays each input value to user
                        break;
                    case 11: //when -/+ button is pressed
                        char temp = inputFromUser.charAt(0); //gets the first character of user input
                        //if 1st char is - (meaning if the number is already negative)
                        if (temp == '-') {
                            holdOldValue = inputFromUser;
                            inputFromUser = "";
                            inputFromUser = holdOldValue.substring(1);
                            computeOperationField.setText(inputFromUser);
                        } else {
                            holdOldValue = inputFromUser;
                            inputFromUser = "";
                            inputFromUser = "-" + holdOldValue;
                            computeOperationField.setText(inputFromUser);
                        }
                        break;
                    case 12: //when clear is pressed (resets only input holder and text viewer)
                        inputFromUser = "";
                        computeOperationField.setText("0");
                        break;
                    case 13: //when clear all is pressed (resets all corresponding variables and value holders)
                        inputFromUser = "";
                        computeOperationField.setText("0");
                        firstNum = "0";
                        gotFirstNum = 0;
                        computed = false;
                        break;
                    case 14: //when back button is pressed (like a back space keyboard button)
                        inputFromUser = inputFromUser.substring(0, inputFromUser.length() - 1);
                        computeOperationField.setText(inputFromUser);
                        if (inputFromUser.length() == 0) {
                            computeOperationField.setText("0");
                            inputFromUser = "0";
                        }
                        break;
                    //when numbers 0-9 is pressed
                    case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
                        //if user finished inserting the first number to be computed
                        if (computed) {
                            inputFromUser = "";
                        }
                        inputFromUser = inputFromUser + values[i];
                        computeOperationField.setText(inputFromUser);
                        break;
                    case 23: //when = sign is pressed
                        secondNum = inputFromUser;
                        if ("0".equals(secondNum) && "\u00f7".equals(operator)) {
                            computeOperationField.setText("Invalid Input");
                        } else {
                            inputFromUser = Double.toString(calculate(operator, firstNum, secondNum));
                            computeOperationField.setText(inputFromUser);
                            firstNum = inputFromUser;
                            computed = true;
                        }
                        break;
                    default: //when operation signs are pressed
                        //operationProcessField.setText(inputFromUser + Buttons[i].getText());
                        if (i >= 15 && i < 23) { 
                            //includes other operation signs except -,+,*,/,=
                            if (i >= 15 && i < 19) {
                                //special exception for negative numbers in square root
                                if (Float.parseFloat(inputFromUser) < 0 && i == 16) {
                                    computeOperationField.setText("Invalid Input");
                                } else {
                                    operator = Buttons[i].getText();
                                    firstNum = inputFromUser;
                                    inputFromUser = Double.toString(calculate(operator, firstNum, secondNum));
                                    computeOperationField.setText(inputFromUser);
                                    firstNum = inputFromUser;
                                }
                                // when buttons -,+,*,/ are pressed
                            } else { 
                                gotFirstNum++;
                                if (gotFirstNum > 1) {
                                    secondNum = inputFromUser;
                                    if ("0".equals(secondNum) && "\u00f7".equals(operator)) {
                                        computeOperationField.setText("Invalid Input");
                                    } else {
                                        inputFromUser = Double.toString(calculate(operator, firstNum, secondNum));
                                        computeOperationField.setText(inputFromUser);
                                        firstNum = inputFromUser;
                                        secondNum = "";
                                        computed = true;
                                        operator = Buttons[i].getText();
                                    }
                                } else {
                                    operator = Buttons[i].getText();
                                    firstNum = inputFromUser;
                                    inputFromUser = "";
                                    computeOperationField.setText("0");
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    //function to compute the operation
    public double calculate(String op, String firstNum, String secondNum) {
        float calculatedValue = 0;
        if ("+".equals(op)) { //add two values
            calculatedValue = Float.parseFloat(firstNum) + Float.parseFloat(secondNum);
        } else if ("-".equals(op)) { //subtract two values
            calculatedValue = Float.parseFloat(firstNum) - Float.parseFloat(secondNum);
        } else if ("\u00d7".equals(op)) { //multiply two values
            calculatedValue = Float.parseFloat(firstNum) * Float.parseFloat(secondNum);
        } else if ("\u00f7".equals(op)) { //divide two values
            calculatedValue = Float.parseFloat(firstNum) / Float.parseFloat(secondNum);
        } else if ("\u221A".equals(op)) { //square root of a value
            if (Float.parseFloat(firstNum) < 0) {
                calculatedValue = -1;
            } else {
                calculatedValue = (float) Math.sqrt(Float.valueOf(firstNum));
            }
        } else if ("X\u00B2".equals(op)) { // square of a number
            calculatedValue = (Float.parseFloat(firstNum) * Float.parseFloat(firstNum));
        } else if ("1/x".equals(op)) { // 1/x of a number
            calculatedValue = (1 / Float.parseFloat(firstNum));
        } else if ("%".equals(op)){
            calculatedValue = (Float.parseFloat(firstNum)/100);
        }
        
        return calculatedValue;
    }
}

public class BasicCalculator {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.createCalculatorGUI();
    }

}
